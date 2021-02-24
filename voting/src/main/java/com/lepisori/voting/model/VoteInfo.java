package com.lepisori.voting.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class VoteInfo {
    private String voteName;
    private int voteNumber;
    private long SuccessPercentage;
    private ArrayList<String> candidates;
    private String privateKey;

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public int getVoteNumber() {
        return voteNumber;
    }

    public void setVoteNumber(int voteNumber) {
        this.voteNumber = voteNumber;
    }

    public long getSuccessPercentage() {
        return SuccessPercentage;
    }

    public void setSuccessPercentage(long successPercentage) {
        SuccessPercentage = successPercentage;
    }

    public ArrayList<String> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<String> candidates) {
        this.candidates = candidates;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        return "VoteInfo{" +
                "voteName='" + voteName + '\'' +
                ", voteNumber=" + voteNumber +
                ", SuccessPercentage=" + SuccessPercentage +
                ", candidates=" + candidates +
                ", privateKey='" + privateKey + '\'' +
                '}';
    }


}
