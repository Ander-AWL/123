package com.example.mapper;

import com.example.entity.Apply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ApplyMapper {

    int insert(Apply apply);

    void updateById(Apply apply);

    void deleteById(Integer id);

    @Select("select * from `apply` where id = #{id}")
    Apply selectById(Integer id);

    List<Apply> selectAll(Apply apply);

    @Select("select * from apply where student_id = #{studentId} and job_id = #{jobId}")
    List<Apply> selectByStudentIdAndJobId(@Param("studentId") Integer studentId, @Param("jobId") Integer jobId);
}
