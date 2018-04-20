package com.example.shoppingmanager.controller;

import com.example.shoppingmanager.entity.Information;
import com.example.shoppingmanager.entity.UserEntity;
import com.example.shoppingmanager.service.InformationService;
import com.example.shoppingmanager.utils.PageUtils;
import com.example.shoppingmanager.utils.Query;
import com.example.shoppingmanager.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/4/20.
 */
@RestController
@RequestMapping("/info")
public class InformationController {

    @Autowired
    InformationService informationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<Information> infoList = informationService.queryList(query);
        int total = informationService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(infoList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        Information information = informationService.queryObject(id);

        return R.ok().put("info", information);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Information information){
        information.setCreateTime(LocalDateTime.now());
        informationService.save(information);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Information information){
        information.setUpdateTime(LocalDateTime.now());
        informationService.update(information);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        informationService.deleteBatch(ids);
        return R.ok();
    }
}
