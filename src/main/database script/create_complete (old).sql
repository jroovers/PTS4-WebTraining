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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
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

-- Data exporting was unselected.
-- Dumping structure for table db_dev_infosupport.Location
DROP TABLE IF EXISTS `Location`;
CREATE TABLE IF NOT EXISTS `Location` (
  `ID_Location` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_Location`),
  UNIQUE KEY `Location_UNIQUE` (`Name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
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

-- Data exporting was unselected.
-- Dumping structure for table db_dev_infosupport.User
DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `ID_User` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_User`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
-- Dumping structure for table db_dev_infosupport.UserType
DROP TABLE IF EXISTS `UserType`;
CREATE TABLE IF NOT EXISTS `UserType` (
  `ID_UserType` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_UserType`),
  UNIQUE KEY `UserType_UNIQUE` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
