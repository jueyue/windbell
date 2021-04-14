package com.wupaas.boot.admin.common.exception;

import com.wupaas.boot.core.common.exception.BootException;

/**
 * @author JueYue
 * @Description 业务异常的封装
 * @date 2016年11月12日 下午5:05:10
 */
public class BizException extends BootException {

    public BizException(BizExceptionEnum bizExceptionEnum) {
        super(bizExceptionEnum.getCode(), bizExceptionEnum.getMessage(), "");
    }
}
