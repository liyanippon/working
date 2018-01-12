/*
Navicat MySQL Data Transfer

Source Server         : mysql-connection
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : dms

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-12 16:55:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dms_document
-- ----------------------------
DROP TABLE IF EXISTS `dms_document`;
CREATE TABLE `dms_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_sn` varchar(30) NOT NULL COMMENT '文档编号',
  `author_name` varchar(20) NOT NULL COMMENT '作者名字',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `document_name` varchar(20) NOT NULL COMMENT '文档名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_document
-- ----------------------------
INSERT INTO `dms_document` VALUES ('1', '2018011200013369', '李延', '2018-01-12 11:39:57', null, '在路上');
INSERT INTO `dms_document` VALUES ('2', '2018011200023369', '王雨薇', '2018-01-12 11:40:59', null, '革命');
INSERT INTO `dms_document` VALUES ('3', '2018011200033369', '郑新宇', '2018-01-12 11:43:36', null, '天路');
INSERT INTO `dms_document` VALUES ('4', '2018011200043369', '周邦彦', '2018-01-12 11:43:41', null, '失忆');
INSERT INTO `dms_document` VALUES ('5', '2018011200053369', '何鑫', '2018-01-12 11:43:38', null, '乱世太平');
INSERT INTO `dms_document` VALUES ('6', '2018011200063369', '何鑫', '2018-01-10 11:45:53', null, '心中');
INSERT INTO `dms_document` VALUES ('7', '2018011200073369', '周邦彦', '2018-02-01 11:45:57', null, '雨中情');
INSERT INTO `dms_document` VALUES ('8', '2018011200083369', '白描', '2018-01-15 11:46:00', null, '乱');
INSERT INTO `dms_document` VALUES ('9', '2018011200093369', '四方章人', '2017-12-31 11:46:04', null, '春晖气暖');
INSERT INTO `dms_document` VALUES ('10', '2018011200103369', '秋元康', '2018-01-23 11:49:13', null, '川流不息');
INSERT INTO `dms_document` VALUES ('11', '2018011200113369', '见岳章', '2018-02-07 11:49:18', null, '惠清');
INSERT INTO `dms_document` VALUES ('12', '2018011200123369', '秋元康', '2018-01-30 11:49:22', null, '花到春来');
INSERT INTO `dms_document` VALUES ('13', '2018011200133369', '秋元康', '2018-02-08 11:49:25', null, '凌水寺');
INSERT INTO `dms_document` VALUES ('14', '2018011200143369', '徐萌', '2018-01-08 11:49:51', null, '武士');
INSERT INTO `dms_document` VALUES ('15', '2018011200153369', '秋元康', '2018-01-02 11:49:47', null, '夕阳');
INSERT INTO `dms_document` VALUES ('16', '2018011200163369', '秋元康', '2018-01-03 11:49:44', null, '闯关东');
INSERT INTO `dms_document` VALUES ('17', '2018011200173369', '秋元康', '2018-01-04 11:49:41', null, '走西口');
INSERT INTO `dms_document` VALUES ('18', '2018011200183369', '秋元康', '2018-01-03 11:49:37', null, '少年情');
INSERT INTO `dms_document` VALUES ('19', '2018011200193369', '秋元康', '2018-01-03 11:49:33', null, '离别');
INSERT INTO `dms_document` VALUES ('20', '2018011200203369', '秋元康', '2018-01-02 11:49:30', null, '走过的地方');

-- ----------------------------
-- Table structure for dms_user
-- ----------------------------
DROP TABLE IF EXISTS `dms_user`;
CREATE TABLE `dms_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `phone` decimal(15,0) NOT NULL COMMENT '电话号码',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `password` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dms_user
-- ----------------------------
INSERT INTO `dms_user` VALUES ('32', 'ajkl', '男', '17084179707', 'adf', 'asdf');
