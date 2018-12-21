package com.example.demo;


import com.example.demo.web.DoPostGetJsonController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DoPostGetJsonTest {
    private DoPostGetJsonController doPostGetJsonController;

    @Test
    public void getDoPostGetJson(){

        String responseString = doPostGetJsonController.getBasicOpenOcr();
                System.out.println("resutl : "+responseString);
    }
}
