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
			<div id="schoolInfoMenu">
				<ul class="schoolInfoMenu">
					<li><a href="ChargeManagerServlet">查看收款码信息</a></li>
					<li><a href="addCharge.jsp">新增收款码信息</a></li>
				</ul>
			</div>
			<div class="box2" style="margin-top:30px;">
				<div class="box3">
					<div id="environment">
						<!-- 显示表格 -->
						<div class="addBox2">
							<p id="addTitle">添加收款码信息</p>
							<form action="AddChargeServlet" method="post"
								enctype="multipart/form-data" style="margin-top: 30px">
								<input type="text" name="babyClass" placeholder="班级"
									class="addInput" /> <br /> <br /> <input type="text"
									name="teacher" placeholder="老师" class="addInput" /> <br /> <br />
								<div class="chooseBox">
									<input type="file" name="weChart" class="chooseFile" />
								</div>
								<br /> <br />
								<div class="chooseBox">
									<input type="file" name="alipay" class="chooseFile" />
								</div>
								<br /> <br /> <input type="submit" value="提交"
									id="submitAddInfo" />
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>