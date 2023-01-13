package com.example.votingsystem.dao;

import com.example.votingsystem.model.VotingItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVotingItemDao extends JpaRepository<VotingItem,Integer> {
    Page<VotingItem> findByUserId(int userId,Pageable pageable);
}
