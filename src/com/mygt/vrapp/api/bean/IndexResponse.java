package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class IndexResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private String status;

    private IndexInfo response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public IndexInfo getResponse() {
        return response;
    }

    public void setResponse(IndexInfo response) {
        this.response = response;
    }
}
