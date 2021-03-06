<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="menu">
		<li id="user" onclick="showUserMenu()"><a>用户信息管理</a> <img
			src="imgs/home/xiala.png" id="xiala1"></li>
		<ul class="user_menu" id="click_user_menu" style="display: none">
			<li onclick="showMenu_info1()" id="user_info"><a
				href="userManage" class="jump">用户信息</a></li>
		</ul>
		<li id="enroll" onclick="showEnrollMenu()"><a>报名信息管理</a><img
			src="imgs/home/xiala.png" id="xiala2"></li>
		<ul class="enroll_menu" id="click_enroll_menu" style="display: none">
			<li onclick="showMenu_info2()" id="enroll_info"><a
				href="GetApplyInfoServlet" class="jump">查看报名信息</a></li>
			<li onclick="showMenu_manage2()" id="enroll_info_manage"><a
				href="GetApplyInfoServlet2" class="jump">管理报名信息</a></li>
			<li onclick="showMenu_add2()" id="enroll_info_add"><a
				href="addApplyinfo.jsp" class="jump">新增报名信息</a></li>
		</ul>
		<li id="introduce" onclick="showIntroduceMenu()"><a>学校简介信息管理</a>
			<img src="imgs/home/xiala.png" id="xiala3"></li>
		<ul class="introduce_menu" id="click_introduce_menu"
			style="display: none">
			<li onclick="showMenu_info3()" id="introduce_info"><a
				href="DescriptionManageServlet" class="jump">学校简介</a></li>
			<li onclick="showMenu_add3()" id="introduce_add"><a
				href="addEnvironmentPicture.jsp" class="jump">新增学校信息</a></li>
		</ul>
		<li id="teacher" onclick="showTeacherMenu()"><a>教师信息管理</a><img
			src="imgs/home/xiala.png" id="xiala4"></li>
		<ul class="teacher_menu" id="click_teacher_menu" style="display: none">
			<li onclick="showMenu_info4()" id="teacher_info"><a
				href="teacherManage" class="jump">查看教师信息</a></li>
			<li onclick="showMenu_add4()" id="teacher_add"><a
				href="addTeacher.jsp" class="jump">新增教师信息</a></li>
			<li onclick="showMenu_info4()" id="teacher_delete"><a
				href="deletedTeacherManage" class="jump">查看离职教师信息</a></li>
		</ul>
		<li id="account" onclick="showAccountMenu()"><a>收费信息管理</a><img
			src="imgs/home/xiala.png" id="xiala5"></li>
		<ul class="account_menu" id="click_account_menu" style="display: none">
			<li onclick="showMenu_info5()" id="account_info"><a
				href="SearchClassTime" class="jump">查看本学期上课时间</a></li>
			<li onclick="showMenu_info5()" id="account_info"><a
				href="ChargeManagerServlet" class="jump">查看收款码信息</a></li>
		</ul>
		<li id="student" onclick="showStudentMenu()"><a>学生信息管理</a><img
            src="imgs/home/xiala.png" id="xiala2"></li>
        <ul class="student_menu" id="click_student_menu" style="display: none">
            <li onclick="showMenu_info6()" id="student_info"><a
                href="StudentsManageServlet">查看学生信息</a></li>
        </ul>
	</ul>

</body>
</html>