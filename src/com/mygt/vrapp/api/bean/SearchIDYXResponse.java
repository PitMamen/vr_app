package com.mygt.vrapp.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/5/6.
 *
 * @author lkuan
 */

public class SearchIDYXResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String status;
    private YXResponse response;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public YXResponse getResponse() {
        return response;
    }

    public void setResponse(YXResponse response) {
        this.response = response;
    }

    public class YXResponse implements Serializable {
        private static final long serialVersionUID = 1L;
        private GameVo game;
        private String IMAGE_URL_PREFIX;
        private List<GameVo> choice_game;

        public GameVo getGame() {
            return game;
        }

        public void setGame(GameVo game) {
            this.game = game;
        }

        public String getIMAGE_URL_PREFIX() {
            return IMAGE_URL_PREFIX;
        }

        public void setIMAGE_URL_PREFIX(String IMAGE_URL_PREFIX) {
            this.IMAGE_URL_PREFIX = IMAGE_URL_PREFIX;
        }

        public List<GameVo> getChoice_game() {
            return choice_game;
        }

        public void setChoice_game(List<GameVo> choice_game) {
            this.choice_game = choice_game;
        }
    }
}
