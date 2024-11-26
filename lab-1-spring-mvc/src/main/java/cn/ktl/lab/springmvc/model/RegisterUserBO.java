package cn.ktl.lab.springmvc.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @Author lin ho
 * Des: TODO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserBO {

//    @NotNull(message = "user id can not empty")
    private Long id;


    @NotEmpty(message = "userNo can not empty")
    private String userNo;


    @NotEmpty(message = "登录账号不能为空")
    @Length(min = 4, max = 64, message = "账号长度为 4-64 位")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账号格式为数字以及字母")
    private String username;


    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;



    @NotNull(message = "用户类型不能为空")
    private Integer userType;


    @NotEmpty(message = "姓不能为空")
    private String firstName;

    @NotEmpty(message = "名不能为空")
    private String lastName;

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private String phone;

    private String countryOfResidence;

    /**
     * 可以传多个，以 “,”隔开
     */
    private String language;


}
