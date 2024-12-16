package cn.ktl.lab.springmvc.controller;

import cn.ktl.lab.springmvc.base.ResponseResult;
import cn.ktl.lab.springmvc.model.RegisterUserBO;
import cn.ktl.lab.springmvc.service.UserService;
import cn.ktl.lab.springmvc.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author lin ho
 * Des: TODO
 */

@RestController
@RequestMapping("/mock")
@RequiredArgsConstructor
public class MockUserController {

    private final UserService userService;

    @PostMapping("")
    public ResponseResult<Boolean> create(@RequestBody RegisterUserBO userBO) {
        userService.createUser(userBO);
        return ResponseResult.success(Boolean.TRUE);
    }

    @PostMapping("/delete")
    public ResponseResult<Boolean> delete(@RequestBody List<String> emails) {
        userService.batchDeleteUser(emails);
        return ResponseResult.success(Boolean.TRUE);
    }

    @PostMapping("/list")
    public ResponseResult<Boolean> list() {
        userService.handleUserMappingAuth();
        return ResponseResult.success(Boolean.TRUE);
    }

    @GetMapping("/em")
    public ResponseResult<Boolean> employeeIds() {
        userService.queryEmployeeIdOrderDesc();
        return ResponseResult.success(Boolean.TRUE);
    }

    @GetMapping("/kv")
    public ResponseResult<String> kv() {
        userService.getRedisKey();
        return ResponseResult.success( userService.getRedisKey());
    }
}
