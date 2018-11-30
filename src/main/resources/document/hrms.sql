/*
 Navicat Premium Data Transfer

 Source Server         : coon
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : hrms

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 29/10/2018 10:42:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for complaint_identification_bind_record
-- ----------------------------
DROP TABLE IF EXISTS `complaint_identification_bind_record`;
CREATE TABLE `complaint_identification_bind_record`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sex` tinyint(2) NULL DEFAULT NULL,
  `agency_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `agency_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `now_identification_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `used_identification_type` tinyint(2) NULL DEFAULT NULL,
  `used_identification_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `attachment_ids` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `check_status` tinyint(2) NULL DEFAULT NULL,
  `check_situation` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `check_people` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `personal_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint_identification_bind_record
-- ----------------------------
INSERT INTO `complaint_identification_bind_record` VALUES ('2f3a08f58ccc42199096c0f82c11da43', '周旭武', 1, '1', '昆山第一人民医院', '320583198806119617', 1, 'aaaa', NULL, NULL, NULL, NULL, '1', '2018-10-28 21:08:37', 'admin', '2018-10-28 21:14:39', 'admin');

-- ----------------------------
-- Table structure for complaint_personal_information
-- ----------------------------
DROP TABLE IF EXISTS `complaint_personal_information`;
CREATE TABLE `complaint_personal_information`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sex` tinyint(2) NULL DEFAULT NULL,
  `agency_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '管理机构ID',
  `identification_type` tinyint(2) NULL DEFAULT NULL,
  `identification_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `personnel_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint_personal_information
-- ----------------------------
INSERT INTO `complaint_personal_information` VALUES ('1', '老王', 1, '', 1, '1213', NULL, '2018-10-27 19:49:32', 'admin', '2018-10-27 19:49:35', 'admin');
INSERT INTO `complaint_personal_information` VALUES ('2e189ab03147409e9417dbf7cad63397', 'Mary', 1, '1', 1, '320583198806119619', '3', '2018-10-28 13:25:30', 'admin', '2018-10-28 13:25:30', 'admin');
INSERT INTO `complaint_personal_information` VALUES ('3cb577a862314e7b9177f12e64d298d3', '张三', 2, '1', 1, '320583198806119621', '5', '2018-10-28 18:29:00', 'admin', '2018-10-28 18:29:00', 'admin');
INSERT INTO `complaint_personal_information` VALUES ('7a446f7aebe045be8846de8c01de665c', '李四', 4, '', 1, '320583198806119620', '4', '2018-10-28 12:27:47', 'admin', '2018-10-28 12:27:47', 'admin');

-- ----------------------------
-- Table structure for complaint_personal_record
-- ----------------------------
DROP TABLE IF EXISTS `complaint_personal_record`;
CREATE TABLE `complaint_personal_record`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `agency_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `agency_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `old_identification_type` tinyint(2) NULL DEFAULT NULL,
  `now_identification_type` tinyint(2) NULL DEFAULT NULL,
  `old_identification_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `now_identification_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `old_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `now_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `old_sex` tinyint(2) NULL DEFAULT NULL,
  `now_sex` tinyint(2) NULL DEFAULT NULL,
  `complaint_problem_type` tinyint(2) NULL DEFAULT NULL,
  `complaint_status` tinyint(2) NULL DEFAULT NULL,
  `treatment_situation` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `attachment_ids` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `personal_complaint_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of complaint_personal_record
-- ----------------------------
INSERT INTO `complaint_personal_record` VALUES ('7b26f864950a499fb5beaa147a878c35', 'Mary', NULL, '昆山市第一人民医院', 1, 1, '320583198806119619', '320583198806119619', 'Mary', 'Mary66', 1, 1, 3, 1, NULL, '', '2e189ab03147409e9417dbf7cad63397', '2018-10-28 13:25:46', 'admin', '2018-10-28 13:25:46', 'admin');
INSERT INTO `complaint_personal_record` VALUES ('bbdaac51e0564f99ad3a23d6c940b011', '老王', NULL, NULL, 1, 1, '1213', '1213111', '老王', '老王', 1, 1, 2, 2, 'aaa', 'bbdabb59269b4c798031f207a4d21638', '1', '2018-10-27 20:03:48', 'admin', '2018-10-27 20:04:06', 'admin');
INSERT INTO `complaint_personal_record` VALUES ('e42d1e0baca243da86aab3567f825494', '李四', NULL, NULL, 1, 1, '320583198806119620', '320583198806119620', '李四', '李四', 4, 1, 4, 2, 'aaa', '', '7a446f7aebe045be8846de8c01de665c', '2018-10-28 12:28:28', 'admin', '2018-10-28 12:29:19', 'admin');
INSERT INTO `complaint_personal_record` VALUES ('edc1da34f79d4f5f9210308dcba51b31', '老王', NULL, NULL, 1, 1, '1213', '1213', '老王', '老王666', 1, 2, 5, 1, NULL, '', '1', '2018-10-27 20:06:34', 'admin', '2018-10-27 20:06:34', 'admin');

-- ----------------------------
-- Table structure for db_connection
-- ----------------------------
DROP TABLE IF EXISTS `db_connection`;
CREATE TABLE `db_connection`  (
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of db_connection
-- ----------------------------
INSERT INTO `db_connection` VALUES ('HRMS数据库', 'jdbc:mysql://127.0.0.1:3306/hrms?useUnicode=true&characterEncoding=UTF-8', 'MYSQL', 'root', NULL, NULL, '2018-04-12 13:41:16', 'admin', '2018-04-12 13:41:23', 'admin');
INSERT INTO `db_connection` VALUES ('医德医风数据库', 'jdbc:mysql://127.0.0.1:3306/hangfeng_base?useUnicode=true&characterEncoding=UTF-8', 'MYSQL', 'root', NULL, NULL, '2018-04-08 09:19:25', 'admin', '2018-04-08 09:19:32', 'admin');

-- ----------------------------
-- Table structure for org_agency
-- ----------------------------
DROP TABLE IF EXISTS `org_agency`;
CREATE TABLE `org_agency`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '主键',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '机构代码（唯一）',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '机构名称',
  `address` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `town_street` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `city_code` int(11) UNSIGNED NOT NULL COMMENT '城市代码',
  `town_code` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '镇区代码',
  `district_code` int(11) UNSIGNED NOT NULL COMMENT '区域代码',
  `create_time` datetime(0) NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `update_time` datetime(0) NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_agency
-- ----------------------------
INSERT INTO `org_agency` VALUES ('1', '0001', '昆山市第一人民医院', '前进路1000号', NULL, 3205831, NULL, 3205831, '2018-10-15 17:30:43', 'admin', '2018-10-15 17:30:51', 'admin', 0);
INSERT INTO `org_agency` VALUES ('2', '00011', '玉山社区服务中心', '前进路1000号', NULL, 32058311, NULL, 32058311, '2018-10-15 17:30:43', 'admin', '2018-10-15 17:30:51', 'admin', 0);
INSERT INTO `org_agency` VALUES ('3', '562959264', '昆山南站门诊部', '昆山市', '昆山市中华园路828号', 320583, 320583, 320583, '2018-10-16 08:55:47', 'admin', '2018-10-16 08:55:57', 'admin', 0);
INSERT INTO `org_agency` VALUES ('4', 'PDY70624X', '昆山萌牙客口腔门诊部', '昆山市', '昆山市珠江中路199号第6层', 320583, 320583, 320583, '2018-10-16 08:57:16', 'admin', '2018-10-16 08:57:26', 'admin', 0);

-- ----------------------------
-- Table structure for org_agency_detail
-- ----------------------------
DROP TABLE IF EXISTS `org_agency_detail`;
CREATE TABLE `org_agency_detail`  (
  `agency_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '机构ID',
  `agency_classify` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '机构分类管理',
  `agency_belong` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '机构隶属',
  `host_unit` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '主办单位',
  `economic_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '经济类型',
  `agency_category` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '机构类别',
  `hospital_grade` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '医院等级',
  `agency_change` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '机构变动情况',
  `agency_founding_time` datetime(0) NULL DEFAULT NULL COMMENT '单位成立时间',
  `license_start_time` datetime(0) NULL DEFAULT NULL COMMENT '许可证有效期(开始)',
  `license_end_time` datetime(0) NULL DEFAULT NULL COMMENT '许可证有效期(结束)',
  `juridical_person_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '法人代表姓名',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `approval_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '登记批准文号',
  `approval_agency` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '登记批准机构',
  `registered_capital` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '注册资金(万)',
  `certificate_time` datetime(0) NULL DEFAULT NULL COMMENT '办证日期',
  `open_bed_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '开放床位数',
  `is_out_hospital_patient` tinyint(4) NULL DEFAULT NULL COMMENT '是否填报出院病人表(0否1是)',
  `is_enabled` tinyint(4) NULL DEFAULT NULL COMMENT '是否启用(0否1是)',
  `is_substrate_unit` tinyint(4) NULL DEFAULT NULL COMMENT '是否为基层单位(0否1是)',
  `is_branch_agency` tinyint(4) NULL DEFAULT NULL COMMENT '是否为分支机构(0否1是)',
  `postal_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `unit_website` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '单位网站',
  `unit_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '单位邮箱',
  `transact_people` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '经办人',
  `input_people` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '录入人',
  `is_reporting_clinic` tinyint(4) NULL DEFAULT NULL COMMENT '是否为代报诊所(0否1是)',
  `clinic_reporting_agency` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '诊所所属代报机构',
  `is_village_clinic` tinyint(4) NULL DEFAULT NULL COMMENT '是否为代报村卫生室(0否1是)',
  `village_clinic_agency` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '村卫生室所属代报机构',
  `agency_create_time` datetime(0) NULL DEFAULT NULL COMMENT '新增机构创建时间',
  `date_obsolete` datetime(0) NULL DEFAULT NULL COMMENT '作废日期',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`agency_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_agency_detail
-- ----------------------------
INSERT INTO `org_agency_detail` VALUES ('3', '营利性医疗机构', 'test', '个人', '私有', '综合门诊部', 'test', '开业', '2018-10-16 00:00:00', '2011-12-01 12:45:51', '2030-12-31 12:46:34', 'test', '13844138444', 'test', 'test', '1000', '2018-10-16 12:47:47', '0', 1, 0, 1, 0, '215300', 'www.baidu.com', '88@qq.com', 'test', 'test', 0, 'test', 0, 'test', '2018-10-16 12:50:33', '2018-10-16 12:50:36', '2018-10-16 12:50:37', 'admin', '2018-10-16 12:50:47', 'admin', 0);
INSERT INTO `org_agency_detail` VALUES ('4', '营利性医疗机构', 'test2', '个人', '私有', '口腔门诊部', 'test2', '开业', '2018-10-16 00:00:00', '2016-06-01 13:36:12', '2030-12-31 12:46:34', 'test2', '0512-82628020', 'test2', 'test2', '1500', '2018-10-16 13:37:13', '0', 1, 1, 1, 0, '215300', 'www.baidu.com', '88@qq.com', 'test2', 'test2', 0, 'test2', 0, 'test2', '2018-10-16 13:38:32', '2018-10-16 13:38:35', '2018-10-16 13:38:38', 'admin', '2018-10-16 13:38:42', 'admin', 0);

-- ----------------------------
-- Table structure for org_agency_manager
-- ----------------------------
DROP TABLE IF EXISTS `org_agency_manager`;
CREATE TABLE `org_agency_manager`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `identification_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '身份证',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `agency_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '管理机构ID',
  `cellphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码',
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `department_position` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部门职务',
  `create_time` datetime(0) NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `update_time` datetime(0) NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for org_district
-- ----------------------------
DROP TABLE IF EXISTS `org_district`;
CREATE TABLE `org_district`  (
  `code` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `parent_code` int(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_district
-- ----------------------------
INSERT INTO `org_district` VALUES (3205831, '昆山市', NULL);
INSERT INTO `org_district` VALUES (32058311, '玉山镇', 3205831);
INSERT INTO `org_district` VALUES (32058312, '开发区', 3205831);
INSERT INTO `org_district` VALUES (32058313, '花桥镇', 3205831);

-- ----------------------------
-- Table structure for org_district_manager
-- ----------------------------
DROP TABLE IF EXISTS `org_district_manager`;
CREATE TABLE `org_district_manager`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `identification_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '身份证',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `district_code` int(10) UNSIGNED NOT NULL COMMENT '管理区域代码',
  `cellphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '手机号码',
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `department_position` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部门职务',
  `create_time` datetime(0) NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `update_time` datetime(0) NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_district_manager
-- ----------------------------
INSERT INTO `org_district_manager` VALUES ('3205831', '320583198806119618', '章三分', 3205831, '13584952231', '3205831', '2', NULL, NULL, NULL, '2018-10-16 13:38:29', 'admin', '2018-10-16 13:38:37', 'admin');
INSERT INTO `org_district_manager` VALUES ('32058311', '320583198806119617', '何炳坤', 32058311, '13584950680', '32058311', '1', NULL, NULL, NULL, '2018-10-16 13:37:36', 'admin', '2018-10-16 13:37:44', 'admin');

-- ----------------------------
-- Table structure for org_personnel
-- ----------------------------
DROP TABLE IF EXISTS `org_personnel`;
CREATE TABLE `org_personnel`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `identification_type` tinyint(4) NOT NULL COMMENT '证件类型',
  `identification_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '证件号码',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `sex` tinyint(4) NULL DEFAULT NULL,
  `used_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '曾用名',
  `cellphone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `office_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `profile_photo` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `agency_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `agency_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '机构名称（冗余）',
  `city_code` int(11) UNSIGNED NULL DEFAULT NULL,
  `town_code` int(11) UNSIGNED NULL DEFAULT NULL,
  `district_code` int(11) UNSIGNED NULL DEFAULT NULL,
  `nationality` smallint(6) NULL DEFAULT NULL COMMENT '国籍',
  `nation` smallint(6) NULL DEFAULT NULL COMMENT '民族',
  `birthday` datetime(0) NULL DEFAULT NULL,
  `start_work_time` datetime(0) NULL DEFAULT NULL COMMENT '开始工作时间',
  `join_party_time` datetime(0) NULL DEFAULT NULL COMMENT '加入党派时间',
  `political_affiliation` tinyint(4) NULL DEFAULT NULL COMMENT '政治面貌',
  `interest` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '兴趣爱好',
  `native_place` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '籍贯',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态',
  `create_time` datetime(0) NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `update_time` datetime(0) NULL,
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `is_delete` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_personnel
-- ----------------------------
INSERT INTO `org_personnel` VALUES ('1', 1, '320583198806119617', '周旭武', 1, '周明', '13584950688', '', NULL, '1', '昆山第一人民医院', 3205831, NULL, 3205831, 1, 1, '2018-07-10 00:00:00', '2018-10-23 00:00:00', '2018-10-10 00:00:00', 1, '蹴鞠，打篮球', '江苏昆山', 1, '2018-10-15 17:31:26', 'admin', '2018-10-25 14:42:53', 'admin', 0);
INSERT INTO `org_personnel` VALUES ('2', 1, '320583198806119618', 'Jack', 2, NULL, NULL, NULL, NULL, '2', '玉山社区服务中心', 3205831, 32058311, 32058311, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2018-10-15 17:31:26', 'admin', '2018-10-15 17:31:29', 'admin', 0);
INSERT INTO `org_personnel` VALUES ('3', 1, '320583198806119619', 'Mary', 1, NULL, NULL, NULL, NULL, '1', '昆山第一人民医院', 3205831, NULL, 3205831, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2018-10-15 17:31:26', 'admin', '2018-10-15 17:31:29', 'admin', 0);
INSERT INTO `org_personnel` VALUES ('4', 1, '320583198806119620', '李四', 4, NULL, NULL, NULL, NULL, '2', '玉山社区服务中心', 3205831, 32058311, 32058311, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2018-10-15 17:31:26', 'admin', '2018-10-15 17:31:29', 'admin', 0);
INSERT INTO `org_personnel` VALUES ('5', 1, '320583198806119621', '张三', 2, NULL, NULL, NULL, NULL, '1', '昆山第一人民医院', 3205831, NULL, 3205831, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2018-10-15 17:31:26', 'admin', '2018-10-15 17:31:29', 'admin', 0);
INSERT INTO `org_personnel` VALUES ('6', 6, '320583198806119622', 'Ashlee', 3, NULL, NULL, NULL, NULL, '1', '昆山第一人民医院', 3205831, NULL, 3205831, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, '2018-10-15 17:31:26', 'admin', '2018-10-15 17:31:29', 'admin', 0);

-- ----------------------------
-- Table structure for org_personnel_education
-- ----------------------------
DROP TABLE IF EXISTS `org_personnel_education`;
CREATE TABLE `org_personnel_education`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `personnel_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `school_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学校名称',
  `school_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学校地址',
  `education` tinyint(4) NULL DEFAULT NULL COMMENT '学历',
  `diploma_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '毕业证书号码',
  `academic_degree` tinyint(4) NULL DEFAULT NULL COMMENT '学位',
  `academic_degree_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学位证书',
  `major` tinyint(4) NULL DEFAULT NULL COMMENT '专业',
  `education_type` tinyint(4) NULL DEFAULT NULL COMMENT '教育类型',
  `join_school_time` datetime(0) NULL DEFAULT NULL COMMENT '入学时间',
  `graduation_time` datetime(0) NULL DEFAULT NULL COMMENT '毕业时间',
  `has_record_status` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '获取证书情况',
  `other_instructions` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '其他说明',
  `attachments` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '附件（附件ID拼接，最多3个）',
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_personnel_education
-- ----------------------------
INSERT INTO `org_personnel_education` VALUES ('1', '1', '哈尔滨大学', '吉林长江路100号', 1, '103601200505003736', 1, '103604051874', 1, 1, '2018-10-24 10:16:54', '2018-10-24 10:16:57', NULL, NULL, '1,2', 0);
INSERT INTO `org_personnel_education` VALUES ('2', '1', '南京大学', '南京紫金路9号', 2, '103601200505003736', 2, '103604051874', 1, 1, '2018-10-24 10:18:16', '2018-10-24 10:18:19', NULL, NULL, '1', 0);

-- ----------------------------
-- Table structure for org_user
-- ----------------------------
DROP TABLE IF EXISTS `org_user`;
CREATE TABLE `org_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'id',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '账号',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `is_admin` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否管理人员',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '更新人',
  `is_delete` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_user
-- ----------------------------
INSERT INTO `org_user` VALUES ('5af7e023693a4dfc89c2ea22ded525f0', '周旭武', 'zhouxuwu', '', 0, '2018-10-09 11:26:38', 'admin', '2018-10-09 11:26:38', 'admin', 0);

-- ----------------------------
-- Table structure for org_work_info
-- ----------------------------
DROP TABLE IF EXISTS `org_work_info`;
CREATE TABLE `org_work_info`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `dept` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在科室',
  `dept_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '科室具体名称',
  `agency_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `agency_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `major` tinyint(4) NULL DEFAULT NULL COMMENT '从事专业类别',
  `duty` tinyint(4) NULL DEFAULT NULL COMMENT '行政／业务管理职务',
  `technical_qualification` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业技术资格（评或考试）',
  `gain_date` date NULL DEFAULT NULL COMMENT '专业技术资格取得时间',
  `tech_post` tinyint(4) NULL DEFAULT NULL COMMENT '专业技术职务（聘）',
  `employ_date` date NULL DEFAULT NULL COMMENT '专业技术聘用时间',
  `employ_post` tinyint(4) NULL DEFAULT NULL COMMENT '专业技术职务聘用岗位',
  `formation` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编制情况',
  `inorout` tinyint(4) NULL DEFAULT NULL COMMENT '年内人员流动情况',
  `inorout_date` date NULL DEFAULT NULL COMMENT '流入/流出时间',
  `is_statistical` tinyint(4) NULL DEFAULT NULL COMMENT '是否从事统计信息化业务工作',
  `work_content` tinyint(4) NULL DEFAULT NULL,
  `personnel_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联id',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NULL,
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `is_delete` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_work_info
-- ----------------------------
INSERT INTO `org_work_info` VALUES ('123', '8049', '重症医', '重症医学科', '昆山第一人民医院', '467170249', 2, 1, '高级统计师', '2018-10-24', 2, '2018-10-24', NULL, '1', 3, '2018-10-24', 1, 1, '1', 'admin', '2018-10-24 17:17:00', '5af7e023693a4dfc89c2ea22ded525f0', '2018-10-29 10:41:55', 0);

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `suffix` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `size` int(11) UNSIGNED NULL DEFAULT NULL,
  `pelative_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------
INSERT INTO `sys_attachment` VALUES ('3a70817c45da46649e76d5918813d463', 'text/plain', '附件', '.txt', 48, '20181018/3a70817c45da46649e76d5918813d463.txt');
INSERT INTO `sys_attachment` VALUES ('717ff5de2efd444091fc1646d61cd130', 'image/jpeg', '附件', '.jpg', 42539, '20181018/717ff5de2efd444091fc1646d61cd130.jpg');
INSERT INTO `sys_attachment` VALUES ('bbdabb59269b4c798031f207a4d21638', 'image/png', '附件', '.png', 345637, '20181027/bbdabb59269b4c798031f207a4d21638.png');

-- ----------------------------
-- Table structure for sys_constant
-- ----------------------------
DROP TABLE IF EXISTS `sys_constant`;
CREATE TABLE `sys_constant`  (
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '分类',
  `code` int(11) NOT NULL COMMENT '编码ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `order_no` int(11) NULL DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`type`, `code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_constant
-- ----------------------------
INSERT INTO `sys_constant` VALUES ('academic-degree', 1, '名誉博士', 1);
INSERT INTO `sys_constant` VALUES ('academic-degree', 2, '博士', 2);
INSERT INTO `sys_constant` VALUES ('academic-degree', 3, '硕士', 3);
INSERT INTO `sys_constant` VALUES ('academic-degree', 4, '学士', 4);
INSERT INTO `sys_constant` VALUES ('boolean', 0, '否', 2);
INSERT INTO `sys_constant` VALUES ('boolean', 1, '是', 1);
INSERT INTO `sys_constant` VALUES ('check-status', 0, '--请选择审核状态--', 1);
INSERT INTO `sys_constant` VALUES ('check-status', 1, '未审核', 2);
INSERT INTO `sys_constant` VALUES ('check-status', 2, '审核通过', 3);
INSERT INTO `sys_constant` VALUES ('check-status', 3, '审核不通过', 4);
INSERT INTO `sys_constant` VALUES ('complaint-problem-type', 0, '--请选择申诉问题--', 1);
INSERT INTO `sys_constant` VALUES ('complaint-problem-type', 1, '证件类型修正', 2);
INSERT INTO `sys_constant` VALUES ('complaint-problem-type', 2, '身份证修正', 3);
INSERT INTO `sys_constant` VALUES ('complaint-problem-type', 3, '姓名修正', 4);
INSERT INTO `sys_constant` VALUES ('complaint-problem-type', 4, '性别修正', 5);
INSERT INTO `sys_constant` VALUES ('complaint-problem-type', 5, '全面信息修正', 6);
INSERT INTO `sys_constant` VALUES ('complaint-status', 0, '--请选择申诉状态--', 1);
INSERT INTO `sys_constant` VALUES ('complaint-status', 1, '提交申报', 2);
INSERT INTO `sys_constant` VALUES ('complaint-status', 2, '信息核对通过可处理', 3);
INSERT INTO `sys_constant` VALUES ('complaint-status', 3, '信息核对不通过不予处理', 4);
INSERT INTO `sys_constant` VALUES ('complaint-status', 4, '处理完成', 5);
INSERT INTO `sys_constant` VALUES ('duty', 1, '党委（副）书记', 1);
INSERT INTO `sys_constant` VALUES ('duty', 2, '院（所、站）长', 2);
INSERT INTO `sys_constant` VALUES ('duty', 3, '院副（所、站）长', 3);
INSERT INTO `sys_constant` VALUES ('duty', 4, '科室主任', 4);
INSERT INTO `sys_constant` VALUES ('duty', 5, '科室副主任', 5);
INSERT INTO `sys_constant` VALUES ('education', 0, '其他', 8);
INSERT INTO `sys_constant` VALUES ('education', 1, '研究生', 1);
INSERT INTO `sys_constant` VALUES ('education', 2, '大学本科', 2);
INSERT INTO `sys_constant` VALUES ('education', 3, '大学专科及专科学校', 3);
INSERT INTO `sys_constant` VALUES ('education', 4, '中专及中技', 4);
INSERT INTO `sys_constant` VALUES ('education', 5, '技工学校', 5);
INSERT INTO `sys_constant` VALUES ('education', 6, '高中', 6);
INSERT INTO `sys_constant` VALUES ('education', 7, '初中及以下', 7);
INSERT INTO `sys_constant` VALUES ('education-type', 0, '其他', 5);
INSERT INTO `sys_constant` VALUES ('education-type', 1, '全日制', 1);
INSERT INTO `sys_constant` VALUES ('education-type', 2, '函授', 2);
INSERT INTO `sys_constant` VALUES ('education-type', 3, '成人教育', 3);
INSERT INTO `sys_constant` VALUES ('education-type', 4, '继续教育', 4);
INSERT INTO `sys_constant` VALUES ('employ-post', 1, '医生', 1);
INSERT INTO `sys_constant` VALUES ('employ-post', 2, '护士', 2);
INSERT INTO `sys_constant` VALUES ('employ-post', 3, '行政后勤', 3);
INSERT INTO `sys_constant` VALUES ('employ-post', 4, '辅助', 4);
INSERT INTO `sys_constant` VALUES ('employ-post', 5, '其他', 5);
INSERT INTO `sys_constant` VALUES ('formation', 1, '编制内', 1);
INSERT INTO `sys_constant` VALUES ('formation', 2, '合同制', 2);
INSERT INTO `sys_constant` VALUES ('formation', 3, '临聘人员', 3);
INSERT INTO `sys_constant` VALUES ('formation', 4, '返聘', 4);
INSERT INTO `sys_constant` VALUES ('formation', 5, '其他', 5);
INSERT INTO `sys_constant` VALUES ('identification-type', 1, '身份证', 1);
INSERT INTO `sys_constant` VALUES ('identification-type', 2, '军官证', 2);
INSERT INTO `sys_constant` VALUES ('identification-type', 3, '台湾通行证', 3);
INSERT INTO `sys_constant` VALUES ('inorout', 1, '无', 1);
INSERT INTO `sys_constant` VALUES ('inorout', 2, '流入：高、中等院校毕业', 2);
INSERT INTO `sys_constant` VALUES ('inorout', 3, '流入：其他卫生机构调入', 3);
INSERT INTO `sys_constant` VALUES ('inorout', 4, '流入：非卫生机构调入', 4);
INSERT INTO `sys_constant` VALUES ('inorout', 5, '流入：军转人员', 5);
INSERT INTO `sys_constant` VALUES ('inorout', 6, '流入：其他', 6);
INSERT INTO `sys_constant` VALUES ('inorout', 7, '流出：调往其他卫生机构', 7);
INSERT INTO `sys_constant` VALUES ('inorout', 8, '流出：考取研究生', 8);
INSERT INTO `sys_constant` VALUES ('inorout', 9, '流出：出国留学', 9);
INSERT INTO `sys_constant` VALUES ('inorout', 10, '流出：退休', 10);
INSERT INTO `sys_constant` VALUES ('inorout', 11, '流出：辞职（辞退）', 11);
INSERT INTO `sys_constant` VALUES ('inorout', 12, '流出：自然减员（不含退休）', 12);
INSERT INTO `sys_constant` VALUES ('inorout', 13, '流出：其他', 13);
INSERT INTO `sys_constant` VALUES ('major', 1, '执业医师', 1);
INSERT INTO `sys_constant` VALUES ('major', 2, '执业助理医师', 2);
INSERT INTO `sys_constant` VALUES ('major', 3, '见习医师', 3);
INSERT INTO `sys_constant` VALUES ('major', 4, '全科医生', 4);
INSERT INTO `sys_constant` VALUES ('major', 5, '乡村医生', 5);
INSERT INTO `sys_constant` VALUES ('major', 6, '乡村助理执业医师', 6);
INSERT INTO `sys_constant` VALUES ('major', 7, '注册护士', 7);
INSERT INTO `sys_constant` VALUES ('major', 8, '助产士', 8);
INSERT INTO `sys_constant` VALUES ('major', 9, '西药师（士）', 9);
INSERT INTO `sys_constant` VALUES ('major', 10, '中药师（士）', 10);
INSERT INTO `sys_constant` VALUES ('major', 11, '检验技师（士）', 11);
INSERT INTO `sys_constant` VALUES ('major', 12, '影像技师（士）', 12);
INSERT INTO `sys_constant` VALUES ('major', 13, '其他卫生技术人员', 13);
INSERT INTO `sys_constant` VALUES ('major', 14, '其它技术人员', 14);
INSERT INTO `sys_constant` VALUES ('major', 15, '管理人员', 15);
INSERT INTO `sys_constant` VALUES ('major', 16, '工勤技能人员', 16);
INSERT INTO `sys_constant` VALUES ('major-type', 1, '哲学', 1);
INSERT INTO `sys_constant` VALUES ('major-type', 2, '经济学', 2);
INSERT INTO `sys_constant` VALUES ('major-type', 3, '法学', 3);
INSERT INTO `sys_constant` VALUES ('major-type', 4, '教育学', 4);
INSERT INTO `sys_constant` VALUES ('major-type', 5, '文学', 5);
INSERT INTO `sys_constant` VALUES ('major-type', 6, '历史学', 6);
INSERT INTO `sys_constant` VALUES ('major-type', 7, '理学', 7);
INSERT INTO `sys_constant` VALUES ('major-type', 8, '工学', 8);
INSERT INTO `sys_constant` VALUES ('major-type', 9, '农学', 9);
INSERT INTO `sys_constant` VALUES ('major-type', 10, '医学', 10);
INSERT INTO `sys_constant` VALUES ('major-type', 11, '基础医学类', 11);
INSERT INTO `sys_constant` VALUES ('major-type', 12, '预防医学类', 12);
INSERT INTO `sys_constant` VALUES ('major-type', 13, '临床医学与医学技术类', 13);
INSERT INTO `sys_constant` VALUES ('major-type', 14, '临床医学', 14);
INSERT INTO `sys_constant` VALUES ('major-type', 15, '麻醉学', 15);
INSERT INTO `sys_constant` VALUES ('major-type', 16, '医学影像学', 16);
INSERT INTO `sys_constant` VALUES ('major-type', 17, '医学检验', 17);
INSERT INTO `sys_constant` VALUES ('major-type', 18, '其他医学技术专业', 18);
INSERT INTO `sys_constant` VALUES ('major-type', 19, '口腔医学', 19);
INSERT INTO `sys_constant` VALUES ('major-type', 20, '中医学类', 20);
INSERT INTO `sys_constant` VALUES ('major-type', 21, '中医学', 21);
INSERT INTO `sys_constant` VALUES ('major-type', 22, '针灸推拿学', 22);
INSERT INTO `sys_constant` VALUES ('major-type', 23, '中医学类其他专', 23);
INSERT INTO `sys_constant` VALUES ('major-type', 24, '法医学', 24);
INSERT INTO `sys_constant` VALUES ('major-type', 25, '护理学', 25);
INSERT INTO `sys_constant` VALUES ('major-type', 26, '药学类', 26);
INSERT INTO `sys_constant` VALUES ('major-type', 27, '药学', 27);
INSERT INTO `sys_constant` VALUES ('major-type', 28, '中药学', 28);
INSERT INTO `sys_constant` VALUES ('major-type', 29, '其他药学专业', 29);
INSERT INTO `sys_constant` VALUES ('major-type', 30, '管理类', 30);
INSERT INTO `sys_constant` VALUES ('nation', 1, '汉族', 1);
INSERT INTO `sys_constant` VALUES ('nation', 2, '回族', 2);
INSERT INTO `sys_constant` VALUES ('nation', 3, '朝鲜族', 3);
INSERT INTO `sys_constant` VALUES ('nationality', 1, '中国', 1);
INSERT INTO `sys_constant` VALUES ('nationality', 2, '外国', 2);
INSERT INTO `sys_constant` VALUES ('peronnel-status', 1, '正常', 1);
INSERT INTO `sys_constant` VALUES ('personal-info-status', 0, '未审核', 1);
INSERT INTO `sys_constant` VALUES ('personal-info-status', 1, '已通过', 2);
INSERT INTO `sys_constant` VALUES ('personal-info-status', 2, '未通过', 3);
INSERT INTO `sys_constant` VALUES ('political-affiliation', 1, '共产党', 1);
INSERT INTO `sys_constant` VALUES ('political-affiliation', 2, '普通群众', 3);
INSERT INTO `sys_constant` VALUES ('political-affiliation', 3, '团员', 2);
INSERT INTO `sys_constant` VALUES ('publicity-message-status', 0, '暂存', 1);
INSERT INTO `sys_constant` VALUES ('publicity-message-status', 1, '待审核', 2);
INSERT INTO `sys_constant` VALUES ('publicity-message-status', 2, '驳回', 3);
INSERT INTO `sys_constant` VALUES ('publicity-message-status', 9, '审核成功', 4);
INSERT INTO `sys_constant` VALUES ('publicity-message-status', 10, '待发布', 5);
INSERT INTO `sys_constant` VALUES ('publicity-message-type', 1, '公告', 1);
INSERT INTO `sys_constant` VALUES ('publicity-message-type', 2, '文章', 2);
INSERT INTO `sys_constant` VALUES ('publicity-message-type', 3, '视频', 3);
INSERT INTO `sys_constant` VALUES ('sex', 1, '男', 1);
INSERT INTO `sys_constant` VALUES ('sex', 2, '女', 2);
INSERT INTO `sys_constant` VALUES ('sex', 3, '未知', 3);
INSERT INTO `sys_constant` VALUES ('sex', 4, '未说明的性别', 4);
INSERT INTO `sys_constant` VALUES ('status', 0, NULL, 0);
INSERT INTO `sys_constant` VALUES ('tech-post', 1, '正高', 1);
INSERT INTO `sys_constant` VALUES ('tech-post', 2, '副高', 2);
INSERT INTO `sys_constant` VALUES ('tech-post', 3, '中级', 3);
INSERT INTO `sys_constant` VALUES ('tech-post', 4, '师级/助理', 4);
INSERT INTO `sys_constant` VALUES ('tech-post', 5, '士级', 5);
INSERT INTO `sys_constant` VALUES ('tech-post', 6, '待聘', 6);
INSERT INTO `sys_constant` VALUES ('work-content', 1, '卫生统计', 1);
INSERT INTO `sys_constant` VALUES ('work-content', 2, '网络运维管理', 2);
INSERT INTO `sys_constant` VALUES ('work-content', 3, '应用系统开发运维', 3);
INSERT INTO `sys_constant` VALUES ('work-content', 4, '信息标准与安全', 4);
INSERT INTO `sys_constant` VALUES ('work-content', 5, '业务管理', 5);
INSERT INTO `sys_constant` VALUES ('work-content', 6, '其他', 6);

-- ----------------------------
-- Table structure for sys_tree
-- ----------------------------
DROP TABLE IF EXISTS `sys_tree`;
CREATE TABLE `sys_tree`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` tinyint(4) NULL DEFAULT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_no` tinyint(4) NULL DEFAULT NULL,
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tree
-- ----------------------------
INSERT INTO `sys_tree` VALUES ('1', 'dept', 1, '高等学校教师', 1, '0');
INSERT INTO `sys_tree` VALUES ('11', 'dept', 1, '教授', 1, '1');
INSERT INTO `sys_tree` VALUES ('12', 'dept', 1, '副教授', 2, '1');
INSERT INTO `sys_tree` VALUES ('13', 'dept', 1, '讲师', 3, '1');
INSERT INTO `sys_tree` VALUES ('14', 'dept', 1, '助教', 4, '1');
INSERT INTO `sys_tree` VALUES ('2', 'dept', 2, '经济专业人员', 2, '0');
INSERT INTO `sys_tree` VALUES ('21', 'dept', 2, '高级经济师', 1, '2');
INSERT INTO `sys_tree` VALUES ('22', 'dept', 2, '经济师', 2, '2');
INSERT INTO `sys_tree` VALUES ('3', 'technicalQualification', 3, '统计专业人员', 3, '0');
INSERT INTO `sys_tree` VALUES ('31', 'technicalQualification', 3, '高级统计师', 1, '3');
INSERT INTO `sys_tree` VALUES ('32', 'technicalQualification', 3, '统计师', 2, '3');
INSERT INTO `sys_tree` VALUES ('4', 'technicalQualification', 4, '体育教练', 4, '0');
INSERT INTO `sys_tree` VALUES ('41', 'technicalQualification', 4, '国家级教练', 1, '4');
INSERT INTO `sys_tree` VALUES ('42', 'technicalQualification', 4, '主教练', 2, '4');
INSERT INTO `sys_tree` VALUES ('43', 'technicalQualification', 4, '教练', 3, '4');
INSERT INTO `sys_tree` VALUES ('44', 'technicalQualification', 4, '助理教练', 4, '4');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'id',
  `account` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `salt` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '盐',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '用户信息表ID',
  `state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '是否启用，0不启用',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0:默认，1：管理员',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '创建人',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `update_user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '更新人',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `unique_account`(`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('9b316169b43f45569dbaf58bea35369e', 'zhouxuwu', '8e0dd11902e67f6aaa24cc76d00d2b60', '7c17f88dbacbf3037a38310f983ac6fb', '5af7e023693a4dfc89c2ea22ded525f0', 1, 2, '2018-10-09 11:26:38', 'admin', '2018-10-09 11:26:38', 'admin', 0);
INSERT INTO `sys_user` VALUES ('ad313d38fe9447ce863fe8584743a010', 'admin', 'bd168e346bb59730335ead5464ff5eb2', '3ad6c1dd71e0ec26c62ae1b7c81b3e82', 'admin', 1, 1, '2018-07-18 14:40:41', 'admin', '2018-03-13 16:20:29', 'admin', 0);
INSERT INTO `sys_user` VALUES ('ad313d38fe9447ce863fe8584743a011', '3205831', '32325e35d1d3475b927871d7db3ae7fd', '3ad6c1dd71e0ec26c62ae1b7c81b3e82', '3205831', 1, 2, '2018-07-18 14:40:41', 'admin', '2018-03-13 16:20:29', 'admin', 0);
INSERT INTO `sys_user` VALUES ('ad313d38fe9447ce863fe8584743a012', '32058311', '32325e35d1d3475b927871d7db3ae7fd', '3ad6c1dd71e0ec26c62ae1b7c81b3e82', '32058311', 1, 2, '2018-07-18 14:40:41', 'admin', '2018-03-13 16:20:29', 'admin', 0);

-- ----------------------------
-- Table structure for sys_visit_cache
-- ----------------------------
DROP TABLE IF EXISTS `sys_visit_cache`;
CREATE TABLE `sys_visit_cache`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'IP',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'IP',
  `value` varchar(400) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_visit_cache
-- ----------------------------
INSERT INTO `sys_visit_cache` VALUES ('a4bf142ed7b74ab0a9b881fe262b294f', '0:0:0:0:0:0:0:1', 'data_project_path', 'D:\\woke\\idea\\project\\hrms');

SET FOREIGN_KEY_CHECKS = 1;


/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : hrms

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-10-30 10:47:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `org_personnel_job`
-- ----------------------------
DROP TABLE IF EXISTS `org_personnel_job`;
CREATE TABLE `org_personnel_job` (
  `id` varchar(32) NOT NULL,
  `employee_no` varchar(30) DEFAULT NULL COMMENT '工号',
  `dept` varchar(50) DEFAULT NULL COMMENT '所在科室',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '科室具体名称',
  `agency_name` varchar(50) DEFAULT NULL COMMENT '机构名称',
  `agency_no` varchar(30) DEFAULT NULL COMMENT '组织机构代码',
  `major` varchar(32) DEFAULT NULL COMMENT '从事专业类别',
  `duty` varchar(32) DEFAULT NULL COMMENT '行政／业务管理职务',
  `technical_qualification` varchar(32) DEFAULT NULL COMMENT '专业技术资格（评或考试）',
  `gain_date` date DEFAULT NULL COMMENT '专业技术资格取得时间',
  `tech_post` varchar(32) DEFAULT NULL COMMENT '专业技术职务（聘）',
  `employ_date` date DEFAULT NULL COMMENT '专业技术聘用时间',
  `employ_post` varchar(32) DEFAULT NULL COMMENT '专业技术职务聘用岗位',
  `formation` varchar(32) DEFAULT NULL COMMENT '编制情况',
  `inorout` varchar(32) DEFAULT NULL COMMENT '年内人员流动情况',
  `inorout_date` date DEFAULT NULL COMMENT '流入/流出时间',
  `is_statistical` tinyint(4) DEFAULT NULL COMMENT '是否从事统计信息化业务工作',
  `work_content` varchar(32) DEFAULT NULL,
  `attachments` varchar(120) DEFAULT NULL,
  `status` tinyint(4) NOT NULL COMMENT '关联id',
  `update_user_id` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL,
  `create_user_id` varchar(32) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_personnel_job
-- ----------------------------

