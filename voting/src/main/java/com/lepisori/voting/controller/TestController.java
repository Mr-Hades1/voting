package com.lepisori.voting.controller;


import com.lepisori.voting.model.VoteSummary;
import com.lepisori.voting.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.web3j.protocol.Web3j;

import java.io.IOException;
import java.util.List;

@Controller
public class TestController {
    //注入web3j对象
    @Autowired
    private Web3j web3j;
    @Autowired
    ContractService contractService;

    //测试页面
    @RequestMapping("/test")
    public String test(Model model) throws IOException {
        List<String> accounts = web3j.ethAccounts().send().getAccounts();
        model.addAttribute("accounts",accounts);
        return "test";
    }
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        List<VoteSummary> allContracts = contractService.getAllContract();
        System.out.println(allContracts);
        modelAndView.addObject("contracts",allContracts);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @RequestMapping("/addBallot")
    public String addBallot(){
        return "addBallot";
    }
    @RequestMapping("/vote")
    public String vote(){
        return "vote";
    }

}
