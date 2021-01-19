<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>AddTeacher</title>
<link rel="stylesheet" type="text/css" href="css/editTeacher.css">
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
	<div id="box">
		<%@ include file="header.jsp"%>
		<div id="index_home">
			<div id="menu">
				<ul class="menu">
					<li id="user" onclick="showUserMenu()"><a class="jump">用户信息管理</a>
						<img src="imgs/home/xiala.png" id="xiala1"></li>
					<ul class="user_menu" id="click_user_menu" style="display: none">
						<li onclick="showMenu_info1()" id="user_info"><a class="jump"
							href="#">用户信息</a></li>
						<li onclick="showMenu_add1()" id="user_add"><a class="jump"
							href="#">新增用户</a></li>
					</ul>
					<li id="enroll" onclick="showEnrollMenu()"><a class="jump">报名信息管理</a><img
						src="imgs/home/xiala.png" id="xiala2"></li>
					<ul class="enroll_menu" id="click_enroll_menu"
						style="display: none">
						<li onclick="showMenu_info2()" id="enroll_info"><a
							class="jump" href="#">查看报名信息</a></li>
						<li onclick="showMenu_manage2()" id="enroll_info_manage"><a
							class="jump" href="#">管理报名信息</a></li>
						<li onclick="showMenu_add2()" id="enroll_info_add"><a
							class="jump" href="#">新增报名信息</a></li>
					</ul>
					<li id="introduce" onclick="showIntroduceMenu()"><a
						class="jump">管理学校简介信息</a> <img src="imgs/home/xiala.png"
						id="xiala3"></li>
					<ul class="introduce_menu" id="click_introduce_menu"
						style="display: none">
						<li onclick="showMenu_info3()" id="introduce_info"><a
							class="jump" href="environmentDescription.jsp">学校简介</a></li>
						<li onclick="showMenu_add3()" id="introduce_add"><a
							class="jump" href="addEnvironmentDescription.jsp">新增学校信息</a></li>
					</ul>
					<li id="teacher" onclick="showTeacherMenu()"><a class="jump">教师信息管理</a><img
						src="imgs/home/xiala.png" id="xiala4"></li>
					<ul class="teacher_menu" id="click_teacher_menu"
						style="display: none">
						<li onclick="showMenu_info4()" id="teacher_info"><a
							class="jump" href="teacherManage">查看教师信息</a></li>
						<li onclick="showMenu_add4()" id="teacher_add"><a
							class="jump" href="addTeacher.jsp">新增教师信息</a></li>
					</ul>
				</ul>
			</div>
			<div class="editBox1">
				<div class="addBox2">
					<p id="addTitle">Add Teacher Info</p>
					<form action="addTeacherManage" method="post"
						enctype="multipart/form-data" style="margin-top: 30px">
						<input type="text" name="teacherName" placeholder="教师姓名"
							class="addInput" /> <br />
						<br /> <input type="text" name="teacherPosition"
							placeholder="教师职位" class="addInput" /> <br />
						<br /> <input type="text" name="teacherPhone" placeholder="联系方式"
							class="addInput" /> <br />
						<div class="picBox">
							<div class="chooseBox">
								<input type="file" name="teacherPicture" class="chooseFile" />
							</div>
						</div>
						<br /> <input type="text" name="teacherMotto" placeholder="座右铭"
							class="addInput" /> <br />
						<br /> <input type="submit" value="提交" id="submitAddInfo" />
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>