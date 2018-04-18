package com.example.shoppingmanager.controller;



import com.example.shoppingmanager.entity.UserEntity;
import com.example.shoppingmanager.entity.UserMain;
import com.example.shoppingmanager.entity.UserMainDetail;
import com.example.shoppingmanager.service.UserService;
import com.example.shoppingmanager.utils.PageUtils;
import com.example.shoppingmanager.utils.Query;
import com.example.shoppingmanager.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-02-27 11:20:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserEntity> studentList = userService.queryList(query);
		int total = userService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(studentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		UserEntity user = userService.queryObject(id);
		
		return R.ok().put("student", user);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody UserEntity user){
		UserMain userMain = new UserMain();
		UserMainDetail userMainDetail = new UserMainDetail();
		userMain.setName(user.getName());
		userMain.setNickName("user");
		userMain.setEmail(user.getEmail());
		userMain.setCreateTime(LocalDateTime.now());
		userMain.setRole(0);
		userMain.setStatus(0);
		if (userService.queryObjectByName(user.getName())!=null){
			return R.error("用户名已存在");
		}
		userService.save(userMain);
		userMainDetail.setPassword(user.getPassword());
		userMainDetail.setPhoneNumber(user.getPhoneNumber());
		userMainDetail.setUserId(userService.queryObjectByName(user.getName()).getId());
		userMainDetail.setCreateTime(LocalDateTime.now());
		userService.saveDetail(userMainDetail);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody UserEntity user){
		UserMain userMain = new UserMain();
		UserMainDetail userMainDetail = new UserMainDetail();
		userMain.setName(user.getName());
		userMain.setEmail(user.getEmail());
		userMain.setUpdateTime(LocalDateTime.now());
		userService.updateMain(userMain);
		userMainDetail.setPassword(user.getPassword());
		userMainDetail.setPhoneNumber(user.getPhoneNumber());
		userMainDetail.setUpdateTime(LocalDateTime.now());
		userService.updateDetail(userMainDetail);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		userService.deleteBatch(ids);
		
		return R.ok();
	}

	@RequestMapping(value = "/forbidden", method = RequestMethod.POST)
	public R forbidden(Integer id){
		System.out.println("id = " + id);
		System.out.println("StudentController.forbidden");
		if(userService.forbidden(id)){
			return R.ok();
		}else {
			return R.error();
		}
	}

	@RequestMapping("/recover")
	public R recover( Integer id){
		if(userService.recover(id)){
			return R.ok();
		}else {
			return R.error();
		}
	}
}
