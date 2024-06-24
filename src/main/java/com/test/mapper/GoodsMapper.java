package com.test.mapper;

import com.test.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86187
* @description 针对表【goods(商品表)】的数据库操作Mapper
* @createDate 2024-06-24 12:16:16
* @Entity com.test.pojo.Goods
*/
@Mapper
public interface GoodsMapper {
    @Select("select * from trade_platform.goods")
    List<Goods> query();
}




