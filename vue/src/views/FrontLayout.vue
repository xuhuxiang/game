<template>
  <div>
<!--    <div style="padding: 5px 20px; color: #666; font-size: 12px" ><i class="el-icon-bell" style="margin-right: 2px"></i>公告：{{ top }}</div>-->
    <!--头部-->
    <div class="header">
      <div class="header-left">
        <img src="@/assets/imgs/logo.png" alt="">
        <div class="title">STORM游戏商城</div>
      </div>
      <div class="header-center">
        <div class="header-nav">
          <el-menu :default-active="$route.path" mode="horizontal" router>
						<el-menu-item index="/front/index">首页</el-menu-item>
						<el-menu-item index="/front/goods">所有游戏</el-menu-item>
						<el-menu-item v-if="user.role !== 1" index="/front/cart">我的购物车</el-menu-item>
						<el-menu-item v-if="user.role !== 1" index="/front/order">我的订单</el-menu-item>
          </el-menu>
        </div>
      </div>
      <div style="width: 300px">
        <el-input size="medium" style="width: 200px" v-model="name" clearable placeholder="请输入商品名称搜索"></el-input>
        <el-button size="medium" @click="search" style="margin-left: 5px">搜索</el-button>
      </div>
      <div class="header-right">
        <div v-if="!user.username">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown>
            <div class="header-dropdown">
              <img :src="user.avatar" alt="">
              <div style="margin-left: 10px">
                <span>{{ user.username }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
              </div>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div @click="goToPerson()">个人中心</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div style="text-decoration: none" @click="logout">退出</div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <!--主体-->
    <div class="main-body">
      <router-view ref="child" />
    </div>

  </div>

</template>

<script>
import request from "@/utils/request";

export default {
  name: "FrontLayout",

  data () {
    return {
      top: '',
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      name: ''
    }
  },

  methods: {
    goToPerson() {
			if (this.user.role === 1) {
				this.$router.push("/end/pAdmin")
			}
			if (this.user.role === 2) {
				this.$router.push("/end/pUser")
			}

    },
    // 退出登录
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/login");
    },
    search() {
      this.$router.replace('/front/search?name=' + this.name + '&random' + Math.random())
      this.$refs.child.searchGoods()
    }
  }

}
</script>

<style scoped>
  @import "@/assets/css/front.css";
</style>