package com.test.pojo.DTO;

import lombok.Data;

import java.io.Serializable;
@Data
public class GoodPageDTO implements Serializable {
    private String goodsName;

    private String categoryName;

    private int page;

    private int pageSize;
}
