<template>
  <div class="all">
    <el-container>
      <el-container>
        <el-main class="main">
          <router-view class="view"></router-view>
        </el-main>
      </el-container>
      <el-aside>
        <left></left>
      </el-aside>
    </el-container>
  </div>

</template>

<script>
import Left from "@/Layout/left";

export default {
  name: 'index',
  components: {
    Left,
  },
}
</script>

<style lang="scss" scoped>
@import '@/css/Menu.css';
.main {
  position: absolute;
  left: 150px;
  right: 50px;
  top: 29px;
  bottom: 29px;
  border-radius: 20px;
  background-color: var(--bg-color);
  padding: 20px;
  box-sizing: border-box;
  text-align: left;
  transition: 0.6s;
  overflow: hidden;
}
.view{
  margin: 0 0 0 0;
  padding: 0;
  box-sizing: border-box;
  height: 100%;
  //border: solid #5B6DCD 1px;
}
</style>