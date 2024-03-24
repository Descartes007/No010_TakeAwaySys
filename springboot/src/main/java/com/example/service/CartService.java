package com.example.service;

import com.example.entity.AmountDTO;
import com.example.entity.Business;
import com.example.entity.Cart;
import com.example.entity.Goods;
import com.example.mapper.CartMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车业务处理
 **/
@Service
public class CartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private GoodsService goodsService;

    @Resource
    private BusinessService businessService;

    /**
     * 新增
     */
    public void add(Cart cart) {
        cartMapper.insert(cart);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        cartMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            cartMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Cart cart) {
        cartMapper.updateById(cart);
    }

    /**
     * 根据ID查询
     */
    public Cart selectById(Integer id) {
        return cartMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Cart> selectAll(Cart cart) {
        List<Cart> cartList = cartMapper.selectAll(cart);
        for (Cart c : cartList) {   // 设置购物车的商品信息
            Goods goods = goodsService.selectById(c.getGoodsId());
            c.setGoods(goods);
            // 查询商家的信息
            Business business = businessService.selectById(c.getBusinessId());
            c.setBusiness(business);
        }
        return cartList;
    }

    /**
     * 查询出用户在某商家的所有购物商品信息
     * @param userId
     * @param businessId
     * @return
     */
    public List<Cart> selectUserCart(Integer userId, Integer businessId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setBusinessId(businessId);
        return this.selectAll(cart);
    }

    /**
     * 分页查询
     */
    public PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cart> list = selectAll(cart);
        return PageInfo.of(list);
    }

    // 计算购物车的总金额
    public AmountDTO calc(Integer userId, Integer businessId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setBusinessId(businessId);
        // 查出当前用户在某商家的所有购物车商品数据
        List<Cart> cartList = this.selectAll(cart);
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal actual = BigDecimal.ZERO;
        for (Cart c : cartList) {
            Goods goods = c.getGoods();
            if (goods != null) {
                BigDecimal price = goods.getPrice();
                BigDecimal actualPrice = goods.getActualPrice();
                amount = amount.add(price.multiply(BigDecimal.valueOf(c.getNum())));  // 原价
                actual = actual.add(actualPrice.multiply(BigDecimal.valueOf(c.getNum())));  // 打折之后的价格
            }
        }
        AmountDTO amountDTO = new AmountDTO();
        amountDTO.setAmount(amount);
        amountDTO.setActual(actual);
        amountDTO.setDiscount(amount.subtract(actual));  // 优惠的金额
        return amountDTO;
    }

    public void deleteByBusiness(Integer businessId, Integer userId) {
        cartMapper.deleteByBusiness(businessId, userId);
    }
}