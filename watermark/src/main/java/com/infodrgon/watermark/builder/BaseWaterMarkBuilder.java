package com.infodrgon.watermark.builder;

import java.util.ArrayList;
import java.util.List;

import com.infodrgon.watermark.exception.IDWaterMarkerException;

public abstract  class  BaseWaterMarkBuilder<T,ValueType> {
	private List<ValueType> waterMarkers;
	/**
	 * 旋转角度
	 */
	protected int rotation ;
	/**
	 * 是否重复铺满
	 */
	protected boolean repeat ;


	protected float fillOpacity;

	protected float strokingOpacity;

	protected int waterMarkType;


	protected float x;

	protected float y;
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
		if(repeat) {
			this.x=0f;
			this.y=0f;
		}
		return (T)this;
	}
	/**
	 * 填充不透明度
	 * @param fillOpacity
	 * @return
	 */
	public T fillOpacity(float fillOpacity) {
		this.fillOpacity=fillOpacity;
		return (T)this;
	}
	/**
	 * 边缘不透明度
	 * @param strokingOpacity
	 * @return
	 */
	public T strokingOpacity(float strokingOpacity) {
		this.strokingOpacity=strokingOpacity;
		return (T)this;
	}

	public T x(float x) {
		this.x=x;
		return (T)this;
	}

	public T y(float y) {
		this.y=y;
		return (T)this;
	}

	public T add() throws IDWaterMarkerException{
		ValueType v=this.build();
		if(waterMarkers==null) 
		{
			this.waterMarkers=new ArrayList();
		}
		this.waterMarkers.add(this.build());
		this.clear();
		return (T)this;
	}
	public abstract ValueType build()throws IDWaterMarkerException;
	public void clear() {
		this.rotation=0;
		this.fillOpacity=1.0f;
		this.strokingOpacity=1.0f;
		this.x=0f;
		this.y=0f;
		this.repeat=false;
	}
	
	public List<ValueType> waterMarkers(){
		return this.waterMarkers;
	}
	
}
