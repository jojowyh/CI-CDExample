package com.test.mapper;

import com.github.pagehelper.Page;
import com.test.pojo.DTO.GoodPageDTO;
import com.test.pojo.VO.GoodsPageVO;
import com.test.pojo.entity.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86187
* @description 针对表【goods(商品表)】的数据库操作Mapper
* @createDate 2024-06-25 11:22:45
* @Entity com.test.pojo.Goods
*/
@Mapper
public interface GoodsMapper {

    /**
     * 查询所有商品
     * @return
     */
    @Select("select * from trade_platform.goods")
    List<Goods> query();

    /**
     * 根据商品id查询对应商品
     * @return
     */
    @Select("select * from trade_platform.goods where goods_id =#{goodsId}")
    Goods queryById(Long goodsId);

    /**
     * 向商品表里添加一条数据
     * @param goods
     */

    void insert(Goods goods);

    /**
     * 根据分类id查询商品
     * @param categoryId
     * @return
     */
    @Select("select * from trade_platform.goods where category_id =#{categoryId}")
    List<Goods> queryByCategoryId(Long categoryId);

    /**
     * 修改商品
     * @param goods
     */
    void update(Goods goods);

    /**
     * 删除商品
     * @param goodsId
     */
    @Delete("delete from trade_platform.goods where goods_id = #{goodsId}")
    void delete(Long goodsId);

    /**
     * 商品分页查询
     *
     * @param goodPageDTO
     * @return
     */
    Page<GoodsPageVO> pageQuery(GoodPageDTO goodPageDTO);
}




