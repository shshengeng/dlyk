<template>
  <el-container>
    <el-aside width="200px">
      <img src="../assets/loginBox.svg">
      <p class="imgTitle">
        欢迎使用动力云客系统
      </p>
    </el-aside>

    <el-main>
      <div class="loginTitle">欢迎登录</div>

      <el-form ref="loginRefForm" :model="user" label-width="120px" :rules="loginRules">
        <el-form-item label="账号" prop="loginAct">
          <el-input v-model="user.loginAct"/>
        </el-form-item>

        <el-form-item label="密码" prop="loginPwd">
          <el-input type="password" v-model="user.loginPwd"/>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>


        <el-form-item>
          <el-checkbox label="记住我" name="rememberMe" />
        </el-form-item>
      </el-form>

    </el-main>
  </el-container>
</template>


<script>
  import axios from "axios";
  import { doGet, doPost, doPut,doDelete} from "../http/httpRequest.js";
  import {messageTip} from "../util/message.js";

  export default {
    name: "LoginView",

    data(){
      return{
        user: {
          loginAct: '',
          loginPwd: ''
        },
        //定义登录表单的验证规则
        loginRules: {
          //定义账号的验证规则，可以多个，所以是数组
          loginAct: [
            {required: true, message: '请输入登录账号', trigger: 'blur'}
          ],
          loginPwd: [
            {required: true, message: '请输入登录密码', trigger: 'blur'},
            {min: 6, max: 16, message: '登录密码长度为6-16位'}
          ]
        }
      }
    },
    methods: {
      login(){
        //提交前保证输入框的合法性
        this.$refs.loginRefForm.validate((isValid) => {
          //isValid是验证后的结果，如果为true，表示验证通过，否则验证未通过
          if(isValid){
            //验证通过
            let formData = new FormData();
            formData.append('loginAct', this.user.loginAct)
            formData.append('loginPwd', this.user.loginPwd)

            doPost('/api/login', formData).then((resp) => {
              console.log(resp);
              if(resp.data.code === 200){
                messageTip('Login successful', 'success')
              }else {
                messageTip('Login failed', 'error')
              }
            })
          }
        })
      }
    }
  }
</script>


<style scoped>
.el-aside{
  width: 40%;
  background: #1a1a1a;
  text-align: center;
}
.el-main{
  height: calc(100vh);
}
img {
  height: 400px;
}
.imgTitle{
  color: #f9f9f9;
  font-size: 28px;
}
.el-form{
  width: 60%;
  margin: auto;
}
.loginTitle{
  text-align: center;
  margin-top: 100px;
  margin-bottom: 25px;
  font-weight: bold;
}
.el-button{
  width: 100%;
}
</style>