package com.test.pojo.DTO;

import com.test.pojo.entity.Size;
import com.test.pojo.entity.Img;
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
    //图片
    private List<Img> imgList= new ArrayList<>();
    //类别
    private List<Size> sizeList=new ArrayList<>();


    //分类id
    private Long categoryId;

}
