/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : qr

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 20/10/2022 23:58:42
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
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3');

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
  `certificate_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '证书唯一标识',
  `enterprise_id` bigint(20) NULL DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`certificate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11126 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of certificate_info
-- ----------------------------
INSERT INTO `certificate_info` VALUES ('124235346457', '电工证', 100, 100, 'www', 'ok', 100, '2022-10-04', '412822199911193132', '高级', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/124235346457-2022-10-06-18-35-42.png', 11122, NULL);
INSERT INTO `certificate_info` VALUES ('HNDG2022010002', '电气工程师', 88.5, 88.5, '中国电气工程师认证协会', '2022-10-12，复审通过', 88.5, '2022-10-18', '412828200002135544', '教授', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/HNDG2022010002-2022-10-07-09-02-04.png', 11123, NULL);
INSERT INTO `certificate_info` VALUES ('12312343214', '电气工程师', 88.5, 88.5, '中国电气工程师认证协会', '2022-10-12，复审通过', 88.5, '2022-10-16', '412828200002135544', '教授', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/12312343214-2022-10-07-09-02-51.png', 11124, NULL);
INSERT INTO `certificate_info` VALUES ('HND332207050688', 'Java工程师', NULL, NULL, '中国Java开发认证协会', '2022-10-18', NULL, '2022-10-20', NULL, '高级工程师', NULL, 11125, 100002);

-- ----------------------------
-- Table structure for enterprise_info
-- ----------------------------
DROP TABLE IF EXISTS `enterprise_info`;
CREATE TABLE `enterprise_info`  (
  `enterprise_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enterprise_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`enterprise_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100003 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enterprise_info
-- ----------------------------
INSERT INTO `enterprise_info` VALUES ('中国石化郑州分公司', 100002, '\r\n河南省郑州市管城回族区魏庄东里2号院2号', '95388');

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
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证件照路径',
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户唯一标识',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11125 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('sususu', '412822199911193132', 18, '16638421569', '', '男', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/2022-10-06-18-35-25-defaultAva.png', 11122);
INSERT INTO `user_info` VALUES ('黄欢', '412828200002135544', 22, '17719158821', 'zzuli', '女', 'https://newsystem1771915.oss-cn-beijing.aliyuncs.com/qr/HNDG2022010001-2022-10-04-12-17-03.png', 11124);

SET FOREIGN_KEY_CHECKS = 1;
