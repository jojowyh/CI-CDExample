package com.test.pojo.VO;

import com.test.pojo.entity.Size;
import com.test.pojo.entity.Img;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class GoodsDetailsVO implements Serializable {
    private Long goodsId;

    private String goodsName;

    private Double goodsPrice;

    private String goodsDesc;

    private Long categoryId;

    private String sizeName;
    //库存
    private Integer inventory;

    private String categoryName;

    List <Img> imgs =new ArrayList<>();

}
