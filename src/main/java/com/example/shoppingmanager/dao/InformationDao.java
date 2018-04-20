package com.example.shoppingmanager.dao;

import com.example.shoppingmanager.entity.Information;
import com.example.shoppingmanager.entity.UserEntity;
import com.example.shoppingmanager.entity.UserMain;
import com.example.shoppingmanager.entity.UserMainDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/20.
 */
@Mapper
public interface InformationDao {

    Information queryObject(Integer id);

    List<Information> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Information information);

    void update(Information information);

    void deleteBatch(Integer[] ids);

}
