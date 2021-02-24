package com.lepisori.voting.controller;

import com.lepisori.voting.contracts.Ballot;
import com.lepisori.voting.contracts.BallotCollection;
import com.lepisori.voting.model.VoteInfo;
import com.lepisori.voting.model.VoteSummary;
import com.lepisori.voting.service.ContractService;
import com.lepisori.voting.utils.web3jUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.Contract;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于初始化一次投票
 */

@Controller
public class ContractController {
    @Autowired
    private Web3j web3j;
    @Autowired
    ContractService contractService;

    @RequestMapping("/initVote")
    @ResponseBody
    public ModelAndView submitContract(VoteInfo voteInfo) throws Exception {  //进行初始化一次投票
        //获取地址值,并转换为address
        List<String> accounts = web3j.ethAccounts().send().getAccounts();
        List<Address> voters = new ArrayList<>();
        for (int i = 0; i < accounts.size(); i++) {
            voters.add(new Address(accounts.get(i)));
        }

        web3jUtils.setVoteInfo(voteInfo);
        //获取投票发起人的凭证对象
        Credentials credentials = Credentials.create(voteInfo.getPrivateKey());
        //ClientTransactionManager clientTransactionManager = new ClientTransactionManager(web3j, accounts.get(0));
        //部署投票集合合约
        BallotCollection.deploy(web3j, credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT);
        //将候选人类型转换为比特流
        List<Bytes32> candidates = new ArrayList<>();
        for (int i = 0; i < voteInfo.getCandidates().size(); i++) {
            candidates.add(new Bytes32(web3jUtils.stringToBytes32(voteInfo.getCandidates().get(i))));
        }
        //初始化返回对象

        //部署投票合约,获取ballot合约对象
        Ballot chairman_ballot; //发起者合约对象
        try {
            chairman_ballot = Ballot.deploy(web3j, credentials, Contract.GAS_PRICE, Contract.GAS_LIMIT, new Utf8String(voteInfo.getVoteName()),
                    new DynamicArray<Bytes32>(candidates), new Uint256(voteInfo.getSuccessPercentage()),
                    new DynamicArray<Address>(voters)).send();
        } catch (RuntimeException e) {
            //针对初始化时某位用户的余额已经耗尽，无法进行发起投票
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("msg", "私钥输入错误或账户余额不足，请检查您的账户！");
            modelAndView.setViewName("addBallot"); //设定setViewName跳转到指定html页面
            return modelAndView;
        }

        //将合约存储在数据库中
        VoteSummary voteSummary = new VoteSummary();
        voteSummary.setName(voteInfo.getVoteName());
        voteSummary.setAddress(chairman_ballot.getContractAddress());
        contractService.submitContract(voteSummary);

        web3jUtils.setBallot(chairman_ballot); //在utils中设置当前合约发起者对象
        return new ModelAndView("redirect:/index"); //重定向到初始投票界面
    }

    @RequestMapping("/closeVote")
    @ResponseBody
    public ModelAndView closeVote(String address,String privateKey, ModelAndView modelAndView) throws Exception {
        //关闭投票，先验私钥
        Credentials credentials = Credentials.create(privateKey);
        String in_address = credentials.getAddress();
        Ballot ballot = Ballot.load(address, web3j, credentials,
                Contract.GAS_PRICE, Contract.GAS_LIMIT);
        if(ballot.chairman().send().getValue().equals(in_address)){
            ballot.closeBallot().send();
            List<Bytes32> winner1 = ballot.winnerName().send().getValue();
            ArrayList<String> winners = new ArrayList<>();
            for(int i=0;i<winner1.size();i++){
                winners.add(new String(winner1.get(i).getValue()));
            }
            contractService.closeContract(address,winners.get(0));
            System.out.println("终止成功！");
        }else{
            modelAndView.addObject("msg","仅发起人可关闭投票！");
            System.out.println("终止失败！！");
        }
        modelAndView.setViewName("redirect:/index");
        modelAndView.addObject("msg","终止成功！");
        return modelAndView;
    }

}
