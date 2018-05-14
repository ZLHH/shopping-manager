package com.example.shoppingmanager.service;

import com.example.shoppingmanager.dao.CategoryDao;
import com.example.shoppingmanager.dao.ProductDao;
import com.example.shoppingmanager.entity.Category;
import com.example.shoppingmanager.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/21.
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public Product queryObject(Integer id){
        return productDao.queryObject(id);
    }

    public List<Product> queryList(Map<String, Object> map){return productDao.queryList(map);}

    public  int queryTotal(Map<String, Object> map){return productDao.queryTotal(map);}

    public  void save(Product product){productDao.save(product);}

    public 	void update(Product product){productDao.update(product);}

    public void deleteBatch(String[] ids){productDao.deleteBatch(ids);}

    public boolean forbidden(String id){
        return productDao.forbidden(id) == 1 ? true : false;
    }

    public boolean recover(String id){
        return productDao.recover(id) == 1 ? true : false;
    }
}
