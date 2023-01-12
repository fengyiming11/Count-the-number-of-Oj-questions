<template>
  <div class="login-wrap">
    <el-form class="login-container">
      <h1 class="title">用户登录</h1>
      <el-form-item label="账号" style="left: 50%;">
        <el-input type="text" v-model="uname" placeholder="登录账号" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input type="password" v-model="password" placeholder="登录密码" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" style="width: 100%;" @click="dosubmit()">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'Login',
  data: function() {
    return {
      uname: '',
      password: ''
    }
  },
  methods: {
    dosubmit: function() {
      if(this.uname === ''||this.password === '') {
        const h = this.$createElement;
        this.$notify({
          title: '通知',
          message: h('i', { style: 'color: teal'}, '账号或密码不能为空')
        });
      }
      else
      {
        let param = {
          uname:this.uname,
          password:this.password
        }
        axios({
          method:'post',
          url: '/api/login',
          data: param,
        }).then(
            data=>{
              if(data.data=='ok') {
                let ses = window.sessionStorage;
                ses.setItem('token',this.uname);
                axios.get('/api/User/'+sessionStorage.getItem("token")).then(
                    res=>{
                      ses.setItem('Realname',res.data.name);
                      this.$router.push({path:'/home'});
                    }
                )
              }
              else {
                const h = this.$createElement;
                this.$notify({
                  title: '通知',
                  message: h('i', { style: 'color: teal'}, '账号或密码错误')
                });
              }
            }
        )
      }
    }
  }
}
</script>

<style scoped>
.login-wrap {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  padding-top: 10%;
  left: 50%;
  background: var(--background-image) no-repeat center center fixed;
  background-position: center right;
  background-size: 100%;
}

.login-container {
  border-radius: 10px;
  margin: 0px auto;
  width: 350px;
  padding: 30px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  text-align: left;
  box-shadow: 0 0 20px 2px rgba(0, 0, 0, 0.1);
}

.title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
</style>
