package com.wupaas.boot.bussiness.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 带有ID的基础对象
 *
 * @author by jueyue on 18-6-15.
 */
public abstract class IdAutoModel<T> extends BaseModel {

    @TableId(value = "ID", type = IdType.AUTO)
    @ApiModelProperty("Id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
