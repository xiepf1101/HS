package com.rabbit.user;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        User user=new User();
        user.setUserName("rabb");
        user.setPassword("123456789");
        System.out.println("user send : " + user.getUserName()+"/"+user.getPassword());
        this.rabbitTemplate.convertAndSend("userQueue", user);
    }

}
