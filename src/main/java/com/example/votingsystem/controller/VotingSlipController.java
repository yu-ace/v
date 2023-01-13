package com.example.votingsystem.controller;

import com.example.votingsystem.model.User;
import com.example.votingsystem.model.VotingSlip;
import com.example.votingsystem.service.IVotingSlipService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VotingSlipController {

    @Autowired
    IVotingSlipService votingSlipService;

    @RequestMapping(path = "/vote",method = RequestMethod.POST)
    public String vote(
            @RequestParam(name = "votingIdList")
            String votingIdList, Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        try {
            votingSlipService.newVotingItem(votingIdList, user.getId());
            model.addAttribute("tip","投票成功");
            return "users/votingForMe";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("tip",e.getMessage());
            return "users/votingForMe";
        }
    }

    @RequestMapping(path = "/votingSlipByPage",method = RequestMethod.POST)
    public String votingSlipByPage(
            @RequestParam(name = "number")
            int n, Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageRequest of = PageRequest.of(n, 10);
        Page<VotingSlip> votingSlipPage = votingSlipService.getVerificationByUserId(user.getId(), of);
        model.addAttribute("votingSlipPage",votingSlipPage);
        return "users/votingForMe";
    }


}
