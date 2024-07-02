package com.test.controller;

import com.test.common.Result;
import com.test.pojo.DTO.QueryOrderDTO;
import com.test.pojo.DTO.UpdateOrderDTO;
import com.test.pojo.VO.GoodsDetailsVO;
import com.test.pojo.VO.OrdersBaseVO;
import com.test.pojo.VO.QueryOneOrderVO;
import com.test.pojo.entity.Orders;
import com.test.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/order")
@Api(tags = "订单管理")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单(根据有无条件)
     * @return
     */
    @ApiOperation("查询所有订单(根据有无条件)")
    @PostMapping("/query")
    public Result<List<OrdersBaseVO>> queryAll(@RequestBody QueryOrderDTO queryOrderDTO){
        log.info("查询所有订单");
        return orderService.queryAll(queryOrderDTO);
    }

    /**
     * 修改订单状态
     * @param updateOrderDTO
     * @return
     */
    @PutMapping("update")
    @ApiOperation("更新订单状态")
    public Result update(@RequestBody UpdateOrderDTO updateOrderDTO){
        log.info("修改订单状态,订单id:{}",updateOrderDTO);
        return orderService.update(updateOrderDTO);
    }


    /**
     * 查询订单详细页
     * @param ordersId
     * @return
     */
    @GetMapping("/queryOne/{ordersId}")
    @ApiOperation("查询订单详细页")
    public Result<List<QueryOneOrderVO>> queryOne(@PathVariable Long ordersId){
        log.info("查询订单详细页:{}",ordersId);
        return orderService.queryOne(ordersId);
    }

    /**
     * 删除订单
     * @param ordersId
     * @return
     */
    @DeleteMapping("/delete/{ordersId}")
    @ApiOperation("删除订单")
    public Result delete(@PathVariable Long ordersId){
        log.info("删除订单，订单id:{}",ordersId);
        return orderService.delete(ordersId);
    }
}
