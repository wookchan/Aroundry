package com.example.project.DTO;

import java.io.Serializable;

public class BookMarkDTO implements Serializable {
    private String name, point;

    public BookMarkDTO(String name, String point) {
        this.name = name;
        this.point = point;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
