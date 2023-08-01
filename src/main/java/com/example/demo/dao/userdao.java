package com.example.demo.dao;

import com.example.demo.model.userinfo;
import com.example.demo.service.user_transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userdao extends JpaRepository<userinfo,String> {
    userinfo findByUsernameAndPassword(String username, String pass);
    userinfo findByUsername(String username);
    userinfo findByid(Long userid);
}
