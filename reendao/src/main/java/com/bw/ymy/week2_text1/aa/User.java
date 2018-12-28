package com.bw.ymy.week2_text1.aa;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class User {
    @Id(autoincrement = false)
    private  long id;
    private String images;
    private double price;
    private String title;
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getImages() {
        return this.images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 1252061030)
    public User(long id, String images, double price, String title) {
        this.id = id;
        this.images = images;
        this.price = price;
        this.title = title;
    }
    @Generated(hash = 586692638)
    public User() {
    }

}
