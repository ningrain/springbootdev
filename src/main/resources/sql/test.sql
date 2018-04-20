/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 20/04/2018 17:26:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for syspermission
-- ----------------------------
DROP TABLE IF EXISTS `syspermission`;
CREATE TABLE `syspermission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resourceType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parentId` int(11) NULL DEFAULT NULL,
  `parentsIds` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of syspermission
-- ----------------------------
INSERT INTO `syspermission` VALUES (1, '用户管理', 'menu', 'userInfo/userList', 'userInfo:view', 0, '0/', 1);
INSERT INTO `syspermission` VALUES (2, '用户添加', 'button', 'userInfo/userAdd', 'userInfo:add', 1, '0/1', 1);
INSERT INTO `syspermission` VALUES (3, '用户删除', 'button', 'userInfo/userDel', 'userInfo:Del', 1, '0/1', 1);

-- ----------------------------
-- Table structure for sysrole
-- ----------------------------
DROP TABLE IF EXISTS `sysrole`;
CREATE TABLE `sysrole`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sysrole
-- ----------------------------
INSERT INTO `sysrole` VALUES (1, 'admin', '管理员', 1);
INSERT INTO `sysrole` VALUES (2, 'vip', 'vip会员', 1);

-- ----------------------------
-- Table structure for sysrolepermission
-- ----------------------------
DROP TABLE IF EXISTS `sysrolepermission`;
CREATE TABLE `sysrolepermission`  (
  `roleid` int(11) NOT NULL,
  `permissionid` int(11) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sysrolepermission
-- ----------------------------
INSERT INTO `sysrolepermission` VALUES (1, 1);
INSERT INTO `sysrolepermission` VALUES (1, 2);

-- ----------------------------
-- Table structure for sysuserrole
-- ----------------------------
DROP TABLE IF EXISTS `sysuserrole`;
CREATE TABLE `sysuserrole`  (
  `uid` int(11) NULL DEFAULT NULL,
  `roleid` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sysuserrole
-- ----------------------------
INSERT INTO `sysuserrole` VALUES (1, 1);
INSERT INTO `sysuserrole` VALUES (1, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (3, '李四', 13);
INSERT INTO `user` VALUES (4, '李四', 13);
INSERT INTO `user` VALUES (5, '李四', 13);
INSERT INTO `user` VALUES (23, 'bbb', 14);
INSERT INTO `user` VALUES (24, 'bbb', 14);
INSERT INTO `user` VALUES (25, 'bbb', 14);
INSERT INTO `user` VALUES (26, 'bbb', 14);
INSERT INTO `user` VALUES (27, 'bbb', 14);
INSERT INTO `user` VALUES (28, 'bbb', 14);
INSERT INTO `user` VALUES (29, 'bbb', 14);
INSERT INTO `user` VALUES (30, 'bbb', 14);
INSERT INTO `user` VALUES (31, 'bbb', 14);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1, '管理员', 'admin', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0);

SET FOREIGN_KEY_CHECKS = 1;
