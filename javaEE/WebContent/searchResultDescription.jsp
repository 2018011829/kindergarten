<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/schoolInfo.css">
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
					<li id="introduce" onclick="showIntroduceMenu()"><a>学校简介信息管理</a>
						<img src="imgs/home/xiala.png" id="xiala3"></li>
					<ul class="introduce_menu" id="click_introduce_menu"
						style="display: block">
						<li onclick="showMenu_info3()" id="introduce_info" style="background: #009688"><a
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
			            src="imgs/home/xiala.png" id="xiala6"></li>
			        <ul class="student_menu" id="click_student_menu" style="display: none">
			            <li onclick="showMenu_info6()" id="student_info"><a
			                href="StudentsManageServlet">查看学生信息</a></li>
			        </ul>
				</ul>
			</div>
			<div id="schoolInfoMenu">
				<ul class="schoolInfoMenu">
					<li><a href="DescriptionManageServlet">环境描述</a></li>
					<li><a href="PictureManageServlet">环境图片</li>
					<li><a href="BasicInfoServlet">基本信息</a></li>
					<li><a href="PhoneManageServlet">联系电话</a></li>
				</ul>
			</div>
			<div class="box1">
				<form action="SearchDescriptionServlet" method="post">
					<input type="text" name="searchInfo" placeholder="请输入描述信息"
						id="inputName" value="${searchInfo }" /> <input type="submit"
						value="点击搜索" id="submitSearch">
				</form>
			</div>
			<div class="box2">
				<div class="box3">
					<table border="1" style="margin: 0 auto;">
						<tr height="40px">
							<th width="100px" style="text-align: center;">id</th>
							<th width="220px" style="text-align: center;">描述</th>
							<th width="100px"
								style="padding-left: 10px; padding-right: 10px; text-align: center;">操作</th>
						</tr>
						<c:forEach items="${page.list }" var="description">
							<tr align="center">
								<td>${description.id }</td>
								<td>${description.description }</td>
								<td style="padding-left: 10px; padding-right: 10px;"><a
									href="UpdateDescription?id=${description.id }"
									style="margin-right: 8px;"> <img alt=""
										src="imgs/updateTeacher.png"
										style="width: 22px; height: 22px;">
								</a> <a href="DeleteDescriptionServlet?id=${description.id }"> <img
										alt="" src="imgs/deleteTeacher.png"
										style="width: 22px; height: 22px;">
								</a></td>
							</tr>
						</c:forEach>
					</table>
					<div class="box4">
						<div class="box5">
							总共有${page.totalPageNum }页，总共有${page.totalCount }个数据； <a
                                href="SearchDescriptionServlet?page=1&searchInfo=${searchInfo }&userName=${userName }"
                                style="color: black">首页</a> <a
                                href="SearchDescriptionServlet?page=${page.prePageNum }&searchInfo=${searchInfo }&userName=${userName }"
                                style="color: black">上一页</a> <a
                                href="SearchDescriptionServlet?page=${page.nextPageNum }&searchInfo=${searchInfo }&userName=${userName }"
                                style="color: black">下一页</a> <a
                                href="SearchDescriptionServlet?page=${page.totalPageNum }&searchInfo=${searchInfo }&userName=${userName }"
                                style="color: black">末页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>