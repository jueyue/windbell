package com.wupaas.boot.core.business.annotion;

import java.lang.annotation.*;

/**
 * shiro 鉴权，这个url允许通过
 * @author jueyue
 * @date 2021年2月21日
 * @since 1.0.1
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AuthIgnore {
}
