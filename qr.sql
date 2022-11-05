/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : qr

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 05/11/2022 21:41:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 1);

-- ----------------------------
-- Table structure for certificate_info
-- ----------------------------
DROP TABLE IF EXISTS `certificate_info`;
CREATE TABLE `certificate_info`  (
  `certificate_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '证书编号',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '证书类别',
  `test_grade` double NULL DEFAULT NULL COMMENT '理论成绩',
  `practice_grade` double NULL DEFAULT NULL COMMENT '实操成绩',
  `certificate_dept` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '发证机关',
  `review_result` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '复审情况',
  `total_grade` double NULL DEFAULT NULL COMMENT '总成绩',
  `certificate_date` date NULL DEFAULT NULL COMMENT '认证时间',
  `identify_code` varchar(18) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `level` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '证书等级',
  `qr_code_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '二维码路径',
  `certificate_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据库-证书id，无业务意义',
  `enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '纳税人识别号',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '证书扫描件',
  PRIMARY KEY (`certificate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11127 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of certificate_info
-- ----------------------------
INSERT INTO `certificate_info` VALUES ('124235346457', '电工证', 100, 100, 'www', 'ok', 100, '2022-10-04', '412822199911193132', '高级', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/124235346457-2022-10-06-18-35-42.png', 11122, NULL, NULL);
INSERT INTO `certificate_info` VALUES ('HNDG2022010002', '电气工程师', 88.5, 88.5, '中国电气工程师认证协会', '2022-10-12，复审通过', 88.5, '2022-10-18', '412828200002135544', '教授', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/HNDG2022010002-2022-10-07-09-02-04.png', 11123, NULL, NULL);
INSERT INTO `certificate_info` VALUES ('12312343214', '电气工程师', 88.5, 88.5, '中国电气工程师认证协会', '2022-10-12，复审通过', 88.5, '2022-10-16', '412828200002135544', '教授', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/12312343214-2022-10-07-09-02-51.png', 11124, NULL, NULL);
INSERT INTO `certificate_info` VALUES ('HND332207050688', 'Java工程师', NULL, NULL, '中国Java开发认证协会', '2022-10-18', NULL, '2022-10-20', NULL, '高级工程师', NULL, 11125, '100002', NULL);
INSERT INTO `certificate_info` VALUES ('5563228585', '电气工程师', NULL, NULL, '河南', NULL, NULL, '2022-11-08', NULL, NULL, 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/5563228585-2022-11-05-21-38-52.png', 11126, '河南', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/base.jpg');

-- ----------------------------
-- Table structure for enterprise_info
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_info`;
CREATE TABLE `enterprise_info`  (
  `enterprise_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '纳税人识别号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通讯地址',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通讯方式',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据库-单位ID，无业务意义',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of enterprise_info
-- ----------------------------
INSERT INTO `enterprise_info` VALUES ('中国石化郑州分公司', '100002', '\r\n河南省郑州市管城回族区魏庄东里2号院2号', '95388', 1);
INSERT INTO `enterprise_info` VALUES ('河南', '河南', '河南', '16638421569', 2);

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `identify_code` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `age` smallint(2) NULL DEFAULT NULL COMMENT '年龄',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通讯地址',
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '数据库-用户id，无业务意义',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11125 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('sususu', '412822199911193132', 18, '16638421569', '', '男', 11122);
INSERT INTO `user_info` VALUES ('黄欢', '412828200002135544', 22, '17719158821', 'zzuli', '女', 11124);

SET FOREIGN_KEY_CHECKS = 1;
