<template>
  <div class="login-container">
    <div style="display: flex">
      <div style="margin-right: 550px;">
        <div style="text-align: left; font-weight: bold;  color: #000000;   font-size: 100px">Welcome!</div>
        <div style="text-align: left;  margin-left:20px; color: #868484;  font-size: 20px">欢迎登录游戏商城系统</div>
      </div>
      <el-form>
        <el-form-item class="form-box" style="padding-top: 40px;border-radius: 10px">
          <el-form-item >
            <el-input style="margin-bottom: 15px;" size="large" prefix-icon="el-icon-s-custom" placeholder="请输入用户名" v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item >
            <el-input style="margin-bottom: 15px" size="large" show-password prefix-icon="el-icon-key" placeholder="请输入密码" v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item >
            <el-input style="margin-bottom: 15px" size="large" show-password prefix-icon="el-icon-key" placeholder="请输入确认密码" v-model="form.nmm"></el-input>
          </el-form-item>
          <el-form-item >
            <el-select style="width: 100%;margin-bottom: 15px" size="large"  placeholder="请选择角色" v-model="form.role">
              <el-option value="2" label="用户"></el-option>
              <el-option value="3" label="商家"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button style="width: 100%;height: 50px;background-color: #282b33;border-color: #282b33;color:white" size="large"  @click="register">注册</el-button>
          </el-form-item>
        </el-form-item>
        <el-form-item style="background-color: #ffffff; border-radius: 10px; display: flex; justify-content: center;align-items: center">
          <el-form-item class="regBox">
            <a href="javascript:void(0)" @click="navLogin" style="text-indent: 12px;">登录</a><span class="el-icon-right"></span>
          </el-form-item>

        </el-form-item>

      </el-form>
    </div>
  </div>
</template>


<script>

import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      form: {}
    }
  },
  // 页面加载的时候，做一些事情，在created里面
  created() {
  },
  // 定义一些页面上控件出发的事件调用的方法
  methods: {
    // 注册按钮点击事件入口
    register() {
      console.log(this.form.role)
      // 页面表单数据的输入校验
      if (!this.form.username) {
        this.$notify.error("请输入用户名");
        return;
      }
      if (!this.form.password) {
        this.$notify.error("请输入密码");
        return;
      }
      if (this.form.password !== this.form.nmm) {
        this.$notify.error("两次密码输入不一致");
        return;
      }
      if (!this.form.role) {
        this.$notify.error("请选择角色");
        return;
      }
      // 调用后台注册接口
      request.post('/register', this.form).then(res => {
        if(res.code === '0') {
          // 将用户信息保存一份到浏览器缓存里
          this.$notify.success("注册成功！");
          this.$router.push("/login")
        } else {
          this  .$notify.error(res.msg);
        }
      });
    },
    navLogin() {
      this.$router.push("/login")
    }
  }
}
</script>
<style scoped>
.login-container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/imgs/login_bg.jpg");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.form-box {
  width: 300px;
  padding: 20px 40px;
  border-radius: 2px;
  background-color: #ffffff;
  opacity: 1;
}
/deep/.el-form-item {
  margin-bottom: 5px;
}
/deep/.el-input__inner {
  border-color: #ccc !important;
  border-radius: 2px;
  background-color: #ffffff !important;
  color: #333;
}
/deep/.el-input__icon {
  color: #666;
}

a {
  text-decoration: none;
  color: #0c0c0c;
  font-weight: bold;
}
.regBox {
  font-size: 28px;
  margin-bottom: 10px;
  margin-top: 10px;
  text-align: right;
}
</style>