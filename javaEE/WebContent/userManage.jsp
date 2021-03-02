<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>User Manage</title>
<link rel="stylesheet" type="text/css" href="css/teacherStyle.css">
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
	<div id="box">
		<%@ include file="header.jsp"%>
		<div id="menu">
			<%@ include file="left.jsp"%>
		</div>
		<div class="box1">
			<form action="searchUserByPhone" method="post">
				<input type="text" name="userPhone" placeholder="请输入手机号"
					id="inputName" /> <input type="submit" value="点击搜索"
					id="submitSearch">
			</form>
		</div>
		<div class="box2">
			<div class="box3">
				<table border="1">
					<tr height="40px">
						<th width="180px" style="text-align: center;">用户id</th>
						<th width="280px" style="text-align: center;">用户手机号</th>
						<th width="220px" style="text-align: center;">用户昵称</th>
						<th width="180px"
							style="padding-left: 10px; padding-right: 10px; text-align: center;">操作</th>
					</tr>
					<c:forEach items="${page.list }" var="user">
						<tr align="center">
							<td>${user.id }</td>
							<td>${user.phone }</td>
							<td>${user.nickname }</td>
							<td style="padding: 10px;"><a
								href="deleteUser?id=${user.id }"> <img alt=""
									src="imgs/deleteTeacher.png" style="width: 20px; height: 20px;">
							</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="box4">
					<div class="box5">
						总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；&nbsp;&nbsp; <a
							href="userManage?page=1" style="color: black">首页</a>&nbsp;&nbsp;
						<a href="userManage?page=${page.prePageNum }" style="color: black">上一页</a>&nbsp;&nbsp;
						<a href="userManage?page=${page.nextPageNum }"
							style="color: black">下一页</a>&nbsp;&nbsp; <a
							href="userManage?page=${page.totalPageNum }" style="color: black">末页</a>&nbsp;&nbsp;
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>