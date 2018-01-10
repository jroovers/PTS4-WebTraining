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
    `ID_Category` INT(11) NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID_Category`),
    UNIQUE KEY `Course_Category_UNIQUE` (`Name`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Category: ~0 rows (approximately)
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;

INSERT INTO `Category` (Name) VALUES
	('UIX'),
    ('Software Design'),
    ('Java'),
    ('C#'),
	('Webapplicaties'),
    ('Applicatiebeheer'),
    ('BI'),
    ('Deployment'),
    ('Databases'),
	('Office'),
	('SQL');

-- Dumping structure for table db_dev_infosupport.Category
DROP TABLE IF EXISTS `UserGroup`;
CREATE TABLE IF NOT EXISTS `UserGroup` (
    `ID_UserGroup` INT(11) NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID_UserGroup`),
    UNIQUE KEY `UserGroup_UNIQUE` (`Name`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Category: ~0 rows (approximately)
/*!40000 ALTER TABLE `Category` DISABLE KEYS */;
/*!40000 ALTER TABLE `Category` ENABLE KEYS */;

INSERT INTO `UserGroup` (Name) VALUES
    ('MDWK Instroom 2017'),
    ('MDWK Basiskennis'),
    ('MDWK Nieuwe Medewerkers'),
    ('MDWK Senior Medewerkers'),
    ('MDWK Unit Managers'),
    ('MDWK Raad v Bestuur');

-- Dumping structure for table db_dev_infosupport.Course
DROP TABLE IF EXISTS `Course`;
CREATE TABLE IF NOT EXISTS `Course` (
    `ID_Course` INT(11) NOT NULL AUTO_INCREMENT,
    `Code` VARCHAR(10) NOT NULL,
    `Name` VARCHAR(255) NOT NULL,
    `Description` TEXT,
    `CourseMaterials` TEXT,
    `KeyWords` TEXT,
    `Duration` INT(11) DEFAULT '1',
    `Cost` DECIMAL(10 , 0 ) DEFAULT NULL,
    `Supplier` VARCHAR(255) DEFAULT 'InfoSupport',
    PRIMARY KEY (`ID_Course`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Course: ~8 rows (approximately)
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` (`ID_Course`, `Code`, `Name`, `Description`, `CourseMaterials`, `KeyWords`, `Duration`, `Cost`, `Supplier`) VALUES
	(1, '00322', 'Onderzoek', 'Workshop Onderzoek', 'Presentatie', '[]', 2, 90, 'InfoSupport'),
	(2, '01849', 'Feedback geven', 'Workshop Feedback geven', 'Presentatie', 'null', 2, 8, 'InfoSupport'),
	(3, '35820', 'Leer Photoshop', 'Een cursus die je leert hoe je het best Photoshop kan gebruiken', 'Photoshop, Paint', '[]', 3, 1, 'InfoSupport'),
	(4, '34D342', 'Java cursus', 'In deze cursus word Java gegeven.', 'Netbeans', '[java,  netbeans]', 1, 12, 'InfoSupport'),
	(5, '24KY362', 'Presenteren', 'Workshop presenteren', 'Powerpoint, Internet', '[]', 0, 0, 'InfoSupport'),
	(6, '34235', 'Kyle complimenten geven', 'een Makkelijke cursus voor jong en oud.', 'Geen', '[]', 0, 0, 'ExterneLeverancierNaam'),
	(7, '102485', 'Proftaak presentatie', 'Proftaak presenteren', 'Powerpoint', 'java, netbeans,', 1, 20, 'ExterneLeverancierNaam'),
	(8, 'UnitTest1', 'name', 'textDescription', 'courseMaterials', 'Jemam,Jepap,', 3, 1, 'ExterneLeverancierNaam');
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Course_Category
DROP TABLE IF EXISTS `Course_Category`;
CREATE TABLE IF NOT EXISTS `Course_Category` (
    `ID_Course` INT(11) NOT NULL,
    `ID_Category` INT(11) NOT NULL,
    PRIMARY KEY (`ID_Course` , `ID_Category`),
    KEY `fk_Category_Course` (`ID_Course`),
    KEY `fk_Category_Category` (`ID_Category`),
    CONSTRAINT `fk_Category_Course` FOREIGN KEY (`ID_Course`)
        REFERENCES `Course` (`ID_Course`)
        ON DELETE CASCADE,
    CONSTRAINT `fk_Category_Category` FOREIGN KEY (`ID_Category`)
        REFERENCES `Category` (`ID_Category`)
        ON DELETE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Course_Category: ~0 rows (approximately)
/*!40000 ALTER TABLE `Course_Category` DISABLE KEYS */;
/*!40000 ALTER TABLE `Course_Category` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Course_PriorKnowledge
DROP TABLE IF EXISTS `Course_PriorKnowledge`;
CREATE TABLE IF NOT EXISTS `Course_PriorKnowledge` (
    `ID_Course` INT(11) NOT NULL,
    `PriorCourse` INT(11) NOT NULL,
    KEY `fk_PriorKnowledge_MainCourse` (`ID_Course`),
    KEY `fk_PriorKnowledge_PreCourse` (`PriorCourse`),
    CONSTRAINT `fk_PriorKnowledge_MainCourse` FOREIGN KEY (`ID_Course`)
        REFERENCES `Course` (`ID_Course`)
        ON DELETE CASCADE,
    CONSTRAINT `fk_PriorKnowledge_PreCourse` FOREIGN KEY (`PriorCourse`)
        REFERENCES `Course` (`ID_Course`)
        ON DELETE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Course_PriorKnowledge: ~0 rows (approximately)
/*!40000 ALTER TABLE `Course_PriorKnowledge` DISABLE KEYS */;
/*!40000 ALTER TABLE `Course_PriorKnowledge` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Course_UserGroup
DROP TABLE IF EXISTS `Course_UserGroup`;
CREATE TABLE IF NOT EXISTS `Course_UserGroup` (
    `ID_Course` INT(11) NOT NULL,
    `ID_UserGroup` INT(11) NOT NULL,
    PRIMARY KEY (`ID_Course` , `ID_UserGroup`),
    KEY `fk_TargerUsers_Course` (`ID_Course`),
    KEY `fk_TargerUsers_UserGroup` (`ID_UserGroup`),
    CONSTRAINT `fk_TargetUsers_Course` FOREIGN KEY (`ID_Course`)
        REFERENCES `Course` (`ID_Course`)
        ON DELETE CASCADE,
    CONSTRAINT `fk_TargerUsers_UserGroup` FOREIGN KEY (`ID_UserGroup`)
        REFERENCES `UserGroup` (`ID_UserGroup`)
        ON DELETE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Course_TargetUsers: ~0 rows (approximately)
/*!40000 ALTER TABLE `Course_UserGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `Course_UserGroup` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Lesson
DROP TABLE IF EXISTS `Lesson`;
CREATE TABLE IF NOT EXISTS `Lesson` (
    `ID_Lesson` INT(11) NOT NULL AUTO_INCREMENT,
    `StartTime` DATE NOT NULL,
    `EndTime` DATE NOT NULL,
    `Location` VARCHAR(30) NOT NULL,
    `ID_Course` INT(11) NOT NULL,
    `Teacher_ID_User` INT(11),
    PRIMARY KEY (`ID_Lesson`),
    KEY `fk_Lesson_ID_Course` (`ID_Course`),
    KEY `fk_Lesson_Teacher_ID_User` (`Teacher_ID_User`),
    CONSTRAINT `fk_Lesson_ID_Course` FOREIGN KEY (`ID_Course`)
        REFERENCES `Course` (`ID_Course`)
        ON DELETE CASCADE,
    CONSTRAINT `fk_Lesson_Teacher_ID_User` FOREIGN KEY (`Teacher_ID_User`)
        REFERENCES `User` (`ID_User`)
        ON DELETE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Lesson: ~159 rows (approximately)
/*!40000 ALTER TABLE `Lesson` DISABLE KEYS */;
INSERT INTO `Lesson` (`ID_Lesson`, `StartTime`, `EndTime`, `Location`, `ID_Course`,`Teacher_ID_User`) VALUES
	(1, '2017-09-20', '2017-09-20', 'Venray', 1,1),
	(2, '2017-10-02', '2017-10-03', 'utrecht', 1,5),
	(3, '2017-10-09', '2017-10-10', 'utrecht', 1,1),
	(4, '2017-10-17', '2017-10-18', 'utrecht', 2,5),
	(5, '2017-09-27', '2017-09-28', 'Utrecht', 2,1),
	(6, '2018-01-23', '2018-01-24', 'Veenendaal', 3,5),
	(7, '2018-01-23', '2018-01-24', 'Veenendaal', 3,1),
	(8, '2018-01-23', '2018-01-24', 'Veenendaal', 4,5),
	(9, '2018-01-23', '2018-01-24', 'Veenendaal', 5,1),
	(10, '2018-01-23', '2018-01-24', 'Veenendaal', 6,5),
	(11, '2018-01-23', '2018-01-24', 'Veenendaal', 7,1),
	(12, '2017-10-25', '2017-10-25', 'eindhoven', 8,5);
/*!40000 ALTER TABLE `Lesson` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Lesson_Registration
DROP TABLE IF EXISTS `Lesson_Registration`;
CREATE TABLE IF NOT EXISTS `Lesson_Registration` (
    `ID_Lesson` INT(11) NOT NULL,
    `ID_User` INT(11) NOT NULL,
    KEY `fk_Registration_Lesson` (`ID_Lesson`),
    KEY `fk_Registration_Registration` (`ID_User`),
    CONSTRAINT `fk_Registration_Lesson` FOREIGN KEY (`ID_Lesson`)
        REFERENCES `Lesson` (`ID_Lesson`)
        ON DELETE CASCADE,
    CONSTRAINT `fk_Registration_Registration` FOREIGN KEY (`ID_User`)
        REFERENCES `Registration` (`ID_User`)
        ON DELETE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Lesson_Registration: ~20 rows (approximately)
/*!40000 ALTER TABLE `Lesson_Registration` DISABLE KEYS */;
INSERT INTO `Lesson_Registration` (`ID_Lesson`, `ID_User`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(2, 1),
	(2, 1),
	(3, 1),
	(3, 1),
	(4, 1),
	(4, 1),
	(5, 1),
	(5, 1),
	(6, 1),
	(7, 1),
	(8, 1),
	(9, 1),
	(10, 1),
	(11, 1),
	(12, 1);
/*!40000 ALTER TABLE `Lesson_Registration` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Enrollment
DROP TABLE IF EXISTS `Enrollment`;
CREATE TABLE IF NOT EXISTS `Enrollment` (
    `ID_Enrollment` INT(11) NOT NULL AUTO_INCREMENT,
    `ID_Lesson` INT(11) NOT NULL,
    `ID_Student` INT(11) NOT NULL,
    `SignupTime` DATETIME NOT NULL,
    `Status` INT(1) NOT NULL DEFAULT 0,
    `ID_Manager` INT(11),
    `ApprovalTime` DATETIME,
    `Comment` TEXT,
    PRIMARY KEY (`ID_Enrollment`),
    KEY `fk_Enrollment_Lesson` (`ID_Lesson`),
    KEY `fk_Enrollment_Student` (`ID_Student`),
    KEY `fk_Enrollment_Manager` (`ID_Manager`),
    CONSTRAINT `fk_Enrollment_Lesson` FOREIGN KEY (`ID_Lesson`)
        REFERENCES `Lesson` (`ID_Lesson`)
        ON DELETE CASCADE,
    CONSTRAINT `fk_Enrollment_Student` FOREIGN KEY (`ID_Student`)
        REFERENCES `User` (`ID_User`)
        ON DELETE CASCADE,
    CONSTRAINT `fk_Enrollment_Manager` FOREIGN KEY (`ID_Manager`)
        REFERENCES `User` (`ID_User`)
        ON DELETE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Lesson_Registration: ~20 rows (approximately)
/*!40000 ALTER TABLE `Enrollment` DISABLE KEYS */;
INSERT INTO `Enrollment` (`ID_Lesson`, `ID_Student`, `SignupTime`) VALUES
	(1, 1, NOW()),
	(1, 2, NOW()),
	(1, 3, NOW()),
	(2, 1, NOW()),
	(2, 1, NOW()),
	(3, 1, NOW()),
	(3, 1, NOW()),
	(4, 1, NOW()),
	(4, 1, NOW()),
	(5, 1, NOW()),
	(5, 1, NOW()),
	(6, 1, NOW()),
	(7, 1, NOW()),
	(8, 1, NOW()),
	(9, 1, NOW()),
	(10, 1, NOW()),
	(11, 1, NOW()),
	(12, 1, NOW());
/*!40000 ALTER TABLE `Enrollment` ENABLE KEYS */;


-- Dumping structure for table db_dev_infosupport.Location
DROP TABLE IF EXISTS `Location`;
CREATE TABLE IF NOT EXISTS `Location` (
    `ID_Location` INT(11) NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID_Location`),
    UNIQUE KEY `Location_UNIQUE` (`Name`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Location: ~2 rows (approximately)
/*!40000 ALTER TABLE `Location` DISABLE KEYS */;
INSERT INTO `Location` (`ID_Location`, `Name`) VALUES
	(1, 'Utrecht'),
	(2, 'Veenendaal');
/*!40000 ALTER TABLE `Location` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.Registration
DROP TABLE IF EXISTS `Registration`;
CREATE TABLE IF NOT EXISTS `Registration` (
    `ID_User` INT(11) NOT NULL AUTO_INCREMENT,
    `FirstName` VARCHAR(255) NOT NULL,
    `LastName` VARCHAR(255) NOT NULL,
    `Email` VARCHAR(30) NOT NULL,
    `PhoneNumber` INT(11) NOT NULL,
    PRIMARY KEY (`ID_User`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.Registration: ~1 rows (approximately)
/*!40000 ALTER TABLE `Registration` DISABLE KEYS */;
INSERT INTO `Registration` (`ID_User`, `FirstName`, `LastName`, `Email`, `PhoneNumber`) VALUES
	(1, 'Frank', 'Frenken', 'Frank@Frenken.fankie', 623946734);
/*!40000 ALTER TABLE `Registration` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.UserType
DROP TABLE IF EXISTS `UserType`;
CREATE TABLE IF NOT EXISTS `UserType` (
    `ID_UserType` INT(11) NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID_UserType`),
    UNIQUE KEY `UserType_UNIQUE` (`Name`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.UserType: ~0 rows (approximately)
/*!40000 ALTER TABLE `UserType` DISABLE KEYS */;
INSERT INTO `UserType` (`ID_UserType`, `Name`) VALUES
	(1, 'Medewerker Info Support'),
	(2, 'Docent Info Support'),
	(3, 'Business Unit Manager'),
    (4, 'Medewerker Kenniscentrum');
/*!40000 ALTER TABLE `UserType` ENABLE KEYS */;

-- Dumping structure for table db_dev_infosupport.User
DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
    `ID_User` INT(11) NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(255) NOT NULL,
    `Surname` VARCHAR(255) NOT NULL,
    `Username` VARCHAR(255) UNIQUE NULL,
    `Password` VARCHAR(255) NULL,
    `PhoneNr` VARCHAR(255) NOT NULL,
    `Email` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`ID_User`)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

-- Dumping data for table db_dev_infosupport.User: ~2 rows (approximately)
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` (`ID_User`, `Name`, `Surname`, `Username`, `Password`,`PhoneNr`,`Email`) VALUES
	(1, 'Frank', 'franken', 'Frankster', 'frankisthebest', '001234', 'Frankster@TheG.com'),
	(2, 'Bert', 'bertus','Bertster','bertisthebest','004321','BertusThebertustest@banana.com'),
	(3, 'Kyle', 'Raaij','Bender','Bender','09000900','KyleVanRaaij@gmail.com'),
	(4, 'Bartel-Jaap', 'van Rundfunk','BJ','barteljaap','004312321','Barteljaap@msn.com'),
	(5, 'admin', 'admin','admin','admin','09000900','admin@gmail.com');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;

DROP TABLE IF EXISTS `User_UserType`;
CREATE TABLE IF NOT EXISTS `User_UserType` (
    `ID_User` INT(11) NOT NULL,
    `ID_UserType` INT(11) NOT NULL,
    PRIMARY KEY (`ID_User` , `ID_UserType`),
    KEY `fk_Roles_User` (`ID_User`),
    KEY `fk_Roles_Authorization` (`ID_UserType`),
    CONSTRAINT `fk_Roles_User` FOREIGN KEY (`ID_User`)
        REFERENCES `User` (`ID_User`)
        ON DELETE CASCADE,
    CONSTRAINT `fk_Roles_Authorization` FOREIGN KEY (`ID_UserType`)
        REFERENCES `UserType` (`ID_UserType`)
        ON DELETE CASCADE
)  ENGINE=INNODB DEFAULT CHARSET=UTF8;

/*!40000 ALTER TABLE `User_UserType` DISABLE KEYS */;
INSERT INTO `User_UserType` (`ID_User`, `ID_UserType`) VALUES
	(1, 1),
	(1, 2),
    (2, 1),
    (3, 1),
    (3, 3),
    (4, 1),
    (5, 1),
	(5, 2),
	(5, 3),
    (5, 4);
/*!40000 ALTER TABLE `User_UserType` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
