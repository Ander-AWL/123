package com.example.mapper;

import com.example.entity.Resume;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ResumeMapper {

    int insert(Resume resume);

    void updateById(Resume resume);

    void deleteById(Integer id);

    @Select("select * from `resume` where id = #{id}")
    Resume selectById(Integer id);

    List<Resume> selectAll(Resume resume);

}
