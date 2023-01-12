<template>
  <div class="table">
    <el-table :data="tableData" v-loading="loading" width="100%" height="100%" :default-sort = "{prop: 'date', order: 'ascending'}">
      <el-table-column prop="ID"  sortable label="学号"  width="180vmin" align="center"> </el-table-column>
      <el-table-column prop="name" label="姓名" width="180vmin" align="center"> </el-table-column>
      <el-table-column prop="grade" sortable label="年级" width="180vmin" align="center"> </el-table-column>
      <el-table-column prop="todayslovesum" sortable label="今日过题数" align="center"> </el-table-column>
      <el-table-column prop="Rating" sortable label="CodeforcesRating" align="center"> </el-table-column>
      <el-table-column prop="slovesum" sortable label="总过题数" align="center"> </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      tableData:[],
      loading: true
    }
  },
  mounted() {
    axios.get('/api/alldata').then(
        date=>{
          var data = date.data;
          for (var i = 0; i < data.length; i++) {
            this.tableData.push({
              ID:data[i].ID,
              name:data[i].name,
              grade:data[i].grade,
              todayslovesum:data[i].nowslovesum-data[i].slovesum,
              slovesum:data[i].nowslovesum,
              Rating:data[i].Nowrating
            });
          }
          this.loading=false;
        }
    );
  },
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
