package com.example.dao;

import com.example.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderDao extends Mapper<Order> {

	List<Order> findBySearch(@Param("businessId") String businessId,@Param("search") Order search);

	@Select("select * from orderInfo where userId = #{id}")
	List<Order> findByUserId(@Param("id") Integer id);
	//@Select("select * from orderInfo where userId= #{userId} and goodsId=#{goodsId}")
	Order findByUserIdGoodsId(@Param("userId") String userId,@Param("goodsId") String goodsId);
}
