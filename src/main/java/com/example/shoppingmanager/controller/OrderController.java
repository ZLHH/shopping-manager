package com.example.shoppingmanager.controller;

import com.example.shoppingmanager.entity.Order;
import com.example.shoppingmanager.entity.Product;
import com.example.shoppingmanager.service.OrderService;
import com.example.shoppingmanager.service.ProductService;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<Order> orderList = orderService.queryList(query);
        int total = orderService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(orderList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") String id){
        Order order = orderService.queryObject(id);
        return R.ok().put("order", order);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody Order order){
        order.setUpdateTime(LocalDateTime.now());
        orderService.update(order);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
        orderService.deleteBatch(ids);
        return R.ok();
    }

    @RequestMapping(value = "/forbidden", method = RequestMethod.POST)
    public R forbidden(String id){
        if(orderService.forbidden(id)){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
