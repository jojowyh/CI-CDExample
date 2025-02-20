package com.test.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName user
 */
@Data
public class User implements Serializable {

    private Long userId;

    private String userName;

    private String userPhone;

    private String userPwd;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}