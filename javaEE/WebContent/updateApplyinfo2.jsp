<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<%
		String id = request.getParameter("id");
		String userNumber = request.getParameter("userNumber");
		String babyName = request.getParameter("babyName");
		String babyBirthday = request.getParameter("babyBirthday");
		String babySex = request.getParameter("babySex");
		String babyIDnumber = request.getParameter("babyIDnumber");
		String babyAddoAllergies = request.getParameter("babyAddoAllergies");
		String parentName1 = request.getParameter("parentName1");
		String parentName2 = request.getParameter("parentName2");
		String parentIDnumber1 = request.getParameter("parentIDnumber1");
		String parentIDnumber2 = request.getParameter("parentIDnumber2");
		String relation1 = request.getParameter("relation1");
		String relation2 = request.getParameter("relation2");
		String phoneNumber1 = request.getParameter("phoneNumber1");
		String phoneNumber2 = request.getParameter("phoneNumber2");
		String workSpace1 = request.getParameter("workSpace1");
		String workSpace2 = request.getParameter("workSpace2");
		String homeAddress1 = request.getParameter("homeAddress1");
		String homeAddress2 = request.getParameter("homeAddress2");
		String page2 = request.getParameter("page");
		String msg = request.getParameter("msg");
	%>
	<div style="padding-top:100px;margin-bottom:20px;margin-left:260px;text-align:center">
	<form action="UpdateApplyinfoServlet2?page=<%=page2%>&msg=<%=msg%>" method="post">
		<a style="color:green;font-size:25px">幼儿信息</a><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID：<input type="text" name="id" value="<%=id%>" readonly="readonly"><br>
		报名电话：<input type="text" name="userNumber" value="<%=userNumber%>"><br>
		宝宝姓名：<input type="text" name="babyName" value="<%=babyName%>"><br>
		出生日期：<input type="text" name="babyBirthday" value="<%=babyBirthday%>"><br>
		宝宝性别：<input type="text" name="babySex" value="<%=babySex%>"><br>
		身份证号：<input type="text" name="babyIDnumber" value="<%=babyIDnumber%>"><br>
		过敏食物：<input type="text" name="babyAddoAllergies" value="<%=babyAddoAllergies%>"><br><br>
		<a style="color:green;font-size:25px">家长信息1</a><br>
		家长姓名：<input type="text" name="parentName1" value="<%=parentName1%>"><br>
		与宝宝关系：<input type="text" name="relation1" value="<%=relation1%>">&nbsp;&nbsp;&nbsp;<br>
		身份证号：<input type="text" name="parentIDnumber1" value="<%=parentIDnumber1%>"><br>
		联系方式：<input type="text" name="phoneNumber1" value="<%=phoneNumber1%>"><br>
		工作单位：<input type="text" name="workSpace1" value="<%=workSpace1%>"><br>
		家庭住址：<input type="text" name="homeAddress1" value="<%=homeAddress1%>"><br><br>
		<a style="color:green;font-size:25px">家长信息2(选填)</a><br>
		家长姓名：<input type="text" name="parentName2" value="<%=parentName2%>"><br>
		与宝宝关系：<input type="text" name="relation2" value="<%=relation2%>">&nbsp;&nbsp;&nbsp;<br>
		身份证号：<input type="text" name="parentIDnumber2" value="<%=parentIDnumber2%>"><br>
		联系方式：<input type="text" name="phoneNumber2" value="<%=phoneNumber2%>"><br>
		工作单位：<input type="text" name="workSpace2" value="<%=workSpace2%>"><br>
		家庭住址：<input type="text" name="homeAddress2" value="<%=homeAddress2%>"><br><br>
		<input type="submit" value="提交" id="submitSearch">
	</form>
	</div>
</body>
</html>