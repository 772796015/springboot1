/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 29/06/2020 17:18:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(255) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (5, '张三', 5, '长沙', '13100000000');
INSERT INTO `t_user` VALUES (1, '李思', 5, '长沙', '13100000000');
INSERT INTO `t_user` VALUES (NULL, '张三', 20, '长沙', '13100000000');
INSERT INTO `t_user` VALUES (NULL, '张三', 20, '长沙', '13100000000');

SET FOREIGN_KEY_CHECKS = 1;



-- 学生
CREATE TABLE IF NOT EXISTS `student`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(100) ,
   `tid` INT ,
   PRIMARY KEY ( `id` )
)

-- 教师表
CREATE TABLE IF NOT EXISTS `teacher`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(100) ,
   PRIMARY KEY ( `id` )
)

INSERT INTO ``(`id`, `name`, `tid`) VALUES (1, '张三', 1);
INSERT INTO ``(`id`, `name`, `tid`) VALUES (2, '李四', 1);
INSERT INTO ``(`id`, `name`, `tid`) VALUES (3, '王五', 1);
INSERT INTO ``(`id`, `name`, `tid`) VALUES (4, '宋六', 2);

INSERT INTO ``(`id`, `name`) VALUES (1, '李勤');
