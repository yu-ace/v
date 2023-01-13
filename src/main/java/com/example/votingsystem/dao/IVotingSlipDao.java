package com.example.votingsystem.dao;

import com.example.votingsystem.model.VotingSlip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotingSlipDao extends JpaRepository<VotingSlip,Integer> {
    Page<VotingSlip> findByUserId(int userId, Pageable pageable);
    VotingSlip findByUserIdAndVotingId(int userId,int votingId);
}
