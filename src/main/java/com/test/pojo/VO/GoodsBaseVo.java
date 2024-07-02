package com.test.pojo.VO;

import com.test.pojo.entity.Img;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GoodsBaseVo implements Serializable {
    private Long goodsId;

    private String goodsName;

    private Double goodsPrice;

    private Long categoryId;

    private String CategoryName;

    private String src;

    private String sizeName;

    private Integer inventory;
}
