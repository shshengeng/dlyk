<template>
  <el-container>
    <!--left side-->
    <el-aside :width="isCollapse ? '64px' : '200px'">
      <div class="menuTitle">@动力云客管理系统</div>
      <el-menu
          active-text-color="#ffd04b"
          background-color="#334157"
          class="el-menu-vertical-demo"
          default-active="2"
          text-color="#fff"
          :unique-opened="true"
          :collapse="isCollapse"
          :collapse-transition="false"
          :router="true"
          style="border-right: solid 0px">

        <!--市场活动菜单-->
        <el-sub-menu index="1">
          <template #title>
            <el-icon><office-building /></el-icon>
            <span>市场活动</span>
          </template>
            <el-menu-item index="1-1">市场活动</el-menu-item>
        </el-sub-menu>

        <!--线索管理菜单-->
        <el-sub-menu index="2">
          <template #title>
            <el-icon><Magnet /></el-icon>
            <span>线索管理</span>
          </template>
          <el-menu-item index="2-1">线索管理</el-menu-item>
        </el-sub-menu>

        <!--客户管理菜单-->
        <el-sub-menu index="3">
          <template #title>
            <el-icon><Avatar /></el-icon>
            <span>客户管理</span>
          </template>
          <el-menu-item index="3-1">客户管理</el-menu-item>
          <el-menu-item index="3-2">客户管理</el-menu-item>
        </el-sub-menu>

        <!--交易管理菜单-->
        <el-sub-menu index="4">
          <template #title>
            <el-icon><Wallet /></el-icon>
            <span>交易管理</span>
          </template>
          <el-menu-item index="4-1">交易管理</el-menu-item>
          <el-menu-item index="4-2">交易管理</el-menu-item>
        </el-sub-menu>

        <!--产品管理菜单-->
        <el-sub-menu index="5">
          <template #title>
            <el-icon><Goods /></el-icon>
            <span>产品管理</span>
          </template>
          <el-menu-item index="5-1">产品管理</el-menu-item>
          <el-menu-item index="5-2">产品管理</el-menu-item>
        </el-sub-menu>

        <!--字典管理菜单-->
        <el-sub-menu index="6">
          <template #title>
            <el-icon><Grid /></el-icon>
            <span>字典管理</span>
          </template>
          <el-menu-item index="6-1">字典管理</el-menu-item>
          <el-menu-item index="6-2">字典管理</el-menu-item>
        </el-sub-menu>

        <!--用户管理菜单-->
        <el-sub-menu index="7">
          <template #title>
            <el-icon><User /></el-icon>
            <span>用户管理</span>
          </template>
          <el-menu-item index="/dashboard/user">
            <el-icon><User /></el-icon>
            用户管理
          </el-menu-item>
        </el-sub-menu>

        <!--系统管理菜单-->
        <el-sub-menu index="8">
          <template #title>
            <el-icon><Setting /></el-icon>
            <span>系统管理</span>
          </template>
          <el-menu-item index="8-1">系统管理</el-menu-item>
          <el-menu-item index="8-2">系统管理</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-aside>

    <!--right side -->
    <el-container class="right-side">
      <!--right side top-->
      <el-header>
        <el-icon class="fold" @click="showMenu"><Fold /></el-icon>

        <el-dropdown :hide-on-click="false">
          <span class="el-dropdown-link">
            {{user.name}}<el-icon class="el-icon--right"><arrow-down /></el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>我的资料</el-dropdown-item>
              <el-dropdown-item>修改密码</el-dropdown-item>
              <el-dropdown-item divided @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </el-header>

      <!--right side mid-->
      <el-main>
        <router-view></router-view>
      </el-main>

      <!--right side bot-->
      <el-footer>@Copyright 2024-2024</el-footer>
    </el-container>
  </el-container>
</template>



<script>
import {Fold, OfficeBuilding, Operation, User} from "@element-plus/icons-vue";
import {doGet} from "../http/httpRequest.js";
import {messageTip, removeStorageToken} from "../util/message.js";

export default {
  name: "DashboardView",

  components: {Fold, User, Operation, OfficeBuilding},

  data(){
    return{
      isCollapse: false,
      user: {}
    }
  },

  mounted() {
    this.loadLoginUser()
  },

  methods: {
    showMenu(){
      this.isCollapse = !this.isCollapse;
    },

    loadLoginUser(){
      doGet('/api/login/info', {}).then((resp) => {
        console.log(resp);
        this.user = resp.data.data;
      })
    },

    logout(){
      doGet('/api/logout', {userId : this.user.id}).then((resp) => {
        removeStorageToken()
        messageTip('退出成功', 'success')
        setTimeout(()=>{
          window.location.href = '/'
        }, 300)

      })
    }
  }
}
</script>


<style scoped>
.el-aside{
  background: #1a1a1a;
}

.el-header{
  background: azure;
  height: 35px;
  line-height: 35px;
}

.el-footer{
  background: aliceblue;
  height: 35px;
  line-height: 35px;
  text-align: center;
}

.right-side{
  height: calc(100vh);
}

.menuTitle{
  height: 35px;
  line-height: 35px;
  text-align: center;
  color: white;
}

.fold{
  cursor: pointer;
}

.el-dropdown{
  float: right;
  line-height: 35px;
}
</style>