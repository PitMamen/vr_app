package com.mygt.vrapp.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class GameIndexInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<GameVo> games;
    private List<AdvertisVo> advertis;

    public List<GameVo> getGames() {
        return games;
    }

    public void setGames(List<GameVo> games) {
        this.games = games;
    }

    public List<AdvertisVo> getAdvertis() {
        return advertis;
    }

    public void setAdvertis(List<AdvertisVo> advertis) {
        this.advertis = advertis;
    }
}
