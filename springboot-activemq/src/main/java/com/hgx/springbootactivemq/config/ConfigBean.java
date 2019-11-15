package com.hgx.springbootactivemq.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.validation.Valid;

@Component
@EnableJms
public class ConfigBean {

    @Value("${myQueue}")
    private String myQueue ;


    @Bean
    public Queue queue(){
        return new ActiveMQQueue(myQueue) ;
    }




}
