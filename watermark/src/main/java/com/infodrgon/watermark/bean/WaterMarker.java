package com.infodrgon.watermark.bean;

public abstract class WaterMarker {
	/**
	 * 旋转角度<br>
	 * 如果是图片时则是图片的旋转弧度
	 */
	private int rotation ;
	
	private float fillOpacity;
	
	private float strokingOpacity;
	
	private float x;
	
	private float y;
	/**
	 * 是否重复铺满
	 */
	private boolean repeat ;
	
	
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
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}

}
