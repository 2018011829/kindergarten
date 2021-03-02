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
				<%@ include file="left.jsp"%>
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
				<form action="searchTeacherByName" method="post">
					<input type="text" name="teacherName" placeholder="请输入联系电话"
						id="inputName" value="${searchInfo }" /> <input type="submit"
						value="点击搜索" id="submitSearch">
				</form>
			</div>
			<div class="box2">
				<div class="box3">
					<table border="1" style="margin: 0 auto;">
						<tr height="40px">
							<th width="100px" style="text-align: center;">id</th>
							<th width="220px" style="text-align: center;">电话</th>
							<th width="100px"
								style="padding-left: 10px; padding-right: 10px; text-align: center;">操作</th>
						</tr>
						<c:forEach var="phone" items="${page.list }">
							<tr>
								<td>${phone.id }</td>
								<td>${phone.phone }</td>
								<td style="padding-left: 10px; padding-right: 10px;"><a
									href="UpdatePhone?id=${phone.id }" style="margin-right: 8px;">
										<img alt="" src="imgs/updateTeacher.png"
										style="width: 22px; height: 22px;">
								</a></td>
							</tr>
						</c:forEach>
					</table>
					<div class="box4">
						<div class="box5">
							总共有${page.totalPageNum }页，总共有${page.totalCount }个数据； <a
								href="PhoneManageServlet?page=1&userName=${userName }"
								style="color: black">首页</a> <a
								href="PhoneManageServlet?page=${page.prePageNum }&userName=${userName }"
								style="color: black">上一页</a> <a
								href="PhoneManageServlet?page=${page.nextPageNum }&userName=${userName }"
								style="color: black">下一页</a> <a
								href="PhoneManageServlet?page=${page.totalPageNum }&userName=${userName }"
								style="color: black">末页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>