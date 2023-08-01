package com.example.demo.service;

import com.example.demo.dao.transactiondao;
import com.example.demo.dao.userdao;
import com.example.demo.model.TransactionResponse;
import com.example.demo.model.userinfo;
import com.example.demo.model.usertransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class user_transaction {
    @Autowired
    transactiondao ud;
    @Autowired
    userdao us;

    public Boolean updatetransaction(String userid, usertransaction ut) {
        userinfo user = us.findById(userid).orElse(null);

        if (user != null) {
            ut.setUser(user);
            ud.save(ut);
            return true;
        }
       return false;
    }

    public TransactionResponse fetchtransaction(String userid) {
        Long userIdLong = Long.parseLong(userid);
        ArrayList<usertransaction> transactions = ud.findAllByUserId(userIdLong);

        TransactionResponse response = new TransactionResponse();
        response.setTransactions(transactions);

        return response;
    }
}
