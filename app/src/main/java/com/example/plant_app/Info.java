package com.example.plant_app;

public class Info {
    String name,email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Info(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
