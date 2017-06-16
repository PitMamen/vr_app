package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * Created on 2017/5/6.
 *
 * @author lkuan
 */

public class GameUrlResponse implements Serializable{
    private static final long serialVersionUID = 1L;

    private String status;
    private Url response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Url getResponse() {
        return response;
    }

    public void setResponse(Url response) {
        this.response = response;
    }

    public class Url implements Serializable{
        private static final long serialVersionUID = 1L;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
