package com.example.Controller;

import com.example.DAO.UserDB;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Map;

@Controller
public class Login {
    @ResponseBody
    @RequestMapping ("login")
    public String check(@RequestBody Map<String,Object> map) throws FileNotFoundException {
        String uname= map.get("uname").toString();
        String password= map.get("password").toString();
        if(new UserDB().Login(uname,password)==true) return "ok";
        return "error";
    }
}
