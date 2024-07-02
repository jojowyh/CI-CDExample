package com.test.mapper;

import com.test.pojo.entity.Img;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86187
* @description 针对表【img(商品图片)】的数据库操作Mapper
* @createDate 2024-06-25 08:19:33
* @Entity com.test.pojo.entity.Img
*/
@Mapper
public interface ImgMapper {
    /**
     * 根据id查询对应的商品图片
     * @param goodsId
     * @return
     */

    @Select("select * from trade_platform.img where goods_id = #{goodsId}")
    List<Img> queryById(Long goodsId);
    /**
     * 根据是否默认查询对应的商品图片
     * @param isDefault
     * @return
     */

    @Select("select * from trade_platform.img where goods_id = #{goodsId} and is_default = #{isDefault}")
    Img queryByDefault(Long goodsId,int isDefault);

    /**
     * 向图片表插入多条数据
     * @param imgList
     */
    void insertBatch(List<Img> imgList);

    /**
     * 根据商品id删除对应的图片
     * @param goodsId
     */
    @Delete("delete from img where goods_id = #{goodsId}")
    void delete(Long goodsId);
}




