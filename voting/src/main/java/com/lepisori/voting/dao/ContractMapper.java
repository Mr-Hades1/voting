package com.lepisori.voting.dao;

import com.lepisori.voting.model.VoteInfo;
import com.lepisori.voting.model.VoteSummary;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ContractMapper {
    public void insertContract(VoteSummary voteSummary);
    public void updateContract(String winner,String address);
    public List<VoteSummary> getAllContract();
}
