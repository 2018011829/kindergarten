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
				<form action="SearchPictureServlet" method="post">
					<input type="text" name="searchInfo" placeholder="请输入描述id"
						id="inputName" value="${searchInfo }" /> <input type="submit"
						value="点击搜索" id="submitSearch">
				</form>
			</div>
			<div class="box2">
				<div class="box3">
					<table border="1" style="margin: 0 auto;">
						<tr height="40px">
							<th width="100px" style="text-align: center;">id</th>
							<th width="220px" style="text-align: center;">描述图片</th>
							<th width="220px" style="text-align: center;">描述id</th>
							<th width="100px"
								style="padding-left: 10px; padding-right: 10px; text-align: center;">操作</th>
							<th width="100px" style="text-align: center;">id</th>
							<th width="220px" style="text-align: center;">描述图片</th>
							<th width="220px" style="text-align: center;">描述id</th>
							<th width="100px"
								style="padding-left: 10px; padding-right: 10px; text-align: center;">操作</th>
						</tr>
						<c:forEach items="${page.list }" var="picture" begin="0" end="2"
							varStatus="s">
							<tr>

								<c:if test="${s.index ==0 }">
									<c:forEach items="${page.list }" begin="0" end="5"
										var="picture" step="3">
										<td>${picture.id }</td>
										<td style="text-align: center;"><img
											alt="${picture.picture}"
											src="imgs/schoolInfoPicture/${picture.picture }"
											style="width: 150px; height: 180px; background: #ffffff; padding-top: 10px;">
										</td>
										<td>${picture.descriptionId }</td>
										<td style="padding-left: 10px; padding-right: 10px;"><a
											href="UpdatePicureServlet?id=${picture.id }"
											style="margin-right: 8px;"> <img alt=""
												src="imgs/updateTeacher.png"
												style="width: 22px; height: 22px;">
										</a> <a href="DeletePictureServlet?id=${picture.id }"> <img
												alt="" src="imgs/deleteTeacher.png"
												style="width: 22px; height: 22px;">
										</a></td>
									</c:forEach>
								</c:if>
								<c:if test="${s.index ==1 }">
									<c:forEach items="${page.list }" begin="1" end="5"
										var="picture" step="3">
										<td>${picture.id }</td>
										<td style="text-align: center;"><img
											alt="${picture.picture}"
											src="imgs/schoolInfoPicture/${picture.picture }"
											style="width: 150px; height: 180px; background: #ffffff; padding-top: 10px;">
										</td>
										<td>${picture.descriptionId }</td>
										<td style="padding-left: 10px; padding-right: 10px;"><a
											href="UpdatePicureServlet?id=${picture.id }"
											style="margin-right: 8px;"> <img alt=""
												src="imgs/updateTeacher.png"
												style="width: 22px; height: 22px;">
										</a> <a href="DeletePictureServlet?id=${picture.id }"> <img
												alt="" src="imgs/deleteTeacher.png"
												style="width: 22px; height: 22px;">
										</a></td>
									</c:forEach>
								</c:if>
								<c:if test="${s.index ==2 }">
									<c:forEach items="${page.list }" begin="2" end="5"
										var="picture" step="3">
										<td>${picture.id }</td>
										<td style="text-align: center;"><img
											alt="${picture.picture}"
											src="imgs/schoolInfoPicture/${picture.picture }"
											style="width: 150px; height: 180px; background: #ffffff; padding-top: 10px;">
										</td>
										<td>${picture.descriptionId }</td>
										<td style="padding-left: 10px; padding-right: 10px;"><a
											href="UpdatePicureServlet?id=${picture.id }"
											style="margin-right: 8px;"> <img alt=""
												src="imgs/updateTeacher.png"
												style="width: 22px; height: 22px;">
										</a> <a href="DeletePictureServlet?id=${picture.id }"> <img
												alt="" src="imgs/deleteTeacher.png"
												style="width: 22px; height: 22px;">
										</a></td>
									</c:forEach>
								</c:if>

							</tr>
						</c:forEach>

					</table>
					<div class="box4">
						<div class="box5">
							总共有${page.totalPageNum }页，总共有${page.totalCount }个数据； <a
								href="PictureManageServlet?page=1&userName=${userName }"
								style="color: black">首页</a> <a
								href="PictureManageServlet?page=${page.prePageNum }&userName=${userName }"
								style="color: black">上一页</a> <a
								href="PictureManageServlet?page=${page.nextPageNum }&userName=${userName }"
								style="color: black">下一页</a> <a
								href="PictureManageServlet?page=${page.totalPageNum }&userName=${userName }"
								style="color: black">末页</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>