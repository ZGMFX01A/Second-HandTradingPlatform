/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : test1

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 13/07/2021 16:55:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, 'admin', 'admin');
INSERT INTO `admin_user` VALUES (2, 'ljq', 'ljq');
INSERT INTO `admin_user` VALUES (3, '123', '123');

-- ----------------------------
-- Table structure for classification
-- ----------------------------
DROP TABLE IF EXISTS `classification`;
CREATE TABLE `classification`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` int NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of classification
-- ----------------------------
INSERT INTO `classification` VALUES (2, '数码产品', 0, 1);
INSERT INTO `classification` VALUES (4, '食品', 4, 1);
INSERT INTO `classification` VALUES (6, '学习资料', 0, 1);
INSERT INTO `classification` VALUES (14, '手机', 2, 2);
INSERT INTO `classification` VALUES (15, '电脑', 2, 2);
INSERT INTO `classification` VALUES (16, '四六级资料', 6, 2);
INSERT INTO `classification` VALUES (17, '可口可乐', 4, 2);
INSERT INTO `classification` VALUES (18, '体育用品', 0, 1);
INSERT INTO `classification` VALUES (19, '耳机', 2, 2);
INSERT INTO `classification` VALUES (20, '日常用品', 0, 1);
INSERT INTO `classification` VALUES (21, '夏季防暑小风扇', 20, 2);
INSERT INTO `classification` VALUES (22, '数码外设', 2, 2);
INSERT INTO `classification` VALUES (23, '考研资料', 6, 2);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_time` datetime NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  `total` double NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  `isdel` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (14, '天津工业大学', '张钟亮', '2021-06-30 10:24:50', '18892217883', 4, 2555, 1, NULL);
INSERT INTO `order` VALUES (15, '天津师范大学', '张三', '2021-06-30 10:54:27', '685854785', 4, 248, 1, NULL);
INSERT INTO `order` VALUES (16, '工大教师公寓', 'ZZL', '2021-06-30 11:35:03', '67894564', 4, 41910, 1, NULL);
INSERT INTO `order` VALUES (17, '天津师范大学', '李四', '2021-06-30 14:12:55', '135254895', 4, 32, 1, NULL);
INSERT INTO `order` VALUES (18, '北京市海淀区', '王五', '2021-06-30 14:25:38', '6894253', 4, 7, 1, NULL);
INSERT INTO `order` VALUES (19, '学生公寓5-222', '赵六', '2021-06-30 14:46:18', '135254895', 4, 3157, 1, 1);
INSERT INTO `order` VALUES (20, '天津东丽区', '王家成', '2021-06-30 19:39:33', '6895421', 3, 4980, 1, NULL);
INSERT INTO `order` VALUES (21, '山东济南', 'Jack', '2021-06-30 20:22:20', '6506714', 1, 4980, 1, NULL);
INSERT INTO `order` VALUES (22, '四川成都市', '张亮', '2021-06-30 20:25:44', '13551493553', 1, 25005, 1, NULL);
INSERT INTO `order` VALUES (23, '四川省巴中市', '源码工程师', '2021-07-02 10:47:38', '6225493', 4, 380, 1, NULL);
INSERT INTO `order` VALUES (24, '地球', 'xukai', '2021-07-02 10:50:48', '6501234', 4, 2090, 1, 1);
INSERT INTO `order` VALUES (25, 'xxxqdqwdada', 'aa', '2021-07-07 10:58:40', '12345678901', 4, 7510, 4, NULL);
INSERT INTO `order` VALUES (26, 'xxxqdqwdada', 'aa', '2021-07-07 11:09:40', '12345678901', 4, 7510, 4, NULL);
INSERT INTO `order` VALUES (27, 'xxxqdqwdada', 'aa', '2021-07-07 11:12:46', '12345678901', 4, 7510, 4, NULL);
INSERT INTO `order` VALUES (28, 'xxxqdqwdada', 'aa', '2021-07-07 11:26:11', '12345678901', 1, 7510, 4, NULL);
INSERT INTO `order` VALUES (29, 'xxxqdqwdada', 'aa', '2021-07-07 11:39:48', '12345678901', 1, 7510, 4, NULL);
INSERT INTO `order` VALUES (30, 'xxxqdqwdada', 'aa', '2021-07-07 11:39:51', '12345678901', 1, 7510, 4, NULL);
INSERT INTO `order` VALUES (31, 'xxxqdqwdada', 'aa', '2021-07-07 11:41:59', '12345678901', 1, 7510, 4, NULL);
INSERT INTO `order` VALUES (32, 'xxxqdqwdada', 'aa', '2021-07-08 09:47:05', '12345678901', 2, 2400, 4, NULL);
INSERT INTO `order` VALUES (33, 'xxxqdqwdada', 'aa', '2021-07-09 10:50:44', '12345678901', 4, 4800, 4, NULL);
INSERT INTO `order` VALUES (34, 'xxxqdqwdada', 'aa', '2021-07-09 14:42:25', '12345678901', 1, 2499, 4, NULL);

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `count` int NULL DEFAULT NULL,
  `order_id` int NULL DEFAULT NULL,
  `product_id` int NULL DEFAULT NULL,
  `sub_total` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES (21, 1, 18, 29, 7);
