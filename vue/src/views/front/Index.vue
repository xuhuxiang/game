<template>
  <div class="main-content">
    <div class="main-line" >
      <div class="main-line-1">
        <div class="main-img">
          <el-carousel height="80vh" :interval="2000">
            <el-carousel-item v-for="(item,index) in carousel_1_1" :key="index">
              <img :src="item.img" alt=""  style="height: 500px" @click="$router.push('/front/detail?id=' + item.goodsId)">
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>

    <!--    热门商品展示-->
    <div style="background-color: rgba(180,180,238,0.07);padding: 10px 50px">
      <!--      1 文字部分-->
      <div style="margin-top: 10px;margin-bottom: 10px">
        <div style="text-align: center;margin:30px auto">
          <div style="color: #2c2b2b; font-size: 28px;font-weight:550;margin-bottom: 10px; font-family: 'Dubai Medium'">火爆游戏</div>
        </div>
      </div>
      <!--   3热门商品展示-->
      <div >
        <el-row >
          <el-col :span="6" v-for="obj in goodsData" style="margin-bottom: 10px;"  >
            <el-card :body-style="{ padding: '0px' }" style="height: 400px;width: 95%;margin: auto" >
              <img :src="obj.img" alt="" style="width: 100%;height: 300px;" class="image" @click="$router.push('/front/detail?id=' + obj.id)">
              <div style="padding: 14px; text-align: center">
                <div class="overflowShow" style="font-weight: 550;font-size: 16px; color:#5b5a5a;letter-spacing: 1px;margin-bottom: 24px">{{obj.name}}</div>
                <div class="bottom clearfix">
                  <span class="time">￥{{obj.price}}</span>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>

  </div>
</template>

<script>
import request from "@/utils/request";

export default {

  data() {
    return {
      carousel_1_1: [],
      activeName: '',
      typeData: [],
      goodsData: []
    }
  },
  // 监听路由
  watch: {
    $route(to, from) {
      if(to.path === '/front/index') {
        location.reload()
      }
    }
  },
  mounted() {
    this.load()
    this.loadBanner()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    //加载热门商品
    load() {
      request.get("/goods/hotgoods").then(res => {
        if (res.code === '0') {
          this.goodsData = res.data
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    //  加载轮播图
    loadBanner(){
      request.get("/banner").then(res => {
        if (res.code === '0'){
          this.carousel_1_1 = res.data;
          console.log(this.carousel_1_1)
        } else {
          this.$notify.error(res.msg);
        }
      })
    },
    handleClick(tab) {
      this.load(tab.name)
    },
  }
}
</script>
