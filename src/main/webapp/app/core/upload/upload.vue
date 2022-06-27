<template>
  <el-dialog :title="title" :visible.sync="upLoadVisible" width="30%" @close="closeDialog">
    <div class="upload-box">
      <el-upload
        ref="upload"
        drag
        class="upload-demo"
        accept=".xls,.xlsx"
        action="#"
        :limit="1"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :file-list="fileList"
        :auto-upload="false"
        :before-upload="beforeUpload"
        :http-request="uploadHttpRequest"
      >
        <i class="el-icon-upload" />
        <div class="el-upload__text">将文件拖到此处，或<em style="color: #409eff">点击上传</em></div>

        <div slot="tip" class="el-upload__tip">只能上传xlsx文件, 文件大小不超过10M</div>
      </el-upload>
      <el-progress
        v-if="processVisible"
        :percentage="processPercent"
        :text-inside="true"
        :stroke-width="24"
        :status="processStatus"
        style="margin: 20px 0"
      />
      <el-alert v-if="tipVisible" :title="tipTitle" type="error" @close="tipVisible = false" />
      <el-button style="margin: 10px 0" icon="el-icon-upload2" size="small" type="primary" :loading="uploading" @click="submitUpload">
        上传到服务器
      </el-button>
      <el-button style="margin-left: 0" icon="el-icon-circle-close" size="small" @click="cancelUpload"> 取 消 </el-button>
    </div>
  </el-dialog>
</template>
<script>
import axios from 'axios';
export default {
  props: {
    title: {
      type: String,
      default: '上传文件',
    },
    upLoadVisible: {
      type: Boolean,
      default: false,
    },
    uploadUrl: {
      // 上传文件的api
      type: String,
      default: '',
    },
  },
  data() {
    return {
      fileList: [],
      uploading: false,
      processPercent: 5,
      processStatus: 'success',
      processVisible: false,
      tipVisible: false,
      tipTitle: '',
      timer: null,
    };
  },
  methods: {
    // 上传文件之前的钩子：判断上传文件格式、大小等，若返回false则停止上传
    beforeUpload(file) {
      // 文件类型
      console.log('文件类型', file.type);
      const isType = file.type === 'application/vnd.ms-excel' || file.type === 'application/wps-office.xlsx';
      const isTypeComputer = file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet';
      const fileType = isType || isTypeComputer;
      if (!fileType) {
        this.$message.error('上传文件只能是xls/xlsx格式！');
      }

      // 文件大小限制为10M
      const fileLimit = file.size / 1024 / 1024 < 10;
      if (!fileLimit) {
        this.$message.error('上传文件大小不超过10M！');
      }
      return fileType && fileLimit;
    },
    // 自定义上传方法，param是默认参数，可以取得file文件信息
    async uploadHttpRequest(param) {
      const that = this;
      // 显示按钮加载中
      this.uploading = true;
      // 显示进度条
      this.processVisible = true;
      this.processPlus();
      // FormData对象，添加参数只能通过append('key', value)的形式添加
      const formData = new FormData();
      // 添加文件对象
      formData.append('file', param.file);

      const config = {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      };

      console.log('上传地址', this.uploadUrl);
      // 这里注意一定要在当前vue 引入axios否则, 一直不调用
      axios
        .post(this.uploadUrl, formData, config)
        .then(res => {
          // 这里的res需根据后台返回情况判断
          console.log('导入后返回', res);
          if (res.status === 200) {
            this.processPercent = 100;
            that.uploading = false;
            this.processVisible = false;
            this.timer = null;
            that.$emit('closeUpload');
            that.$message.success('上传成功');
          } else {
            this.processPercent = 100;
            this.processStatus = 'exception';
            this.tipVisible = true;
            this.tipTitle = res.data.msg;
            this.uploading = false;
          }
        })
        .catch(reject => {
          console.log(reject);
        });
    },
    // 上传至服务器
    submitUpload() {
      this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    cancelUpload() {
      this.$refs.upload.clearFiles(); // 清除上传文件对象
      this.fileList = []; // 清空选择的文件列表
      this.$emit('closeUpload');
    },
    // 前期进度条增加
    processPlus() {
      this.timer = setInterval(() => {
        if (this.processPercent < 90) {
          this.processPercent += 10;
        }
      }, 200);
    },
    closeDialog() {
      this.$emit('closeUploadDialog');
    },
  },
};
// 原文链接：https://blog.csdn.net/cwxxiayi/article/details/117121012
</script>
<style scoped>
.upload-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.upload-demo {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
