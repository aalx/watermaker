package com.infodrgon.watermark.builder;

import com.infodrgon.watermark.bean.ImageWaterMarker;
import com.infodrgon.watermark.exception.IDWaterMarkerException;
import com.itextpdf.text.pdf.StringUtils;

public class ImageWaterMarkBuilder extends BaseWaterMarkBuilder<ImageWaterMarkBuilder,ImageWaterMarker>{
	private String imagePath;

	private int rotationDegree;

	private float scalePercent;

	public ImageWaterMarkBuilder imagePath(String imagePath) {
		this.imagePath=imagePath;
		return this;
	}

	public ImageWaterMarkBuilder rotationDegree(int rotationDegree){
		this.rotationDegree=rotationDegree;
		return this;
	}

	public ImageWaterMarkBuilder scalePercent(float scalePercent) {
		this.scalePercent=scalePercent;
		return this;
	}

	@Override
	public ImageWaterMarker build() throws IDWaterMarkerException{
		ImageWaterMarker waterMarker=new ImageWaterMarker();
		if(this.imagePath==null||this.imagePath.length()==0) {
			throw new IDWaterMarkerException("水印图片路径必须指定");
		}
		waterMarker.setRotation(this.rotation);
		waterMarker.setRepeat(this.repeat);
		waterMarker.setFillOpacity(this.fillOpacity);
		waterMarker.setStrokingOpacity(this.strokingOpacity);
		waterMarker.setX(this.x);
		waterMarker.setY(this.y);
		waterMarker.setImagePath(this.imagePath);
		waterMarker.setRotationDegree(this.rotationDegree);
		waterMarker.setScalePercent(this.scalePercent);
		return waterMarker;
	}
	@Override
	public void clear() {
		super.clear();
		this.imagePath=null;
		this.fillOpacity=1.0f;
		this.strokingOpacity=1.0f;
		this.rotationDegree=0;
		this.scalePercent=100f;
	}
}
