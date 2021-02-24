package com.lepisori.voting.controller;

import com.lepisori.voting.utils.web3jUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * 作为展示页面，仅仅用于实时数据展示
 */

@Controller
public class RefreshController {

    @RequestMapping("/refresh")
    @ResponseBody
    public ModelAndView refresh() throws Exception {

        HashMap<String, Integer> map = web3jUtils.checkVotes();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", "刷新成功！");
        modelAndView.addObject("candidates", map);
        modelAndView.setViewName("list"); //设定setViewName跳转到指定html页面
        return modelAndView;

    }
}
