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
                <ul class="menu">
                    <li id="user" onclick="showUserMenu()"><a>用户信息管理</a> <img
                        src="imgs/home/xiala.png" id="xiala1"></li>
                    <ul class="user_menu" id="click_user_menu" style="display: none">
                        <li onclick="showMenu_info1()" id="user_info"><a href="userManage" class="jump">用户信息</a></li>
                    </ul>
                    <li id="enroll" onclick="showEnrollMenu()"><a>报名信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala2"></li>
                    <ul class="enroll_menu" id="click_enroll_menu"
                        style="display: none">
                        <li onclick="showMenu_info2()" id="enroll_info"><a href="GetApplyInfoServlet" class="jump">查看报名信息</a></li>
                        <li onclick="showMenu_manage2()" id="enroll_info_manage"><a
                            href="GetApplyInfoServlet2" class="jump">管理报名信息</a></li>
                        <li onclick="showMenu_add2()" id="enroll_info_add"><a
                            href="addApplyinfo.jsp" class="jump">新增报名信息</a></li>
                    </ul>
                    <li id="introduce" onclick="showIntroduceMenu()"><a>学校简介信息管理</a> <img
                        src="imgs/home/xiala.png" id="xiala3"></li>
                    <ul class="introduce_menu" id="click_introduce_menu" style="display: none">
                        <li onclick="showMenu_info3()" id="introduce_info"><a
                            href="DescriptionManageServlet" class="jump">学校简介</a></li>
                        <li onclick="showMenu_add3()" id="introduce_add"><a
                            href="addEnvironmentPicture.jsp" class="jump">新增学校信息</a></li>
                    </ul>
                    <li id="teacher" onclick="showTeacherMenu()"><a>教师信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala4"></li>
                    <ul class="teacher_menu" id="click_teacher_menu"
                        style="display: none">
                        <li onclick="showMenu_info4()" id="teacher_info"><a href="teacherManage" class="jump">查看教师信息</a></li>
                        <li onclick="showMenu_add4()" id="teacher_add"><a href="addTeacher.jsp" class="jump">新增教师信息</a></li>
                        <li onclick="showMenu_info4()" id="teacher_delete"><a href="deletedTeacherManage" class="jump">查看离职教师信息</a></li>
                    </ul>
                    <li id="account" onclick="showAccountMenu()"><a>收费信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala5"></li>
                    <ul class="account_menu" id="click_account_menu"
                        style="display: none">
                        <li onclick="showMenu_info5()" id="account_info"><a href="SearchClassTime" class="jump">查看本学期上课时间</a></li>
                        <li onclick="showMenu_info5()" id="account_info"><a href="ChargeManagerServlet" class="jump">查看收款码信息</a></li>
                    </ul>
                </ul>
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