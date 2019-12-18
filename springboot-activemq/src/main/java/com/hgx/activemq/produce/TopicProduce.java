package com.hgx.activemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

@Component
public class TopicProduce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(topic, "topic" + UUID.randomUUID().toString());
    }

    /**
     * 3秒一次
     */
//    @Scheduled(fixedDelay = 3000)
    public void produceMSgScheduled() {

        jmsMessagingTemplate.convertAndSend(topic, "produceMSgScheduled topic" + UUID.randomUUID().toString());

        System.out.println("produceMSgScheduled topic.................");

    }

}
