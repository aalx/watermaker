<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String pdfPath=request.getParameter("path");
String waterMark=request.getParameter("waterMark");
String roles=request.getParameter("waterMark");
%>
<html>
<head>
<title>readpdf</title>
<script>
		function GetQueryString(name) {
			  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
			  //   console.log(window.decodeURIComponent(decodeURI(window.location.search)));
			  var r = window.decodeURIComponent(decodeURI(window.location.search)).substr(1).match(reg);
			  if (r!=null) return (r[2]); return null;
			}
</script>
<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn,
	em, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var,
	b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend,
	table, caption, tbody, tfoot, thead, tr, th, td, article, aside, canvas,
	details, embed, figure, figcaption, footer, header, hgroup, menu, nav,
	output, ruby, section, summary, time, mark, audio, video {
	margin: 0;
	padding: 0;
	border: 0;
	font-size: 100%;
	font: inherit;
	vertical-align: baseline;
}
</style>
</head>
<body>
	<iframe id='pdf'
		src="<%=request.getContextPath() %>/pdfjs/web/viewer.jsp?file=<%=request.getContextPath() %>/out_put_file.jsp?path=<%=pdfPath%>"
		width="100%" height="100%"></iframe>
</body>
</html>