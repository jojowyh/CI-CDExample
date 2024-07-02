package com.test.pojo.list;

import lombok.Data;

import java.io.Serializable;

@Data
public class SizeList implements Serializable {

   private Integer inventory;
   private String sizeName;

}
