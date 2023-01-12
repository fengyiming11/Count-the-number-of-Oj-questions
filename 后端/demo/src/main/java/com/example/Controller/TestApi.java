package com.example.Controller;

import com.example.DAO.SQLUpdate;
import com.example.Object.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TestApi {
    @ResponseBody
    @RequestMapping ("test")
    public String index() throws Exception {
        return "index";
    }
}
