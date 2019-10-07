package com.hgx.activemq.acknowledge.consumers;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 在事务开启的情况下，签收参数无所谓，事务提交成功则默认签收，如果不commit，则签收无效，消息会被重复消费到
 */
public class AcknowledgeTransactionQueueConsumer {
    private static final String url = "tcp://106.14.217.80:61616";

    public static void main(String[] args) throws JMSException, IOException {
        //1.创建连接工厂，按照给定的URL地址，使用默认的用户和密码
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        //2.通过连接工厂获取connection并访问
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3.创建会话session
        //两个参数，第一参数：事务，第二个参数：签收
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        //4.创建目的地，具体是队列Queue还是主题topic
        Queue queue01 = session.createQueue("queueAcknowledgeTransaction");
        //5.创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue01);

        //消费方式1：同步阻塞方式（receive）订阅者或接收者调用MessageConsumer的receive方法来接收，receive方法在接收到消息之前或超时之前将一直阻塞
        while (true) {
            Message message = messageConsumer.receive(1000);
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                //手动签收
                //message.acknowledge();
                System.out.println(textMessage.getText());
            } else {
                break;
            }

        }


        //关闭资源
        messageConsumer.close();
        session.commit();
        session.close();
        connection.close();


    }

}
