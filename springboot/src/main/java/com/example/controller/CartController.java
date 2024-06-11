package com.example.controller;

import com.example.common.Result;
import com.example.entity.Cart;
import com.example.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 购物车相关接口
*/
@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @ApiOperation(value = "新增订单接口",notes = "调用购物车的添加方法")
    @PostMapping
    public Result add(@RequestBody Cart cart) {
        cartService.add(cart);
        return Result.success(cart);
    }

    @ApiOperation(value = "删除订单接口",notes = "调用购物车的根据ID删除订单方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        cartService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "更新订单接口",notes = "调用购物车的更新方法")
    @PutMapping
    public Result update(@RequestBody Cart cart) {

        cartService.update(cart);
        return Result.success();
    }

    @ApiOperation(value = "查询订单接口",notes = "调用购物车的根据ID查询方法")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Cart cart = cartService.findById(id);
        return Result.success(cart);
    }

    @ApiOperation(value = "新增订单接口",notes = "调用购物车的展示所有方法")
    @GetMapping
    public Result all() {
        return Result.success(cartService.findAll());
    }

    @ApiOperation(value = "新增订单接口",notes = "调用购物车的添加方法")
    @GetMapping("/search")
    public Result One(@RequestParam("userId") String userId,@RequestParam("goodsId") String goodsId){
        return Result.success(cartService.findByUserId(userId,goodsId));
    }

    @ApiOperation(value = "分页查询",notes = "使得每一页都只有五个内容")
    @PostMapping("/page")
    public Result page(@RequestBody Cart search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(cartService.findPage(search, pageNum, pageSize));
    }
}
