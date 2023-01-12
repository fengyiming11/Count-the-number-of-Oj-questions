<template>
  <div class="fill" v-loading="loading">
    <el-card class="fill">
      <el-descriptions style="color: var(--theme-text-color)" title="个人中心" :column="2" border>
        <el-descriptions-item  labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                               contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-picture-outline"></i>
            头像
          </template>
          <img class="img" :src="avatar" />
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-user"></i>
            学号
          </template>
          {{ ID }}
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-s-custom"></i>
            姓名
          </template>
          {{ name }}
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-odometer"></i>
            总过题数
          </template>
          {{slovesum}}
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-location-outline"></i>
            届别
          </template>
          {{grade}}
        </el-descriptions-item>

        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-s-data"></i>
            届别人数
          </template>
          {{ gradesum }}
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-ship"></i>
            CodeforcesID
          </template>
          {{CodeforcesID}}
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-trophy"></i>
            CodeforcesRating
          </template>
          {{ Rating }}
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-notebook-2"></i>
            各大oj做题数
          </template>
          <el-select width="40%" v-model="value" popper-class="select" filterable placeholder="请选择" @change="change(value)">
            <el-option v-for="item in ojname" :key="item.label" :lable="item.label" :value="item.label">
            </el-option>
          </el-select>
          &ensp;&ensp;&ensp;&ensp;&ensp;做题数为:&ensp;{{ ojslovesum }}
        </el-descriptions-item>
      </el-descriptions>
      <el-descriptions class="margin-top" :column="1" border>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-caret-right"></i>
            今日做题数排名
          </template>
          在同届的&ensp;{{gradesum}}&ensp;人中你排&ensp;{{ todayslovesumrate }}&ensp;名,在所有的&ensp;{{allgradesum}}&ensp;人中总排&ensp;{{alltodayslovesumrate}}&ensp;名。
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-caret-right"></i>
            CodeforcesRating排名
          </template>
          在同届的&ensp;{{gradesum}}&ensp;人中你排&ensp;{{ Codeforcerate }}&ensp;名,在所有的&ensp;{{allgradesum}}&ensp;人中总排&ensp;{{allCodeforcerate}}&ensp;名。
        </el-descriptions-item>
        <el-descriptions-item labelStyle="background: var(--bg-color);color: var(--theme-text-color);"
                              contentStyle="background: var(--bg-color);color: var(--theme-text-color);">
          <template slot="label">
            <i class="el-icon-caret-right"></i>
            总做题数排名
          </template>
          在同届的&ensp;{{gradesum}}&ensp;人中你排&ensp;{{ slovesumrate }}&ensp;名,在所有的&ensp;{{allgradesum}}&ensp;人中总排&ensp;{{allslovesumrate}}&ensp;名。
        </el-descriptions-item>
      </el-descriptions>
      <el-row>
        <div align="center" class="buttondiv">
          <el-button type="primary" @click="updateDialog()" round>修改密码</el-button>
