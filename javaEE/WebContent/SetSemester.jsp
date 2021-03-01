<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/semester.css">
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
                        <li onclick="showMenu_info1()" id="user_info"><a href="userManage">用户信息</a></li>
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
					<li id="introduce" onclick="showIntroduceMenu()"><a>学校简介信息管理</a>
						<img src="imgs/home/xiala.png" id="xiala3"></li>
					<ul class="introduce_menu" id="click_introduce_menu"
						style="display: none">
						<li onclick="showMenu_info3()" id="introduce_info"><a
							href="DescriptionManageServlet">学校简介</a></li>
						<li onclick="showMenu_add3()" id="introduce_add"><a
							class="jump" href="addEnvironmentPicture.jsp">新增学校信息</a></li>
					</ul>
					<li id="teacher" onclick="showTeacherMenu()"><a>教师信息管理</a><img
						src="imgs/home/xiala.png" id="xiala4"></li>
					<ul class="teacher_menu" id="click_teacher_menu"
						style="display: none">
						<li onclick="showMenu_info4()" id="teacher_info"><a
							href="teacherManage">查看教师信息</a></li>
						<li onclick="showMenu_add4()" id="teacher_add"><a
							href="addTeacher.jsp">新增教师信息</a></li>
						<li onclick="showMenu_info5()" id="teacher_add"><a
							href="deletedTeacherManage">查看离职教师信息</a></li>
					</ul>
					<li id="account" onclick="showAccountMenu()"><a>本学期上课安排</a><img
						src="imgs/home/xiala.png" id="xiala5"></li>
					<ul class="account_menu" id="click_account_menu"
						style="display: block">
                        <li onclick="showMenu_info5()" id="account_info"
							style="background: #009688"><a href="SearchClassTime">查看本学期上课时间</a></li>
					</ul>
				</ul>
			</div>
			<div id="schoolInfoMenu">
				<ul class="schoolInfoMenu">
					<li><a href="SearchClassTime">查看本学期上课时间</a></li>
					<li><a href="inputAskForLeave.jsp">录入学生请假信息</a></li>
					<li><a href="SetSemester.jsp">安排本学期上课时间</a></li>
					<li><a href="SearchScreenshotInfo">查看本月缴费情况</a></li>
				</ul>
			</div>
			<div class="box2">
				<div class="box3">
					<form action="PreserveMonth">
						<div class="day_div">
							<span>1：<input type="text" placeholder="请输入天数" name="one" id="input_day"></span>
							<span class="day_span">2：<input type="text" placeholder="请输入天数" name="two" id="input_day"></span>
						</div>
						<div class="day_div">
							<span>3：<input type="text" placeholder="请输入天数" name="three" id="input_day"></span>
							<span class="day_span">4：<input type="text" placeholder="请输入天数" name="four" id="input_day"></span>
						</div>
						<div class="day_div">
							<span>5：<input type="text" placeholder="请输入天数" name="five" id="input_day"></span>
							<span class="day_span">6：<input type="text" placeholder="请输入天数" name="six" id="input_day"></span>
						</div>
						<div class="day_div">
							<span>7：<input type="text" placeholder="请输入天数" name="seven" id="input_day"></span>
							<span class="day_span">8：<input type="text" placeholder="请输入天数" name="eight" id="input_day"></span>
						</div>
						<div class="day_div">
							<span>9：<input type="text" placeholder="请输入天数" name="nine" id="input_day"></span>
							<span class="day_span">10：<input type="text" placeholder="请输入天数" name="ten" id="input_day"></span>
						</div>
						<div class="day_div">
							<span>11：<input type="text" placeholder="请输入天数" name="eleven" id="input_day"></span>
							<span class="day_span">12：<input type="text" name="twelve" placeholder="请输入天数" id="input_day"></span>
						</div>
						<div class="submit_div">
							<div class="error_info">
								${errorInfo }
							</div>
							<input type="submit" id="submit_button" value="保存">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>