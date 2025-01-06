package cn.ktl.lab.springmvc.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName um_country
 */
@TableName(value ="um_country")
@Data
public class Country implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * ISO国家二位字母代码
     */
    @TableField(value = "iso2")
    private String iso2;

    /**
     * 所属区域id
     */
    @TableField(value = "subregion_id")
    private Integer subregionId;

    /**
     * 所属区域名称(例如: Eastern Asia)
     */
    @TableField(value = "subregion_name")
    private String subregionName;

    /**
     * 所属区域名称
     */
    @TableField(value = "region_id")
    private Integer regionId;

    /**
     * 所属区域名称(例如: Asia)
     */
    @TableField(value = "region_name")
    private String regionName;

    /**
     * 国家名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * ISO国家三位字母代码
     */
    @TableField(value = "iso3")
    private String iso3;

    /**
     * ISO国家数字代码
     */
    @TableField(value = "numeric_code")
    private String numericCode;

    /**
     * 手机区号
     */
    @TableField(value = "phone_code")
    private String phoneCode;

    /**
     * 首都
     */
    @TableField(value = "capital")
    private String capital;

    /**
     * 货币名称
     */
    @TableField(value = "currency")
    private String currency;

    /**
     * 
     */
    @TableField(value = "currency_name")
    private String currencyName;

    /**
     * 货币符号
     */
    @TableField(value = "currency_symbol")
    private String currencySymbol;

    /**
     * 顶级域名
     */
    @TableField(value = "tld")
    private String tld;

    /**
     * 本地名称
     */
    @TableField(value = "native")
    private String nativeName;

    /**
     * 国际名称
     */
    @TableField(value = "nationality")
    private String nationality;

    /**
     * 纬度
     */
    @TableField(value = "latitude")
    private String latitude;

    /**
     * 经度
     */
    @TableField(value = "longitude")
    private String longitude;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    private Date updatedAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}