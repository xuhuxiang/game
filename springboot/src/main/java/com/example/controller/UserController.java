package com.example.controller;

import com.example.common.Result;
import com.example.entity.*;
import com.example.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 用户相关接口
*/
@CrossOrigin(origins = {"http://114.55.219.66:8081", "null"})
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "新增用户接口",notes = "直接调用用户的添加方法")
    @PostMapping
    public Result add(@RequestBody User user) {

        userService.add(user);
        return Result.success(user);
    }

    @ApiOperation(value = "删除用户接口",notes = "直接调用用户的根据ID删除方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "更新用户信息接口",notes = "直接调用用户的更新方法")
    @PutMapping
    public Result update(@RequestBody User user) {

        userService.update(user);
        return Result.success();
    }

    @ApiOperation(value = "更新用户信息接口",notes = "直接调用用户的更新方法")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        User user = userService.findById(id);
        return Result.success(user);
    }

    @ApiOperation(value = "展示所有用户接口",notes = "直接调用用户的更新方法")
    @GetMapping
    public Result all() {
        return Result.success(userService.findAll());
    }

    @ApiOperation(value = "分页查询",notes = "使得一页展示五个内容")
    @PostMapping("/page")
    public Result page(@RequestBody User search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(userService.findPage(search, pageNum, pageSize));
    }
}
