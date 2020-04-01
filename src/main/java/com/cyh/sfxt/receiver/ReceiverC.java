package com.cyh.sfxt.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.C")
public class ReceiverC {
    @RabbitHandler
    public void procecss (String hello){
        System.out.println("ReceiverC  : " + hello);
    }
}
