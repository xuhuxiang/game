package com.example.dao;

import com.example.entity.*;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
@Repository
public interface BusinessDao extends Mapper<Business> {
    @Select("select * from business where `username` = #{name}")
    Business findByUsername(@Param("name") String name);

    List<Business> findBySearch(@Param("search") Business search);
}
