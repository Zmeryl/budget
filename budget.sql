/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : budget

Target Server Type    : MYSQL
Target Server Version : 50699
File Encoding         : 65001

Date: 2017-09-02 21:37:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for b_basecatalog
-- ----------------------------
DROP TABLE IF EXISTS `b_basecatalog`;
CREATE TABLE `b_basecatalog` (
`CataId`  int(12) NOT NULL AUTO_INCREMENT ,
`SuperId`  int(12) NULL DEFAULT NULL ,
`CataName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CataType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`CataId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=18

;

-- ----------------------------
-- Records of b_basecatalog
-- ----------------------------
BEGIN;
INSERT INTO `b_basecatalog` VALUES ('11', '0', '研发工程师', '1'), ('12', '0', '服务器', '2'), ('13', '0', '市场调研', '3'), ('14', '0', '计算机', '2'), ('15', '0', '开发经理', '1'), ('16', '0', '其它开发人员', '1'), ('17', '0', '公关', '3');
COMMIT;

-- ----------------------------
-- Table structure for b_basepricehuman
-- ----------------------------
DROP TABLE IF EXISTS `b_basepricehuman`;
CREATE TABLE `b_basepricehuman` (
`PriceId`  int(12) NOT NULL AUTO_INCREMENT ,
`CataId`  int(12) NULL DEFAULT NULL ,
`CataName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DeptRoleId`  int(12) NULL DEFAULT NULL COMMENT 'һɫԱʼͬһ۸' ,
`RoleName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PriceType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Price`  double NULL DEFAULT NULL ,
`PublicRate`  int(4) NULL DEFAULT NULL ,
`Info`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserId`  int(12) NULL DEFAULT NULL ,
`UserName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`PriceId`),
FOREIGN KEY (`UserId`) REFERENCES `b_user` (`UserId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`CataId`) REFERENCES `b_basecatalog` (`CataId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`DeptRoleId`) REFERENCES `b_deptrole` (`DeptRoleId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_16` (`UserId`) USING BTREE ,
INDEX `FK_Reference_8` (`CataId`) USING BTREE ,
INDEX `FK_Reference_9` (`DeptRoleId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=15

;

-- ----------------------------
-- Records of b_basepricehuman
-- ----------------------------
BEGIN;
INSERT INTO `b_basepricehuman` VALUES ('7', '16', '其它开发人员', '11', 'Java工程师', '1', '8000', '1', '', '28', 'zhangsan'), ('8', '16', '其它开发人员', '11', 'Java工程师', '1', '8000', '1', '', '29', 'lisi'), ('9', '16', '其它开发人员', '12', '前端工程师', '1', '7000', '1', '', '30', 'wangwu'), ('10', '15', '开发经理', '10', '开发经理', '1', '12000', '1', '', '27', 'leonyip'), ('11', '16', '其它开发人员', '13', '测试工程师', '1', '5000', '1', '', '31', 'zhaoliu'), ('12', '11', '研发工程师', '15', 'ASP.NET工程师', '1', '7500', '1', '', '32', 'weixiaobao'), ('13', '15', '开发经理', '8', '产品经理A', '1', '10000', '1', '康熙来了', '33', 'kangxi'), ('14', '15', '开发经理', '7', '研发经理B', '1', '10000', '1', '', '19', 'testuser1');
COMMIT;

-- ----------------------------
-- Table structure for b_basepriceother
-- ----------------------------
DROP TABLE IF EXISTS `b_basepriceother`;
CREATE TABLE `b_basepriceother` (
`PriceId`  int(12) NOT NULL AUTO_INCREMENT ,
`CataId`  int(12) NULL DEFAULT NULL ,
`CataName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ResName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ResUnit`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PriceType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Price`  double NULL DEFAULT NULL ,
`PublicRate`  int(4) NULL DEFAULT NULL ,
`Info`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`PriceId`),
FOREIGN KEY (`CataId`) REFERENCES `b_basecatalog` (`CataId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_11` (`CataId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=7

;

-- ----------------------------
-- Records of b_basepriceother
-- ----------------------------
BEGIN;
INSERT INTO `b_basepriceother` VALUES ('4', '13', '市场调研', '一级市场调研', '次', '2', '20', '1', 'xxxxxxx'), ('5', '13', '市场调研', '市场推广', '次', '2', '10', '1', ''), ('6', '17', '公关', '请客户吃饭', '次', '1', '2000', '1', '只是吃饭。');
COMMIT;

-- ----------------------------
-- Table structure for b_basepriceres
-- ----------------------------
DROP TABLE IF EXISTS `b_basepriceres`;
CREATE TABLE `b_basepriceres` (
`PriceId`  int(12) NOT NULL AUTO_INCREMENT ,
`CataId`  int(12) NULL DEFAULT NULL ,
`CataName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ResName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ResUnit`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ResBrand`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Provider`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PriceType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Price`  double NULL DEFAULT NULL ,
`PublicRate`  int(4) NULL DEFAULT NULL ,
`Info`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`PriceId`),
FOREIGN KEY (`CataId`) REFERENCES `b_basecatalog` (`CataId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_10` (`CataId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of b_basepriceres
-- ----------------------------
BEGIN;
INSERT INTO `b_basepriceres` VALUES ('3', '12', '服务器', 'dell2950', '台', 'dell', 'xxx', '1', '20000', '1', 'xxxxxxx'), ('4', '12', '服务器', 'DELL XPS 服务器', '台', 'DELL', 'DELL', '1', '25000', '1', ''), ('5', '14', '计算机', '笔记本电脑（开发用）', '台', 'Thinkpad', '采购', '1', '12000', '1', '开发人员每人一台。');
COMMIT;

-- ----------------------------
-- Table structure for b_department
-- ----------------------------
DROP TABLE IF EXISTS `b_department`;
CREATE TABLE `b_department` (
`DeptId`  int(12) NOT NULL AUTO_INCREMENT ,
`SuperId`  int(12) NULL DEFAULT NULL ,
`DeptName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DeptInfo`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`DeptId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=21

;

-- ----------------------------
-- Records of b_department
-- ----------------------------
BEGIN;
INSERT INTO `b_department` VALUES ('17', '0', '研发部', '研发部'), ('18', '0', '产品部', '产品部'), ('20', '0', 'aa', 'aa');
COMMIT;

-- ----------------------------
-- Table structure for b_deptrole
-- ----------------------------
DROP TABLE IF EXISTS `b_deptrole`;
CREATE TABLE `b_deptrole` (
`DeptRoleId`  int(12) NOT NULL AUTO_INCREMENT ,
`DeptId`  int(12) NULL DEFAULT NULL ,
`DeptRoleName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DeptRolePrice`  double NULL DEFAULT NULL ,
`DeptRoleInfo`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`DeptRoleId`),
FOREIGN KEY (`DeptId`) REFERENCES `b_department` (`DeptId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_6` (`DeptId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=16

;

-- ----------------------------
-- Records of b_deptrole
-- ----------------------------
BEGIN;
INSERT INTO `b_deptrole` VALUES ('6', '17', '研发经理A', '10000', '研发经理A'), ('7', '17', '研发经理B', '10000', '研发经理B'), ('8', '18', '产品经理A', '10000', '产品经理A'), ('9', '18', '产品经理B', '10000', '产品经理B'), ('10', '17', '开发经理', '12000', '负责开发管理的经理。'), ('11', '17', 'Java工程师', '8000', 'Java开发工程师'), ('12', '17', '前端工程师', '7000', '前端工程师。'), ('13', '17', '测试工程师', '5000', '测试工程师。。'), ('15', '17', 'ASP.NET工程师', '7500', 'ASP.NET工程师ASP.NET工程师ASP.NET工程师ASP.NET工程师ASP.NET工程师');
COMMIT;

-- ----------------------------
-- Table structure for b_user
-- ----------------------------
DROP TABLE IF EXISTS `b_user`;
CREATE TABLE `b_user` (
`UserId`  int(12) NOT NULL AUTO_INCREMENT ,
`DeptRoleId`  int(12) NULL DEFAULT NULL ,
`DeptRoleName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SysRoleId`  int(12) NULL DEFAULT NULL ,
`SysRoleName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`LoginName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`LoginPwd`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Status`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`LastLoginTime`  datetime NULL DEFAULT NULL ,
`StopTime`  datetime NULL DEFAULT NULL ,
`CreateDate`  datetime NULL DEFAULT NULL ,
`UpdateDate`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`UserId`),
FOREIGN KEY (`SysRoleId`) REFERENCES `s_sysrole` (`SysRoleId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`DeptRoleId`) REFERENCES `b_deptrole` (`DeptRoleId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_15` (`SysRoleId`) USING BTREE ,
INDEX `FK_Reference_7` (`DeptRoleId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='ûϵͳеĻϢ'
AUTO_INCREMENT=34

;

-- ----------------------------
-- Records of b_user
-- ----------------------------
BEGIN;
INSERT INTO `b_user` VALUES ('19', '6', '研发经理A', '1', '超级管理员', 'testuser1', '961f8366a98ffed6a91afe66a6c9fcbf', '1', '2009-02-05 12:47:17', '2009-02-05 12:47:17', '2009-02-05 12:47:17', '2013-11-01 08:53:35'), ('20', '7', '研发经理B', '5', '普通用户', 'testuser2', '58dd024d49e1d1b83a5d307f09f32734', '1', '2009-02-05 12:47:37', '2009-02-05 12:47:37', '2009-02-05 12:47:37', '2010-05-12 14:33:09'), ('21', '6', '研发经理A', '5', '普通用户', 'testuser3', '1e4332f65a7a921075fbfb92c7c60cce', '1', '2009-02-05 13:08:49', '2009-02-05 13:08:49', '2009-02-05 13:08:49', '2010-05-12 14:33:16'), ('23', '6', '研发经理A', '1', '超级管理员', 'admin', '21232f297a57a5a743894a0e4a801fc3', '1', '2009-02-08 15:13:36', '2009-02-08 15:13:36', '2009-02-08 15:13:36', '2009-02-08 15:13:36'), ('26', '6', '研发经理A', '5', '普通用户', 'white', 'd508fe45cecaf653904a0e774084bb5c', '1', '2010-05-10 10:20:39', '2010-05-10 10:20:39', '2010-05-10 10:20:39', '2010-05-12 14:33:25'), ('27', '10', '开发经理', '1', '超级管理员', 'leonyip', 'a29cdd29a735706cc9c6a3f64a91a4d4', '1', '2012-10-18 10:38:12', '2012-10-18 10:38:12', '2012-10-18 10:38:12', '2012-10-18 10:38:12'), ('28', '11', 'Java工程师', '5', '普通用户', 'zhangsan', '01d7f40760960e7bd9443513f22ab9af', '1', '2012-10-18 10:38:36', '2012-10-18 10:38:36', '2012-10-18 10:38:36', '2012-10-18 10:38:36'), ('29', '11', 'Java工程师', '5', '普通用户', 'lisi', 'dc3a8f1670d65bea69b7b65048a0ac40', '1', '2012-10-18 10:38:48', '2012-10-18 10:38:48', '2012-10-18 10:38:48', '2012-10-18 10:38:48'), ('30', '12', '前端工程师', '5', '普通用户', 'wangwu', '9f001e4166cf26bfbdd3b4f67d9ef617', '1', '2012-10-18 10:39:01', '2012-10-18 10:39:01', '2012-10-18 10:39:01', '2012-10-18 10:39:01'), ('31', '13', '测试工程师', '5', '普通用户', 'zhaoliu', '27311020efc4ce2806feca0aab933fbd', '1', '2012-10-18 10:39:15', '2012-10-18 10:39:15', '2012-10-18 10:39:15', '2012-10-18 10:39:15'), ('32', '15', 'ASP.NET工程师', '5', '普通用户', 'weixiaobao', 'c4dc62c134ab8b73ec7ea07297e4a5b5', '1', '2013-10-30 11:17:20', '2013-10-30 11:17:20', '2013-10-30 11:17:20', '2013-10-30 11:17:20'), ('33', '8', '产品经理A', '1', '超级管理员', 'kangxi', 'cdd9876b122c924b0a52b20147b3f637', '1', '2013-10-30 15:30:51', '2013-10-30 15:30:51', '2013-10-30 15:30:51', '2013-10-30 15:30:51');
COMMIT;

-- ----------------------------
-- Table structure for b_userdetail
-- ----------------------------
DROP TABLE IF EXISTS `b_userdetail`;
CREATE TABLE `b_userdetail` (
`UserId`  int(12) NOT NULL AUTO_INCREMENT ,
`RealName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`Sex`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`JoinTime`  datetime NULL DEFAULT NULL ,
`UserType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserPrice`  double NULL DEFAULT NULL ,
`UserDesc`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`UserRate`  int(4) NULL DEFAULT NULL ,
`WorkStatus`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`UserId`),
FOREIGN KEY (`UserId`) REFERENCES `b_user` (`UserId`) ON DELETE RESTRICT ON UPDATE RESTRICT
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=34

;

-- ----------------------------
-- Records of b_userdetail
-- ----------------------------
BEGIN;
INSERT INTO `b_userdetail` VALUES ('19', 'testuser1', '1', '2009-02-06 00:00:00', '0', '5000', '', '0', null), ('20', 'testuser2', '1', '2009-02-06 00:00:00', '0', '10000', 'asdasdasd', '0', null), ('23', 'hjhjhjhj', '1', null, '0', '10000', '', '0', null), ('26', 'white', '1', '2010-05-10 00:00:00', '0', '10000', '这是一头猪', '0', null), ('27', 'leonyip', '1', '2012-10-18 00:00:00', '1', '12000', '', '0', null), ('28', 'zhangsan', '1', '2012-10-18 00:00:00', '1', '8000', '', '0', null), ('29', 'lisi', '1', '2012-10-18 00:00:00', '0', '8000', '', '0', null), ('30', 'wangwu', '0', '2012-10-18 00:00:00', '2', '7000', '', '0', null), ('31', 'zhaoliu', '1', '2012-10-18 00:00:00', '0', '5000', '', '0', null), ('32', '韦小宝', '1', '2013-10-29 00:00:00', '1', '7500', '', '0', null), ('33', 'kangxi', null, null, null, '0', null, '0', null);
COMMIT;

-- ----------------------------
-- Table structure for m_milestone
-- ----------------------------
DROP TABLE IF EXISTS `m_milestone`;
CREATE TABLE `m_milestone` (
`MilestoneId`  int(12) NOT NULL AUTO_INCREMENT ,
`PrjId`  int(12) NULL DEFAULT NULL ,
`PrjName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`MilestoneName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BeginDatePlan`  date NULL DEFAULT NULL ,
`EndDatePlan`  date NULL DEFAULT NULL ,
`BeginDateFact`  date NULL DEFAULT NULL ,
`EndDateFact`  date NULL DEFAULT NULL ,
`PricePlan`  double NULL DEFAULT NULL ,
`PriceFact`  double NULL DEFAULT NULL ,
`MilestoneDesc`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ModifyDesc`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PriceRatePlan`  int(12) NULL DEFAULT NULL ,
`PriceRateFact`  int(12) NULL DEFAULT NULL ,
PRIMARY KEY (`MilestoneId`),
FOREIGN KEY (`PrjId`) REFERENCES `m_project` (`PrjId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_3` (`PrjId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=48

;

-- ----------------------------
-- Records of m_milestone
-- ----------------------------
BEGIN;
INSERT INTO `m_milestone` VALUES ('16', '8', 'test prj', 'stone1', '2009-02-07', '2009-02-08', '2009-02-07', '2009-02-08', '45000', '50000', 'stone1', '', null, null), ('17', '9', 'test2', 'stone1', '2009-02-06', '2009-02-21', '2009-02-06', '2009-02-21', '35000', '35000', 'stone1', '', null, null), ('18', '9', 'test2', 'stone2', '2009-02-08', '2009-02-22', '2009-02-08', '2009-02-22', '3333.33', '3333.33', 'stone2', '', null, null), ('19', '9', 'test2', 'stone3', '2009-02-08', '2009-02-14', '2009-02-08', '2009-02-14', '1904.76', '4285.71', 'dasdasd', '', null, null), ('20', '9', 'test2', 'stone5', '2009-02-09', '2009-02-14', '2009-02-09', '2009-02-14', '952.38', '0', 'dsd', '', null, null), ('21', '9', 'test2', 'stone6', '2009-02-16', '2009-02-28', '2009-02-20', '2009-03-20', '22380.95', '40952.38', 'ddddd', '', null, null), ('22', '9', 'test2', 'asdasd', '2009-02-22', '2009-02-28', '2009-02-22', '2009-02-28', '0', '476.19', 'asdasd', '', null, null), ('23', '10', 'CMS', '需求分析', '2009-02-10', '2009-02-16', '2009-02-10', '2009-02-16', '476.19', '714.19', '需求分析', '', null, null), ('24', '10', 'CMS', '设计', '2009-02-17', '2009-02-23', '2009-02-17', '2009-02-23', '20476.19', '20476.19', '设计', '', null, null), ('25', '10', 'CMS', '配置管理', '2009-02-17', '2009-02-23', '2009-02-17', '2009-02-23', '0', '0', '配置管理', '', null, null), ('26', '10', 'CMS', '开发', '2009-02-24', '2009-03-20', '2009-02-24', '2009-03-20', '0', '0', '开发', '', null, null), ('27', '10', 'CMS', '测试', '2009-03-24', '2009-03-31', '2009-03-24', '2009-03-31', '0', '0', '测试', '', null, null), ('29', '13', 't1', 'a1', '2010-05-14', '2010-05-15', '2010-05-14', '2010-05-15', '40476.19', '40476.19', 'dd', '', '10', '10'), ('30', '15', 'aaa', '项目开始', '2012-07-15', '2012-07-16', '2012-07-15', '2012-07-16', '7500', '7500', '项目开始了。', '', '10000', '10000'), ('33', '17', '网上书城项目', '需求分析', '2012-10-22', '2012-10-29', '2012-10-22', '2012-10-29', '2857.15', '2857.15', '需求分析。。。。', '', '0', '0'), ('34', '17', '网上书城项目', '软件设计', '2012-10-30', '2012-11-12', '2012-10-30', '2012-11-12', '8571.42', '8571.42', '具体设计阶段', '', '0', '0'), ('35', '17', '网上书城项目', '编码实现', '2012-11-13', '2012-12-17', '2012-11-13', '2012-12-17', '112999.84', '112999.84', '编码实现阶段', '', '0', '0'), ('36', '17', '网上书城项目', '测试发布', '2012-12-19', '2012-12-28', '2012-12-19', '2012-12-28', '13333.32', '13333.32', '测试发布阶段', '', '0', '0'), ('38', '19', '这是大买卖', '项目筹备', '2013-10-31', '2013-11-06', '2013-10-31', '2013-11-06', '58933.4', '58933.4', '项目筹备阶段，花钱比较多。', '', '0', '0'), ('39', '19', '这是大买卖', '开发阶段', '2013-11-07', '2013-11-19', '2013-11-07', '2013-11-19', '19695.7', '19095.7', '这是开发的阶段', '', '0', '0'), ('40', '20', '攻占首尔', '项目筹备', '2013-11-01', '2013-11-06', '2013-11-01', '2013-11-06', '75928.6', '70728.6', '招兵买马，粮草先行。', '实际支出少于预算。', '0', '0'), ('41', '20', '攻占首尔', '项目开始', '2013-11-07', '2013-11-15', '2013-11-07', '2013-11-15', '5728.9', '5728.9', '项目开始了。', '', '0', '0'), ('42', '21', '保卫钓鱼岛', '保卫钓鱼岛1', '2013-11-05', '2014-06-05', '2013-11-05', '2014-06-05', '0', '0', '点点滴滴', '', '0', '0'), ('43', '23', 'aa', 'aaa', '2013-11-15', '2013-12-15', '2013-11-15', '2013-12-15', '4700', '4700', 'aaa', '', '0', '0'), ('44', '24', 'aabb', 'aaa', '2013-11-19', '2013-11-30', '2013-11-19', '2013-11-30', '1742.9', '1742.9', 'asdasd', '', '0', '0'), ('45', '25', 'asdasd', 'ass', '2013-11-20', '2013-11-30', '2013-11-20', '2013-11-30', '609.6', '609.6', 'asd', '', '0', '0'), ('46', '26', 'dfsdf', 'as', '2013-11-23', '2013-11-27', '2013-11-23', '2013-11-27', '0', '0', 'asd', '', '0', '0'), ('47', '27', 'aaaaaaaaaaaaaaa', 'aaa', '2014-11-27', '2014-11-29', '2014-11-27', '2014-11-29', '76.2', '76.2', 'aaa', '', '0', '0');
COMMIT;

-- ----------------------------
-- Table structure for m_milestonedetail
-- ----------------------------
DROP TABLE IF EXISTS `m_milestonedetail`;
CREATE TABLE `m_milestonedetail` (
`DetailId`  int(12) NOT NULL AUTO_INCREMENT ,
`MilestoneId`  int(12) NULL DEFAULT NULL ,
`ResId`  int(12) NULL DEFAULT NULL ,
`ResName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ResNum`  int(12) NULL DEFAULT NULL ,
`BeginDate`  date NULL DEFAULT NULL ,
`EndDate`  date NULL DEFAULT NULL ,
`UseRate`  int(12) NULL DEFAULT NULL ,
`Price`  double NULL DEFAULT NULL ,
`ResType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PriceRate`  int(10) NULL DEFAULT NULL ,
PRIMARY KEY (`DetailId`),
FOREIGN KEY (`MilestoneId`) REFERENCES `m_milestone` (`MilestoneId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_1` (`MilestoneId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=140

;

-- ----------------------------
-- Records of m_milestonedetail
-- ----------------------------
BEGIN;
INSERT INTO `m_milestonedetail` VALUES ('48', '16', '1', 'testuser1', '1', '2009-02-06', '2009-02-09', '50', '5000', '1', null), ('49', '16', '3', 'dell2950', '2', '2009-02-12', '2009-02-17', '100', '40000', '2', null), ('50', '16', '4', '一级市场调研', '1', '2009-02-12', '2009-02-18', '100', '0', '3', null), ('51', '17', '1', 'testuser1', '1', '2009-02-06', '2009-02-21', '50', '5000', '1', null), ('52', '17', '3', 'dell2950', '1', '2009-02-12', '2009-02-25', '100', '20000', '2', null), ('53', '17', '5', '市场推广', '1', '2009-02-06', '2009-02-12', '100', '0', '3', null), ('54', '17', '1', 'testuser1', '1', '2009-02-08', '2009-02-14', '100', '10000', '1', null), ('55', '18', '1', 'testuser1', '1', '2009-02-08', '2009-02-10', '100', '476.19', '1', null), ('56', '18', '3', 'testuser2', '1', '2009-02-10', '2009-02-18', '100', '2857.14', '1', null), ('57', '19', '3', 'testuser2', '1', '2009-02-10', '2009-02-14', '100', '1904.76', '1', null), ('58', '19', '1', 'testuser1', '1', '2009-02-08', '2009-02-14', '80', '1904.8', '1', null), ('59', '20', '1', 'testuser1', '1', '2009-02-10', '2009-02-12', '100', '952.38', '1', null), ('60', '20', '3', 'testuser2', '1', '2009-02-10', '2009-02-13', '100', '1428.57', '1', null), ('61', '21', '1', 'testuser1', '1', '2009-02-20', '2009-02-25', '100', '1428.57', '1', null), ('62', '21', '3', 'testuser2', '1', '2009-02-24', '2009-02-26', '100', '952.38', '1', null), ('63', '21', '3', 'dell2950', '1', '2009-02-20', '2009-02-25', '100', '20000', '2', null), ('64', '22', '1', 'testuser1', '1', '2009-02-22', '2009-02-28', '100', '476.19', '1', null), ('65', '23', '1', 'testuser1', '1', '2009-02-10', '2009-02-16', '100', '476.19', '1', null), ('66', '23', '3', 'testuser2', '1', '2009-02-10', '2009-02-16', '50', '238', '1', null), ('67', '24', '1', 'testuser1', '1', '2009-02-17', '2009-02-20', '100', '476.19', '1', null), ('68', '24', '3', 'dell2950', '1', '2009-02-17', '2009-02-20', '100', '20000', '2', null), ('69', '24', '4', '一级市场调研', '1', '2009-02-17', '2009-02-20', '10', '0', '3', null), ('70', '29', '3', 'dell2950', '2', '2010-05-14', '2010-05-15', '100', '40000', '2', '0'), ('71', '29', '5', '市场推广', '1', '2010-05-14', '2010-05-15', '100', '0', '3', '10'), ('72', '29', '1', 'testuser1', '1', '2010-05-14', '2010-05-15', '100', '476.19', '1', '0'), ('73', '30', '4', 'DELL XPS 服务器', '1', '2012-07-15', '2012-07-16', '30', '7500', '2', '0'), ('76', '30', '3', 'testuser2', '1', '2012-07-15', '2012-07-16', '20', '0', '1', '10000'), ('91', '33', '10', 'leonyip', '1', '2012-10-22', '2012-10-29', '100', '2857.15', '1', '0'), ('92', '34', '10', 'leonyip', '1', '2012-10-30', '2012-11-12', '100', '5142.87', '1', '0'), ('93', '34', '7', 'zhangsan', '1', '2012-10-30', '2012-11-12', '100', '3428.55', '1', '0'), ('94', '35', '10', 'leonyip', '1', '2012-11-13', '2012-12-17', '100', '13714.32', '1', '0'), ('95', '35', '7', 'zhangsan', '1', '2012-11-13', '2012-12-17', '100', '9142.8', '1', '0'), ('96', '35', '8', 'lisi', '1', '2012-11-13', '2012-12-17', '100', '9142.8', '1', '0'), ('97', '35', '9', 'wangwu', '1', '2012-11-13', '2012-12-17', '100', '7999.92', '1', '0'), ('98', '35', '4', 'DELL XPS 服务器', '1', '2012-11-13', '2012-12-17', '100', '25000', '2', '0'), ('99', '35', '5', '笔记本电脑（开发用）', '4', '2012-11-13', '2012-12-17', '100', '48000', '2', '0'), ('100', '36', '10', 'leonyip', '1', '2012-12-19', '2012-12-28', '100', '4000.01', '1', '0'), ('101', '36', '7', 'zhangsan', '1', '2012-12-19', '2012-12-28', '100', '2666.65', '1', '0'), ('102', '36', '8', 'lisi', '1', '2012-12-19', '2012-12-28', '100', '2666.65', '1', '0'), ('103', '36', '9', 'wangwu', '1', '2012-12-19', '2012-12-28', '100', '2333.31', '1', '0'), ('104', '36', '11', 'zhaoliu', '1', '2012-12-19', '2012-12-28', '100', '1666.7', '1', '0'), ('108', '38', '13', 'kangxi', '1', '2013-10-31', '2013-11-06', '20', '381', '1', '0'), ('109', '38', '12', 'weixiaobao', '1', '2013-10-31', '2013-11-06', '20', '285.8', '1', '0'), ('110', '38', '9', 'wangwu', '1', '2013-10-31', '2013-11-06', '20', '266.6', '1', '0'), ('111', '38', '3', 'dell2950', '1', '2013-10-31', '2013-11-06', '100', '20000', '2', '0'), ('112', '38', '5', '笔记本电脑（开发用）', '3', '2013-10-31', '2013-11-06', '100', '36000', '2', '0'), ('113', '38', '6', '请客户吃饭', '1', '2013-10-31', '2013-11-06', '100', '2000', '3', '0'), ('114', '39', '13', 'kangxi', '1', '2013-11-07', '2013-11-19', '50', '1905', '1', '0'), ('115', '39', '12', 'weixiaobao', '1', '2013-11-07', '2013-11-19', '50', '1428.5', '1', '0'), ('116', '39', '9', 'wangwu', '1', '2013-11-07', '2013-11-19', '60', '1600.2', '1', '0'), ('117', '39', '11', 'zhaoliu', '1', '2013-11-07', '2013-11-19', '40', '762', '1', '0'), ('118', '39', '5', '笔记本电脑（开发用）', '1', '2013-11-07', '2013-11-19', '100', '12000', '2', '0'), ('119', '39', '6', '请客户吃饭', '1', '2013-11-07', '2013-11-19', '100', '2000', '3', '0'), ('120', '40', '13', 'kangxi', '1', '2013-11-01', '2013-11-06', '20', '285.8', '1', '0'), ('121', '40', '12', 'weixiaobao', '1', '2013-11-01', '2013-11-06', '20', '214.2', '1', '0'), ('122', '40', '8', 'lisi', '1', '2013-11-01', '2013-11-06', '20', '228.6', '1', '0'), ('123', '40', '9', 'wangwu', '1', '2013-11-01', '2013-11-06', '20', '200', '1', '0'), ('124', '40', '4', 'DELL XPS 服务器', '1', '2013-11-01', '2013-11-06', '100', '25000', '2', '0'), ('125', '40', '5', '笔记本电脑（开发用）', '4', '2013-11-01', '2013-11-06', '100', '48000', '2', '0'), ('126', '40', '6', '请客户吃饭', '1', '2013-11-01', '2013-11-06', '100', '2000', '3', '0'), ('127', '41', '10', 'leonyip', '1', '2013-11-07', '2013-11-15', '10', '342.9', '1', '0'), ('128', '41', '12', 'weixiaobao', '1', '2013-11-07', '2013-11-15', '10', '214.3', '1', '0'), ('129', '41', '8', 'lisi', '1', '2013-11-07', '2013-11-15', '20', '457.2', '1', '0'), ('130', '41', '11', 'zhaoliu', '1', '2013-11-07', '2013-11-15', '50', '714.5', '1', '0'), ('131', '41', '6', '请客户吃饭', '2', '2013-11-07', '2013-11-15', '100', '4000', '3', '0'), ('132', '43', '11', 'zhaoliu', '1', '2013-11-15', '2013-12-15', '10', '500', '1', '0'), ('133', '43', '5', '笔记本电脑（开发用）', '1', '2013-11-15', '2013-12-15', '30', '3600', '2', '0'), ('134', '43', '6', '请客户吃饭', '1', '2013-11-15', '2013-12-15', '30', '600', '3', '0'), ('135', '44', '8', 'lisi', '1', '2013-11-19', '2013-11-30', '10', '342.9', '1', '0'), ('136', '44', '5', '笔记本电脑（开发用）', '1', '2013-11-19', '2013-11-30', '10', '1200', '2', '0'), ('137', '44', '6', '请客户吃饭', '1', '2013-11-19', '2013-11-30', '10', '200', '3', '0'), ('138', '45', '7', 'zhangsan', '1', '2013-11-20', '2013-11-30', '20', '609.6', '1', '0'), ('139', '47', '7', 'zhangsan', '1', '2014-11-27', '2014-11-29', '10', '76.2', '1', '0');
COMMIT;

-- ----------------------------
-- Table structure for m_milestonedetailfact
-- ----------------------------
DROP TABLE IF EXISTS `m_milestonedetailfact`;
CREATE TABLE `m_milestonedetailfact` (
`DetailId`  int(12) NOT NULL AUTO_INCREMENT ,
`MilestoneId`  int(12) NULL DEFAULT NULL ,
`ResId`  int(12) NULL DEFAULT NULL ,
`ResName`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ResNum`  int(12) NULL DEFAULT NULL ,
`BeginDate`  date NULL DEFAULT NULL ,
`EndDate`  date NULL DEFAULT NULL ,
`UseRate`  int(4) NULL DEFAULT NULL ,
`Price`  double NULL DEFAULT NULL ,
`AlterDesc`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ResType`  char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`PriceRate`  int(11) NULL DEFAULT NULL ,
PRIMARY KEY (`DetailId`),
FOREIGN KEY (`MilestoneId`) REFERENCES `m_milestone` (`MilestoneId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_2` (`MilestoneId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='ΪʵʵһӦ̱ڲԴãֻ̱һǷ̱ԵĲ'
AUTO_INCREMENT=140

;

-- ----------------------------
-- Records of m_milestonedetailfact
-- ----------------------------
BEGIN;
INSERT INTO `m_milestonedetailfact` VALUES ('48', '16', '1', 'testuser1', '1', '2009-02-06', '2009-02-09', '100', '10000', '', '1', null), ('49', '16', '3', 'dell2950', '2', '2009-02-12', '2009-02-17', '100', '40000', '', '2', null), ('50', '16', '4', '一级市场调研', '1', '2009-02-12', '2009-02-18', '100', '0', '', '3', null), ('51', '17', '1', 'testuser1', '1', '2009-02-06', '2009-02-21', '50', '5000', '', '1', null), ('52', '17', '3', 'dell2950', '1', '2009-02-12', '2009-02-25', '100', '20000', '', '2', null), ('53', '17', '5', '市场推广', '1', '2009-02-06', '2009-02-12', '100', '0', '', '3', null), ('54', '17', '1', 'testuser1', '1', '2009-02-08', '2009-02-14', '100', '10000', '', '1', null), ('55', '18', '1', 'testuser1', '1', '2009-02-08', '2009-02-10', '100', '476.19', '', '1', null), ('56', '18', '3', 'testuser2', '1', '2009-02-10', '2009-02-18', '100', '2857.14', '', '1', null), ('57', '19', '3', 'testuser2', '1', '2009-02-10', '2009-02-14', '100', '1904.76', '', '1', null), ('58', '19', '1', 'testuser1', '1', '2009-02-08', '2009-02-14', '100', '2380.95', '', '1', null), ('62', '21', '3', 'testuser2', '1', '2009-02-24', '2009-02-26', '100', '952.38', '', '1', null), ('63', '21', '3', 'dell2950', '2', '2009-02-20', '2009-03-20', '100', '40000', 'asd', '2', null), ('64', '22', '1', 'testuser1', '1', '2009-02-22', '2009-02-28', '100', '476.19', '', '1', null), ('65', '23', '1', 'testuser1', '1', '2009-02-10', '2009-02-16', '100', '476.19', '', '1', null), ('66', '23', '3', 'testuser2', '1', '2009-02-10', '2009-02-16', '50', '238', '', '1', null), ('67', '24', '1', 'testuser1', '1', '2009-02-17', '2009-02-20', '100', '476.19', '', '1', null), ('68', '24', '3', 'dell2950', '1', '2009-02-17', '2009-02-20', '100', '20000', '', '2', null), ('69', '24', '4', '一级市场调研', '1', '2009-02-17', '2009-02-20', '10', '0', '', '3', null), ('70', '29', '3', 'dell2950', '2', '2010-05-14', '2010-05-15', '100', '40000', '', '2', '0'), ('71', '29', '5', '市场推广', '1', '2010-05-14', '2010-05-15', '100', '0', '', '3', '10'), ('72', '29', '1', 'testuser1', '1', '2010-05-14', '2010-05-15', '100', '476.19', '', '1', '0'), ('73', '30', '4', 'DELL XPS 服务器', '1', '2012-07-15', '2012-07-16', '30', '7500', '', '2', '0'), ('76', '30', '3', 'testuser2', '1', '2012-07-15', '2012-07-16', '20', '0', '', '1', '10000'), ('91', '33', '10', 'leonyip', '1', '2012-10-22', '2012-10-29', '100', '2857.15', '', '1', '0'), ('92', '34', '10', 'leonyip', '1', '2012-10-30', '2012-11-12', '100', '5142.87', '', '1', '0'), ('93', '34', '7', 'zhangsan', '1', '2012-10-30', '2012-11-12', '100', '3428.55', '', '1', '0'), ('94', '35', '10', 'leonyip', '1', '2012-11-13', '2012-12-17', '100', '13714.32', '', '1', '0'), ('95', '35', '7', 'zhangsan', '1', '2012-11-13', '2012-12-17', '100', '9142.8', '', '1', '0'), ('96', '35', '8', 'lisi', '1', '2012-11-13', '2012-12-17', '100', '9142.8', '', '1', '0'), ('97', '35', '9', 'wangwu', '1', '2012-11-13', '2012-12-17', '100', '7999.92', '', '1', '0'), ('98', '35', '4', 'DELL XPS 服务器', '1', '2012-11-13', '2012-12-17', '100', '25000', '', '2', '0'), ('99', '35', '5', '笔记本电脑（开发用）', '4', '2012-11-13', '2012-12-17', '100', '48000', '', '2', '0'), ('100', '36', '10', 'leonyip', '1', '2012-12-19', '2012-12-28', '100', '4000.01', '', '1', '0'), ('101', '36', '7', 'zhangsan', '1', '2012-12-19', '2012-12-28', '100', '2666.65', '', '1', '0'), ('102', '36', '8', 'lisi', '1', '2012-12-19', '2012-12-28', '100', '2666.65', '', '1', '0'), ('103', '36', '9', 'wangwu', '1', '2012-12-19', '2012-12-28', '100', '2333.31', '', '1', '0'), ('104', '36', '11', 'zhaoliu', '1', '2012-12-19', '2012-12-28', '100', '1666.7', '', '1', '0'), ('108', '38', '13', 'kangxi', '1', '2013-10-31', '2013-11-06', '20', '381', '', '1', '0'), ('109', '38', '12', 'weixiaobao', '1', '2013-10-31', '2013-11-06', '20', '285.8', '', '1', '0'), ('110', '38', '9', 'wangwu', '1', '2013-10-31', '2013-11-06', '20', '266.6', '', '1', '0'), ('111', '38', '3', 'dell2950', '1', '2013-10-31', '2013-11-06', '100', '20000', '', '2', '0'), ('112', '38', '5', '笔记本电脑（开发用）', '3', '2013-10-31', '2013-11-06', '100', '36000', '', '2', '0'), ('113', '38', '6', '请客户吃饭', '1', '2013-10-31', '2013-11-06', '100', '2000', '', '3', '0'), ('114', '39', '13', 'kangxi', '1', '2013-11-07', '2013-11-19', '50', '1905', '', '1', '0'), ('115', '39', '12', 'weixiaobao', '1', '2013-11-07', '2013-11-19', '50', '1428.5', '', '1', '0'), ('116', '39', '9', 'wangwu', '1', '2013-11-07', '2013-11-19', '60', '1600.2', '', '1', '0'), ('117', '39', '11', 'zhaoliu', '1', '2013-11-07', '2013-11-19', '40', '762', '', '1', '0'), ('118', '39', '5', '笔记本电脑（开发用）', '1', '2013-11-07', '2013-11-19', '100', '12000', '', '2', '0'), ('119', '39', '6', '请客户吃饭', '1', '2013-11-07', '2013-11-19', '70', '1400', '人少，没花那么多钱。', '3', '0'), ('120', '40', '13', 'kangxi', '1', '2013-11-01', '2013-11-06', '20', '285.8', '', '1', '0'), ('121', '40', '12', 'weixiaobao', '1', '2013-11-01', '2013-11-06', '20', '214.2', '', '1', '0'), ('122', '40', '8', 'lisi', '1', '2013-11-01', '2013-11-06', '20', '228.6', '', '1', '0'), ('123', '40', '9', 'wangwu', '1', '2013-11-01', '2013-11-06', '20', '200', '', '1', '0'), ('124', '40', '4', 'DELL XPS 服务器', '1', '2013-11-01', '2013-11-06', '100', '25000', '', '2', '0'), ('125', '40', '5', '笔记本电脑（开发用）', '4', '2013-11-01', '2013-11-06', '90', '43200', '熟人，打了9折', '2', '0'), ('126', '40', '6', '请客户吃饭', '1', '2013-11-01', '2013-11-06', '80', '1600', '人没那么多，所以钱花的少。', '3', '0'), ('127', '41', '10', 'leonyip', '1', '2013-11-07', '2013-11-15', '10', '342.9', '', '1', '0'), ('128', '41', '12', 'weixiaobao', '1', '2013-11-07', '2013-11-15', '10', '214.3', '', '1', '0'), ('129', '41', '8', 'lisi', '1', '2013-11-07', '2013-11-15', '20', '457.2', '', '1', '0'), ('130', '41', '11', 'zhaoliu', '1', '2013-11-07', '2013-11-15', '50', '714.5', '', '1', '0'), ('131', '41', '6', '请客户吃饭', '2', '2013-11-07', '2013-11-15', '100', '4000', '', '3', '0'), ('132', '43', '11', 'zhaoliu', '1', '2013-11-15', '2013-12-15', '10', '500', '', '1', '0'), ('133', '43', '5', '笔记本电脑（开发用）', '1', '2013-11-15', '2013-12-15', '30', '3600', '', '2', '0'), ('134', '43', '6', '请客户吃饭', '1', '2013-11-15', '2013-12-15', '30', '600', '', '3', '0'), ('135', '44', '8', 'lisi', '1', '2013-11-19', '2013-11-30', '10', '342.9', '', '1', '0'), ('136', '44', '5', '笔记本电脑（开发用）', '1', '2013-11-19', '2013-11-30', '10', '1200', '', '2', '0'), ('137', '44', '6', '请客户吃饭', '1', '2013-11-19', '2013-11-30', '10', '200', '', '3', '0'), ('138', '45', '7', 'zhangsan', '1', '2013-11-20', '2013-11-30', '20', '609.6', '', '1', '0'), ('139', '47', '7', 'zhangsan', '1', '2014-11-27', '2014-11-29', '10', '76.2', 'a', '1', '0');
COMMIT;

-- ----------------------------
-- Table structure for m_project
-- ----------------------------
DROP TABLE IF EXISTS `m_project`;
CREATE TABLE `m_project` (
`PrjId`  int(12) NOT NULL AUTO_INCREMENT ,
`PrjName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`DeptId`  int(12) NULL DEFAULT NULL ,
`DeptName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`ManagerId`  int(12) NULL DEFAULT NULL ,
`ManagerName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`BeginDatePlan`  date NULL DEFAULT NULL ,
`EndDatePlan`  date NULL DEFAULT NULL ,
`SumPricePlan`  double NULL DEFAULT NULL ,
`BeginDateFact`  date NULL DEFAULT NULL ,
`EndDateFact`  date NULL DEFAULT NULL ,
`SumPriceFact`  double NULL DEFAULT NULL ,
`PrjDesc`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`PrjId`),
FOREIGN KEY (`DeptId`) REFERENCES `b_department` (`DeptId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`ManagerId`) REFERENCES `b_user` (`UserId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_12` (`DeptId`) USING BTREE ,
INDEX `FK_Reference_5` (`ManagerId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=28

;

-- ----------------------------
-- Records of m_project
-- ----------------------------
BEGIN;
INSERT INTO `m_project` VALUES ('8', 'test prj', '17', '研发部', '19', 'testuser1', '2009-02-06', '2009-02-28', '54000', '2009-02-06', '2009-02-28', '60000', 'test prj'), ('9', 'test2', '18', '产品部', '20', 'testuser2', '2009-02-06', '2009-02-07', '63571.42', '2009-02-06', '2009-02-07', '84047.61', 'adasd'), ('10', 'CMS', '17', '研发部', '23', 'hjhjhjhj', '2009-02-10', '2009-04-01', '20952.38', '2009-02-10', '2009-04-01', '25428.38', '测试'), ('13', 't1', '17', '研发部', '26', 'white', '2010-05-14', '2010-05-24', '44523.79', '2010-05-14', '2010-05-24', '44523.79', 'ddd'), ('14', 'test项目', '17', '研发部', '19', 'testuser1', '2012-07-20', '2012-08-02', '0', '2012-07-20', '2012-08-02', '0', '实训项目'), ('15', 'aaa', '17', '研发部', '19', 'testuser1', '2012-07-15', '2012-07-31', '757500', '2012-07-15', '2012-07-31', '757500', 'aaa'), ('17', '网上书城项目', '17', '研发部', '27', 'leonyip', '2012-10-22', '2012-12-28', '137761.73', '2012-10-22', '2012-12-28', '137761.73', '这是一个全新的项目，很具挑战性。'), ('19', '这是大买卖', '17', '研发部', '33', 'kangxi', '2013-10-31', '2013-11-30', '78629.1', '2013-10-31', '2013-11-30', '78029.1', '这真是一单大买卖'), ('20', '攻占首尔', '17', '研发部', '33', 'kangxi', '2013-11-01', '2013-12-01', '81657.5', '2013-11-01', '2013-12-01', '76457.5', '先攻占首尔，再攻占东京。'), ('21', '保卫钓鱼岛', '17', '研发部', '28', 'zhangsan', '2013-11-05', '2014-11-01', '0', '2013-11-05', '2014-11-01', '0', '保卫钓鱼岛'), ('22', 'HR', '17', '研发部', '20', 'testuser2', '2013-11-13', '2013-11-21', '0', '2013-11-13', '2013-11-21', '0', 'HR'), ('23', 'aa', '20', 'aa', '29', 'lisi', '2013-11-15', '2013-12-15', '4700', '2013-11-15', '2013-12-15', '4700', 'aa'), ('24', 'aabb', '18', '产品部', '31', 'zhaoliu', '2013-11-19', '2013-11-30', '1742.9', '2013-11-19', '2013-11-30', '1742.9', 'aa'), ('25', 'asdasd', '20', 'aa', '31', 'zhaoliu', '2013-11-21', '2013-11-29', '609.6', '2013-11-20', '2013-11-30', '609.6', 'asdasd'), ('26', 'dfsdf', '17', '研发部', '27', 'leonyip', '2013-11-23', '2013-11-27', '0', '2013-11-23', '2013-11-27', '0', 'wedawfd'), ('27', 'aaaaaaaaaaaaaaa', '18', '产品部', '20', 'testuser2', '2014-11-27', '2016-11-30', '76.2', '2014-11-27', '2016-11-30', '76.2', 'aaaaaaaaaaaaaa');
COMMIT;

-- ----------------------------
-- Table structure for s_sysfunction
-- ----------------------------
DROP TABLE IF EXISTS `s_sysfunction`;
CREATE TABLE `s_sysfunction` (
`FunId`  int(12) NOT NULL AUTO_INCREMENT ,
`FunName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FunCode`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`FunDesc`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`FunId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=44

;

-- ----------------------------
-- Records of s_sysfunction
-- ----------------------------
BEGIN;
INSERT INTO `s_sysfunction` VALUES ('1', '添加人员功能', 'add', 'user'), ('2', '修改人员功能', 'edit', 'user'), ('3', '删除人员功能', 'delete', 'user'), ('4', '查看人员功能', 'view', 'user'), ('5', '添加部门', 'add', 'department'), ('6', '修改部门', 'edit', 'department'), ('7', '删除部门', 'delete', 'department'), ('8', '查看部门', 'view', 'department'), ('9', '添加部门角色', 'add', 'deptrole'), ('10', '修改部门角色', 'edit', 'deptrole'), ('11', '删除部门角色', 'delete', 'deptrole'), ('12', '查看部门角色', 'view', 'deptrole'), ('13', '添加基价分类', 'add', 'catalog'), ('14', '修改基价分类', 'edit', 'catalog'), ('15', '删除基价分类', 'delete', 'catalog'), ('16', '查看基价分类', 'view', 'catalog'), ('17', '添加人员基价', 'add', 'humanprice'), ('18', '修改人员基价', 'edit', 'humanprice'), ('19', '删除人员基价', 'delete', 'humanprice'), ('20', '查看人员基价', 'view', 'humanprice'), ('21', '添加资源基价', 'add', 'resprice'), ('22', '修改资源基价', 'edit', 'resprice'), ('23', '删除资源基价', 'delete', 'resprice'), ('24', '查看资源基价', 'view', 'resprice'), ('25', '添加其他基价', 'add', 'otherprice'), ('26', '修改其他基价', 'edit', 'otherprice'), ('27', '删除其他基价', 'delete', 'otherprice'), ('28', '查看其他基价', 'view', 'otherprice'), ('29', '添加系统功能', 'add', 'sysfunction'), ('30', '修改系统功能', 'edit', 'sysfunction'), ('31', '删除系统功能', 'delete', 'sysfunction'), ('32', '查看系统功能', 'view', 'sysfunction'), ('33', '添加系统角色', 'add', 'sysrole'), ('34', '修改系统角色', 'edit', 'sysrole'), ('35', '删除系统角色', 'delete', 'sysrole'), ('36', '查看系统角色', 'view', 'sysrole'), ('37', '添加项目', 'add', 'prj'), ('38', '修改项目', 'edit', 'prj'), ('39', '删除项目', 'delete', 'prj'), ('40', '查看项目（列表）', 'view', 'prj'), ('41', '查看项目（成本报告）', 'report', 'prj'), ('42', '查看项目（我负责的）', 'viewbyuser', 'prj'), ('43', '查看项目（我的部门）', 'viewbydept', 'prj');
COMMIT;

-- ----------------------------
-- Table structure for s_sysrole
-- ----------------------------
DROP TABLE IF EXISTS `s_sysrole`;
CREATE TABLE `s_sysrole` (
`SysRoleId`  int(12) NOT NULL AUTO_INCREMENT ,
`SysRoleName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SysRoleDesc`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
PRIMARY KEY (`SysRoleId`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=6

;

-- ----------------------------
-- Records of s_sysrole
-- ----------------------------
BEGIN;
INSERT INTO `s_sysrole` VALUES ('1', '超级管理员', '系统管理者'), ('5', '普通用户', '普通用户');
COMMIT;

-- ----------------------------
-- Table structure for s_sysrolefun
-- ----------------------------
DROP TABLE IF EXISTS `s_sysrolefun`;
CREATE TABLE `s_sysrolefun` (
`RefId`  int(12) NOT NULL AUTO_INCREMENT ,
`FunId`  int(12) NULL DEFAULT NULL ,
`FunName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`SysRoleId`  int(12) NULL DEFAULT NULL ,
`SysRoleName`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`CreateDate`  datetime NULL DEFAULT NULL ,
`UpdateDate`  datetime NULL DEFAULT NULL ,
PRIMARY KEY (`RefId`),
FOREIGN KEY (`FunId`) REFERENCES `s_sysfunction` (`FunId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`SysRoleId`) REFERENCES `s_sysrole` (`SysRoleId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_13` (`FunId`) USING BTREE ,
INDEX `FK_Reference_14` (`SysRoleId`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=316

;

-- ----------------------------
-- Records of s_sysrolefun
-- ----------------------------
BEGIN;
INSERT INTO `s_sysrolefun` VALUES ('249', '1', '添加人员功能', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('250', '2', '修改人员功能', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('251', '3', '删除人员功能', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('252', '4', '查看人员功能', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('253', '5', '添加部门', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('254', '6', '修改部门', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('255', '7', '删除部门', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('256', '8', '查看部门', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('257', '9', '添加部门角色', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('258', '10', '修改部门角色', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('259', '11', '删除部门角色', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('260', '12', '查看部门角色', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('261', '13', '添加基价分类', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('262', '14', '修改基价分类', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('263', '15', '删除基价分类', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('264', '16', '查看基价分类', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('265', '17', '添加人员基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('266', '18', '修改人员基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('267', '19', '删除人员基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('268', '20', '查看人员基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('269', '21', '添加资源基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('270', '22', '修改资源基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('271', '23', '删除资源基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('272', '24', '查看资源基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('273', '25', '添加其他基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('274', '26', '修改其他基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('275', '27', '删除其他基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('276', '28', '查看其他基价', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('277', '29', '添加系统功能', '1', '超级管理员', '2013-11-14 14:28:22', '2013-11-14 14:28:22'), ('278', '30', '修改系统功能', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('279', '31', '删除系统功能', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('280', '32', '查看系统功能', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('281', '33', '添加系统角色', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('282', '34', '修改系统角色', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('283', '35', '删除系统角色', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('284', '36', '查看系统角色', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('285', '37', '添加项目', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('286', '38', '修改项目', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('287', '39', '删除项目', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('288', '40', '查看项目（列表）', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('289', '41', '查看项目（成本报告）', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('290', '42', '查看项目（我负责的）', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('291', '43', '查看项目（我的部门）', '1', '超级管理员', '2013-11-14 14:28:23', '2013-11-14 14:28:23'), ('304', '4', '查看人员功能', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('305', '8', '查看部门', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('306', '12', '查看部门角色', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('307', '16', '查看基价分类', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('308', '24', '查看资源基价', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('309', '37', '添加项目', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('310', '38', '修改项目', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('311', '39', '删除项目', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('312', '40', '查看项目（列表）', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('313', '41', '查看项目（成本报告）', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('314', '42', '查看项目（我负责的）', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18'), ('315', '43', '查看项目（我的部门）', '5', '普通用户', '2013-11-20 09:57:18', '2013-11-20 09:57:18');
COMMIT;

-- ----------------------------
-- Auto increment value for b_basecatalog
-- ----------------------------
ALTER TABLE `b_basecatalog` AUTO_INCREMENT=18;

-- ----------------------------
-- Auto increment value for b_basepricehuman
-- ----------------------------
ALTER TABLE `b_basepricehuman` AUTO_INCREMENT=15;

-- ----------------------------
-- Auto increment value for b_basepriceother
-- ----------------------------
ALTER TABLE `b_basepriceother` AUTO_INCREMENT=7;

-- ----------------------------
-- Auto increment value for b_basepriceres
-- ----------------------------
ALTER TABLE `b_basepriceres` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for b_department
-- ----------------------------
ALTER TABLE `b_department` AUTO_INCREMENT=21;

-- ----------------------------
-- Auto increment value for b_deptrole
-- ----------------------------
ALTER TABLE `b_deptrole` AUTO_INCREMENT=16;

-- ----------------------------
-- Auto increment value for b_user
-- ----------------------------
ALTER TABLE `b_user` AUTO_INCREMENT=34;

-- ----------------------------
-- Auto increment value for b_userdetail
-- ----------------------------
ALTER TABLE `b_userdetail` AUTO_INCREMENT=34;

-- ----------------------------
-- Auto increment value for m_milestone
-- ----------------------------
ALTER TABLE `m_milestone` AUTO_INCREMENT=48;

-- ----------------------------
-- Auto increment value for m_milestonedetail
-- ----------------------------
ALTER TABLE `m_milestonedetail` AUTO_INCREMENT=140;

-- ----------------------------
-- Auto increment value for m_milestonedetailfact
-- ----------------------------
ALTER TABLE `m_milestonedetailfact` AUTO_INCREMENT=140;

-- ----------------------------
-- Auto increment value for m_project
-- ----------------------------
ALTER TABLE `m_project` AUTO_INCREMENT=28;

-- ----------------------------
-- Auto increment value for s_sysfunction
-- ----------------------------
ALTER TABLE `s_sysfunction` AUTO_INCREMENT=44;

-- ----------------------------
-- Auto increment value for s_sysrole
-- ----------------------------
ALTER TABLE `s_sysrole` AUTO_INCREMENT=6;

-- ----------------------------
-- Auto increment value for s_sysrolefun
-- ----------------------------
ALTER TABLE `s_sysrolefun` AUTO_INCREMENT=316;
