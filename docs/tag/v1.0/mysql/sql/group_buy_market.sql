/*
 Navicat Premium Data Transfer

 Source Server         : cat-centos
 Source Server Type    : MySQL
 Source Server Version : 90001
 Source Host           : 192.168.136.129:3306
 Source Schema         : group_buy_market

 Target Server Type    : MySQL
 Target Server Version : 90001
 File Encoding         : 65001

 Date: 16/04/2025 20:51:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crowd_tags
-- ----------------------------
DROP TABLE IF EXISTS `crowd_tags`;
CREATE TABLE `crowd_tags`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群ID',
  `tag_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群名称',
  `tag_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群描述',
  `statistics` int NOT NULL COMMENT '人群标签统计量',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_tag_id`(`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人群标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crowd_tags
-- ----------------------------
INSERT INTO `crowd_tags` VALUES (1, 'CROWD_01', '潜在消费用户', '潜在消费用户', 16, '2025-04-15 19:19:21', '2025-04-15 20:24:24');

-- ----------------------------
-- Table structure for crowd_tags_detail
-- ----------------------------
DROP TABLE IF EXISTS `crowd_tags_detail`;
CREATE TABLE `crowd_tags_detail`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '人群ID',
  `user_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_tag_user`(`tag_id`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人群标签明细' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crowd_tags_detail
-- ----------------------------
INSERT INTO `crowd_tags_detail` VALUES (1, 'CROWD_01', 'cat', '2024-12-28 14:42:30', '2024-12-28 14:42:30');
INSERT INTO `crowd_tags_detail` VALUES (2, 'CROWD_01', 'dog', '2024-12-28 14:42:30', '2024-12-28 14:42:30');
INSERT INTO `crowd_tags_detail` VALUES (5, 'CROWD_01', 'cat1', '2025-04-15 19:24:02', '2025-04-15 19:24:02');
INSERT INTO `crowd_tags_detail` VALUES (6, 'CROWD_01', 'cat2', '2025-04-15 19:24:02', '2025-04-15 19:24:02');
INSERT INTO `crowd_tags_detail` VALUES (7, 'CROWD_01', 'cat3', '2025-04-15 19:24:02', '2025-04-15 19:24:02');

-- ----------------------------
-- Table structure for crowd_tags_job
-- ----------------------------
DROP TABLE IF EXISTS `crowd_tags_job`;
CREATE TABLE `crowd_tags_job`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签ID',
  `batch_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '批次ID',
  `tag_type` tinyint(1) NOT NULL DEFAULT 1 COMMENT '标签类型（参与量、消费金额）',
  `tag_rule` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '标签规则（限定类型 N次）',
  `stat_start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '统计数据，开始时间',
  `stat_end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '统计数据，结束时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态；0初始、1计划（进入执行阶段）、2重置、3完成',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_batch_id`(`batch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '人群标签任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of crowd_tags_job
-- ----------------------------
INSERT INTO `crowd_tags_job` VALUES (1, 'CROWD_01', '10001', 0, '100', '2025-04-15 19:20:27', '2025-04-15 19:20:27', 0, '2025-04-15 19:20:27', '2025-04-15 19:20:27');

-- ----------------------------
-- Table structure for group_buy_activity
-- ----------------------------
DROP TABLE IF EXISTS `group_buy_activity`;
CREATE TABLE `group_buy_activity`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `activity_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `discount_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '折扣ID',
  `group_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '拼团方式（0自动成团、1达成目标拼团）',
  `take_limit_count` int NOT NULL DEFAULT 1 COMMENT '拼团次数限制',
  `target` int NOT NULL DEFAULT 1 COMMENT '拼团目标',
  `valid_time` int NOT NULL DEFAULT 15 COMMENT '拼团时长（分钟）',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '活动状态（0创建、1生效、2过期、3废弃）',
  `start_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动开始时间',
  `end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '活动结束时间',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人群标签规则标识',
  `tag_scope` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人群标签规则范围（多选；1可见限制、2参与限制）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_activity_id`(`activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '拼团活动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buy_activity
-- ----------------------------
INSERT INTO `group_buy_activity` VALUES (1, 100123, '测试活动', '25120209', 0, 4, 2, 30, 1, '2025-01-04 11:19:02', '2027-01-04 11:19:02', '1', '1', '2025-01-04 11:19:02', '2025-04-16 16:37:54');

-- ----------------------------
-- Table structure for group_buy_discount
-- ----------------------------
DROP TABLE IF EXISTS `group_buy_discount`;
CREATE TABLE `group_buy_discount`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `discount_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '折扣ID',
  `discount_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '折扣标题',
  `discount_desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '折扣描述',
  `discount_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '折扣类型（0:base、1:tag）',
  `market_plan` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'ZJ' COMMENT '营销优惠计划（ZJ:直减、MJ:满减、ZK:折扣、N元购）',
  `market_expr` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营销优惠表达式',
  `tag_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '人群标签，特定优惠限定',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_discount_id`(`discount_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group_buy_discount
-- ----------------------------
INSERT INTO `group_buy_discount` VALUES (1, '25120207', '直减优惠20元', '直减优惠20元', 0, 'ZJ', '20', NULL, '2025-01-04 11:20:35', '2025-01-04 11:20:35');
INSERT INTO `group_buy_discount` VALUES (2, '25120208', '满减优惠100-10元', '满减优惠100-10元', 0, 'MJ', '100,10', NULL, '2025-01-04 11:20:35', '2025-01-04 11:20:35');
INSERT INTO `group_buy_discount` VALUES (4, '25120209', '折扣优惠8折', '折扣优惠8折', 0, 'ZK', '0.8', NULL, '2025-01-04 11:20:35', '2025-01-04 11:20:35');
INSERT INTO `group_buy_discount` VALUES (5, '25120210', 'N元购买优惠', 'N元购买优惠', 0, 'N', '1.99', NULL, '2025-01-04 11:20:35', '2025-01-04 11:20:35');

-- ----------------------------
-- Table structure for group_buy_order
-- ----------------------------
DROP TABLE IF EXISTS `group_buy_order`;
CREATE TABLE `group_buy_order`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `team_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拼单组队ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '渠道',
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源',
  `original_price` decimal(8, 2) NOT NULL COMMENT '原始价格',
  `deduction_price` decimal(8, 2) NOT NULL COMMENT '折扣金额',
  `pay_price` decimal(8, 2) NOT NULL COMMENT '支付价格',
  `target_count` int NOT NULL COMMENT '目标数量',
  `complete_count` int NOT NULL COMMENT '完成数量',
  `lock_count` int NOT NULL COMMENT '锁单数量',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态（0-拼单中、1-完成、2-失败）',
  `valid_start_time` datetime NOT NULL COMMENT '拼团开始时间',
  `valid_end_time` datetime NOT NULL COMMENT '拼团结束时间',
  `notify_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'HTTP' COMMENT '通知类型',
  `notify_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '回调地址',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_team_id`(`team_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_buy_order_list
-- ----------------------------
DROP TABLE IF EXISTS `group_buy_order_list`;
CREATE TABLE `group_buy_order_list`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户ID',
  `team_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拼单组队ID',
  `order_id` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `end_time` datetime NOT NULL COMMENT '活动结束时间',
  `goods_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '渠道',
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源',
  `original_price` decimal(8, 2) NOT NULL COMMENT '原始价格',
  `deduction_price` decimal(8, 2) NOT NULL COMMENT '折扣金额',
  `pay_price` decimal(8, 2) NOT NULL COMMENT '支付金额',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态；0初始锁定、1消费完成、2用户退单',
  `out_trade_no` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '外部交易单号-确保外部调用唯一幂等',
  `out_trade_time` datetime NULL DEFAULT NULL COMMENT '外部交易时间',
  `biz_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业务唯一ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_order_id`(`order_id`) USING BTREE,
  INDEX `idx_user_id_activity_id`(`user_id`, `activity_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for notify_task
-- ----------------------------
DROP TABLE IF EXISTS `notify_task`;
CREATE TABLE `notify_task`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `team_id` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '拼单组队ID',
  `notify_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'HTTP' COMMENT '通知类型',
  `notify_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回调接口',
  `notify_count` int NOT NULL COMMENT '回调次数',
  `notify_status` tinyint(1) NOT NULL COMMENT '回调状态【0初始、1完成、2重试、3失败】',
  `parameter_json` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '参数对象',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `notify_mq` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '回调消息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sc_sku_activity
-- ----------------------------
DROP TABLE IF EXISTS `sc_sku_activity`;
CREATE TABLE `sc_sku_activity`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '渠道',
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源',
  `activity_id` bigint NOT NULL COMMENT '活动ID',
  `goods_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_sc_goodsid`(`source`, `channel`, `goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '渠道商品活动配置关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sc_sku_activity
-- ----------------------------
INSERT INTO `sc_sku_activity` VALUES (1, 's01', 'c01', 100123, '9890001', '2025-01-04 11:21:42', '2025-01-04 11:21:42');

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `source` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '渠道',
  `channel` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '来源',
  `goods_id` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品ID',
  `goods_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `original_price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (1, 's01', 'c01', '9890001', '《手写MyBatis：渐进式源码实践》', 100.00, '2024-12-21 11:10:06', '2024-12-21 11:10:06');

SET FOREIGN_KEY_CHECKS = 1;
