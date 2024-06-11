<template>
  <div class="detail-container">
    <div class="detail-title">详情信息</div>
    <div class="detail-content">
      <div class="detail-left-img-fields">
        <div style="flex: 1;"><img :src="goods.img" style="width: 100%; height: 440px; margin-top: 10px; border-radius: 5px; border: #e3e3e3 solid 1px"></div>
        <div style="flex: 2; margin-left: 50px">
          <div class="detail-fields-item overflowShow" style="font-size: 18px;color:#000000;font-weight: 550;">{{goods.name}}</div>
          <div>
            <span style="font-size: 20px">￥</span>
            <span style="font-size: 30px">{{goods.price}}</span>
            <span style="font-size: 20px;margin-left: 10px;font-weight: lighter;text-decoration: line-through">￥{{goods.originalprice}}</span>
          </div>
          <div class="detail-fields-item overflowShow">
            <span style="font-size: 15px;color: #8c8a8a; margin-right: 20px">发行日期</span>
            <span style="font-size: 15px;color: #2f2e2e">{{goods.time}}</span>
          </div>
          <div class="detail-fields-item overflowShow">
            <span style="font-size: 15px;color: #8c8a8a; margin-right: 20px">显卡要求</span>
            <span style="font-size: 15px;color: #2f2e2e">{{goods.gpu}}</span>
          </div>
          <div class="detail-fields-item overflowShow" style="display: flex;">
            <div style="font-size: 12px;color: #8c8a8a; margin-right: 10px;text-align: center;line-height: 20px">关注</div>
            <img src="@/assets/imgs/点赞-active.png" v-if="praiseCheck" alt="" style="margin-right: 10px; width: 20px" @click="praise()">
            <img src="@/assets/imgs/点赞.png" v-else alt="" style="margin-right: 10px; width: 20px" @click="praise">
            <div style="font-size: 13px;color: #8c8a8a;text-align: center;line-height: 20px">{{goods.praise}}</div>
          </div>
          <div class="detail-fields-item overflowShow" style="margin-top: 40px">

            <el-button style="width: 150px;height: 60px;background-color: dodgerblue;color: white;margin-left: -2px; border:5px;border-radius: 2px;" @click="addCart">
              <span style="font-size: 16px">加入购物车</span>
            </el-button>
            <template>
              <el-popconfirm title="确定支付吗？" @confirm="save">
                <el-button style="width: 150px;height: 60px;background-color: dodgerblue;color: white;margin-left: 10px; border:5px;border-radius: 2px;" slot="reference" >
                  <span style="font-size: 16px">支付</span>
                </el-button>
              </el-popconfirm>
            </template>
          </div>
          <div>
            <el-button style="width: 150px;height: 60px;background-color: dodgerblue;color: white;margin-left: -2px; border:none;border-radius: 0;" @click="download">
              <span style="font-size:16px;">立即下载</span>
            </el-button>
          </div>
        </div>
      </div>

      <!--   富文本详情页部分   -->
      <div class="detail-richtext">
        <video-player class="video-player vjs-custom-skin" ref="videoPlayer" :playsinline="true" :options="playerOptions"/>
        <div class="detail-richtext-content w-e-text" v-html="goods.description"></div>
      </div>
    </div>
    <div class="detail-title">评论信息</div>
    <div class="detail-comment">
      <div>
        <el-row v-for="item in comments" style="margin-bottom: 10px">
          <el-col :span="4" style="display: flex; align-items: center;">
            <img :src="item.avatar" style="width: 50px; height: 50px; border-radius: 50%;">
            <div style="margin-left: 10px; flex: 1" class="overflowShow">{{item.userName}}</div>
          </el-col>
          <el-col :span="20">
            <el-row>
              <el-col :span="20" style="height: 50px; line-height: 50px">{{item.content}}</el-col>
              <el-col :span="4" style="height: 50px; line-height: 50px; text-align: right">{{item.time}}</el-col>
            </el-row>
            <el-row :span="3" style="margin-top: 20px" v-for="child in item.children">
              <el-col :span="4" style="display: flex; align-items: center;">
                <img :src="child.avatar" style="width: 50px; height: 50px; border-radius: 50%;">
                <div style="margin-left: 10px; flex: 1" class="overflowShow">{{child.userName}} 回复：</div>
              </el-col>
              <el-col :span="16" style="height: 50px; line-height: 50px">{{child.content}}</el-col>
              <el-col :span="4" style="height: 50px; line-height: 50px; text-align: right">{{child.time}}</el-col>
            </el-row>

            <el-row :span="3" style="margin-top: 20px">
              <el-col :span="15">
                <el-input v-model="item.reply"></el-input>
              </el-col>
              <el-col :span="4" style="margin-left: 10px">
                <el-button type="primary" @click="submit(item.id, item.reply)">回复</el-button>
              </el-col>
            </el-row>
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
    let goodsId = this.$route.query.id
    return {
      user: JSON.parse(localStorage.getItem("user") || {}),
      goods: {},
      goodsId: goodsId,
      gameLink:"",
      order:{},
      have:"123",
      comments: [],
      addressList: [],
      dialogVisible: false,
      content: '',
      praiseCheck: false,
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        autoplay: false, //如果true,浏览器准备好时开始回放。
        muted: false, // 默认情况下将会消除任何音频。
        loop: false, // 导致视频一结束就重新开始。
        preload: 'auto', // 建议浏览器在<video>加载元素后是否应该开始下载视频数据。auto浏览器选择最佳行为,立即开始加载视频（如果浏览器支持）
        language: 'zh-CN',
        aspectRatio: '16:9', // 将播放器置于流畅模式，并在计算播放器的动态大小时使用该值。值应该代表一个比例 - 用冒号分隔的两个数字（例如"16:9"或"4:3"）
        fluid: true, // 当true时，Video.js player将拥有流体大小。换句话说，它将按比例缩放以适应其容器。
        sources: [{
          type: "video/mp4",// 这里的种类支持很多种：基本视频格式、直播、流媒体等，具体可以参看git网址项目
          src: ""// url地址
        }],
        poster:"",
        notSupportedMessage: '此视频暂无法播放，请稍后再试', // 允许覆盖Video.js无法播放媒体源时显示的默认信息。
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true  // 全屏按钮
        }
      }
    }
  },
  mounted() {
    this.load()
    this.loadComment()
    this.checkPraise()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    load() {
      request.get('/goods/' + this.goodsId).then(res => {
        this.goods = res.data
        this.playerOptions['poster'] = this.goods.img
        this.playerOptions['sources'][0]['src'] = this.goods.video;
      })
    },
    loadComment() {
      request.get("/comment/" + this.goodsId).then(res => {
        if (res.code === '0') {
          this.comments = res.data
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    // buy() {
    //   this.dialogVisible = true;
    // },
    save() {
      if (!this.user && !this.user.username) {
        this.$notify.error("登录失效，请重新登陆")
        this.$router.push("/login")
        return;
      }
      if (this.user.role === 1) {
        this.$notify.warning("您是管理员，不支持该操作")
        return;
      }
      request.get("/order/searchOne?userId="+this.user.id+"&goodsId="+this.goods.id).then(res => {
        if (res.code === '0' && res.data===null) {
          request.get("/cart/search?userId="+this.user.id+"&goodsId="+this.goods.id).then(res=>{
            if(res.code === '0' && res.data===null){
              this.goods.userId = this.user.id;
              this.goods.goodsId = this.goods.id;
              this.goods.id = null;
              request.post("/order/buy/one", this.goods).then(res => {
                if (res.code === '0') {
                  this.$notify.success("购买成功")
                  this.$router.push("/front/order")
                } else {
                  this.$notify.error(res.msg)
                }
              })
            } else {
              this.$notify.error("你已将此游戏放入购物车，请去购物车支付");
            }
          })
        } else {
          this.$notify.error("你已购买过此游戏");
        }
      })

    },
    addCart() {
      if (!this.user && !this.user.username) {
        this.$notify.error("登录失效，请重新登陆")
        this.$router.push("/login")
        return;
      }
      if (this.user.role === 1) {
        this.$notify.warning("您是管理员，不支持该操作")
        return;
      }
      request.get("/order/searchOne?userId="+this.user.id+"&goodsId="+this.goods.id).then(res => {
        if (res.code === '0' && res.data===null){
          request.get("/cart/search?userId="+this.user.id+"&goodsId="+this.goods.id).then(res=>{
            if(res.code === '0' && res.data===null){
              let data = {
                userId: this.user.id,
                goodsId: this.goods.id,
              }
              request.post("/cart", data).then(res => {
                if (res.code === '0') {
                  this.$notify.success("加入成功")
                  this.$router.push("/front/cart")
                } else {
                  this.$notify.error(res.msg)
                }
              })
            } else {
              this.$notify.error("你已将此游戏放入购物车，请去购物车支付");
            }
          })
        } else {
          this.$notify.error("你已购买过此游戏");
        }
      })

      // let data = {
      //   userId: this.user.id,
      //   goodsId: this.goods.id,
      // }
      //
      // request.post("/cart", data).then(res => {
      //   if (res.code === '0') {
      //     this.$notify.success("加入成功")
      //     this.$router.push("/front/cart")
      //   } else {
      //     this.$notify.error(res.msg)
      //   }
      // })
    },
    download(){
      if (!this.user && !this.user.username) {
        this.$notify.error("登录失效，请重新登陆")
        this.$router.push("/login")
        return;
      }

      request.get("/order/searchOne?userId="+this.user.id+"&goodsId="+this.goods.id).then(res => {
        if (res.code === '0'&&res.data!=null) {
          this.order = res.data
          if("已完成"==this.order.status){
            window.location.href =  this.goods.goodsLink
          }else{
            this.$notify.error("请先购买后再下载")
          }
        } else {
          this.$notify.error("请先购买后再下载")
        }
      })
    },
    submit(parentId, content) {
      if (!content) {
        this.$notify.warning("请输入内容")
      }
      let data = {content: content, userId: this.user.id, role: this.user.role, parentId: parentId, relId: this.goodsId}
      request.post("/comment", data).then(res => {
        if (res.code ==='0') {
          this.$notify.success("评论成功")
          this.loadComment()
          this.content = ""
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    checkPraise() {
      request.get("/goods/praise/check/" + this.goodsId).then(res => {
        if (res.code === '0') {
          this.praiseCheck = res.data
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    praise() {
      request.get("/goods/praise/" + this.goodsId).then(res => {
        if (res.code === '0') {
          this.checkPraise();
          this.load();
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>
