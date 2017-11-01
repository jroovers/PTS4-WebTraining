-- --------------------------------------------------------
-- Host:                         77.174.23.144
-- Server version:               5.5.54-0+deb8u1 - (Raspbian)
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table db_dev_infosupport.Category
DROP TABLE IF EXISTS `Category`;
CREATE TABLE IF NOT EXISTS `Category` (
  `ID_Category` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_Category`),
  UNIQUE KEY `Course_Category_UNIQUE` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Category: ~0 rows (approximately)
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Course
DROP TABLE IF EXISTS `Course`;
CREATE TABLE IF NOT EXISTS `Course` (
  `ID_Course` int(11) NOT NULL AUTO_INCREMENT,
  `Code` varchar(10) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Description` text,
  `CourseMaterials` text,
  `KeyWords` text,
  `Duration` int(11) DEFAULT '1',
  `Cost` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID_Course`)
) ENGINE=InnoDB AUTO_INCREMENT=446 DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Course: ~8 rows (approximately)
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` (`ID_Course`, `Code`, `Name`, `Description`, `CourseMaterials`, `KeyWords`, `Duration`, `Cost`) VALUES
	(2, '00322', 'Onderzoek', 'Workshop Onderzoek', 'Presentatie', '[]', 2, 90),
	(3, '01849', 'Feedback geven', 'Workshop Feedback geven', 'Presentatie', 'null', 2, 8),
	(34, '35820', 'Leer Photoshop', 'Een cursus die je leert hoe je het best Photoshop kan gebruiken', 'Photoshop, Paint', '[]', 3, 1),
	(78, '34D342', 'Java cursus', 'In deze cursus word Java gegeven.', 'Netbeans', '[java,  netbeans]', 1, 12),
	(115, '24KY362', 'Presenteren', 'Workshop presenteren', 'Powerpoint, Internet', '[]', 0, 0),
	(119, '34235', 'Kyle complimenten geven', 'een Makkelijke cursus voor jong en oud.', 'Geen', '[]', 0, 0),
	(154, '102485', 'Proftaak presentatie', 'Proftaak presenteren', 'Powerpoint', 'java, netbeans,', 1, 20),
	(289, 'UnitTest1', 'name', 'textDescription', 'courseMaterials', 'Jemam,Jepap,', 3, 1);
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Course_Category
DROP TABLE IF EXISTS `Course_Category`;
CREATE TABLE IF NOT EXISTS `Course_Category` (
  `ID_Course` int(11) NOT NULL,
  `ID_Category` int(11) NOT NULL,
  KEY `fk_Category_Cours` (`ID_Course`),
  KEY `fk_Category_Category` (`ID_Category`),
  CONSTRAINT `fk_Category_Cours` FOREIGN KEY (`ID_Course`) REFERENCES `Course` (`ID_Course`) ON DELETE CASCADE,
  CONSTRAINT `fk_Category_Category` FOREIGN KEY (`ID_Category`) REFERENCES `Category` (`ID_Category`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Course_Category: ~0 rows (approximately)
/*!40000 ALTER TABLE `Course_Category` DISABLE KEYS */;
/*!40000 ALTER TABLE `Course_Category` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Course_PriorKnowledge
DROP TABLE IF EXISTS `Course_PriorKnowledge`;
CREATE TABLE IF NOT EXISTS `Course_PriorKnowledge` (
  `ID_Course` int(11) NOT NULL,
  `PriorCourse` int(11) NOT NULL,
  KEY `fk_PriorKnowledge_MainCourse` (`ID_Course`),
  KEY `fk_PriorKnowledge_PreCourse` (`PriorCourse`),
  CONSTRAINT `fk_PriorKnowledge_MainCourse` FOREIGN KEY (`ID_Course`) REFERENCES `Course` (`ID_Course`) ON DELETE CASCADE,
  CONSTRAINT `fk_PriorKnowledge_PreCourse` FOREIGN KEY (`PriorCourse`) REFERENCES `Course` (`ID_Course`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Course_PriorKnowledge: ~0 rows (approximately)
/*!40000 ALTER TABLE `Course_PriorKnowledge` DISABLE KEYS */;
/*!40000 ALTER TABLE `Course_PriorKnowledge` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Course_TargetUsers
DROP TABLE IF EXISTS `Course_TargetUsers`;
CREATE TABLE IF NOT EXISTS `Course_TargetUsers` (
  `ID_Course` int(11) NOT NULL,
  `ID_UserType` int(11) NOT NULL,
  KEY `fk_TargerUsers_Course` (`ID_Course`),
  KEY `fk_TargerUsers_UserType` (`ID_UserType`),
  CONSTRAINT `fk_TargerUsers_Course` FOREIGN KEY (`ID_Course`) REFERENCES `Course` (`ID_Course`) ON DELETE CASCADE,
  CONSTRAINT `fk_TargerUsers_UserType` FOREIGN KEY (`ID_UserType`) REFERENCES `UserType` (`ID_UserType`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Course_TargetUsers: ~0 rows (approximately)
/*!40000 ALTER TABLE `Course_TargetUsers` DISABLE KEYS */;
/*!40000 ALTER TABLE `Course_TargetUsers` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Lesson
DROP TABLE IF EXISTS `Lesson`;
CREATE TABLE IF NOT EXISTS `Lesson` (
  `ID_Lesson` int(11) NOT NULL AUTO_INCREMENT,
  `StartTime` date NOT NULL,
  `EndTime` date NOT NULL,
  `Location` varchar(30) NOT NULL,
  `ID_Course` int(11) NOT NULL,
  PRIMARY KEY (`ID_Lesson`),
  KEY `fk_Lesson_ID_Course` (`ID_Course`),
  CONSTRAINT `fk_Lesson_ID_Course` FOREIGN KEY (`ID_Course`) REFERENCES `Course` (`ID_Course`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=188 DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Lesson: ~159 rows (approximately)
/*!40000 ALTER TABLE `Lesson` DISABLE KEYS */;
INSERT INTO `Lesson` (`ID_Lesson`, `StartTime`, `EndTime`, `Location`, `ID_Course`) VALUES
	(5, '2017-09-20', '2017-09-20', 'Venray', 2),
	(6, '2017-10-02', '2017-10-03', 'utrecht', 3),
	(7, '2017-10-09', '2017-10-10', 'utrecht', 3),
	(8, '2017-10-17', '2017-10-18', 'utrecht', 2),
	(15, '2017-09-27', '2017-09-28', 'Utrecht', 2),
	(17, '2018-01-23', '2018-01-24', 'Veenendaal', 3),
	(29, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(30, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(31, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(32, '2018-01-19', '2018-01-20', 'Veenendaal', 3),
	(33, '2018-01-24', '2018-01-25', 'Veenendaal', 2),
	(35, '2018-01-25', '2018-01-26', 'Veenendaal', 3),
	(36, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(37, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(38, '2018-02-05', '2018-02-06', 'Veenendaal', 2),
	(39, '2018-02-13', '2018-02-14', 'Utrecht', 3),
	(43, '2017-09-19', '2017-09-19', 'Utrecht', 78),
	(44, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(45, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(46, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(48, '2018-03-13', '2018-03-14', 'Veenendaal', 2),
	(49, '2018-03-15', '2018-03-15', 'Veenendaal', 78),
	(50, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(51, '2018-04-13', '2018-04-14', 'Eindhoven', 2),
	(52, '2018-05-14', '2018-05-16', 'Veenendaal', 34),
	(53, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(54, '2018-08-22', '2018-08-23', 'Veenendaal', 3),
	(55, '2020-06-30', '2020-07-01', 'Veenendaal', 2),
	(56, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(57, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(58, '2017-09-27', '2017-09-27', 'eindhoven', 2),
	(60, '2017-09-28', '2017-09-28', 'eindhoven', 2),
	(61, '2017-10-03', '2017-10-03', 'eindhoven', 2),
	(62, '2017-10-03', '2017-10-03', 'eindhoven', 2),
	(63, '2017-10-04', '2017-10-04', 'eindhoven', 2),
	(64, '2017-10-04', '2017-10-04', 'eindhoven', 2),
	(65, '2017-10-18', '2017-10-18', 'Eindhoven', 115),
	(66, '2017-10-04', '2017-10-04', 'eindhoven', 2),
	(67, '2017-10-05', '2017-10-05', 'Fontys', 154),
	(68, '2017-10-04', '2017-10-04', 'eindhoven', 2),
	(69, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(70, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(71, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(72, '2017-09-18', '2017-09-18', 'Utrecht', 154),
	(73, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(74, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(75, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(76, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(77, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(78, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(79, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(80, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(81, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(82, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(83, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(84, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(85, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(86, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(87, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(88, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(89, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(90, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(91, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(92, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(93, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(94, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(95, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(96, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(97, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(98, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(99, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(100, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(101, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(102, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(103, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(104, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(105, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(106, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(107, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(108, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(109, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(110, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(111, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(112, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(113, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(114, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(115, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(116, '2017-10-10', '2017-10-10', 'eindhoven', 2),
	(117, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(118, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(119, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(120, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(121, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(122, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(123, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(124, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(125, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(126, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(127, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(128, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(129, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(130, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(131, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(132, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(133, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(134, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(135, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(136, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(137, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(138, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(139, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(140, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(141, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(142, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(143, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(144, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(145, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(146, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(147, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(148, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(149, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(150, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(151, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(152, '2017-10-11', '2017-10-11', 'eindhoven', 2),
	(153, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(154, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(155, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(156, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(157, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(158, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(159, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(160, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(161, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(162, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(163, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(164, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(165, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(166, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(167, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(168, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(169, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(170, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(171, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(172, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(173, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(174, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(175, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(176, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(177, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(178, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(179, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(180, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(181, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(182, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(183, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(184, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(185, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(186, '2017-10-24', '2017-10-24', 'eindhoven', 2),
	(187, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(188, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(189, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(190, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(191, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(192, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(193, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(194, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(195, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(196, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(197, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(198, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(199, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(200, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(201, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(202, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(203, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(204, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(205, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(206, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(207, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(208, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(209, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(210, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(211, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(212, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(213, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(214, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(215, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(216, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(217, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(218, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(219, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(220, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(221, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(222, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(223, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(224, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(225, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(226, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(227, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(228, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(229, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(230, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(231, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(232, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(233, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(234, '2017-10-25', '2017-10-25', 'eindhoven', 2),
	(235, '2017-10-25', '2017-10-25', 'eindhoven', 2);
/*!40000 ALTER TABLE `Lesson` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Lesson_Registration
DROP TABLE IF EXISTS `Lesson_Registration`;
CREATE TABLE IF NOT EXISTS `Lesson_Registration` (
  `ID_Lesson` int(11) NOT NULL,
  `ID_User` int(11) NOT NULL,
  KEY `fk_Registration_Lesson` (`ID_Lesson`),
  KEY `fk_Registration_Registration` (`ID_User`),
  CONSTRAINT `fk_Registration_Lesson` FOREIGN KEY (`ID_Lesson`) REFERENCES `Lesson` (`ID_Lesson`) ON DELETE CASCADE,
  CONSTRAINT `fk_Registration_Registration` FOREIGN KEY (`ID_User`) REFERENCES `Registration` (`ID_User`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Lesson_Registration: ~20 rows (approximately)
/*!40000 ALTER TABLE `Lesson_Registration` DISABLE KEYS */;
INSERT INTO `Lesson_Registration` (`ID_Lesson`, `ID_User`) VALUES
	(5, 1),
	(5, 1),
	(30, 1),
	(43, 1),
	(7, 1),
	(67, 1),
	(30, 1),
	(6, 1),
	(29, 1),
	(39, 1),
	(17, 1),
	(7, 1),
	(65, 1),
	(15, 1),
	(6, 1),
	(5, 1),
	(52, 1),
	(67, 1),
	(72, 1),
	(43, 1);
/*!40000 ALTER TABLE `Lesson_Registration` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Location
DROP TABLE IF EXISTS `Location`;
CREATE TABLE IF NOT EXISTS `Location` (
  `ID_Location` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_Location`),
  UNIQUE KEY `Location_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Location: ~2 rows (approximately)
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` (`ID_Location`, `Name`) VALUES
	(1, 'Utrecht'),
	(2, 'Veenendaal');
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Registration
DROP TABLE IF EXISTS `Registration`;
CREATE TABLE IF NOT EXISTS `Registration` (
  `ID_User` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `PhoneNumber` int(11) NOT NULL,
  PRIMARY KEY (`ID_User`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.Registration: ~1 rows (approximately)
/*!40000 ALTER TABLE `Registration` DISABLE KEYS */;
INSERT INTO `Registration` (`ID_User`, `FirstName`, `LastName`, `Email`, `PhoneNumber`) VALUES
	(1, 'Frank', 'Frenken', 'Frank@Frenken.fankie', 623946734);
/*!40000 ALTER TABLE `Registration` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.UserType
DROP TABLE IF EXISTS `UserType`;
CREATE TABLE IF NOT EXISTS `UserType` (
  `ID_UserType` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_UserType`),
  UNIQUE KEY `UserType_UNIQUE` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `UserType` (`ID_UserType`, `Name`) VALUES
	(1, 'Guest'),
	(2, 'Admin'),
	(3, 'User');

-- Dumping data for table db_dev_infosupport.UserType: ~0 rows (approximately)
/*!40000 ALTER TABLE `UserType` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserType` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.User
DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `ID_User` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Surname` varchar(255) NOT NULL,
  `Username` varchar(255) UNIQUE NULL,
  `Password` varchar(255) NULL,
  `PhoneNr` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `ID_UserType` INT(11) NOT NULL,
  PRIMARY KEY (`ID_User`),
  KEY `fk_User_Type` (`ID_UserType`),
  CONSTRAINT `fk_User_Type` FOREIGN KEY (`ID_UserType`) REFERENCES `UserType` (`ID_UserType`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table db_dev_infosupport.User: ~2 rows (approximately)
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` (`ID_User`, `Name`, `Surname`, `Username`, `Password`,`PhoneNr`,`Email`,`ID_UserType`) VALUES
	(1, 'Frank', 'franken', 'Frankster', 'frankisthebest', '001234', 'Frankster@TheG.com', 2),
	(2, 'Bert', 'bertus','Bertster','bertisthebest','004321','BertusThebertustest@banana.com', 1),
	(3, 'Kyle', 'bendover','Bender','BendingLoverxx','09000900','Kylethe****Lover@gmail.com', 3);
/*!40000 ALTER TABLE `User` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
