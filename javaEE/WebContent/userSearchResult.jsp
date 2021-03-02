<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search User</title>
<link rel="stylesheet" type="text/css" href="css/teacherStyle.css">
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
    <div id="box">
        <%@ include file="header.jsp"%>
        <div id="index_home">
            <div id="menu">
                <%@ include file="left.jsp"%>
            </div>
            <div class="box0">
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
                            <a href="searchUserByPhone?userPhone=${phone }&page=1" style="color: black">首页</a>&nbsp;&nbsp; 
                            <a href="searchUserByPhone?userPhone=${phone }&page=${page.prePageNum }" style="color: black">上一页</a>&nbsp;&nbsp;
                            <a href="searchUserByPhone?userPhone=${phone }&page=${page.nextPageNum }" style="color: black">下一页</a>&nbsp;&nbsp;
                            <a href="searchUserByPhone?userPhone=${phone }&page=${page.totalPageNum }" style="color: black">末页</a>&nbsp;&nbsp;
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>