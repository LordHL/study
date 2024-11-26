package cn.ktl.lab.springmvc.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author lin ho
 * Des: TODO
 */


@Data
@TableName("um_user_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserGroup implements Serializable {

    private static final long serialVersionUID = -3836401769559845765L;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "group_id")
    private Long groupId;

    // 用来做排序用的
    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;
}
