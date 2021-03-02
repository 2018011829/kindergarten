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
        <div id="index_home">
            <%@ include file="header.jsp"%>
            <div id="menu">
               <%@ include file="left.jsp"%>
            </div>
            <div id="schoolInfo">
                <div id="schoolInfoMenu">
                </div>
                <div id="environment">
                <!-- 显示表格 -->
                <div class="addBox2">
                    <p id="addTitle">添加描述</p>
                    <form action="addTeacherManage" method="post" enctype="multipart/form-data" style="margin-top:30px">
                            <input type="text" name="id" placeholder="描述id" class="addInput"/>
                            <br/><br/>
                            <input type="text" name="description" placeholder="描述" class="addInput"/>
                            <br/><br/>
                             <input type="submit" value="提交"  id="submitAddInfo"/>
                    </form>
                </div>
            </div>
            </div>  
        </div>
    </div>
</body>
</html>