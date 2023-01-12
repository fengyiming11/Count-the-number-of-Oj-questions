package com.example.DAO;

import com.example.Object.User;
import com.example.config.JdbcConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ContestDB {
    @Autowired
    JdbcTemplate jdbcTemplate1;
    @Autowired
    DataSource dataSource = JdbcConfig.dataSource1();
    public List<String> findallContest() {
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "SELECT DISTINCT contestName from codeforces_user_rating_change ORDER BY ratingUpdateTimeSeconds DESC";
        List<String> x = jdbcTemplate1.queryForList(sql,String.class);
        return x;
    }
    public List<Map<String, Object>> findcontestdata(String s){
        jdbcTemplate1 = new JdbcTemplate(dataSource);
        String sql = "Select * from codeforces_user_rating_change WHERE contestName = '"+s+"'";
        List<Map<String, Object>> x = jdbcTemplate1.queryForList(sql);
        List<User> users = new SQLUpdate().findalluser();
        for (int i=0;i< x.size();i++){
            for(int j=0;j< users.size();j++){
                if(x.get(i).get("handle").equals(users.get(j).getCodeforcesId())){
                    x.get(i).put("ID",users.get(j).getRealID());
                    x.get(i).put("name",users.get(j).getRealname());
                    break;
                }
            }
        }
        return x;
    }

}
