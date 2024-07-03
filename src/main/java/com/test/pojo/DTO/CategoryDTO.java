package com.test.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.pojo.entity.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class CategoryDTO implements Serializable {
    private Long categoryId;

    private String categoryName;

    private Integer categoryGrade;

    private List<String>sizeNameList=new ArrayList<>();

}
