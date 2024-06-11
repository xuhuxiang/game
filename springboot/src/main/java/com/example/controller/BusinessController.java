package com.example.controller;

import com.example.common.Result;
import com.example.entity.Business;
import com.example.service.BusinessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/business")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @ApiOperation(value = "新增商家接口",notes = "直接调用商家的添加方法")
    @PostMapping
    public Result add(@RequestBody Business business) {
        businessService.add(business);
        return Result.success(business);
    }

    @ApiOperation(value = "删除商家接口",notes = "直接调用商家的根据ID删除方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        businessService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "更新商家信息接口",notes = "直接调用商家的更新方法")
    @PutMapping
    public Result update(@RequestBody Business business) {

        businessService.update(business);
        return Result.success();
    }

    @ApiOperation(value = "查询商家接口",notes = "直接调用商家的根据ID查询方法")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Business business = businessService.findById(id);
        return Result.success(business);
    }

    @ApiOperation(value = "展示所有商家接口",notes = "直接调用商家的展示所有方法")
    @GetMapping
    public Result all() {
        return Result.success(businessService.findAll());
    }

    @ApiOperation(value = "分页查询",notes = "使得每一页都只有五个内容")
    @PostMapping("/page")
    public Result page(@RequestBody Business search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(businessService.findPage(search, pageNum, pageSize));
    }
}

