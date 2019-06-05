/*
 Navicat Premium Data Transfer

 Source Server         : 121.42.11.118
 Source Server Type    : MySQL
 Source Server Version : 50554
 Source Host           : localhost:3308
 Source Schema         : windbell

 Target Server Type    : MySQL
 Target Server Version : 50554
 File Encoding         : 65001

 Date: 03/06/2019 15:27:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_base_area
-- ----------------------------
DROP TABLE IF EXISTS `t_base_area`;
CREATE TABLE `t_base_area`  (
  `ID` int(6) NOT NULL,
  `NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `PARENTID` int(6) NULL DEFAULT NULL,
  `SHORTNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LEVELTYPE` int(11) NULL DEFAULT NULL,
  `CITYCODE` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ZIPCODE` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MERGERNAME` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LNG` float NULL DEFAULT NULL,
  `LAT` float NULL DEFAULT NULL,
  `PINYIN` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '地区表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_base_industry
-- ----------------------------
DROP TABLE IF EXISTS `t_base_industry`;
CREATE TABLE `t_base_industry`  (
  `ID` int(4) NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属行业名称',
  `PARENT_ID` int(4) NOT NULL,
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行业信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_base_sequence
-- ----------------------------
DROP TABLE IF EXISTS `t_base_sequence`;
CREATE TABLE `t_base_sequence`  (
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '序列名称',
  `desc` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `current_value` int(11) NOT NULL COMMENT '当前值',
  `increment` int(11) NOT NULL DEFAULT 1 COMMENT '步长',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '序列管理' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_base_sequence
-- ----------------------------
INSERT INTO `t_base_sequence` VALUES ('001', '001', 2, 1, '2019-05-12 13:15:48', '2019-05-12 13:15:57');
INSERT INTO `t_base_sequence` VALUES ('DEPT_CODE', 'DEPT_CODE', 4, 1, '2019-05-12 13:19:50', '2019-05-12 16:09:56');
INSERT INTO `t_base_sequence` VALUES ('DEPT_CODE_000003', 'DEPT_CODE_000003', 1, 1, '2019-05-12 16:30:30', NULL);
INSERT INTO `t_base_sequence` VALUES ('TENANT_NO', 'TENANT_NO', 2, 1, '2019-05-12 14:02:34', '2019-05-12 16:09:56');

-- ----------------------------
-- Table structure for t_system_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_system_dept`;
CREATE TABLE `t_system_dept`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `NUM` int(11) NULL DEFAULT NULL COMMENT '排序',
  `P_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父部门ID',
  `SIMPLE_NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简称',
  `FULL_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '全称',
  `DEPT_CODE` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `TIPS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `VERSION` int(11) NULL DEFAULT NULL COMMENT '版本（乐观锁保留字段）',
  `TENANT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户',
  `WXID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信ID',
  `DDID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉ID',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `P_ID`(`P_ID`) USING BTREE COMMENT '父部门'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_dept
-- ----------------------------
INSERT INTO `t_system_dept` VALUES ('1127453973414965250', 1, NULL, '众一科技', '众一科技', '000003', NULL, NULL, 'TE00000001', NULL, NULL, NULL, '2019-05-12 14:02:28', NULL, NULL);
INSERT INTO `t_system_dept` VALUES ('1127486025342513154', 1, NULL, '大鱼车贷', '大鱼车贷', '000004', NULL, NULL, 'TE00000002', NULL, NULL, NULL, '2019-05-12 16:09:50', NULL, NULL);
INSERT INTO `t_system_dept` VALUES ('1127491203810586626', NULL, '1127453973414965250', '研发', '研发部', '0001', NULL, NULL, NULL, NULL, NULL, NULL, '2019-05-12 16:30:25', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_system_dict`;
CREATE TABLE `t_system_dict`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `DICT_KEY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'KEY',
  `P_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级字典',
  `PKEY` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父KEY',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `TIPS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_dict
-- ----------------------------
INSERT INTO `t_system_dict` VALUES ('1038705800676401153', 'sex', NULL, NULL, '性别', NULL, NULL, '2018-09-09 16:28:55', NULL, '2018-09-11 21:10:07');
INSERT INTO `t_system_dict` VALUES ('1038705800693178369', '1', '1038705800676401153', 'sex', '男', NULL, NULL, '2018-09-11 21:10:07', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1038705800701566978', '2', '1038705800676401153', 'sex', '女', NULL, NULL, '2018-09-11 21:10:07', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039488048204693505', 'tenant_type', NULL, NULL, '客户类型', NULL, NULL, '2018-09-11 20:17:17', NULL, '2018-09-11 20:46:47');
INSERT INTO `t_system_dict` VALUES ('1039488048242442242', '0', '1039488048204693505', 'tenant_type', '平台', NULL, NULL, '2018-09-11 20:46:47', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039488048242442243', '1', '1039488048204693505', 'tenant_type', '个人', NULL, NULL, '2018-09-11 20:46:47', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039488048242442244', '2', '1039488048204693505', 'tenant_type', '企业', NULL, NULL, '2018-09-11 20:46:47', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039488048242442245', '3', '1039488048204693505', 'tenant_type', '大客户', NULL, NULL, '2018-09-11 20:46:47', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039498255924322305', 'tenant_status', NULL, NULL, '租户状态', NULL, NULL, '2018-09-11 20:57:51', NULL, '2018-10-10 22:01:58');
INSERT INTO `t_system_dict` VALUES ('1039498255924322306', '1', '1039498255924322305', 'tenant_status', '未签约', NULL, NULL, '2018-10-10 22:01:59', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039498255924322307', '2', '1039498255924322305', 'tenant_status', '未续约', NULL, NULL, '2018-10-10 22:01:59', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039498255924322308', '3', '1039498255924322305', 'tenant_status', '签约', NULL, NULL, '2018-10-10 22:01:59', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039498255924322309', '4', '1039498255924322305', 'tenant_status', '禁止签约', NULL, NULL, '2018-10-10 22:01:59', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039498255924322310', '5', '1039498255924322305', 'tenant_status', '清退', NULL, NULL, '2018-10-10 22:01:59', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1039498255924322311', '6', '1039498255924322305', 'tenant_status', '作废', NULL, NULL, '2018-10-10 22:01:59', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050026224362287106', 'product_type', NULL, NULL, '产品类型', NULL, NULL, '2018-10-10 22:12:14', NULL, '2018-10-10 22:15:36');
INSERT INTO `t_system_dict` VALUES ('1050026224379064322', '1', '1050026224362287106', 'product_type', '系统服务', NULL, NULL, '2018-10-10 22:15:36', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050026224425201666', '2', '1050026224362287106', 'product_type', '企业基础', NULL, NULL, '2018-10-10 22:15:36', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050027325107003393', 'product_status', NULL, NULL, '产品状态', NULL, NULL, '2018-10-10 22:16:37', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050027325132169217', '1', '1050027325107003393', 'product_status', '正常', NULL, NULL, '2018-10-10 22:16:37', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050027325182500866', '2', '1050027325107003393', 'product_status', '临时停用', NULL, NULL, '2018-10-10 22:16:37', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050027325207666690', '3', '1050027325107003393', 'product_status', '注销', NULL, NULL, '2018-10-10 22:16:37', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050643779200262145', 'menu_web_type', NULL, NULL, '菜单显示类型', NULL, NULL, '2018-10-12 15:06:11', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050643779221233666', '1', '1050643779200262145', 'menu_web_type', '后台', NULL, NULL, '2018-10-12 15:06:11', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050643779229622273', '2', '1050643779200262145', 'menu_web_type', '协同软件', NULL, NULL, '2018-10-12 15:06:11', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050643779246399490', '3', '1050643779200262145', 'menu_web_type', '小程序', NULL, NULL, '2018-10-12 15:06:11', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050643779250593794', '4', '1050643779200262145', 'menu_web_type', '公众号', NULL, NULL, '2018-10-12 15:06:11', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050643779258982402', '5', '1050643779200262145', 'menu_web_type', '门户', NULL, NULL, '2018-10-12 15:06:11', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659138993172482', 'yes_no', NULL, NULL, '是否', NULL, NULL, '2018-10-12 16:07:13', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659139148361729', '1', '1050659138993172482', 'yes_no', '是', NULL, NULL, '2018-10-12 16:07:13', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659139416797186', '2', '1050659138993172482', 'yes_no', '否', NULL, NULL, '2018-10-12 16:07:13', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659589155237889', 'status', NULL, NULL, '状态', NULL, NULL, '2018-10-12 16:09:01', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659589176209409', '1', '1050659589155237889', 'status', '启用', NULL, NULL, '2018-10-12 16:09:01', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659589184598017', '2', '1050659589155237889', 'status', '停用', NULL, NULL, '2018-10-12 16:09:01', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659589201375234', '3', '1050659589155237889', 'status', '注销', NULL, NULL, '2018-10-12 16:09:01', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659697343115265', 'is_open', NULL, NULL, '是否展开', NULL, NULL, '2018-10-12 16:09:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659697351503874', '1', '1050659697343115265', 'is_open', '展开', NULL, NULL, '2018-10-12 16:09:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1050659697351503875', '2', '1050659697343115265', 'is_open', '闭合', NULL, NULL, '2018-10-12 16:09:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073124534716030978', 'LOAN_STATUS', NULL, NULL, '借据状态', NULL, NULL, '2018-12-13 15:56:41', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073124534917357569', '1', '1073124534716030978', 'LOAN_STATUS', '放款中', NULL, NULL, '2018-12-13 15:56:41', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073124535152238593', '2', '1073124534716030978', 'LOAN_STATUS', '已放款', NULL, NULL, '2018-12-13 15:56:41', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073124535340982273', '3', '1073124534716030978', 'LOAN_STATUS', '已冲正', NULL, NULL, '2018-12-13 15:56:41', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073124535533920258', '4', '1073124534716030978', 'LOAN_STATUS', '已撤销', NULL, NULL, '2018-12-13 15:56:41', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073124535722663938', '5', '1073124534716030978', 'LOAN_STATUS', '已还款', NULL, NULL, '2018-12-13 15:56:41', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073124535911407617', '6', '1073124534716030978', 'LOAN_STATUS', '已结清', NULL, NULL, '2018-12-13 15:56:41', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073124536100151298', '7', '1073124534716030978', 'LOAN_STATUS', '非应记', NULL, NULL, '2018-12-13 15:56:41', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073125146409132034', 'LOAN_FORM', NULL, NULL, '贷款状态', NULL, NULL, '2018-12-13 15:59:07', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073125146644013057', '1', '1073125146409132034', 'LOAN_FORM', '正常·', NULL, NULL, '2018-12-13 15:59:07', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073125146883088386', '2', '1073125146409132034', 'LOAN_FORM', '逾期', NULL, NULL, '2018-12-13 15:59:07', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073381664970858498', 'TERM_STATUS', NULL, NULL, '本期状态', NULL, NULL, '2018-12-14 08:58:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073381665184768002', '1', '1073381664970858498', 'TERM_STATUS', '正常', NULL, NULL, '2018-12-14 08:58:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073381665398677505', '2', '1073381664970858498', 'TERM_STATUS', '逾期', NULL, NULL, '2018-12-14 08:58:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073381665608392706', '3', '1073381664970858498', 'TERM_STATUS', '已充正', NULL, NULL, '2018-12-14 08:58:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073381665826496514', '4', '1073381664970858498', 'TERM_STATUS', '已撤销', NULL, NULL, '2018-12-14 08:58:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1073381666036211713', '5', '1073381664970858498', 'TERM_STATUS', '已结清', NULL, NULL, '2018-12-14 08:58:26', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1075586464621879298', 'relation_people', NULL, NULL, '征信关系人', NULL, NULL, '2018-12-20 10:59:31', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1075586464869343234', '1', '1075586464621879298', 'relation_people', '主借', NULL, NULL, '2018-12-20 10:59:31', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1075586465112612866', '2', '1075586464621879298', 'relation_people', '配借', NULL, NULL, '2018-12-20 10:59:31', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1075586465360076801', '3', '1075586464621879298', 'relation_people', '共偿', NULL, NULL, '2018-12-20 10:59:31', NULL, NULL);
INSERT INTO `t_system_dict` VALUES ('1075586465603346434', '4', '1075586464621879298', 'relation_people', '担保人', NULL, NULL, '2018-12-20 10:59:31', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_link_dept_data_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_system_link_dept_data_auth`;
CREATE TABLE `t_system_link_dept_data_auth`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `DEPT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门ID',
  `DATA_DEPT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据部门ID',
  `TYPE` int(1) NULL DEFAULT NULL COMMENT '类型 1 包含下级 2 不包含下级',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `DEPT_ID`(`DEPT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门数据权限表关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_link_dept_data_auth
-- ----------------------------
INSERT INTO `t_system_link_dept_data_auth` VALUES ('1127443221501300737', '1127443221404831745', '1127443221404831745', 1, NULL, '2019-05-12 13:19:45', NULL, NULL);
INSERT INTO `t_system_link_dept_data_auth` VALUES ('1127444079425212417', '1127444079341326337', '1127444079341326337', 1, NULL, '2019-05-12 13:23:10', NULL, NULL);
INSERT INTO `t_system_link_dept_data_auth` VALUES ('1127453973624680449', '1127453973414965250', '1127453973414965250', 1, NULL, '2019-05-12 14:02:29', NULL, NULL);
INSERT INTO `t_system_link_dept_data_auth` VALUES ('1127486025451565058', '1127486025342513154', '1127486025342513154', 1, NULL, '2019-05-12 16:09:50', NULL, NULL);
INSERT INTO `t_system_link_dept_data_auth` VALUES ('1127491203881889793', '1127491203810586626', '1127491203810586626', 1, NULL, '2019-05-12 16:30:25', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_link_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_link_role_menu`;
CREATE TABLE `t_system_link_role_menu`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `MENU_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单ID',
  `ROLE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色ID',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_link_role_menu
-- ----------------------------
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438248370178', '1127953341855322113', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438260953089', '1127953798766022657', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438269341698', '1133024086411202561', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438277730305', '1133024774058954754', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438277730306', '1133026785395494913', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438286118913', '1133028001551399940', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438286118914', '1133028811803924993', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438302896130', '1133029041073263618', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438311284737', '1133029681988763649', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438319673345', '1133028001551399969', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438323867650', '1133028811804924993', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438323867651', '1133029041074263618', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438332256258', '1133029681988764649', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438332256259', '1133028001551399970', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438332256260', '1133028821804924993', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438332256261', '1133029021074263618', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438340644866', '1133029621988764649', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438349033474', '1133028001552399970', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438349033475', '1133028821904924993', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438349033476', '1133029021974263618', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438349033477', '1133029621088764649', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438357422081', '1133028001561399931', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438357422082', '1133038811803924993', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438357422083', '1133039041073263618', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438357422084', '1133039681988763649', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438365810690', '1133028003551399938', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438365810691', '1133028810803924993', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438365810692', '1133029047073263618', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135192438365810693', '1133029689988763649', '1127491457364652033', NULL, '2019-06-02 22:32:22', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135391348807987202', '1127953798766022657', '1127492478858031106', NULL, '2019-06-03 11:42:46', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135391348816375810', '1133024086411202561', '1127492478858031106', NULL, '2019-06-03 11:42:46', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135391348816375811', '1133024774058954754', '1127492478858031106', NULL, '2019-06-03 11:42:46', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135391348816375812', '1133026785395494913', '1127492478858031106', NULL, '2019-06-03 11:42:46', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135391348816375813', '1133028003551399938', '1127492478858031106', NULL, '2019-06-03 11:42:46', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135391348820570113', '1133028810803924993', '1127492478858031106', NULL, '2019-06-03 11:42:46', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135391348820570114', '1133029047073263618', '1127492478858031106', NULL, '2019-06-03 11:42:46', NULL, NULL);
INSERT INTO `t_system_link_role_menu` VALUES ('1135391348820570115', '1133029689988763649', '1127492478858031106', NULL, '2019-06-03 11:42:46', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_link_tenant_product
-- ----------------------------
DROP TABLE IF EXISTS `t_system_link_tenant_product`;
CREATE TABLE `t_system_link_tenant_product`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `PRO_CODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品代码',
  `TENANT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构编号',
  `START_TIME` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `END_TIME` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '状态 1 有效 2 临时停用 3 注销',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`, `PRO_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租户开通产品信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_link_tenant_product
-- ----------------------------
INSERT INTO `t_system_link_tenant_product` VALUES ('1127453973846978561', '1001', 'TE00000001', '2019-05-12 14:02:29', '2020-05-12 14:02:29', 1, NULL, '2019-05-12 14:02:29', NULL, NULL);
INSERT INTO `t_system_link_tenant_product` VALUES ('1127486025665474562', '1001', 'TE00000002', '2019-05-12 16:09:50', '2020-05-12 16:09:50', 1, NULL, '2019-05-12 16:09:50', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_link_user_data_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_system_link_user_data_auth`;
CREATE TABLE `t_system_link_user_data_auth`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `DATA_DATA_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据部门ID',
  `TYPE` int(1) NULL DEFAULT NULL COMMENT '类型 1 包含下级 2 不包含下级',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  INDEX `USER_ID`(`USER_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户数据权限表关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_system_link_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_link_user_role`;
CREATE TABLE `t_system_link_user_role`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和用户关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_system_login_log
-- ----------------------------
DROP TABLE IF EXISTS `t_system_login_log`;
CREATE TABLE `t_system_login_log`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `LOGNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `USER_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员ID',
  `SUCCEED` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否执行成功',
  `MESSAGE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '具体消息',
  `IP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录IP',
  `TENANT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户',
  `CREATETIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '登录记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_system_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_system_menu`;
CREATE TABLE `t_system_menu`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `WEB_TYPE` int(2) NULL DEFAULT NULL COMMENT '显示类型 1 PC 后台 2 钉钉||微信 3小程序 4公众号',
  `CODE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `P_ID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父编号',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `ICON` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `URL` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL地址',
  `NUM` int(3) NULL DEFAULT NULL COMMENT '菜单排序号',
  `LEVELS` int(3) NULL DEFAULT NULL COMMENT '菜单层级',
  `ISMENU` int(1) NULL DEFAULT NULL COMMENT '是否是菜单（1：是  0：不是）',
  `TIPS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `PRO_CODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属产品',
  `STATUS` int(1) NULL DEFAULT NULL COMMENT '菜单状态 :  1:启用   0:不启用',
  `ISOPEN` int(1) NULL DEFAULT NULL COMMENT '是否打开:    1:打开   0:不打开',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_menu
-- ----------------------------
INSERT INTO `t_system_menu` VALUES ('1127953341855322113', 1, 'system', NULL, '系统管理', 'ios-book', '/system', 999, NULL, 1, NULL, '1001', 1, 2, NULL, '2019-05-13 23:06:47', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1127953798766022657', 1, 'tenant', '1127953341855322113', '客户管理', 'ios-bulb', '/tenant/list', 1, NULL, 1, NULL, '1001', 1, 2, NULL, '2019-05-13 23:08:36', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133024086411202561', 1, 'tenant_add', '1127953798766022657', '新增', 'add', '/tenant/create', 1, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 22:56:07', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133024774058954754', 1, 'tenant_update', '1127953798766022657', '修改', 'ios-create-outline', '/tenant/update', 2, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 22:58:51', NULL, '2019-05-27 22:59:17');
INSERT INTO `t_system_menu` VALUES ('1133026785395494913', 1, 'tenant_delete', '1127953798766022657', '删除', 'md-trash', '/tenant/delete', 3, NULL, 2, NULL, '1001', 2, 2, NULL, '2019-05-27 23:06:50', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133028001551399940', 1, 'user', '1127953341855322113', '用户管理', 'md-git-network', '/user/tree', 2, NULL, 1, NULL, '1001', 1, 2, NULL, '2019-05-27 23:11:41', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133028001551399969', 1, 'dict', '1127953341855322113', '字典管理', 'md-git-network', '/dict/list', 5, NULL, 1, NULL, '1001', 1, 2, NULL, '2019-05-27 23:11:41', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133028001551399970', 1, 'product', '1127953341855322113', '产品管理', 'md-git-network', '/product/list', 6, NULL, 1, NULL, '1001', 1, 2, NULL, '2019-05-27 23:11:41', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133028001552399970', 1, 'menu', '1127953341855322113', '菜单管理', 'md-git-network', '/menu/tree', 7, NULL, 1, NULL, '1001', 1, 2, NULL, '2019-05-27 23:11:41', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133028001561399931', 1, 'role', '1127953341855322113', '角色管理', 'md-git-network', '/role/list', 3, NULL, 1, NULL, '1001', 1, 2, NULL, '2019-05-27 23:11:41', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133028003551399938', 1, 'dept', '1127953341855322113', '部门管理', 'md-git-network', '/dept/tree', 4, NULL, 1, NULL, '1001', 1, 2, NULL, '2019-05-27 23:11:41', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133028810803924993', 1, 'dept_create', '1133028003551399938', '新增', 'md-add', '/dept/create', 1, NULL, 2, NULL, '1001', 1, 1, NULL, '2019-05-27 23:14:53', NULL, '2019-05-27 23:16:21');
INSERT INTO `t_system_menu` VALUES ('1133028811803924993', 1, 'user_create', '1133028001551399940', '新增', 'md-add', '/user/create', 1, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:14:53', NULL, '2019-05-27 23:16:21');
INSERT INTO `t_system_menu` VALUES ('1133028811804924993', 1, 'dict_create', '1133028001551399969', '新增', 'md-add', '/dict/create', 1, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:14:53', NULL, '2019-05-27 23:16:21');
INSERT INTO `t_system_menu` VALUES ('1133028821804924993', 1, 'product_create', '1133028001551399970', '新增', 'md-add', '/product/create', 1, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:14:53', NULL, '2019-05-27 23:16:21');
INSERT INTO `t_system_menu` VALUES ('1133028821904924993', 1, 'menu_create', '1133028001552399970', '新增', 'md-add', '/menu/create', 1, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:14:53', NULL, '2019-05-27 23:16:21');
INSERT INTO `t_system_menu` VALUES ('1133029021074263618', 1, 'product_update', '1133028001551399970', '修改', 'update', '/product/update', 2, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:15:50', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133029021974263618', 1, 'menu_update', '1133028001552399970', '修改', 'update', '/menu/update', 2, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:15:50', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133029041073263618', 1, 'user_update', '1133028001551399940', '修改', 'update', '/user/update', 2, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:15:50', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133029041074263618', 1, 'dict_update', '1133028001551399969', '修改', 'update', '/dict/update', 2, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:15:50', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133029047073263618', 1, 'dept_update', '1133028003551399938', '修改', 'update', '/dept/update', 2, NULL, 1, NULL, '1001', 1, 1, NULL, '2019-05-27 23:15:50', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133029621088764649', 1, 'menu_delete', '1133028001552399970', '删除', 'md-trash', '/menu/delete', 3, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:18:23', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133029621988764649', 1, 'product_delete', '1133028001551399970', '删除', 'md-trash', '/product/delete', 3, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:18:23', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133029681988763649', 1, 'user_delete', '1133028001551399940', '删除', 'md-trash', '/user/delete', 3, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:18:23', NULL, '2019-05-28 20:52:22');
INSERT INTO `t_system_menu` VALUES ('1133029681988764649', 1, 'dict_delete', '1133028001551399969', '删除', 'md-trash', '/dict/delete', 3, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:18:23', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133029689988763649', 1, 'dept_delete', '1133028003551399938', '删除', 'md-trash', '/dept/delete', 3, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:18:23', NULL, '2019-05-28 21:03:02');
INSERT INTO `t_system_menu` VALUES ('1133038811803924993', 1, 'role_create', '1133028001561399931', '新增', 'md-add', '/role/create', 1, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:14:53', NULL, '2019-05-27 23:16:21');
INSERT INTO `t_system_menu` VALUES ('1133039041073263618', 1, 'role_update', '1133028001561399931', '修改', 'update', '/role/update', 2, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:15:50', NULL, NULL);
INSERT INTO `t_system_menu` VALUES ('1133039681988763649', 1, 'role_delete', '1133028001561399931', '删除', 'md-trash', '/role/delete', 3, NULL, 2, NULL, '1001', 1, 2, NULL, '2019-05-27 23:18:23', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `t_system_operation_log`;
CREATE TABLE `t_system_operation_log`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `LOGTYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志类型',
  `LOGNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志名称',
  `USERID` int(65) NULL DEFAULT NULL COMMENT '用户ID',
  `CLASSNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名称',
  `METHOD` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法名称',
  `CREATETIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `SUCCEED` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否成功',
  `MESSAGE` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `TENANT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_system_product
-- ----------------------------
DROP TABLE IF EXISTS `t_system_product`;
CREATE TABLE `t_system_product`  (
  `PRO_CODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品代码',
  `PRO_TYPE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品类型（系统服务类 1，企业基础服务类 2）',
  `PRO_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态：1.正常使用中2.临时停用3.注销',
  `MEMO` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`PRO_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品配置' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_product
-- ----------------------------
INSERT INTO `t_system_product` VALUES ('1001', '1', '后台管理', '1', '后台管理', NULL, '2019-05-12 13:37:21', NULL, '2019-05-12 13:47:59');
INSERT INTO `t_system_product` VALUES ('1002', '1', '用户小程序', '1', '给员工使用', NULL, '2019-05-12 16:55:40', NULL, '2019-05-20 22:52:42');

-- ----------------------------
-- Table structure for t_system_role
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role`  (
  `ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `NUM` int(11) NULL DEFAULT NULL COMMENT '序号',
  `P_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父角色ID',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `DEPT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `TIPS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提示',
  `VERSION` int(11) NULL DEFAULT NULL COMMENT '保留字段(暂时没用）',
  `TENANT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户',
  `WXID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信ID',
  `DDID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉ID',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_role
-- ----------------------------
INSERT INTO `t_system_role` VALUES ('1127491457364652033', NULL, NULL, '管理员', NULL, '管理员', NULL, '', '666', NULL, NULL, '2019-05-12 16:31:25', NULL, '2019-05-12 16:35:45');
INSERT INTO `t_system_role` VALUES ('1127492249819672577', NULL, NULL, '运营', NULL, '运营', NULL, NULL, NULL, NULL, NULL, '2019-05-12 16:34:34', NULL, NULL);
INSERT INTO `t_system_role` VALUES ('1127492478858031106', NULL, NULL, '客服', NULL, '客服', NULL, NULL, NULL, NULL, NULL, '2019-05-12 16:35:29', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_system_tenant`;
CREATE TABLE `t_system_tenant`  (
  `ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `TENANT_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员',
  `MER_TYPE` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '客户类型 0: 平台 1:个人  2:企业 3:大客户',
  `NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商户名称',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '6种状态  1:未签约 2:未续签 3:签约 4:禁止续签 5:清退 6:作废',
  `CREDIT_CODE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照',
  `LINKMAN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `PROVINCE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `CITY` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `AREA` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `ADDRESS` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `INDUSTRY_BIG` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业大类',
  `INDUSTRY_SMALL` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业小类',
  `SMS_SIGN` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短信签名',
  `MEMO` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `TENANT_ID`(`TENANT_ID`) USING BTREE,
  UNIQUE INDEX `PHONE`(`PHONE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_tenant
-- ----------------------------
INSERT INTO `t_system_tenant` VALUES ('1127453973129752578', 'TE00000001', NULL, '0', '众一科技', '1', '', '王振兴', '13112345678', '', '', '', '广东深圳众一科技有限公司', '', '', '众一科技', '', NULL, '2019-05-12 14:02:28', NULL, '2019-05-12 14:02:58');
INSERT INTO `t_system_tenant` VALUES ('1127486025174740994', 'TE00000002', NULL, '2', '大鱼车贷', '1', NULL, '小明', '14412345678', NULL, NULL, NULL, NULL, NULL, NULL, '大鱼车贷', NULL, NULL, '2019-05-12 16:09:50', NULL, NULL);

-- ----------------------------
-- Table structure for t_system_user
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user`  (
  `ID` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键ID',
  `AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `ACCOUNT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `PASSWORD` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `SALT` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MD5密码盐',
  `NAME` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名字',
  `BIRTHDAY` datetime NULL DEFAULT NULL COMMENT '生日',
  `SEX` int(11) NULL DEFAULT NULL COMMENT '性别（1：男 2：女）',
  `EMAIL` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `PHONE` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `DEPT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门ID',
  `STATUS` int(11) NULL DEFAULT NULL COMMENT '状态(1：启用  2：冻结  3：删除）',
  `IS_ADMIN` int(1) NOT NULL DEFAULT 2 COMMENT '1是管理员,2不是',
  `DDID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '钉钉ID',
  `WXID` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信ID',
  `TENANT_ID` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户',
  `CRT_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `CRT_TIME` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `MDF_USER_ID` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `MDF_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`) USING BTREE,
  UNIQUE INDEX `ACCOUNT`(`ACCOUNT`, `TENANT_ID`) USING BTREE COMMENT '账户索引'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
INSERT INTO `t_system_user` VALUES ('1125393558145175553', 'https://i.loli.net/2017/08/21/599a521472424.jpg', '15563766882', '43fd393ca4eb0ca9995328db', 'j1iFseSR', '绝月', '2019-05-12 00:00:00', 1, 'quranbo@foxmail.com', '15563766888', '1127491203810586626', 1, 0, NULL, NULL, 'TE00000001', NULL, '2019-05-06 21:35:07', NULL, '2019-05-12 16:40:18');
INSERT INTO `t_system_user` VALUES ('1127453973716955137', NULL, '13112345678', '2c84b506ed2441747fad8e42', '6jAImQ9o', '王振兴', NULL, NULL, NULL, '13112345678', '1127453973414965250', 1, 1, NULL, NULL, 'TE00000001', NULL, '2019-05-12 14:02:29', NULL, NULL);
INSERT INTO `t_system_user` VALUES ('1127486025556422658', NULL, '14412345678', 'f53994ce95805b6afad0603c', 'LSyUyY1d', '小明', NULL, NULL, NULL, '14412345678', '1127486025342513154', 1, 1, NULL, NULL, NULL, NULL, '2019-05-12 16:09:50', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
