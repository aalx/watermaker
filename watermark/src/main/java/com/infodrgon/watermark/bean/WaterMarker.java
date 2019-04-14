package com.infodrgon.watermark.bean;

public abstract class WaterMarker {
	/**
	 * 水印宽度
	 */
	private int width;
	/**
	 * 水印高度
	 */
	private int height;
	
	/**
	 * 旋转角度<br>
	 * 如果是图片时则是图片的旋转弧度
	 */
	private int rotation ;
	
	private float fillOpacity;
	
	private float strokingOpacity;
	/**
	 * 是否重复铺满
	 */
	private boolean repeat ;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isRepeat() {
		return repeat;
	}
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public float getFillOpacity() {
		return fillOpacity;
	}
	public void setFillOpacity(float fillOpacity) {
		this.fillOpacity = fillOpacity;
	}
	public float getStrokingOpacity() {
		return strokingOpacity;
	}
	public void setStrokingOpacity(float strokingOpacity) {
		this.strokingOpacity = strokingOpacity;
	}

}
