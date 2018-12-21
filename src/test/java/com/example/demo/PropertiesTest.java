package com.example.demo;

import com.example.demo.comm.DemoProperties;
import com.example.demo.comm.OtherProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {
    @Value("${demo.title}")
    private String title;

    @Resource
    private DemoProperties properties;

    @Test
    public void testSingle() {
        String a ="123456789qazwsx";
        System.out.println(a.substring(3));
        Assert.assertEquals(title,"纯洁的微笑");
    }



    @Test
    public void testMore() throws Exception {
        System.out.println("title:"+properties.getTitle());
        System.out.println("description:"+properties.getDescription());
    }

    @Resource
    private OtherProperties otherProperties;
    @Test
    public void testOther() throws Exception {
        System.out.println("title:"+otherProperties.getTitle());
        System.out.println("blog:"+otherProperties.getBlog());
    }
}
