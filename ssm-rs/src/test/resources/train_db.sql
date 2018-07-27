/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : train_db

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-27 15:17:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tra_permission
-- ----------------------------
DROP TABLE IF EXISTS `tra_permission`;
CREATE TABLE `tra_permission` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) NOT NULL,
  `perimission_url` varchar(255) NOT NULL,
  `permissioon_type` bigint(20) NOT NULL,
  PRIMARY KEY (`permission_id`),
  KEY `FK_permissionType` (`permissioon_type`),
  CONSTRAINT `FK_permissionType` FOREIGN KEY (`permissioon_type`) REFERENCES `tra_permissiontype` (`permissonType_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tra_permission
-- ----------------------------
INSERT INTO `tra_permission` VALUES ('1', 'prize:add', '/v1.0/prize/add', '1');
INSERT INTO `tra_permission` VALUES ('2', 'prize:delete', '/v1.0/prize/delete/*', '1');
INSERT INTO `tra_permission` VALUES ('3', 'prize:update', '/v1.0/prize/update/*', '1');
INSERT INTO `tra_permission` VALUES ('4', 'prize:get', '/v1.0/prize/get/*', '1');

-- ----------------------------
-- Table structure for tra_permissiontype
-- ----------------------------
DROP TABLE IF EXISTS `tra_permissiontype`;
CREATE TABLE `tra_permissiontype` (
  `permissonType_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permissionType_name` varchar(255) NOT NULL,
  KEY `permissonType_id` (`permissonType_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tra_permissiontype
-- ----------------------------
INSERT INTO `tra_permissiontype` VALUES ('1', '奖品管理');

-- ----------------------------
-- Table structure for tra_prizes
-- ----------------------------
DROP TABLE IF EXISTS `tra_prizes`;
CREATE TABLE `tra_prizes` (
  `prize_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一表识',
  `prize_name` varchar(50) NOT NULL COMMENT '奖品名称',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`prize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of tra_prizes
-- ----------------------------
INSERT INTO `tra_prizes` VALUES ('15', 'string', '2018-07-26 11:49:10');
INSERT INTO `tra_prizes` VALUES ('16', 'string', '2018-07-26 12:00:43');
INSERT INTO `tra_prizes` VALUES ('17', 'string', '2018-07-26 12:01:55');
INSERT INTO `tra_prizes` VALUES ('18', 'string', '2018-07-26 12:03:16');
INSERT INTO `tra_prizes` VALUES ('19', 'string', '2018-07-26 17:25:47');
INSERT INTO `tra_prizes` VALUES ('20', 'string', '2018-07-26 17:27:47');
INSERT INTO `tra_prizes` VALUES ('21', 'string', '2018-07-26 17:31:54');
INSERT INTO `tra_prizes` VALUES ('22', 'string', '2018-07-26 17:54:20');
INSERT INTO `tra_prizes` VALUES ('23', 'string', '2018-07-27 08:46:05');
INSERT INTO `tra_prizes` VALUES ('24', 'string', '2018-07-27 09:00:45');
INSERT INTO `tra_prizes` VALUES ('25', 'string', '2018-07-27 11:06:17');
INSERT INTO `tra_prizes` VALUES ('26', 'string', '2018-07-27 14:45:56');
INSERT INTO `tra_prizes` VALUES ('27', 'string', '2018-07-27 14:54:25');
INSERT INTO `tra_prizes` VALUES ('28', 'string', '2018-07-27 14:55:07');
INSERT INTO `tra_prizes` VALUES ('29', 'string', '2018-07-27 14:56:29');
INSERT INTO `tra_prizes` VALUES ('30', 'string', '2018-07-27 14:58:23');
INSERT INTO `tra_prizes` VALUES ('31', 'string', '2018-07-27 15:05:13');

-- ----------------------------
-- Table structure for tra_role
-- ----------------------------
DROP TABLE IF EXISTS `tra_role`;
CREATE TABLE `tra_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `uniq_rolename` (`role_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tra_role
-- ----------------------------
INSERT INTO `tra_role` VALUES ('1', 'admin');

-- ----------------------------
-- Table structure for tra_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tra_role_permission`;
CREATE TABLE `tra_role_permission` (
  `role_permission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_permission_id`),
  UNIQUE KEY `uniq_roleId_permissionId` (`role_id`,`permission_id`) USING BTREE,
  KEY `FK_permission_id` (`permission_id`),
  CONSTRAINT `FK_permission_id` FOREIGN KEY (`permission_id`) REFERENCES `tra_permission` (`permission_id`),
  CONSTRAINT `FK_role_ids` FOREIGN KEY (`role_id`) REFERENCES `tra_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tra_role_permission
-- ----------------------------
INSERT INTO `tra_role_permission` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for tra_user
-- ----------------------------
DROP TABLE IF EXISTS `tra_user`;
CREATE TABLE `tra_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(15) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uniq_username` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tra_user
-- ----------------------------
INSERT INTO `tra_user` VALUES ('1', 'liuruojing', '123456');

-- ----------------------------
-- Table structure for tra_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tra_user_role`;
CREATE TABLE `tra_user_role` (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uniq_userId_roleId` (`user_id`,`role_id`) USING BTREE,
  KEY `FK_user_id` (`user_id`) USING BTREE,
  KEY `FK_role_id` (`role_id`),
  CONSTRAINT `FK_role_id` FOREIGN KEY (`role_id`) REFERENCES `tra_role` (`role_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`user_id`) REFERENCES `tra_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tra_user_role
-- ----------------------------
INSERT INTO `tra_user_role` VALUES ('1', '1', '1');
