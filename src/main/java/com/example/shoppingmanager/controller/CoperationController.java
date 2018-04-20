package com.example.shoppingmanager.controller;

import com.example.shoppingmanager.entity.Coperation;
import com.example.shoppingmanager.entity.Information;
import com.example.shoppingmanager.service.CoperationService;
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
@RequestMapping("/coperation")
public class CoperationController {

    @Autowired
    CoperationService coperationService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<Coperation> coperationList = coperationService.queryList(query);
        int total = coperationService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(coperationList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        Coperation coperation = coperationService.queryObject(id);
        return R.ok().put("coperation", coperation);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Coperation coperation){
        coperation.setCreateTime(LocalDateTime.now());
        coperation.setStatus(0);
        coperationService.save(coperation);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Coperation coperation){
        coperation.setUpdateTime(LocalDateTime.now());
        coperationService.update(coperation);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        coperationService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 处理
     */
    @RequestMapping("/changeStatus")
    public R changeStatus(Integer id){
        if(coperationService.changeStatus(id)){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
