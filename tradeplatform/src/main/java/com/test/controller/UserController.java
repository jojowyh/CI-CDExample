package com.test.controller;

import com.test.common.Result;
import com.test.pojo.VO.UserUpdateVO;
import com.test.pojo.entity.User;
import com.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户管理")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result userLogin(@RequestBody User user){
            log.info("用户登录:{}",user);
            return userService.login(user);

    }

    /**
     * 用户注册
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result userRegister(@RequestBody User user){
        log.info("用户注册:{}",user);
        return userService.regist(user);
    }

    /**
     * 根据手机号查找账号
     * @param userPhone
     * @return
     */
    @GetMapping("/find/{userPhone}")
    @ApiOperation("根据手机号查找账号")
    public Result findByPhone(@PathVariable String userPhone){

        log.info("根据手机号查找账号:{}",userPhone);
        return userService.findByPhone(userPhone);
    }

    /**
     * 修改密码
     * @param userUpdateVO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改密码")
    public Result update(@RequestBody UserUpdateVO userUpdateVO){
        log.info("用户修改密码");
        return userService.update(userUpdateVO);
    }

}
