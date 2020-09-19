/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost:3306
 Source Schema         : dev_test

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 19/09/2020 22:16:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for channel
-- ----------------------------
DROP TABLE IF EXISTS `channel`;
CREATE TABLE `channel`  (
  `channel_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键：逻辑待定',
  `channel_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通道编号',
  `channel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通道名称\r\n\r\n\r\n',
  `signal_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信号类型',
  `data_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数模类型',
  `input_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入类型',
  `input_type_range` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入类型范围',
  `is_output_power` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否输出电源',
  `pin_num` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通道pin数',
  `measure_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属测点id',
  `enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`channel_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_type_custom_field
-- ----------------------------
DROP TABLE IF EXISTS `dev_type_custom_field`;
CREATE TABLE `dev_type_custom_field`  (
  `dev_type_custom_field_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备字段主键:dev_element_id+\"-\"+dev_type_field_name',
  `dev_element_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备类型id:对应dev_type_elements表的type为1时的主键',
  `dev_type_field_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段名称',
  `dev_type_field_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字段类型：1文本、2数字、3日期、4图片',
  PRIMARY KEY (`dev_type_custom_field_id`) USING BTREE,
  UNIQUE INDEX `dev_type_id`(`dev_element_id`, `dev_type_field_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备类型用户自定义字段表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dev_type_custom_field
-- ----------------------------
INSERT INTO `dev_type_custom_field` VALUES ('642c85c74f4943e08e48b71d42c2183d-自定义图片', '642c85c74f4943e08e48b71d42c2183d', '自定义图片', '4');
INSERT INTO `dev_type_custom_field` VALUES ('642c85c74f4943e08e48b71d42c2183d-自定义字符串', '642c85c74f4943e08e48b71d42c2183d', '自定义字符串', '1');
INSERT INTO `dev_type_custom_field` VALUES ('642c85c74f4943e08e48b71d42c2183d-自定义日期', '642c85c74f4943e08e48b71d42c2183d', '自定义日期', '3');

-- ----------------------------
-- Table structure for dev_type_custom_value
-- ----------------------------
DROP TABLE IF EXISTS `dev_type_custom_value`;
CREATE TABLE `dev_type_custom_value`  (
  `dev_type_field_value_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键：用于标识一行数据的uuid',
  `dev_element_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市面上这一类设备的标识，对应dev_type_elements表的type为1时的主键',
  `dev_type_custom_field_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备自定义的字段主键',
  `value_string` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文本值',
  `value_date` datetime(0) NULL DEFAULT NULL COMMENT '日期值',
  `value_blob` mediumblob NULL COMMENT '图片值',
  UNIQUE INDEX `dev_type_field_value_id`(`dev_type_field_value_id`, `dev_element_id`, `dev_type_custom_field_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备类型自定义字段值表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for dev_type_elements
-- ----------------------------
DROP TABLE IF EXISTS `dev_type_elements`;
CREATE TABLE `dev_type_elements`  (
  `dev_element_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组件id(当type=2)或者设备类型id(当type=1)',
  `dev_parent_element_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父组件id',
  `dev_element_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组件名称',
  `dev_type_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组件类型id：即设备类型id',
  `type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型：1是设备类型，2是设备组件',
  PRIMARY KEY (`dev_element_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备类型组件表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dev_type_elements
-- ----------------------------
INSERT INTO `dev_type_elements` VALUES ('09c6feecc1594eb99265d244fba2b888', NULL, '水泵', '09c6feecc1594eb99265d244fba2b888', '1');
INSERT INTO `dev_type_elements` VALUES ('7220557142074d41884a6d8b0b61f46b', '09c6feecc1594eb99265d244fba2b888', '非驱动端11', '09c6feecc1594eb99265d244fba2b888', '2');
INSERT INTO `dev_type_elements` VALUES ('7e6f60c12b84410cb2f3351560ca0b72', NULL, '变频电机', '7e6f60c12b84410cb2f3351560ca0b72', '1');
INSERT INTO `dev_type_elements` VALUES ('8e2b0857e8a3413faa58ea6f05dcc717', '09c6feecc1594eb99265d244fba2b888', '驱动端', '09c6feecc1594eb99265d244fba2b888', '2');
INSERT INTO `dev_type_elements` VALUES ('93f6c457065242c29297c19009f71114', '7e6f60c12b84410cb2f3351560ca0b72', '控制柜', '7e6f60c12b84410cb2f3351560ca0b72', '2');

-- ----------------------------
-- Table structure for dev_type_fixed_field
-- ----------------------------
DROP TABLE IF EXISTS `dev_type_fixed_field`;
CREATE TABLE `dev_type_fixed_field`  (
  `dev_type_field_value_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键：用于标识一行数据的uuid',
  `dev_type_operate_enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备运营商id',
  `dev_type_service_enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备服务商id',
  `dev_type_production_enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备生产商id',
  `dev_element_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '市面上这一类设备的标识，对应dev_type_elements表的type为1时的主键',
  `dev_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `dev_type_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `dev_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `dev_type_charge_user_id` int(11) NULL DEFAULT NULL COMMENT '负责人id',
  `dev_type_pic` mediumblob NULL COMMENT '设备图片',
  PRIMARY KEY (`dev_type_field_value_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设备类型固定字段表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise`  (
  `enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业编号',
  `enterprise_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `enterprise_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业类型',
  PRIMARY KEY (`enterprise_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of enterprise
-- ----------------------------
INSERT INTO `enterprise` VALUES ('32e', '德国西门子', '设备运营商');
INSERT INTO `enterprise` VALUES ('32eaa', '德州仪器', '设备生产商');
INSERT INTO `enterprise` VALUES ('32esqc', '日本三菱', '设备生产商');
INSERT INTO `enterprise` VALUES ('32esqqdefc', '韩国三星', '设备服务商');

-- ----------------------------
-- Table structure for logic_node
-- ----------------------------
DROP TABLE IF EXISTS `logic_node`;
CREATE TABLE `logic_node`  (
  `logic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键：逻辑待定',
  `logic_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '逻辑节点编号',
  `logic_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '逻辑节点名称',
  `enterprise` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`logic_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for logic_relation
-- ----------------------------
DROP TABLE IF EXISTS `logic_relation`;
CREATE TABLE `logic_relation`  (
  `logic_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑id',
  `measure_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物理测点id',
  `channel_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '通道id'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '逻辑测点与物理关系及对应通道的关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for measure
-- ----------------------------
DROP TABLE IF EXISTS `measure`;
CREATE TABLE `measure`  (
  `measure_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键：逻辑待定',
  `measure_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测点类型',
  `measure_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测点编号',
  `measure_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测点名称',
  `measure_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测点ip地址',
  `measure_channel_num` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测点通道数',
  `network_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '测点所属网关id',
  `enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`measure_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for network
-- ----------------------------
DROP TABLE IF EXISTS `network`;
CREATE TABLE `network`  (
  `network_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键：逻辑待定',
  `network_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网关类型',
  `network_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网关编号',
  `network_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网关名称',
  `network_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网关IP地址',
  `input_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输入地址',
  `output_agreement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '输出协议',
  `enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业id',
  PRIMARY KEY (`network_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id：递增',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户名',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `user_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号状态：1启用，0禁用',
  `enterprise_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属公司id',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '用户1改', '123456', '顶顶顶顶', '1234455555', '1', '32esqc');
INSERT INTO `user` VALUES (2, '用户2', '123456', NULL, NULL, '1', '32e');
INSERT INTO `user` VALUES (3, '用户3', '123456', NULL, NULL, '1', '32e');
INSERT INTO `user` VALUES (4, '用户4', '123456', NULL, '1234455555', '1', '32esqc');
INSERT INTO `user` VALUES (5, '用户1', '123456', NULL, '1234455555', '1', '32esqc');
INSERT INTO `user` VALUES (6, '用户1', '123456', NULL, NULL, '1', '32esqqdefc');
INSERT INTO `user` VALUES (7, '用户2', '123456', NULL, NULL, '1', '32esqqdefc');
INSERT INTO `user` VALUES (8, '用户3', '123456', NULL, NULL, '1', '32esqqdefc');
INSERT INTO `user` VALUES (9, '用户4', '123456', NULL, NULL, '1', '32esqqdefc');
INSERT INTO `user` VALUES (14, '用户7', '123456', NULL, NULL, '1', '32esqqdefc');

SET FOREIGN_KEY_CHECKS = 1;
