package com.test.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName orders_detail
 */
@Data
public class OrdersDetail implements Serializable {
    private Long detailId;

    private Long goodsId;

    private Integer goodsNumber;

    private Long ordersId;

    private Long sizeId;

    private static final long serialVersionUID = 1L;
}