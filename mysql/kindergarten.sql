/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : kindergarten

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2021-01-21 21:13:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `head_picture` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'lxl', '111', '');
INSERT INTO `admin` VALUES ('2', 'lg', '222', '');

-- ----------------------------
-- Table structure for `applyinfo`
-- ----------------------------
DROP TABLE IF EXISTS `applyinfo`;
CREATE TABLE `applyinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userNumber` varchar(11) NOT NULL,
  `babyName` varchar(255) NOT NULL,
  `babyBirthday` varchar(255) NOT NULL,
  `babySex` varchar(255) NOT NULL,
  `babyIDnumber` varchar(255) NOT NULL,
  `babyAddoAllergies` varchar(255) NOT NULL,
  `parentName1` varchar(255) NOT NULL,
  `relation1` varchar(255) NOT NULL,
  `parentIDnumber1` varchar(255) NOT NULL,
  `phoneNumber1` varchar(255) NOT NULL,
  `workSpace1` varchar(255) NOT NULL,
  `homeAddress1` varchar(255) NOT NULL,
  `parentName2` varchar(255) DEFAULT NULL,
  `relation2` varchar(255) DEFAULT NULL,
  `parentIDnumber2` varchar(255) DEFAULT NULL,
  `phoneNumber2` varchar(255) DEFAULT NULL,
  `workSpace2` varchar(255) DEFAULT NULL,
  `homeAddress2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of applyinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `child`
-- ----------------------------
DROP TABLE IF EXISTS `child`;
CREATE TABLE `child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `grade` varchar(10) NOT NULL,
  `sex` varchar(5) NOT NULL,
  `parentPhone` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child
-- ----------------------------
INSERT INTO `child` VALUES ('1', '小红', '小班', '女', '19831127142');
INSERT INTO `child` VALUES ('2', '小明', '中班', '男', '19831127142');

-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------

-- ----------------------------
-- Table structure for `kindergarten_environment_description`
-- ----------------------------
DROP TABLE IF EXISTS `kindergarten_environment_description`;
CREATE TABLE `kindergarten_environment_description` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(600) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kindergarten_environment_description
-- ----------------------------
INSERT INTO `kindergarten_environment_description` VALUES ('1', '我园环境优美，空气清新，阳光充足。\r\n宽敞的操场，硕果累累：');
INSERT INTO `kindergarten_environment_description` VALUES ('2', '充满文艺气息的楼道');
INSERT INTO `kindergarten_environment_description` VALUES ('3', '整洁的室内环境');
INSERT INTO `kindergarten_environment_description` VALUES ('4', '好玩的大滑梯（我园配备了大型玩具游乐设施供幼儿玩耍）');
INSERT INTO `kindergarten_environment_description` VALUES ('5', '温馨的区角：我园非常注重幼儿动手，动脑能力，发展幼儿思维，为此设立了活动区域');

-- ----------------------------
-- Table structure for `kindergarten_environment_picture`
-- ----------------------------
DROP TABLE IF EXISTS `kindergarten_environment_picture`;
CREATE TABLE `kindergarten_environment_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `picture` varchar(50) NOT NULL,
  `description_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kindergarten_environment_picture
