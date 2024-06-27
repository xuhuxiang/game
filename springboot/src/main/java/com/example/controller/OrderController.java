package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.*;
import com.example.service.GoodsService;
import com.example.service.OrderService;
import com.example.service.TypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* 订单相关接口
*/
@CrossOrigin(origins = {"http://114.55.219.66:8081", "null"})
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Resource
    private OrderService orderService;


    @ApiOperation(value = "购物车购买商品接口",notes = "调用订单的添加商品方法")
    @PostMapping("/buy")
    public Result add(@RequestBody OrderDto order) {
        orderService.add(order);
        return Result.success();
    }

    @ApiOperation(value = "详情页购买商品接口",notes = "调用订单的添加一个商品方法")
    @PostMapping("/buy/one")
    public Result add(@RequestBody Order order) {
        orderService.addOne(order);
        return Result.success();
    }

    @ApiOperation(value = "删除订单接口",notes = "调用订单的删除商品方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        orderService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "更新订单接口",notes = "调用订单的更新方法")
    @PutMapping
    public Result update(@RequestBody Order order) {
        orderService.update(order);
        return Result.success();
    }

    @ApiOperation(value = "查询订单接口",notes = "调用订单的根据ID查询方法")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Order order = orderService.findById(id);
        return Result.success(order);
    }

    @ApiOperation(value = "展示所有订单接口",notes = "调用订单的展示所有方法")
    @GetMapping
    public Result all() {
        return Result.success(orderService.findAll());
    }

    @ApiOperation(value = "查找特定订单接口",notes = "根据用户的ID以及商品的ID进行查找")
    @GetMapping("/searchOne")
    public Result One(@RequestParam("userId") String userId,@RequestParam("goodsId") String goodsId){
        return Result.success(orderService.findByUserIdAndGoodsId(userId,goodsId));
    }
    @ApiOperation(value = "分页查询",notes = "使得每一页都只有五个内容")
    @PostMapping("/page")
    public Result page(@RequestBody Order search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam("businessId") String businessId,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(orderService.findPage(search, pageNum,businessId, pageSize));
    }

}
