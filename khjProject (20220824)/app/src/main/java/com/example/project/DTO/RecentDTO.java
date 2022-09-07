package com.example.project.DTO;

import java.io.Serializable;

public class RecentDTO implements Serializable {

    private String recent;

    public  String getRecent() {
        return recent;
    }

    public void setRecent(String recent) {
        this.recent = recent;
    }

    public  RecentDTO(String recent) {
        this.recent = recent;
    }
}
