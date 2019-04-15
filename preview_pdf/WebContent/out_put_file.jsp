<%@page import="com.infodrgon.watermark.builder.ImageWaterMarkBuilder"%>
<%@page import="com.infodrgon.watermark.bean.ImageWaterMarker"%>
<%@page import="com.infodrgon.watermark.bean.WaterMarker"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.itextpdf.text.pdf.BaseFont"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.infodrgon.watermark.builder.TextWaterMarkBuilder"%>
<%@page import="com.infodrgon.watermark.bean.TextWaterMarker"%>
<%@page import="com.infodrgon.watermark.PDFWaterMarkerBuilder"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%
	String path = request.getParameter("path");
	String fileName=path.substring(path.lastIndexOf(File.separator)+1);
	System.out.println(path);
	System.out.println(fileName);
	File file = new File(path);
	response.setHeader("Content-Disposition", "attachment;fileName="+fileName);
	response.setContentType("multipart/form-data");
	try (OutputStream outputStream = response.getOutputStream();) {
		FileInputStream fileInputStream = new FileInputStream(file);
		PDFWaterMarkerBuilder pdfbuilder=new PDFWaterMarkerBuilder().pdfIn(fileInputStream).pdfOut(outputStream);
		
		/**
		**文字水印构造
		**/
		TextWaterMarkBuilder textWaterMarkBuilder=new TextWaterMarkBuilder();
		List twms= textWaterMarkBuilder.x(80).y(800).fontSize(25).fillOpacity(0.8f).strokingOpacity(1f).fontColor(BaseColor.BLUE).text("XX国土局").add()
				.fontSize(20).rotation(30).fillOpacity(0.5f).strokingOpacity(0.8f).fontColor(BaseColor.GRAY).repeat(true).text("InfoDragon"+"   "+"1434").add()
				.waterMarkers();
		pdfbuilder.addWaterMarkers(twms);
		
		
		/**
		**图片水印构造
		**/
		String imagePth=request.getRealPath("") +"logo.jpg";
		ImageWaterMarkBuilder imageWaterMarkBuilder=new ImageWaterMarkBuilder();
		List iwms=imageWaterMarkBuilder.x(80).y(5).fillOpacity(0.8f).imagePath(imagePth).rotation(-20).rotationDegree(45).scalePercent(50).add().waterMarkers();
				//.fillOpacity(1f).strokingOpacity(1f).scalePercent(110).imagePath(imagePth).repeat(true).waterMarkers();
		pdfbuilder.addWaterMarkers(iwms);
		
		
		pdfbuilder.build();
		IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		pageContext.getOut().clear();
	}
%>