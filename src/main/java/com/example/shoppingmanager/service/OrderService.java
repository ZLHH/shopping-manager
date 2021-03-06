package com.example.shoppingmanager.service;

import com.example.shoppingmanager.dao.OrderDao;
import com.example.shoppingmanager.dao.ProductDao;
import com.example.shoppingmanager.entity.Order;
import com.example.shoppingmanager.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/21.
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;

    public Order queryObject(String id){
        return orderDao.queryObject(id);
    }

    public List<Order> queryList(Map<String, Object> map){return orderDao.queryList(map);}

    public  int queryTotal(Map<String, Object> map){return orderDao.queryTotal(map);}

    public 	void update(Order order){orderDao.update(order);}

    public void deleteBatch(String[] ids){orderDao.deleteBatch(ids);}

    public boolean forbidden(String id){
        return orderDao.forbidden(id) == 1 ? true : false;
    }
}
