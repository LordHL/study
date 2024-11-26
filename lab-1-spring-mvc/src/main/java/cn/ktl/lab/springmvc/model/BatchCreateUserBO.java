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
public class BatchCreateUserBO {

    private List<RegisterUserBO> users;

}
