package com.example.DAO;

import com.example.JSON.GetJson;
import com.example.Object.User;
import com.example.config.JdbcConfig;
import com.example.Controller.Getdata;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SQLUpdate {
    @Autowired
    JdbcTemplate jdbcTemplate1;
    @Autowired
    DataSource dataSource = JdbcConfig.dataSource1();
    public List<User> findalluser(){
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        List<User> user = jdbcTemplate1.query("SELECT * FROM user",new BeanPropertyRowMapper<User>(User.class));
        return user;
    }
    public List<String> findallOj(){
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        List<String> ojname = jdbcTemplate1.queryForList("SELECT Ojname FROM alloj",String.class);
        return ojname;
    }
    public void UpdateContestId() {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        JSONArray result = new Getdata().GetContestId();
        for(int i=0;i<result.length();i++)
        {
            JSONObject x = result.getJSONObject(i);
            if(!Objects.equals(x.getString("type"), "IOI")||!Objects.equals(x.getString("phase"),"FINISHED")) continue;

            int count=0;
            try{
                String sql = "select ContestId From contest where ContestId = '"+x.getInt("id")+"'";
                count=jdbcTemplate1.queryForObject(sql,int.class);
            }catch (Exception e) {
                String sql = "INSERT INTO contest VALUES ('" + x.getInt("id") + "','" + x.getString("name") + "','" +
                        x.getString("type") + "','" + x.getInt("durationSeconds") + "','" + x.getInt("startTimeSeconds") + "')";
                jdbcTemplate1.update(sql);
            }
            if(count!=0) break;
        }
    }
    public int updatanowslovesum(String name) throws Exception {
        List<String> oj = findallOj();
        List<User> user = findalluser();
        Map<String, Integer> mp = new HashMap<>();
        int slovesum=0;
        for(int i=0;i<user.size();i++)
        {
            if(!Objects.equals(user.get(i).getRealname(), name)) continue;
            for(int j=0;j<oj.size();j++)
            {
                if(j%5==0) Thread.currentThread().sleep(3000);
                String ojname=oj.get(j),userid = null;
                if(Objects.equals(ojname,"aizu")) userid=user.get(i).getAIZUID();
                else if(Objects.equals(ojname,"atcoder")) userid=user.get(i).getAtCoderID();
                else if(Objects.equals(ojname,"acwing")) userid=user.get(i).getAcwingCookie();
                else if(Objects.equals(ojname,"bnu")) userid=user.get(i).getBNUOJID();
                else if(Objects.equals(ojname,"codechef")) userid=user.get(i).getCodeChefID();
                else if(Objects.equals(ojname,"codeforces")) userid=user.get(i).getCodeforcesId();
                else if(Objects.equals(ojname,"codewars")) userid=user.get(i).getCodewarsID();
                else if(Objects.equals(ojname,"csu")) userid=user.get(i).getCSUID();
                else if(Objects.equals(ojname,"dashiye")) userid=user.get(i).getHYSBZID();
                else if(Objects.equals(ojname,"dmoj")) userid=user.get(i).getDMOJID();
                else if(Objects.equals(ojname,"eljudge")) userid=user.get(i).getElJudgeID();
                else if(Objects.equals(ojname,"fzu")) userid=user.get(i).getFZUID();
                else if(Objects.equals(ojname,"hdu")) userid=user.get(i).getHDUID();
                else if(Objects.equals(ojname,"leetcode_cn")) userid=user.get(i).getLeetCode_CNID();
                else if(Objects.equals(ojname,"loj")) userid=user.get(i).getLibreOJID();
                else if(Objects.equals(ojname,"luogu")) userid=user.get(i).getLuoguID();
                else if(Objects.equals(ojname,"nbut")) userid=user.get(i).getNBUTID();
                else if(Objects.equals(ojname,"nod")) userid=user.get(i).getNodID();
                else if(Objects.equals(ojname,"nowcoder")) userid=user.get(i).getNiukeOJID();
                else if(Objects.equals(ojname,"poj")) userid=user.get(i).getPOJID();
                else if(Objects.equals(ojname,"spoj")) userid=user.get(i).getSPOJID();
                else if(Objects.equals(ojname,"timus")) userid=user.get(i).getTimusID();
                else if(Objects.equals(ojname,"uestc")) userid=user.get(i).getUESTCID();
                else if(Objects.equals(ojname,"uoj")) userid=user.get(i).getUOJID();
                else if(Objects.equals(ojname,"uva")) userid=user.get(i).getUVAID();
                else if(Objects.equals(ojname,"uvalive")) userid=user.get(i).getICPCLiveArchiveID();
                else if(Objects.equals(ojname,"vjudge")) userid=user.get(i).getVJudgeID();
                else if(Objects.equals(ojname,"zoj")) userid=user.get(i).getZOJID();
                else if(Objects.equals(ojname,"daimayuan")) userid=user.get(i).getDaimayuanID();
                if(userid==null) continue;
                else if(ojname.equals("acwing"))
                {
                    slovesum+=updataojslove(ojname,user.get(i).getRealID(),0);
                    continue;
                }
                else if(ojname.equals("daimayuan"))
                {
                    slovesum+=updataojslove(ojname,user.get(i).getRealID(),new Getdata().Getdaimayuanlovesum(userid));
                }
                else
                {
                    String address = "https://ojhunt.com/api/crawlers/"+ojname+"/"+userid;
                    JSONObject dayLine = new GetJson().getHttpJson(address, 1);
                    if(dayLine==null) {
                        System.out.println("null "+ojname+" "+userid);
                        slovesum+=updataojslove(ojname,user.get(i).getRealID(),0);
                        continue;
                    }
                    if(dayLine.getBoolean("error")==true) {
                        System.out.println(user.get(i).getRealID()+" "+user.get(i).getRealname()+" 信息错误,在"+ojname+"中不存在该用户:"+userid);
                        slovesum+=updataojslove(ojname,user.get(i).getRealID(),0);
                        continue;
                    }
                    JSONObject result = dayLine.getJSONObject("data");
                    slovesum+=updataojslove(ojname,user.get(i).getRealID(),result.getInt("solved"));
                }
            }
        }
        return slovesum;
    }

    public void UpdateUserData(Map<String,String> mp,String CodeforcesId,int slovesum){
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "select count(*) From codeforces_user where UserID = '"+CodeforcesId+"'";
        int count=jdbcTemplate1.queryForObject(sql,int.class);
        if(count==0)
        {
            String sql2;
            if(!mp.containsKey("rating"))
            {
                sql2 = "INSERT INTO codeforces_user VALUES ('"+CodeforcesId+"',"+-1+","+-1+
                        ",'"+mp.get("avatar")+"',"+0+","+mp.get("nowslovesum")+")";
                jdbcTemplate1.update(sql2);
                return;
            }
            if(slovesum==-1)
            {
                sql2 = "INSERT INTO codeforces_user VALUES ('"+CodeforcesId+"',"+mp.get("rating")+","+mp.get("maxRating")+
                        ",'"+mp.get("avatar")+"',"+0+","+mp.get("nowslovesum")+")";
            }
            else
            {
                sql2 = "INSERT INTO codeforces_user VALUES ('"+CodeforcesId+"',"+mp.get("rating")+","+mp.get("maxRating")+
                        ",'"+mp.get("avatar")+"',"+mp.get("slovesum")+","+mp.get("nowslovesum")+")";
            }
            jdbcTemplate1.update(sql2);
        }
        else
        {
            String sql2;
            if(slovesum==-1)
            {
                if(!mp.containsKey("rating"))
                {
                    sql2 = "UPDATE codeforces_user SET Avatar = '"+mp.get("avatar")
                            +"',nowslovesum = "+mp.get("nowslovesum")+" where UserID = '"+CodeforcesId+"'";
                    jdbcTemplate1.update(sql2);
                    return;
                }
                sql2 = "UPDATE codeforces_user SET Nowrating = "+mp.get("rating")+",Maxrating = "+mp.get("maxRating")+",Avatar = '"+mp.get("avatar")
                        +"',nowslovesum = "+mp.get("nowslovesum")+" where UserID = '"+CodeforcesId+"'";
            }
            else
            {
                if(!mp.containsKey("rating"))
                {
                    sql2 = "UPDATE codeforces_user SET Avatar = '"+mp.get("avatar")
                            +"',slovesum = "+mp.get("slovesum")+",nowslovesum = "+mp.get("nowslovesum")+" where UserID = '"+CodeforcesId+"'";
                    jdbcTemplate1.update(sql2);
                    return;
                }
                sql2 = "UPDATE codeforces_user SET Nowrating = "+mp.get("rating")+",Maxrating = "+mp.get("maxRating")+",Avatar = '"+mp.get("avatar")
                        +"',slovesum = "+mp.get("slovesum")+",nowslovesum = "+mp.get("nowslovesum")+" where UserID = '"+CodeforcesId+"'";
            }
            jdbcTemplate1.update(sql2);
        }
    }
    public void updataCodeforcesUserData() throws Exception {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        List<User> user = findalluser();
        for(int i=0;i<user.size();i++){
            System.out.println(user.get(i).getRealname());
            if(i%4==0) Thread.currentThread().sleep(3000);
            int slovesum=updatanowslovesum(user.get(i).getRealname());
            Map<String,String> mp = new Getdata().GetUserData(user.get(i).getCodeforcesId());
            if(mp==null) {
                i--;
                continue;
            }
            else if(mp.get("status").equals("error")){
                System.out.println(user.get(i).getRealname()+":cf用户名错误");
                continue;
            }
            mp.put("nowslovesum", String.valueOf(slovesum));
            UpdateUserData(mp,user.get(i).getCodeforcesId(),-1);
        }
    }

    public void UpdateSlovesum() {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        List<User> user = findalluser();
        for(int i=0;i<user.size();i++){
            String sql = "select count(*) From codeforces_user where UserID = '"+user.get(i).getCodeforcesId()+"'";
            int count=jdbcTemplate1.queryForObject(sql,int.class);
            if(count==0){
                continue;
            }
            else
            {
                Inserthistory(user.get(i));
                sql="UPDATE codeforces_user SET slovesum = nowslovesum WHERE UserID = '"+user.get(i).getCodeforcesId()+"'";
                jdbcTemplate1.update(sql);
            }
        }
    }

    public void Inserthistory(User u) {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "Select count(*) from codeforces_user where UserID = '"+u.getCodeforcesId()+"'";
        int count=jdbcTemplate1.queryForObject(sql,int.class);
        if(count==0)
        {
            return;
        }
        sql = "Select * from codeforces_user where UserID = '"+u.getCodeforcesId()+"'";
        Map<String, Object> s = jdbcTemplate1.queryForMap(sql);
        int todayslovesum=Integer.parseInt(s.get("nowslovesum").toString())-Integer.parseInt(s.get("slovesum").toString());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = format.format(date);
        sql = "INSERT INTO history VALUES ('"+u.getRealID()+"',"+todayslovesum+",'"+str+"')";
        jdbcTemplate1.update(sql);
    }

    public void UpdateCodeforces_rating_data() throws InterruptedException {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        List<User> user = findalluser();
        Getdata getdata = new Getdata();
        for(int i=0;i<user.size();i++){
            if(i%5==0) Thread.currentThread().sleep(3000);
            JSONArray result = getdata.GetrateData(user.get(i).getCodeforcesId());
            if(result==null){
                continue;
            }
            for (int j=0;j<result.length();j++){
                JSONObject now = result.getJSONObject(j);
                String sql = "SELECT count(*) from codeforces_user_rating_change WHERE contestId = '"+now.getInt("contestId")+"' and handle = '"+now.getString("handle")+"'";
                int count = jdbcTemplate1.queryForObject(sql,int.class);
                if(count==0){
                    sql = "INSERT INTO codeforces_user_rating_change VALUES ('"+now.getInt("contestId")
                            +"','"+now.getString("contestName")+"','"+now.getString("handle")
                            +"','"+now.getInt("rank")+"','"+now.getInt("ratingUpdateTimeSeconds")
                            +"','"+now.getInt("oldRating")+"','"+now.getInt("newRating")+"')";
                    jdbcTemplate1.update(sql);
                }
                else break;
            }
        }
    }
    public int updataojslove(String ojname,String realID,int slovesum){
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "SELECT count(*) from allojslovesum WHERE RealID = '"+realID+"'";
        int count = jdbcTemplate1.queryForObject(sql,int.class);
        if(count==0){
            sql = "INSERT INTO allojslovesum (RealID) VALUES ('"+realID+"')";
            jdbcTemplate1.update(sql);
        }
        sql="SELECT "+ojname+" from allojslovesum WHERE RealID = '"+realID+"'";
        int now;
        if(jdbcTemplate1.queryForObject(sql,int.class)==null) now=0;
        else now=jdbcTemplate1.queryForObject(sql,int.class);
        int result=Math.max(slovesum,now);
        sql="UPDATE allojslovesum SET "+ojname+" = "+result+" WHERE RealID = '"+realID+"'";
        jdbcTemplate1.update(sql);
        return result;
    }

    public void UpdateAcwing() {
        List<User> users = findalluser();
        for (int i=0;i<users.size();i++){
            updataacwingslove(users.get(i).getRealID(),new Getdata().GetAcwingslovesum(users.get(i).getAcwingCookie()));
        }
    }
    private void updataacwingslove(String realID, int slove) {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql="UPDATE allojslovesum SET acwing = "+slove+" WHERE RealID = '"+realID+"'";
        jdbcTemplate1.update(sql);
    }

    public void Updatahistory() {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "insert into allojslovesum_history(RealID,acwing,aizu,atcoder,bnu,codechef,codeforces,codewars,csu,dashiye\n" +
                ",daimayuan,dmoj,eljudge,fzu,hdu,leetcode_cn,loj,luogu\n" +
                ",nbut,nod,nowcoder,poj,spoj,timus,uestc,uoj,uva,uvalive,vjudge,zoj,inserttime) SELECT RealID,acwing,aizu,atcoder,bnu,codechef,codeforces,codewars,csu,dashiye\n" +
                ",daimayuan,dmoj,eljudge,fzu,hdu,leetcode_cn,loj,luogu\n" +
                ",nbut,nod,nowcoder,poj,spoj,timus,uestc,uoj,uva,uvalive,vjudge,zoj,NOW() as inserttime from allojslovesum";
        jdbcTemplate1.update(sql);
    }
    public void clear() {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "Delete From allojslovesum_history where DATE(inserttime) <= DATE(DATE_SUB(NOW(),INTERVAL 30 day))";
        jdbcTemplate1.update(sql);
    }
}