package com.hgx.activemq.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;
import javax.validation.Valid;

@Component
@EnableJms
public class ConfigBean {

    @Value("${myQueue}")
    private String myQueue ;

    @Value("${myTopic}")
    private String myTopic;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myQueue) ;
    }

    @Bean
    public Topic topic(){
        return new ActiveMQTopic(myTopic) ;
    }




}
