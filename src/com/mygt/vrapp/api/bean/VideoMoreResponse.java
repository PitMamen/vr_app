package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class VideoMoreResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;

    private VideoMoreInfo response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VideoMoreInfo getResponse() {
        return response;
    }

    public void setResponse(VideoMoreInfo response) {
        this.response = response;
    }
}
