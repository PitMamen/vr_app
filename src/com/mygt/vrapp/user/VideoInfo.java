package com.mygt.vrapp.user;

import com.mygt.vrapp.util.MemoryUtil;

import android.graphics.Bitmap;

public class VideoInfo {

	private String displayName;
	
	private String path;
	
	private Bitmap bitmap;
	
	private long size;
	
	public String memory;
	
	private boolean isSelect;

	public VideoInfo() {
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getMemory() {
		if(memory == null) {
			memory = MemoryUtil.memorySizeChange(getSize());
		}
		return memory;
	}
	
}
