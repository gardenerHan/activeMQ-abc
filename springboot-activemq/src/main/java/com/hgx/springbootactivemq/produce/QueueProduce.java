package com.hgx.springbootactivemq.produce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

@Component
public class QueueProduce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void produceMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "kkkkk" + UUID.randomUUID().toString());
    }

    /**
     * 3秒一次
     */
    @Scheduled(fixedDelay = 3000)
    public void produceMSgScheduled() {

        jmsMessagingTemplate.convertAndSend(queue, "produceMSgScheduled" + UUID.randomUUID().toString());

        System.out.println("produceMSgScheduled.................");

    }

}
