import Vue from 'vue'
import App from './App.vue'
import router from "@/router";
import ElementUI, {Button, Radio, RadioGroup} from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'
import Echart from './components/Echart/index.vue'
import * as echarts from 'echarts'
import dataV from '@jiaminghi/data-view'
import VueAxios from 'vue-axios'
import axios from "axios";

Vue.use(VueAxios,axios);
Vue.use(dataV);
Vue.config.productionTip = false
Vue.use(ElementUI, {locale})
Vue.component("Echart",Echart)
Vue.prototype.$echarts = echarts;
// element组件
Vue.use(Radio);
Vue.use(Button);
Vue.use(RadioGroup)


router.beforeEach(function(to, from, next) {
  if (to.meta.needLogin) {//页面是否登录
    if (sessionStorage.getItem("token")) {//本地存储中是否有token(uid)数据
      if(to.path==="/login") {
        next({
          path:"/home"
        });
      }
      else next(); //表示已经登录
    } else {//next可以传递一个路由对象作为参数 表示需要跳转到的页面
      if (sessionStorage.getItem("token")) {//本地存储中是否有token(uid)数据
        if (to.path === "/login") {
          next({
            path: "/home"
          });
        } else next(); //表示已经登录
      }
      else next({
        path:"/login"
      });
    }
  } else {//表示不需要登录
    next(); //继续往后走
  }
});

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

