package com.mygt.vrapp.api.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Richie on 2017/5/15.
 */

public class MoreVideoResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private MoreVideoInfo response;

    public MoreVideoInfo getResponse() {
        return response;
    }

    public void setResponse(MoreVideoInfo response) {
        this.response = response;
    }


    public class MoreVideoInfo implements  Serializable {
        private static final long serialVersionUID = 1L;
        private String imagePrefix;
        private  List<VideoVo> data;

        public String getImagePrefix() {
            return imagePrefix;
        }

        public void setImagePrefix(String imagePrefix) {
            this.imagePrefix = imagePrefix;
        }

        public List<VideoVo> getData() {
            return data;
        }

        public void setData(List<VideoVo> data) {
            this.data = data;
        }
    }
}
