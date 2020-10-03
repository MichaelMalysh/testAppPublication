package com.example.model;

import javax.persistence.*;

/**
 * @author Misha Malysh
 */
@Entity
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String photo;
    private int price;
    private String theme;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, nullable = true)
    private User user;

    public Publication(String title, String photo, int price, String theme, User user) {
        this.title = title;
        this.photo = photo;
        this.price = price;
        this.theme = theme;
        this.user = user;
    }

    public Publication(String title, String photo, int price, String theme) {
        this.title = title;
        this.photo = photo;
        this.price = price;
        this.theme = theme;
    }

    public Publication() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
