SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `db_dev_infosupport`.`Course_PriorKnowledge`;
DROP TABLE IF EXISTS `db_dev_infosupport`.`Course_TargetUsers`;
DROP TABLE IF EXISTS `db_dev_infosupport`.`Course_Category`;
DROP TABLE IF EXISTS `db_dev_infosupport`.`Lesson`;
DROP TABLE IF EXISTS `db_dev_infosupport`.`Course`;
DROP TABLE IF EXISTS `db_dev_infosupport`.`Category`;
DROP TABLE IF EXISTS `db_dev_infosupport`.`UserType`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `db_dev_infosupport`.`UserType` (
  `ID_UserType` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID_UserType`),
  UNIQUE INDEX `UserType_UNIQUE` (`Name` ASC)
);

CREATE TABLE `db_dev_infosupport`.`Category` (
  `ID_Category` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID_Category`),
  UNIQUE INDEX `Course_Category_UNIQUE` (`Name` ASC)
);

CREATE TABLE `db_dev_infosupport`.`Course`(
  `ID_Course` INT NOT NULL AUTO_INCREMENT,
  `Code` VARCHAR(10) NOT NULL,
  `Name` VARCHAR(255) NOT NULL,
  `Description` TEXT,
  `CourseMaterials` TEXT,
  `KeyWords` TEXT,
  `Duration` INT DEFAULT '1',
  `Cost` DECIMAL,
  PRIMARY KEY (`ID_Course`)
);

CREATE TABLE `db_dev_infosupport`.`Course_Category`(
  `ID_Course` INT NOT NULL,
  `ID_Category` INT NOT NULL,
  CONSTRAINT `fk_Category_Cours`
   FOREIGN KEY (`ID_Course`)
   REFERENCES `db_dev_infosupport`.`Course` (`ID_Course`)
   ON DELETE CASCADE,
  CONSTRAINT `fk_Category_Category`
   FOREIGN KEY (`ID_Category`)
   REFERENCES `db_dev_infosupport`.`Category` (`ID_Category`)
   ON DELETE CASCADE
);

CREATE TABLE `db_dev_infosupport`.`Course_TargetUsers`(
  `ID_Course` INT NOT NULL,
  `ID_UserType` INT NOT NULL,
  CONSTRAINT `fk_TargerUsers_Course`
   FOREIGN KEY (`ID_Course`)
   REFERENCES `db_dev_infosupport`.`Course` (`ID_Course`)
   ON DELETE CASCADE,
  CONSTRAINT `fk_TargerUsers_UserType`
   FOREIGN KEY (`ID_UserType`)
   REFERENCES `db_dev_infosupport`.`UserType` (`ID_UserType`)
   ON DELETE CASCADE
);

CREATE TABLE `db_dev_infosupport`.`Course_PriorKnowledge`(
  `ID_Course` INT NOT NULL,
  `PriorCourse` INT NOT NULL,
  CONSTRAINT `fk_PriorKnowledge_MainCourse`
  FOREIGN KEY (`ID_Course`)
  REFERENCES `db_dev_infosupport`.`Course` (`ID_Course`)
  ON DELETE CASCADE,
CONSTRAINT `fk_PriorKnowledge_PreCourse`
  FOREIGN KEY (`PriorCourse`)
  REFERENCES `db_dev_infosupport`.`Course` (`ID_Course`)
  ON DELETE CASCADE
);


CREATE TABLE `db_dev_infosupport`.`Lesson`(
  `ID_Lesson` INT NOT NULL AUTO_INCREMENT,
  `StartTime` DATE NOT NULL,
  `EndTime` DATE NOT NULL,
  `Location` VARCHAR(30) NOT NULL,
  `ID_Course` INT NOT NULL,
  
  PRIMARY KEY (`ID_Lesson`),
  CONSTRAINT `fk_Lesson_ID_Course`
  FOREIGN KEY (`ID_Course`)
  REFERENCES `db_dev_infosupport`.`Course` (`ID_Course`)
  ON DELETE CASCADE
);