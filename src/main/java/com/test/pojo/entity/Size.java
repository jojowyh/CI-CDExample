package com.test.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName size
 */
@Data
public class Size implements Serializable {
    private Long sizeId;

    private String sizeName;

    private Long categoryId;



    private static final long serialVersionUID = 1L;
}