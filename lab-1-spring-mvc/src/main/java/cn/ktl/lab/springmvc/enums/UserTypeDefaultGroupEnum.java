package cn.ktl.lab.springmvc.enums;

import lombok.Getter;

/**
 * 用户类型对应这应的默认分组name,
 * 用来映射用户注册时的默认分组
 * <p>
 * OV2-7258
 * <p>
 * Des: UserType
 */

@Getter
public enum UserTypeDefaultGroupEnum {

    CROWD(UserTypeEnum.CROWD.getValue(), "Crowd"),
    PROFESSIONALS(UserTypeEnum.PROFESSIONALS.getValue(), "Professional"),
    AGENCIES(UserTypeEnum.AGENCIES.getValue(), "Agency"),
    EMPLOYEES(UserTypeEnum.EMPLOYEES.getValue(), "Employee"),
    ;
    private final int value;

    private final String groupName;

    UserTypeDefaultGroupEnum(int value, String groupName) {
        this.value = value;
        this.groupName = groupName;
    }

    /**
     * 基于user type 获取默认分组
     *
     * @param userType userType
     * @return 获取userIdName
     */
    public static UserTypeDefaultGroupEnum getUserTypeEnumByUserType(Integer userType) {
        if (userType != null) {
            for (UserTypeDefaultGroupEnum userTypeDefaultGroupEnum : UserTypeDefaultGroupEnum.values()) {
                if (userTypeDefaultGroupEnum.value == userType) {
                    return userTypeDefaultGroupEnum;
                }
            }
        }
        return null;
    }
}
