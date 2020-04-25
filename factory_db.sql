/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : factory_db

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2019-12-20 11:40:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `uu_id` varchar(40) NOT NULL COMMENT '用户ID',
  `user_name` varchar(30) NOT NULL COMMENT '用户名称',
  `short_query` varchar(255) DEFAULT NULL COMMENT '简称',
  `user_age` int(3) DEFAULT NULL COMMENT '年龄',
  `user_sex` int(1) DEFAULT NULL COMMENT '性别',
  `tel_phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `department` varchar(2) NOT NULL COMMENT '部门',
  `work_age` int(3) DEFAULT NULL COMMENT '工龄',
  `admin_flag` varchar(1) NOT NULL DEFAULT '1' COMMENT '是否是管理员',
  `entry_time` datetime DEFAULT NULL COMMENT '注册时间',
  `user_addr` varchar(255) DEFAULT NULL COMMENT '地址',
  `del_flag` varchar(1) DEFAULT '0' COMMENT '离职flag',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`uu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('201911334239', 'user5', 'u5', '13', '1', '13880492489', '1', '2', '1', '2019-11-30 23:29:30', null, '0', '2019-12-18 22:46:37', '2019-12-18 23:11:13', 'admin');
INSERT INTO `user_info` VALUES ('201912335208', 'user7', 'u7', '25', '0', '13780492488', '2', '1', '1', '2019-12-18 23:12:43', null, '0', null, null, null);
INSERT INTO `user_info` VALUES ('201912335227', 'user1', 'u1', '23', '0', '15703216767', '3', '1', '1', '2019-12-01 22:13:37', null, '0', '2019-12-18 22:01:43', null, null);
INSERT INTO `user_info` VALUES ('admin', 'admin', 'ad', '24', '1', '13780492489', '1', '1', '0', '2019-11-30 17:54:31', null, '0', '2019-12-18 23:10:53', null, null);

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `pwd_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `uu_id` varchar(40) DEFAULT NULL COMMENT '用户ID',
  `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(255) DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`pwd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_password
-- ----------------------------
INSERT INTO `user_password` VALUES ('1', '201911334239', 'ICy5YqxZB1uWSwcVLSNLcA==', '2019-12-18 21:57:20', 'admin');
INSERT INTO `user_password` VALUES ('2', 'admin', '123456', '2019-12-01 21:52:49', 'admin');
INSERT INTO `user_password` VALUES ('3', '201912335227', 'gdyb21LQTcIANtvYMT7QVQ==', '2019-12-18 22:03:27', '201912335227');
