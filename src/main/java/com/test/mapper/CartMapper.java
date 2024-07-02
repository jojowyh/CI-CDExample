package com.test.mapper;

import com.test.pojo.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author 86187
* @description 针对表【cart(购物车表)】的数据库操作Mapper
* @createDate 2024-06-26 19:49:02
* @Entity com.test.pojo.entity.Cart
*/
@Mapper
public interface CartMapper {
    /**
     * 向购物车中添加商品
     * @param cart
     */
    @Insert("insert into trade_platform.cart(user_id, goods_id, good_number,size_id)" +
            "values (#{userId},#{goodsId},#{goodNumber},#{sizeId})")
    void save(Cart cart);

    /**
     * 修改购物车中商品数量
     * @param cart
     */
    @Update("update trade_platform.cart set good_number = #{goodNumber} " +
            "where user_id = #{userId} and goods_id = #{goodsId} and size_id = #{sizeId}")
    void update(Cart cart);

    /**
     * 根据条件查询购物车列表
     * @param cart
     * @return
     */
    List<Cart> queryListByCondition(Cart cart);


    /**
     * 根据商品信息删除
     * @param cart
     */
    @Delete("delete from trade_platform.cart where goods_id = #{goodsId} and user_id = #{userId} and size_id = #{sizeId} ")
    void delete(Cart cart);

    @Select("select *from trade_platform.cart where user_id  = #{userId} and goods_id  = #{goodsId} and size_id = #{sizeId}")
    Cart queryByCondition(Cart cart);
}




