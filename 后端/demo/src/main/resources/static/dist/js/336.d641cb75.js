"use strict";(self["webpackChunkmenu"]=self["webpackChunkmenu"]||[]).push([[336],{8248:function(t,e,n){n.d(e,{Z:function(){return p}});var i=function(){var t=this,e=t._self._c;return e("Menu",{staticClass:"Menu"})},a=[],s=function(){var t=this,e=t._self._c;return e("div",{staticClass:"menu-wrapper"},[t._m(0),e("div",{staticClass:"content"},[e("div",{staticClass:"person-info"},[e("img",{attrs:{src:n(2194),alt:""}}),e("div",{staticClass:"person-name"},[e("div",{staticClass:"name"},[t._v(t._s(t.param.name))]),e("span",{staticClass:"detail"},[t._v(t._s(t.param.number))])])]),e("div",{staticClass:"menu-content"},[e("div",{staticClass:"menu-list"},t._l(t.menuData,(function(n){return e("div",{key:n.id,staticClass:"menu-list-item",on:{click:function(e){return t.link(e)}}},[e("div",{staticClass:"block"}),e("span",{staticClass:"iconfont",class:n.iconFont}),e("div",{staticClass:"item-name"},[t._v(t._s(n.menuName))])])})),0)])])])},o=[function(){var t=this,e=t._self._c;return e("div",{staticClass:"menu-title"},[e("span",{staticClass:"iconfont icon-24gf-bag"}),e("span",{staticClass:"title-text"},[t._v("MENU")])])}],r=(n(7658),{data(){return{param:{name:window.sessionStorage.getItem("Realname"),number:window.sessionStorage.getItem("token")},menuData:[{id:1,menuName:"首页",iconFont:"icon-caidan"},{id:2,menuName:"今日刷题",iconFont:"icon-gouwu"},{id:3,menuName:"最近比赛排名",iconFont:"icon-category"},{id:4,menuName:"数据分析",iconFont:"icon-bingtu"},{id:5,menuName:"当前成员",iconFont:"icon-denglu-yonghuzu"},{id:6,menuName:"设置",iconFont:"icon-shezhi"},{id:7,menuName:"登出",iconFont:"icon-jinru"},{id:8,menuName:window.sessionStorage.getItem("theme-color")+" Mode",iconFont:"icon-yueliang"}],themeDark:!1}},created(){"Light"==window.sessionStorage.getItem("theme-color")?(document.body.setAttribute("theme-color","Dark"),this.menuData.at(7).menuName="Light Mode"):(document.body.setAttribute("theme-color","Light"),this.menuData.at(7).menuName="Dark Mode")},methods:{link(t){"首页"==t.currentTarget.innerText?this.$router.push({path:"/home"}):"今日刷题"==t.currentTarget.innerText?this.$router.push({path:"/Today"}):"最近比赛排名"==t.currentTarget.innerText?this.$router.push({path:"/RecentlyRating"}):"数据分析"==t.currentTarget.innerText?this.$router.push({path:"/Rating"}):"当前成员"==t.currentTarget.innerText?this.$router.push({path:"/NowMember"}):"表单"==t.currentTarget.innerText?this.$router.push({path:"/ToSubmit"}):"设置"==t.currentTarget.innerText?this.$router.push({path:"/Setting"}):"登出"==t.currentTarget.innerText?(window.sessionStorage.clear(),this.$router.push({path:"/login"})):"Dark"==window.sessionStorage.getItem("theme-color")?(document.body.setAttribute("theme-color","Dark"),window.sessionStorage.setItem("theme-color","Light"),this.menuData.at(7).menuName="Light Mode"):(document.body.setAttribute("theme-color","Light"),window.sessionStorage.setItem("theme-color","Dark"),this.menuData.at(7).menuName="Dark Mode")}}}),u=r,c=n(1001),m=(0,c.Z)(u,s,o,!1,null,"06205bb9",null),l=m.exports,h={name:"left",components:{Menu:l}},d=h,g=(0,c.Z)(d,i,a,!1,null,"48677be4",null),p=g.exports},7336:function(t,e,n){n.r(e),n.d(e,{default:function(){return m}});var i=function(){var t=this,e=t._self._c;return e("div",{staticClass:"all"},[e("el-container",[e("el-container",[e("el-main",{staticClass:"main"},[e("router-view",{staticClass:"view"})],1)],1),e("el-aside",[e("left")],1)],1)],1)},a=[],s=n(8248),o={name:"index",components:{Left:s.Z}},r=o,u=n(1001),c=(0,u.Z)(r,i,a,!1,null,"48289f5f",null),m=c.exports},2194:function(t,e,n){t.exports=n.p+"img/head_portrait.4e45323e.jpg"}}]);
//# sourceMappingURL=336.d641cb75.js.map