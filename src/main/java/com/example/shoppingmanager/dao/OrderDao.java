package com.example.shoppingmanager.dao;

import com.example.shoppingmanager.entity.Order;
import com.example.shoppingmanager.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/21.
 */
@Mapper
public interface OrderDao {

    Order queryObject(String id);

    List<Order> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void update(Order order);

    void deleteBatch(String[] ids);

    Integer forbidden(String id);
}
