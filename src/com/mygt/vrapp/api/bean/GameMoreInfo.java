package com.mygt.vrapp.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Richie on 2017/5/15.
 */

public class GameMoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<GameVo> data;
    private String IMAGE_URL_PREFIX;

    public List<GameVo> getData() {
        return data;
    }

    public void setData(List<GameVo> data) {
        this.data = data;
    }

    public String getIMAGE_URL_PREFIX() {
        return IMAGE_URL_PREFIX;
    }

    public void setIMAGE_URL_PREFIX(String IMAGE_URL_PREFIX) {
        this.IMAGE_URL_PREFIX = IMAGE_URL_PREFIX;
    }
}
