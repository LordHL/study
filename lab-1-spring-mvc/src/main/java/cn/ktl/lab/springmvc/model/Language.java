package cn.ktl.lab.springmvc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName um_language
 */
@TableName(value = "um_language")
@Data
public class Language implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 语言名称
     */
    @TableField(value = "value")
    private String value;

    /**
     * 所属上级, 顶级为-1
     */
    @TableField(value = "main_id")
    private Integer mainId;

    /**
     * 语言编码
     */
    @TableField(value = "local_code")
    private String localCode;

    /**
     * 语言编码
     */
    @TableField(value = "status")
    private Integer status;


    /**
     * 语言编码
     */
    @TableField(value = "sort")
    private Integer sort;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}