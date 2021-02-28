<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div id="header">
            <div id="header_content">
                <img src="imgs/home/logo.png"> <span
                    class="left_content_text"> <b>管理平台</b>
                </span> <span class="right_content_user_settings">
                    <ul>
                        <li onmouseout="hiddenAdminSetting()"
                            onmouseover="showAdminSetting()" id="admin"><span> <b>
                                    ${userName } <c:if test="${empty userName }">
                                        <jsp:forward page="error.jsp"></jsp:forward>
                                    </c:if>
                                    <c:set value="${userName }" var="userName" scope="request"></c:set>
                            </b>
                        </span><span><img src="imgs/home/xiala.png" id="admin_img"></span></li>
                    </ul>
                    <ul id="admin_settings" style="display: none">
                        <li onmouseout="hiddenAdminSetting()"
                            onmouseover="showAdminSetting()"><a class="jump" href="adminInfo.jsp">个人信息</a></li>
                        <li onmouseout="hiddenAdminSetting()"
                            onmouseover="showAdminSetting()"><a class="jump" href="index.jsp">切换账户</a></li>
                    </ul>
                </span>
            </div>
        </div>
</body>
</html>