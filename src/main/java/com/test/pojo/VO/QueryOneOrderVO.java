package com.test.pojo.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class QueryOneOrderVO implements Serializable {
    private Long goodsId;

    private String goodsName;

    private Double goodsPrice;

    private Integer goodsNumber;

    private Long sizeId;

    private String size_name;

}
