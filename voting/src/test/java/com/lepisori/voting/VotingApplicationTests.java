package com.lepisori.voting;

import com.lepisori.voting.model.VoteSummary;
import com.lepisori.voting.service.ContractService;
import com.lepisori.voting.service.impl.ContractServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Credentials;

@SpringBootTest
class VotingApplicationTests {

    @Test
    void contextLoads() {
        Credentials credentials = Credentials.create("0xa107ff50e1f46a86c975cc40951ae2b37368d81b0533cea6ed182e6579ce3e9d");
        System.out.println(credentials.getAddress());
    }

    @Test
    public void test(){
        ContractServiceImpl contractService = new ContractServiceImpl();
        VoteSummary voteSummary = new VoteSummary();
        voteSummary.setName("竞选主席");
        voteSummary.setWinner("小明");
        contractService.submitContract(voteSummary);
    }

}
