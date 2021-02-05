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
				<ul class="menu">
					<li id="user" onclick="showUserMenu()"><a>用户信息管理</a> <img
						src="imgs/home/xiala.png" id="xiala1"></li>
					<ul class="user_menu" id="click_user_menu" style="display: none">
						<li onclick="showMenu_info1()" id="user_info"><a href="#">用户信息</a></li>
						<li onclick="showMenu_add1()" id="user_add"><a href="#">新增用户</a></li>
					</ul>
					<li id="enroll" onclick="showEnrollMenu()"><a>报名信息管理</a><img
						src="imgs/home/xiala.png" id="xiala2"></li>
					<ul class="enroll_menu" id="click_enroll_menu"
						style="display: block">
						<li onclick="showMenu_info2()" id="enroll_info"
							style="background: #009688"><a href="GetApplyInfoServlet">查看报名信息</a></li>
						<li onclick="showMenu_manage2()" id="enroll_info_manage"><a
							href="GetApplyInfoServlet2">管理报名信息</a></li>
						<li onclick="showMenu_add2()" id="enroll_info_add"><a
							href="addApplyinfo.jsp">新增报名信息</a></li>
					</ul>
					<li id="introduce" onclick="showIntroduceMenu()"><a>管理学校简介信息</a>
						<img src="imgs/home/xiala.png" id="xiala3"></li>
					<ul class="introduce_menu" id="click_introduce_menu"
						style="display: none">
						<li onclick="showMenu_info3()" id="introduce_info">
						<a href="environmentDescription.jsp">学校简介</a></li>
						<li onclick="showMenu_add3()" id="introduce_add"><a
							href="addEnvironmentDescription.jsp">新增学校信息</a></li>
					</ul>
					<li id="teacher" onclick="showTeacherMenu()"><a>教师信息管理</a><img
						src="imgs/home/xiala.png" id="xiala4"></li>
					<ul class="teacher_menu" id="click_teacher_menu"
						style="display: none">
                        <li onclick="showMenu_info4()" id="teacher_info"><a href="teacherManage">查看教师信息</a></li>
                        <li onclick="showMenu_add4()" id="teacher_add"><a href="addTeacher.jsp">新增教师信息</a></li>
                        <li onclick="showMenu_info5()" id="teacher_add"><a href="deletedTeacherManage">查看离职教师信息</a></li>
					</ul>
				</ul>
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