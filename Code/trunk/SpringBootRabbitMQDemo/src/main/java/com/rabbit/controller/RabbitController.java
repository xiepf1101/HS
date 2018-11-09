package com.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.callback.CallBackSender;
import com.rabbit.fanout.FanoutSender;
import com.rabbit.hello.HelloSender1;
import com.rabbit.topic.TopicSender;
import com.rabbit.user.UserSender;

/*
 * 被滴了酒的纸
 * 只适合用来画坎坷
 * 而我
 * 构思了很久的是平坦
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    
    @Autowired
    private HelloSender1 helloSender1;
    @Autowired
    private HelloSender1 helloSender2;
    @Autowired
    private UserSender userSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;
    @Autowired CallBackSender callBackSender;
    @PostMapping("/hello")
    public void hello() {
        helloSender1.send("hello Rabbit");
    }
    
    
    /**
     * 单生产者-多消费者
     */
    @PostMapping("/oneToMany")
    public void oneToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("hellomsg:"+i);
        }
        
    }
    
    /**
     * 多生产者-多消费者
     */
    @PostMapping("/manyToMany")
    public void manyToMany() {
        for(int i=0;i<10;i++){
            helloSender1.send("hellomsg:"+i);
            helloSender2.send("hellomsg:"+i);
        }
        
    }
    
    /**
     * 实体类传输测试
     */
    @PostMapping("/userTest")
    public void userTest() {
           userSender.send();
    }
    
    
    /**
     * topic ExChange示例
	 *	topic 是RabbitMQ中最灵活的一种方式，可以根据binding_key自由的绑定不同的队列
	 *	首先对topic规则配置，这里使用两个队列来测试（也就是在Application类中创建和绑定的topic.message和topic.messages两个队列），其中topic.message的bindting_key为
 	 *	“topic.message”，topic.messages的binding_key为“topic.#”；
     */
    
    /**
     * topic exchange类型rabbitmq测试
     */
    @PostMapping("/topicTest")
    public void topicTest() {
           topicSender.send();
    }
    /**
     * sender1发送的消息,routing_key是“topic.message”，所以exchange里面的绑定的binding_key是“topic.message”，topic.＃都符合路由规则;所以sender1
 		发送的消息，两个队列都能接收到；
  		sender2发送的消息，routing_key是“topic.messages”，所以exchange里面的绑定的binding_key只有topic.＃都符合路由规则;所以sender2发送的消息只有队列
 		topic.messages能收到。
     */
    
    /**
     * fanout ExChange示例
 		Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout转发器发送消息，绑定了这个转发器的所有队列都收到这个消息。
 		这里使用三个队列来测试（也就是在Application类中创建和绑定的fanout.A、fanout.B、fanout.C）这三个队列都和Application中创建的fanoutExchange转发器绑定。
     */
    /**
     * fanout exchange类型rabbitmq测试
     */
    @PostMapping("/fanoutTest")
    public void fanoutTest() {
           fanoutSender.send();
    }
    /*
     * 就算fanoutSender发送消息的时候，指定了routing_key为"abcd.ee"，但是所有接收者都接受到了消息
     */
    
    
    /**
     * 带callback的消息发送
 		增加回调处理，这里不再使用application.properties默认配置的方式，会在程序中显示的使用文件中的配置信息。该示例中没有新建队列和exchange，用的是第5节中的topic.messages队列和exchange转发器。消费者也是第5节中的topicMessagesReceiver
     */
    @PostMapping("/callback")
    public void callbak() {
        callBackSender.send();
    }
    
}