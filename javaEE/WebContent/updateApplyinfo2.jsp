<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
<script type="text/javascript" src="js/index.js"></script>

</head>
<body>

	<div id="box">
		<%@ include file="header.jsp"%>
		<div id="index_home">
			<div id="menu">
				<ul class="menu">
                    <li id="user" onclick="showUserMenu()"><a>用户信息管理</a> <img
                        src="imgs/home/xiala.png" id="xiala1"></li>
                    <ul class="user_menu" id="click_user_menu" style="display: none">
                        <li onclick="showMenu_info1()" id="user_info"><a href="userManage">用户信息</a></li>
                    </ul>
                    <li id="enroll" onclick="showEnrollMenu()"><a>报名信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala2"></li>
                    <ul class="enroll_menu" id="click_enroll_menu"
                        style="display: block">
                        <li onclick="showMenu_info2()" id="enroll_info"><a href="GetApplyInfoServlet">查看报名信息</a></li>
                        <li onclick="showMenu_manage2()" id="enroll_info_manage"><a
                            href="GetApplyInfoServlet2" style="background: #009688">管理报名信息</a></li>
                        <li onclick="showMenu_add2()" id="enroll_info_add"><a
                            href="addApplyinfo.jsp">新增报名信息</a></li>
                    </ul>
                    <li id="introduce" onclick="showIntroduceMenu()"><a>管理学校简介信息</a> <img
                        src="imgs/home/xiala.png" id="xiala3"></li>
                    <ul class="introduce_menu" id="click_introduce_menu" style="display: none">
                        <li onclick="showMenu_info3()" id="introduce_info"><a
                            href="DescriptionManageServlet">学校简介</a></li>
                        <li onclick="showMenu_add3()" id="introduce_add"><a
                            href="addEnvironmentPicture.jsp">新增学校信息</a></li>
                    </ul>
                    <li id="teacher" onclick="showTeacherMenu()"><a>教师信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala4"></li>
                    <ul class="teacher_menu" id="click_teacher_menu"
                        style="display: none">
                        <li onclick="showMenu_info4()" id="teacher_info"><a href="teacherManage">查看教师信息</a></li>
                        <li onclick="showMenu_add4()" id="teacher_add"><a href="addTeacher.jsp">新增教师信息</a></li>
                        <li onclick="showMenu_info4()" id="teacher_delete"><a href="deletedTeacherManage">查看离职教师信息</a></li>
                    </ul>
                    <li id="account" onclick="showAccountMenu()"><a>本学期上课安排</a><img
                        src="imgs/home/xiala.png" id="xiala5"></li>
                    <ul class="account_menu" id="click_account_menu"
                        style="display: none">
                        <li onclick="showMenu_info5()" id="account_info"><a href="SearchClassTime">查看本学期上课时间</a></li>
                    </ul>
                </ul>
			</div>

			<!-- 站点数据统计 -->

		</div>
	</div>

	<%
		String id = request.getParameter("id");
		String userNumber = request.getParameter("userNumber");
		String babyName = request.getParameter("babyName");
		String babyBirthday = request.getParameter("babyBirthday");
		String babySex = request.getParameter("babySex");
		String babyIDnumber = request.getParameter("babyIDnumber");
		String babyAddoAllergies = request.getParameter("babyAddoAllergies");
		String parentName1 = request.getParameter("parentName1");
		String parentName2 = request.getParameter("parentName2");
		String parentIDnumber1 = request.getParameter("parentIDnumber1");
		String parentIDnumber2 = request.getParameter("parentIDnumber2");
		String relation1 = request.getParameter("relation1");
		String relation2 = request.getParameter("relation2");
		String phoneNumber1 = request.getParameter("phoneNumber1");
		String phoneNumber2 = request.getParameter("phoneNumber2");
		String workSpace1 = request.getParameter("workSpace1");
		String workSpace2 = request.getParameter("workSpace2");
		String homeAddress1 = request.getParameter("homeAddress1");
		String homeAddress2 = request.getParameter("homeAddress2");
		String page2 = request.getParameter("page");
		String msg = request.getParameter("msg");
	%>
	<div style="padding-top:100px;margin-bottom:20px;margin-left:260px;text-align:center">
	<form action="UpdateApplyinfoServlet2?page=<%=page2%>&msg=<%=msg%>" method="post">
		<a style="color:green;font-size:25px">幼儿信息</a><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ID：<input type="text" name="id" value="<%=id%>" readonly="readonly"><br>
		报名电话：<input type="text" name="userNumber" value="<%=userNumber%>" required="required" pattern="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$" 
		title="请输入正确的手机号"><br>
		宝宝姓名：<input type="text" name="babyName" value="<%=babyName%>" required="required"><br>
		出生日期：<input type="text" name="babyBirthday" value="<%=babyBirthday%>" required="required" placeholder="xxxx年xx月xx日"><br>
		宝宝性别：<input type="text" name="babySex" value="<%=babySex%>" required="required"><br>
		身份证号：<input type="text" name="babyIDnumber" value="<%=babyIDnumber%>" required="required" pattern="^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\d)|(3[0-1]))((\d{4})|(\d{3}[Xx]))$" 
		title="请输入正确的身份证号"><br>
		过敏食物：<input type="text" name="babyAddoAllergies" value="<%=babyAddoAllergies%>" required="required" placeholder="若没有请填写'无'" ><br><br>
		<a style="color:green;font-size:25px">家长信息1</a><br>
		家长姓名：<input type="text" name="parentName1" value="<%=parentName1%>" required="required"><br>
		与宝宝关系：<input type="text" name="relation1" value="<%=relation1%>" required="required">&nbsp;&nbsp;&nbsp;<br>
		身份证号：<input type="text" name="parentIDnumber1" value="<%=parentIDnumber1%>" required="required" pattern="^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\d)|(3[0-1]))((\d{4})|(\d{3}[Xx]))$" 
		title="请输入正确的身份证号"><br>
		联系方式：<input type="text" name="phoneNumber1" value="<%=phoneNumber1%>" required="required" pattern="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$" 
		title="请输入正确的手机号"><br>
		工作单位：<input type="text" name="workSpace1" value="<%=workSpace1%>" required="required"><br>
		家庭住址：<input type="text" name="homeAddress1" value="<%=homeAddress1%>" required="required"><br><br>
		<a style="color:green;font-size:25px">家长信息2(选填)</a><br>
		家长姓名：<input type="text" name="parentName2" value="<%=parentName2%>"><br>
		与宝宝关系：<input type="text" name="relation2" value="<%=relation2%>">&nbsp;&nbsp;&nbsp;<br>
		身份证号：<input type="text" name="parentIDnumber2" value="<%=parentIDnumber2%>"  pattern="^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2]\d)|(3[0-1]))((\d{4})|(\d{3}[Xx]))$" 
		title="请输入正确的身份证号"><br>
		联系方式：<input type="text" name="phoneNumber2" value="<%=phoneNumber2%>"  pattern="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$" 
		title="请输入正确的手机号"><br>
		工作单位：<input type="text" name="workSpace2" value="<%=workSpace2%>"><br>
		家庭住址：<input type="text" name="homeAddress2" value="<%=homeAddress2%>"><br><br>
		<input type="submit" value="提交" id="submitSearch">
	</form>
	</div>
</body>
</html>