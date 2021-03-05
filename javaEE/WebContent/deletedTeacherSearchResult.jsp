<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Deleted Teacher</title>
<link rel="stylesheet" type="text/css" href="css/teacherStyle.css">
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
					<ul class="enroll_menu" id="click_enroll_menu" style="display: none">
						<li onclick="showMenu_info2()" id="enroll_info"><a
							href="GetApplyInfoServlet">查看报名信息</a></li>
						<li onclick="showMenu_manage2()" id="enroll_info_manage"><a
							href="GetApplyInfoServlet2">管理报名信息</a></li>
						<li onclick="showMenu_add2()" id="enroll_info_add"><a
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
					<ul class="teacher_menu" id="click_teacher_menu" style="display: block">
						<li onclick="showMenu_info4()" id="teacher_info"><a
							href="teacherManage"  style="background: #009688">查看教师信息</a></li>
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
				</ul>
            </div>
			<div class="box0">
				<div class="box3">
					<table border="1" style="margin: 0 auto;">
						<tr height="40px">
							<th width="100px" style="text-align: center;">教师id</th>
							<th width="120px" style="text-align: center;">教师姓名</th>
							<th width="120px" style="text-align: center;">教师职位</th>
							<th width="150px" style="text-align: center;">联系方式</th>
							<th width="180px" style="text-align: center;">教师图片</th>
							<th style="text-align: center;padding-left:10px;padding-right:10px;">座右铭</th>
						</tr>
						<c:forEach items="${page.list }" var="teacher">
							<tr align="center">
								<td>${teacher.id }</td>
								<td>${teacher.name }</td>
								<td>${teacher.position }</td>
								<td>${teacher.phone }</td>
								<td style="text-align: center;"><img alt=""
									src="${teacher.picture }"
									style="width: 140px; height: 180px; background: #ffffff;">
								</td>
								<td width="200px" style="padding-left: 10px; text-align: left;">${teacher.motto }</td>
							</tr>
						</c:forEach>
					</table>
					<div class="box4">
						<div class="box5">
							总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；&nbsp;&nbsp;
							<a href="searchDeletedTeacherByName?teacherName=${name }&page=1">首页</a>&nbsp;&nbsp;
							<a href="searchDeletedTeacherByName?teacherName=${name }&page=${page.prePageNum }" style="color: black">上一页</a>&nbsp;&nbsp;
							<a href="searchDeletedTeacherByName?teacherName=${name }&page=${page.nextPageNum }" style="color: black">下一页</a>&nbsp;&nbsp;
							<a href="searchDeletedTeacherByName?teacherName=${name }&page=${page.totalPageNum }" style="color: black">末页</a>&nbsp;&nbsp;
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>