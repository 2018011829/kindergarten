<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>华兴幼儿园</title>
	<link rel="stylesheet" type="text/css" href="css/login.css" />
	<style type="text/css">
		body {
		    background-image: url("imgs/home/logo.png");
		    background-size: 100%;
		    background-repeat: no-repeat;
		}
	</style>
</head>
<body>
    <div id="login_frame">
        <p id="image_logo"><img src="imgs/home/logo.png" height="100px" width="260px"></p>
     
        <form method="post" action="login">
     
            <p><label class="label_input">用户名</label><input type="text" id="username" name="userName" value="${userName }" class="text_field"/></p>
            <p><label class="label_input">密码</label><input type="password" id="password" name="userPwd" value="${userPwd }" class="text_field"/></p>
     
            <div id="login_control">
                <input type="submit" id="btn_login" value="登录"/><br>
                <a id="forget_pwd" href="forgetPwd.jsp">忘记密码？</a>&nbsp&nbsp&nbsp
                <a id="forget_pwd" href="register.jsp">注册</a><br>
                <div style="color:#990033;font-size: 14px;margin-top:10px">${errorInfo }</div>
            </div>
        </form>
    </div>
 
</body>
</html>