package com.lepisori.voting.service.impl;

import com.lepisori.voting.dao.ContractMapper;
import com.lepisori.voting.model.VoteInfo;
import com.lepisori.voting.model.VoteSummary;
import com.lepisori.voting.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractMapper contractMapper;


    @Override
    public List<VoteSummary> getAllContract() {
        List<VoteSummary> allContracts = contractMapper.getAllContract();
        return allContracts;
    }

    @Override
    public void submitContract(VoteSummary voteSummary) {
        contractMapper.insertContract(voteSummary);
    }

    @Override
    public void closeContract(String contractAddress, String winner) {
        contractMapper.updateContract(winner,contractAddress);
    }
}
