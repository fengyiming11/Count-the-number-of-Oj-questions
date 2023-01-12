<template>
  <div id="app" class="inx">
    <router-view></router-view>
  </div>
</template>



<script>
export default {
  name: 'App',
  components: {
  },
}
</script>

<style>

.inx{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  height: 100vh;
}
body {
  margin: 0;
  padding: 0;
  width: 1920px;
  min-width: 1440px;
  max-width: 100%;
  min-height: 730px;
  max-height: 100%;
  background: var(--background-image) no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  position: relative;
}
</style>
