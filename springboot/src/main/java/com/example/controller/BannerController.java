package com.example.controller;

import com.example.common.Result;
import com.example.entity.Banner;
import com.example.service.BannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 广告位展示管理相关接口
*/
@RestController
@RequestMapping(value = "/banner")
public class BannerController {

    @Resource
    private BannerService bannerService;


    @ApiOperation(value = "新增轮播图接口",notes = "直接调用轮播图的添加方法")
    @PostMapping
    public Result add(@RequestBody Banner banner) {

        bannerService.add(banner);
        return Result.success(banner);
    }

    @ApiOperation(value = "根据ID删除轮播图接口",notes = "直接调用轮播图的删除方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        bannerService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "更新轮播图接口",notes = "直接调用轮播图的更新方法")
    @PutMapping
    public Result update(@RequestBody Banner banner) {

        bannerService.update(banner);
        return Result.success();
    }

    @ApiOperation(value = "查询轮播图接口",notes = "直接调用轮播图的根据ID查询方法")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Banner banner = bannerService.findById(id);
        return Result.success(banner);
    }


    @ApiOperation(value = "显示所有轮播图接口",notes = "直接调用轮播图显示所有的方法")
    @GetMapping
    public Result all() {
        return Result.success(bannerService.findAll());
    }

    @ApiOperation(value = "分页查询",notes = "使得每一页都只有五个内容")
    @PostMapping("/page")
    public Result page(@RequestBody Banner search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(bannerService.findPage(search, pageNum, pageSize));
    }


}
