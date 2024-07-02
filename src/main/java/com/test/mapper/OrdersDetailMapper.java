package com.test.mapper;

import com.test.pojo.entity.OrdersDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author 86187
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2024-06-28 09:07:20
* @Entity com.test.pojo.entity.Orders
*/
@Mapper
public interface OrdersDetailMapper {

    @Select("select * from trade_platform.orders_detail where orders_id =#{ordersId}")
    List<OrdersDetail> query(Long ordersId);

    /**
    * @author 86187
    * @description 针对表【orders_detail(订单明细表)】的数据库操作Mapper
    * @createDate 2024-07-01 09:18:46
    * @Entity com.test.pojo.entity.OrdersDetail
    */

}




