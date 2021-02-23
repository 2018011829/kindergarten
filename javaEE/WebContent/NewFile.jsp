<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Insert title here</title>
<link rel="stylesheet" href="css/teacherStyle.css">
<script type="text/javascript" src="js/index.js"></script>
</head>
<body>
    <div id="box">
        <div id="index_home">
            <div class="box1">
            <img alt="" src="imgs/home/logo.png" style="width: 22px; height: 22px;">
                <form action="searchTeacherByName" method="post">
                    <input type="text" name="teacherName" placeholder="请输入教师名字"
                        id="inputName" /> <input type="submit" value="点击搜索"
                        id="submitSearch">
                </form>
            </div>
             <img alt="" src="imgs/home/123.jpg" >
              <img alt="" src="imgs/home/123.jpg" style="width: 220px; height: 220px;">
               <img alt="" src="imgs/home/123.jpg" style="width: 220px; height: 220px;">
                <img alt="" src="imgs/home/123.jpg" style="width: 420px; height: 402px;">
                 <img alt="" src="imgs/home/123.jpg" style="width: 622px; height: 622px;">
        </div>
    </div>
</body>
</html>