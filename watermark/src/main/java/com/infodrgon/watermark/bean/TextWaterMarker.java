package com.infodrgon.watermark.bean;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.BaseFont;

public class TextWaterMarker extends WaterMarker{
	/**
	 * 字体路径
	 */
	private BaseFont baseFont;
	/**
	 * 字体大小
	 */
	private int fontSize;
	/**
	 * 字体颜色
	 */
	private BaseColor fontColor;
	/**
	 * 水印内容
	 */
	private String text;
	public BaseFont getBaseFont() {
		return baseFont;
	}
	public void setBaseFont(BaseFont baseFont) {
		this.baseFont = baseFont;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	public BaseColor getFontColor() {
		return fontColor;
	}
	public void setFontColor(BaseColor fontColor) {
		this.fontColor = fontColor;
	}
	public void setText(String text) {
		this.text=text;
	}
	public String getText() {
		return text;
	}
	

}
