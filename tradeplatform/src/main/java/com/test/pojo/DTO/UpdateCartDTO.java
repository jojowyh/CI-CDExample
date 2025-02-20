package com.test.pojo.DTO;

import lombok.Data;

@Data
public class UpdateCartDTO {

    private Long  userId;

    private Long  goodsId;

    private Long  sizeId;
}
