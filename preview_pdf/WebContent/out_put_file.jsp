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
		TextWaterMarkBuilder textWaterMarkBuilder=new TextWaterMarkBuilder();
		TextWaterMarker twm1= textWaterMarkBuilder.width(50).height(150).fontSize(18).rotation(45).fontColor(BaseColor.BLUE).repeat(true).text("XX国土局").build();
		textWaterMarkBuilder.clear();
		TextWaterMarker twm2=textWaterMarkBuilder.width(100).height(200).fontSize(20).rotation(30).fontColor(BaseColor.GRAY).repeat(true).text("InfoDragon").build();
		pdfbuilder.addWaterMarker(twm1);
		pdfbuilder.build();
		IOUtils.write(IOUtils.toByteArray(fileInputStream), outputStream);
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		pageContext.getOut().clear();
	}
%>