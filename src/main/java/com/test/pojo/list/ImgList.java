package com.test.pojo.list;

import com.test.pojo.entity.Img;
import com.test.pojo.entity.Size;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ImgList implements Serializable {

   private String src;

}
