package com.mygt.vrapp.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created on 2017/5/5.
 *
 * @author lkuan
 */

public class MainIndexInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<VideoVo> GQMV;
    private List<VideoVo> KXYL;
    private String imagePrefix;
    private List<VideoVo> YXSP;
    private List<VideoVo> KHJS;
    private List<VideoVo> XGMN;
    private List<VideoVo> WYZY;
    private List<AdvertisVo> advertis;
    private List<VideoVo> JBRW;
    private List<VideoVo> JZMJ;

    public List<VideoVo> getGQMV() {
        return GQMV;
    }

    public void setGQMV(List<VideoVo> GQMV) {
        this.GQMV = GQMV;
    }

    public List<VideoVo> getKXYL() {
        return KXYL;
    }

    public void setKXYL(List<VideoVo> KXYL) {
        this.KXYL = KXYL;
    }

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }

    public List<VideoVo> getYXSP() {
        return YXSP;
    }

    public void setYXSP(List<VideoVo> YXSP) {
        this.YXSP = YXSP;
    }

    public List<VideoVo> getKHJS() {
        return KHJS;
    }

    public void setKHJS(List<VideoVo> KHJS) {
        this.KHJS = KHJS;
    }

    public List<VideoVo> getXGMN() {
        return XGMN;
    }

    public void setXGMN(List<VideoVo> XGMN) {
        this.XGMN = XGMN;
    }

    public List<VideoVo> getWYZY() {
        return WYZY;
    }

    public void setWYZY(List<VideoVo> WYZY) {
        this.WYZY = WYZY;
    }

    public List<AdvertisVo> getAdvertis() {
        return advertis;
    }

    public void setAdvertis(List<AdvertisVo> advertis) {
        this.advertis = advertis;
    }

    public List<VideoVo> getJBRW() {
        return JBRW;
    }

    public void setJBRW(List<VideoVo> JBRW) {
        this.JBRW = JBRW;
    }

    public List<VideoVo> getJZMJ() {
        return JZMJ;
    }

    public void setJZMJ(List<VideoVo> JZMJ) {
        this.JZMJ = JZMJ;
    }
}
