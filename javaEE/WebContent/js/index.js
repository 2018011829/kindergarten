/*
 * @Author: dell
 * @Date:   2020-12-12 15:29:21
 * @Last Modified by:   dell
 * @Last Modified time: 2020-12-12 17:34:01
 */
function showUserMenu() {
	var user = document.getElementById("user");
	var userMenu = document.getElementById("click_user_menu");
	var enroll = document.getElementById("enroll");
	var xiala = document.getElementById("xiala1");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala2 = document.getElementById("xiala2");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala3 = document.getElementById("xiala3");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala4 = document.getElementById("xiala4");
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "hidden";
	introduceMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "hidden";
	teacherMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	g("user");
	if (userMenu.style.display == "none") {
		userMenu.style.visibility = "visible";
		userMenu.style.display = "inline-block";
		xiala.src = "imgs/home/shangla.png";
	} else {
		userMenu.style.visibility = "hidden";
		userMenu.style.display = "none";
		xiala.src = "imgs/home/xiala.png";
	}
}
function showEnrollMenu() {
	var enroll = document.getElementById("enroll");
	var book = document.getElementById("book");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala = document.getElementById("xiala2");
	var userMenu = document.getElementById("click_user_menu");
	var xiala2 = document.getElementById("xiala1");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala3 = document.getElementById("xiala3");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala4 = document.getElementById("xiala4");
	userMenu.style.visibility = "hidden";
	userMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "hidden";
	introduceMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "hidden";
	teacherMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	g("enroll");
	if (enrollMenu.style.display == "none") {
		enrollMenu.style.visibility = "visible";
		enrollMenu.style.display = "inline-block";
		xiala.src = "imgs/home/shangla.png";
	} else {
		enrollMenu.style.visibility = "hidden";
		enrollMenu.style.display = "none";
		xiala.src = "imgs/home/xiala.png";
	}
}
function showIntroduceMenu() {
	var active = document.getElementById("active");
	var introduce = document.getElementById("introduce");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala = document.getElementById("xiala3");
	var userMenu = document.getElementById("click_user_menu");
	var xiala2 = document.getElementById("xiala1");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala3 = document.getElementById("xiala2");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala4 = document.getElementById("xiala4");
	userMenu.style.visibility = "hidden";
	userMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "hidden";
	teacherMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	g("introduce");
	if (introduceMenu.style.display == "none") {
		introduceMenu.style.visibility = "visible";
		introduceMenu.style.display = "inline-block";
		xiala.src = "imgs/home/shangla.png";
	} else {
		introduceMenu.style.visibility = "hidden";
		introduceMenu.style.display = "none";
		xiala.src = "imgs/home/xiala.png";
	}
}
function showTeacherMenu() {
	var teacher = document.getElementById("teacher");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala = document.getElementById("xiala4");
	var userMenu = document.getElementById("click_user_menu");
	var xiala2 = document.getElementById("xiala1");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala3 = document.getElementById("xiala2");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala4 = document.getElementById("xiala3");
	userMenu.style.visibility = "hidden";
	userMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "hidden";
	introduceMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	g("teacher");
	if (teacherMenu.style.display == "none") {
		teacherMenu.style.visibility = "visible";
		teacherMenu.style.display = "inline-block";
		xiala.src = "imgs/home/shangla.png";
	} else {
		teacherMenu.style.visibility = "hidden";
		teacherMenu.style.display = "none";
		xiala.src = "imgs/home/xiala.png";
	}
}
function showMenu_info1() {
	var userMenu = document.getElementById("click_user_menu");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala2 = document.getElementById("xiala2");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala3 = document.getElementById("xiala3");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala4 = document.getElementById("xiala4");
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "hidden";
	introduceMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "hidden";
	teacherMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";

	userMenu.style.visibility = "visible";
	userMenu.style.display = "inline-block";
	g("user_info");
}
function showMenu_add1() {
	var userMenu = document.getElementById("click_user_menu");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala2 = document.getElementById("xiala2");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala3 = document.getElementById("xiala3");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala4 = document.getElementById("xiala4");
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "hidden";
	introduceMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "hidden";
	teacherMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	userMenu.style.visibility = "visible";
	userMenu.style.display = "inline-block";
	g("user_add");
}
function showMenu_info2() {
	var enrollMenu = document.getElementById("click_enroll_menu");
	var userMenu = document.getElementById("click_user_menu");
	var xiala2 = document.getElementById("xiala1");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala3 = document.getElementById("xiala3");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala4 = document.getElementById("xiala4");
	userMenu.style.visibility = "hidden";
	userMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "hidden";
	introduceMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "hidden";
	teacherMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	enrollMenu.style.visibility = "visible";
	enrollMenu.style.display = "inline-block";
	g("enroll_info");
}
function showMenu_info3() {
	var introduceMenu = document.getElementById("click_introduce_menu");
	var userMenu = document.getElementById("click_user_menu");
	var xiala2 = document.getElementById("xiala1");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala3 = document.getElementById("xiala2");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala4 = document.getElementById("xiala4");
	userMenu.style.visibility = "hidden";
	userMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "hidden";
	teacherMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "visible";
	introduceMenu.style.display = "inline-block";
	g("introduce_info");
}
function showMenu_add3() {
	var introduceMenu = document.getElementById("click_introduce_menu");
	var userMenu = document.getElementById("click_user_menu");
	var xiala2 = document.getElementById("xiala1");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala3 = document.getElementById("xiala2");
	var teacherMenu = document.getElementById("click_teacher_menu");
	var xiala4 = document.getElementById("xiala4");
	userMenu.style.visibility = "hidden";
	userMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "hidden";
	teacherMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "visible";
	introduceMenu.style.display = "inline-block";
	g("introduce_add");
}
function showMenu_info4() {
	var teacherMenu = document.getElementById("click_teacher_menu");
	var userMenu = document.getElementById("click_user_menu");
	var xiala2 = document.getElementById("xiala1");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala3 = document.getElementById("xiala2");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala4 = document.getElementById("xiala3");
	userMenu.style.visibility = "hidden";
	userMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "hidden";
	introduceMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "visible";
	teacherMenu.style.display = "inline-block";
	g("teacher_info");
}
function showMenu_add4() {
	var teacherMenu = document.getElementById("click_teacher_menu");
	var userMenu = document.getElementById("click_user_menu");
	var xiala2 = document.getElementById("xiala1");
	var enrollMenu = document.getElementById("click_enroll_menu");
	var xiala3 = document.getElementById("xiala2");
	var introduceMenu = document.getElementById("click_introduce_menu");
	var xiala4 = document.getElementById("xiala3");
	userMenu.style.visibility = "hidden";
	userMenu.style.display = "none";
	xiala2.src = "imgs/home/xiala.png";
	enrollMenu.style.visibility = "hidden";
	enrollMenu.style.display = "none";
	xiala3.src = "imgs/home/xiala.png";
	introduceMenu.style.visibility = "hidden";
	introduceMenu.style.display = "none";
	xiala4.src = "imgs/home/xiala.png";
	teacherMenu.style.visibility = "visible";
	teacherMenu.style.display = "inline-block";
	g("teacher_add");
}
function g(x) {
	d = document.getElementsByTagName('li')
	for (p = d.length; p--;) {
		if (d[p].id != x) {
			d[p].style.backgroundColor = '#1A1B20';/* 其他 */
		} else {
			d[p].style.backgroundColor = '#009688';/* 点击的 */
		}
	}
}
function showAdminSetting() {
	var admin_settings = document.getElementById("admin_settings");
	var admin_img = document.getElementById("admin_img");
	if (admin_settings.style.display == "none") {
		admin_settings.style.visibility = "visible";
		admin_settings.style.display = "inline-block";
		admin_img.src = "imgs/home/shangla.png";
	}
}
function hiddenAdminSetting() {
	var admin_settings = document.getElementById("admin_settings");
	var admin_img = document.getElementById("admin_img");
	if (admin_settings.style.display == "inline-block") {
		admin_settings.style.visibility = "hidden";
		admin_settings.style.display = "none";
		admin_img.src = "imgs/home/xiala.png";
	}
}