# Host: 127.0.0.1  (Version: 5.5.15)
# Date: 2017-05-09 00:58:47
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "administrator_info"
#

INSERT INTO `administrator_info` VALUES (1,'Nick','725713'),(2,'Jessy','123456');

#
# Structure for table "customer_info"
#

DROP TABLE IF EXISTS `customer_info`;
CREATE TABLE `customer_info` (
  `userid` varchar(11) NOT NULL,
  `username` varchar(11) NOT NULL,
  `PIN` varchar(11) NOT NULL,
  `cardnumber` varchar(16) NOT NULL,
  `accountnumber` varchar(14) NOT NULL,
  `status` varchar(1) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "customer_info"
#

INSERT INTO `customer_info` VALUES ('123456','Jessy','123456','2070246745018007','07400579191564','1'),('1314','test status','1314','1629611232791088','61859859609304','0'),('13343','Bella','13343','6970912975382732','39544456705248','0'),('2323','232','','2229542764669461','26419392536092','0'),('725713','Nick','725713','5842513045193032','60037960012757','1'),('wewe','wewew','wewewe','5936962269645793','13477346766680','0');

#
# Structure for table "record_info"
#

DROP TABLE IF EXISTS `record_info`;
CREATE TABLE `record_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `count` float NOT NULL,
  `type` varchar(11) NOT NULL,
  `tdate` date NOT NULL,
  `uid` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Record_info_customer_info` (`uid`),
  CONSTRAINT `fk_Record_info_customer_info` FOREIGN KEY (`uid`) REFERENCES `customer_info` (`userid`)
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
  `uid` varchar(16) NOT NULL,
  PRIMARY KEY (`account_number`),
  KEY `fk_SavingAccount_info_customer_info` (`uid`),
  CONSTRAINT `fk_SavingAccount_info_customer_info` FOREIGN KEY (`uid`) REFERENCES `customer_info` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "savingaccount_info"
#

