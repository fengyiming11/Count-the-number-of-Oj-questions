<template>
  <div>
    <div class="allslove" v-loading="loading1">
      <echart id="slovedata"></echart>
    </div>
    <div class="bangdan">
      <el-table
          :data="tableData"
          height="100%"
          width="100%"
          v-loading="loading"
          :default-sort = "{prop: 'slovesum', order: 'descending'}">
        <el-table-column
            type="index"
            align="center">
        </el-table-column>
        <el-table-column
            property="ID"
            label="学号"
            align="center">
        </el-table-column>
        <el-table-column
            property="name"
            label="姓名"
            align="center">
        </el-table-column>
        <el-table-column
            property="slovesum"
            label="今日过题数">
        </el-table-column>
      </el-table>
    </div>
    <div class="alloj">
      <div class="allojdiv">
        <span>
        <a href="https://codeforces.com/" class="link">
          <img src="../assets/img/CodeForces.png" class="img"/>CodeForces
        </a>
      </span>
        <span>
        <a href="https://www.acwing.com/" class="link">
          <img src="../assets/img/Acwing.png" class="img"/>AcWing
        </a>
      </span>
        <span>
        <a href="https://atcoder.jp/company" class="link">
          <img src="../assets/img/atcoder.png" class="img"/>Atcoder
        </a>
      </span>
        <span>
        <a href="https://acm.hdu.edu.cn/" class="link">
          <img src="../assets/img/HDU.png" class="img"/>HDU
        </a>
      </span>
        <span>
        <a href="http://poj.org/" class="link">
          <img src="../assets/img/poj.png" class="img"/>POJ
        </a>
      </span>
        <span>
        <a href="https://www.jisuanke.com/problems" class="link">
          <img src="../assets/img/jisuanke.png" class="img"/>计蒜客
        </a>
      </span>
        <span>
        <a href="https://www.luogu.com.cn/" class="link">
          <img src="../assets/img/luogu.png" class="img"/>洛谷
        </a>
      </span>
        <span>
        <a href="https://vjudge.net/" class="link">
          <img src="../assets/img/Vj.jpg" class="img"/>VJudge
        </a>
      </span>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import {config1,config2} from '../JS/config.js'
import {date} from "mockjs/src/mock/random/date";
import axios from "axios";

export default {
  data() {
    return {
      tableData:[],
      loading:true,
      loading1:true,
      grade:[],
      gradeslove:[]
    }
  },
  name: "page",
  mounted() {
    var chartDom = document.getElementById('slovedata');
    var myChart = echarts.init(chartDom);
    var option;
    axios.get('/api/slovedata').then(
        data=> {
          for (var key in data.data) {
            this.grade.push(key);
            this.gradeslove.push(data.data[key]);
          }
          option = {
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'shadow'
              }
            },
            legend: {},
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis: {
              type: 'value',
              boundaryGap: [0, 0.01]
            },
            yAxis: {
              type: 'category',
              data: this.grade,
            },
            series: [
              {
                type: 'bar',
                data: this.gradeslove,
                showBackground: true,
                itemStyle: {
                  color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                    { offset: 0, color: '#83bff6' },
                    { offset: 0.5, color: '#188df0' },
                    { offset: 1, color: '#188df0' }
                  ])
                },
                emphasis: {
                  itemStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [
                      { offset: 0, color: '#2378f7' },
                      { offset: 0.7, color: '#2378f7' },
                      { offset: 1, color: '#83bff6' }
                    ])
                  }
                }
              }
            ]
          };
          option && myChart.setOption(option);
          this.loading1=false;
        }
    );

    axios.get('/api/slovesum/all').then(
        date=>{
          var data = date.data;
          for (var i = 0; i < data.length; i++) {
            this.tableData.push({
              ID:data[i].ID,
              name:data[i].name,
              slovesum:data[i].nowslovesum-data[i].slovesum,
            });
          }
          this.loading=false;
        }
    );
  }
}
</script>

<style scoped>
.allslove {
  float: left;
  margin-top: 2%;
  margin-left: 2%;
  height: 250px;
  width: 600px;
  color: var(--theme-text-color);
  border:solid 3px lightblue;
  border-radius: 10px;
}

.bangdan {
  margin-top: 2%;
  margin-right: 2%;
  float: right;
  height: 543px;
  width: 500px;
  border: solid 3px lightblue;
  border-radius: 10px;
  align-items: center;
}

.alloj{
  position: absolute;
  height: 250px;
  width: 600px;
  left: 45px;
  top: 50%;
  border:solid 3px lightblue;
  border-radius: 10px;
}
.allojdiv{
  margin-top: 10%;
  margin-left: 5%;
  display: grid;
  grid-template-columns: repeat(4,1fr);
  grid-template-rows: repeat(4,1fr);
  grid-template-areas:
            "a1 a2 a3 a4"
            "a5 a6 a7 a8";
  row-gap: 50px;
  column-gap: 10px;
}
.img{
  height: 25px;
  width: 25px;
  margin-bottom: -5%;
  margin-right: 4%;
}
.link{
  text-decoration:none;
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
