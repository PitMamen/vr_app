package com.mygt.vrapp.api.bean;

import java.io.Serializable;

/**
 * Created on 2017/5/6.
 *
 * @author lkuan
 */

public class GameInfoResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private GameResponse response;
    private String status;

    public GameResponse getResponse() {
        return response;
    }

    public void setResponse(GameResponse response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class GameResponse implements Serializable{
        private static final long serialVersionUID = 1L;

        private String imagePrefix;
        private ResponseGame games;

        public String getImagePrefix() {
            return imagePrefix;
        }

        public void setImagePrefix(String imagePrefix) {
            this.imagePrefix = imagePrefix;
        }

        public ResponseGame getGames() {
            return games;
        }

        public void setGames(ResponseGame games) {
            this.games = games;
        }
    }

    public class ResponseGame implements Serializable{
        private static final long serialVersionUID = 1L;

        private GameInfo response;
        private String status;

        public GameInfo getResponse() {
            return response;
        }

        public void setResponse(GameInfo response) {
            this.response = response;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
    public class GameInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        private GameVo data;

        public GameVo getData() {
            return data;
        }

        public void setData(GameVo data) {
            this.data = data;
        }
    }
}
