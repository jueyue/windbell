package cn.afterturn.boot.paas.common.aop;

import cn.afterturn.boot.bussiness.exception.BootException;
import cn.afterturn.boot.bussiness.response.ErrorResponse;
import cn.afterturn.boot.core.aop.BaseControllerExceptionHandler;
import cn.afterturn.boot.paas.common.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author JueYue
 * @date 2016年11月12日 下午3:19:56
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ErrorResponse bizException(BootException e) {
        log.error("业务异常:", e);
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

}
