package com.example.Object;

public class RankChangedataObject {
    private String contestId,contestName,handle;
    private int rank,ratingUpdateTimeSeconds,oldRating,newRating;

    public RankChangedataObject(String contestId, String contestName, String handle, int rank, int ratingUpdateTimeSeconds, int oldRating, int newRating) {
        this.contestId = contestId;
        this.contestName = contestName;
        this.handle = handle;
        this.rank = rank;
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
        this.oldRating = oldRating;
        this.newRating = newRating;
    }

    public String getContestId() {
        return contestId;
    }

    public void setContestId(String contestId) {
        this.contestId = contestId;
    }

    public String getContestName() {
        return contestName;
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getRatingUpdateTimeSeconds() {
        return ratingUpdateTimeSeconds;
    }

    public void setRatingUpdateTimeSeconds(int ratingUpdateTimeSeconds) {
        this.ratingUpdateTimeSeconds = ratingUpdateTimeSeconds;
    }

    public int getOldRating() {
        return oldRating;
    }

    public void setOldRating(int oldRating) {
        this.oldRating = oldRating;
    }

    public int getNewRating() {
        return newRating;
    }

    public void setNewRating(int newRating) {
        this.newRating = newRating;
    }
}
