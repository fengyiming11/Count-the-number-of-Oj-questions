<template>
  <div class="allbox">
    <div class="topbox">
      <div class="Ownmessage" v-loading="loading1">
        <div class="img">
          <img id="avatar" v-bind:src="avatar" class="circleImg"/>
        </div>
        <div class="textarea">
          <span class="text">姓名: {{name}}</span><br>
          <span class="text">学号: {{ID}}</span><br>
          <span class="text">Codeforces Rating: </span>
          <span id="Rating" class="Rating">{{Rating}}</span><br>
          <span class="text">总过题数: {{slovesum}}</span>
        </div>
      </div>
      <div class="Todayslove" v-loading="loading2">
        <p class="Todaytext">今日做题数: {{todayslovesum}}</p>
        <p class="yiyan">{{text}}</p>
      </div>
    </div>
    <p></p>
    <div class="bottombox">
      <div class="Notice" v-loading="loading3">
        <h1 class="Notice-text">公告</h1>
      </div>
      <div class="RecentContest">
        <h1 class="RecentContesttext">近期比赛</h1>
        <el-table :data="tableData" v-loading="loading4" width="100%" height="80%" :default-sort = "{prop: 'date', order: 'ascending'}" @row-click="handleahnshu">
          <el-table-column prop="oj"  label="OJ平台"  width="120vmin" align="center"> </el-table-column>
          <el-table-column prop="name" label="比赛名称" width="180vmin" align="center"> </el-table-column>
          <el-table-column prop="date" label="时间" align="center"> </el-table-column>
          <el-table-column prop="link" v-if="false"> </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {date} from "mockjs/src/mock/random/date";

