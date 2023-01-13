package com.example.votingsystem.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.votingsystem.dao.IVotingContentDao;
import com.example.votingsystem.dao.IVotingDao;
import com.example.votingsystem.model.Voting;
import com.example.votingsystem.model.VotingContent;
import com.example.votingsystem.service.IVotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional()
public class VotingService implements IVotingService {

    @Autowired
    IVotingDao votingDao;

    @Autowired
    IVotingContentDao votingContentDao;

    @Override
    public Voting getVotingById(int id) {
        return votingDao.findById(id);
    }

    @Override
    public Voting getVotingByName(String name) {
        return votingDao.findByName(name);
    }

    @Override
    public Page<Voting> getVotingByStatus(int status, Pageable pageable) {
        return votingDao.findByStatus(status,pageable);
    }

    @Override
    public Page<Voting> getVotingByUserId(int userId, Pageable pageable) {
        Page<Voting> votingPage = votingDao.findByUserId(userId, pageable);
        for(Voting voting : votingPage){
            if(voting.getEndTime().getTime() >= new Date().getTime()){
                voting.setStatus(1);
                votingDao.save(voting);
            }
        }
        return votingPage;
    }

    @Override
    public Voting newVoting(String name, String description, String startTime,String endTime, int userId, int count) {
        Voting voting = new Voting();
        voting.setName(name);
        voting.setDescription(description);
        voting.setCreateTime(new Date());
        DateTime startTime1 = DateUtil.parse(startTime);
        DateTime endTime1 = DateUtil.parse(endTime);
        voting.setStartTime(startTime1);
        voting.setEndTime(endTime1);
        voting.setUserId(userId);
        voting.setStatus(0);
        voting.setCount(count);
        votingDao.save(voting);
        return voting;
    }

    @Override
    public Voting newVoting1(Voting voting,List<VotingContent> contentList) {
        votingDao.save(voting);
        for (VotingContent votingContent : contentList) {
            votingContent.setVotingId(voting.getId());
        }
        votingContentDao.saveAll(contentList);
        return null;
    }



    @Override
    public Page<VotingContent> getVotingContentByVotingId(int votingId, Pageable pageable) {
        return votingContentDao.findByVotingId(votingId,pageable);
    }

    @Override
    public void newVotingContent(String contentList, int votingId) {
        String[] contents = contentList.split(",");
        for (String content : contents) {
            VotingContent votingContent = new VotingContent();
            votingContent.setContent(content);
            votingContent.setVotingId(votingId);
            votingContent.setCount(0);
            votingContentDao.save(votingContent);
        }
    }

    @Override
    public VotingContent getVotingContentById(int id) {
        return votingContentDao.findById(id);
    }

    @Override
    public VotingContent addVotingContent(int contentId) {
        VotingContent votingContent = votingContentDao.findById(contentId);
        votingContent.setCount(votingContent.getCount() + 1);
        votingContentDao.save(votingContent);
        return votingContent;
    }
}
