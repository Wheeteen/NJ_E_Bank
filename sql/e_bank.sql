-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2017-05-11 08:22:37
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
  `Cnumber` varchar(16) NOT NULL,
  `Anumber` varchar(14) NOT NULL,
  `status` varchar(18) NOT NULL,
  `balance` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `customer_info`
--

INSERT INTO `customer_info` (`Cid`, `Cname`, `PIN`, `Cnumber`, `Anumber`, `status`, `balance`) VALUES
('123', 'Jessy', '5DEDAF99D256C1CFD97CE2975F4B8624', '7794031046962995', '60792033286835', '0', 51100),
('5689', 'Mery', '502E4A16930E414107EE22B6198C578F', '7690073845204719', '42028231558359', '0', 0),
('725713', 'Nick', 'F401B51D4F52C1D427911F7041392AA2', '6165447250033919', '34255285735054', '0', 0),
('78755', 'Jesscia', '024D7F84FFF11DD7E8D9C510137A2381', '7439246675335062', '47242827361233', '0', 0),
('7889', 'Susan', '8B6DD7DB9AF49E67306FEB59A8BDC52C', '7571675253994344', '24350588439959', '0', 0),
('789', 'Dorry', '68053AF2923E00204C3CA7C6A3150CF7', '2874324596367932', '15226696456237', '0', 0),
('87654', 'Tony', '024D7F84FFF11DD7E8D9C510137A2381', '9225614298670717', '47195296213400', '0', 0),
('8976', 'Tracy', '621461AF90CADFDAF0E8D4CC25129F91', '4064084334830211', '54200992820472', '0', 0);

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
(16, '60792033286835', 51000, 51100, 'CNY', 'Deposit', '2017-05-11 05:48:26');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
