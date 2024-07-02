package com.test.service;

import com.test.common.Result;
import com.test.pojo.DTO.UserRegisterDTO;
import com.test.pojo.VO.UserUpdateVO;
import com.test.pojo.VO.UserVO;
import com.test.pojo.entity.User;

import java.util.List;

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
    Result regist(UserRegisterDTO userRegisterDTO);

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

    /**
     * 删除用户
     * @param userId
     * @return
     */
    Result delete(Long userId);

    Result<List<UserVO>> query();

    /**
     * 查询所有用户
     *
     * @return
     */

}
