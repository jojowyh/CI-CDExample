package com.test.pojo.DTO;

import com.test.pojo.entity.Img;
import com.test.pojo.entity.Size;

import com.test.pojo.list.ImgList;
import com.test.pojo.list.SizeList;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class InsertGoodsDTO implements Serializable {
    private Long goodsId;


    private String goodsName;

    private Double goodsPrice;

    private String goodsDesc;

    //分类名字
    private String categoryName;

    private Integer inventory;

    private String sizeName;

    //图片路径
    private List<Img> imgList=new ArrayList<>();

}
