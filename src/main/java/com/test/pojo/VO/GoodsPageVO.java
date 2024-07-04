package com.test.pojo.VO;

import com.test.pojo.entity.Img;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class GoodsPageVO implements Serializable {
    private Long goodsId;

    private String goodsName;

    private Double goodsPrice;

    private String goodsDesc;

    private Long categoryId;

    private String sizeName;
    //库存
    private Integer inventory;

    private String categoryName;

    private String src;
}
