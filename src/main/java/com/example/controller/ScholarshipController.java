package com.example.controller;

import com.example.common.Result;
import com.example.entity.Scholarship;
import com.example.service.ScholarshipService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 奖学金信息前端请求接口
 */
@RestController
@RequestMapping("/scholarship")
public class ScholarshipController {

    @Resource
    private ScholarshipService scholarshipService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Scholarship scholarship) {
        scholarshipService.add(scholarship);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody Scholarship scholarship) {
        scholarshipService.updateById(scholarship);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        scholarshipService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        scholarshipService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Scholarship scholarship = scholarshipService.selectById(id);
        return Result.success(scholarship);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Scholarship scholarship) {
        List<Scholarship> list = scholarshipService.selectAll(scholarship);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Scholarship scholarship,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Scholarship> pageInfo = scholarshipService.selectPage(scholarship, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
