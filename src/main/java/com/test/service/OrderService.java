package com.test.service;

import com.test.common.Result;
import com.test.pojo.DTO.QueryOrderDTO;
import com.test.pojo.DTO.UpdateOrderDTO;
import com.test.pojo.VO.OrdersBaseVO;
import com.test.pojo.VO.QueryOneOrderVO;
import com.test.pojo.entity.Orders;

import java.util.List;

public interface OrderService {
    /**
     * 查询所有订单
     * @return
     */
    Result<List<OrdersBaseVO>> queryAll(QueryOrderDTO queryOrderDTO);

    /**
     * 修改订单状态
     * @param updateOrderDTO
     * @return
     */
    Result update(UpdateOrderDTO updateOrderDTO);
    /**
     * 查询订单详细页
     *
     * @param ordersID
     * @return
     */
    Result<List<QueryOneOrderVO>> queryOne(Long ordersID);
}
