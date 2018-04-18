package com.example.shoppingmanager.dao;

import com.example.shoppingmanager.entity.UserEntity;
import com.example.shoppingmanager.entity.UserMain;
import com.example.shoppingmanager.entity.UserMainDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-02-27 11:20:25
 */
@Mapper
public interface UserDao {

    UserEntity queryObject(Integer id);

    UserEntity queryObjectByName(String name);

    List<UserEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(UserMain userMain);

    void saveDetail(UserMainDetail userMainDetail);

    void update(UserEntity tdSupplier);

    void updateSelective(UserEntity tdSupplier);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    Integer forbidden(Integer id);

    Integer recover(Integer id);
}
