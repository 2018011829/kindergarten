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
				<%@ include file="left.jsp"%>
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
					<form action="AskForLeaveServlet">
						<div class="leave_div">
							<span class="leave_span">请假学生姓名：<input type="text" placeholder="请输入学生姓名" name="stuName" id="input_day" value="${stuName }"></span>
						</div>
						<div class="leave_div">
							<span class="leave_span">家长联系方式：<input type="text" placeholder="请输入家长的11位电话号码" name="phone" id="input_day" value="${phone }"></span>
						</div>
						<div class="leave_div">
							<span class="leave_span">请假开始时间(本月几号)：<input type="text" placeholder="请输入数字" name="dayStart" id="input_day" value="${dayNum }"></span>
						</div>
						<div class="leave_div">
							<span class="leave_span">请假结束时间(本月几号)：<input type="text" placeholder="请输入数字" name="dayEnd" id="input_day" value="${dayNum }"></span>
						</div>
						<div class="submit_div">
							<div class="error_info">
								${errorInfo }
							</div>
							<input type="submit" id="submit_button" value="提交">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>