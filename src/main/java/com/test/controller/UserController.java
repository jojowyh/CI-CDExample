package com.test.controller;

import com.test.common.Result;
import com.test.pojo.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @return
     */
    @PostMapping("/login")
    public Result<User> userLogin(@RequestBody User user){
            User user1=userService.login(user);
            return Result.success(user1);
    }

}
