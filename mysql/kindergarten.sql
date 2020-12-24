/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50701
Source Host           : localhost:3306
Source Database       : kindergarten

Target Server Type    : MYSQL
Target Server Version : 50701
File Encoding         : 65001

Date: 2020-12-23 23:07:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_num` varchar(20) NOT NULL,
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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------

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
