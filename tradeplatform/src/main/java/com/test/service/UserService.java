package com.test.service;

import com.test.common.Result;
import com.test.pojo.VO.UserUpdateVO;
import com.test.pojo.entity.User;

public interface UserService {


    /**
     * 用户登录
     *
     * @return
     */
    Result login(User user);
    /**
     * 用户注册
     * @return
     */
    Result regist(User user);

    /**
     * 根据手机号查询用户
     * @param userPhone
     * @return
     */
    Result findByPhone(String userPhone);
    /**
     * 修改密码
     * @param userUpdateVO
     * @return
     */
    Result update(UserUpdateVO userUpdateVO);
}
