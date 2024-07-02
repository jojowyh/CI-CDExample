package com.test.pojo.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateVO implements Serializable {

    private Long userId;

    private String userName;

    private String userPwd;

}
