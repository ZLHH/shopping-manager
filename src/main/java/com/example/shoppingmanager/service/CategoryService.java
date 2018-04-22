package com.example.shoppingmanager.service;

import com.example.shoppingmanager.dao.CategoryDao;
import com.example.shoppingmanager.dao.CoperationDao;
import com.example.shoppingmanager.entity.Category;
import com.example.shoppingmanager.entity.Coperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/21.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    public Category queryObject(Integer id){
        return categoryDao.queryObject(id);
    }

    public List<Category> queryList(Map<String, Object> map){return categoryDao.queryList(map);}

    public  int queryTotal(Map<String, Object> map){return categoryDao.queryTotal(map);}

    public  void save(Category category){categoryDao.save(category);}

    public 	void update(Category category){categoryDao.update(category);}

    public void deleteBatch(Integer[] ids){categoryDao.deleteBatch(ids);}
}
