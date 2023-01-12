package com.example.DAO;

import com.example.Object.User;
import com.example.config.JdbcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDB {
    @Autowired
    JdbcTemplate jdbcTemplate1;
    @Autowired
    DataSource dataSource = JdbcConfig.dataSource1();
    public void InsertUser(User u) throws FileNotFoundException, InterruptedException {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "INSERT INTO user VALUES ('"+u.getRealID()+"','"+u.getRealname()+"','"+u.getGrade()+"','"+u.getCodeforcesId()+"','"+u.getAcwingCookie()+"','"+u.getNiukeOJID()+"')";
        jdbcTemplate1.update(sql);
        SQLUpdate x = new SQLUpdate();
    }
    public boolean Login(String uname,String passwd) {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "select count(*) from user where RealID = '"+uname+"' and password = '"+passwd+"'";
        int count=0;
        try{
            count=jdbcTemplate1.queryForObject(sql,int.class);
        }catch (Exception e) {
            return false;
        }
        if(count!=0) return true;
        return false;
    }

    public String updatepassword(String realID, String oldpassword, String newpassword) {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "select count(*) from user where RealID = '" +realID+"' AND  password = '"+oldpassword+"'";
        int count=jdbcTemplate1.queryForObject(sql,int.class);
        if(count==0) return "error";
        else
        {
            sql = "UPDATE user SET password = '"+newpassword+"' WHERE codeforces.user.RealID = '"+realID+"'";
            jdbcTemplate1.update(sql);
            return "ok";
        }
    }
    public Map<String, Object> queryown(String id){
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "select Realname from user where RealID = '" +id+"'";
        String name = jdbcTemplate1.queryForObject(sql,String.class);
        sql = "select CodeforcesId from user where RealID = '" +id+"'";
        String codeforcesID = jdbcTemplate1.queryForObject(sql,String.class);
        sql = "Select count(*) from codeforces_user where UserID = '"+codeforcesID+"'";
        int count=jdbcTemplate1.queryForObject(sql,int.class);
        if(count==0)
        {
            Map<String, Object> s = new HashMap<>();
            s.put("name",name);
            s.put("status","error");
            return s;
        }
        else
        {
            String sql2 = "Select * from codeforces_user where UserID = '"+codeforcesID+"'";
            Map<String, Object> s = jdbcTemplate1.queryForMap(sql2);
            s.put("status","ok");
            s.put("name",name);
            return s;
        }
    }
    public List<Map<String,Object> > query(){
        List<Map<String,Object> > data = new ArrayList<>();
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        List<User> users = new SQLUpdate().findalluser();
        for(int i=0;i<users.size();i++){
            String sql = "Select count(*) from codeforces_user where UserID = '"+users.get(i).getCodeforcesId()+"'";
            int count=jdbcTemplate1.queryForObject(sql,int.class);
            if(count==0)
            {
                continue;
            }
            else
            {
                sql = "Select nowslovesum from codeforces_user where UserID = '"+users.get(i).getCodeforcesId()+"'";
                Map<String, Object> s = new HashMap<>();
                s.put("nowslovesum",jdbcTemplate1.queryForObject(sql,int.class));
                sql = "Select slovesum from codeforces_user where UserID = '"+users.get(i).getCodeforcesId()+"'";
                s.put("slovesum",jdbcTemplate1.queryForObject(sql,int.class));
                s.put("ID",users.get(i).getRealID());
                s.put("name",users.get(i).getRealname());
                data.add(s);
            }
        }
        return data;
    }
    public List<Map<String,Object> > queryalldata(){
        List<Map<String,Object> > data = new ArrayList<>();
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        List<User> users = new SQLUpdate().findalluser();
        for(int i=0;i<users.size();i++){
            String sql = "Select count(*) from codeforces_user where UserID = '"+users.get(i).getCodeforcesId()+"'";
            int count=jdbcTemplate1.queryForObject(sql,int.class);
            if(count==0)
            {
                continue;
            }
            else
            {
                sql = "Select * from codeforces_user where UserID = '"+users.get(i).getCodeforcesId()+"'";
                Map<String, Object> s = jdbcTemplate1.queryForMap(sql);
                s.put("ID",users.get(i).getRealID());
                s.put("name",users.get(i).getRealname());
                s.put("grade",users.get(i).getGrade());
                data.add(s);
            }
        }
        return data;
    }

    public Map<String, Object> querygradedata() {
        Map<String,Object> data = new HashMap<>();
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        List<User> users = new SQLUpdate().findalluser();
        for(int i=0;i<users.size();i++){
            String sql = "Select count(*) from codeforces_user where UserID = '"+users.get(i).getCodeforcesId()+"'";
            int count=jdbcTemplate1.queryForObject(sql,int.class);
            if(count==0)
            {
                continue;
            }
            else
            {
                sql = "Select * from codeforces_user where UserID = '"+users.get(i).getCodeforcesId()+"'";
                Map<String, Object> s = jdbcTemplate1.queryForMap(sql);
                int slovesum=Integer.parseInt(s.get("nowslovesum").toString())-Integer.parseInt(s.get("slovesum").toString());
                if(data.containsKey(users.get(i).getGrade())){
                    slovesum=Integer.parseInt(data.get(users.get(i).getGrade()).toString())+slovesum;
                    data.put(users.get(i).getGrade(),slovesum);
                }
                else {
                    data.put(users.get(i).getGrade(),slovesum);
                }
            }
        }
        return data;
    }

    public List<Map<String, Object>> queryhistory(String RealID) {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "Select count(*) from history where RealID = '"+RealID+"'";
        int count=jdbcTemplate1.queryForObject(sql,int.class);
        if(count==0) return null;
        sql = "Select * from history where RealID = '"+RealID+"'";
        List<Map<String, Object>> data = jdbcTemplate1.queryForList(sql);
        return data;
    }

    public Map<String, Object> queryojslove(String RealID) {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "Select * from allojslovesum where RealID = '"+RealID+"'";
        Map<String, Object> data = jdbcTemplate1.queryForMap(sql);
        return data;
    }

    public void updatecookie(String username, String cookie) {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "Update user SET AcwingCookie = '"+cookie+"' where RealID = '"+username+"'";
        jdbcTemplate1.update(sql);
    }
}
