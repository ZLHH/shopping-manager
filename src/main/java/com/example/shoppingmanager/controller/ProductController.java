package com.example.shoppingmanager.controller;

import com.example.shoppingmanager.entity.Product;
import com.example.shoppingmanager.service.ProductService;
import com.example.shoppingmanager.utils.CommonUtil;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        //查询列表数据
        Query query = new Query(params);

        List<Product> productList = productService.queryList(query);
        int total = productService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(productList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
        Product product = productService.queryObject(id);
        return R.ok().put("product", product);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody Product product){
        product.setCreateTime(LocalDateTime.now());
        productService.save(product);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    public R update(@RequestBody Product product){
        product.setUpdateTime(LocalDateTime.now());
        productService.update(product);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] ids){
        productService.deleteBatch(ids);
        return R.ok();
    }

    @RequestMapping(value = "/forbidden", method = RequestMethod.POST)
    public R forbidden(String id){
        if(productService.forbidden(id)){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @RequestMapping("/recover")
    public R recover( String id){
        if(productService.recover(id)){
            return R.ok();
        }else {
            return R.error();
        }
    }
}
