package com.wupaas.boot.bussiness.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 基础对象
 *
 * @author JueYue on 2017/9/14.
 */
@Data
public abstract class BaseModel<T> extends Model {

    public final static String BASE_RESULT_MAP = "BaseResultMap";

    @TableField(value = "CRT_USER_ID")
    @ApiModelProperty(value = "创建人Id(基础字段,新增修改勿填)")
    private String crtUserId;

    @TableField(exist = false)
    @ApiModelProperty("创建人名字(基础字段,新增修改勿填)")
    private String crtUserName;

    @TableField(value = "CRT_TIME")
    @ApiModelProperty("创建时间(基础字段,新增修改勿填)")
    private Date crtTime;

    @TableField(value = "MDF_USER_ID")
    @ApiModelProperty("修改人Id(基础字段,新增修改勿填)")
    private String mdfUserId;

    @TableField(exist = false)
    @ApiModelProperty("修改人名字(基础字段,新增修改勿填)")
    private String mdfUserName;

    @TableField(value = "MDF_TIME")
    @ApiModelProperty("修改时间(基础字段,新增修改勿填)")
    private Date mdfTime;

}
