package com.example.demo.comm;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix="demo")
public class DemoProperties {

    public static String DEMO_SERVICE = "basicOpenOcr";

    public static String DEMO_PID = "54e55ff759ddc0f1bf53a5f551a79361";

    public  static String DEMO_LANG = "zh-CHS";

    public  static String DEMO_KEY = "96056a0d85ae5cf6ef85a43f3c1d65d1";

    private String title;
    private String description;

    private String service;
    private String pid;
    private String lang;
    private String key;

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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;

    }
}
