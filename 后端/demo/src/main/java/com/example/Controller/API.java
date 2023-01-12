package com.example.Controller;

import com.example.DAO.ContestDB;
import com.example.DAO.SQLUpdate;
import com.example.DAO.UpdataAll;
import com.example.DAO.UserDB;
import com.example.Object.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class API {

    @ResponseBody
    @GetMapping ("User")
    public List<User> GetUserdata(){
        List<User> x = new SQLUpdate().findalluser();
        return x;
    }

    @ResponseBody
    @GetMapping ("User/{id}")
    public Map<String,Object> GetOwnUserdata(@PathVariable String id){
        Map<String,Object> x = new UserDB().queryown(id);
        return x;
    }

    @ResponseBody
    @GetMapping ("slovesum/all")
    public List<Map<String, Object>> Getslovealldata(){
        List<Map<String, Object>> x = new UserDB().query();
        return x;
    }

    @ResponseBody
    @GetMapping ("Codeforces/contestlist")
    public List<String> GetContestList(){
        List<String> list = new ContestDB().findallContest();
        return list;
    }

    @ResponseBody
    @GetMapping ("Codeforces/data/{contestname}")
    public List<Map<String, Object>> GetContestdata(@PathVariable String contestname){
        contestname=contestname.replace("空"," ");
        contestname=contestname.replace("井","#");
        List<Map<String, Object>> x = new ContestDB().findcontestdata(contestname);
        return x;
    }

    @ResponseBody
    @GetMapping ("alldata")
    public List<Map<String, Object>> GetAlldata(){
        List<Map<String, Object>> x = new UserDB().queryalldata();
        return x;
    }

    @ResponseBody
    @GetMapping ("slovedata")
    public Map<String, Object> Getslovedata(){
        Map<String, Object> x = new UserDB().querygradedata();
        return x;
    }

    @ResponseBody
    @RequestMapping ("/get/history/{RealID}")
    public List<Map<String, Object>> gethistory(@PathVariable String RealID) {
        List<Map<String, Object>> x = new UserDB().queryhistory(RealID);
        return x;
    }

    @ResponseBody
    @RequestMapping ("/get/ojslove/{RealID}")
    public Map<String, Object> getojslobve(@PathVariable String RealID) {
        Map<String, Object> x = new UserDB().queryojslove(RealID);
        return x;
    }
    @ResponseBody
    @RequestMapping ("/sql/updatepassword/{RealID}/{oldpassword}/{newpassword}")
    public String updatepassword(@PathVariable String RealID, @PathVariable String newpassword, @PathVariable String oldpassword) throws Exception {
        String result = new UserDB().updatepassword(RealID,oldpassword,newpassword);
        if(result=="ok") return "修改成功";
        else return "密码错误";
    }

    @ResponseBody
    @RequestMapping ("/updatecookie/{username}/{password}/{cookie}")
    public void updatecookie(@PathVariable String username, @PathVariable String password, @PathVariable String cookie) throws Exception {
        boolean x = new UserDB().Login(username,password);
        if(x==true){
            List<User> users = new ArrayList<>();
            cookie=cookie.replace("分",";");
            for (int i=0;i<users.size();i++){
                if(username.equals(users.get(i).getRealID())){
                    System.out.println("原cookie old cookie :" + users.get(i).getAcwingCookie());
                    break;
                }
            }
            new UserDB().updatecookie(username,cookie);
            for (int i=0;i<users.size();i++){
                if(username.equals(users.get(i).getRealID())){
                    System.out.println(" cookie发生变化 new cookie :" + cookie);
                    break;
                }
            }
        }
    }
}
