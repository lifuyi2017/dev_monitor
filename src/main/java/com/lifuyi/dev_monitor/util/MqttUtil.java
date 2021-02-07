package com.lifuyi.dev_monitor.util;



import com.alibaba.fastjson.JSONArray;
import com.lifuyi.dev_monitor.model.mqtt.CollectConfig;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MqttUtil {

    private static String HOST="tcp://106.55.249.130:1883";
    private static final String clientid = "collectionManager";
    //定义一个主题
    public static final String TOPIC = "instructions";

    public static void  putToMqtt(CollectConfig collectionMqtt) throws Exception {
        MqttClient mqttClient = new MqttClient(HOST, clientid, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
                     mqttClient.setCallback(new PushCallback());
        mqttClient.connect(options);
        MqttTopic topic = mqttClient.getTopic(TOPIC);
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(2);
        mqttMessage.setRetained(true);
        String str=JSONArray.toJSON(collectionMqtt).toString();
        System.out.println("发送消息：==========="+str);
        mqttMessage.setPayload(str.getBytes());
        MqttDeliveryToken publish = topic.publish(mqttMessage);
        publish.waitForCompletion();
        System.out.println("message is published completely! "
                + publish.isComplete());
        mqttClient.disconnect();
    }

    public static boolean equalLists(List<String> one, List<String> two){
        if (one == null && two == null){
            return true;
        }
        if((one == null && two != null)
                || one != null && two == null
                || one.size() != two.size()){
            return false;
        }
        //to avoid messing the order of the lists we will use a copy
        //as noted in comments by A. R. S.
        one = new ArrayList<String>(one);
        two = new ArrayList<String>(two);
        Collections.sort(one);
        Collections.sort(two);
        return one.equals(two);
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("a1");
        list1.add("a2");
        list1.add("b");
        List<String> list2 = new ArrayList<>();
        list2.add("b");
        list2.add("a1");
        list2.add("a2");
        list2.add("a22");
        System.out.println(equalLists(list1,list2));
    }

}
