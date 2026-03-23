package com.umid.instalike.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private  String username;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;


    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.createdAt = LocalDateTime.now();
    }
    public User(){}




    public Long getId()
    {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setUsername(String name) {
        this.username = name;
    }
}
