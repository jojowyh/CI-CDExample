package com.test.pojo.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailVo implements Serializable {
    private Long goodsId;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsNumber;
    //订单总价
    private Double amount;
    //图片路径
    private String src;
}
