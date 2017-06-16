package com.mygt.vrapp.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class VideoMoreInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<VideoVo> data;
    private String IMAGE_URL_PREFIX;

    public String getIMAGE_URL_PREFIX() {
        return IMAGE_URL_PREFIX;
    }

    public void setIMAGE_URL_PREFIX(String IMAGE_URL_PREFIX) {
        this.IMAGE_URL_PREFIX = IMAGE_URL_PREFIX;
    }

    public List<VideoVo> getData() {
        return data;
    }

    public void setData(List<VideoVo> data) {
        this.data = data;
    }
}
