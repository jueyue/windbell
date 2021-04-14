package com.wupaas.boot.bussiness.security.shiro;


import com.wupaas.boot.bussiness.cache.RedisUtil;
import com.wupaas.boot.bussiness.context.EnvironmentContextHolder;
import com.wupaas.boot.bussiness.security.jwt.JwtUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @description: shiro 自定义filter 实现 并发登录控制
 */
public class LimitConcurrentSessionControlFilter extends AccessControlFilter {


    /**
     * 踢出之前登录的/之后登录的用户 默认踢出之前登录的用户
     */
    private boolean kickoutAfter = false;

    /**
     * 同一个帐号最大会话数 默认1
     */
    private int maxSession = getMaxSession();
    private Cache<String, Deque<Serializable>> cache;

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }


    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-activeSessionCache");
    }

    /**
     * 是否允许访问，返回true表示允许
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }


    /**
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            //如果没有登录，直接进行之后的流程
            return true;
        }
        Session session = subject.getSession();
        String token = (String) subject.getPrincipal();
        String loginName = JwtUtil.getUserName(token);
        Serializable sessionId = session.getId();

        // 初始化用户的队列放到缓存里
        Deque<Serializable> deque = (Deque<Serializable>) RedisUtil.get(loginName);
        if (deque == null) {
            deque = new LinkedList<Serializable>();
            RedisUtil.put(loginName, deque);
        }

        //如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
            RedisUtil.put(loginName, deque);
        }
        //如果队列里的sessionId数超出最大会话数，开始踢人
        if ("1".equals(getMaxSession())) {
            while (deque.size() > maxSession) {
                Serializable kickoutSessionId = null;
                if ("2".equals(getMaxSession())) { //如果踢出后者
                    kickoutSessionId = deque.removeFirst();
                    try {
                    } catch (Exception e) {//ignore exception
                        e.printStackTrace();
                    }
                } else { //否则踢出前者
                    kickoutSessionId = deque.removeLast();
                    try {
                    } catch (Exception e) {//ignore exception
                        e.printStackTrace();
                    }
                }

            }
        }


        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            //会话被踢出了
            try {
                subject.logout();
            } catch (Exception e) {
            }
            request.getRequestDispatcher("/403/2").forward(request, response);
            return false;
        }

        //如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("forbid") != null) {
            //会话被踢出了
            try {
                subject.logout();

            } catch (Exception e) {
            }
            request.getRequestDispatcher("/403/1").forward(request, response);
            return false;
        }
        return true;
    }

    public int getMaxSession() {
        try {
            return EnvironmentContextHolder.getLongVal("shiro.limit.session").intValue();
        } catch (Exception e) {
            return 1;
        }
    }
}
