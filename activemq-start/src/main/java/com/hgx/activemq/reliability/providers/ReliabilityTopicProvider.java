package com.hgx.activemq.reliability.providers;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ReliabilityTopicProvider {

    private static final String url = "tcp://106.14.217.80:61616";

    public static void main(String[] args) throws JMSException {

        //1.创建连接工厂，按照给定的URL地址，使用默认的用户和密码
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //2.通过连接工厂获取connection
        Connection connection = connectionFactory.createConnection();
        //3.创建会话session
        //两个参数，第一参数：事务，第二个参数：签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地，具体是队列Queue还是主题topic
        Topic topic01 = session.createTopic("topicReliability");
        //5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(topic01);
        messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        connection.start();

        //6. 通过消息生产者生产消息到MQ
        for (int i = 0; i < 3; i++) {
            //6.1 创建消息
            //文本消息
            TextMessage textMessage = session.createTextMessage("hello activeMQ--topic Reliability  msg " + i);
            //6.2 发送到MQ
            messageProducer.send(textMessage);
        }

        //7.关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("消息发送成功************");
    }
}
