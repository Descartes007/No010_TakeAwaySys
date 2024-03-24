package com.example.service;

import com.example.entity.OrdersItem;
import com.example.mapper.OrdersItemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 订单详情表业务处理
 **/
@Service
public class OrdersItemService {

    @Resource
    private OrdersItemMapper ordersItemMapper;

    @Resource
    private GoodsService goodsService;

    /**
     * 新增
     */
    public void add(OrdersItem ordersItem) {
        ordersItemMapper.insert(ordersItem);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        ordersItemMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            ordersItemMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(OrdersItem ordersItem) {
        ordersItemMapper.updateById(ordersItem);
    }

    /**
     * 根据ID查询
     */
    public OrdersItem selectById(Integer id) {
        return ordersItemMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<OrdersItem> selectAll(OrdersItem ordersItem) {
        return ordersItemMapper.selectAll(ordersItem);
    }

    /**
     * 分页查询
     */
    public PageInfo<OrdersItem> selectPage(OrdersItem ordersItem, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrdersItem> list = ordersItemMapper.selectAll(ordersItem);
        return PageInfo.of(list);
    }

    public void deleteByOrderId(Integer orderId) {
        ordersItemMapper.deleteByOrderId(orderId);
    }

    public List<OrdersItem> selectByOrderId(Integer ordersId) {
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setOrderId(ordersId);
        return ordersItemMapper.selectAll(ordersItem);
    }

    public List<OrdersItem> selectByGoodsId(Integer goodsId) {
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setGoodsId(goodsId);
        return ordersItemMapper.selectAll(ordersItem);
    }
}