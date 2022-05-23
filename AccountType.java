package com.cards.zokudo.entities;


import com.cards.zokudo.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name="account_type")
public class AccountType extends  AbstractEntity {

    @Column(name="name" , nullable = false)
    private String name;

    @Column(name="code" , nullable = false)
    private String code;

    @Column(name="status" , nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="daily_limit" , nullable = false)
    private double dailyLimit;

    @Column(name="monthly_limit" , nullable = false)
    private double monthlyLimit;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public double getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(double monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }
}
