package com.example.shoppingmanager.dao;

import com.example.shoppingmanager.entity.Category;
import com.example.shoppingmanager.entity.Coperation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/21.
 */
@Mapper
public interface CategoryDao {

    Category queryObject(Integer id);

    List<Category> queryList(Map<String, Object> map);

    List<Category> querycategoryList();

    int queryTotal(Map<String, Object> map);

    void save(Category category);

    void update(Category category);

    void deleteBatch(Integer[] ids);
}
