<template>
  <div>
    <el-select v-model="value" popper-class="select" filterable placeholder="请选择" @change="change(value)">
      <el-option v-for="item in contestList" :key="item.key" :lable="item.label" :value="item.value">
    </el-option>
    </el-select>
    <el-table :data="tableData" :popper-append-to-body="false" align='center' v-loading="loading" height="90%" width="100%" :default-sort = "{prop: 'Ranking', order: 'ascending'}">
      <el-table-column
          prop="ID"
          label="学号"
          align='center'
          class="column">
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          align='center'
          class="column">
      </el-table-column>
      <el-table-column
          prop="CodeforcesID"
          label="CodeforcesID"
          align='center'
          class="column">
      </el-table-column>
      <el-table-column
          prop="Ranking"
          label="Ranking"
          align='center'
          class="column">
      </el-table-column>
      <el-table-column
          prop="Ratingchange"
          label="分数变化"
          align='center'
          class="column">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";
import {date} from "mockjs/src/mock/random/date";

export default {
  data(){
    return{
      contestList:[],
      tableData:[],
      value:null,
      loading:false
    }
  },
  mounted(){
    axios.get("api/Codeforces/contestlist").then(
        date=> {
          let i;
          for ( i = 0; i < date.data.length; i++) {
            this.contestList.push({
              value:date.data[i],
              label:date.data[i]
            });
          }
        }
    );
  },
  methods:{
    change(value){
      this.loading=true;
      var x = this.value.replace(/ /g,'空');
      x = x.replace('#','井');
      this.value=value;
      axios.get("api/Codeforces/data/"+x).then(
          date=> {
            this.tableData=[];
            let i;
            for ( i = 0; i < date.data.length; i++) {
              this.tableData.push({
                ID:date.data[i].ID,
                name:date.data[i].name,
                CodeforcesID:date.data[i].handle,
                Ranking:parseInt(date.data[i].rank),
                Ratingchange:date.data[i].oldRating+'->'+date.data[i].newRating,
              });
            }
            this.loading=false;
          }
      );
    }
  }
}
</script>

<style scoped>
.column{
  width: 20%;
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
/deep/ .el-input__inner {
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
</style>
<style>
.select{
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
/deep/ .el-select-dropdown__item.hover,
/deep/ .el-select-dropdown__item:hover{
  background-color: var(--theme-hover-color);
}
</style>
