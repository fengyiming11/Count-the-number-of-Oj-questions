package com.example.Controller;
import com.example.JSON.GetJson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Getdata {
    public Map<String,String> GetUserData(String name){
        try {
            String address = "https://codeforces.com/api/user.info?handles="+name;
            JSONObject dayLine = new GetJson().getHttpJson(address, 1);
            if(dayLine==null) return null;
            Map<String,String> mp = new HashMap<>();
            String status = dayLine.getString("status");
            if(Objects.equals(status, "FAILED"))
            {
                mp.put("status","error");
                return mp;
            }
            mp.put("status","ok");
            JSONObject result = (JSONObject) dayLine.getJSONArray("result").get(0);
            if(result.toMap().containsKey("rating"))
            {
                mp.put("handle",result.getString("handle"));
                mp.put("avatar",result.getString("avatar"));
                mp.put("maxRating", String.valueOf(result.getInt("maxRating")));
                mp.put("rating", String.valueOf(result.getInt("rating")));
                return mp;
            }
            else {
                mp.put("handle",result.getString("handle"));
                mp.put("avatar",result.getString("avatar"));
                return mp;
            }
        } catch (Exception e) {
            System.out.printf("Exception occurs!!\n");
            System.out.println(name);
            System.out.println("查无此人");
            System.out.printf("===========================\n");
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray GetrateData(String name){
        try {
            Thread.sleep(1000);
            String address = "https://codeforces.com/api/user.rating?handle="+name;
            JSONObject dayLine = new GetJson().getHttpJson(address,1);
            if(dayLine==null) return null;
            Map<String,String> mp = new HashMap<>();
            String staus = dayLine.getString("status");
            if(Objects.equals(staus, "FAILED"))
            {
                return null;
            }
            JSONArray result = dayLine.getJSONArray("result");
            return result;
        } catch (Exception e) {
            System.out.printf("Exception occurs!!\n");
            System.out.println(name);
            System.out.println("查无此人");
            System.out.printf("===========================\n");
            e.printStackTrace();
        }
        return null;
    }
    public JSONArray GetContestId(){
        try {
            String address = "https://codeforces.com/api/contest.list?gym=false";
            JSONObject dayLine = new GetJson().getHttpJson(address, 1);
            JSONArray result = dayLine.getJSONArray("result");
            return result;

        } catch (Exception e) {
            System.out.printf("Exception occurs!!\n");
            System.out.println("查无此人");
            System.out.printf("===========================\n");
            e.printStackTrace();
        }
        return null;
    }

    public int GetAcwingslovesum(String name) {
        try {
            String address = "http://127.0.0.1:5210/acwing?name=" + name;
            JSONObject dayLine = new GetJson().getHttpJson(address, 1);
            if(dayLine==null)
            {
                System.out.println("Acwingcookie错误");
                return 0;
            }
            int result = dayLine.getInt("result");
            return result;
        } catch (Exception e) {
            System.out.printf("Exception occurs!!\n");
            System.out.println(name);
            System.out.println("查无此人");
            System.out.printf("===========================\n");
            e.printStackTrace();
        }
        return 0;
    }

    public int Getdaimayuanlovesum(String userid) throws IOException {
        String address = "http://oj.daimayuan.top/user/profile/"+userid;
        String dayLine = Jsoup.parse(new URL(address),2000).toString();
        int idx=dayLine.indexOf("AC 过的题目：共 ");
        int slovesum=0;
        for(int i=idx+10;;i++){
            if(dayLine.charAt(i)==' ') break;
            slovesum=slovesum*10+Integer.parseInt(String.valueOf(dayLine.charAt(i)));
        }
        return slovesum;
    }
}
