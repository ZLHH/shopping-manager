package com.example.shoppingmanager.service;


import com.example.shoppingmanager.dao.UserDao;
import com.example.shoppingmanager.entity.UserEntity;
import com.example.shoppingmanager.entity.UserMain;
import com.example.shoppingmanager.entity.UserMainDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-02-27 11:20:25
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserDao userDao;

	public UserEntity queryObject(Integer id){
		return userDao.queryObject(id);
	}

	public UserEntity queryObjectByName(String name){
        return userDao.queryObjectByName(name);
	}
	
    public List<UserEntity> queryList(Map<String, Object> map){return userDao.queryList(map);}
	
	public  int queryTotal(Map<String, Object> map){return userDao.queryTotal(map);}
	
	public  void save(UserMain userMain){userDao.save(userMain);}

	public  void saveDetail(UserMainDetail userMainDetail){userDao.saveDetail(userMainDetail);}
	
    public 	void update(UserEntity user){userDao.update(user);}

	public void updateSelective(UserEntity user){userDao.updateSelective(user);}
	
	public void delete(Integer id){userDao.delete(id);}
	
	public void deleteBatch(Integer[] ids){userDao.deleteBatch(ids);}

	public boolean forbidden(Integer id){
		return userDao.forbidden(id) == 1 ? true : false;
	}

	public boolean recover(Integer id){
		return userDao.recover(id) == 1 ? true : false;
	}
}
