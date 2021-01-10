<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/teacherStyle.css">
</head>
<body>
    <div class="box1">
        <form action="searchTeacherByName" method="post">
            <input type="text" name="teacherName" placeholder="请输入教师名字" id="inputName"/>
            <input type="submit" value="点击搜索" id="submitSearch">
        </form>
        <form action="addTeacher.jsp" method="post">
            <input type="submit" value="添加教师" id="submitAdd">
        </form>
    </div>
    <div class="box2">
        <div class="box3">
            <table border="1" style="margin:0 auto;">
                <tr height="40px">
                    <th width="80px" style="text-align:center;">教师id</th>
                    <th width="100px" style="text-align:center;">教师姓名</th>
                    <th width="100px" style="text-align:center;">教师职位</th>
                    <th width="120px" style="text-align:center;">联系方式</th>
                    <th width="150px" style="text-align:center;">教师图片</th>
                    <th style="text-align:center;">座右铭</th>
                    <th width="80px" style="padding-left:10px;padding-right:10px;text-align:center;">操作</th>
                </tr>
                <c:forEach items="${page.list }" var="teacher">
                <tr align="center">
                    <td>${teacher.id }</td>
                    <td>${teacher.name }</td>
                    <td>${teacher.position }</td>
                    <td>${teacher.phone }</td>
                    <td style="text-align:center;">
                        <img alt="" src="${teacher.picture }" style="width:140px;height:180px;background:#ffffff;">
                    </td>
                    <td width="200px" style="padding-left:10px;text-align:left;">${teacher.motto }</td>
                    <td style="padding-left:10px;padding-right:10px;">
                        <a href="updateTeacher?id=${teacher.id }"  style="margin-right:8px;">
                            <img alt="" src="imgs/updateTeacher.png" style="width:22px;height:22px;">
                        </a>
                        <a href="deleteTeacher?id=${teacher.id }">
                            <img alt="" src="imgs/deleteTeacher.png" style="width:22px;height:22px;">
                        </a>
                    </td>
                </tr>
                </c:forEach>
            </table>
            <div class="box4">
                <div class="box5">
                总共有${page.totalPageNum }页，总共有${page.totalCount }个数据；&nbsp;&nbsp;
                    <a href="teacherManage?page=1">首页</a>&nbsp;&nbsp;
                    <a href="teacherManage?page=${page.prePageNum }">上一页</a>&nbsp;&nbsp;
                    <a href="teacherManage?page=${page.nextPageNum }">下一页</a>&nbsp;&nbsp;
                    <a href="teacherManage?page=${page.totalPageNum }">末页</a>&nbsp;&nbsp;
                </div>
            </div>
        </div>
    </div>
</body>
</html>