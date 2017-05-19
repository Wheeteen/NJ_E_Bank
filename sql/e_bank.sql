-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-05-19 03:44:20
-- 服务器版本： 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e_bank`
--

-- --------------------------------------------------------

--
-- 表的结构 `administrator_info`
--

CREATE TABLE `administrator_info` (
  `id` int(11) NOT NULL,
  `Aname` varchar(25) NOT NULL,
  `pwd` varchar(40) NOT NULL,
  `Acode` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `administrator_info`
--

INSERT INTO `administrator_info` (`id`, `Aname`, `pwd`, `Acode`) VALUES
(1, 'Nick', 'F401B51D4F52C1D427911F7041392AA2', '1314'),
(2, 'Jessy', 'E10ADC3949BA59ABBE56E057F20F883E', '3456');

-- --------------------------------------------------------

--
-- 表的结构 `customer_info`
--

CREATE TABLE `customer_info` (
  `Cid` varchar(20) NOT NULL,
  `Cname` varchar(30) NOT NULL,
  `PIN` varchar(40) NOT NULL,
  `Cnumber` varchar(20) NOT NULL,
  `Anumber` varchar(18) NOT NULL,
  `status` int(10) NOT NULL,
  `balance` int(20) NOT NULL,
  `Uaccount` varchar(45) DEFAULT NULL,
  `Email` varchar(40) DEFAULT NULL,
  `Upwd` varchar(45) DEFAULT NULL,
  `codeRster` varchar(40) DEFAULT NULL,
  `codePwd` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `customer_info`
--

INSERT INTO `customer_info` (`Cid`, `Cname`, `PIN`, `Cnumber`, `Anumber`, `status`, `balance`, `Uaccount`, `Email`, `Upwd`, `codeRster`, `codePwd`) VALUES
('440981199509111724', 'Me', 'E10ADC3949BA59ABBE56E057F20F883E', '5985604409731612', '24440772460151', 2, 0, 'Jessy', '1970234657@qq.com', 'B005619EC5C843D850A33CC94ACF7B01', '8b311f2b5d8f4e30b2f1a6e684ab0978', NULL);

-- --------------------------------------------------------

--
-- 表的结构 `record_info`
--

CREATE TABLE `record_info` (
  `id` int(11) NOT NULL,
  `Anumber` varchar(14) NOT NULL,
  `amount` int(20) NOT NULL,
  `balance` int(20) NOT NULL,
  `currency` varchar(18) NOT NULL,
  `Otype` varchar(15) NOT NULL,
  `Odate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `record_info`
--

INSERT INTO `record_info` (`id`, `Anumber`, `amount`, `balance`, `currency`, `Otype`, `Odate`) VALUES
(12, '60792033286835', 300, 300, 'CNY', 'Deposit', '2017-05-11 05:39:22'),
(13, '60792033286835', 200, 100, 'CNY', 'Withdraw', '2017-05-11 05:39:57'),
(14, '60792033286835', 50000, 50100, 'CNY', 'Deposit', '2017-05-11 05:41:19'),
(15, '60792033286835', 50000, 100, 'CNY', 'Withdraw', '2017-05-11 05:41:50'),
(16, '60792033286835', 51000, 51100, 'CNY', 'Deposit', '2017-05-11 05:48:26'),
(17, '26842124189509', 600, 600, 'CNY', 'Deposit', '2017-05-18 07:12:03'),
(18, '26842124189509', 600, 1200, 'CNY', 'Deposit', '2017-05-18 07:12:50'),
(19, '26842124189509', 500, 1700, 'CNY', 'Deposit', '2017-05-18 07:13:38'),
(20, '26842124189509', 80000, 81700, 'CNY', 'Deposit', '2017-05-18 07:14:05'),
(21, '26842124189509', 90000, 171700, 'CNY', 'Deposit', '2017-05-18 07:14:57'),
(22, '26842124189509', 90000, 261700, 'CNY', 'Deposit', '2017-05-18 07:15:12'),
(23, '26842124189509', 50312, 211388, 'CNY', 'Withdraw', '2017-05-18 07:15:56'),
(24, '26842124189509', 600, 211988, 'CNY', 'Deposit', '2017-05-18 07:20:38'),
(25, '26842124189509', 300, 211688, 'CNY', 'Withdraw', '2017-05-18 07:23:14'),
(26, '26842124189509', 100000, 111688, 'CNY', 'Withdraw', '2017-05-18 07:24:07'),
(27, '26842124189509', 200, 111488, 'CNY', 'Withdraw', '2017-05-18 07:25:21'),
(28, '26842124189509', 300, 111188, 'CNY', 'Withdraw', '2017-05-18 07:25:43'),
(29, '26842124189509', 50000, 61188, 'CNY', 'Withdraw', '2017-05-18 07:25:57'),
(30, '26842124189509', 300, 60888, 'CNY', 'Withdraw', '2017-05-18 07:27:45'),
(31, '26842124189509', 50000, 10888, 'CNY', 'Withdraw', '2017-05-18 07:27:58'),
(32, '26842124189509', 80000, 90888, 'CNY', 'Deposit', '2017-05-18 07:28:15');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator_info`
--
ALTER TABLE `administrator_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer_info`
--
ALTER TABLE `customer_info`
  ADD PRIMARY KEY (`Cid`);

--
-- Indexes for table `record_info`
--
ALTER TABLE `record_info`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `administrator_info`
--
ALTER TABLE `administrator_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `record_info`
--
ALTER TABLE `record_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
