package com.example.votingsystem.dao;

import com.example.votingsystem.model.Voting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotingDao extends JpaRepository<Voting,Integer> {
    Voting findById(int id);
    Voting findByName(String name);
    Page<Voting> findByStatus(int status, Pageable pageable);
    Page<Voting> findByUserId(int userId,Pageable pageable);
}
