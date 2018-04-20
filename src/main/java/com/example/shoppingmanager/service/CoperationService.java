package com.example.shoppingmanager.service;

import com.example.shoppingmanager.dao.CoperationDao;
import com.example.shoppingmanager.dao.InformationDao;
import com.example.shoppingmanager.entity.Coperation;
import com.example.shoppingmanager.entity.Information;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/20.
 */
@Service
public class CoperationService {

    @Autowired
    private CoperationDao coperationDao;

    public Coperation queryObject(Integer id){
        return coperationDao.queryObject(id);
    }

    public List<Coperation> queryList(Map<String, Object> map){return coperationDao.queryList(map);}

    public  int queryTotal(Map<String, Object> map){return coperationDao.queryTotal(map);}

    public  void save(Coperation coperation){coperationDao.save(coperation);}

    public 	void update(Coperation coperation){coperationDao.update(coperation);}

    public void deleteBatch(Integer[] ids){coperationDao.deleteBatch(ids);}

    public Boolean changeStatus(Integer id){coperationDao.changeStatus(id);return true;}
}
