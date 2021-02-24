package com.lepisori.voting.service;

import com.lepisori.voting.model.VoteSummary;

import java.util.List;

public interface ContractService {
    public List<VoteSummary> getAllContract();
    public void submitContract(VoteSummary voteSummary);
    public void closeContract(String contractAddress,String winner);
}
