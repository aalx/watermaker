package com.infodrgon.watermark.builder;

import com.infodrgon.watermark.bean.TextWaterMarker;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.BaseFont;

public class TextWaterMarkBuilder extends BaseWaterMarkBuilder<TextWaterMarkBuilder,TextWaterMarker>{
	/**
	 * 字体文件信息
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
	 * 文字内容
	 */
	private String text;

	public void TextWaterMarkBuilder() {
		this.waterMarkType=1;
	}
	/**
	 * 设置水印的字体文件路径<br>
	 * eg:String fontPath=request.getSession().getServletContext().getRealPath("")+"\\WEB-INF\\fonts\\simsun.ttc";
	 * @param fontPath
	 * @return
	 */
	public TextWaterMarkBuilder baseFont(BaseFont baseFont) {
		this.baseFont=baseFont;
		return this;
	}
	/**
	 * 设置水印文字的字体大小
	 * @param fontSize
	 * @return
	 */
	public TextWaterMarkBuilder fontSize(int fontSize) {
		this.fontSize=fontSize;
		return this;
	}
	/**
	 * 设置水印文字的颜色
	 * @param fontColor
	 * @return
	 */
	public TextWaterMarkBuilder fontColor(BaseColor fontColor) {
		this.fontColor=fontColor;
		return this;
	}
	/**
	 * 设置水印文字的文字内容<br>
	 * eg:最佳建议不要超过50字，会不好看的的
	 * @param fontColor
	 * @return
	 */
	public TextWaterMarkBuilder text(String text) {
		this.text=text;
		return this;
	}

	/**
	 * 复位信息
	 */
	@Override
	public void clear() {
		super.clear();
		this.fontColor=null;
		this.baseFont=null;
		this.fontSize=0;
		this.text=null;
	}

	@Override
	public TextWaterMarker build() {
		TextWaterMarker waterMarker=new TextWaterMarker();
		waterMarker.setWidth(this.width);
		waterMarker.setHeight(this.height);
		waterMarker.setRotation(this.rotation);
		waterMarker.setRepeat(this.repeat);
		waterMarker.setFontColor(this.fontColor);
		waterMarker.setBaseFont(this.baseFont);
		waterMarker.setFontSize(this.fontSize);
		waterMarker.setText(text);
		return waterMarker;
	}

}
