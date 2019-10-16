package com.hgx.activemq.broker;

import org.apache.activemq.broker.Broker;
import org.apache.activemq.broker.BrokerService;

public class ActiveBrokerServer {

    public static void main(String[] args) throws Exception {
        //activeMQ也支持在VM中通信给予嵌入式的broker
        BrokerService brokerService = new BrokerService() ;
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616") ;
        brokerService.start();
    }
}
