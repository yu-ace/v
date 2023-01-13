package com.example.votingsystem.controller;

import com.example.votingsystem.model.User;
import com.example.votingsystem.model.Voting;
import com.example.votingsystem.model.VotingContent;
import com.example.votingsystem.model.VotingSlip;
import com.example.votingsystem.service.*;
import com.example.votingsystem.util.VotingConvert;
import com.example.votingsystem.vo.VoVoting;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PageController {

    @Autowired
    IUserService userService;
    @Autowired
    IVotingSlipService votingSlipService;
    @Autowired
    IVotingService votingService;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public String index(){
        return "login";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/votingIndex",method = RequestMethod.GET)
    public String votingIndex(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "users/votingIndex";
    }

    @RequestMapping(path = "/joinedVoting",method = RequestMethod.GET)
    public String joinedVoting(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageRequest of = PageRequest.of(0, 10);
        Page<VotingSlip> votingSlipPage = votingSlipService.getVerificationByUserId(user.getId(), of);
        model.addAttribute("votingSlipPage",votingSlipPage);
        return "users/votingForMe";
    }

    @RequestMapping(path = "/joinVoting",method = RequestMethod.GET)
    public String joinVoting(Integer votingId,Model model){
        Voting voting = votingService.getVotingById(votingId);
        VoVoting voVotingBy = VotingConvert.getVoVotingBy(voting);
        PageRequest of = PageRequest.of(0, 100);
        Page<VotingContent> votingContents = votingService.getVotingContentByVotingId(voting.getId(),of);
        model.addAttribute("voting",voting);
        model.addAttribute("votingContent",votingContents);
        return "users/joinVoting";
    }

    @RequestMapping(path = "/results",method = RequestMethod.GET)
    public String results(HttpSession session,Model model,Model model1){
        Voting voting = (Voting) session.getAttribute("voting");
        PageRequest of = PageRequest.of(0, 10);
        Page<VotingContent> votingContents = votingService.getVotingContentByVotingId(voting.getId(), of);
        model.addAttribute("voting",voting);
        model1.addAttribute("votingContents",votingContents);
        return "users/results";
    }

    @RequestMapping(path = "/createVoting",method = RequestMethod.GET)
    public String createVoting(Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageRequest of = PageRequest.of(0, 10);
        Page<Voting> voting = votingService.getVotingByUserId(user.getId(), of);
        model.addAttribute("voting",voting);
        return "users/createVoting";
    }

    @RequestMapping(path = "/create",method = RequestMethod.GET)
    public String create(){
        return "users/create";
    }
}
