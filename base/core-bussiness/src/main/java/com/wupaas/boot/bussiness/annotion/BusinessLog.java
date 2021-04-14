package com.wupaas.boot.bussiness.annotion;

import java.lang.annotation.*;

/**
 * 标记需要做业务日志的方法
 *
 * @author JueYue
 * @date 2017-03-31 12:46
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface BusinessLog {

    /**
     * 业务的名称,例如:"修改菜单"
     */
    String value() default "";

    /**
     * 被修改的实体的唯一标识,例如:菜单实体的唯一标识为"id"
     */
    String key() default "id";

    /**
     * 字典(用于查找key的中文名称和字段的中文名称)
     */
    String dict() default "SystemDict";
}
