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
			<div class="box2">
				<div class="box3">
					<div class="weChat">
						<p id="text">原微信收款码</p>
						<img alt="" style="border: 1px #000000 solid;"
							src="imgs/charge/${weChat }" id="oldImg">
						<p id="text" style="margin-top: 30px;">原支付宝收款码</p>
						<img alt="" style="border: 1px #000000 solid;"
							src="imgs/charge/${alipay }" id="oldImg">
					</div>

					<div id="environment">
						<!-- 显示表格 -->
						<div class="addBox2">
							<p id="addTitle">修改收款码信息</p>
							<form action="UploadChargeServlet" method="post"
								enctype="multipart/form-data" style="margin-top: 30px">
								<input type="hidden" name="weChatOld" value="${weChat }" /> 
								<input type="hidden" name="alipayOld" value="${alipay }" /> 
								<input type="hidden" name="id" value="${id }" /> 
								<input type="text" name="babyClass" value="${babyClass }"
									class="addInput" /> <br /> <br /> <input type="text"
									name="teacher" value="${teacher }" class="addInput" /> <br />
								<br />
								<div class="chooseBox">
									<input type="file" name="weChat" class="chooseFile" />
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