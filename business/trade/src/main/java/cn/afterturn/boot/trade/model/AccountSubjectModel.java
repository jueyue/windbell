package cn.afterturn.boot.trade.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;


import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户科目表
 *
 * @author
 * @Date
 */
@Data
@TableName("account_subject")
public class AccountSubjectModel extends Model<AccountSubjectModel> {

    private static final long serialVersionUID = 1L;


    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 科目Id
     */
    @TableField(value = "subject_id")
    private String subjectId;
    /**
     * 科目名称
     */
    @TableField(value = "subject_name")
    private String subjectName;
    /**
     * 科目方向
     */
    @TableField(value = "direction")
    private Integer direction;
    /**
     * 创建时间
     */
    @TableField(value = "crt_time")
    private Date crtTime;
    /**
     * 创建用户
     */
    @TableField(value = "crt_user_id")
    private String crtUserId;
    /**
     * 修改时间
     */
    @TableField(value = "mdf_time")
    private Date mdfTime;
    /**
     * 修改用户
     */
    @TableField(value = "mdf_user_id")
    private String mdfUserId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
