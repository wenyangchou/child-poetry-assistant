package org.example;

import java.util.Arrays;
import java.util.List;

public class LLMRequestBody {

    private String model;
    private Double temperature;
    private Double top_p;
    private Integer n;
    private Integer max_tokens;
    private Boolean stream;
    private List<String> stop;
    private List<Message> messages;


    public LLMRequestBody() {
        this.model = "Qwen";
        this.temperature = 0.0;
        this.top_p = 1.0;
        this.n = 1;
        this.max_tokens = 512;
        this.stream = false;
        this.stop = Arrays.asList("<|endoftext|>", "<|im_end|>");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getTop_p() {
        return top_p;
    }

    public void setTop_p(Double top_p) {
        this.top_p = top_p;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public Integer getMax_tokens() {
        return max_tokens;
    }

    public void setMax_tokens(Integer max_tokens) {
        this.max_tokens = max_tokens;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }

    public List<String> getStop() {
        return stop;
    }

    public void setStop(List<String> stop) {
        this.stop = stop;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
