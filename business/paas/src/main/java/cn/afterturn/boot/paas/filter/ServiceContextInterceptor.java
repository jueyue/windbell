package cn.afterturn.boot.paas.filter;

import cn.afterturn.boot.core.aop.RequestWrapper;
import cn.afterturn.boot.facade.paas.im.model.PaasBaseRequestModel;
import cn.afterturn.boot.paas.common.context.ThirdServiceContext;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 上下文拦截器,免去代码
 *
 * @author jueyue on 20-2-10.
 */
public class ServiceContextInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String         body           = requestWrapper.getBody();
        try {
            PaasBaseRequestModel model = JSON.parseObject(body, PaasBaseRequestModel.class);
            ThirdServiceContext.put(ThirdServiceContext.TENANT_ID, model.getTenantId());
            ThirdServiceContext.put(ThirdServiceContext.APP_CODE, model.getAppCode());
        } catch (Exception e) {
        }
        return true;
    }
}
