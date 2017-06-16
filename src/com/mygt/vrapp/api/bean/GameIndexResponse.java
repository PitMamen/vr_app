package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class GameIndexResponse implements Serializable{
    private static final long serialVersionUID = 1L;

    private String status;

    private GameIndexInfo response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GameIndexInfo getResponse() {
        return response;
    }

    public void setResponse(GameIndexInfo response) {
        this.response = response;
    }
}
