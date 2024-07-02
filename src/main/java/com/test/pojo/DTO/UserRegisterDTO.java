package com.test.pojo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterDTO implements Serializable {

    private String userName;
    private String userPhone;
    private String userPwd;

}
