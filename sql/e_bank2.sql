# Host: 127.0.0.1  (Version: 5.5.15)
# Date: 2017-05-08 13:37:35
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES gb2312 */;

#
# Structure for table "administrator_info"
#

DROP TABLE IF EXISTS `administrator_info`;
CREATE TABLE `administrator_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(11) NOT NULL,
  `password` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "administrator_info"
#

INSERT INTO `administrator_info` VALUES (1,'Nick','725713'),(2,'Jessy','123456');

#
# Structure for table "customer_info"
#

DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `id` int(11) NOT NULL,
  `card_number` varchar(16) NOT NULL,
  `name` varchar(11) NOT NULL,
  `password` varchar(11) NOT NULL,
  `authentication_code` varchar(11) NOT NULL,
  PRIMARY KEY (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "customer_info"
#


#
# Structure for table "record_info"
#

DROP TABLE IF EXISTS `record_info`;
CREATE TABLE `record_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` float NOT NULL,
  `type` varchar(11) NOT NULL,
  `tdate` date NOT NULL,
  `cid` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Record_info_customer_info` (`cid`),
  CONSTRAINT `fk_Record_info_customer_info` FOREIGN KEY (`cid`) REFERENCES `customer_info` (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "record_info"
#


#
# Structure for table "savingaccount_info"
#

DROP TABLE IF EXISTS `savingaccount_info`;
CREATE TABLE `savingaccount_info` (
  `id` int(11) NOT NULL,
  `account_number` varchar(14) NOT NULL,
  `account_PIN` varchar(11) NOT NULL,
  `balance` float NOT NULL,
  `status` char(1) NOT NULL,
  `cid` varchar(16) NOT NULL,
  PRIMARY KEY (`account_number`),
  KEY `fk_SavingAccount_info_customer_info` (`cid`),
  CONSTRAINT `fk_SavingAccount_info_customer_info` FOREIGN KEY (`cid`) REFERENCES `customer_info` (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "savingaccount_info"
#

