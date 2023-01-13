package com.example.votingsystem.util;

import com.example.votingsystem.model.Voting;
import com.example.votingsystem.vo.VoVoting;

import java.util.Date;

public class VotingConvert {

    public static Voting getVotingByVo(VoVoting voting) {
        Voting vo1 = new Voting();
        vo1.setCount(voting.getCount());
        vo1.setName(voting.getName());
        vo1.setUserId(1);
        vo1.setCreateTime(new Date());
        vo1.setStartTime(voting.getStartTime());
        vo1.setEndTime(voting.getEndTime());
        vo1.setDescription(voting.getDescription());
        vo1.setStatus(0);
        return vo1;
    }

    public static VoVoting getVoVotingBy(Voting voting) {
        VoVoting vo1 = new VoVoting();
        vo1.setCount(voting.getCount());
        vo1.setName(voting.getName());
        vo1.setUserId(1);
        vo1.setCreateTime(new Date());
        vo1.setStartTime(voting.getStartTime());
        vo1.setEndTime(voting.getEndTime());
        vo1.setDescription(voting.getDescription());
        vo1.setStatus(0);
        return vo1;
    }
}
