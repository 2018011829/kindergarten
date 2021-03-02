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