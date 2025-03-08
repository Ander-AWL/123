package com.example.controller;

import com.example.common.Result;
import com.example.entity.Work;
import com.example.service.WorkService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工作记录前端请求接口
 */
@RestController
@RequestMapping("/work")
public class WorkController {

    @Resource
    private WorkService workService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Work work) {
        workService.add(work);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Work work) {
        workService.updateById(work);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        workService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        workService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Work work = workService.selectById(id);
        return Result.success(work);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Work work) {
        List<Work> list = workService.selectAll(work);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Work work,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Work> pageInfo = workService.selectPage(work, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
