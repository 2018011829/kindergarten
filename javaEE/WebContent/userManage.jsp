<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>User Manage</title>
<link rel="stylesheet" type="text/css" href="css/teacherStyle.css">
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
    <div id="box">
        <%@ include file="header.jsp"%>
        <div id="index_home">
            <div id="menu">
                <ul class="menu">
                    <li id="user" onclick="showUserMenu()"><a class="jump">用户信息管理</a>
                        <img src="imgs/home/xiala.png" id="xiala1"></li>
                    <ul class="user_menu" id="click_user_menu" style="display: none">
                        <li onclick="showMenu_info1()" id="user_info"><a class="jump"
                            href="userManage">用户信息</a></li>
                    </ul>
                    <li id="enroll" onclick="showEnrollMenu()"><a class="jump">报名信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala2"></li>
                    <ul class="enroll_menu" id="click_enroll_menu"
                        style="display: none">
                        <li onclick="showMenu_info2()" id="enroll_info"><a
                            class="jump" href="GetApplyInfoServlet">查看报名信息</a></li>
                        <li onclick="showMenu_manage2()" id="enroll_info_manage"><a
                            class="jump" href="GetApplyInfoServlet2">管理报名信息</a></li>
                        <li onclick="showMenu_add2()" id="enroll_info_add"><a
                            class="jump" href="addApplyinfo.jsp">新增报名信息</a></li>
                    </ul>
                    <li id="introduce" onclick="showIntroduceMenu()"><a
                        class="jump">管理学校简介信息</a> <img src="imgs/home/xiala.png"
                        id="xiala3"></li>
                    <ul class="introduce_menu" id="click_introduce_menu"
                        style="display: none">
                        <li onclick="showMenu_info3()" id="introduce_info"><a
                            class="jump" href="DescriptionManageServlet">学校简介</a></li>
                        <li onclick="showMenu_add3()" id="introduce_add"><a
                            class="jump" href="addEnvironmentPicture.jsp">新增学校信息</a></li>
                    </ul>
                    <li id="teacher" onclick="showTeacherMenu()"><a class="jump">用户信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala4"></li>
                    <ul class="teacher_menu" id="click_teacher_menu"
                        style="display: block">
                        <li onclick="showMenu_info4()" id="teacher_info"
                            style="background: #009688"><a
                            class="jump" href="teacherManage">查看用户信息</a></li>
                        <li onclick="showMenu_add4()" id="teacher_add"><a
                            class="jump" href="addTeacher.jsp">新增用户信息</a></li>
                        <li onclick="showMenu_info5()" id="teacher_delete"><a
                            class="jump" href="deletedTeacherManage">查看离职用户信息</a></li>
                    </ul>
                    <li id="account" onclick="showAccountMenu()"><a>本学期上课安排</a><img
                        src="imgs/home/xiala.png" id="xiala5"></li>
                    <ul class="account_menu" id="click_account_menu"
                        style="display: none">
                        <li onclick="showMenu_info5()" id="account_info"><a class="jump" href="SearchClassTime">查看本学期上课时间</a></li>
                    </ul>
                </ul>
            </div>
            <div class="box1">
                <form action="searchUserByPhone" method="post">
                    <input type="text" name="userPhone" placeholder="请输入手机号"
                        id="inputName" /> <input type="submit" value="点击搜索"
                        id="submitSearch">
                </form>
            </div>
            <div class="box2">
                <div class="box3">
                    <table border="1" style="margin: 0 auto;">
                        <tr height="40px">
                            <th width="100px" style="text-align: center;">用户id</th>
                            <th width="150px" style="text-align: center;">用户手机号</th>
                            <th width="120px" style="text-align: center;">用户昵称</th>
                            <th width="100px"
                                style="padding-left: 10px; padding-right: 10px; text-align: center;">操作</th>
                        </tr>
                        <c:forEach items="${page.list }" var="user">
                            <tr align="center">
                                <td>${user.id }</td>
                                <td>${user.phone }</td>
                                <td>${user.nickname }</td>
                                <td style="padding-left: 10px; padding-right: 10px;"><a href="deleteUser?id=${user.id }"> <img alt=""
                                        src="imgs/deleteTeacher.png"
                                        style="width: 22px; height: 22px;">
                                </a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="box4">
                        <div class="box5">
                            总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；&nbsp;&nbsp;
                            <a href="userManage?page=1" style="color: black">首页</a>&nbsp;&nbsp; 
                            <a href="userManage?page=${page.prePageNum }" style="color: black">上一页</a>&nbsp;&nbsp;
                            <a href="userManage?page=${page.nextPageNum }" style="color: black">下一页</a>&nbsp;&nbsp;
                            <a href="userManage?page=${page.totalPageNum }" style="color: black">末页</a>&nbsp;&nbsp;
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>