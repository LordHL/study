package cn.ktl.lab.springmvc.controller;

import cn.ktl.lab.springmvc.dto.UserAddDTO;
import cn.ktl.lab.springmvc.dto.UserUpdateDTO;
import cn.ktl.lab.springmvc.service.UserService;
import cn.ktl.lab.springmvc.vo.UserVO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @Author lin ho
 * Des: TODO
 */
class UserControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        List<UserVO> result = userController.list();
        Assertions.assertEquals(result.size(), 3);
    }

    @Test
    void testGet() {
        UserVO result = userController.get(Integer.valueOf(0));
        Assertions.assertEquals(new UserVO(), result);
    }

    @Test
    void testGet2() {
        when(userService.get(anyInt())).thenReturn(new UserVO());

        UserVO result = userController.get2(Integer.valueOf(0));
        Assertions.assertEquals(new UserVO(), result);
    }

    @Test
    void testAdd() {
        Integer result = userController.add(new UserAddDTO());
        Assertions.assertEquals(Integer.valueOf(0), result);
    }

    @Test
    void testUpdate() {
        Boolean result = userController.update(Integer.valueOf(0), new UserUpdateDTO());
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testDelete() {
        Boolean result = userController.delete(Integer.valueOf(0));
        Assertions.assertEquals(Boolean.TRUE, result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme