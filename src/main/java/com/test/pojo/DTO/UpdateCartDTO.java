package com.test.pojo.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateCartDTO implements Serializable {

    private Long  userId;

    private Long  goodsId;

    private Long  sizeId;
}
