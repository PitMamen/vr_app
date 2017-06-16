package com.mygt.vrapp.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class IndexInfo implements Serializable{

    private static final long serialVersionUID = 1L;
    private String imagePrefix;


    private List<AdvertisVo> advertis;
    private List<VideoVo> choiceVideo;
    private List<VideoVo> playVideo;
    private List<GameVo> choiceGame;
    private List<GameVo> downGame;


    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }


    public List<AdvertisVo> getAdvertis() {
        return advertis;
    }

    public void setAdvertis(List<AdvertisVo> advertis) {
        this.advertis = advertis;
    }

    public List<VideoVo> getChoiceVideo() {
        return choiceVideo;
    }

    public void setChoiceVideo(List<VideoVo> choiceVideo) {
        this.choiceVideo = choiceVideo;
    }

    public List<VideoVo> getPlayVideo() {
        return playVideo;
    }

    public void setPlayVideo(List<VideoVo> playVideo) {
        this.playVideo = playVideo;
    }

    public List<GameVo> getChoiceGame() {
        return choiceGame;
    }

    public void setChoiceGame(List<GameVo> choiceGame) {
        this.choiceGame = choiceGame;
    }

    public List<GameVo> getDownGame() {
        return downGame;
    }

    public void setDownGame(List<GameVo> downGame) {
        this.downGame = downGame;
    }
}
