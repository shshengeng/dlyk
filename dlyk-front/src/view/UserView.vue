<template>
  <el-button type="primary" @click="addUser">添加用户</el-button>
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
        <el-button type="primary" @click="view(scope.row.id)">详情</el-button>
        <el-button type="success" @click="edit(scope.row.id)">编辑</el-button>
        <el-button type="danger" @click="del(scope.row.id)">删除</el-button>
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

  <!--用户对话框-->
  <el-dialog v-model="userDialogVisible" title="新增用户" width="50%" center draggable>

    <el-form ref="userRefForm" :model="userQuery" label-width="120px" :rules="userRules ">
      <el-form-item label="账号" prop="loginAct">
        <el-input v-model="userQuery.loginAct"/>
      </el-form-item>

      <el-form-item label="密码" prop="loginPwd">
        <el-input type="password" v-model="userQuery.loginPwd"/>
      </el-form-item>

      <el-form-item label="姓名" prop="name">
        <el-input v-model="userQuery.name"/>
      </el-form-item>

      <el-form-item label="手机" prop="phone">
        <el-input v-model="userQuery.phone"/>
      </el-form-item>

      <el-form-item label="邮箱" prop="email">
        <el-input v-model="userQuery.email"/>
      </el-form-item>

      <el-form-item label="账号未过期" prop="accountNoExpired">
        <el-select v-model="userQuery.accountNoExpired" placeholder="请选择" style="width: 100%">
          <el-option label="是" value="1"/>
          <el-option label="否" value="0"/>
        </el-select>
      </el-form-item>

      <el-form-item label="密码未过期" prop="credentialsNoExpired">
        <el-select v-model="userQuery.credentialsNoExpired" placeholder="请选择" style="width: 100%">
          <el-option label="是" value="1"/>
          <el-option label="否" value="0"/>
        </el-select>
      </el-form-item>

      <el-form-item label="账号未锁定" prop="accountNoLocked">
        <el-select v-model="userQuery.accountNoLocked" placeholder="请选择" style="width: 100%">
          <el-option label="是" value="1"/>
          <el-option label="否" value="0"/>
        </el-select>
      </el-form-item>

      <el-form-item label="账号是否启用" prop="accountEnabled">
        <el-select v-model="userQuery.accountEnabled" placeholder="请选择" style="width: 100%">
          <el-option label="是" value="1"/>
          <el-option label="否" value="0"/>
        </el-select>
      </el-form-item>
    </el-form>

    <template #footer>
      <div class="dialog-footer">
        <el-button @click="this.userDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="userSubmit">添加</el-button>
      </div>
    </template>
  </el-dialog>
</template>


<script>
import {doGet, doPost} from "../http/httpRequest.js";
import {messageTip} from "../util/message.js";

  export default {
    name: "UserView",

    data(){
      return {
        userList: [{}],
        current: 1,
        pageSize: 0,
        total: 0,
        userDialogVisible: false,
        userQuery: {},
        userRules: {
          loginAct: [
            {required: true, message: '请输入登录账号', trigger: 'blur'}
          ],
          loginPwd: [
            {required: true, message: '请输入登录密码', trigger: 'blur'},
            {min: 6, max: 16, message: '登录密码长度为6-16位'}
          ],
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'},
            {pattern:'[\u4E00-\u9FFF]', message: '姓名必须为中文', trigger:'blur'}
          ],
          phone: [
            {required: true, message: '请输入手机号', trigger: 'blur'},
            {pattern:'^(?:\\+?86)?1[3-9]\\d{9}$', message: '手机号或者格式有误', trigger:'blur'}
          ],
          email: [
            {required: true, message: '请输入邮箱', trigger: 'blur'},
            {pattern:'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$', message: '邮箱格式有误', trigger:'blur'}
          ],
          accountNoExpired: [
            {required: true, message: '请选中账号是否过期', trigger: 'blur'},
          ],
          credentialsNoExpired: [
            {required: true, message: '请选择密码是否过期', trigger: 'blur'},
          ],
          accountNoLocked: [
            {required: true, message: '请选择账号是否锁定', trigger: 'blur'},
          ],
          accountEnabled: [
            {required: true, message: '请选择账号是否启用', trigger: 'blur'},
          ]
        }
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
      },

      //新增用户
      addUser(){
        this.userDialogVisible = true;
      },

      //新增用户提交
      userSubmit(){
        let formData = new FormData();
        for(let field in this.userQuery){
          formData.append(field, this.userQuery[field]);
        }

        this.$refs.userRefForm.validate(valid => {
          if(valid){
            doPost('/api/user', formData).then(res=>{
              if(res.data.code === 200){
                messageTip('添加成功', 'success')
              }else {
                messageTip('添加失败', 'error')
              }
            })
          }
        })

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