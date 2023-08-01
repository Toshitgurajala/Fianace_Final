package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="usertransactions")
public class usertransaction {

    @Id
    private String transactionId;
    private LocalDateTime dateTime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private userinfo user;
    private double amount;
    private String reason;
}
