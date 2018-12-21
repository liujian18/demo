package com.example.demo.model;


public class BasicOpenOcr {

    private String service;

    private String pid;

    private String salt;

    private String lang;
    private String image;
    private String sign;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}
