package com.lepisori.voting.controller;


import com.lepisori.voting.contracts.Ballot;
import com.lepisori.voting.utils.web3jUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.util.HashMap;

/**
 * 给一般投票者进行投票的页面
 */

@Controller
public class StartVoteController {
    @Autowired
    Web3j web3j;
    @RequestMapping("/startVote")
    @ResponseBody
    public ModelAndView startVote() throws Exception {

        HashMap<String, Integer> map = web3jUtils.checkVotes();
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.addObject("msg", "开始投票");
        modelAndView.addObject("candidates", map);
        modelAndView.setViewName("vote"); //设定setViewName跳转到指定html页面
        return modelAndView;
    }

    @RequestMapping("/startVote/{address}")
    @ResponseBody
    public ModelAndView entryVote(@PathVariable("address") String address,ModelAndView modelAndView) throws Exception {
        String from = web3j.ethAccounts().send().getAccounts().get(0);
        Ballot ballot=Ballot.load(address,web3j,new ClientTransactionManager(web3j,from), Contract.GAS_PRICE,Contract.GAS_LIMIT);
        HashMap<String, Integer> candidates = new HashMap<>();
        for(int i=0;i<ballot.getProposalNumber().send().getValue().intValue();i++){
            byte[] value1 = ballot.proposals(new Uint256(i)).send().getValue1().getValue();
            String value = new String(value1).trim();
            int value2 = ballot.proposals(new Uint256(i)).send().getValue2().getValue().intValue();
            candidates.put(value,value2);
        }
        System.out.println(candidates);
        //modelAndView.addObject("msg", "开始投票");
        modelAndView.addObject("candidates", candidates);
        modelAndView.addObject("address",address);
        modelAndView.setViewName("vote"); //设定setViewName跳转到指定html页面
        return modelAndView;
    }

}
