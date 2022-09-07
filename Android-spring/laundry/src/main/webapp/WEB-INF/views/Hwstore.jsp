<%@page import="org.springframework.format.annotation.DateTimeFormat"%>
<%@page import="com.google.gson.GsonBuilder"%>
<%@page import="com.google.gson.annotations.JsonAdapter"%>
<%@page import="com.hanul.laundry.dto.*" %>
<%@page import="com.google.gson.Gson" %>
<%@page import="com.google.gson.JsonObject" %>
<%@page import="org.springframework.ui.Model" %>
<%@page import="java.sql.* , javax.sql.*, javax.naming.*, java.util.*, java.io.PrintWriter" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 

	
 	//Object state = request.getAttribute("Hwlist");
	Gson gson = new Gson();

	String json = gson.toJson((ArrayList<HwDTO4>)request.getAttribute("Hwstore"));
	
	// 클라이언트에게 응답
	out.println(json);	 	

	
%>
