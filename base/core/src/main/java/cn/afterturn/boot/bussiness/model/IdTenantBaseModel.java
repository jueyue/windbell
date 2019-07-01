package cn.afterturn.boot.bussiness.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 带有租户基础对象
 *
 * @author by jueyue on 18-6-15.
 */
@Data
public abstract class IdTenantBaseModel<T> extends IdBaseModel {

    @ApiModelProperty("租户")
    @TableField("TENANT_ID")
    private String tenantId;

    @TableField(exist = false)
    @ApiModelProperty("租户名称")
    private String tenantName;

}
