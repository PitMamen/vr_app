package com.mygt.vrapp.database;

import android.os.Parcel;
import android.os.Parcelable;

import com.mygt.vrapp.api.bean.VideoVo;

/**
 * Created on 2017/5/20.
 *
 * @author lkuan
 */

public class VRDbVideo extends VideoVo implements Parcelable {

    private String imagePrefix;

    public boolean isSelect;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getImagePrefix() {
        return imagePrefix;
    }

    public void setImagePrefix(String imagePrefix) {
        this.imagePrefix = imagePrefix;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imagePrefix);
    }

    public VRDbVideo() {
    }

    protected VRDbVideo(Parcel in) {
        this.imagePrefix = in.readString();
    }

    public static final Parcelable.Creator<VRDbVideo> CREATOR = new Parcelable.Creator<VRDbVideo>() {
        @Override
        public VRDbVideo createFromParcel(Parcel source) {
            return new VRDbVideo(source);
        }

        @Override
        public VRDbVideo[] newArray(int size) {
            return new VRDbVideo[size];
        }
    };
}