INSERT INTO `order_item` VALUES (22, 2, 19, 23, 50);
INSERT INTO `order_item` VALUES (23, 1, 19, 24, 699);
INSERT INTO `order_item` VALUES (24, 1, 19, 11, 2400);
INSERT INTO `order_item` VALUES (25, 1, 19, 28, 8);
INSERT INTO `order_item` VALUES (26, 1, 20, 22, 2555);
INSERT INTO `order_item` VALUES (27, 1, 20, 23, 25);
INSERT INTO `order_item` VALUES (28, 1, 20, 11, 2400);
INSERT INTO `order_item` VALUES (29, 1, 21, 22, 2555);
INSERT INTO `order_item` VALUES (30, 1, 21, 23, 25);
INSERT INTO `order_item` VALUES (31, 1, 21, 11, 2400);
INSERT INTO `order_item` VALUES (32, 6, 22, 22, 15330);
INSERT INTO `order_item` VALUES (33, 3, 22, 23, 75);
INSERT INTO `order_item` VALUES (34, 4, 22, 11, 9600);
INSERT INTO `order_item` VALUES (35, 3, 23, 25, 75);
INSERT INTO `order_item` VALUES (36, 2, 23, 27, 298);
INSERT INTO `order_item` VALUES (37, 1, 23, 29, 7);
INSERT INTO `order_item` VALUES (38, 2, 24, 24, 1398);
INSERT INTO `order_item` VALUES (39, 3, 24, 27, 447);
INSERT INTO `order_item` VALUES (40, 1, 24, 31, 245);
INSERT INTO `order_item` VALUES (41, 2, 25, 22, 5110);
INSERT INTO `order_item` VALUES (42, 1, 25, 11, 2400);
INSERT INTO `order_item` VALUES (43, 2, 26, 22, 5110);
INSERT INTO `order_item` VALUES (44, 1, 26, 11, 2400);
INSERT INTO `order_item` VALUES (45, 2, 27, 22, 5110);
INSERT INTO `order_item` VALUES (46, 1, 27, 11, 2400);
INSERT INTO `order_item` VALUES (47, 2, 28, 22, 5110);
INSERT INTO `order_item` VALUES (48, 1, 28, 11, 2400);
INSERT INTO `order_item` VALUES (49, 2, 29, 22, 5110);
INSERT INTO `order_item` VALUES (50, 1, 29, 11, 2400);
INSERT INTO `order_item` VALUES (51, 2, 30, 22, 5110);
INSERT INTO `order_item` VALUES (52, 1, 30, 11, 2400);
INSERT INTO `order_item` VALUES (53, 2, 31, 22, 5110);
INSERT INTO `order_item` VALUES (54, 1, 31, 11, 2400);
INSERT INTO `order_item` VALUES (55, 1, 32, 11, 2400);
INSERT INTO `order_item` VALUES (56, 2, 33, 11, 4800);
INSERT INTO `order_item` VALUES (57, 1, 34, 11, 2400);
INSERT INTO `order_item` VALUES (58, 1, 34, 30, 99);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `csid` int NULL DEFAULT NULL,
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_hot` int NULL DEFAULT NULL,
  `market_price` double NULL DEFAULT NULL,
  `pdate` datetime NULL DEFAULT NULL,
  `shop_price` double NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (11, 15, '交给新款 iPad Pro 的大小任务，它都能漂亮完成，甚至还游刃有余。它的强大性能会让许多笔记本电脑都羡慕不已，可用起来又是那么轻松愉悦。新设计的视网膜显示屏，不仅看着令人惊艳，触控时更能给人惊喜。而所有这一切，都与先进的移动操作系统 iOS 携手而来。iPad Pro 能做到的，不仅是满足你对当今电脑的种种所需，而且还能超越预期。', '/mall/admin/product/img/14AA990E1B5B34B1478F96AB5108B4.png', 1, 2788, '2021-07-09 17:25:53', 2400, 'Apple iPad 平板电脑 9.7英寸（金色)', NULL);
INSERT INTO `product` VALUES (22, 14, '90HZ高刷屏幕，高通骁龙855处理器。原生Android10系统', '/mall/admin/product/img/4965052D63903694F678C5EF8567FE.png', 1, 3999, '2021-06-28 14:17:18', 2555, 'oneplus7pro九成新', NULL);
INSERT INTO `product` VALUES (23, 16, '全国大学生英语四级考试大纲词汇，购买于2020年，7成新。图书内容笔划痕迹少。', '/mall/admin/product/img/187225E95F684990CD7AB3EA728132.jpg', 1, 48, '2021-06-28 14:13:15', 25, '四级词书', NULL);
INSERT INTO `product` VALUES (24, 19, 'airpods苹果耳机-九成新，附带恐龙硅胶保护壳。', '/mall/admin/product/img/0C7C00FD029B019C7297B715B2CC21.jpg', 1, 1299, '2021-06-25 16:08:16', 699, 'airpods苹果耳机-九成新', NULL);
INSERT INTO `product` VALUES (25, 21, '自带USB充电头的便携小风扇\r\n颜色：淡蓝\r\n重量：128g', '/mall/admin/product/img/9C7B5259687001BDA1F7BD2A04EFB9.jpg', 0, 48, '2021-06-25 16:14:56', 25, '夏季防暑小风扇', NULL);
INSERT INTO `product` VALUES (26, 22, '电脑键盘，非机械键盘。敲击感清脆，8成新。\r\n颜色：黑色\r\n重量：115g', '/mall/admin/product/img/53D8E88E31AAF2D075338281692C44.jpg', 0, 38, '2021-06-25 16:25:32', 25, '二手非机械键盘', NULL);
INSERT INTO `product` VALUES (27, 23, '2022考研畅销图书，8成新。章节细致，内容夯实。专为2022考研学子提供可靠教材辅助资料。\r\n     上岸学长真心推荐', '/mall/admin/product/img/CCB87D63A6BCD6B487873D32352359.png', 1, 199, '2021-06-28 14:29:46', 149, '2022考研数学张宇基础36讲', NULL);
INSERT INTO `product` VALUES (28, 16, '12套考研真题，内含详细真题解析。扫码还有配套视频讲解。只做过2套，其余全新。', '/mall/admin/product/img/C4D819F9481CF54ACEA8B4EF57B2C5.png', 1, 12, '2021-06-28 14:35:01', 8, '黄皮书张剑-英语六级考试12套真题', NULL);
INSERT INTO `product` VALUES (29, 23, '该书包含全国考研英语二大纲词汇，高频词汇、基础词汇、新增词汇，面面俱到。帮助你夯实词汇基础，为成功上岸填一份保障。', '/mall/admin/product/img/E60DFE0222337CCDA616AED72A013C.png', 0, 10, '2021-06-28 14:38:10', 7, '考研英语二词汇黄皮书书', NULL);
INSERT INTO `product` VALUES (30, 22, '电子竞技-雷蛇专用鼠标，反应速度快。闪光雷蛇logo，凸显电子竞技魅力。\r\n     游戏发烧友真心推荐！', '/mall/admin/product/img/2F7558F8D9421C002BA587CF6DD4F9.png', 1, 129, '2021-06-28 14:41:19', 99, '雷蛇有线鼠标-9成新', NULL);
INSERT INTO `product` VALUES (31, 22, '小米最新一代手环，黑色。具有NFC、心率检测、通话等功能。8成新', '/mall/admin/product/img/7141F1539E78C7CB7A43F8E98E8B02.png', 1, 299, '2021-06-28 14:43:37', 245, '小米手环', NULL);

-- ----------------------------
-- Table structure for shopcar
-- ----------------------------
DROP TABLE IF EXISTS `shopcar`;
CREATE TABLE `shopcar`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `order_id` int NULL DEFAULT NULL,
  `count` int NULL DEFAULT 1,
  `user_id` int NULL DEFAULT NULL,
  `sub_total` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2048 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shopcar
-- ----------------------------
INSERT INTO `shopcar` VALUES (2041, 11, NULL, 1, 1, NULL);
INSERT INTO `shopcar` VALUES (2043, 24, NULL, 1, 1, NULL);
INSERT INTO `shopcar` VALUES (2044, 27, NULL, 1, 1, NULL);
INSERT INTO `shopcar` VALUES (2045, 11, NULL, 1, 4, NULL);
INSERT INTO `shopcar` VALUES (2047, 30, NULL, 1, 4, NULL);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `pc_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NULL DEFAULT NULL,
  `comment` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`pc_id`) USING BTREE,
  INDEX `Fk_uc`(`user_id`) USING BTREE,
  INDEX `Fk_pc`(`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, 11, '质量不错，绝对真品', 1);
INSERT INTO `t_comment` VALUES (5, 22, '一加真不错！圈粉了', 1);
INSERT INTO `t_comment` VALUES (6, 22, '666666', 4);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'JSU', '2265940560@qq.com', '123', '123', '1234567891-', '123');
INSERT INTO `user` VALUES (2, 'JSU', '2265940561@qq.com', 'LJQ', '123', '1234567891-', 'LJQ');
INSERT INTO `user` VALUES (3, 'JSU', '2265940562@qq.com', 'haha', '123', '1234567891-', 'haha');
INSERT INTO `user` VALUES (4, 'xxxqdqwdada', '1@2.com', 'xxxxxx', 'aa', '12345678901', 'aa');

-- ----------------------------
-- Procedure structure for test
-- ----------------------------
DROP PROCEDURE IF EXISTS `test`;
delimiter ;;
CREATE PROCEDURE `test`()
BEGIN
DECLARE a,b int DEFAULT 5;
insert into T(s1) VALUES (a);
select s1*a from T where s1>=b;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
