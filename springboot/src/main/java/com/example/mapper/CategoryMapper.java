package com.example.mapper;

import com.example.entity.Category;
import java.util.List;

/**
 * 操作category相关数据接口
 */
public interface CategoryMapper {

    /**
     * 新增
     */
    int insert(Category category);

    /**
     * 删除
     */
    int deleteById(Integer id);

    /**
     * 修改
     */
    int updateById(Category category);

    /**
     * 根据ID查询
     */
    Category selectById(Integer id);

    /**
     * 查询所有
     */
    List<Category> selectAll(Category category);

}