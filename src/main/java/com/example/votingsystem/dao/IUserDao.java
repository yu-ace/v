package com.example.votingsystem.dao;

import com.example.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User,Integer> {
    User findByCellphone(String cellphone);
}
