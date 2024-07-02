package com.test.service.impl;

import com.test.common.Result;
import com.test.mapper.CartMapper;
import com.test.mapper.SizeMapper;
import com.test.pojo.DTO.SaveCartDTO;
import com.test.pojo.DTO.UpdateCartDTO;
import com.test.pojo.entity.Cart;
import com.test.pojo.entity.Size;
import com.test.pojo.entity.User;
import com.test.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private SizeMapper sizeMapper;

    /**
     * 添加商品到购物车
     * @param saveCartDTO
     * @return
     */
    @Override
    @Transactional
    public Result save(SaveCartDTO saveCartDTO) {
        //判断商品是否已存在购物车,已存在，数量加一
        Cart cart = new Cart();
        //设置对应参数
        BeanUtils.copyProperties(saveCartDTO,cart);
        Cart queryCart = cartMapper.queryByCondition(cart);
        if ( queryCart != null){
            queryCart.setGoodNumber(queryCart.getGoodNumber()+1);
            cartMapper.update(queryCart);
            return Result.success("商品已存在，数量加1");
        }
        //不存在，数量设为一
        cart.setGoodNumber(1);

        cartMapper.save(cart);
        return Result.success("商品添加到购物车，数量为1");
    }

    /**
     * 购物车中商品数加一
     * @param updateCartDTO
     * @return
     */
    @Override
    @Transactional
    public Result add(UpdateCartDTO updateCartDTO) {
        //TODO 查库存表与当前数目比较
        Size size = new Size();
        BeanUtils.copyProperties(updateCartDTO,size);
        Size querySize=sizeMapper.query(size);

        Cart cart = new Cart();
        BeanUtils.copyProperties(updateCartDTO,cart);

        Cart queryCart = cartMapper.queryByCondition(cart);

        if (querySize.getInventory() <= queryCart.getGoodNumber()){
           return   Result.error("商品数量超出库存数量，无法添加");
        }

        //购物车中商品数加一

        queryCart.setGoodNumber(queryCart.getGoodNumber()+1);
        cartMapper.update(queryCart);

        return Result.success("商品数加一");
    }



    /**
     * 购物车中商品数减一
     * @param updateCartDTO
     * @return
     */

    @Override
    @Transactional
    public Result sub(UpdateCartDTO updateCartDTO) {

        Cart cart = new Cart();
        BeanUtils.copyProperties(updateCartDTO,cart);
        Cart queryCart = cartMapper.queryByCondition(cart);
        if (queryCart.getGoodNumber()==1){
            cartMapper.delete(queryCart);
            return Result.success("删除商品");
        }
        //大于1则继续进行购物车商品数量减1操作

        queryCart.setGoodNumber(queryCart.getGoodNumber()-1);
        cartMapper.update(queryCart);

        return Result.success("商品数减一");
    }



    /**
     * 根据用户id查询用户的购物车
     * @param userId
     * @return
     */
    @Override
    public Result query(Long userId) {
        Cart cart = new Cart();
        cart.setUserId(userId);

        List<Cart> queryCartList=cartMapper.queryListByCondition(cart);
        return Result.success(queryCartList);
    }





}
