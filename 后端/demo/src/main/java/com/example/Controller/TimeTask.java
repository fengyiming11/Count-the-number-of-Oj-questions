package com.example.Controller;

import com.example.DAO.UpdataAll;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimeTask {

    private UpdataAll updataAll = new UpdataAll();

    //编写具体的定时任务组件（@Component注解），并且在需要定时调度的方法上添加@Scheduled触发器
    //每隔5秒执行一次：*/5 * * * * ?
    //每隔1分钟执行一次：0 */1 * * * ?
    //每天23点执行一次：0 0 23 * * ?
    //每天凌晨1点执行一次：0 0 1 * * ?
    //每月1号凌晨1点执行一次：0 0 1 1 * ?
    //每月最后一天23点执行一次：0 0 23 L * ?
    //每周星期天凌晨1点实行一次：0 0 1 ? * L
    //在26分、29分、33分执行一次：0 26,29,33 * * * ?
    //每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?
    @Scheduled(cron = "0 */15 * * * ?")
    public void setupdate() throws Exception {
        this.updataAll.updata();
    }

    @Scheduled(cron = "0 */30 * * * ?")
    public void setupdateacwing() throws Exception {
        this.updataAll.updateacwing();
    }
    @Scheduled(cron = "0 0 3 * * ?")
    public void setUpdataAll() throws Exception {
        this.updataAll.updataall();
    }
}
