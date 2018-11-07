/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50515
 Source Host           : localhost:3306
 Source Schema         : we_chat

 Target Server Type    : MySQL
 Target Server Version : 50515
 File Encoding         : 65001

 Date: 07/11/2018 16:42:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wc_category
-- ----------------------------
DROP TABLE IF EXISTS `wc_category`;
CREATE TABLE `wc_category`  (
  `category_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `creation_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_news
-- ----------------------------
DROP TABLE IF EXISTS `wc_news`;
CREATE TABLE `wc_news`  (
  `news_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻id',
  `news_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻标题',
  `publisher_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻发布人',
  `creation_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_show` tinyint(4) NOT NULL COMMENT '是否发布',
  `news_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻链接',
  `read_num` int(11) NOT NULL COMMENT '阅读量',
  PRIMARY KEY (`news_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_read
-- ----------------------------
DROP TABLE IF EXISTS `wc_read`;
CREATE TABLE `wc_read`  (
  `read_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dynamic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '该动态id',
  `dynamic_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '该动态类别 新闻、话题、说说',
  `reader_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '阅读用户id',
  `reader_equipment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '阅读的设备id',
  `creation_time` datetime NOT NULL COMMENT '阅读时间',
  PRIMARY KEY (`read_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_reply
-- ----------------------------
DROP TABLE IF EXISTS `wc_reply`;
CREATE TABLE `wc_reply`  (
  `reply_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '回复id',
  `reply_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '回复父id',
  `dynamic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '该动态主id',
  `reply_parent_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '被回复人的userId',
  `dynamic_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '帖子发布者的userId',
  `reply_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '回复内容',
  `creation_time` datetime NOT NULL COMMENT '回复时间',
  `is_show` tinyint(4) NOT NULL COMMENT '是否正在显示',
  `dynamic_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '该动态类别 新闻、话题、说说',
  PRIMARY KEY (`reply_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_user
-- ----------------------------
DROP TABLE IF EXISTS `wc_user`;
CREATE TABLE `wc_user`  (
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '个性签名',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `head_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '头像',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '江苏 苏州',
  `age` int(10) DEFAULT NULL,
  `background_wall` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '背景墙',
  `creation_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;