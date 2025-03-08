package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.entity.*;
import com.example.service.*;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Resource
    private ScholarshipService scholarshipService;
    @Resource
    private ApplyService applyService;
    @Resource
    private JobService jobService;
    @Resource
    private FeedbackService feedbackService;
    @Resource
    private ScholarshipApplyService scholarshipApplyService;
    @Resource
    private WorkHourService workHourService;

    @GetMapping("/base")
    public Result base() {
        Map<String, Integer> resultMap = new HashMap<>();

        Apply apply = new Apply();
        apply.setStatus("审核通过");
        List<Apply> applies = applyService.selectAll(apply);

        List<Scholarship> scholarships = scholarshipService.selectAll(new Scholarship());
        List<Job> jobs = jobService.selectAll(new Job());
        List<Feedback> feedbacks = feedbackService.selectAll(new Feedback());

        resultMap.put("scholarshipNum", scholarships.size());
        resultMap.put("workingStudentNum", applies.size());
        resultMap.put("jobNum", jobs.size());
        resultMap.put("feedbackNum", feedbacks.size());
        return Result.success(resultMap);
    }

    @GetMapping("/pie")
    public Result pie() {
        List<Map<String, Object>> resultList = new ArrayList<>();

        Apply apply = new Apply();
        apply.setStatus("审核通过");
        List<Apply> applies = applyService.selectAll(apply);
        Map<String, Long> collectMap = applies.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getJobName()))
                .collect(Collectors.groupingBy(Apply::getJobName, Collectors.counting()));

        for (String key : collectMap.keySet()) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", key);
            map.put("value", collectMap.get(key));
            resultList.add(map);
        }
        return Result.success(resultList);
    }

    @GetMapping("/bar")
    public Result bar() {
        Map<String, List<Object>> resultMap = new HashMap<>();

        ScholarshipApply apply = new ScholarshipApply();
        apply.setStatus("审核通过");
        List<ScholarshipApply> list = scholarshipApplyService.selectAll(apply);

        Map<String, Long> collectMap = list.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getScholarshipName()))
                .collect(Collectors.groupingBy(ScholarshipApply::getScholarshipName, Collectors.counting()));

        List<Object> xList = new ArrayList<>();
        List<Object> yList = new ArrayList<>();

        for (String key : collectMap.keySet()) {
            xList.add(key);
            yList.add(collectMap.get(key));
        }

        resultMap.put("xAxis", xList);
        resultMap.put("yAxis", yList);
        return Result.success(resultMap);
    }

    @GetMapping("/bar2")
    public Result bar2() {
        Map<String, List<Object>> resultMap = new HashMap<>();

        List<WorkHour> workHours = workHourService.selectAll(new WorkHour());
        Map<String, Integer> collectMap = workHours.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getStudentName()))
                .collect(Collectors.groupingBy(WorkHour::getStudentName, Collectors.reducing(0, WorkHour::getTime, Integer::sum)));

        List<Object> xList = new ArrayList<>();
        List<Object> yList = new ArrayList<>();

        for (String key : collectMap.keySet()) {
            xList.add(key);
            yList.add(collectMap.get(key));
        }

        resultMap.put("xAxis", xList);
        resultMap.put("yAxis", yList);
        return Result.success(resultMap);
    }
}
