package com.cyh.sfxt;

import com.cyh.sfxt.entirty.Users;
import com.cyh.sfxt.entirty.result.ResponseData;
import com.cyh.sfxt.rest.HelloSender;
import com.cyh.sfxt.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SfxtApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private HelloSender helloSender;

    @Test
    public void contextLoads() {
    }


    @Test
    public void getUserList(){
        Users users=new Users();
        ResponseData responseData = userService.getUserList(users,1,8);
        System.out.println(responseData.toString());
    }

    @Test
    public void testRabbitMQ(){
        helloSender.send();
    }
}
