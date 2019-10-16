package com.hgx.activemq.start.consumers;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class FirstQueueConsumer {
    private static final String url = "tcp://localhost:61616";

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
        Queue queue01 = session.createQueue("queue01");
        //5.创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue01);

        //消费方式1：同步阻塞方式（receive）订阅者或接收者调用MessageConsumer的receive方法来接收，receive方法在接收到消息之前或超时之前将一直阻塞
//        while (true) {
//            Message message = messageConsumer.receive(6000);
//            if (message instanceof TextMessage) {
//                TextMessage textMessage = (TextMessage) message;
//                System.out.println(textMessage.getText());
//            } else {
//                break;
//            }
//
//        }

        //消费方式2：异步非阻塞方式（监听器onMessage()方法）订阅者或接收者通过MessageConsumer的setMessageListener注册监听器，当消息到达之后，系统自动调用监听器MessageListener的onMessage方法
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        String text = textMessage.getText();
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
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
