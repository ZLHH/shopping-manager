package com.example.shoppingmanager.controller;

import com.example.shoppingmanager.entity.Category;
import com.example.shoppingmanager.service.CategoryService;
import com.example.shoppingmanager.utils.PageUtils;
import com.example.shoppingmanager.utils.Query;
import com.example.shoppingmanager.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/21.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<Category> coperationList = categoryService.queryList(query);
        int total = categoryService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(coperationList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        Category category = categoryService.queryObject(id);
        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Category category){
        category.setCreateTime(LocalDateTime.now());
        categoryService.save(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Category category){
        category.setUpdateTime(LocalDateTime.now());
        categoryService.update(category);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        categoryService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/queryAllCategory")
    public R getCategory(){
        List<Category> categoryList = categoryService.querycategoryList();
        if(categoryList!=null&&!categoryList.isEmpty()){
            return R.ok().put("list",categoryList);
        }
        return R.error("没有类目数据！！！");
    }
}
