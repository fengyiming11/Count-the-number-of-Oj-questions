<template>
  <el-calendar class="bg">
    <template
        slot="dateCell"
        slot-scope="{date, data}">
      <div>
        <p>
          {{ data.day.split('-').slice(1).join('-') }}
          <el-button v-show="checkin(date)" class="calendarbutton" type="success" icon="el-icon-check" @click="success()" circle></el-button>
          <el-button v-show="checkout(date)" class="calendarbutton" type="danger" icon="el-icon-close" @click="danger()" circle></el-button>
        </p>
      </div>
    </template>
  </el-calendar>
</template>
<script>
import axios from "axios";

var mp = new Map();
axios.get('/api/get/history/'+window.sessionStorage.getItem('token')).then(
    res=>{
      for(let i=0; i<res.data.length; i++){
        var x=res.data[i].time.replace(/-/g, '/');
        var x=new Date(new Date(x).getTime()-24*60*60*1000);
        if(res.data[i].todayslovesum>=3) mp.set(new Date(x).toString(),1);
        else mp.set(new Date(x).toString(),0);
      }
    }
)

export default {
  name: "CompletionStatistics",
  data(){
    return{
      tableData:[],
      loading: true,
      show:1
    }
  },
  created() {
  },
  mounted() {
    this.loading=false;
  },
  methods:{
    checkin(data){
      data.setHours(0);
      if(mp.has(data.toString()))
      {
        if(mp.get(data.toString())===1) return true;
        else return false;
      }
      return false;
    },
    checkout(data){
      if(mp.has(data.toString()))
      {
        if(mp.get(data.toString())===0) return true;
        else return false;
      }
      return false;
    },
    success(){
      this.$message({
        message: '当天任务已完成!',
        type: 'success'
      });
    },
    danger(){
      const Url = require("../assets/ma.jpg");
      this.$alert('<img src="'+Url+'" width="100%" height="100%"/><p>缴纳罚款时请备注姓名+未完成任务的时间</p>', '缴纳罚款', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        center: true,
        dangerouslyUseHTMLString: true
      });
    }
  }
}
</script>

<style scoped>
.bg{
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
.calendarbutton{
  margin-top: -10%;
  margin-left: 70%;
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
