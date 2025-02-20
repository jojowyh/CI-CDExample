package com.test.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName goods
 */
@Data
public class Goods implements Serializable {
    private Long goodsId;

    private String goodsName;

    private Double goodsPrice;

    private String goodsDesc;

    private Long categoryId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}