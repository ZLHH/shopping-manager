package com.example.shoppingmanager.dao;

import com.example.shoppingmanager.entity.Category;
import com.example.shoppingmanager.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/21.
 */
@Mapper
public interface ProductDao {

    Product queryObject(String id);

    List<Product> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Product product);

    void update(Product product);

    void deleteBatch(String[] ids);

    Integer forbidden(String id);

    Integer recover(String id);
}
