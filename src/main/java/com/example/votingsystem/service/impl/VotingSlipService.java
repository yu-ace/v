package com.example.votingsystem.service.impl;

import com.example.votingsystem.dao.IVotingItemDao;
import com.example.votingsystem.dao.IVotingSlipDao;
import com.example.votingsystem.model.Voting;
import com.example.votingsystem.model.VotingContent;
import com.example.votingsystem.model.VotingItem;
import com.example.votingsystem.model.VotingSlip;
import com.example.votingsystem.service.IVotingService;
import com.example.votingsystem.service.IVotingSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VotingSlipService implements IVotingSlipService {

    @Autowired
    IVotingSlipDao votingSlipDao;
    @Autowired
    IVotingItemDao votingItemDao;
    @Autowired
    IVotingService votingService;

    @Override
    public Page<VotingSlip> getVerificationByUserId(int userId, Pageable pageable) {
        return votingSlipDao.findByUserId(userId, pageable);
    }

    @Override
    public VotingSlip newVotingSlip(int userId, int votingId) {
        VotingSlip votingSlip = new VotingSlip();
        votingSlip.setUserId(userId);
        votingSlip.setVotingId(votingId);
        votingSlip.setStatus("已投票");
        votingSlip.setCount(1);
        votingSlipDao.save(votingSlip);
        return votingSlip;
    }


    @Override
    public Page<VotingItem> getVotingByUserId(int userId, Pageable pageable) {
        return votingItemDao.findByUserId(userId,pageable);
    }

    @Override
    public void newVotingItem(String votingIdList, int userId) throws Exception{
        String[] split = votingIdList.split(",");
        int votingContentId = Integer.parseInt(split[0]);
        VotingContent votingContent = votingService.getVotingContentById(votingContentId);
        Voting voting = votingService.getVotingById(votingContent.getVotingId());
        VotingSlip votingSlip1 = new VotingSlip();
        votingSlip1.setUserId(userId);
        votingSlip1.setVotingId(voting.getId());
        votingSlip1.setStatus("已投票");
        votingSlip1.setCount(1);
        votingSlipDao.save(votingSlip1);
        int number;
        if(split.length <= voting.getCount()){
            number = split.length;
        }else{
            number = voting.getCount();
        }
        for(int i = 0;i < number;i++){
            VotingItem votingItem = new VotingItem();
            votingItem.setUserId(userId);
            votingItem.setVotingContentId(votingContentId);
            votingItem.setVotingSlipId(votingSlip1.getId());
            votingService.addVotingContent(votingContentId);
            votingItemDao.save(votingItem);
        }
    }
}
