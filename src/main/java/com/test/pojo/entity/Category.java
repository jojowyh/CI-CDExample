package com.test.pojo.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * @TableName category
 */
@Data
public class Category implements Serializable {
    private Long categoryId;

    private String categoryName;

    private Integer categoryGrade;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;



    private static final long serialVersionUID = 1L;
}