package org.example;

import com.alibaba.fastjson.JSON;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import okhttp3.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class chatTTS {

    public static final String LLM_URL = "http://10.13.66.12:19000/v1/chat/completions";
    public static final String TTS_URL = "http://10.13.66.12:7870/v1/audio/speech";

    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS) // 连接超时
            .readTimeout(60, TimeUnit.SECONDS)    // 读取超时
            .writeTimeout(60, TimeUnit.SECONDS)   // 写入超时
            .build();


    public static void main(String[] args) {

        JFrame frame = new JFrame( "儿童诗歌小助手");
        frame.setContentPane(new chatTTS().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize( 600,800);//
        frame.pack();
        frame.setVisible(true);

    }
    public chatTTS() {
        Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = textField1.getText();

                textField1.setText("思考中，请稍等");


                String output = sendHttpRequest(input);
 //               String output = TmpLLMRequest.sendMessage(input);
                playTTs(output);

                textField1.setText(null);
            }
        });
    }

    private String sendHttpRequest(String inputText) {

        // 构造JSON数据
        LLMRequestBody requestBody = new LLMRequestBody();
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("system", "你是一个3岁儿童的早教助手，要求根据用户输入创造一首儿诗，要求儿诗内容简单易懂，语言优美，富有想象力，且只有4句，每句尽量简短。"));
        messages.add(new Message("user", inputText));
        requestBody.setMessages(messages);

        String jsonBody = JSON.toJSONString(requestBody);

        // 设置请求体的媒体类型
        MediaType mediaType = MediaType.parse("application/json");

        System.out.println(jsonBody);

        // 创建请求体
        RequestBody body = RequestBody.create( mediaType,jsonBody);

        // 创建请求对象
        Request request = new Request.Builder()
                .url(LLM_URL)  // 替换成实际的URL
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        // 发送请求并处理响应
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // 处理成功响应
                String responseBody = response.body().string();
                LLMResponse res = JSON.parseObject(responseBody, LLMResponse.class);
                return res.getChoices().get(0).getMessage().get("content");


            } else {
                // 处理失败响应
                System.out.println("请求失败. Code: " + response.code() + ", Message: " + response.message());
            }
        } catch (IOException e) {
            // 处理异常情况
            System.out.println("请求失败. Exception: " + e.getMessage());
        }
        return "不好意思，我没听懂，再说一遍";
    }


    private static void playTTs(String input){
        TTSRequestBody requestBody = new TTSRequestBody();
        requestBody.setInput(input);
        String jsonBody = JSON.toJSONString(requestBody);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create( mediaType,jsonBody);
        Request request = new Request.Builder()
                .url(TTS_URL)
                .post(body)
                .build();

        // 发送请求并处理响应
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }


            // 保存响应的音频文件
            try (FileOutputStream fos = new FileOutputStream("/tmp/speech.mp3")) {
                fos.write(response.body().bytes());
            }

            try (FileInputStream fis = new FileInputStream("/tmp/speech.mp3")) {
                AdvancedPlayer player = new AdvancedPlayer(fis);
                player.play();
            } catch (JavaLayerException e) {
                throw new RuntimeException(e);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JTextField textField1;
    private JPanel panel1;
    private JButton Button;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
