package com.test.service.impl;

import com.test.common.DateUtils;
import com.test.common.MD5Util;
import com.test.common.Result;
import com.test.mapper.UserMapper;
import com.test.pojo.DTO.UserRegisterDTO;
import com.test.pojo.VO.UserUpdateVO;
import com.test.pojo.VO.UserVO;
import com.test.pojo.entity.User;
import com.test.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        return Result.success("登录成功");
    }
    /**
     * 用户注册
     * @return
     */
    @Override
    public Result regist(UserRegisterDTO userRegisterDTO) {
        //判断该手机号是否已注册
        User Newuser = new User();
        Newuser.setUserPhone(userRegisterDTO.getUserPhone());
        if (userRegisterDTO.getUserName().isEmpty()) {
            return Result.error("用户名为空");
        }
        if (userRegisterDTO.getUserPhone().isEmpty()) {
            return Result.error("手机号为空");
        }
        if (userRegisterDTO.getUserPwd().isEmpty()) {
            return Result.error("密码为空");
        }

        User userByCondition=userMapper.getByCondition(Newuser);
        if (userByCondition != null ){
            return Result.error("该用户已注册");
        }
        //设置密码为MD5加密后的密码
        Newuser.setUserPwd(MD5Util.encrypt(userRegisterDTO.getUserPwd()));
        Newuser.setUserName(userRegisterDTO.getUserName());
        Newuser.setCreateTime(DateUtils.now());
        Newuser.setUpdateTime(DateUtils.now());
        //存入数据库
        userMapper.insert(Newuser);

        return Result.success("注册成功");
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

    @Override
    @Transactional
    public Result delete(Long userId) {
        User user = new User();
        user.setUserId(userId);
        User queryUser = userMapper.getByCondition(user);
        if (queryUser == null){
            return Result.error("查无此人");
        }
        userMapper.delete(userId);
        return Result.success("删除成功");
    }

    @Override
    public Result<List<UserVO>> query() {
        List <UserVO>userVOList=new ArrayList<>();
       List<User>userList= userMapper.query();
       userList.forEach(user -> {
           UserVO userVO = new UserVO();
           BeanUtils.copyProperties(user,userVO);

           userVOList.add(userVO);
       });
        return Result.success(userVOList);
    }


}
