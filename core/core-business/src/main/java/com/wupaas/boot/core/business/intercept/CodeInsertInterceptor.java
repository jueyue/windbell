package com.wupaas.boot.core.business.intercept;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 统一插入拦截器
 *
 * @author JueYue on 2017/9/13.
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class CodeInsertInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeInsertInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        //遍历处理所有参数，update方法有两个参数，参见Executor类中的update()方法。
        for (int i = 0; i < args.length; i++) {
            Object parameterObject = args[i];
            //第一个参数处理。根据它判断是否给“操作属性”赋值。
            if (parameterObject instanceof MappedStatement) {
                MappedStatement ms = (MappedStatement) parameterObject;
                SqlCommandType sqlCommandType = ms.getSqlCommandType();
                if (sqlCommandType == SqlCommandType.INSERT) {
                    continue;
                } else {
                    break;
                }
            } else if (parameterObject instanceof Map) {
                Map map = (Map) parameterObject;
                for (Object obj : map.values()) {
                    setProperty(obj);
                }
            } else {//原始参数传入
                setProperty(parameterObject);
            }
        }
        return invocation.proceed();
    }

    private void setProperty(Object parameterObject) {
        //设置参数值
        Method method;
        try {
            if ((method = ReflectionUtils.findMethod(parameterObject.getClass(), "setCrtTime", Date.class)) != null) {
                ReflectionUtils.invokeMethod(method, parameterObject, new Date());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
