package com.example.mobileapp.Domain;

public class CourseDomain {
    private int id;
    private String title;
    private String owner;
    private double price;
    private double star;
    private String courseCover;
    public CourseDomain(int id, String title, String owner, double price, double star, String courseCover) {
        this.id=id;
        this.title = title;
        this.owner = owner;
        this.price = price;
        this.star=star;
        this.courseCover = courseCover;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getCourseCover() {
        return courseCover;
    }
    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }
    public double getStar() {
        return star;
    }
    public void setStar(double star) {
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
