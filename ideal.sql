/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : ideal

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-05-08 18:35:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comment_info`
-- ----------------------------
DROP TABLE IF EXISTS `comment_info`;
CREATE TABLE `comment_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) NOT NULL COMMENT '影片ID',
  `comment_type` char(1) DEFAULT NULL COMMENT '评论类型：0:短评；1:长评',
  `comment_nick_name` varchar(50) DEFAULT NULL COMMENT '评论人昵称',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `comment_info_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='影片评价表';

-- ----------------------------
-- Records of comment_info
-- ----------------------------

-- ----------------------------
-- Table structure for `dict`
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '字典类型',
  `label` varchar(32) DEFAULT NULL COMMENT '标签名',
  `dict_value` char(1) DEFAULT NULL COMMENT '数据值',
  `description` varchar(50) DEFAULT NULL COMMENT '描述',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `remarks` varchar(50) DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) DEFAULT NULL COMMENT '删除标记；0：已删除；1：未删除',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of dict
-- ----------------------------

-- ----------------------------
-- Table structure for `movie`
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(40) DEFAULT NULL COMMENT '影片名称',
  `url` varchar(100) DEFAULT NULL,
  `rate` float DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie
-- ----------------------------

-- ----------------------------
-- Table structure for `movie_details`
-- ----------------------------
DROP TABLE IF EXISTS `movie_details`;
CREATE TABLE `movie_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chinese_name` varchar(50) DEFAULT NULL COMMENT '影片中文名',
  `english_name` varchar(50) DEFAULT NULL COMMENT '影片英文名',
  `ask` varchar(50) DEFAULT NULL COMMENT '别名',
  `images` varchar(500) DEFAULT NULL COMMENT '电影竖图海报',
  `grade` float DEFAULT NULL COMMENT '影片评分',
  `country` varchar(50) DEFAULT NULL COMMENT '制片国家',
  `film_years` date DEFAULT NULL COMMENT '年代',
  `film_types` varchar(5) DEFAULT NULL COMMENT '影片类型；（每个影片对应的类型，至少一个）',
  `introduction` mediumtext COMMENT '简介',
  `film_label` varchar(32) DEFAULT NULL COMMENT '影片标签（可以是多个，比影片类型维度更广）',
  `movie_list_id` int(11) DEFAULT NULL COMMENT '影单ID',
  `film_source` varchar(5) DEFAULT NULL COMMENT '影片来源；0:豆瓣；1:IMDB；2:letterboxd；3:烂番茄；4:Netflix；5:Facebook；',
  `medium_type` varchar(5) DEFAULT NULL COMMENT '媒体类型；0：电影；1：电视剧；2：动漫；3：纪录片；4：纪录片；5：综艺节目',
  `film_time` int(11) DEFAULT NULL COMMENT '时长（单位：分钟）',
  `heat` int(11) DEFAULT '0' COMMENT '影片热度；指数越高，排名越高',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_info_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='影片信息表';

-- ----------------------------
-- Records of movie_details
-- ----------------------------

-- ----------------------------
-- Table structure for `movie_list`
-- ----------------------------
DROP TABLE IF EXISTS `movie_list`;
CREATE TABLE `movie_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_list_id` int(11) DEFAULT NULL COMMENT '影单ID',
  `movie_info_id` int(11) DEFAULT NULL COMMENT '影片ID',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modidy_id` int(11) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `movie_list_details_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='影单详情表';

-- ----------------------------
-- Records of movie_list
-- ----------------------------

-- ----------------------------
-- Table structure for `roles`
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `description` mediumtext COMMENT '描述',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `roles_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------

-- ----------------------------
-- Table structure for `staff`
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_info_id` int(11) DEFAULT NULL COMMENT '影片信息表ID',
  `role` varchar(5) DEFAULT NULL COMMENT '角色：0：导演；1：副导演；2：主演；3：演员；4：出品人；5：制片人',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `photo` varchar(300) DEFAULT NULL COMMENT '照片，头像',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `staff_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职员表';

-- ----------------------------
-- Records of staff
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '真实姓名',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `sex` varchar(5) DEFAULT NULL COMMENT '性别',
  `phone_number` int(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `create_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_id` int(11) DEFAULT NULL COMMENT '修改人ID',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_item` varchar(32) DEFAULT NULL COMMENT '修改项',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_info_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
