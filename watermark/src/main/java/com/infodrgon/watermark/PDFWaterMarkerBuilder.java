package com.infodrgon.watermark;

import java.awt.FontMetrics;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

import com.infodrgon.watermark.bean.ImageWaterMarker;
import com.infodrgon.watermark.bean.TextWaterMarker;
import com.infodrgon.watermark.bean.WaterMarker;
import com.infodrgon.watermark.exception.IDWaterMarkerException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PDFWaterMarkerBuilder {
	private List<WaterMarker> waterMarkers;
	private InputStream pdfIn;
	private OutputStream pdfOut;
	private PdfReader reader;
	private PdfStamper stamp ;
	private PdfContentByte under;
	private static BaseFont defaultBaseFont;
	static {
		try {
			defaultBaseFont=BaseFont.createFont(getChineseFont(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public PDFWaterMarkerBuilder pdfIn(InputStream pdfIn)  {
		this.pdfIn=pdfIn;
		return this;
	}
	public PDFWaterMarkerBuilder pdfOut(OutputStream pdfOut)  {
		this.pdfOut=pdfOut;
		return this;
	}
	public PDFWaterMarkerBuilder addWaterMarker(WaterMarker waterMarker) {
		if(this.waterMarkers==null) {
			this.waterMarkers=new ArrayList<WaterMarker>();
		}
		this.waterMarkers.add(waterMarker);
		return this;
	}
	public PDFWaterMarkerBuilder addWaterMarkers(List<WaterMarker> waterMarkers) {
		if(this.waterMarkers==null) {
			this.waterMarkers=new ArrayList<WaterMarker>();
		}
		this.waterMarkers.addAll(waterMarkers);
		return this;
	}
	private static int interval = -5;



	public  void build() throws Exception {
		if(this.waterMarkers==null||waterMarkers.size()==0) {
			throw new IDWaterMarkerException("必须构造一个水印并添加");
		}
		this.reader = new PdfReader(this.pdfIn, "PDF".getBytes());
		this.stamp = new PdfStamper(reader, this.pdfOut);
		for(WaterMarker wm:waterMarkers) {
			if(wm instanceof TextWaterMarker) {
				this.mixTextWaterMarker((TextWaterMarker)wm);
			}else if(wm instanceof ImageWaterMarker) {
				this.mixImagMark((ImageWaterMarker)wm);
			}else {
				throw new IDWaterMarkerException("不支持的水印类型--->"+wm.getClass().getSimpleName());
			}
		}
		stamp.close();
	}


	//	private void mixTextWaterMarker(TextWaterMarker twm) throws Exception{
	//		String textMark=twm.getText();
	//		BaseFont font =twm.getBaseFont()==null?defaultBaseFont:twm.getBaseFont();
	//		Font f = new Font(font,twm.getFontSize());
	//		PdfGState gs = new PdfGState();
	//		gs.setFillOpacity(twm.getFillOpacity());
	//		gs.setStrokeOpacity(twm.getStrokingOpacity());
	//		int pageSize = reader.getNumberOfPages();// 原pdf文件的总页数
	//		for(int i = 1; i <= pageSize; i++) {
	//			under = stamp.getUnderContent(i);// 水印在之前文本下
	//			//			under = stamp.getOverContent(i);//水印在之前文本上
	//			under.saveState();
	//			under.setGState(gs);
	//			under.setFontAndSize(font, twm.getFontSize());
	//			under.setColorFill(twm.getFontColor());// 文字水印 颜色
	//			under.beginText();
	////			under.setTextMatrix(twm.getWidth(), twm.getHeight());// 文字水印 起始位置
	//			//			ColumnText.showTextAligned(under, Element.ALIGN_CENTER, p, twm.getWidth(), twm.getHeight(), twm.getRotation());
	//			//			under.restoreState();
	//			under.showTextAligned(Element.ALIGN_CENTER, textMark, twm.getWidth(), twm.getHeight(), twm.getRotation());
	//		}
	//	}

	private  void mixTextWaterMarker(TextWaterMarker twm) throws Exception {
		String markText=twm.getText();
		BaseFont font =twm.getBaseFont()==null?defaultBaseFont:twm.getBaseFont(); //BaseFont.createFont(twm.getFontPath()+",1", "UniGB-UCS2-H",BaseFont.EMBEDDED);//设置字体
		Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		gs.setFillOpacity(twm.getFillOpacity());
		gs.setStrokeOpacity(twm.getStrokingOpacity());
		int total = reader.getNumberOfPages() + 1;
		JLabel label = new JLabel();
		FontMetrics metrics;
		int textH = 0;
		int textW = 0;
		label.setText(markText);
		metrics = label.getFontMetrics(label.getFont());
		textH = metrics.getHeight();
		textW = metrics.stringWidth(label.getText());
		for (int i = 1; i < total; i++) {
			pageRect = reader.getPageSizeWithRotation(i);
			under = stamp.getOverContent(i);
			under.saveState();
			under.setGState(gs);
			under.beginText();
			under.setFontAndSize(font, twm.getFontSize());
			under.setColorFill(twm.getFontColor());// 文字水印 颜色
			// 水印文字成30度角倾斜  
			//你可以随心所欲的改你自己想要的角度
			if(twm.isRepeat()) {
				for (int height = interval + textH; height < pageRect.getHeight();height = height + textH * 3) {
					for (int width = interval + textW; width < pageRect.getWidth() + textW; width = width + textW * 2) {
						under.showTextAligned(Element.ALIGN_LEFT, markText, width - textW, height - textH, twm.getRotation());
					}
				}
			}else {
				under.showTextAligned(Element.ALIGN_CENTER, markText, twm.getX(),twm.getY(), twm.getRotation());
			}
			under.endText();  
		}
	}



	private  void mixImagMark(ImageWaterMarker iwm) throws  Exception{
		PdfGState gs1 = new PdfGState();
		gs1.setFillOpacity(iwm.getFillOpacity());// 透明度设置
		gs1.setStrokeOpacity(iwm.getStrokingOpacity());
		Image img = Image.getInstance(iwm.getImagePath()); // 插入图片水印
		img.setAbsolutePosition(iwm.getX(), iwm.getY()); // 坐标
		img.setRotation(iwm.getRotation());// 旋转 弧度
		img.setRotationDegrees(iwm.getRotationDegree());// 旋转 角度
		// img.scaleAbsolute(200,100);//自定义大小
		img.scalePercent(iwm.getScalePercent());//依照比例缩放
		int total = reader.getNumberOfPages() + 1;
		int textH = (int) img.getWidth();
		int textW = (int) img.getHeight();
		for (int i = 1; i < total; i++) {
			Rectangle pageRect =  reader.getPageSizeWithRotation(i);
			under = stamp.getOverContent(i);
			under.saveState();
			under.setGState(gs1);
			under.beginText();
			// 水印文字成30度角倾斜  
			//你可以随心所欲的改你自己想要的角度
			if(iwm.isRepeat()) {
				for (int height = interval + textH; height < pageRect.getHeight();height = height + textH * 3) {
					for (int width = interval + textW; width < pageRect.getWidth() + textW; width = width + textW * 2) {
						img.setAbsolutePosition(width - textW, height - textH);//坐标  
						under.addImage(img);
					}
				}
			}else {
				img.setAbsolutePosition(iwm.getX(), iwm.getY());
				under.addImage(img);
			}
			under.endText();  
		}
	}
	private static String getChineseFont(){
		//黑体--在windows下
		String font ="C:/Windows/Fonts/simsun.ttc";

		//判断系统类型，加载字体文件
		java.util.Properties prop = System.getProperties();
		String osName = prop.getProperty("os.name").toLowerCase();
		// System.out.println(osName);
		if (osName.indexOf("linux")>-1) {
			font="/usr/share/fonts/simsun.ttc";
		}
		if(!new File(font).exists()){
			throw new RuntimeException("字体文件不存在,影响导出pdf中文显示！"+font);
		}
		return font+",1";
	}
}
