package cn.ktl.lab.springmvc.service;

/**
 * @Author lin ho
 * Des: TODO
 */
import cn.ktl.lab.springmvc.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserVO get(Integer id) {
        return new UserVO().setId(id).setUsername("test");
    }

}
