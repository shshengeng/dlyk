<template>
  <el-button type="primary">添加用户</el-button>
  <el-button type="danger">批量删除 </el-button>

  <el-table
      :data="userList"
      style="width: 100%"
      @selection-change="handleSelectionChange"
  >
    <el-table-column type="selection" width="55" />

    <el-table-column type="index" label="序号" width="120" />

    <el-table-column property="loginAct" label="账号" width="120" />

    <el-table-column property="name" label="姓名" show-overflow-tooltip width="120" />

    <el-table-column property="phone" label="手机" show-overflow-tooltip width="160" />

    <el-table-column property="email" label="邮箱" show-overflow-tooltip  />

    <el-table-column property="createTime" label="创建时间" show-overflow-tooltip />

    <el-table-column label="操作" show-overflow-tooltip >
      <template #default="scope">
        <el-button type="primary" @click="view(1)">详情</el-button>
        <el-button type="success" @click="edit()">编辑</el-button>
        <el-button type="danger" @click="del()">删除</el-button>
      </template>
    </el-table-column>/


  </el-table>

  <el-pagination
      background layout="prev, pager, next"
      :total="total"
      :page-size="pageSize"
      @prev-click="toPage"
      @next-click="toPage"
      @current-change="toPage"
  />
</template>


<script>
  import {doGet} from "../http/httpRequest.js";

  export default {
    name: "UserView",

    data(){
      return {
        userList: [{}],
        current: 1,
        pageSize: 0,
        total: 0
      }
    },

    created() {
      this.getData(this.current);
    },

    methods: {
      handleSelectionChange(selection){},

      //获取用户的数据
      getData(current){
        doGet('/api/users', {current: current}).then(res=>{
          console.log(res);
          if(res.data.code === 200){
            this.userList = res.data.data.list
            this.pageSize = res.data.data.pageSize
            this.total = res.data.data.total

          }
        })
      },

      //这个currentPage是组件传过来，就是当前页号
      toPage(currentPage){
        this.getData(currentPage)
      },

      view(id){
        //跳转到/dashboard/user/1
        this.$router.push('/dashboard/user/' + id)
      }
    }
  }
</script>

<style scoped>
.el-table{
  margin-top: 20px;
}
.el-pagination{
  margin-top: 20px;
}
</style>