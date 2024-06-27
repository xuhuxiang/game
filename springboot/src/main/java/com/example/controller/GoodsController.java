package com.example.controller;

import com.example.common.Result;
import com.example.entity.*;
import com.example.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
* 商品管理相关接口
*/

@CrossOrigin(origins = {"http://114.55.219.66:8081", "null"})
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @ApiOperation(value = "新增商品接口",notes = "直接调用商品的添加方法")
    @PostMapping
    public Result add(@RequestBody Goods goods) {

        goodsService.add(goods);
        return Result.success(goods);
    }

    @ApiOperation(value = "删除商品接口",notes = "直接调用商品的根据ID删除方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        goodsService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "更新商品接口",notes = "直接调用商品的更新方法")
    @PutMapping
    public Result update(@RequestBody Goods goods) {

        goodsService.update(goods);
        return Result.success();
    }

    @ApiOperation(value = "查询商品接口",notes = "直接调用商品的根据ID查询方法")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Goods goods = goodsService.findById(id);
        return Result.success(goods);
    }

    @ApiOperation(value = "根据分类查询商品接口",notes = "直接调用商品的根据其ID查询方法全部放在一个列表中")
    @GetMapping("/type/{id}")
    public Result type(@PathVariable Integer id) {
        List<Goods> list = goodsService.findByFlId(id);
        return Result.success(list);
    }

    @ApiOperation(value = "展示所有商品接口",notes = "直接调用商品的展示所有方法")
    @GetMapping
    public Result all() {
        return Result.success(goodsService.findAll());
    }
    @ApiOperation(value = "展示热门商品接口",notes = "将所有商品根据是否热门进行过滤")
    @GetMapping("/hotgoods")
    public Result hotall() {
        List<Goods> collect = goodsService.findAll().stream().filter(item -> "是".equals(item.getIshot())).collect(Collectors.toList());
        return Result.success(collect);
    }
    @ApiOperation(value = "分页查询所有商品",notes = "使得每一页都只有五个内容")
    @PostMapping("/page")
    public Result page(@RequestBody Goods search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(goodsService.findPage(search, pageNum, pageSize));
    }
    @PostMapping("/page1")
    public Result page1(@RequestBody Goods search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam("userId") String businessId,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(goodsService.findPage1(search, pageNum,businessId, pageSize));
    }
    @ApiOperation(value = "分页查询热门商品",notes = "使得每一页都只有五个内容")
    @PostMapping("/hot/page")
    public Result hotpage(@RequestBody Goods search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(goodsService.findHotPage(search, pageNum, pageSize));
    }

    @ApiOperation(value = "点赞功能接口",notes = "直接调用商品的点赞方法")
    @GetMapping("/praise/{goodsId}")
    public Result praise(@PathVariable Integer goodsId) {
        goodsService.praise(goodsId);
        return Result.success();
    }

    @ApiOperation(value = "查看点赞状态接口",notes = "调用商品的查看点赞状态方法")
    @GetMapping("/praise/check/{goodsId}")
    public Result praiseCheck(@PathVariable Integer goodsId) {
        return Result.success(goodsService.praiseCheck(goodsId));
    }

    @ApiOperation(value = "搜索商品接口",notes = "使得每一页都只有五个内容")
    @GetMapping("/search/{name}")
    public Result search(@PathVariable String name) {
        return Result.success(goodsService.search(name));
    }
}
