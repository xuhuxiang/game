<template>
  <div>
    <div>
      <div class="main-body-content">
        <div class="main-body-search">
          <div class="main-body-header">游戏管理</div>
          <el-input size="small" class="main-input" placeholder="请输入游戏名称" v-model="search.name"></el-input>
          <el-button size="small" type="primary" @click="load()">查询</el-button>
          <el-button size="small" type="primary" @click="add">新增</el-button>
        </div>
        <div class="main-table-box">
          <el-table style="width: 100%" :data="tableData" size="small" strip header-cell-class-name="tableHeaderClass" >
            <el-table-column label="游戏图片">
              <template v-slot="scope">
                <el-image :src="scope.row.img" :preview-src-list="[scope.row.img]" style="width: 50px; height: 50px; border-radius: 50%"></el-image>
              </template>
            </el-table-column>
            <el-table-column label="游戏名称" prop="name"></el-table-column>
            <el-table-column label="游戏分类" prop="type"></el-table-column>
            <el-table-column label="发行时间" prop="time"></el-table-column>
            <el-table-column label="游戏价格" prop="price"></el-table-column>
            <el-table-column label="显卡要求" prop="gpu"></el-table-column>
            <el-table-column label="点赞量" prop="praise"></el-table-column>
            <el-table-column label="是否热门" prop="ishot"></el-table-column>
            <el-table-column label="游戏介绍">
              <template v-slot="scope">
                <el-button size="small" type="primary" @click="initEditorView(scope.row)">查看</el-button>
              </template>
            </el-table-column>
            <el-table-column label="游戏视频">
              <template v-slot="scope">
                <el-button size="small" type="primary" @click="initEditorView1(scope.row)">查看</el-button>
              </template>
            </el-table-column>
            <el-table-column label="操作" :width="colWidth" fixed="right">
              <template v-slot="scope">
                <el-button size="small" type="primary" @click="edit(scope.row)">编辑</el-button>
                <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
                  <el-button size="small" type="danger" slot="reference">删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div style="margin: 20px 0">
          <el-pagination
              background
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-size="5"
              layout="total, prev, pager, next"
              :total="total">
          </el-pagination>
        </div>
      </div>
    </div>
    <el-dialog title="请填写信息" :visible.sync="dialogVisible" width="40%">
      <el-form :model="form" label-position="right" label-width="100px" style="padding-right: 40px">
        <el-form-item label="游戏图片">
          <el-upload action="http://localhost:8080/files/upload" ref="jdtp" :on-success="jdtpSuccessUpload">
            <el-button size="small" type="success">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="游戏视频">
          <el-upload action="http://localhost:8080/files/upload" ref="jdtp" :on-success="jdtpSuccessUpload1">
            <el-button size="small" type="success">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="游戏链接">
          <el-upload action="http://localhost:8080/files/upload" ref="jdtp" :on-success="jdtpSuccessUpload2">
            <el-button size="small" type="success">点击上传</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="游戏名称">
          <el-input size="small" v-model="form.name" placeholder="请输入游戏名称"></el-input>
        </el-form-item>
        <el-form-item label="游戏分类">
          <el-select size="small" style="width: 100%" v-model="form.typeId">
            <el-option v-for="item in typeData" :value="item.id" :label="item.name" :key="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否热门">
          <el-radio-group size="small" v-model="form.ishot">
            <el-radio label="是"></el-radio>
            <el-radio label="否"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="游戏价格">
          <el-input size="small" v-model="form.price" placeholder="请输入游戏价格"></el-input>
        </el-form-item>
        <el-form-item label="显卡要求">
          <el-input size="small" v-model="form.GPU" placeholder="请输入显卡要求"></el-input>
        </el-form-item>
        <el-form-item label="游戏介绍">
          <div id="wang-editor"></div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save">保 存</el-button>
      </div>
    </el-dialog>
    <el-dialog title="游戏介绍" :visible.sync="richTextDialogVisible">
      <div class="w-e-text" v-html="form.description"></div>
    </el-dialog>
    <el-dialog title="游戏视频" :visible.sync="richVideoDialogVisible">
      <video-player class="video-player vjs-custom-skin" ref="videoPlayer" :playsinline="true" :options="playerOptions"/>
    </el-dialog>

  </div>
</template>

<script>
import request from "@/utils/request";
import wangEditor from "wangeditor";
let baseUrl = "/goods/"

