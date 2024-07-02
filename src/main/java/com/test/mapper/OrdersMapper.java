package com.test.mapper;

import com.test.pojo.DTO.UpdateOrderDTO;
import com.test.pojo.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
* @author 86187
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2024-06-28 09:07:20
* @Entity com.test.pojo.entity.Orders
*/
@Mapper
public interface OrdersMapper {
    /**
     * 查询所有订单(根据有无条件)
     * @return
     */
    List<Orders> query(Orders orders);


    @Update("update trade_platform.orders set state = #{state},update_time = #{updateTime} where orders_id = #{ordersId}")
    void update(UpdateOrderDTO updateOrderDTO);

    @Select("select * from trade_platform.orders where orders_id=#{ordersId}" )
    Orders queryById(Long ordersId);
}




