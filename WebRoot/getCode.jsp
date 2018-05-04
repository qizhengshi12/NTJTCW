<%@ page contentType="image/jpeg" import="com.database.Utils.*"%>

<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);

RandImgCreater rc = new RandImgCreater(response,request);
rc.createRandImage();
//RandImgCreater rc = new RandImgCreater(response,8,"abcdef");
//rc.setBgColor(100,100,100);

%>
