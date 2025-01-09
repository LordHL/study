package cn.ktl.lab.springmvc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author lin ho
 * Des: TODO
 */
@Setter
@Getter
public class UserGroupVO {

    private Long id;
    private String email;

    private List<String> GroupNames;
}
