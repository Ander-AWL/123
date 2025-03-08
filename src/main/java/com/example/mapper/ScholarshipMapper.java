package com.example.mapper;

import com.example.entity.Scholarship;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScholarshipMapper {

    int insert(Scholarship scholarship);

    void updateById(Scholarship scholarship);

    void deleteById(Integer id);

    @Select("select * from `scholarship` where id = #{id}")
    Scholarship selectById(Integer id);

    List<Scholarship> selectAll(Scholarship scholarship);

}
