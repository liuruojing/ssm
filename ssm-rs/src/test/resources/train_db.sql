-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : train_db

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-07-11 23:35:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tra_prizes
-- ----------------------------
DROP TABLE IF EXISTS `tra_prizes`;
CREATE TABLE `tra_prizes` (
  `prize_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `prize_name` varchar(50) NOT NULL,
  `created_time` datetime NOT NULL,
  PRIMARY KEY (`prize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
