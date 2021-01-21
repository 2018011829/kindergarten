<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
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
						<li onclick="showMenu_info1()" id="user_info"><a href="#">用户信息</a></li>
						<li onclick="showMenu_add1()" id="user_add"><a href="#">新增用户</a></li>
					</ul>
					<li id="enroll" onclick="showEnrollMenu()"><a>报名信息管理</a><img
						src="imgs/home/xiala.png" id="xiala2"></li>
					<ul class="enroll_menu" id="click_enroll_menu"
						style="display: none">
						<li onclick="showMenu_info2()" id="enroll_info"><a href="GetApplyInfoServlet">查看报名信息</a></li>
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
			
			
			<div style="padding-top:100px;margin-left:260px;text-align:center">
	<h1 style="color:green">报名信息</h1>
	<br>
	<div style="width:1050px;overflow-y:none;overflow-x:scroll">
	<table border="1px" cellspacing="0" bordercolor="#C0C0C0" width="2700px">
		<tr align="center" style="background-color:#D3D3D3" height="40px">
			<td width="150">ID</td>
			<td width="150">报名电话</td>
			<td width="150">宝宝姓名</td>
			<td width="150">宝宝生日</td>
			<td width="150">宝宝性别</td>
			<td width="150">宝宝身份证号</td>
			<td width="150">宝宝过敏食物</td>
			<td width="150">家长姓名1</td>
			<td width="150">与宝宝关系1</td>
			<td width="150">家长身份证号1</td>
			<td width="150">联系方式1</td>
			<td width="150">工作单位1</td>
			<td width="150">家庭住址1</td>
			<td width="150">家长姓名2</td>
			<td width="150">与宝宝关系2</td>
			<td width="150">家长身份证号2</td>
			<td width="150">联系方式2</td>
			<td width="150">工作单位2</td>
			<td width="150">家庭住址2</td>
		</tr>
	<c:forEach var="applyInfo" items="${page.list }">
		<tr align="center" height="40px">
			<td width="50">${applyInfo.getId()}</td>
			<td >${applyInfo.getUserNumber()}</td>
			<td >${applyInfo.getBabyName()}</td>
			<td >${applyInfo.getBabyBirthday() }</td>
			<td >${applyInfo.getBabySex() }</td>
			<td >${applyInfo.getBabyIDnumber() }</td>
			<td >${applyInfo.getBabyAddoAllergies() }</td>
			<td >${applyInfo.getParentName1()}</td>
			<td >${applyInfo.getRelation1() }</td>
			<td >${applyInfo.getParentIDnumber1() }</td>
			<td >${applyInfo.getPhoneNumber1() }</td>
			<td >${applyInfo.getWorkSpace1() }</td>
			<td >${applyInfo.getHomeAddress1()}</td>
			<td >${applyInfo.getParentName2()}</td>
			<td >${applyInfo.getRelation2() }</td>
			<td >${applyInfo.getParentIDnumber2() }</td>
			<td >${applyInfo.getPhoneNumber2() }</td>
			<td >${applyInfo.getWorkSpace2() }</td>
			<td >${applyInfo.getHomeAddress2()}</td>
		</tr>
	</c:forEach>
	</table>
	</div>
	<br>
	总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；
	<a href="GetApplyInfoServlet?page=1" style="color:blue">首页</a>
	<a href="GetApplyInfoServlet?page=${page.prePageNum }" style="color:blue">上一页</a>
	<a href="GetApplyInfoServlet?page=${page.nextPageNum }" style="color:blue">下一页</a>
	<a href="GetApplyInfoServlet?page=${page.totalPageNum }" style="color:blue">末页</a>
	</div>
		</div>
	</div>

	
</body>
</html>