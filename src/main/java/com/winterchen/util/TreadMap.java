package com.winterchen.util;

import com.winterchen.model.ResultMessage;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.HashMap;
import java.util.Map;

public class TreadMap {

    private static Map<String,ClientThread> threadMap=new HashMap<>();

    public static void startConsumerThread(String topic,ClientThread thread){
        thread.start();
        threadMap.put(topic,thread);
    }

    public static void stopConsumerThread(String topic) throws MqttException {
        if(threadMap.containsKey(topic)){
            ClientThread clientThread = threadMap.get(topic);
            clientThread.close();
            threadMap.remove(topic);
        }
    }

}
