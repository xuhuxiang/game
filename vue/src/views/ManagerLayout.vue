<template>
  <div>
    <!--头部-->
    <div class="manager-header" id="header">
      <a href="">
        <div class="manager-header-left">
          <img src="@/assets/imgs/logo.png" alt="">
          <div class="title">STORM游戏商城后台</div>
        </div>
      </a>
      <div class="manager-header-center">欢迎您，{{ user.username }}</div>
      <div class="manager-header-right">
        <el-dropdown placement="bottom">
          <div class="avatar-box"><img :src="user.avatar"></div>
          <el-dropdown-menu slot="dropdown">
            <div @click="goToPerson()"><el-dropdown-item>个人信息</el-dropdown-item></div>
            <a href="/front/index"><div><el-dropdown-item>前往前台</el-dropdown-item></div></a>
            <div @click="logout()"><el-dropdown-item>退出登录</el-dropdown-item></div>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
    <!-- 中间部分-->
    <div style="display: flex">
      <!--菜单-->
      <div id="main-menu">
        <div class="main-nav">
          <el-menu :default-active="$route.path" router class="main-nav-menu">
            <el-submenu index="1" v-if="user.role === 1">
              <template slot="title"><i class="el-icon-user"></i><span>用户管理</span></template>
              <el-menu-item index="/end/admin">管理员</el-menu-item>
              <el-menu-item index="/end/user">用户</el-menu-item>
              <el-menu-item index="/end/business">商家</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-folder-opened"></i><span>信息管理</span></template>
              <el-menu-item index="/end/type" v-if="user.role === 1">游戏分类</el-menu-item>
              <el-menu-item index="/end/goods" v-if="user.role !== 2">商品管理</el-menu-item>
              <el-menu-item index="/end/hotgoods" v-if="user.role === 1">热销管理</el-menu-item>
              <el-menu-item index="/end/banner" v-if="user.role === 1">轮播图管理</el-menu-item>
              <el-menu-item index="/end/order" v-if="user.role === 3">订单管理</el-menu-item>
              <el-menu-item index="/end/comment" v-if="user.role !==3 ">评论管理</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-setting"></i><span>系统管理</span></template>
              <el-menu-item index="/end/password">修改密码</el-menu-item>
              <el-menu-item index="/end/logout" @click="logout()">退出登录</el-menu-item>
            </el-submenu>
          </el-menu>
        </div>
      </div>

      <!--主体-->
      <div class="main-body">
        <div>
          <router-view />
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "ManagerLayout",

  data () {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },

  mounted() {
    this.$bus.$on('updateTx', (tx) => {
      this.user.tx = tx
    })
  },

  methods: {
    goToPerson() {
      if (this.user.role === 1) {
        this.$router.push("/end/pAdmin")
      }
      if (this.user.role === 2) {
        this.$router.push("/end/pUser")
      }
      if(this.user.role === 3){
        this.$router.push("/end/pBusiness")
      }
    },
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/login");
    }
  }
}
</script>

<style scoped>
@import "@/assets/css/manager.css";
</style>