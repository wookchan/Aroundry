<%@page import="com.hanul.laundry.dto.UserDTO" %>

<%@page import="com.google.gson.Gson" %>
<%@page import="com.google.gson.JsonObject" %>

<%@page import="org.springframework.ui.Model" %>
<%@page import="java.sql.* , javax.sql.*, javax.naming.*, java.util.*, java.io.PrintWriter" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
	//Gson gson = new Gson();
	//String json = gson.toJson((UserDTO)request.getAttribute("kangJoin"));
	//out.println(json);
	
	out.println( new Gson().toJson((HashMap<String, Object>)request.getAttribute("kangJoin")) );
	/* 로그인의 경우 : MemberDTO 
		Gson gson = new Gson();
		String json = gson.toJson((MemberDTO)request.getAttribute("anLogin"));
	*/
	
	

%>    
    