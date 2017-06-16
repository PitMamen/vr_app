package com.mygt.vrapp.api.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Richie on 2017/5/15.
 */

public class MoreGameResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private MoreGameInfo response;

    public MoreGameInfo getResponse() {
        return response;
    }

    public void setResponse(MoreGameInfo response) {
        this.response = response;
    }

    public class MoreGameInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        private String imagePrefix;
        private List<GameVo> data;

        public String getImagePrefix() {
            return imagePrefix;
        }

        public void setImagePrefix(String imagePrefix) {
            this.imagePrefix = imagePrefix;
        }

        public List<GameVo> getData() {
            return data;
        }

        public void setData(List<GameVo> data) {
            this.data = data;
        }

    }
}