export default {

  data:function() {
      return {
        avatar: 'https://userpic.codeforces.org/no-avatar.jpg',
        name: null,
        ID: sessionStorage.getItem("token"),
        text:null,
        Rating: null,
        slovesum: null,
        todayslovesum:null,
        tableData: [],
        loading1: true,
        loading2: true,
        loading3: false,
        loading4: true
      }
  },
  mounted() {
    axios.get('/api/User/'+sessionStorage.getItem("token")).then(
        date=>{
          if(date.data.status==='error')
          {
            this.name=date.data.name;
            this.$notify({
              title: '提示',
              message: '数据正在导入请稍后查看',
              duration: 0
            });
            this.loading1=false;
            return;
          }
          this.name=date.data.name;
          this.Rating=date.data.Nowrating;
          this.slovesum=date.data.nowslovesum;
          this.todayslovesum=date.data.nowslovesum-date.data.slovesum;
          this.avatar=date.data.Avatar;
          var e = document.getElementById('Rating');
          if(this.Rating<1200) e.style.color = '#808080';
          else if(this.Rating<1400) e.style.color = '#008000';
          else if(this.Rating<1600) e.style.color = '#03a89e';
          else if(this.Rating<1900) e.style.color = '#0000ff';
          else if(this.Rating<2100) e.style.color = '#a0a';
          else if(this.Rating<2300) e.style.color = '#ff8c00';
          else if(this.Rating<2400) e.style.color = '#ff8c00';
          else if(this.Rating<2600) e.style.color = '#ff0000';
          else if(this.Rating<3000) e.style.color = '#ff0000';
          else e.style.color = '#ff0000';
          this.loading1=false;
        }
    );

    axios.get('https://v1.hitokoto.cn/?c=j').then(
        date=>{
          this.text=date.data.hitokoto;
          this.loading2=false;
        }
    );


    axios.get('http://algcontest.rainng.com/acm').then(
        data=> {
          let i;
          for ( i = 0; i < data.data.length; i++) {
            this.tableData.push({
              oj:data.data[i].oj,
              name:data.data[i].name,
              date:data.data[i].startTime,
              link:data.data[i].link
            });
          }
        }
    );
    axios.get('https://codeforces.com/api/contest.list?gym=false').then(
        data => {
          let i;
          data = data.data.result;
          for ( i = 0; i < data.length; i++) {
            if(data[i].startTimeSeconds<Date.parse( new Date())/1000) break;
            var date = new Date(data[i].startTimeSeconds * 1000);
            let Y,M,D,h,m,s;
            Y = date.getFullYear() + '-';
            M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            D = (date.getDate()+1 < 10 ? '0'+(date.getDate()) : date.getDate()) + ' ';
            h = (date.getHours()+1 < 10 ? '0'+(date.getHours()) : date.getHours()) + ':';
            m = (date.getMinutes()+1 < 10 ? '0'+(date.getMinutes()) : date.getMinutes()) + ':';
            s = (date.getSeconds()+1 < 10 ? '0'+(date.getSeconds()) : date.getSeconds());
            this.tableData.push({
              oj:'Codeforces',
              name:data[i].name,
              date:Y+M+D+h+m+s,
              link:'https://codeforces.com/contests'
            });
          }
          this.loading4=false;
        }
    );

  },
  methods:{
    handleahnshu(row){
      window.open(row.link,'_blank');
    }
  }
}
</script>
<style scoped>
body{
  background: var(--background-image) no-repeat center center fixed;
}
.allbox{
  display: -webkit-flex;
  display: flex;
  -webkit-flex-direction: column;
  flex-direction: column;
  color: var(--theme-text-color);
  /*border: solid 10px palegoldenrod;*/
  overflow: hidden;
}
.topbox{
  box-sizing: content-box;
  margin-top: 2%;
  height: 200px;
  /*border: solid 10px rebeccapurple;*/
}
.bottombox{
  box-sizing: content-box;
  padding: 0;
  margin-bottom: 2%;
  height: 600px;
  overflow: hidden;
  /*border: solid 10px rebeccapurple;*/
}
.Ownmessage {
  float: left;
  margin-left: 4%;
  width: 40%;
  height: 100%;
  border:solid 3px lightblue;
  border-radius: 10px;
  text-align: center;
}
.img{
  margin-top: 8%;
  margin-left: 12%;
  float: left;
}
.circleImg{
  border-radius: 60%;
  width: 120%;
  height: 120%;
  display: inline;
}
.textarea{
  margin-top: 7%;
  margin-right: 15%;
  float: right;
}
.Rating {
  font-size: 15px;
  font-weight: 700;
  font-family: Verdana,Arial,sans-serif;
  display: inline;
}
.text {
  font-size: 14px;
  font-weight: 700;
  font-family: Verdana,Arial,sans-serif;
}


.Todayslove {
  float: right;
  margin-right: 4%;
  width: 40%;
  height: 100%;
  border:solid 3px lightblue;
  border-radius: 10px;
  text-align: center;
}
.Todaytext{
  margin-top: 50px;
  font-size: 6vmin;
  font-family: 华文行楷;
}
.yiyan{
  margin-top: -2%;
  font-size: 2vmin;
}


.Notice{
  float: left;
  margin-top: 2%;
  margin-left: 4%;
  margin-bottom: 2%;
  width: 40%;
  height: 88%;
  border:solid 3px lightblue;
  border-radius: 10px;
}
.Notice-text{
  margin-top: 2%;
  margin-left: 2%;
}

.RecentContest{
  float: right;
  margin-top: 2%;
  margin-right: 4%;
  margin-bottom: 2%;
  width: 40%;
  height: 88%;
  border:solid 3px lightblue;
  border-radius: 10px;
}
.RecentContesttext{
  margin-top: 2%;
  margin-left: 2%;
}
::v-deep .el-table,
::v-deep .el-table__expanded-cell {
  border-color: var(--theme-border-color);
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
::v-deep .el-table th,
::v-deep .el-table tr,
::v-deep .el-table td {
  background-color: var(--bg-color);
  color: var(--theme-text-color);
  border-color: var(--theme-border-color);
}
</style>
