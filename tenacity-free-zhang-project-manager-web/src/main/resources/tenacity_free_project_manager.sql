/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : tenacity_free_project_manager

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-02-23 23:11:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tenacity_free_project_manager_biz
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_biz`;
CREATE TABLE `tenacity_free_project_manager_biz` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `biz_name` varchar(50) NOT NULL COMMENT '业务线名称',
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_biz
-- ----------------------------

-- ----------------------------
-- Table structure for tenacity_free_project_manager_datatype
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_datatype`;
CREATE TABLE `tenacity_free_project_manager_datatype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '数据类型名称',
  `about` varchar(200) DEFAULT NULL COMMENT '数据类型描述',
  `biz_id` int(11) NOT NULL COMMENT '业务线ID，为0表示公共',
  `owner` varchar(100) DEFAULT NULL COMMENT '负责人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_datatype
-- ----------------------------
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('1', 'String', '字符串类型', '0', null);
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('2', 'Integer', '数字整型', '0', null);
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('3', 'Short', '短整型', '0', null);
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('4', 'Long', '长整型', '0', null);
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('5', 'Float', '单精度浮点数', '0', null);
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('6', 'Double', '双精度浮点数', '0', null);
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('7', 'Boolean', '布尔类型', '0', null);
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('8', 'DATE', '日期类型，格式“yyyy-MM-mm”', '0', null);
INSERT INTO `tenacity_free_project_manager_datatype` VALUES ('9', 'DATETIME', '日期类型，格式“yyyy-MM-mm HH:mm:ss”', '0', null);

-- ----------------------------
-- Table structure for tenacity_free_project_manager_datatype_fileds
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_datatype_fileds`;
CREATE TABLE `tenacity_free_project_manager_datatype_fileds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_datatype_id` int(11) NOT NULL COMMENT '所属，数据类型ID',
  `field_name` varchar(100) NOT NULL COMMENT '字段名称',
  `field_about` varchar(200) DEFAULT NULL COMMENT '字段描述',
  `field_datatype_id` int(11) NOT NULL COMMENT '字段数据类型ID',
  `field_type` tinyint(4) NOT NULL COMMENT '字段形式：0=默认、1=数组',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_datatype_fileds
-- ----------------------------

-- ----------------------------
-- Table structure for tenacity_free_project_manager_document
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_document`;
CREATE TABLE `tenacity_free_project_manager_document` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `group_id` int(11) NOT NULL COMMENT '分组ID',
  `name` varchar(50) NOT NULL COMMENT '接口名称',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态：0-启用、1-维护、2-废弃',
  `star_level` tinyint(4) NOT NULL COMMENT '星标等级：0-普通接口、1-一星接口',
  `request_url` varchar(100) NOT NULL COMMENT 'Request URL：相对地址',
  `request_method` varchar(20) NOT NULL COMMENT 'Request Method：如POST、GET',
  `request_headers` text COMMENT 'Request Headers：Map-JSON格式字符串',
  `query_params` text COMMENT 'Query String Parameters：VO-JSON格式字符串',
  `response_params` text COMMENT 'Response Parameters：VO-JSON格式字符串',
  `response_datatype_id` int(11) NOT NULL DEFAULT '0' COMMENT '响应数据类型ID',
  `success_resp_type` varchar(50) NOT NULL COMMENT 'Response Content-type：成功接口，如JSON、XML、HTML、TEXT、JSONP',
  `success_resp_example` text COMMENT 'Response Content：成功接口',
  `fail_resp_type` varchar(255) NOT NULL COMMENT 'Response Content-type：失败接口',
  `fail_resp_example` text COMMENT 'Response Content：失败接口',
  `remark` text COMMENT '备注',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_document
-- ----------------------------

-- ----------------------------
-- Table structure for tenacity_free_project_manager_group
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_group`;
CREATE TABLE `tenacity_free_project_manager_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `name` varchar(255) NOT NULL COMMENT '分组名称',
  `order` int(11) NOT NULL COMMENT '分组排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_group
-- ----------------------------

-- ----------------------------
-- Table structure for tenacity_free_project_manager_mock
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_mock`;
CREATE TABLE `tenacity_free_project_manager_mock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` int(11) NOT NULL COMMENT '接口ID',
  `uuid` varchar(50) NOT NULL COMMENT 'UUID',
  `resp_type` varchar(50) NOT NULL COMMENT 'Response Content-type：如JSON、XML、HTML、TEXT、JSONP',
  `resp_example` text COMMENT 'Response Content',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_mock
-- ----------------------------

-- ----------------------------
-- Table structure for tenacity_free_project_manager_project
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_project`;
CREATE TABLE `tenacity_free_project_manager_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `desc` varchar(200) DEFAULT NULL COMMENT '项目描述',
  `permission` tinyint(4) NOT NULL DEFAULT '0' COMMENT '访问权限：0-公开、1-私有',
  `base_url_product` varchar(200) NOT NULL COMMENT '跟地址：线上环境',
  `base_url_ppe` varchar(200) DEFAULT NULL COMMENT '跟地址：预发布环境',
  `base_url_qa` varchar(200) DEFAULT NULL COMMENT '跟地址：测试环境',
  `biz_id` int(11) NOT NULL DEFAULT '0' COMMENT '业务线ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_project
-- ----------------------------

-- ----------------------------
-- Table structure for tenacity_free_project_manager_test_history
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_test_history`;
CREATE TABLE `tenacity_free_project_manager_test_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `document_id` int(11) NOT NULL COMMENT '接口ID',
  `add_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `request_url` varchar(200) NOT NULL COMMENT 'Request URL：绝对地址',
  `request_method` varchar(20) NOT NULL COMMENT 'Request Method：如POST、GET',
  `request_headers` text COMMENT 'Request Headers：Map-JSON格式字符串',
  `query_params` text COMMENT 'Query String Parameters：VO-JSON格式字符串',
  `resp_type` varchar(50) DEFAULT NULL COMMENT 'Response Content-type：如JSON',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_test_history
-- ----------------------------

-- ----------------------------
-- Table structure for tenacity_free_project_manager_user
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_user`;
CREATE TABLE `tenacity_free_project_manager_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型：0-普通用户、1-超级管理员',
  `realname` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_user
-- ----------------------------
INSERT INTO `tenacity_free_project_manager_user` VALUES ('1', 'admin', '123456', '1', '管理员');

-- ----------------------------
-- Table structure for tenacity_free_project_manager_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `tenacity_free_project_manager_user_permission`;
CREATE TABLE `tenacity_free_project_manager_user_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `product_id` int(11) NOT NULL COMMENT '项目ID',
  `add_time` datetime NOT NULL COMMENT '新增时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tenacity_free_project_manager_user_permission
-- ----------------------------
