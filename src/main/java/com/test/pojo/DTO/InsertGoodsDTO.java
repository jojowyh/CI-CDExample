package com.test.pojo.DTO;

import com.test.pojo.entity.Img;
import com.test.pojo.entity.Size;

import com.test.pojo.list.ImgList;
import com.test.pojo.list.SizeList;
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

    //图片路径
    private List<Img> imgList=new ArrayList<>();
    //类别
    private List<Size> sizeList=new ArrayList<>();

    //分类id
    private Long categoryId;

    //分类名字
    private String categoryName;

}
