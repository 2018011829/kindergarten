<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/schoolInfo.css" />
<script type="text/javascript" src="js/index.js"></script>
<body>
	<div id="box">
		<%@ include file="header.jsp"%>
		<div id="index_home">
			<div id="menu">
				<%@ include file="left.jsp"%>
			</div>
			<div id="schoolInfo">
				<div id="environment">
					<!-- 显示表格 -->
					<div class="addBox2">
						<p id="addTitle">添加描述图片</p>
						<form action="addPictureServlet" method="post" enctype="multipart/form-data" style="margin-top: 30px">
							<div class="chooseBox">
								<input type="file" name="Picture" class="chooseFile" />
							</div>
							<br /> <br /> <input type="text" name="descriptionId"
								placeholder="描述id" class="addInput" /> <br /> <br /> <input
								type="submit" value="提交" id="submitAddInfo" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>