package com.hgx.spring.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class ActiveMQProduce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        ActiveMQProduce activeMQProduce = (ActiveMQProduce) applicationContext.getBean("activeMQProduce");
        activeMQProduce.jmsTemplate.send(session -> {
            return session.createTextMessage("-------spring activeMQ message--------");
        });

        //消费
//        String content = (String) activeMQProduce.jmsTemplate.receiveAndConvert();
        System.out.println("--------spring message send success-----------");
    }
}
