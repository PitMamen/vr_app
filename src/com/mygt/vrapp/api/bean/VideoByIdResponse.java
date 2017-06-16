package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class VideoByIdResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String status;
    private SignalUrl response;
//    private String url;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SignalUrl getResponse() {
        return response;
    }

    public void setResponse(SignalUrl response) {
        this.response = response;
    }

//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
}
