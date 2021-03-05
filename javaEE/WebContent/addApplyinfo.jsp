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
				<ul class="menu">
					<li id="user" onclick="showUserMenu()"><a>用户信息管理</a> <img
						src="imgs/home/xiala.png" id="xiala1"></li>
					<ul class="user_menu" id="click_user_menu" style="display: none">
						<li onclick="showMenu_info1()" id="user_info"><a
							href="userManage">用户信息</a></li>
					</ul>
					<li id="enroll" onclick="showEnrollMenu()"><a>报名信息管理</a><img
						src="imgs/home/xiala.png" id="xiala2"></li>
					<ul class="enroll_menu" id="click_enroll_menu" style="display: block">
						<li onclick="showMenu_info2()" id="enroll_info"><a
							href="GetApplyInfoServlet">查看报名信息</a></li>
						<li onclick="showMenu_manage2()" id="enroll_info_manage"><a
							href="GetApplyInfoServlet2">管理报名信息</a></li>
						<li onclick="showMenu_add2()" id="enroll_info_add" style="background: #009688"><a
							href="addApplyinfo.jsp">新增报名信息</a></li>
					</ul>
					<li id="enroll" onclick="showEnrollMenu()"><a>学生信息管理</a><img
						src="imgs/home/xiala.png" id="xiala2"></li>
					<ul class="enroll_menu" id="click_enroll_menu" style="display: none">
						<li onclick="showMenu_info2()" id="enroll_info"><a
							href="GetApplyInfoServlet">查看学生信息</a></li>
					</ul>
					<li id="introduce" onclick="showIntroduceMenu()"><a>学校简介信息管理</a>
						<img src="imgs/home/xiala.png" id="xiala3"></li>
					<ul class="introduce_menu" id="click_introduce_menu"
						style="display: none">
						<li onclick="showMenu_info3()" id="introduce_info"><a
							href="DescriptionManageServlet">学校简介</a></li>
						<li onclick="showMenu_add3()" id="introduce_add"><a
							href="addEnvironmentPicture.jsp">新增学校信息</a></li>
					</ul>
					<li id="teacher" onclick="showTeacherMenu()"><a>教师信息管理</a><img
						src="imgs/home/xiala.png" id="xiala4"></li>
					<ul class="teacher_menu" id="click_teacher_menu" style="display: none">
						<li onclick="showMenu_info4()" id="teacher_info"><a
							href="teacherManage">查看教师信息</a></li>
						<li onclick="showMenu_add4()" id="teacher_add"><a
							href="addTeacher.jsp">新增教师信息</a></li>
						<li onclick="showMenu_info4()" id="teacher_delete"><a
							href="deletedTeacherManage">查看离职教师信息</a></li>
					</ul>
					<li id="account" onclick="showAccountMenu()"><a>收费信息管理</a><img
						src="imgs/home/xiala.png" id="xiala5"></li>
					<ul class="account_menu" id="click_account_menu" style="display: none">
						<li onclick="showMenu_info5()" id="account_info"><a
							href="SearchClassTime">查看本学期上课时间</a></li>
						<li onclick="showMenu_info5()" id="account_info"><a
							href="ChargeManagerServlet">查看收款码信息</a></li>
					</ul>
					
					<li id="student" onclick="showStudentMenu()"><a>学生信息管理</a><img
			            src="imgs/home/xiala.png" id="xiala2"></li>
			        <ul class="student_menu" id="click_student_menu" style="display: none">
			            <li onclick="showMenu_info6()" id="student_info"><a
			                href="StudentsManageServlet">查看学生信息</a></li>
			        </ul>
				</ul>
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