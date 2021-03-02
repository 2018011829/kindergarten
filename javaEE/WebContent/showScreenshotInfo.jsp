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
					<table border="1" style="margin: 0 auto;">
						<tr height="40px">
							<th width="100px" style="text-align: center;">id</th>
							<th width="150px" style="text-align: center;">孩子姓名</th>
							<th width="100px" style="text-align: center;">年级</th>
							<th width="100px" style="text-align: center;">班级</th>
							<th width="150px" style="text-align: center;">联系方式</th>
							<th width="200px" style="text-align: center;">截图</th>
						</tr>
						<c:forEach items="${ScreenshotList }" var="screenshotInfo">
							<tr align="center">
								<td>${screenshotInfo.id }</td>
								<td>${screenshotInfo.babyName }</td>
								<td>${screenshotInfo.babyGrade }</td>
								<td>${screenshotInfo.babyClass }</td>
								<td>${screenshotInfo.phone }</td>
								<td><img src="imgs/screenshot/${screenshotInfo.photoName }" height="150px" width="100px"></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>