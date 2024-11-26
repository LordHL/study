package cn.ktl.lab.springmvc.model;

import cn.ktl.lab.springmvc.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName um_group
 */
@TableName(value = "um_group")
@Data
public class Group extends BaseEntity implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     *
     */
    @TableField(value = "group_description")
    private String groupDescription;

    /**
     * group type：Internal and External
     * 10-Internal,11-External
     */
    @TableField(value = "group_type")
    private Integer groupType;

    /**
     * 是否为默认字段
     **/
    @TableField(value = "is_default")
    private Integer isDefault;

    /**
     * status： 1: Active and 0: Inactive
     */
    @TableField(value = "group_status")
    private Integer groupStatus;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}