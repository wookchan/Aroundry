package com.example.project.DTO;

import java.io.Serializable;

public class PS_QRCodeDTO implements Serializable {
    private String name, point;

    public PS_QRCodeDTO(String name, String point) {
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
