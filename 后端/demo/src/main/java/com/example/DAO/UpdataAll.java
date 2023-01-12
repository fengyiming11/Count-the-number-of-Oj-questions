package com.example.DAO;

public class UpdataAll {
    private SQLUpdate sqlUpdate = new SQLUpdate();
    public void updata() throws Exception {
        sqlUpdate.updataCodeforcesUserData();
    }
    public void updateacwing() throws Exception {
        sqlUpdate.UpdateAcwing();
        sqlUpdate.Updatahistory();
    }
    public void updataall() throws Exception {
        sqlUpdate.clear();
        sqlUpdate.UpdateContestId();
//        sqlUpdate.updataCodeforcesUserData();
        sqlUpdate.UpdateCodeforces_rating_data();
        sqlUpdate.UpdateSlovesum();
    }
}