package cn.ktl.lab.springmvc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 * @TableName um_dict
 */
@TableName(value ="um_dict")
@Data
public class Dict  implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;


    /**
     * 值
     */
    @TableField(value = "value")
    private String value;


    /**
     * 编码
     */
    @TableField(value = "code")
    private String code;

    /**
     * 父编码
     */
    @TableField(value = "parent_code")
    private String parentCode;

    /**
     * 排序, 仅针对dict item有效
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 状态, （0-Inactive，1-Active）
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 简介
     */
    @TableField(value = "description")
    private String description;

}