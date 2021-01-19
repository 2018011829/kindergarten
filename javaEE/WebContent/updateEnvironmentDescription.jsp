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
                <ul class="menu">
                    <li id="user" onclick="showUserMenu()"><a>用户信息管理</a> <img
                        src="imgs/home/xiala.png" id="xiala1"></li>
                    <ul class="user_menu" id="click_user_menu" style="display: none">
                        <li onclick="showMenu_info1()" id="user_info"><a
                            href="#">用户信息</a></li>
                        <li onclick="showMenu_add1()" id="user_add"><a
                            href="#">新增用户</a></li>
                    </ul>
                    <li id="enroll" onclick="showEnrollMenu()"><a>报名信息管理</a><img
                        src="imgs/home/xiala.png" id="xiala2"></li>
                    <ul class="enroll_menu" id="click_enroll_menu" style="display: none">
                        <li onclick="showMenu_info2()" id="enroll_info"><a
                            href="#">报名信息</a></li>
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
                        <li onclick="showMenu_info4()" id="teacher_info"><a>教师信息展示</a></li>
                        <li onclick="showMenu_add4()" id="teacher_add"><a>新增教师</a></li>
                    </ul>
                </ul>
            </div>
            <div id="schoolInfo">
                <fieldset>
        <div class="editBox1"style="width:100%;height:800px;text-align:center;">
            <div class="updateBox2">
               <form action="UploadDescription" method="post"  style="margin-top:30px">
                    <input type="hidden" name="teacherId" value="${teacherId }" />
                    <div class="updateBox3">
                        <div class="updateBox4">id：</div>
                        <input type="text" name="id" value="${id }" class="updateInput"/>
                    </div><br/>
                    <div class="updateBox3">
                        <div class="updateBox4">描述：</div>
                        <input type="text" name="description" value="${description }" class="updateInput"/>
                    </div><br/>
                    <input type="submit" value="提交修改信息"  id="submitUpdate"/>
                </form>
            </div>
        </div>
    </fieldset>
            </div>


            

            
            
        </div>
    </div>
</body>
</html>