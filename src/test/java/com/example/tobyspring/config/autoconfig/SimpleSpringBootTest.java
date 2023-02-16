package com.example.tobyspring.config.autoconfig;

import com.example.tobyspring.HelloService;
import com.example.tobyspring.TobySpringApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration( classes = TobySpringApplication.class)
public class SimpleSpringBootTest {

     @Autowired
     HelloService helloService;

    @Test
    public void simpleController(){
    //given
        String teest = helloService.sayHello("teest");
        System.out.println("test" + teest);
        //when
    //then
    }
}
