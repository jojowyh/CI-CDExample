package com.test.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.pojo.entity.Img;
import com.test.pojo.entity.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class CategoryVO implements Serializable {
    private Long categoryId;

    private String categoryName;

    private Integer categoryGrade;

    private List<Size>sizeList=new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

}
