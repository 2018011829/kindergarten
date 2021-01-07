<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
							href="#">用户信息</a></li>
						<li onclick="showMenu_add1()" id="user_add"><a
							href="#">新增用户</a></li>
					</ul>
					<li id="enroll" onclick="showEnrollMenu()"><a>报名信息管理</a><img
						src="imgs/home/xiala.png" id="xiala2"></li>
					<ul class="enroll_menu" id="click_enroll_menu" style="display: none">
						<li onclick="showMenu_info2()" id="enroll_info"><a
							href="#">查看报名信息</a></li>
						<li onclick="showMenu_manage2()" id="enroll_info_manage"><a
                            href="#">管理报名信息</a></li>
                        <li onclick="showMenu_add2()" id="enroll_info_add"><a
                            href="#">新增报名信息</a></li>
					</ul>
					<li id="introduce" onclick="showIntroduceMenu()"><a href="#">管理学校简介信息</a> <img
						src="imgs/home/xiala.png" id="xiala3"></li>
					<ul class="introduce_menu" id="click_introduce_menu" style="display: none">
						<li onclick="showMenu_info3()" id="introduce_info"><a
							href="#">查看学校简介</a></li>
						<li onclick="showMenu_add3()" id="introduce_add"><a
                            href="#">管理学校简介</a></li>
					</ul>
					<li id="teacher" onclick="showTeacherMenu()"><a>教师信息管理</a><img
						src="imgs/home/xiala.png" id="xiala4"></li>
					<ul class="teacher_menu" id="click_teacher_menu"
						style="display: none">
						<li onclick="showMenu_info4()" id="teacher_info"><a href="#">查看教师信息</a></li>
						<li onclick="showMenu_add4()" id="teacher_add"><a href="#">新增教师信息</a></li>
					</ul>
				</ul>
			</div>

			<!-- 站点数据统计 -->
			
		</div>
	</div>
</body>
</html>