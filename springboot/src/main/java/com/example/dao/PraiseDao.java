package com.example.dao;

import com.example.entity.Praise;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface PraiseDao extends Mapper<Praise> {

	@Select("select * from praise where relId = #{relId} and userId = #{userId} and role = #{role}")
	Praise findByRelIdAndUserIdAndRole(@Param("relId") Integer goodsId, @Param("userId") Integer userId, @Param("role") Integer role);

	@Delete("delete from praise where relId = #{relId} and userId = #{userId} and role = #{role}")
	void deleteByRelIdAndUserIdAndRole(@Param("relId") Integer goodsId, @Param("userId") Integer userId, @Param("role") Integer role);
}
