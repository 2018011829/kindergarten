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
<script type="text/javascript" src="js/schoolInfo.js"></script>
<body>
    <div id="box">
        <%@ include file="header.jsp"%>
        <div id="index_home">
            <div id="menu">
                <ul class="menu">
                    <li id="user" onclick="showUserMenu()"><a>用户信息管理</a> <img
                        src="imgs/home/xiala.png" id="xiala1"></li>
                    <ul class="user_menu" id="click_user_menu" style="display: none">
                        <li onclick="showMenu_info1()" id="user_info"><a href="#">用户信息</a></li>
                        <li onclick="showMenu_add1()" id="user_add"><a href="#">新增用户</a></li>
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
                    <li id="introduce" onclick="showIntroduceMenu()"><a href="#">管理学校简介信息</a> <img
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
                        <li onclick="showMenu_info5()" id="teacher_add"><a href="deletedTeacherManage">查看离职教师信息</a></li>
                    </ul>
                </ul>
            </div>
            <div id="schoolInfo">
                <div id="schoolInfoMenu">

                <ul class="schoolInfoMenu">
                    <li><a href="DescriptionManageServlet">环境描述</a></li>
                    <li>环境图片</li>
                    <li><a href="BasicInfoServlet">基本信息</a></li>
                    <li><a href="PhoneManageServlet">联系电话</a></li>
                </ul>
                </div>
                <div id="environment">
                <!-- 显示表格 -->
                <div style="height: 40px; line-height: 40px; margin-bottom: 20px">
                    <form action="SearchPictureServlet" style="margin-left: 500px">
                        <span> <input type="text" name="searchInfo"
                            style="height: 25px; width: 200px; vertical-align: bottom"
                            placeholder="请输入要查找的书籍信息" value="${searchInfo }">
                        </span> 
                        <span> 
                            <input value="" type="submit" style="border:none;width:40px;height:30px;background: url('imgs/home/search.png');background-size: 40px 30px; vertical-align: bottom">
                        </span>
                        <input type="hidden" name="userName" value="${userName }">
                    </form>
                </div>
                <div style="width: 1100px; text-align: center; margin-bottom: 20px;margin-left: 100px">
                    <table class="table">
                        <tr>
                            <td style="background: #F2F2F2">id</td>
                            <td style="background: #F2F2F2">图片</td>
                            <td style="background: #F2F2F2">描述id</td>
                            <td style="background: #F2F2F2">操作1</td>
                            <td style="background: #F2F2F2">操作2</td>
                        </tr>
                        <!-- 循环输出菜单 -->
                        <c:forEach var="picture" items="${page.list }">
                            <tr>
                                <td>${picture.id }</td>
                                <td><img alt="${picture.picture}" src="imgs/schoolInfoPicture/${picture.picture }"
                                    style="width: 90px; height: 120px"></td>
                                <td>${picture.descriptionId }</td>
                                <td><a
                                    href="UpdatePicureServlet?id=${picture.id }&userName=${userName }"
                                    style="color: black">修改</a></td>
                                <td><a
                                    href="DeletePictureServlet?id=${picture.id }&userName=${userName }&page=${page.prePageNum+1 }"
                                    style="color: black">删除</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div style="margin-top: 30px">
                        总共有${page.totalPageNum }页，总共有${page.totalCount }个数据； <a
                            href="PictureManageServlet?page=1&userName=${userName }"
                            style="color: black">首页</a> <a
                            href="PictureManageServlet?page=${page.prePageNum }&userName=${userName }"
                            style="color: black">上一页</a> <a
                            href="PictureManageServlet?page=${page.nextPageNum }&userName=${userName }"
                            style="color: black">下一页</a> <a
                            href="PictureManageServlet?page=${page.totalPageNum }&userName=${userName }"
                            style="color: black">末页</a>
                    </div>
                </div>

            </div>
            </div>


            

            
            
        </div>
    </div>
</body>
</html>