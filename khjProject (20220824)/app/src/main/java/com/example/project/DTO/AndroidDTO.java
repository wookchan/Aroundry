package com.example.project.DTO;

import java.io.Serializable;

public class AndroidDTO implements Serializable {
    private String id, money;

    public AndroidDTO(String id, String money) {
        this.id = id;
        this.money = money;
    }



    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
