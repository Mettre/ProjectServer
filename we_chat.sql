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

 Date: 11/11/2018 21:40:15
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
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10001 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '兴趣类---新闻' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_feedback
-- ----------------------------
DROP TABLE IF EXISTS `wc_feedback`;
CREATE TABLE `wc_feedback`  (
  `feedback_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(0) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '反馈人id',
  `creation_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '反馈内容部分',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '反馈处理状态',
  PRIMARY KEY (`feedback_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_follow
-- ----------------------------
DROP TABLE IF EXISTS `wc_follow`;
CREATE TABLE `wc_follow`  (
  `follow_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `followed_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '被关注人的id',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '关注状态:是否取消关注等',
  `creation_time` datetime NOT NULL COMMENT '关注时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`follow_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '关注关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_forum
-- ----------------------------
DROP TABLE IF EXISTS `wc_forum`;
CREATE TABLE `wc_forum`  (
  `forum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '论坛id',
  `forum_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '论坛标题',
  `publisher_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '论坛发布人',
  `creation_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_show` tinyint(4) NOT NULL COMMENT '是否发布',
  `read_num` int(11) NOT NULL COMMENT '阅读量',
  `comment_num` int(11) NOT NULL COMMENT '评论数',
  PRIMARY KEY (`forum_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_moments
-- ----------------------------
DROP TABLE IF EXISTS `wc_moments`;
CREATE TABLE `wc_moments`  (
  `moments_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '说说id',
  `moments_title` varchar(140) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '说说标题',
  `publisher_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '说说发布人',
  `creation_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_show` tinyint(4) NOT NULL COMMENT '是否发布',
  `moments_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '说说图',
  `comment_num` int(11) NOT NULL COMMENT '评论数',
  PRIMARY KEY (`moments_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_news
-- ----------------------------
DROP TABLE IF EXISTS `wc_news`;
CREATE TABLE `wc_news`  (
  `news_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '新闻id',
  `news_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻标题',
  `publisher_user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻发布人',
  `creation_time` datetime NOT NULL COMMENT '发布时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `is_show` tinyint(4) NOT NULL COMMENT '是否发布',
  `news_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻链接',
  `read_num` int(11) NOT NULL COMMENT '阅读量',
  `news_image` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '新闻列表图',
  `comment_num` int(11) NOT NULL COMMENT '评论数',
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
  `reader_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '阅读用户id',
  `reader_equipment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '阅读的设备id',
  `creation_time` datetime NOT NULL COMMENT '阅读时间',
  PRIMARY KEY (`read_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_reply
-- ----------------------------
DROP TABLE IF EXISTS `wc_reply`;
CREATE TABLE `wc_reply`  (
  `reply_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '回复id',
  `reply_parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '回复父id',
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
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '个性签名',
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `head_avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '江苏 苏州',
  `age` int(10) NULL DEFAULT NULL,
  `background_wall` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '背景墙',
  `creation_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `visitor_num` int(11) NULL DEFAULT NULL COMMENT '空间访问量',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for wc_visitor
-- ----------------------------
DROP TABLE IF EXISTS `wc_visitor`;
CREATE TABLE `wc_visitor`  (
  `visitor_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `creation_time` datetime NOT NULL COMMENT '访问时间',
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '访客id',
  `visitors_uesr` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '被访问的id',
  PRIMARY KEY (`visitor_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '访客记录表  group by' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