-- ----------------------------
INSERT INTO `kindergarten_environment_picture` VALUES ('1', 'all.png', '1');
INSERT INTO `kindergarten_environment_picture` VALUES ('2', 'fruit1.png', '1');
INSERT INTO `kindergarten_environment_picture` VALUES ('3', 'fruit2.png', '1');
INSERT INTO `kindergarten_environment_picture` VALUES ('4', 'fruit3.png', '1');
INSERT INTO `kindergarten_environment_picture` VALUES ('5', 'fruit4.png', '1');
INSERT INTO `kindergarten_environment_picture` VALUES ('6', 'wall1.png', '2');
INSERT INTO `kindergarten_environment_picture` VALUES ('7', 'wall2.png', '2');
INSERT INTO `kindergarten_environment_picture` VALUES ('8', 'desk.png', '3');
INSERT INTO `kindergarten_environment_picture` VALUES ('9', 'drawer.png', '3');
INSERT INTO `kindergarten_environment_picture` VALUES ('10', 'play1.png', '4');
INSERT INTO `kindergarten_environment_picture` VALUES ('11', 'play2.png', '4');
INSERT INTO `kindergarten_environment_picture` VALUES ('12', 'play3.png', '4');
INSERT INTO `kindergarten_environment_picture` VALUES ('13', 'play4.png', '4');
INSERT INTO `kindergarten_environment_picture` VALUES ('14', 'angle1.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('15', 'angle2.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('16', 'angle3.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('17', 'angle4.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('18', 'angle5.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('19', 'angle6.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('20', 'angle7.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('21', 'angle8.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('22', 'angle9.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('23', 'angle10.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('24', 'angle11.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('25', 'angle12.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('26', 'angle13.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('27', 'angle14.png', '5');
INSERT INTO `kindergarten_environment_picture` VALUES ('28', 'angle15.png', '5');

-- ----------------------------
-- Table structure for `kindergarten_info`
-- ----------------------------
DROP TABLE IF EXISTS `kindergarten_info`;
CREATE TABLE `kindergarten_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `introduce_file_name` varchar(50) NOT NULL,
  `address` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kindergarten_info
-- ----------------------------
INSERT INTO `kindergarten_info` VALUES ('1', '园所简介.txt', '裕华区体育南大街华兴小区华兴小学旁');

-- ----------------------------
-- Table structure for `kindergarten_instruction`
-- ----------------------------
DROP TABLE IF EXISTS `kindergarten_instruction`;
CREATE TABLE `kindergarten_instruction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `instruction` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kindergarten_instruction
-- ----------------------------
INSERT INTO `kindergarten_instruction` VALUES ('1', '幼儿入园前到裕华区妇幼保健站体检（空腹不喝水，体检三个月内有效）');
INSERT INTO `kindergarten_instruction` VALUES ('2', '报名携带体检单、户口本和保健手册');
INSERT INTO `kindergarten_instruction` VALUES ('3', '自备被褥芯和枕头芯：被长1.40米，宽1.1米；褥长1.40米，宽0.6米；枕芯长0.42米，宽0.27米。');
INSERT INTO `kindergarten_instruction` VALUES ('4', '准备2张幼儿1寸彩色照片，办理入园档案。');

-- ----------------------------
-- Table structure for `kindergarten_phone`
-- ----------------------------
DROP TABLE IF EXISTS `kindergarten_phone`;
CREATE TABLE `kindergarten_phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kindergarten_phone
-- ----------------------------
INSERT INTO `kindergarten_phone` VALUES ('1', '0311-858857');

-- ----------------------------
-- Table structure for `relation`
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from` char(11) NOT NULL,
  `to` char(11) NOT NULL,
  `remark` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relation
-- ----------------------------

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `position` varchar(60) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `picture` varchar(100) DEFAULT NULL,
  `motto` varchar(200) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '贾静宇', '园长', '18732184631', 'teacherImgs/jiajingyu.jpg', '爱就是教育，没有爱便没有教育；不求尽善尽美，但求无愧于心。', '1');
INSERT INTO `teacher` VALUES ('2', '周萍', '后勤主任', '15832138253', 'teacherImgs/zhouping.jpg', '用我的细心、耐心和爱心，换你的安心、放心和舒心。', '1');
INSERT INTO `teacher` VALUES ('3', '丁锦', '保健主任', '13315989238', 'teacherImgs/dingjin.jpg', null, '1');
INSERT INTO `teacher` VALUES ('4', '李可冉', '教学主任', '15630182128', 'teacherImgs/likeran.jpg', '爱己之心爱人，律人之心律己', '1');
INSERT INTO `teacher` VALUES ('5', '郭立轻', '教师', '18103397561', 'teacherImgs/guoliqing.jpg', null, '1');
INSERT INTO `teacher` VALUES ('6', '尹亚红', '教师', '15803216933', 'teacherImgs/yinyahong.jpg', null, '1');
INSERT INTO `teacher` VALUES ('7', '闫慧娟', '教师', '13403214476', 'teacherImgs/yanhuijuan.jpg', null, '1');
INSERT INTO `teacher` VALUES ('8', '贾佳佳', '教师', '18931187590', 'teacherImgs/jiajiajia.jpg', null, '1');
INSERT INTO `teacher` VALUES ('9', '孙红森', '教师', '18713138712', 'teacherImgs/sunhongsen.jpg', null, '1');
INSERT INTO `teacher` VALUES ('10', '康丽', '教师', '13931860066', 'teacherImgs/kangli.jpg', null, '1');
INSERT INTO `teacher` VALUES ('11', '索素敏', '教师', '15354215535', 'teacherImgs/suosumin.jpg', null, '1');
INSERT INTO `teacher` VALUES ('12', '牛金叶', '教师', '15226590277', 'teacherImgs/niujinye.jpg', null, '1');
INSERT INTO `teacher` VALUES ('13', '冯稳', '教师', '17732170224', 'teacherImgs/fengwen.jpg', null, '1');
INSERT INTO `teacher` VALUES ('14', '张宇彤', '教师', '18031920309', 'teacherImgs/zhangyutong.jpg', null, '1');
INSERT INTO `teacher` VALUES ('15', '张敬敏', '教师', '15100110968', 'teacherImgs/zhangjingmin.jpg', null, '1');
INSERT INTO `teacher` VALUES ('16', '李艳雪', '教师', '15831121240', 'teacherImgs/liyanxue.jpg', null, '1');
INSERT INTO `teacher` VALUES ('17', '宋勋勋', '教师', '13832165042', 'teacherImgs/songxunxun.jpg', null, '1');
INSERT INTO `teacher` VALUES ('18', '高静', '教师', '13184772586', 'teacherImgs/gaojing.jpg', null, '1');
INSERT INTO `teacher` VALUES ('19', '宋术娟', '教师', '15632305770', 'teacherImgs/songshujuan.jpg', null, '1');
INSERT INTO `teacher` VALUES ('20', '韩利亚', '保育员', '15531170879', 'teacherImgs/hanliya.jpg', null, '1');
INSERT INTO `teacher` VALUES ('21', '董焕丽', '保育员', '15631169325', 'teacherImgs/donghuanli.jpg', null, '1');
INSERT INTO `teacher` VALUES ('22', '倪小娜', '保育员', '13126132773', 'teacherImgs/nixiaona.jpg', null, '1');
INSERT INTO `teacher` VALUES ('23', '杜花英', '保育员', '18233187590', 'teacherImgs/duhuaying.jpg', null, '1');
INSERT INTO `teacher` VALUES ('24', '戎计梅', '保育员', '13633212136', 'teacherImgs/rongjimei.jpg', null, '1');
INSERT INTO `teacher` VALUES ('25', '王俊杰', '保育员', '13315989238', 'teacherImgs/wangjunjie.jpg', null, '1');
INSERT INTO `teacher` VALUES ('26', '刘哲', '保育员', '13933127175', 'teacherImgs/liuzhe.jpg', null, '1');
INSERT INTO `teacher` VALUES ('27', '刘兰', '保育员', '15303292267', 'teacherImgs/liulan.jpg', null, '1');

-- ----------------------------
-- Table structure for `teacher_class`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_class`;
CREATE TABLE `teacher_class` (
  `teacher_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`teacher_id`,`class_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_class
-- ----------------------------

-- ----------------------------
-- Table structure for `teacher_course`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_course`;
CREATE TABLE `teacher_course` (
  `teacher_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`teacher_id`,`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_course
-- ----------------------------

-- ----------------------------
-- Table structure for `user_parent`
-- ----------------------------
DROP TABLE IF EXISTS `user_parent`;
CREATE TABLE `user_parent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(11) NOT NULL,
  `password` varchar(15) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `avatar` varchar(20) DEFAULT 'user_default.png',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_parent
-- ----------------------------
INSERT INTO `user_parent` VALUES ('1', '18831166551', '7777777', '七道', 'user_default.png');
INSERT INTO `user_parent` VALUES ('2', '18831166552', '77777772', '七道二', 'user_default.png');

-- ----------------------------
-- Table structure for `user_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `user_teacher`;
CREATE TABLE `user_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(11) NOT NULL,
  `password` varchar(15) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `avatar` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_teacher
-- ----------------------------
