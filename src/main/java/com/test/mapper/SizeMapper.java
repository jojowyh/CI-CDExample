package com.test.mapper;

import com.test.pojo.entity.Size;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86187
* @description 针对表【size(规格表)】的数据库操作Mapper
* @createDate 2024-06-25 10:10:20
* @Entity com.test.pojo.Size
*/
@Mapper
public interface SizeMapper {
    /**
     * 查询对应商品规格
     * @param goodsId
     * @return
     */
    @Select("select * from trade_platform.size where goods_id = #{goodsId}")
    List<Size> queryById(Long goodsId);



    /**
     * 添加对应商品规格
     * @param sizeList
     */
    void insertBatch(List<Size> sizeList);

    /**
     * 根据商品id删除商品规格
     * @param goodsId
     */
    @Delete("delete from size where goods_id = #{goodsId}")
    void delete(Long goodsId);

    /**
     * 根据条件查询一个规格
     * @param size
     * @return
     */

    @Select("select * from trade_platform.size where size_id = #{sizeId} and goods_id = #{goodsId}")
    Size query(Size size);
}




