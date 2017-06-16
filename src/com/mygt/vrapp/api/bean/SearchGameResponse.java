package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * Created on 2017/5/6.
 *
 * @author lkuan
 */

public class SearchGameResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;
    private GameInfoResponse.GameInfo response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GameInfoResponse.GameInfo getResponse() {
        return response;
    }

    public void setResponse(GameInfoResponse.GameInfo response) {
        this.response = response;
    }
}
