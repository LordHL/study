package cn.ktl.lab.springmvc.enums;

import lombok.Getter;

/**
 * @Author lin ho
 * Des: UserType
 */

@Getter
public enum UserTypeEnum {

    CROWD(10,"Contrator ID","crowd"),
    PROFESSIONALS(20,"Contrator ID","professionals"),
    AGENCIES(30,"Agency ID","agencies"),
    EMPLOYEES(40,"Employee ID","employees"),
    ;

    private final int value;

    private final String userIdName;

    private final String desc;

    UserTypeEnum(int value, String userIdName, String desc) {
        this.value = value;
        this.userIdName = userIdName;
        this.desc = desc;
    }

    /**
     * 基于user type 获取userIdName
     * @param userType userType
     * @return 获取userIdName
     */
    public static UserTypeEnum getUserTypeEnumByUserType(Integer userType){
        if (userType != null) {
            for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
                if (userTypeEnum.value == userType) {
                    return userTypeEnum;
                }
            }
        }
        return null;
    }
}
