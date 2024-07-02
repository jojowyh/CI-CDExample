package com.test.service.impl;

import com.test.common.DateUtils;
import com.test.common.Result;
import com.test.mapper.*;
import com.test.pojo.DTO.QueryOrderDTO;
import com.test.pojo.DTO.UpdateOrderDTO;
import com.test.pojo.VO.OrderDetailVo;
import com.test.pojo.VO.OrdersBaseVO;
import com.test.pojo.VO.QueryOneOrderVO;
import com.test.pojo.entity.*;
import com.test.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SizeMapper sizeMapper;

    @Autowired
    private AddressMapper addressMapper;



    /**
     * 查询所有订单(根据有无条件)
     * @return
     */
    @Override
    public Result<List<OrdersBaseVO>> queryAll(QueryOrderDTO queryOrderDTO) {
      /*  //根据用户名字查询id
        User user = new User();
        user.setUserName(queryOrderDTO.getUserName());
        User userMapperByCondition = userMapper.getByCondition(user);
        if (userMapperByCondition != null){
            orders.setUserId(userMapperByCondition.getUserId());
        }*/
        //根据状态查询订单信息
        Orders orders = new Orders();

        if (queryOrderDTO.getStatus() != null){
            orders.setState(queryOrderDTO.getStatus());
        }
        List<OrdersBaseVO> ordersBaseVOList=new ArrayList<>();
        List<Orders>ordersList=ordersMapper.query(orders);


        //订单信息
        ordersList.forEach(orders1 -> {
            OrdersBaseVO ordersBaseVO = new OrdersBaseVO();
            BeanUtils.copyProperties(orders1,ordersBaseVO);
            ordersBaseVOList.add(ordersBaseVO);
        });

        //收货信息
        Address address = new Address();

        ordersBaseVOList.forEach(ordersBaseVO -> {
            address.setUserId(ordersBaseVO.getUserId());
            address.setIsDefaul(1);
            List<Address> addresses = addressMapper.queryByCondition(address);
            BeanUtils.copyProperties(addresses.get(0),ordersBaseVO);
        });

        return Result.success(ordersBaseVOList);
    }
    /**
     * 修改订单状态
     * @param updateOrderDTO
     * @return
     */
    @Override
    public Result update(UpdateOrderDTO updateOrderDTO) {
        updateOrderDTO.setUpdateTime(DateUtils.now());
        ordersMapper.update(updateOrderDTO);
        return Result.success("修改订单状态成功");
    }

    /**
     * 查询订单详细页
     *
     * @param ordersId
     * @return
     */
    @Override
    @Transactional
    public Result<List<QueryOneOrderVO>> queryOne(Long ordersId) {

        List<QueryOneOrderVO> queryOneOrderVOList=new ArrayList<>();
        //从商品详情表获取信息
        List<OrdersDetail> ordersDetailsList=ordersDetailMapper.query(ordersId);
        for (OrdersDetail ordersDetail : ordersDetailsList) {
            QueryOneOrderVO vo = new QueryOneOrderVO();
            BeanUtils.copyProperties(ordersDetail,vo);
            queryOneOrderVOList.add(vo);
        }
        //从商品表获取信息
        for (QueryOneOrderVO oneOrderVO : queryOneOrderVOList) {
            Goods goods = goodsMapper.queryById(oneOrderVO.getGoodsId());
            BeanUtils.copyProperties(goods,oneOrderVO);
        }

        //从规格表获取信息


        for (QueryOneOrderVO queryOneOrderVO : queryOneOrderVOList) {
            Size size = new Size();
            size.setSizeId(queryOneOrderVO.getSizeId());
            size.setGoodsId(queryOneOrderVO.getGoodsId());
            size = sizeMapper.query(size);

            queryOneOrderVO.setSize_name(size.getSizeName());
        }

        return Result.success(queryOneOrderVOList);

    }


}
