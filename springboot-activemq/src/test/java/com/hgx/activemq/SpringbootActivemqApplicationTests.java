package com.hgx.activemq;

import com.hgx.activemq.produce.QueueProduce;
import com.hgx.activemq.produce.TopicProduce;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = SpringbootActivemqApplication.class)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class SpringbootActivemqApplicationTests {

    @Autowired
    private QueueProduce queueProduce ;
    @Autowired
    private TopicProduce topicProduce ;

    @Test
    public void testSend(){
        queueProduce.produceMsg();
    }

    @Test
    public void testSendSch(){
        queueProduce.produceMSgScheduled();
    }

    @Test
    public void testTopicSend(){
        topicProduce.produceMsg();
    }

    @Test
    public void testTopicSendSch(){
        topicProduce.produceMSgScheduled();
    }
}
