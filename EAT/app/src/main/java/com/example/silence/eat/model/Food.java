package com.example.silence.eat.model;

import java.io.Serializable;

/**
 * Created by silence on 2017/7/12.
 */

public class Food implements Serializable{

    public Food() {

    }

    private int fid;
    private String name ;//菜名
    private String style;//菜系
    private String material;;//主材料
    private String accessories;//辅料
    private String steps;//步骤
    private String path_image;//图片路径
    private String path_audio;//音频路径
    private String path_video;//视频路径
//全部的，包括ID
    public Food(int fid, String name, String style, String material, String accessories, String steps, String path_image, String path_audio, String path_video) {
        this.fid = fid;
        this.name = name;
        this.style = style;
        this.material = material;
        this.accessories = accessories;
        this.steps = steps;
        this.path_image = path_image;
        this.path_audio = path_audio;
        this.path_video = path_video;
    }

    //全部的不包括ID
    public Food( String name, String style, String material, String accessories, String steps, String path_image, String path_audio, String path_video) {
        this.name = name;
        this.style = style;
        this.material = material;
        this.accessories = accessories;
        this.steps = steps;
        this.path_image = path_image;
        this.path_audio = path_audio;
        this.path_video = path_video;
    }

    //菜名、菜系、原料、辅料、步骤 5个
    public Food(String name, String style, String material, String accessories, String steps) {
        this.name = name;
        this.style = style;
        this.material = material;
        this.accessories = accessories;
        this.steps = steps;
    }
    //菜名、菜系、原料、辅料、步骤 5个 +id
    public Food(int fid, String name, String style, String material, String accessories, String steps) {
        this.fid = fid;
        this.name = name;
        this.style = style;
        this.material = material;
        this.accessories = accessories;
        this.steps = steps;
    }

    /*
        重写输出函数
         */
    @Override
    public String toString() {
        return "Food{" +
                "fid=" + fid +
                ", name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", material='" + material + '\'' +
                ", accessories='" + accessories + '\'' +
                ", steps='" + steps + '\'' +
                ", path_image='" + path_image + '\'' +
                ", path_audio='" + path_audio + '\'' +
                ", path_video='" + path_video + '\'' +
                '}';
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getPath_image() {
        return path_image;
    }

    public void setPath_image(String path_image) {
        this.path_image = path_image;
    }

    public String getPath_audio() {
        return path_audio;
    }

    public void setPath_audio(String path_audio) {
        this.path_audio = path_audio;
    }

    public String getPath_video() {
        return path_video;
    }

    public void setPath_video(String path_video) {
        this.path_video = path_video;
    }
}
