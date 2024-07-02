package com.test.pojo.DTO;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class InsertCategoryDTO implements Serializable {

    private String categoryName;

    private Integer categoryGrade;

    private List<String> SizeNameList=new ArrayList<>();
}
