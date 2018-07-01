/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.5.25a : Database - spring
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `spring`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `cartId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cartName` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`cartId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `cart` */

insert  into `cart`(`cartId`,`cartName`) values 
(25,'Clothings');

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(20) DEFAULT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `fees` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `courseName` (`courseName`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `course` */

insert  into `course`(`id`,`courseName`,`startTime`,`endTime`,`fees`,`active`) values 
(15,'Maths','08:00:00','09:00:00',10000,0),
(16,'Database','09:00:00','10:00:00',6000,0),
(17,'Java','10:00:00','11:00:00',12000,0);

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentName` varchar(100) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `departmentName` (`departmentName`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;

/*Data for the table `department` */

insert  into `department`(`id`,`departmentName`,`active`) values 
(38,'Management',1),
(40,'Development',1),
(49,'Borad of Directors',1);

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `departmentId` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `department_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FKqrxujpcl9722pv7acfm7s8wyq` (`departmentId`),
  KEY `FK14tijxqry9ml17nk86sqfp561` (`department_id`),
  CONSTRAINT `employee_department_departmentId` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`),
  CONSTRAINT `FK14tijxqry9ml17nk86sqfp561` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `FKqrxujpcl9722pv7acfm7s8wyq` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;

/*Data for the table `employee` */

insert  into `employee`(`id`,`name`,`age`,`departmentId`,`active`,`department_id`) values 
(47,'Priyanka Manjrekar',31,38,1,NULL),
(48,'Vivek Yadav',31,40,1,NULL),
(52,'JB',2,49,1,NULL);

/*Table structure for table `employeetest` */

DROP TABLE IF EXISTS `employeetest`;

CREATE TABLE `employeetest` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `employeetest` */

/*Table structure for table `enrolled` */

DROP TABLE IF EXISTS `enrolled`;

CREATE TABLE `enrolled` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK731n66xonrvyledhyje1jh281` (`studentId`),
  KEY `FK7rsotvffqysxa6ojmqf5qlkm1` (`courseId`),
  CONSTRAINT `FK731n66xonrvyledhyje1jh281` FOREIGN KEY (`studentId`) REFERENCES `student` (`ID`),
  CONSTRAINT `FK7rsotvffqysxa6ojmqf5qlkm1` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`),
  CONSTRAINT `fn__enrolled_course_id` FOREIGN KEY (`courseId`) REFERENCES `course` (`id`),
  CONSTRAINT `fn__enrolled_student_id` FOREIGN KEY (`studentId`) REFERENCES `student` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `enrolled` */

insert  into `enrolled`(`id`,`studentId`,`courseId`,`active`) values 
(6,1,16,1);

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values 
(2);

/*Table structure for table `hibernate_sequences` */

DROP TABLE IF EXISTS `hibernate_sequences`;

CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `hibernate_sequences` */

insert  into `hibernate_sequences`(`sequence_name`,`next_val`) values 
('default',53);

/*Table structure for table `items` */

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items` (
  `itemId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `itemName` varchar(30) DEFAULT NULL,
  `cartId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`itemId`),
  KEY `cartId` (`cartId`),
  CONSTRAINT `FKm601o7gxqiiacaphls11pgvcd` FOREIGN KEY (`cartId`) REFERENCES `cart` (`cartId`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`cartId`) REFERENCES `cart` (`cartId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `items` */

insert  into `items`(`itemId`,`itemName`,`cartId`) values 
(26,'pant',25),
(27,'shirt',25);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `message` */

insert  into `message`(`id`,`text`) values 
(1,'Hello World!');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  `age` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`ID`,`NAME`,`active`,`age`) values 
(1,'Priyanka Manjrekar',1,30),
(2,'Vivek Yadav',1,30),
(3,'Harshal Manjrekar',1,25),
(4,'JB',1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
