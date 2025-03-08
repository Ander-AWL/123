package com.example.controller;

import com.example.common.Result;
import com.example.entity.Student;
import com.example.entity.WorkHour;
import com.example.service.WorkHourService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 工时记录前端请求接口
 */
@RestController
@RequestMapping("/workHour")
public class WorkHourController {

    @Resource
    private WorkHourService workHourService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody WorkHour workHour) {
        workHourService.add(workHour);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody WorkHour workHour) {
        workHourService.updateById(workHour);
        return Result.success();
    }
    /**
     * 提现
     */
    @PutMapping("/cash")
    public Result cash(@RequestBody WorkHour workHour) {
        Student student = workHourService.cash(workHour);
        return Result.success(student);
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        workHourService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        workHourService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        WorkHour workHour = workHourService.selectById(id);
        return Result.success(workHour);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(WorkHour workHour) {
        List<WorkHour> list = workHourService.selectAll(workHour);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(WorkHour workHour,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<WorkHour> pageInfo = workHourService.selectPage(workHour, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
