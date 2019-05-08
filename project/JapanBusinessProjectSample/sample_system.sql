

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
    `business_fax` varchar(20) NOT NULL,
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
    `payment_sight` int(10) DEFAULT NULL,
    `account_kind` varchar(20) DEFAULT NULL,
    `comment` varchar(200) DEFAULT NULL COMMENT '追加情報',

    `principal_id` int(11) DEFAULT NULL COMMENT '担当者',

    `create_user` varchar(20) DEFAULT NOT NULL COMMENT '作成者',
    `update_user` varchar(20) DEFAULT NOT NULL COMMENT '最終更新者',
    `create_time` datetime DEFAULT NULL COMMENT '作成時点',
    `update_time` datetime DEFAULT NULL COMMENT '更新時点',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- ----------------------------
--  Table structure for `sample_principle`
-- ----------------------------
DROP TABLE IF EXISTS `sample_principle`;
CREATE TABLE `sample_business` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `principle_name` varchar(20) NOT NULL,
    `department` varchar(20) DEFAULT NULL,
    `position` varchar(20) DEFAULT NULL,
    `business_id` int(11) NOT NULL,

    `mail` varchar(20) DEFAULT NULL,
    `tel` varchar(20) DEFAULT NULL COMMENT '携帯電話',
    `phone` varchar(20) DEFAULT NULL COMMENT '電話',
    `fax` varchar(20) DEFAULT NULL,,
    `address` varchar(20) DEFAULT NULL,
    `postcode` varchar(20) DEFAULT NULL,
    `favorite` varchar(20) DEFAULT NULL,
    `comment` varchar(200) DEFAULT NULL COMMENT '備考',

    `create_user` varchar(20) DEFAULT NOT NULL COMMENT '作成者',
    `update_user` varchar(20) DEFAULT NOT NULL COMMENT '最終更新者',
    `create_time` datetime DEFAULT NULL COMMENT '作成時点',
    `update_time` datetime DEFAULT NULL COMMENT '更新時点',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


