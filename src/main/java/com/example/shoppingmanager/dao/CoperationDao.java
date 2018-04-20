package com.example.shoppingmanager.dao;

import com.example.shoppingmanager.entity.Coperation;
import com.example.shoppingmanager.entity.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/20.
 */
@Mapper
public interface CoperationDao {

    Coperation queryObject(Integer id);

    List<Coperation> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(Coperation coperation);

    void update(Coperation coperation);

    void deleteBatch(Integer[] ids);

    void changeStatus(Integer id);
}
