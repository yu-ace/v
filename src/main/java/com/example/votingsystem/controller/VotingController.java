package com.example.votingsystem.controller;

import com.example.votingsystem.model.User;
import com.example.votingsystem.model.Voting;
import com.example.votingsystem.model.VotingContent;
import com.example.votingsystem.service.IVotingService;
import com.example.votingsystem.vo.VoVoting;
import com.example.votingsystem.vo.VoVotingContent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.votingsystem.util.VotingConvert.getVotingByVo;

@Controller
public class VotingController {

    @Autowired
    IVotingService votingService;

    @ResponseBody
    @RequestMapping(path = "/votingId",method = RequestMethod.GET)
    public Voting votingId(int votingId){
        Voting voting = votingService.getVotingById(votingId);
        return voting;
    }

    @RequestMapping(path = "/results",method = RequestMethod.POST)
    public String results(
            @RequestParam(name = "name")
            String name,HttpSession session){
        Voting voting = votingService.getVotingByName(name);
        session.setAttribute("voting",voting);
        return "redirect:/results";
    }

    @RequestMapping(path = "/votedResults",method = RequestMethod.POST)
    public String votedResults(
            @RequestParam(name = "votingId")
            int votingId,HttpSession session){
        Voting voting = votingService.getVotingById(votingId);
        session.setAttribute("voting",voting);
        return "redirect:/results";
    }

    @RequestMapping(path = "/votingByPage",method = RequestMethod.POST)
    public String votingByPage(
            @RequestParam(name = "number")
            int n, Model model,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        PageRequest of = PageRequest.of(n, 10);
        Page<Voting> voting = votingService.getVotingByUserId(user.getId(), of);
        model.addAttribute("voting",voting);
        return "users/createVoting";
    }

    @RequestMapping(path = "/createNewVoting",method = RequestMethod.POST)
    public String createNewVoting(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name= "description")
            String description,
            @RequestParam(name = "contentList")
            String contentList,
            @RequestParam(name = "startTime")
            String startTime,
            @RequestParam(name = "endTime")
            String endTime,
            @RequestParam(name = "count")
            int count,HttpSession session,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Voting voting = votingService.newVoting(name, description, startTime, endTime,user.getId(), count);
        votingService.newVotingContent(contentList, voting.getId());
        session.setAttribute("voting",voting);
        return "redirect:/joinVoting";
    }

    @ResponseBody
    @RequestMapping(path = "/createNewVoting2",method = RequestMethod.POST)
    public String createNewVoting2(
            @RequestBody VoVoting voting, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Voting vo1 = getVotingByVo(voting);

        List<VoVotingContent> contentList = voting.getContentList();
        List<VotingContent> contents = new ArrayList<>();
        for (VoVotingContent voVotingContent : contentList) {
            VotingContent v = new VotingContent();
            v.setContent(voVotingContent.getContent());
            v.setCount(0);
            contents.add(v);
        }
        try {
            votingService.newVoting1(vo1,contents);
            return "OK";
        }catch (Exception exception){
            return exception.getMessage();
        }
    }




    @RequestMapping(path = "/votingResultsByPage" ,method = RequestMethod.POST)
    public String votingResultsByPage(
            @RequestParam(name = "name")
            String name,
            @RequestParam(name = "number")
            int n, Model model){
        Voting voting = votingService.getVotingByName(name);
        PageRequest of = PageRequest.of(n, 10);
        Page<VotingContent> votingContents = votingService.getVotingContentByVotingId(voting.getId(), of);
        model.addAttribute("votingContents",votingContents);
        return "users/results";
    }
}
