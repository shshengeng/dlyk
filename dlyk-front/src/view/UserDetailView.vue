<template>
  <el-form ref="loginRefForm" :model="userDetail" label-width="120px">
    <el-form-item label="ID">
      <div class="detail">&nbsp;{{userDetail.id}}</div>
    </el-form-item>

    <el-form-item label="账号">
      <div class="detail">&nbsp;{{userDetail.loginAct}}</div>
    </el-form-item>

    <el-form-item label="密码">
      <div class="detail">&nbsp;******</div>
    </el-form-item>

    <el-form-item label="姓名">
      <div class="detail">&nbsp;{{userDetail.name}}</div>
    </el-form-item>

    <el-form-item label="手机">
      <div class="detail">&nbsp;{{userDetail.phone}}</div>
    </el-form-item>

    <el-form-item label="邮箱">
      <div class="detail">&nbsp;{{userDetail.email}}</div>
    </el-form-item>

    <el-form-item label="账号未过期">
      <div class="detail">&nbsp;{{userDetail.accountNoExpired == 1 ? "是" : "否"}}</div>
    </el-form-item>

    <el-form-item label="密码未过期">
      <div class="detail">&nbsp;{{userDetail.credentialsNoExpired == 1 ? "是" : "否"}}</div>
    </el-form-item>

    <el-form-item label="账号未锁定">
      <div class="detail">&nbsp;{{userDetail.accountNoLocked == 1 ? "是" : "否"}}</div>
    </el-form-item>

    <el-form-item label="账号是否启用">
      <div class="detail">&nbsp;{{userDetail.accountEnabled == 1 ? "是" : "否"}}</div>
    </el-form-item>

    <el-form-item label="创建时间">
      <div class="detail">&nbsp;{{userDetail.createTime}}</div>
    </el-form-item>

    <el-form-item label="创建人">
      <div class="detail">&nbsp;{{userDetail.createBy}}</div>
    </el-form-item>

    <el-form-item label="编辑时间">
      <div class="detail">&nbsp;{{userDetail.editTime}}</div>
    </el-form-item>

    <el-form-item label="编辑人">
      <div class="detail">&nbsp;{{userDetail.editBy}}</div>
    </el-form-item>

    <el-form-item label="上次登录时间">
      <div class="detail">&nbsp;{{userDetail.lastLoginTime}}</div>
    </el-form-item>

    <el-form-item >
      <el-button type="success" @click="goBack">返回</el-button>
    </el-form-item>
  </el-form>
</template>



<script>
import {doGet} from "../http/httpRequest.js";

export default {
  name: "UserDetail",


  data(){
    return {
      //用户详情对象，初始值为null
      userDetail: {}
    }
  },

  mounted() {
    this.loadUserDetail()
  },

  methods: {
    loadUserDetail(){
      let id = this.$route.params.id;
      doGet('/api/user/' + id, {}).then(res => {
        if(res.data.code == 200){
          this.userDetail = res.data.data
        }
      })
    },

    goBack(){
      this.$router.go(-1)
    }
  }
}
</script>


<style scoped>
.detail{
  width: 100%;
  background-color: #F0FFFF;
  padding-left: 15px;
}
</style>