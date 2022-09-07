package com.example.project.DTO;

import java.io.Serializable;

public class MoneyDTO implements Serializable {

    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public MoneyDTO(String money) {
        this.money = money;
    }
}
