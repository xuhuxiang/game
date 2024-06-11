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

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Resource
    private GoodsDao goodsDao;
    @Resource
    private PraiseDao praiseDao;

    public Goods add(Goods goods) {
        goodsDao.insertSelective(goods);
        return goods;
    }

    public void delete(Integer id) {
        goodsDao.deleteByPrimaryKey(id);
    }

    public void update(Goods goods) {
        goodsDao.updateByPrimaryKeySelective(goods);
    }

    public Goods findById(Integer id) {
        return goodsDao.selectByPrimaryKey(id);
    }

    public List<Goods> findAll() {
        return goodsDao.findBySearch(null);
    }

    public PageInfo<Goods> findPage(Goods search, Integer pageNum, Integer pageSize) {
        Account user = getAccount();
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> all = findByCondition(user, search);

        return PageInfo.of(all);
    }
    public PageInfo<Goods> findPage1(Goods search, Integer pageNum,String businessId, Integer pageSize) {
        Account user = getAccount();
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> all = findByCondition1(user,businessId, search);

        return PageInfo.of(all);
    }
    public PageInfo<Goods> findHotPage(Goods search, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> byHot = goodsDao.findByHot();
        return PageInfo.of(byHot);
    }

    public List<Goods> findByCondition1(Account user, String businessId,Goods search) {
		return goodsDao.findBySearch1(businessId,search);
    }
    public List<Goods> findByCondition(Account user,Goods search) {
        return goodsDao.findBySearch(search);
    }
    public List<Goods> findByFlId(Integer id) {
        return goodsDao.findByTypeId(id);
    }

    public void praise(Integer goodsId) {
        Goods goods = goodsDao.selectByPrimaryKey(goodsId);
        Account user = getAccount();
        Praise praise = praiseDao.findByRelIdAndUserIdAndRole(goodsId, user.getId(), user.getRole());
        if (ObjectUtil.isNull(praise)) {
            Praise tmp = new Praise();
            tmp.setUserId(user.getId());
            tmp.setRole(user.getRole());
            tmp.setRelId(goodsId);
            praiseDao.insertSelective(tmp);
            goods.setPraise(goods.getPraise() + 1);
        } else {
            praiseDao.deleteByRelIdAndUserIdAndRole(goodsId, user.getId(), user.getRole());
            goods.setPraise(goods.getPraise() - 1);
        }
        goodsDao.updateByPrimaryKeySelective(goods);
    }

    private Account getAccount() {
        Account user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
        return user;
    }

    public boolean praiseCheck(Integer goodsId) {
        Account user = getAccount();
        Praise praise = praiseDao.findByRelIdAndUserIdAndRole(goodsId, user.getId(), user.getRole());
        return ObjectUtil.isNotEmpty(praise);
    }

    public List<Goods> search(String name) {
        return goodsDao.findByName(name);
    }
}