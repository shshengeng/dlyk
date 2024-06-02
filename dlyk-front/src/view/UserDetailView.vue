<template>
  <h1>sdsdsd</h1>
  {{userDetail.id}}
  {{userDetail.loginAct}}
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
    }
  }
}
</script>


<style scoped>

</style>