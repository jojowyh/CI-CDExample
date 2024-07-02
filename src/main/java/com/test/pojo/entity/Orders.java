package com.test.pojo.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName orders
 */
@Data
public class Orders implements Serializable {
    private Long ordersId;

    private Long userId;

    //订单状态
    private Integer state;

    private Double amount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}