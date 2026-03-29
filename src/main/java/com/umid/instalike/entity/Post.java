package com.umid.instalike.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


    @Column(nullable = false)
    private String caption;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    public Post() {}

    public Post(String caption, User author)
    {
        this.caption = caption;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return Id;
    }

    public User getAuthor() {
        return author;
    }

    public String getCaption() {
        return caption;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
