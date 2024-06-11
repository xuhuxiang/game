package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.config.JwtTokenUtils;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.*;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BusinessService {
    @Resource
    private BusinessDao businessDao;

    public Business add(Business business) {
        // 唯一校验
        Business info = businessDao.findByUsername(business.getUsername());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (StringUtils.isEmpty(business.getPassword())) {
            // 默认密码123456
            business.setPassword("123456");
        }
        if (StringUtils.isEmpty(business.getAvatar())) {
            business.setAvatar("http://localhost:8080/files/qy-default.png");
        }
        businessDao.insertSelective(business);
        return business;
    }

    public void delete(Integer id) {
        businessDao.deleteByPrimaryKey(id);
    }

    public void update(Business business) {
        businessDao.updateByPrimaryKeySelective(business);
    }

    public Business findById(Integer id) {
        return businessDao.selectByPrimaryKey(id);
    }

    public List<Business> findAll() {
        return businessDao.findBySearch(null);
    }

    public PageInfo<Business> findPage(Business search, Integer pageNum, Integer pageSize) {
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Business> all = findByCondition(search);

        return PageInfo.of(all);
    }

    public List<Business> findByCondition(Business search) {
        return businessDao.findBySearch(search);
    }

    public Business findByUserName(String username) {
        return businessDao.findByUsername(username);
    }

    public Business login(String username, String mm) {
        Business business = businessDao.findByUsername(username);
        if (business == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!mm.equalsIgnoreCase(business.getPassword())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return business;
    }

}
