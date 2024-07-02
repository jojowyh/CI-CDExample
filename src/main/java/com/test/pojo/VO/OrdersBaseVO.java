package com.test.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName orders
 */
@Data
public class OrdersBaseVO implements Serializable {
    private Long ordersId;

    private Long userId;

    //订单状态
    private Integer state;

    private Double amount;

    private String receiver;

    private String phone;

    private String addDetail;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}