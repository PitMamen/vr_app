package com.mygt.vrapp.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/5/6.
 *
 * @author lkuan
 */

public class TypeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;
    private TypeResponseInfo response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TypeResponseInfo getResponse() {
        return response;
    }

    public void setResponse(TypeResponseInfo response) {
        this.response = response;
    }

    public class TypeResponseInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        private AdvertisVoData data;
        private String IMAGE_URL_PREFIX;

        public AdvertisVoData getData() {
            return data;
        }

        public void setData(AdvertisVoData data) {
            this.data = data;
        }

        public String getIMAGE_URL_PREFIX() {
            return IMAGE_URL_PREFIX;
        }

        public void setIMAGE_URL_PREFIX(String IMAGE_URL_PREFIX) {
            this.IMAGE_URL_PREFIX = IMAGE_URL_PREFIX;
        }
    }

    public class AdvertisVoData implements Serializable {
        private static final long serialVersionUID = 1L;
        private List<AdvertisVo> data;

        public List<AdvertisVo> getData() {
            return data;
        }

        public void setData(List<AdvertisVo> data) {
            this.data = data;
        }
    }
}
