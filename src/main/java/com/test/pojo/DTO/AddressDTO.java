package com.test.pojo.DTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @TableName address
 */
@Data
public class AddressDTO implements Serializable {

    private Long userId;

    private String receiver;

    private String phone;

    private String addDetail;


    private static final long serialVersionUID = 1L;
}