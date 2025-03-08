package com.example.controller;

import com.example.common.Result;
import com.example.entity.ScholarshipApply;
import com.example.service.ScholarshipApplyService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 奖学金申请前端请求接口
 */
@RestController
@RequestMapping("/scholarshipApply")
public class ScholarshipApplyController {

    @Resource
    private ScholarshipApplyService scholarshipApplyService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody ScholarshipApply scholarshipApply) {
        scholarshipApplyService.add(scholarshipApply);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody ScholarshipApply scholarshipApply) {
        scholarshipApplyService.updateById(scholarshipApply);
        return Result.success();
    }

    /**
     * 单个删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        scholarshipApplyService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        scholarshipApplyService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 单个查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ScholarshipApply scholarshipApply = scholarshipApplyService.selectById(id);
        return Result.success(scholarshipApply);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(ScholarshipApply scholarshipApply) {
        List<ScholarshipApply> list = scholarshipApplyService.selectAll(scholarshipApply);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(ScholarshipApply scholarshipApply,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ScholarshipApply> pageInfo = scholarshipApplyService.selectPage(scholarshipApply, pageNum, pageSize);
        return Result.success(pageInfo);
    }

}
