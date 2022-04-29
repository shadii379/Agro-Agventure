/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.5.20-log : Database - agro agventure
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`agro agventure` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `agro agventure`;

/*Table structure for table `agricultural_equipment` */

DROP TABLE IF EXISTS `agricultural_equipment`;

CREATE TABLE `agricultural_equipment` (
  `Equipment_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(25) DEFAULT NULL,
  `Image` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Equipment_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `agricultural_equipment` */

insert  into `agricultural_equipment`(`Equipment_ID`,`Name`,`Image`,`Description`,`Price`) values 
(2,'asdfghjk','<FileStorage: \'\' (\'application/octet-stream\')>','dcfhjkl.','22'),
(3,'xvnm','<FileStorage: \'\' (\'application/octet-stream\')>','','45100'),
(4,'wertyu','<FileStorage: \'LEVEL0.png\' (\'image/png\')>','qasdfghjklkjhgfd','563'),
(5,',mnbvcx','<FileStorage: \'LEVEL0.png\' (\'image/png\')>','dfgjkllkhg','452');

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `Bank_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Account_No` varchar(30) DEFAULT NULL,
  `IFSC` varchar(30) DEFAULT NULL,
  `Key` int(11) DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  `Faremer_ID` int(11) DEFAULT NULL,
  `Shop_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Bank_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `Bill_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Total_Price` int(11) DEFAULT NULL,
  `Status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Bill_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

/*Table structure for table `bill_equipment` */

DROP TABLE IF EXISTS `bill_equipment`;

CREATE TABLE `bill_equipment` (
  `Bill_Equipment_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Farmer_ID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Total_Price` int(11) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Bill_Equipment_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bill_equipment` */

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `Complaint_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Product_ID` int(11) DEFAULT NULL,
  `Shop_ID` int(11) DEFAULT NULL,
  `Complaint` varchar(50) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `REplay` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Complaint_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

/*Table structure for table `farmer` */

DROP TABLE IF EXISTS `farmer`;

CREATE TABLE `farmer` (
  `Farmer_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Login_ID` int(11) DEFAULT NULL,
  `First_Name` varchar(20) DEFAULT NULL,
  `Last_Name` varchar(20) DEFAULT NULL,
  `Place` varchar(30) DEFAULT NULL,
  `Post` varchar(35) DEFAULT NULL,
  `Pin` int(11) DEFAULT NULL,
  `District` varchar(35) DEFAULT NULL,
  `Phone` int(11) DEFAULT NULL,
  PRIMARY KEY (`Farmer_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `farmer` */

insert  into `farmer`(`Farmer_ID`,`Login_ID`,`First_Name`,`Last_Name`,`Place`,`Post`,`Pin`,`District`,`Phone`) values 
(1,11,'Amal','Manooj','Bathery','Kolloor',673512,'Wayanad',856214526),
(2,12,'jaseem','muhammed','kalpette','kalpette',325698,'wayanad',2147483647);

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `Feedback_ID` int(11) NOT NULL AUTO_INCREMENT,
  `From_ID` int(11) DEFAULT NULL,
  `To_ID` int(11) DEFAULT NULL,
  `Feedback` varchar(300) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`Feedback_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`Feedback_ID`,`From_ID`,`To_ID`,`Feedback`,`Date`) values 
(1,NULL,0,NULL,NULL),
(2,NULL,0,NULL,NULL);

/*Table structure for table `krishibhavan` */

DROP TABLE IF EXISTS `krishibhavan`;

CREATE TABLE `krishibhavan` (
  `Krishibhavan_ID` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) DEFAULT NULL,
  `PLace` varchar(30) DEFAULT NULL,
  `Post` varchar(30) DEFAULT NULL,
  `Pin` varchar(30) DEFAULT NULL,
  `District` varchar(30) DEFAULT NULL,
  `Phone` int(11) DEFAULT NULL,
  PRIMARY KEY (`Krishibhavan_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `krishibhavan` */

insert  into `krishibhavan`(`Krishibhavan_ID`,`lid`,`PLace`,`Post`,`Pin`,`District`,`Phone`) values 
(2,6,'Bathery','bathery','670366','Wayand',2147483647),
(3,7,'Mananthavady','Mananthavady','670645','Wayanad',453270585),
(4,10,'Mananthavady','Mananthavady','0213524','Wayanad',2147483647);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_ID`),
  UNIQUE KEY `User_Name` (`User_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_ID`,`User_Name`,`Password`,`Type`) values 
(4,'kb1','123','krishibhavan'),
(6,'kb2','234','krishibhavan'),
(7,'kb3','234','krishibhavan'),
(9,'admin','1234','admin'),
(10,'kb4','1254','krishibhavan'),
(11,'fm1','123','farmer'),
(12,'fm2','123','rejected');

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `Notification_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Notification` varchar(30) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`Notification_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `notification` */

insert  into `notification`(`Notification_ID`,`Notification`,`Date`) values 
(1,'<built-in function id>','2021-12-29'),
(2,'<built-in function id>','2021-12-29'),
(3,'fyrfkuyty','2021-12-29'),
(4,'asdfghjkl','2021-12-29'),
(5,'asdfghjkl','2021-12-29'),
(6,'asdfghjkl','2021-12-29'),
(7,'asdfghjkl','2021-12-29'),
(8,'asdfghjkl','2021-12-29');

/*Table structure for table `order_equipment` */

DROP TABLE IF EXISTS `order_equipment`;

CREATE TABLE `order_equipment` (
  `Order_Equipment_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Equipment_ID` int(11) DEFAULT NULL,
  `Farmer_ID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Bill_Equipment_ID` int(11) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Order_Equipment_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order_equipment` */

/*Table structure for table `order_product` */

DROP TABLE IF EXISTS `order_product`;

CREATE TABLE `order_product` (
  `Order_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Product_ID` int(11) DEFAULT NULL,
  `Shop_ID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Quality` varchar(20) DEFAULT NULL,
  `Bill_ID` varchar(20) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Order_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `order_product` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `Product_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Farmer_ID` int(11) DEFAULT NULL,
  `Product_Name` varchar(30) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Image` varchar(50) DEFAULT NULL,
  `Quantity` varchar(25) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Product_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `product` */

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `Shop_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Login_ID` int(11) DEFAULT NULL,
  `Shop_Name` varchar(30) DEFAULT NULL,
  `PLace` varchar(20) DEFAULT NULL,
  `Post` varchar(20) DEFAULT NULL,
  `Pin` int(11) DEFAULT NULL,
  `District` varchar(30) DEFAULT NULL,
  `Phone` int(11) DEFAULT NULL,
  `Email` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`Shop_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `shop` */

/*Table structure for table `upload_news` */

DROP TABLE IF EXISTS `upload_news`;

CREATE TABLE `upload_news` (
  `News_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Image` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`News_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `upload_news` */

insert  into `upload_news`(`News_ID`,`Image`,`Description`,`Date`) values 
(1,'LEVEL0.png','ljhkj','2021-12-28'),
(2,'LEVEL0.png','deytedty','2021-12-28'),
(3,'LEVEL0.png','bjehdoie','2021-12-28');

/*Table structure for table `upload_post` */

DROP TABLE IF EXISTS `upload_post`;

CREATE TABLE `upload_post` (
  `Post_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Image` varchar(50) DEFAULT NULL,
  `Description` varchar(30) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`Post_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `upload_post` */

insert  into `upload_post`(`Post_ID`,`Image`,`Description`,`Date`) values 
(1,'LEVEL0.png','fjyhgkjg n vh','2021-12-28'),
(2,'LEVEL0.png','yewiyesp98','2021-12-28');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
