package com.example.controller;

import com.example.common.Result;
import com.example.entity.*;
import com.example.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 管理员相关接口
*/
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation(value = "管理员添加管理员接口",notes = "调用管理员的添加管理员方法")
    @PostMapping
    public Result add(@RequestBody Admin admin) {

        adminService.add(admin);
        return Result.success(admin);
    }

    @ApiOperation(value = "删除用户接口",notes = "直接调用管理员的删除用户方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.delete(id);
        return Result.success();
    }

    /**
     * 描述：更新
     */
    @ApiOperation(value = "更新用户信息接口",notes = "直接调用管理员的更新用户信息方法")
    @PutMapping
    public Result update(@RequestBody Admin admin) {

        adminService.update(admin);
        return Result.success();
    }

    @ApiOperation(value = "根据ID查询用户接口",notes = "直接调用管理员的通过ID查找用户方法")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Admin admin = adminService.findById(id);
        return Result.success(admin);
    }

    @ApiOperation(value = "显示所有用户接口",notes = "直接调用管理员的显示所有用户方法")
    @GetMapping
    public Result all() {
        return Result.success(adminService.findAll());
    }

    @ApiOperation(value = "分页查询接口",notes = "让每一页只有五个结果")
    @PostMapping("/page")
    public Result page(@RequestBody Admin search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(adminService.findPage(search, pageNum, pageSize));
    }
}
