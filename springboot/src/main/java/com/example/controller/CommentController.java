package com.example.controller;

import com.example.common.Result;
import com.example.entity.Comment;
import com.example.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* 评论相关接口
*/
@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation(value = "新增评论接口",notes = "调用评论的添加方法")
    @PostMapping
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.success(comment);
    }

    @ApiOperation(value = "删除评论接口",notes = "调用评论的根据ID删除方法")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        commentService.delete(id);
        return Result.success();
    }

    @ApiOperation(value = "展示所有评论接口",notes = "调用评论的展示所有方法")
    @GetMapping("/{goodsId}")
    public Result all(@PathVariable Integer goodsId) {
        return Result.success(commentService.findAll(goodsId));
    }

    @ApiOperation(value = "分页查询",notes = "使得每一页都只有五个内容")
    @PostMapping("/page")
    public Result page(@RequestBody Comment search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize) {
        return Result.success(commentService.findPage(search, pageNum, pageSize));
    }
}
