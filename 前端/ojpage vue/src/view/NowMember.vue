<template>
  <div>
    <el-table :data="tableData" v-loading="loading" align='center' height="100%" width="100%">
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
          prop="grade"
          label="年级"
          align='center'
          class="column">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>

import axios from "axios";

export default {
  data(){
    return{
      tableData:[],
      loading:true
    }
  },
  mounted() {
    axios.get('/api/User').then(
        data=>{
          this.tableData=[];
          for(var i=0;i<data.data.length;i++)
          {
            this.tableData.push({
              ID:data.data[i].realID,
              name:data.data[i].realname,
              grade:data.data[i].grade
            })
          }
          this.loading=false;
        }
    )
  }
}
</script>

<style scoped>
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
