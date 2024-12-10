package cn.ktl.lab.springmvc.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lin ho
 * Des: DynamicEmployee
 */

@Data
@TableName("um_dynamic_employee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DynamicEmployee {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "full_name")
    private String fullName;

    @TableField(value = "employee_manager_id")
    private String employeeManagerId;


    @TableField(value = "employee_first_name")
    private String employeeFirstName;

    @TableField(value = "employee_last_name")
    private String employeeLastName;


    @TableField(value = "email")
    private String email;

    @TableField(value = "employee_id")
    private String employeeId;

    @TableField(value = "time_zone")
    private String timeZone;

}
