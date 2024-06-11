<template>
  <div class="main-content1">
    <div style="margin-top: 40px;padding: 0 260px">
      <div class="main-table-box">
        <div style="height: 50px; line-height: 50px; font-weight: bold; font-size: 20px;margin-bottom: 40px">我的购物车</div>
        <el-table style="width: 100%" :data="cartData" size="small" strip header-cell-class-name="tableHeaderClass" >
          <el-table-column label="商品" width="100px">
            <template v-slot="scope">
              <el-image :src="scope.row.img" :preview-src-list="[scope.row.img]" style="width: 50px; height: 50px; border-radius: 50%"></el-image>
            </template>
          </el-table-column>
          <el-table-column label="商品名称" prop="goodsName" width="400px"></el-table-column>
          <el-table-column label="价格" prop="price"></el-table-column>
          <el-table-column label="操作" fixed="right">
            <template v-slot="scope">
              <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
                <el-button size="small" type="danger" slot="reference">删除</el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 100px; text-align: right; padding: 20px 60px">
          <div class="detail-fields-item overflowShow" style="margin-top: 20px">
            <template>
              <el-popconfirm title="确定支付吗？" @confirm="save">
                <el-button size="big" type="danger" slot="reference" >支付</el-button>
              </el-popconfirm>
            </template>
          </div>
        </div>
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
      cartData: [],
      goods: {},
      goodsId: goodsId,
      dialogVisible: false,
      addressId: null,
    }
  },
  mounted() {
    this.load()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    load() {
      request.get("/cart").then(res => {
        if (res.code === '0') {
          this.cartData = res.data
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    del(id) {
      request.delete("/cart/" + id).then(res => {
        if (res.code === '0') {
          this.$notify.success("删除成功")
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
    },
    // buy() {
    //   this.dialogVisible = true;
    // },
    save() {
      if (this.cartData.length === 0) {
        this.$notify.warning("您的购物车空空如也")
        return
      }
      // if (!this.addressId) {
      //   this.$notify.warning("请选择收货地址,如没有地址，请到地址管理设置地址")
      //   return
      // }
      // let data = {list: this.cartData, addressId: this.addressId}
      let data = {list: this.cartData}
      request.post("/order/buy", data).then(res => {
        if (res.code === '0') {
          this.$notify.success("购买成功")
          this.$router.push("/front/order")
        } else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>
