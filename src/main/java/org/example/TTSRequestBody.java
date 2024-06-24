package org.example;

public class TTSRequestBody {

    private String input;
    private String model;
    private String voice;
    private String response_format;
    private Double speed;
    private Integer seed;
    private Double temperature;
    private Integer batch_size;

    public TTSRequestBody() {
        this.model = "chattts-4w";
        this.voice = "female2";
        this.response_format = "mp3";
        this.speed = 0.65;
        this.seed = 0;
        this.temperature = 0.3;
        this.batch_size = 1;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getResponse_format() {
        return response_format;
    }

    public void setResponse_format(String response_format) {
        this.response_format = response_format;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getSeed() {
        return seed;
    }

    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getBatch_size() {
        return batch_size;
    }

    public void setBatch_size(Integer batch_size) {
        this.batch_size = batch_size;
    }
}
