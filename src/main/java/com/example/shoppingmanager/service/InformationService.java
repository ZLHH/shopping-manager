package com.example.shoppingmanager.service;

import com.example.shoppingmanager.dao.InformationDao;
import com.example.shoppingmanager.entity.Information;
import com.example.shoppingmanager.entity.UserEntity;
import com.example.shoppingmanager.entity.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/20.
 */
@Service
public class InformationService {

    @Autowired
    private InformationDao informationDao;

    public Information queryObject(Integer id){
        return informationDao.queryObject(id);
    }

    public UserEntity queryObjectByName(String name){
        return informationDao.queryObjectByName(name);
    }

    public List<Information> queryList(Map<String, Object> map){return informationDao.queryList(map);}

    public  int queryTotal(Map<String, Object> map){return informationDao.queryTotal(map);}

    public  void save(Information information){informationDao.save(information);}

    public 	void update(Information information){informationDao.update(information);}

    public void deleteBatch(Integer[] ids){informationDao.deleteBatch(ids);}
}
