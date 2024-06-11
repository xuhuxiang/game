package com.example.controller;

import com.example.common.config.JwtTokenUtils;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.*;
import com.example.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 系统用户操作相关接口
 */
@RestController
public class AccountController {

	@Resource
	private AdminService adminService;
	@Resource
	private UserService userService;
	@Resource
	private BusinessService businessService;
    /**
     * 描述：用户登录接口
     */
    @PostMapping("/login")
	@ApiOperation(value = "用户登录接口",notes = "在数据库中查询用户")
    public Result login(@RequestBody Account account) {
        Integer role = account.getRole();
        Account loginUser = new Account();
        Map<String, Object> map = new HashMap<>();
		if (1 == role) {
			loginUser = adminService.login(account.getUsername(), account.getPassword());
			map.put("user", loginUser);
		}
		if (2 == role) {
			loginUser = userService.login(account.getUsername(), account.getPassword());
			map.put("user", loginUser);
		}
		if(3 == role){
			loginUser = businessService.login(account.getUsername(),account.getPassword());
			map.put("user", loginUser);
		}
        // create token
        String token = JwtTokenUtils.genToken(loginUser.getId() + "-" + loginUser.getRole(), loginUser.getPassword());
        map.put("token", token);
        return Result.success(map);
    }

	@ApiOperation(value = "用户注册接口",notes = "根据注册类型添加用户")
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        Integer role = account.getRole();
        Account login = new Account();
		if (1 == role) {
			Admin info = new Admin();
			BeanUtils.copyProperties(account, info);
			login = adminService.add(info);
		}
		if (2 == role) {
			User info = new User();
			BeanUtils.copyProperties(account, info);
			login = userService.add(info);
		}
		if(3 == role){
			Business info = new Business();
			BeanUtils.copyProperties(account, info);
			login = businessService.add(info);
		}
        return Result.success(login);
    }


	@ApiOperation(value = "获取登录用户信息",notes = "根据用户类型，调取对应类型的根据ID查找用户的方法")
    @GetMapping("/getUser")
    public Result getAccountInfo() {
        Account loginUser = JwtTokenUtils.getCurrentUser();
        if (loginUser == null) {
            return Result.success(new Object());
        }
        Integer role = loginUser.getRole();

		if (1 == role) {
			return Result.success(adminService.findById(loginUser.getId()));
		}
		if (2 == role) {
			return Result.success(userService.findById(loginUser.getId()));
		}
		if (3 == role) {
			return Result.success(businessService.findById(loginUser.getId()));
		}
        return Result.success(new Object());
    }


	@ApiOperation(value = "更新密码接口",notes="先根据token获取当前用户，然后设置当前用户的密码，根据角色分别更新数据库")
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account info) {
        Account account = JwtTokenUtils.getCurrentUser();
        if (account == null) {
            return Result.error(ResultCode.USER_NOT_EXIST_ERROR.code, ResultCode.USER_NOT_EXIST_ERROR.msg);
        }
        String oldPassword = info.getPassword();
        if (!oldPassword.equals(account.getPassword())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        Integer role = account.getRole();
		if (1 == role) {
			Admin admin = new Admin();
			BeanUtils.copyProperties(account, admin);
			admin.setPassword(info.getNmm());
			adminService.update(admin);
		}
		if (2 == role) {
			User user = new User();
			BeanUtils.copyProperties(account, user);
			user.setPassword(info.getNmm());
			userService.update(user);
		}
		if (3 == role) {
			Business business = new Business();
			BeanUtils.copyProperties(account, business);
			business.setPassword(info.getNmm());
			businessService.update(business);
		}
        return Result.success();
    }

	@ApiOperation(value = "重置密码接口",notes="先根据token获取当前用户，然后设置当前用户的密码，根据角色分别更新数据库")
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody Account account) {
        Integer role = account.getRole();
		if (1 == role) {
			Admin info = adminService.findByUsername(account.getUsername());
			if (info == null) {
				return Result.error(ResultCode.USER_NOT_EXIST_ERROR);
			}
			info.setPassword("123456");
			adminService.update(info);
		}
		if (2 == role) {
			User info = userService.findByUserName(account.getUsername());
			if (info == null) {
				return Result.error(ResultCode.USER_NOT_EXIST_ERROR);
			}
			info.setPassword("123456");
			userService.update(info);
		}
		if (3 == role) {
			Business info = businessService.findByUserName(account.getUsername());
			if (info == null) {
				return Result.error(ResultCode.USER_NOT_EXIST_ERROR);
			}
			info.setPassword("123456");
			businessService.update(info);
		}
        return Result.success();
    }
}
