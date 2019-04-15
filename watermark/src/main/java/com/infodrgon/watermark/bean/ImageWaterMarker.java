package com.infodrgon.watermark.bean;

public class ImageWaterMarker extends WaterMarker{
	private String imagePath;
	/**
	 * 图片的旋转角度
	 */
	private int rotationDegree;
	
	/**
	 * 缩放比例
	 */
	private float scalePercent;

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

	public float getScalePercent() {
		return scalePercent;
	}

	public void setScalePercent(float scalePercent) {
		this.scalePercent = scalePercent;
	}
	

}
