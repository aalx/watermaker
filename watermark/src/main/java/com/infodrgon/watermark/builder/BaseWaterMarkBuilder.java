package com.infodrgon.watermark.builder;

public abstract  class  BaseWaterMarkBuilder<T,ValueType> {
	/**
	 * 水印宽度
	 */
	protected int width;
	/**
	 * 水印高度
	 */
	protected int height;
	/**
	 * 旋转角度
	 */
	protected int rotation ;
	/**
	 * 是否重复铺满
	 */
	protected boolean repeat ;

	protected int waterMarkType;
	/**
	 * 设置水印宽度<br>
	 * <br>
	 * <br>
	 */
	public T width(int width) {
		this.width=width;
		return (T)this;
	}
	/**
	 * 设置水印高度<br>
	 * <br>
	 * <br>
	 */
	public T height(int height) {
		this.height=height;
		return (T)this;
	}
	/**
	 * 设置旋转角度，如果是图片则是图片的旋转弧度<br>
	 * <br>
	 * <br>
	 */
	public T rotation(int rotation) {
		this.rotation=rotation;
		return (T)this;
	}
	/**
	 * 设置是否重复铺满<br>
	 * <br>
	 * <br>
	 */
	public T repeat(boolean repeat) {
		this.repeat=repeat;
		return (T)this;
	}
	public abstract ValueType build();
	public void clear() {
		this.width=0;
		this.height=0;
		this.rotation=0;
		this.repeat=false;
	}
}