// 富文本
let editor
function initWangEditor(content) {	setTimeout(() => {
  if (!editor) {
    editor = new wangEditor('#wang-editor')
    editor.config.placeholder = '请输入内容'
    editor.config.uploadFileName = 'file'
    editor.config.uploadImgServer = 'http://localhost:8080/files/wang/upload'
    editor.create()
  }
  editor.txt.html(content)
}, 0)
}
export default {
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      colWidth: 150,
      tableData: [],
      pageNum: 1,
      dialogVisible: false,
      search: {},
      form: {},
      total: 0,
      typeData: [],
      richTextDialogVisible: false,
      richVideoDialogVisible:false,
      playerOptions: {
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        autoplay: true, //如果true,浏览器准备好时开始回放。
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
  // 页面加载的时候，做一些事情，在created里面
  mounted() {
    // 加载游戏分类
    this.loadType();
    this.load()
  },
  // 定义一些页面上控件出发的事件调用的方法
  methods: {
    operationSpace() {
      let width = 0
      let _this = this
      setTimeout(() => {
        let rows = document.getElementsByClassName('el-table__row');
        if (rows && rows.length) {
          let tds = rows[0].getElementsByTagName('td')
          let btns = tds[tds.length - 1].getElementsByTagName('button')
          for (let i = 0; i <btns.length; i++) {
            width += btns[i].offsetWidth + 5
          }
          _this.colWidth = width
        }
      }, 0)
    },
    // 分页加载表格数据
    load() {
      if(this.user.role === 1){
        request.post(baseUrl + "page?pageNum=" + this.pageNum, this.search).then(res => {
          if (res.code === '0') {
            this.tableData = res.data.list;
            this.total = res.data.total;
            this.operationSpace();
          } else {
            this.$notify.error(res.msg);
          }
        });
      } else if(this.user.role===3){
        request.post(baseUrl + "page1?pageNum=" + this.pageNum+"&userId=" + this.user.id, this.search).then(res => {
          if (res.code === '0') {
            this.tableData = res.data.list;
            this.total = res.data.total;
            this.operationSpace();
          } else {
            this.$notify.error(res.msg);
          }
        });
      }
    },
    // 新增初始化对话框
    add() {
      this.form = {};
      this.form.businessId = this.user.id
      initWangEditor('');
      this.$nextTick(() => {
        this.$refs.jdtp.clearFiles()
      });
      this.dialogVisible = true;
    },
    // 编辑初始化模态框
    edit(row) {
      this.form = JSON.parse(JSON.stringify(row));
      initWangEditor(row.description);
      this.$nextTick(() => {
        this.$refs.jdtp.clearFiles()
      });
      this.dialogVisible = true;
    },
    initEditorView(o) {
      this.form = JSON.parse(JSON.stringify(o));
      this.richTextDialogVisible = true;
    },
    initEditorView1(o) {
      this.form = JSON.parse(JSON.stringify(o));
      this.richVideoDialogVisible = true;
      this.playerOptions['sources'][0]['src']=this.form.video
    },
    save() {
      this.form.description = editor.txt.html();

      if (!this.form.id) {
        // 没有ID，说明是新增操作
        request.post(baseUrl, this.form).then(res => {
          if (res.code === '0') {
            this.$notify.success("添加成功");
            this.dialogVisible = false;
            this.load();
          } else {
            this.$notify.error(res.msg);
          }
        })
      } else {
        // 有ID，说明是更新操作
        request.put(baseUrl, this.form).then(res => {
          if (res.code === '0') {
            this.$notify.success('更新成功');
            this.dialogVisible = false;
            this.load();
          } else {
            this.$notify.error(res.msg);
          }
        })
      }
    },
    // 根据ID删除某条数据
    del(id) {
      request.delete(baseUrl + id).then(res => {
        if (res.code === '0') {
          this.$notify.success('删除成功');
          this.load();
        } else {
          this.$notify.error(res.msg);
        }
      });
    },
    jdtpSuccessUpload(res) {
      this.form.img = res.data;
    },
    jdtpSuccessUpload1(res) {
      this.form.video = res.data;
    },
    jdtpSuccessUpload2(res) {
      this.form.goodsLink = res.data;
    },
    // 加载游戏分类
    loadType() {
      request.get("/type").then(res => {
        if (res.code === '0') {
          console.log(res);
          this.typeData = res.data;
        } else {
          this.$notify.error(res.msg);
        }
      })
    },

    handleCurrentChange(pageNum) {
      this.pageNum = pageNum;
      this.load();
    },
  }
}
</script>
