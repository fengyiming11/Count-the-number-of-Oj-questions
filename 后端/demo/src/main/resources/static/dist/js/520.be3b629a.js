"use strict";(self["webpackChunkmenu"]=self["webpackChunkmenu"]||[]).push([[520],{4520:function(a,e,t){t.r(e),t.d(e,{default:function(){return d}});var l=function(){var a=this,e=a._self._c;return e("div",{staticClass:"table"},[e("el-table",{directives:[{name:"loading",rawName:"v-loading",value:a.loading,expression:"loading"}],attrs:{data:a.tableData,width:"100%",height:"100%","default-sort":{prop:"date",order:"ascending"}}},[e("el-table-column",{attrs:{prop:"ID",sortable:"",label:"学号",width:"180vmin",align:"center"}}),e("el-table-column",{attrs:{prop:"name",label:"姓名",width:"180vmin",align:"center"}}),e("el-table-column",{attrs:{prop:"grade",sortable:"",label:"年级",width:"180vmin",align:"center"}}),e("el-table-column",{attrs:{prop:"todayslovesum",sortable:"",label:"今日过题数",align:"center"}}),e("el-table-column",{attrs:{prop:"Rating",sortable:"",label:"CodeforcesRating",align:"center"}}),e("el-table-column",{attrs:{prop:"slovesum",sortable:"",label:"总过题数",align:"center"}})],1)],1)},n=[],o=(t(7658),t(4311)),r={data(){return{tableData:[],loading:!0}},mounted(){o.Z.get("/api/alldata").then((a=>{console.log(a.data);for(var e=a.data,t=0;t<e.length;t++)this.tableData.push({ID:e[t].ID,name:e[t].name,grade:e[t].grade,todayslovesum:e[t].nowslovesum-e[t].slovesum,slovesum:e[t].nowslovesum,Rating:e[t].Nowrating});this.loading=!1}))}},s=r,i=t(1001),u=(0,i.Z)(s,l,n,!1,null,"e4930eaa",null),d=u.exports}}]);
//# sourceMappingURL=520.be3b629a.js.map