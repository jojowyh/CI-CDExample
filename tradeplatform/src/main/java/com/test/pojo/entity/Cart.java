package com.test.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName cart
 */
@Data
public class Cart implements Serializable {
    private Long cartId;

    private Long userId;

    private Long goodsId;

    private Integer goodNumber;

    private Long sizeId;

    private static final long serialVersionUID = 1L;
}