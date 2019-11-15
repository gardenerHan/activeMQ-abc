package com.hgx.springbootactivemq;

import com.hgx.springbootactivemq.produce.QueueProduce;
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

    @Test
    public void testSend(){
        queueProduce.produceMsg();
    }

}
