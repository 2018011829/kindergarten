<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>UpdateTeacherInfo</title>
<link rel="stylesheet" type="text/css" href="css/editTeacher.css">
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
	<div id="box">
		<%@ include file="header.jsp"%>
		<div id="index_home">
			<div id="menu">
                <%@ include file="left.jsp"%>
            </div>
			<div class="editBox1">
				<div class="updateBox1">
					<p id="text">原图片</p><br/>
					<img alt="" src="${teacher.picture }" id="oldImg">
				</div>
				<div class="updateBox2">
					<p id="updateTitle">Update Teacher Info</p>
					<form action="updateTeacherManage" method="post"
						enctype="multipart/form-data" style="margin-top: 30px">
						<input type="hidden" name="teacherId" value="${teacherId }" />
						<div class="updateBox3">
							<div class="updateBox4">教师姓名：</div>
							<input type="text" name="teacherName" value="${teacher.name }"
								class="updateInput" />
						</div>
						<br />
						<div
							style="width: 500px; height: 50px; text-align: center; text-indent: 8px;">
							<div style="margin: 0 auto;">
								<div class="updateBox4">教师图片：</div>
								<div class="updateBox5">
									<input type="file" name="teacherPicture" id="updateImgFile" />
								</div>
							</div>
						</div>
						<br />
						<div class="updateBox3">
							<div class="updateBox4">教师职位：</div>
							<input type="text" name="teacherPosition"
								value="${teacher.position }" class="updateInput" />
						</div>
						<br />
						<div class="updateBox3">
							<div class="updateBox4">联系方式：</div>
							<input type="text" name="teacherPhone" value="${teacher.phone }"
								class="updateInput" />
						</div>
						<br />
						<div class="updateBox3">
							<div class="updateBox4">座右铭：</div>
							<input type="text" name="teacherMotto" value="${teacher.motto }"
								class="updateInput" />
						</div>
						<br /> <input type="submit" value="提交修改信息" id="submitUpdate" />
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>