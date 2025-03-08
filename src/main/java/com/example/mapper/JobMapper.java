package com.example.mapper;

import com.example.entity.Job;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobMapper {

    int insert(Job job);

    void updateById(Job job);

    void deleteById(Integer id);

    @Select("select * from `job` where id = #{id}")
    Job selectById(Integer id);

    List<Job> selectAll(Job job);

}
