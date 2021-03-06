package com.stackroute.StudentProfileService.service;

import com.stackroute.StudentProfileService.domain.AuthStudent;
import com.stackroute.StudentProfileService.domain.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {
    public RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void send(String routingkey,AuthStudent student){
        rabbitTemplate.convertAndSend(exchange,routingkey,student);
    }

}
