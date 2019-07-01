package cn.afterturn.boot.bussiness.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 带有ID的基础对象
 *
 * @author by jueyue on 18-6-15.
 */
@Data
public abstract class IdBaseModel<T> extends BaseModel {

    @TableId(value = "ID", type = IdType.ID_WORKER_STR)
    @ApiModelProperty("Id")
    /** 解决前端 LONG 失真问题
     @JSONField(serializeUsing = ToStringSerializer.class)**/
    private String id;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
