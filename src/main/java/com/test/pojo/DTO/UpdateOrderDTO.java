package com.test.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName address
 */
@Data
public class UpdateOrderDTO implements Serializable {

    private Long ordersId;

    private Integer state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}