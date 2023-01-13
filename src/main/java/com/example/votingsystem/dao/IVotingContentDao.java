package com.example.votingsystem.dao;

import com.example.votingsystem.model.VotingContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotingContentDao extends JpaRepository<VotingContent,Integer> {
    Page<VotingContent> findByVotingId(int votingId, Pageable pageable);
    VotingContent findById(int id);
}
