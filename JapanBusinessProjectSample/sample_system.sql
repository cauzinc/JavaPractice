

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
--  Table structure for `sample_business`
-- ----------------------------
DROP TABLE IF EXISTS `sample_business`;
CREATE TABLE `sample_business` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `business_name` varchar(20) NOT NULL,
    `business_tel` varchar(20) NOT NULL,
    `business_fax` varchar(20) DEFAULT NULL,
    `website` varchar(20) DEFAULT NULL,

    `address` varchar(20) DEFAULT NULL,
    `postcode` varchar(20) DEFAULT NULL,
    `advantage_field` varchar(20) DEFAULT NULL,
    `bank_name` varchar(20) DEFAULT NULL,
    `bank_branch_name` varchar(20) DEFAULT NULL,
    `bank_number` varchar(30) DEFAULT NULL,
    `bank_represent` varchar(20) DEFAULT NULL,
    `stuff_num` int(10) DEFAULT NULL,
    `annual_sales` decimal(20, 2) DEFAULT NULL,
    `transaction_deadline` datetime DEFAULT NULL,
    `payment_sight` varchar(10) DEFAULT NULL,
    `account_kind` varchar(20) DEFAULT NULL,
    `comment` varchar(200) DEFAULT NULL COMMENT '追加情報',

    `principal_id` int(11) DEFAULT NULL COMMENT '担当者',
    `status` int(10) DEFAULT 0 COMMENT '0 进行中, 1 开发完成',

    `create_user` varchar(20) NOT NULL COMMENT '作成者',
    `update_user` varchar(20) NOT NULL COMMENT '最終更新者',
    `create_time` datetime DEFAULT NULL COMMENT '作成時点',
    `update_time` datetime DEFAULT NULL COMMENT '更新時点',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sample_staff`
-- ----------------------------
DROP TABLE IF EXISTS `sample_staff`;
CREATE TABLE `sample_staff` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `staff_name` varchar(20) NOT NULL,
    `staff_gender` int(2) DEFAULT 1,
    `position` varchar(20) DEFAULT NULL,
    `business_id` varchar(20) NOT NULL,
    `prefered_field` varchar(20) DEFAULT NULL,
    `prefered_language` varchar(20) DEFAULT NULL,

    `mail` varchar(20) DEFAULT NULL,
    `tel` varchar(20) DEFAULT NULL,
    `birthday` datetime DEFAULT NULL,
    `postcode` varchar(20) DEFAULT NULL,
    `address` varchar(20) DEFAULT NULL,
    `emergency_address` varchar(20) DEFAULT NULL,
    `cloest_station` varchar(20) DEFAULT NULL,
    `favorite` varchar(30) DEFAULT NULL,
    `note` varchar(200) DEFAULT NULL COMMENT '評価',
    `comment` varchar(200) DEFAULT NULL COMMENT '追加情報',

    `create_user` varchar(20) NOT NULL COMMENT '作成者',
    `update_user` varchar(20) NOT NULL COMMENT '最終更新者',
    `create_time` datetime DEFAULT NULL COMMENT '作成時点',
    `update_time` datetime DEFAULT NULL COMMENT '更新時点',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Records of `sample_business`
-- ----------------------------
BEGIN;
INSERT INTO `sample_business`
VALUES (1, 'Nintendo', '18044443333', '000', 'nintendo.com', 'Tokyo', '123-223', 'game', 'Japan Bank', 'tokyo Japan bank', '20', 'tanaka', 5000, 20000000, '2017-06-13 21:27:06', '10D', 'normal', null, 20, 0, 'admin', 'admin', '2017-04-13 21:27:06', '2017-04-13 21:27:06'),
       (2, 'Sony', '18022226655', '002', 'sony.com', 'Tokyo', '123-213', 'electric product', 'Japan Bank', 'tokyo Japan bank', '21', 'eiirou', 20000, 80000000, '2017-06-13 21:27:06', '20D', 'normal', null, 21, 0, 'admin', 'admin', '2017-04-13 21:27:06', '2017-04-13 21:27:06');
COMMIT;

-- ----------------------------
--  Table structure for `sample_principle`
-- ----------------------------
DROP TABLE IF EXISTS `sample_principle`;
CREATE TABLE `sample_principle` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `principle_name` varchar(20) NOT NULL,
    `department` varchar(20) DEFAULT NULL,
    `position` varchar(20) DEFAULT NULL,

    `mail` varchar(20) DEFAULT NULL,
    `tel` varchar(20) DEFAULT NULL COMMENT '携帯電話',
    `phone` varchar(20) DEFAULT NULL COMMENT '電話',
    `fax` varchar(20) DEFAULT NULL,
    `address` varchar(20) DEFAULT NULL,
    `postcode` varchar(20) DEFAULT NULL,
    `favorite` varchar(20) DEFAULT NULL,
    `comment` varchar(200) DEFAULT NULL COMMENT '備考',

    `create_user` varchar(20) NOT NULL COMMENT '作成者',
    `update_user` varchar(20) NOT NULL COMMENT '最終更新者',
    `create_time` datetime DEFAULT NULL COMMENT '作成時点',
    `update_time` datetime DEFAULT NULL COMMENT '更新時点',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `sample_principle`
-- ----------------------------
BEGIN;
INSERT INTO `sample_principle`
VALUES (20, 'Cauzinc', 'development', 'se', 'cauzinc@gmail.com',  '1804440134', '7121-1313', '123-11', 'Tokyo', '122-000', 'travel', null, 'admin', 'admin', '2017-04-13 21:27:06', '2017-04-13 21:27:06'),
       (21, 'Jack', 'development', 'se', 'Jack@gmail.com',  '1804440111', '7121-1233', '123-13', 'Tokyo', '122-000', 'travel', null, 'admin', 'admin', '2017-04-13 21:27:06', '2017-04-13 21:27:06');
COMMIT;
