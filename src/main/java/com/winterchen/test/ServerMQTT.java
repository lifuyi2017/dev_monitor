package com.winterchen.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ServerMQTT {


    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
    public static final String HOST = "tcp://106.55.249.130:1883";
    //定义一个主题
    public static final String TOPIC = "lifuyi";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "server11";

    private MqttClient client;
    private static MqttTopic topic11;
//    private String userName = "mqtt";  //非必须
//    private String passWord = "mqtt";  //非必须

    private static MqttMessage message;

    /**
     * 构造函数
     * @throws MqttException
     */
    public ServerMQTT() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    /**
     *  用来连接服务器
     */
    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
//        options.setUserName(userName);
//        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
            client.setCallback(new PushCallback());
            client.connect(options);
            topic11 = client.getTopic(TOPIC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param topic
     * @param message
     * @throws MqttPersistenceException
     * @throws MqttException
     */
    public static void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);

        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }


    public static void sendMessage(String clieId,String msg)throws Exception{
        ServerMQTT server = new ServerMQTT();
        server.message = new MqttMessage();
        server.message.setQos(1);  //保证消息能到达一次
        server.message.setRetained(true);
        String str ="{\"clieId\":\""+clieId+"\",\"mag\":\""+msg+"\"}";
        server.message.setPayload(str.getBytes());
        try{
            publish(server.topic11 , server.message);
            //断开连接
//            server.client.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     *  启动入口
     * @param args
     * @throws MqttException
     */
    public static void main(String[] args) throws Exception {
//        sendMessage("123444","哈哈");
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
        String str="{\"clieId\":\"123444\",\"mag\":\"——————————————————————————————————112哈\"}";
        mqttMessage.setPayload(str.getBytes());
        MqttDeliveryToken publish = topic.publish(mqttMessage);
        publish.waitForCompletion();
        System.out.println("message is published completely! "
                + publish.isComplete());
        mqttClient.disconnect();
    }

}
