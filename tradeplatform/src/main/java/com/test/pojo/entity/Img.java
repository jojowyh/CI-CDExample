package com.test.pojo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName img
 */
@Data
public class Img implements Serializable {
    private Long imgId;

    private String src;

    private Long goodsId;

    private Integer isDefault;

    private static final long serialVersionUID = 1L;
}