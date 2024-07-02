package com.test.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName address
 */
@Data
public class Address implements Serializable {
    private Long addressId;

    private Long userId;

    private String receiver;

    private String phone;

    private String addDetail;

    private Integer isDefaul;

    private static final long serialVersionUID = 1L;
}