package com.example.controller;

import com.example.common.Result;
import com.example.entity.*;
import com.example.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
*  游戏分类相关接口
*/
@RestController
@RequestMapping(value = "/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    @ApiOperation(value = "新增分类接口",notes = "直接调用类型的添加方法")
    @PostMapping
    public Result add(@RequestBody Type type) {

        typeService.add(type);
        return Result.success(type);
    }

    @ApiOperation(value = "删除分类接口",notes = "直接调用类型的根据ID删除某个类型方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        typeService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "更新分类接口",notes = "直接调用类型的更新方法")
    @PutMapping
    public Result update(@RequestBody Type type) {

        typeService.update(type);
        return Result.success();
    }

    @ApiOperation(value = "查询分类接口",notes = "直接调用类型的根据ID查询方法")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Type type = typeService.findById(id);
        return Result.success(type);
    }

    @ApiOperation(value = "展示所有分类接口",notes = "直接调用类型的展示所有方法")
    @GetMapping
    public Result all() {
        return Result.success(typeService.findAll());
    }
    @ApiOperation(value = "分页查询",notes = "使得一页展示五个内容")
    @PostMapping("/page")
    public Result page(@RequestBody Type search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(typeService.findPage(search, pageNum, pageSize));
    }




}
