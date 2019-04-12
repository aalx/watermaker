package com.infodrgon.watermark.bean;

public class ImageWaterMarker extends WaterMarker{
	private String imagePath;
	/**
	 * 图片的旋转角度
	 */
	private int rotationDegree;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getRotationDegree() {
		return rotationDegree;
	}

	public void setRotationDegree(int rotationDegree) {
		this.rotationDegree = rotationDegree;
	}
	

}
