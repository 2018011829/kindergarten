<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddTeacher</title>
<link rel="stylesheet" href="css/editTeacher.css">
</head>
<body>
        <div class="editBox1">
            <div class="addBox2">
               <p id="addTitle">Add Teacher Info</p>
               <form action="addTeacherManage" method="post" enctype="multipart/form-data" style="margin-top:30px">
                    <input type="text" name="teacherName" placeholder="教师姓名" class="addInput"/>
                    <br/><br/>
                    <input type="text" name="teacherPosition" placeholder="教师职位" class="addInput"/>
                    <br/><br/>
                    <input type="text" name="teacherPhone" placeholder="联系方式" class="addInput"/>
                    <br/>
                    <div class="picBox">
                        <div class="chooseBox">
                            <input type="file" name="teacherPicture" class="chooseFile"/>
                        </div>
                    </div>
                    <br/>
                    <input type="text" name="teacherMotto" placeholder="座右铭" class="addInput"/>
                    <br/><br/>
                    <input type="submit" value="提交"  id="submitAddInfo"/>
                </form>
            </div>
        </div>
</body>
</html>