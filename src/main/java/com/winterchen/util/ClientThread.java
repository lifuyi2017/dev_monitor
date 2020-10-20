package com.winterchen.util;

import com.winterchen.test.PushCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClientThread extends Thread{

    private static  String HOST = "tcp://106.55.249.130:1883";
    private static  String TOPIC1 = "";
    private static  String clientId = "";
    private static MqttClient client;
    private static MqttConnectOptions options;

    public ClientThread(String topic,String clientId) {
        this.TOPIC1=topic;
        this.clientId=clientId;
    }

    public static void main(String[] args) throws InterruptedException, MqttException {
        ClientThread clientThread = new ClientThread("lifuyi", "test");
        clientThread.start();
        Thread.sleep(60000);
        clientThread.close();
//        Thread.sleep(30000);
//        clientThread.interrupt();// 等中断信号量设置后再调用
//        Thread.sleep(3000);
//        System.out.println("Stopping application...");
    }


    @Override
    public void run() {
        try {
            client = new MqttClient(HOST, clientId, new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            options.setAutomaticReconnect(true);
            //这儿替换成写mongoDb
            client.setCallback(new PushCallback());
            MqttTopic topic = client.getTopic(TOPIC1);
            options.setWill(topic, "close".getBytes(), 2, true);
            client.connect(options);
            int[] Qos = {2};//0：最多一次 、1：最少一次 、2：只有一次
            String[] topic1 = {TOPIC1};
            client.subscribe(topic1, Qos);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("任务完成");
        }

/*        while (!Thread.currentThread().isInterrupted()) {
            try {
                client = new MqttClient(HOST, clientId, new MemoryPersistence());
                options = new MqttConnectOptions();
                options.setCleanSession(false);
                options.setConnectionTimeout(10);
                options.setKeepAliveInterval(20);
                options.setAutomaticReconnect(true);
                //这儿替换成写mongoDb
                client.setCallback(new PushCallback());
                MqttTopic topic = client.getTopic(TOPIC1);
                options.setWill(topic, "close".getBytes(), 2, true);
                client.connect(options);
                int[] Qos = {2};//0：最多一次 、1：最少一次 、2：只有一次
                String[] topic1 = {TOPIC1};
                client.subscribe(topic1, Qos);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    client.disconnect();
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Thread exiting under request...");*/
    }


    public void close() throws MqttException {
        if(client!=null){
            client.disconnect();
        }
    }


}
