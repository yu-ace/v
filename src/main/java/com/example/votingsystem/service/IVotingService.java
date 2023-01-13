package com.example.votingsystem.service;

import com.example.votingsystem.model.Voting;
import com.example.votingsystem.model.VotingContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVotingService {
    Voting getVotingById(int id);
    Voting getVotingByName(String name);
    Page<Voting> getVotingByStatus(int status, Pageable pageable);
    Page<Voting> getVotingByUserId(int userId,Pageable pageable);
    Voting newVoting(String name, String description,String startTime,String endTime,int userId,int count);
    Voting newVoting1(Voting voting,List<VotingContent> contentList);

    Page<VotingContent> getVotingContentByVotingId(int votingId, Pageable pageable);
    void newVotingContent(String content,int votingId);
    VotingContent getVotingContentById(int id);
    VotingContent addVotingContent(int contentId);
}
