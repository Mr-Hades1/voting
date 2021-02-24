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
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.Contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 用作投票结果的控制页面，处理投票结果
 */

@Controller
public class VoteController {

    @Autowired
    private Web3j web3j; //注入web3对象

    @RequestMapping("/voteFor/{address}")
    @ResponseBody
    public ModelAndView vote(@PathVariable("address") String address, String candidate, String privateKey) throws Exception { //将post参数直接作为形参接受

        //根据privateKey参数获取凭证，验证私钥是否正确
        boolean flag = false;
        //ballot = web3jUtils.getBallot(); //从utils中获取Ballot
        //Ballot chairman_ballot = web3jUtils.getBallot();
        //ClientTransactionManager clientTransactionManager = new ClientTransactionManager(web3j,web3j.ethAccounts().send().getAccounts().get(0));
        BigInteger GASPRICE = BigInteger.valueOf(10L); //设置较低的GASPRICE值
        Ballot voter_ballot = Ballot.load(address, web3j, Credentials.create(privateKey),
                GASPRICE, Contract.GAS_LIMIT);

        //进行投票
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<voter_ballot.getProposalNumber().send().getValue().intValue();i++){
            list.add(new String(voter_ballot.proposals(new Uint256(i)).send().getValue1().getValue()).trim());
        }
        ModelAndView modelAndView = new ModelAndView();
        try {
            long j = 10000;    //得到选举人序号，初始化为10000，若未正确匹配到候选者将异常跳转。若设为-1将无法转换uint
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase(candidate))
                    j = i;
            }
            Uint256 index = new Uint256(j);     //得到Uint256格式的下标
            voter_ballot.vote(index).send();
            //投完票后展示当前每位候选人所得票数
            modelAndView.addObject("msg", "投票成功！");
            modelAndView.setViewName("list");

        } catch (RuntimeException e) {
            /*
             若私钥对应的用户不存在，voter_ballot将返回一个账户余额为0的用户，对应报错信息为：
            Error processing transaction request: sender doesn't have enough funds to send tx.
            The upfront cost is: 43000000 and the sender's account only has: 0

            针对sol源码中的require异常抛出，java中仅抛出RuntimeException
            require(legal, "the voter is illeagal!");
            require(proposal < proposals.length, "the prosal is invalid!");
            require(!voteRecords[voterAddress], "Already voted.");

            */
            String errorMsg = e.getMessage();
            System.out.println(errorMsg);
            if (errorMsg.contains("only")) {
                modelAndView.addObject("msg", "您的私钥输入错误，请确认！");
                modelAndView.setViewName("vote");

            } else if (errorMsg.contains("invalid")) {
                //用户在提交页面手动修改候选者名称导致无效
                modelAndView.addObject("msg", "对不起，您选择的候选者无效！");
                modelAndView.setViewName("vote");

            } else if (errorMsg.contains("Already")) {
                //已经投过票
                modelAndView.addObject("msg", "对不起，您已经投过票！");
                modelAndView.setViewName("list");

            } else if (errorMsg.contains("illeagal")) {
                //该用户私钥输入正确，但是没有投票权
                modelAndView.addObject("msg", "对不起，您没有投票权！");
                modelAndView.setViewName("list");

            }
        } finally {
            HashMap<String, Integer> map = new HashMap<>();
            for(int i=0;i<voter_ballot.getProposalNumber().send().getValue().intValue();i++){
                byte[] value1 = voter_ballot.proposals(new Uint256(i)).send().getValue1().getValue();
                String value = new String(value1).trim();
                int value2 = voter_ballot.proposals(new Uint256(i)).send().getValue2().getValue().intValue();
                map.put(value,value2);
            }
            modelAndView.addObject("candidates", map);
        }

        return modelAndView;
    }


}
