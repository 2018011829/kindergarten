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
				<div class="editBox1">
                        <div class="updateBox2">
                            <p id="updateTitle">Update Teacher Info</p>
                            <form action="UploadPhone" method="post" style="margin-top: 30px">
                                <input type="hidden" name="phoneId" value="${id }" />
                                <div class="updateBox3">
                                    <div class="updateBox4">电话：</div>
                                    <input type="text" name="phone"
                                        value="${phone }" class="updateInput" />
                                </div>
                                <br /> <input type="submit" value="提交" id="submitSearch">
                            </form>
                        </div>
                    </div>
			</div>
		</div>
	</div>
</body>
</html>