<!--          <el-button type="primary" @click="updateOJ" round>修改OJ</el-button>-->
        </div>
      </el-row>
    </el-card>
    <el-dialog
        title="修改密码"
        :visible.sync="centerDialogVisible"
        width="30%"
        >
      <div class="input-suffix">
        旧密码:<el-input placeholder="请输入旧密码" v-model="oldpassword" show-password></el-input>
      </div>
      <div class="input-suffix">
        新密码:<el-input placeholder="请输入新密码" v-model="newpassword" show-password></el-input>
      </div>
      <div class="input-suffix">
        确认密码:<el-input placeholder="请输入新密码" v-model="confignewpassword" show-password></el-input>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="centerDialogVisible=false">取 消</el-button>
        <el-button type="primary" @click="updatepassword">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data(){
    return{
      ID:sessionStorage.getItem("token"),
      avatar:null,
      name:null,
      Rating:null,
      grade:null,
      slovesum:null,
      CodeforcesID:null,
      todayslovesum:null,
      todayslovesumrate:null,
      Codeforcerate:null,
      slovesumrate:null,
      gradesum:null,
      alltodayslovesumrate:null,
      allCodeforcerate:null,
      allslovesumrate:null,
      allgradesum:null,
      ojname:[],
      value:null,
      ojslovesum:null,
      centerDialogVisible:false,
      oldpassword:"",
      newpassword:"",
      confignewpassword:"",
      loading:true,
    }
  },
  mounted(){
    axios.get('/api/User/'+sessionStorage.getItem("token")).then(
        date=>{
          this.Rating=date.data.Nowrating;
          this.name=date.data.name;
          this.slovesum=date.data.nowslovesum;
          this.avatar=date.data.Avatar;
          this.CodeforcesID=date.data.UserID;
        }
    );
    axios.get('/api/get/ojslove/'+sessionStorage.getItem("token")).then(
        date=>{
          for(var key in date.data){
            if(key==='RealID') continue;
            if(date.data[key]===null) continue;
            this.ojname.push({
              label:key,
              value:date.data[key]
            });
          }
        }
    );
    axios.get('/api/alldata').then(
        date=>{
          var data = date.data;
          let slovesumarr=[];
          let todayslovesum=[];
          let Ratingarr=[];
          let allslovesumarr=[];
          let alltodayslovesum=[];
          let allRatingarr=[];
          for (var i = 0; i < data.length; i++) {
            if(data[i].ID===this.ID)
            {
              this.grade=data[i].grade;
              this.todayslovesum=data[i].nowslovesum-data[i].slovesum;
            }
            allslovesumarr.push(parseInt(data[i].nowslovesum));
            alltodayslovesum.push(parseInt(parseInt(data[i].nowslovesum)-parseInt(data[i].slovesum)));
            allRatingarr.push(parseInt(data[i].Nowrating));
          }
          for (var i = 0; i < data.length; i++) {
            if(data[i].grade===this.grade)
            {
              slovesumarr.push(parseInt(data[i].nowslovesum));
              todayslovesum.push(parseInt(parseInt(data[i].nowslovesum)-parseInt(data[i].slovesum)));
              Ratingarr.push(parseInt(data[i].Nowrating));
            }
          }
          todayslovesum.sort(function(a, b){return b-a});
          Ratingarr.sort(function(a, b){return b-a});
          slovesumarr.sort(function(a, b){return b-a});
          alltodayslovesum.sort(function(a, b){return b-a});
          allRatingarr.sort(function(a, b){return b-a});
          allslovesumarr.sort(function(a, b){return b-a});
          this.gradesum=todayslovesum.length;
          this.allgradesum=alltodayslovesum.length;
          for (var i = 0; i < todayslovesum.length; i++) {
            if(todayslovesum[i]===this.todayslovesum){
              this.todayslovesumrate=i+1;
              break;
            }
          }
          for (var i = 0; i < Ratingarr.length; i++) {
            if(Ratingarr[i]===this.Rating){
              this.Codeforcerate=i+1;
              break;
            }
          }
          for (var i = 0; i < slovesumarr.length; i++) {
            if(slovesumarr[i]===this.slovesum){
              this.slovesumrate=i+1;
              break;
            }
          }
          for (var i = 0; i < alltodayslovesum.length; i++) {
            if(alltodayslovesum[i]===this.todayslovesum){
              this.alltodayslovesumrate=i+1;
              break;
            }
          }
          for (var i = 0; i < allRatingarr.length; i++) {
            if(allRatingarr[i]===this.Rating){
              this.allCodeforcerate=i+1;
              break;
            }
          }
          for (var i = 0; i < allslovesumarr.length; i++) {
            if(allslovesumarr[i]===this.slovesum){
              this.allslovesumrate=i+1;
              break;
            }
          }
          this.loading=false;
        }
    );
  },
  methods:{
    change(value){
      for(var i=0;i<this.ojname.length;i++){
        if(this.ojname[i].label===value){
          this.ojslovesum=this.ojname[i].value;
          break;
        }
      }
    },
    updateDialog(){
      this.centerDialogVisible=true;
    },
    updatepassword(){
      if(this.oldpassword===""){
        this.$message.error('旧密码不能为空');
      }
      else if(this.newpassword===""){
        this.$message.error('新密码不能为空');
      }
      else if(this.confignewpassword===""){
        this.$message.error('确认密码不能为空');
      }
      else if(this.newpassword!==this.confignewpassword) {
        this.$message.error('两次密码输入不一致');
      }
      else
      {
        axios.get('/api/sql/updatepassword/'+window.sessionStorage.getItem('token')+'/'+this.oldpassword+'/'+this.newpassword).then(
            res=>{
              if(res.data==='密码错误'){
                this.$message.error('密码输入错误');
              }
              else {
                this.$message({
                  message: '密码修改成功',
                  type: 'success'
                });
                this.oldpassword="";
                this.newpassword="";
                this.confignewpassword="";
                this.centerDialogVisible=false;
              }
            }
        );
      }
    }
  }
}
</script>

<style scoped>
.input-suffix{
  margin-top: 5%;
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
.el-card ::v-deep .el-card__header {
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
.el-card ::v-deep .el-card__body {
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
.fill{
  width : 100%;
  height : 100%;
  color: var(--theme-text-color);
  background-color: var(--bg-color);
  border-color: var(--bg-color);
  padding: 0;
}
.img {
  width: 80px;
  height: 80px;
  border-radius:40px;
}
/deep/ .el-dialog__title {
  color: var(--theme-text-color);
}
/deep/ .el-dialog__header {
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
/deep/ .el-dialog__body {
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
/deep/ .el-dialog__footer {
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
/deep/ .el-input__inner {
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
.buttondiv{
  margin-top: 5%;
}
</style>
<style>
.select{
  color: var(--theme-text-color);
  background-color: var(--bg-color);
}
</style>
