package com.edu.lgdu.darts.objects;


import java.io.Serializable;

public class RoundObject implements Serializable {
    private Long id;
    private int amount;
    private int fullAmount;
    private String photoPath;
    private String login;

    public RoundObject(Long id, int amount, int fullAmount, String photoPath) {
        this.id = id;
        this.amount = amount;
        this.fullAmount = fullAmount;
        this.photoPath = photoPath;
    }

    public RoundObject() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(int fullAmount) {
        this.fullAmount = fullAmount;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }




}
