package com.test.service.impl;

import com.test.common.DateUtils;
import com.test.common.MD5Util;
import com.test.common.Result;
import com.test.mapper.UserMapper;
import com.test.pojo.VO.UserUpdateVO;
import com.test.pojo.entity.User;
import com.test.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result login(User user) {
        //通过手机号查询是否已注册
        User Newuser = new User();
        Newuser.setUserPhone(user.getUserPhone());
        User userByCondition=userMapper.getByCondition(Newuser);
        if (userByCondition == null){
            return Result.error("该手机号未注册");
        }
        //加密传过来的明文密码与用户密码比较
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        if (!user.getUserPwd() .equals(userByCondition.getUserPwd()) ){
            return Result.error("密码错误");
        }
        return Result.success();
    }
    /**
     * 用户注册
     * @return
     */
    @Override
    public Result regist(User user) {
        //判断该手机号是否已注册
        User Newuser = new User();
        Newuser.setUserPhone(user.getUserPhone());
        User userByCondition=userMapper.getByCondition(Newuser);
        if (userByCondition != null ){
            return Result.error("该用户已注册");
        }
        //设置密码为MD5加密后的密码
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        user.setCreateTime(DateUtils.now());
        user.setUpdateTime(DateUtils.now());
        //存入数据库
        userMapper.insert(user);

        return Result.success();
    }

    @Override
    public Result findByPhone(String userPhone) {
        //userMapper.
        User Newuser = new User();
        Newuser.setUserPhone(userPhone);
        User userByCondition=userMapper.getByCondition(Newuser);
        if (userByCondition == null ){
            return Result.error("查询不到该用户");
        }
        userByCondition.setUserPwd("");
        return Result.success(userByCondition);
    }

    @Override
    public Result update(UserUpdateVO userUpdateVO) {
        //密码加密
        User user=new User();
        user.setUserId(userUpdateVO.getUserId());
        String pwd = userMapper.getByCondition(user).getUserPwd();
        String NewPwd = MD5Util.encrypt(userUpdateVO.getUserPwd());
        if (pwd.equals(NewPwd)){
            return Result.error("原密码和新密码相同");
        }

        BeanUtils.copyProperties(userUpdateVO,user);
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        user.setUpdateTime(DateUtils.now());
        //修改密码
        userMapper.update(user);
        return Result.success("修改密码成功");
    }


}
