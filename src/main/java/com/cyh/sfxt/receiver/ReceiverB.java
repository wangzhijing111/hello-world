package com.cyh.sfxt.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.B")
public class ReceiverB {
    @RabbitHandler
    public void procecss (String hello){
        System.out.println("ReceiverB  : " + hello);
    }
}
