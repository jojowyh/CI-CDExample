package com.test.controller;

import com.test.common.Result;
import com.test.pojo.DTO.UserRegisterDTO;
import com.test.pojo.VO.UserUpdateVO;
import com.test.pojo.VO.UserVO;
import com.test.pojo.entity.User;
import com.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result userRegister(@RequestBody UserRegisterDTO userRegisterDTO){
        log.info("用户注册:{}",userRegisterDTO);
        return userService.regist(userRegisterDTO);
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
        log.info("用户修改密码:{}",userUpdateVO);
        return userService.update(userUpdateVO);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @DeleteMapping("/delete/{userId}")
    @ApiOperation("删除用户")
    public Result delete(@PathVariable Long userId){
        log.info("删除用户,用户id:{}",userId);
        return userService.delete(userId);
    }

    /**
     * 查询所有用户
     * @param
     * @return
     */
    @GetMapping("query")
    @ApiOperation("查询所有用户")
    public Result<List<UserVO>> query(){
        log.info("查询所有用户");
        return userService.query();
    }


}
