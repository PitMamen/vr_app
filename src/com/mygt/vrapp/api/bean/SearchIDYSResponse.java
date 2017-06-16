package com.mygt.vrapp.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/5/6.
 *
 * @author lkuan
 */

public class SearchIDYSResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;
    private YSResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public YSResponse getResponse() {
        return response;
    }

    public void setResponse(YSResponse response) {
        this.response = response;
    }

    public class YSResponse implements Serializable {
        private static final long serialVersionUID = 1L;
        private VideoVo video;
        private String IMAGE_URL_PREFIX;
        private List<VideoVo> choice_video;

        public VideoVo getVideo() {
            return video;
        }

        public void setVideo(VideoVo video) {
            this.video = video;
        }

        public String getIMAGE_URL_PREFIX() {
            return IMAGE_URL_PREFIX;
        }

        public void setIMAGE_URL_PREFIX(String IMAGE_URL_PREFIX) {
            this.IMAGE_URL_PREFIX = IMAGE_URL_PREFIX;
        }

        public List<VideoVo> getChoice_video() {
            return choice_video;
        }

        public void setChoice_video(List<VideoVo> choice_video) {
            this.choice_video = choice_video;
        }
    }
}
