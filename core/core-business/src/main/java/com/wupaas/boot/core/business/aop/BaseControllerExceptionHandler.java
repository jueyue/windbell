package com.wupaas.boot.core.business.aop;

import com.wupaas.boot.core.business.response.ErrorResponse;
import com.wupaas.boot.core.exception.BootException;
import com.wupaas.boot.core.exception.BootExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author JueYue
 * @date 2016年11月12日 下午3:19:56
 */
public class BaseControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     *
     * @author JueYue
     */
    @ExceptionHandler(BootException.class)
    @ResponseBody
    public ErrorResponse notFount(BootException e) {
        log.error("业务异常:", e);
        return new ErrorResponse(e.getCode(), e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     *
     * @author JueYue
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse valid(MethodArgumentNotValidException e) {
        log.error("请求参数异常:", e);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < e.getBindingResult().getFieldErrors().size(); i++) {
            sb.append(e.getBindingResult().getFieldErrors().get(i).getField());
            sb.append(e.getBindingResult().getFieldErrors().get(i).getDefaultMessage());
            sb.append(";");
        }
        return new ErrorResponse(BootExceptionEnum.REQUEST_ERROR.getCode(), BootExceptionEnum.REQUEST_ERROR.getMessage() + ",详情: " + sb.toString());
    }

    /**
     * 拦截未知的运行时异常
     *
     * @author JueYue
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorResponse notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return new ErrorResponse(BootExceptionEnum.SERVER_ERROR.getCode(), BootExceptionEnum.SERVER_ERROR.getMessage());
    }

}
