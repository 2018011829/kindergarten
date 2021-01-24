<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/schoolInfo.css" />
<script type="text/javascript" src="js/index.js"></script>
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
						style="display: none">
						<li onclick="showMenu_info2()" id="enroll_info"><a href="#">报名信息</a></li>
					</ul>
					<li id="introduce" onclick="showIntroduceMenu()"><a>学校简介信息管理</a>
						<img src="imgs/home/xiala.png" id="xiala3"></li>
					<ul class="introduce_menu" id="click_introduce_menu"
						style="display: block">
						<li onclick="showMenu_info3()" id="introduce_info"><a
							href="environmentDescription.jsp">学校简介</a></li>
						<li onclick="showMenu_add3()" id="introduce_add"
							style="background: #009688"><a
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
			<div id="schoolInfo">
				<div id="schoolInfoMenu">
				</div>
				<div id="environment">
					<!-- 显示表格 -->
					<div class="addBox2">
						<p id="addTitle">添加基本信息</p>
						<form action="addTeacherManage" method="post"
							enctype="multipart/form-data" style="margin-top: 30px">
							<input type="text" name="id" placeholder="id" class="addInput" />
							<br />
							<br />
							<div class="chooseBox">
								<input type="file" name="teacherPicture" class="chooseFile" />
							</div>
							<br />
							<br /> <input type="text" name="descriptionId" placeholder="地址"
								class="addInput" /> <br />
							<br /> <input type="submit" value="提交" id="submitAddInfo" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>