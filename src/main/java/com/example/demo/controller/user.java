package com.example.demo.controller;

import com.example.demo.model.TransactionResponse;
import com.example.demo.model.userinfo;
import com.example.demo.model.usertransaction;
import com.example.demo.service.user_transaction;
import com.example.demo.service.uservalidateservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("user")
public class user {


    @Autowired
    uservalidateservice us;
    @Autowired
    user_transaction t;

    @RequestMapping("/")
    public String welcome()
    {
        return "WeLcome to Finance Application";
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody userinfo ui) {

        String l= us.validate(ui);
            if(l==null)
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Check your Username or Password");
            else
                return ResponseEntity.ok(l);
    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody userinfo ur)
    {
        Boolean status = us.register(ur);
        if(status)
        {
            return ResponseEntity.ok("User " + ur.getUsername() + " Registered Successfully");
        }
        else
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ur.getUsername() + " Already Exists");
    }
    @PutMapping("update/{userid}")
    public ResponseEntity<String> updateuser(@PathVariable String userid, @RequestBody userinfo ui) {
        Boolean success =   us.updateuser(userid,ui);

        if(success)
        {
            return ResponseEntity.ok("User Updated Successfully!");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Updation Failed");
    }
    @GetMapping("{userid}")
    public String getuser(@PathVariable String userid)
    {
       String s = us.getuser(userid);
       return s;
    }

    @PostMapping("transaction/{userid}")
    public ResponseEntity update_transaction(@PathVariable String userid, @RequestBody usertransaction ut)
    {
        Boolean success= t.updatetransaction(userid,ut);
        if(success)
            return ResponseEntity.ok("Ok");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed");

    }
    @GetMapping("fetchtransactions/{userid}")
    public ResponseEntity<TransactionResponse> getTransactionsForUser(@PathVariable String userid) {
        TransactionResponse response = t.fetchtransaction(userid);

        if (!response.getTransactions().isEmpty()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
