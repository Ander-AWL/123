package com.example.mapper;

import com.example.entity.ScholarshipApply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScholarshipApplyMapper {

    int insert(ScholarshipApply scholarshipApply);

    void updateById(ScholarshipApply scholarshipApply);

    void deleteById(Integer id);

    @Select("select * from `scholarshipApply` where id = #{id}")
    ScholarshipApply selectById(Integer id);

    List<ScholarshipApply> selectAll(ScholarshipApply scholarshipApply);

    @Select("select * from scholarship_apply where student_id = #{studentId} and scholarship_id = #{scholarshipId}")
    List<ScholarshipApply> selectByStudentIdAndScholarshipId(@Param("studentId") Integer studentId, @Param("scholarshipId") Integer scholarshipId);
}
