package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class MainIndexResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private String status;

    private MainIndexInfo response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MainIndexInfo getResponse() {
        return response;
    }

    public void setResponse(MainIndexInfo response) {
        this.response = response;
    }
}
