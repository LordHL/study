package cn.ktl.lab.springmvc.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * @description  Entity基础类
 * @date 2020-11-26
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 删除标识
     **/
    @TableField(value = "is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField(value = "created_by",fill = FieldFill.INSERT)
    private String createdBy;

    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @TableField(value = "deleted_by",fill = FieldFill.INSERT_UPDATE)
    private String deletedBy;

    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private LocalDateTime createdTime;


    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableField(value = "deleted_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime deletedTime;

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                builder.append(f.getName(), f.get(this)).append("\n");
            }
        } catch (Exception e) {
            builder.append("toString builder encounter an error");
        }
        return builder.toString();
    }

}
