<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/apply.css" />
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
                        style="display: none">
                        <li onclick="showMenu_info2()" id="enroll_info"><a href="GetApplyInfoServlet">查看报名信息</a></li>
                        <li onclick="showMenu_manage2()" id="enroll_info_manage"><a
                            href="GetApplyInfoServlet2">管理报名信息</a></li>
                        <li onclick="showMenu_add2()" id="enroll_info_add"><a
                            href="addApplyinfo.jsp">新增报名信息</a></li>
                    </ul>
                    <li id="introduce" onclick="showIntroduceMenu()"><a>学校简介信息管理</a> <img
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
                    <li id="account" onclick="showAccountMenu()"><a>收费信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala5"></li>
                    <ul class="account_menu" id="click_account_menu"
                        style="display: none">
                        <li onclick="showMenu_info5()" id="account_info"><a href="SearchClassTime">查看本学期上课时间</a></li>
                        <li onclick="showMenu_info5()" id="account_info"><a href="ChargeManagerServlet">查看收款码信息</a></li>
                    </ul>
                </ul>
			</div>
		</div>
		<div class="box1" style="height:150px;">
			<form action="SearchApplyInfoServlet" method="post">
				<input type="text" name="teacherName" placeholder="请输入孩子的姓氏或名字"
					id="inputName" /> <input type="submit" value="点击搜索"
					id="submitSearch">
			</form>
		</div>
		<div class="box2">
			<div class="box3">
				<div style="width: 1050px; overflow-y: none; overflow-x: scroll">
					<table border="1" style="margin: 0 auto;width:2700px">
						<tr align="center" style="height: 40px">
							<td width="150">ID</td>
							<td width="150">报名电话</td>
							<td width="150">宝宝姓名</td>
							<td width="150">宝宝生日</td>
							<td width="150">宝宝性别</td>
							<td width="150">宝宝身份证号</td>
							<td width="150">宝宝过敏食物</td>
							<td width="150">家长姓名1</td>
							<td width="150">与宝宝关系1</td>
							<td width="150">家长身份证号1</td>
							<td width="150">联系方式1</td>
							<td width="150">工作单位1</td>
							<td width="150">家庭住址1</td>
							<td width="150">家长姓名2</td>
							<td width="150">与宝宝关系2</td>
							<td width="150">家长身份证号2</td>
							<td width="150">联系方式2</td>
							<td width="150">工作单位2</td>
							<td width="150">家庭住址2</td>
							<td width="150">操作</td>
						</tr>
						<c:forEach var="applyInfo" items="${page.list }">
							<tr align="center" height="40px">
								<td>${applyInfo.getId()}</td>
								<td>${applyInfo.getUserNumber()}</td>
								<td>${applyInfo.getBabyName()}</td>
								<td>${applyInfo.getBabyBirthday() }</td>
								<td>${applyInfo.getBabySex() }</td>
								<td>${applyInfo.getBabyIDnumber() }</td>
								<td>${applyInfo.getBabyAddoAllergies() }</td>
								<td>${applyInfo.getParentName1()}</td>
								<td>${applyInfo.getRelation1() }</td>
								<td>${applyInfo.getParentIDnumber1() }</td>
								<td>${applyInfo.getPhoneNumber1() }</td>
								<td>${applyInfo.getWorkSpace1() }</td>
								<td>${applyInfo.getHomeAddress1()}</td>
								<td>${applyInfo.getParentName2()}</td>
								<td>${applyInfo.getRelation2() }</td>
								<td>${applyInfo.getParentIDnumber2() }</td>
								<td>${applyInfo.getPhoneNumber2() }</td>
								<td>${applyInfo.getWorkSpace2() }</td>
								<td>${applyInfo.getHomeAddress2()}</td>
								<td><a
									href="updateApplyinfo.jsp?page=${page.currentPageNum }&id=${applyInfo.getId() }&babyName=${applyInfo.getBabyName()}&babyBirthday=${applyInfo.getBabyBirthday() }&babySex=${applyInfo.getBabySex()}&babyIDnumber=${applyInfo.getBabyIDnumber()}&babyAddoAllergies=${applyInfo.getBabyAddoAllergies()}&
									parentName1=${applyInfo.getParentName1()}&relation1=${applyInfo.getRelation1()}&parentIDnumber1=${applyInfo.getParentIDnumber1()}&phoneNumber1=${applyInfo.getPhoneNumber1()}&workSpace1=${applyInfo.getWorkSpace1()}&homeAddress1=${applyInfo.getHomeAddress1()}&userNumber=${applyInfo.getUserNumber()}&
									parentName2=${applyInfo.getParentName2()}&relation2=${applyInfo.getRelation2()}&parentIDnumber2=${applyInfo.getParentIDnumber2()}&phoneNumber2=${applyInfo.getPhoneNumber2()}&workSpace2=${applyInfo.getWorkSpace2()}&homeAddress2=${applyInfo.getHomeAddress2()}"> <img alt=""
										src="imgs/updateTeacher.png"
										style="width: 22px; height: 22px;">
									</a> 
									<a href="DeleteApplyinfoServlet?id=${applyInfo.getId() }&page=${page.currentPageNum }&size=${page.pageSize }&count=${page.totalCount }"
									> <img alt=""
											src="imgs/deleteTeacher.png"
											style="width: 22px; height: 22px;">
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="box4">
					<div class="box5">
						总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；
						<a href="GetApplyInfoServlet2?page=1" style="color: black">首页</a>
						<a href="GetApplyInfoServlet2?page=${page.prePageNum }"
							style="color: black">上一页</a>
						<a href="GetApplyInfoServlet2?page=${page.nextPageNum }"
							style="color: black">下一页</a>
						<a href="GetApplyInfoServlet2?page=${page.totalPageNum }"
							style="color: black">末页</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>