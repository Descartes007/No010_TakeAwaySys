package com.example.mapper;

import com.example.entity.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作cart相关数据接口
 */
public interface CartMapper {

    /**
     * 新增
     */
    int insert(Cart cart);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Cart cart);

    /**
     * 根据ID查询
     */
    Cart selectById(Integer id);

    /**
     * 查询所有
     */
    List<Cart> selectAll(Cart cart);

    @Delete("delete from cart where business_id = #{businessId} and user_id = #{userId}")
    void deleteByBusiness(@Param("businessId") Integer businessId, @Param("userId") Integer userId);
}