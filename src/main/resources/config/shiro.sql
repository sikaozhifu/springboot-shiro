/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 07/05/2019 18:36:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '用户管理', '/list');
INSERT INTO `permission` VALUES (2, '用户添加/更新', '/add');
INSERT INTO `permission` VALUES (3, '用户删除', '/delete');
INSERT INTO `permission` VALUES (4, '用户编辑页面', 'user:edit');
INSERT INTO `permission` VALUES (5, '文章管理', 'post:list');
INSERT INTO `permission` VALUES (6, '文章查询', 'post:search');
INSERT INTO `permission` VALUES (7, '创建文章页面', 'post:new');
INSERT INTO `permission` VALUES (8, '添加/修改文章', 'post:save');
INSERT INTO `permission` VALUES (9, '编辑文章页面', 'post:edit');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员');
INSERT INTO `role` VALUES (2, 'author', '作者');
INSERT INTO `role` VALUES (3, 'subscriber', '订阅者');

-- ----------------------------
-- Table structure for role_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_ref`;
CREATE TABLE `role_permission_ref`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表role_permission_id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission_ref
-- ----------------------------
INSERT INTO `role_permission_ref` VALUES (1, 1, 1);
INSERT INTO `role_permission_ref` VALUES (2, 1, 2);
INSERT INTO `role_permission_ref` VALUES (3, 1, 3);
INSERT INTO `role_permission_ref` VALUES (4, 1, 4);
INSERT INTO `role_permission_ref` VALUES (5, 1, 5);
INSERT INTO `role_permission_ref` VALUES (6, 1, 6);
INSERT INTO `role_permission_ref` VALUES (7, 1, 7);
INSERT INTO `role_permission_ref` VALUES (8, 1, 8);
INSERT INTO `role_permission_ref` VALUES (9, 1, 9);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐值',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `create_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'tom', '9ade9a62fe19ed021b87f8dff0236ad7', '汤姆', 'n3R2XG', 'tom@tom.com', '2019-05-05 10:38:49', '2019-05-05 10:38:49');
INSERT INTO `user` VALUES (2, 'cat', '5b345ca8ab78e8728cc8448203246496', 'CAT', '1gvEVM', 'cat@cat.com', '2019-05-05 10:39:11', '2019-05-05 10:39:11');

-- ----------------------------
-- Table structure for user_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `user_role_ref`;
CREATE TABLE `user_role_ref`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '关联表user_role_id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role_ref
-- ----------------------------
INSERT INTO `user_role_ref` VALUES (1, 1, 1);
INSERT INTO `user_role_ref` VALUES (2, 1, 2);
INSERT INTO `user_role_ref` VALUES (3, 1, 3);
INSERT INTO `user_role_ref` VALUES (4, 2, 2);

SET FOREIGN_KEY_CHECKS = 1;
