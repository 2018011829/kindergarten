<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Teacher</title>
<link rel="stylesheet" type="text/css" href="css/teacherStyle.css">
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
	<div id="box">
		<%@ include file="header.jsp"%>
		<div id="index_home">
			<div id="menu">
                <%@ include file="left.jsp"%>
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
                            <th style="text-align: center;">座右铭</th>
                            <th width="100px"
                                style="padding-left: 10px; padding-right: 10px; text-align: center;">操作</th>
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
								<td style="padding-left: 10px; padding-right: 10px;"><a
									href="updateTeacher?id=${teacher.id }"
									style="margin-right: 8px;"> <img alt=""
										src="imgs/updateTeacher.png"
										style="width: 22px; height: 22px;">
								</a> <a href="deleteTeacher?id=${teacher.id }"> <img alt=""
										src="imgs/deleteTeacher.png"
										style="width: 22px; height: 22px;">
								</a></td>
							</tr>
						</c:forEach>
					</table>
					<div class="box4">
						<div class="box5">
							总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；&nbsp;&nbsp;
							<a href="searchTeacherByName?teacherName=${name }&page=1" style="color: black">首页</a>&nbsp;&nbsp;
							<a href="searchTeacherByName?teacherName=${name }&page=${page.prePageNum }" style="color: black">上一页</a>&nbsp;&nbsp;
							<a href="searchTeacherByName?teacherName=${name }&page=${page.nextPageNum }" style="color: black">下一页</a>&nbsp;&nbsp;
							<a href="searchTeacherByName?teacherName=${name }&page=${page.totalPageNum }" style="color: black">末页</a>&nbsp;&nbsp;
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>