package com.hgx.activemq.start.providers;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class FirstQueueProvider {

    private static final String url = "tcp://localhost:61616";

    public static void main(String[] args) throws JMSException {

        //1.创建连接工厂，按照给定的URL地址，使用默认的用户和密码
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //2.通过连接工厂获取connection并访问
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.创建会话session
        //两个参数，第一参数：事务，第二个参数：签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.创建目的地，具体是队列Queue还是主题topic
        Queue queue01 = session.createQueue("queue01");
        //5.创建消息的生产者
        MessageProducer messageProducer = session.createProducer(queue01);
        //6. 通过消息生产者生产消息到MQ
        for (int i = 0; i < 3; i++) {
            //6.1 创建消息
            //文本消息
            TextMessage textMessage = session.createTextMessage("hello activeMQ--msg " + i);
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
