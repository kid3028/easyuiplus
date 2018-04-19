/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : easyui

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-19 21:51:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL,
  `text` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `menu_order` smallint(10) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', null, '首页', null, null, 'closed');
INSERT INTO `menu` VALUES ('2', '1', '系统管理', null, null, 'closed');
INSERT INTO `menu` VALUES ('3', '2', '菜单管理', null, null, 'open');
INSERT INTO `menu` VALUES ('4', '2', '角色管理', null, null, 'open');
INSERT INTO `menu` VALUES ('5', '2', '权限管理', null, null, 'open');
INSERT INTO `menu` VALUES ('6', '2', '用户管理', '/user/dataGrid', null, 'open');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_name` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('5', '李四2', 'lisi', '123456', '2018-04-15 17:35:52', '2018-04-15 17:35:52');
INSERT INTO `user` VALUES ('6', '李四3', 'lisi3', '123456', '2018-04-15 17:36:08', '2018-04-15 17:36:08');
INSERT INTO `user` VALUES ('7', '王五', 'wangwu', '123456', '2018-04-15 17:36:31', '2018-04-15 17:36:31');
INSERT INTO `user` VALUES ('8', '王五1', 'wangwu1', '123456', '2018-04-15 17:36:53', '2018-04-15 17:36:53');
INSERT INTO `user` VALUES ('25', 'admin', 'admin', '123456', '2018-04-16 22:24:13', '2018-04-16 22:24:13');
INSERT INTO `user` VALUES ('26', '唐三', 'tangsan', '123456', '2018-04-16 22:24:33', '2018-04-16 22:24:33');
INSERT INTO `user` VALUES ('27', '萧炎', 'xiaoyan', '123456', '2018-04-16 22:24:56', '2018-04-16 22:24:56');
INSERT INTO `user` VALUES ('28', '赵六', 'zhaoliu', '123456', '2018-04-16 22:25:28', '2018-04-16 22:25:28');
INSERT INTO `user` VALUES ('29', '张三', 'zhangsan', '123456', '2018-04-17 09:50:43', '2018-04-17 09:50:43');
INSERT INTO `user` VALUES ('30', 'aaaaaa', 'aaaaaa', '123456', '2018-04-18 09:15:34', '2018-04-18 09:15:34');
INSERT INTO `user` VALUES ('31', 'bbbbb', 'bbbb', '123456', '2018-04-18 09:16:11', '2018-04-18 09:16:11');
INSERT INTO `user` VALUES ('32', 'AAAA', 'AAAAA', '123456', '2018-04-19 13:01:06', '2018-04-19 13:01:06');
INSERT INTO `user` VALUES ('33', '华仔', 'huazai', '123456', '2018-04-19 14:27:04', '2018-04-19 14:27:04');
INSERT INTO `user` VALUES ('34', '诸葛亮', 'zhugeliang', '123456', '2018-04-19 14:29:59', '2018-04-19 14:29:59');
