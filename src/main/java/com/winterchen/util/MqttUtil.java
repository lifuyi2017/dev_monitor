package com.winterchen.util;



import com.alibaba.fastjson.JSONArray;
import com.winterchen.model.CollectionMqtt;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttUtil {

    private static String HOST="tcp://106.55.249.130:1883";
    private static final String clientid = "collectionManager";
    //定义一个主题
    public static final String TOPIC = "lifuyi";

    public static void  putToMqtt(CollectionMqtt collectionMqtt) throws Exception {
        MqttClient mqttClient = new MqttClient(HOST, clientid, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
//        mqttClient.setCallback(new PushCallback());
        mqttClient.connect(options);
        MqttTopic topic = mqttClient.getTopic(TOPIC);
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(1);
        mqttMessage.setRetained(true);

//        String str="{\"clieId\":\"123444\",\"mag\":\"哈哈\"}";
        String str=JSONArray.toJSON(collectionMqtt).toString();
        mqttMessage.setPayload(str.getBytes());
        MqttDeliveryToken publish = topic.publish(mqttMessage);
        publish.waitForCompletion();
        System.out.println("message is published completely! "
                + publish.isComplete());
        mqttClient.disconnect();
    }

    public static void main(String[] args) {
        CollectionMqtt collectionMqtt = new CollectionMqtt();
        collectionMqtt.setChannel_code("jjjjjjjjjjjj");
        String s = JSONArray.toJSON(collectionMqtt).toString();
        System.out.println(s);
    }

}
