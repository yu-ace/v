package com.example.votingsystem.service;

import com.example.votingsystem.model.VotingItem;
import com.example.votingsystem.model.VotingSlip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVotingSlipService {
    Page<VotingSlip> getVerificationByUserId(int userId, Pageable pageable);
    VotingSlip newVotingSlip(int userId,int votingId);

    Page<VotingItem> getVotingByUserId(int userId, Pageable pageable);
    void newVotingItem(String votingIdList, int userId) throws Exception;
}
