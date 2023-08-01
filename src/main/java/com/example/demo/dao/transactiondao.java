package com.example.demo.dao;

import com.example.demo.model.userinfo;
import com.example.demo.model.usertransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface transactiondao extends JpaRepository<usertransaction,String> {
    ArrayList<usertransaction> findAllByUserId(Long userIdLong);
}
