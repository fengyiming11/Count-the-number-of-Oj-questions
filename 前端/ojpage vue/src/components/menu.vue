<template>
  <div class="menu-wrapper">
    <div class="menu-title">
      <span class="iconfont icon-24gf-bag"></span>
      <span class="title-text">MENU</span>
    </div>
    <div class="content">
      <div class="person-info">
        <img src="../assets/head_portrait.jpg" alt="" />
        <div class="person-name">
          <div class="name">{{param.name}}</div>
          <span class="detail">{{ param.number }}</span>
        </div>
      </div>
      <div class="menu-content">
        <div class="menu-list">
          <div class="menu-list-item" v-for="item in menuData" :key="item.id" @click="link($event)">
            <div class="block"></div>
            <span class="iconfont" :class="item.iconFont"></span>
            <div class="item-name">{{ item.menuName }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      param:{
        name:window.sessionStorage.getItem('Realname'),
        number:window.sessionStorage.getItem('token'),
      },
      menuData: [
        {
          id: 1,
          menuName: "首页",
          iconFont: "icon-caidan",
        },
        {
          id: 2,
          menuName: "今日刷题",
          iconFont: "icon-gouwu",
        },
        {
          id: 3,
          menuName: "最近比赛排名",
          iconFont: "icon-category",
        },
        {
          id: 4,
          menuName: "数据分析",
          iconFont: "icon-bingtu",
        },
        {
          id: 5,
          menuName: "当前成员",
          iconFont: "icon-denglu-yonghuzu",
        },
        {
          id: 6,
          menuName: "每天任务完成情况",
          iconFont: "icon-order-fill",
        },
        {
          id: 7,
          menuName: "个人中心",
          iconFont: "icon-shezhi",
        },
        {
          id: 8,
          menuName: "登出",
          iconFont: "icon-jinru",
        },
        {
          id: 9,
          menuName: window.sessionStorage.getItem("theme-color")+" Mode",
          iconFont: "icon-yueliang",
        },
      ],
      themeDark: false,
    };
  },
  created() {
    if (window.sessionStorage.getItem("theme-color")=="Light") {
      document.body.setAttribute("theme-color", "Dark");
      this.menuData.at(8).menuName="Light Mode";
    } else {
      document.body.setAttribute("theme-color", "Light");
      this.menuData.at(8).menuName="Dark Mode";
    }
  },
  methods: {
    link(e) {
      if(e.currentTarget.innerText=="首页") {
        this.$router.push({ path:'/home'});
      }
      else if(e.currentTarget.innerText=="今日刷题") {
        this.$router.push({ path:'/Today'});
      }
      else if(e.currentTarget.innerText=="最近比赛排名") {
        this.$router.push({ path:'/RecentlyRating'});
      }
      else if(e.currentTarget.innerText=="数据分析") {
        this.$router.push({ path:'/Rating'});
      }
      else if(e.currentTarget.innerText=="当前成员") {
        this.$router.push({ path:'/NowMember'});
      }
      else if(e.currentTarget.innerText=="每天任务完成情况") {
        this.$router.push({ path:'/CompletionStatistics'});
      }
      else if(e.currentTarget.innerText=="个人中心") {
        this.$router.push({ path:'/Setting'});
      }
      else if(e.currentTarget.innerText=="登出") {
        window.sessionStorage.clear();
        this.$router.push({ path:'/login'});
      }
      else
      {
        if (window.sessionStorage.getItem("theme-color")=="Dark") {
          document.body.setAttribute("theme-color", "Dark");
          window.sessionStorage.setItem("theme-color","Light");
          this.menuData.at(8).menuName="Light Mode";
        } else {
          document.body.setAttribute("theme-color", "Light");
          window.sessionStorage.setItem("theme-color","Dark");
          this.menuData.at(8).menuName="Dark Mode";
        }
      }
    }

  },
};
</script>

<style lang="scss" scoped>
@import url(../assets/iconfont/iconfont.css);
@import url(../assets/themecss/theme.scss);
.iconfont {
  font-family: "iconfont" !important;
  font-style: normal;
  font-size: 25px;
  color: var(--theme-text-color);
  vertical-align: middle;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.menu-wrapper {
  width: 90px;
  border-radius: 20px;
  background-color: var(--menu-color);
  padding: 20px;
  box-sizing: border-box;
  text-align: left;
  transition: 0.6s;
  overflow: hidden;
  -webkit-backdrop-filter: blur(8px);
  backdrop-filter: blur(8px);
  .menu-title {
    padding-bottom: 20px;
    box-sizing: border-box;
    border-bottom: 1px solid rgb(229, 233, 236);
    margin-bottom: 20px;
    .title-text {
      margin-left: 10px;
      font-family: "BasketBall";
      font-size: 30px;
      vertical-align: middle;
      opacity: 0;
      transition: 0.6s;
      color: var(--theme-info-text-color);
    }
    .icon-24gf-bag {
      font-size: 30px;
      padding-left: 10px;
      color: rgb(103, 56, 223);
    }
  }
  .content {
    // text-align: center;
    .person-info {
      margin-top: 20px;
      white-space: nowrap;
      img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
        border: 2px solid #fff;
        box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.2);
        vertical-align: middle;
      }
      .person-name {
        margin-left: 15px;
        vertical-align: middle;
        opacity: 0;
        transition: 0.6s;
        overflow: hidden;
        color: var(--theme-info-text-color);
        display: inline-block;
        .name {
          font-size: 20px;
          font-weight: 600;
        }
        .detail {
          font-size: 12px;
        }
      }
    }
    .menu-content {
      margin-top: 20px;
      .menu-list {
        .menu-list-item {
          cursor: pointer;
          width: 100%;
          height: 50px;
          font-size: 18px;
          position: relative;
          border-radius: 10px;
          padding-left: 10px;
          white-space: nowrap;
          .block {
            width: 6px;
            height: 25px;
            background: rgb(101, 57, 225);
            position: absolute;
            right: -10px;
            top: 13px;
            transition: 0.5s;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
            opacity: 0;
          }
          .item-name {
            line-height: 50px;
            display: inline-block;
            margin-left: 10px;
            font-size: 14px;
            color: var(--theme-text-color);
            font-weight: 100;
            transition: 0.6s;
            opacity: 0;
          }
          &:hover {
            background-color: var(--theme-hover-menu-color);
            .item-name {
              color: var(--theme-hover-color);
            }
            .iconfont {
              color: var(--theme-hover-color);
            }
            .block {
              opacity: 1;
            }
          }
        }
      }
    }
  }
  &:hover {
    width: 220px;
    transform: translateX(20%);
    .menu-title {
      .title-text {
        opacity: 1;
      }
    }

    .person-info {
      .person-name {
        opacity: 1;
      }
    }
    .menu-content {
      .menu-list {
        .menu-list-item {
          .item-name {
            opacity: 1;
          }
          .btn {
            opacity: 1;
          }
        }
      }
    }
  }
}
</style>
