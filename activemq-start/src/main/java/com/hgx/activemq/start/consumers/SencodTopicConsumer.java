package com.hgx.activemq.start.consumers;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class SencodTopicConsumer {
    private static final String url = "tcp://106.14.217.80:61616";

    public static void main(String[] args) throws JMSException, IOException {
        //1.创建连接工厂，按照给定的URL地址，使用默认的用户和密码
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //2.通过连接工厂获取connection并访问
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.创建会话session
        //两个参数，第一参数：事务，第二个参数：签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地，具体是队列Queue还是主题topic
        Topic topic01 = session.createTopic("topic01");
        //5.创建消费者
        MessageConsumer messageConsumer = session.createConsumer(topic01);
        messageConsumer.setMessageListener(message->{
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println(text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();

        //关闭资源
        messageConsumer.close();
        session.close();
        connection.close();


    }

}
