<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
<script type="text/javascript" src="js/index.js"></script>

</head>
<body>

	<div id="box">
		<%@ include file="header.jsp"%>
		<div id="index_home">
			<div id="menu">
				<%@ include file="left.jsp"%>
			</div>
			<!-- 站点数据统计 -->
		</div>
	</div>
	<div style="padding-top:100px;margin-bottom:20px;margin-left:260px;text-align:center">
	<form action="AddChildEnterInformationServlet2">
		<a style="color:green;font-size:25px">幼儿信息</a><br>
		报名电话：<input type="text" name="userNumber"><br>
		宝宝姓名：<input type="text" name="babyName"><br>
		出生日期：<input type="text" name="babyBirthday" placeholder="xxxx年xx月xx日"><br>
		宝宝性别：&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" name="babySex" value="男">男
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="radio" name="babySex" value="女">女
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
		身份证号：<input type="text" name="babyIDnumber"><br>
		过敏食物：<input type="text" name="babyAddoAllergies"><br><br>
		<a style="color:green;font-size:25px">家长信息1</a><br>
		家长姓名：<input type="text" name="parentName1"><br>
		与宝宝关系：<input type="text" name="relation1">&nbsp;&nbsp;&nbsp;<br>
		身份证号：<input type="text" name="parentIDnumber1"><br>
		联系方式：<input type="text" name="phoneNumber1"><br>
		工作单位：<input type="text" name="workSpace1"><br>
		家庭住址：<input type="text" name="homeAddress1"><br><br>
		<a style="color:green;font-size:25px">家长信息2(选填)</a><br>
		家长姓名：<input type="text" name="parentName2"><br>
		与宝宝关系：<input type="text" name="relation2">&nbsp;&nbsp;&nbsp;<br>
		身份证号：<input type="text" name="parentIDnumber2"><br>
		联系方式：<input type="text" name="phoneNumber2"><br>
		工作单位：<input type="text" name="workSpace2"><br>
		家庭住址：<input type="text" name="homeAddress2"><br><br>
		<input type="submit" value="提交" id="submitSearch">
	</form>
	</div>
</body>
</html>