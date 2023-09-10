/*
 Navicat Premium Data Transfer

 Source Server         : WEB APP
 Source Server Type    : MySQL
 Source Server Version : 100428 (10.4.28-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : web_report

 Target Server Type    : MySQL
 Target Server Version : 100428 (10.4.28-MariaDB)
 File Encoding         : 65001

 Date: 10/09/2023 10:35:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Agus');
INSERT INTO `customer` VALUES (2, 'Budi');
INSERT INTO `customer` VALUES (3, 'Cecep');
INSERT INTO `customer` VALUES (4, 'Ahmad');
INSERT INTO `customer` VALUES (5, 'Hendra');
INSERT INTO `customer` VALUES (6, 'Rahmat');
INSERT INTO `customer` VALUES (7, 'Agung');
INSERT INTO `customer` VALUES (8, 'Ridwan');
INSERT INTO `customer` VALUES (9, 'Asep');
INSERT INTO `customer` VALUES (10, 'Bima');

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `merchant_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES (1, 'Nasi Goreng');
INSERT INTO `merchant` VALUES (2, 'Nasi Padang');
INSERT INTO `merchant` VALUES (3, 'Ayam Gepuk');
INSERT INTO `merchant` VALUES (4, 'Sate Kambing');
INSERT INTO `merchant` VALUES (5, 'Bakso');

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `payment_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (1, 'Transfer');
INSERT INTO `payment` VALUES (2, 'COD');
INSERT INTO `payment` VALUES (3, 'Pay Later');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `staff_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (1, 'Hanif');
INSERT INTO `staff` VALUES (2, 'Wawan');
INSERT INTO `staff` VALUES (3, 'Bayu');
INSERT INTO `staff` VALUES (4, 'Riki');
INSERT INTO `staff` VALUES (5, 'Hadi');
INSERT INTO `staff` VALUES (6, 'Fikri');
INSERT INTO `staff` VALUES (7, 'Herman');
INSERT INTO `staff` VALUES (8, 'Ade');
INSERT INTO `staff` VALUES (9, 'Lisa');
INSERT INTO `staff` VALUES (10, 'Putri');

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction`  (
  `no_transaction` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `merchant_id` int NULL DEFAULT NULL,
  `transaction_time` timestamp NOT NULL DEFAULT current_timestamp ON UPDATE CURRENT_TIMESTAMP,
  `staff_id` int NULL DEFAULT NULL,
  `pay_amount` int NULL DEFAULT NULL,
  `payment_id` int NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL,
  `tax` int NULL DEFAULT NULL,
  `change_amount` int NULL DEFAULT NULL,
  `total_amount` int NULL DEFAULT NULL,
  `payment_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`no_transaction`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of transaction
-- ----------------------------
INSERT INTO `transaction` VALUES ('TRX212990099910', 2, '2023-09-06 21:56:10', 1, 1000000, 1, 1, 10, 100000, 1100000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099911', 1, '2023-09-06 21:57:18', 2, 1200000, 2, 3, 11, 132000, 1332000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099912', 3, '2023-09-06 21:58:06', 3, 1000000, 3, 4, 10, 100000, 1100000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099913', 4, '2023-09-06 21:58:39', 4, 900000, 1, 5, 5, 45000, 945000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099914', 3, '2023-09-06 21:59:52', 6, 700000, 1, 7, 4, 28000, 728000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099915', 1, '2023-09-06 22:04:30', 1, 950000, 1, 8, 5, 47500, 997500, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099916', 3, '2023-09-06 22:04:41', 2, 1500000, 2, 9, 10, 150000, 1650000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099917', 1, '2023-09-06 22:06:11', 6, 2000000, 3, 10, 10, 200000, 2200000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099918', 3, '2023-09-06 22:07:25', 7, 1800000, 2, 8, 10, 180000, 1980000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099919', 5, '2023-09-06 22:08:54', 4, 3000000, 3, 9, 10, 300000, 3300000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099920', 5, '2023-09-06 22:11:16', 6, 2800000, 1, 5, 10, 280000, 3080000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099921', 3, '2023-09-06 22:13:54', 4, 1230000, 1, 7, 9, 110700, 1340700, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099922', 2, '2023-09-06 23:40:16', 2, 1340000, 2, 3, 5, 67250, 1407250, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099923', 4, '2023-09-06 22:17:42', 1, 980000, 3, 6, 5, 49000, 1029000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099924', 5, '2023-09-06 22:19:02', 3, 9000000, 1, 8, 10, 900000, 9900000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099925', 2, '2023-09-06 22:19:49', 1, 800000, 1, 2, 5, 40000, 840000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099926', 3, '2023-09-06 22:20:51', 1, 760000, 1, 7, 5, 38000, 798000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099927', 2, '2023-09-06 22:21:23', 6, 890000, 2, 4, 5, 44500, 934500, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099928', 3, '2023-09-06 22:24:08', 4, 700000, 3, 6, 5, 35000, 735000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099929', 5, '2023-09-06 22:25:23', 3, 10000000, 1, 2, 7, 700000, 10700000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099930', 2, '2023-08-29 22:26:34', 1, 870000, 1, 3, 5, 43500, 913500, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099931', 3, '2023-09-03 22:27:56', 3, 600000, 1, 5, 3, 18000, 618000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099932', 4, '2023-09-06 22:29:24', 2, 980000, 2, 8, 5, 49000, 1029000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099933', 5, '2023-09-06 22:32:09', 7, 1300000, 3, 9, 10, 130000, 1430000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099934', 2, '2023-09-02 22:32:23', 2, 1299000, 1, 2, 10, 129900, 1428900, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099935', 3, '2023-09-04 22:34:12', 6, 1800000, 1, 3, 10, 180000, 1980000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099936', 5, '2023-09-06 22:36:36', 1, 890000, 2, 4, 5, 44500, 934500, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099937', 2, '2023-09-06 22:37:51', 2, 780000, 2, 8, 5, 39000, 819000, 'Paid');
INSERT INTO `transaction` VALUES ('TRX212990099938', 4, '2023-09-06 22:39:09', 4, 900000, 3, 3, 5, 45000, 945000, 'Unpaid');
INSERT INTO `transaction` VALUES ('TRX212990099939', 1, '2023-09-04 22:39:24', 3, 11000000, 1, 2, 7, 770000, 11770000, 'Paid');

SET FOREIGN_KEY_CHECKS = 1;
