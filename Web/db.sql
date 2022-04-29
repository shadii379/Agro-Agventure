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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `agricultural_equipment` */

insert  into `agricultural_equipment`(`Equipment_ID`,`Name`,`Image`,`Description`,`Price`) values 
(1,'Plough','frst.JPG','Plough description','200'),
(3,'knife','actfloat.png','fghgh','24');

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `Bank_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Account_No` varchar(30) DEFAULT NULL,
  `IFSC` varchar(30) DEFAULT NULL,
  `Key` int(11) DEFAULT NULL,
  `Amount` int(11) DEFAULT NULL,
  `Farmer_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`Bank_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

insert  into `bank`(`Bank_ID`,`Account_No`,`IFSC`,`Key`,`Amount`,`Farmer_ID`) values 
(1,'123','111',22,88952,6),
(2,'22','33',44,89960,13);

/*Table structure for table `bill` */

DROP TABLE IF EXISTS `bill`;

CREATE TABLE `bill` (
  `Bill_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` int(11) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `Total_Price` int(11) DEFAULT NULL,
  `Status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Bill_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `bill` */

insert  into `bill`(`Bill_ID`,`Shop_ID`,`Date`,`Total_Price`,`Status`) values 
(1,13,'2022-01-20',20,'payed'),
(2,13,'2022-01-20',30,'pending'),
(3,13,'2022-01-20',120,'accepted'),
(4,13,'2022-01-20',65,'rejected');

/*Table structure for table `bill_equipment` */

DROP TABLE IF EXISTS `bill_equipment`;

CREATE TABLE `bill_equipment` (
  `Bill_Equipment_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Farmer_ID` int(11) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `Total_Price` int(11) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Bill_Equipment_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `bill_equipment` */

insert  into `bill_equipment`(`Bill_Equipment_ID`,`Farmer_ID`,`Date`,`Total_Price`,`Status`) values 
(1,6,'2022-01-13',400,'payed'),
(2,6,'2022-01-13',1200,'payed'),
(3,6,'2022-01-13',400,'pending'),
(4,6,'2022-01-13',800,'payed'),
(5,6,'2022-01-20',248,'payed');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `Complaint_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Product_ID` int(11) DEFAULT NULL,
  `Shop_ID` int(11) DEFAULT NULL,
  `Complaint` varchar(50) DEFAULT NULL,
  `Date` varchar(50) DEFAULT NULL,
  `Reply` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Complaint_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`Complaint_ID`,`Product_ID`,`Shop_ID`,`Complaint`,`Date`,`Reply`) values 
(1,4,2,'complaint','2022-01-12','okkk'),
(2,6,2,'bddd','2022-01-14','pending'),
(3,6,2,'yyuu','2022-01-20','m,nm,'),
(4,12,13,'fhghgh','2022-01-20','dfdff'),
(5,6,13,'hhh','2022-01-20','kkkk');

/*Table structure for table `crop` */

DROP TABLE IF EXISTS `crop`;

CREATE TABLE `crop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `crop` varchar(45) DEFAULT NULL,
  `details` text,
  `image` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `crop` */

insert  into `crop`(`id`,`crop`,`details`,`image`) values 
(6,'Coconut','coconut is the most important crop in Kerala. The crop is grown over all the state. Most of the Kerala houses also have Coconut palm grown for immediate household needs. The growing of coconuts is by tradition part of the local rural economy rather than a major element of national agriculture. Three- or four-month-old seedlings are usually uprooted and transferred to a much larger field,where they will be planted at least 8-m/26 ft apart.','spicesCoconutThumb.jpg'),
(8,'Pepper\r\n','Pepper is commonly cultivated as \"homestead cultivation\" growing it as a secondary crop interspersed with several other crops. Cultivation of pepper as a pure crop is also practiced though it is becoming rare. It is more so in Kerala State, which accounts for 97.4 per cent of the total area under the crop in the country.','spicesPepperThumb.jpg'),
(9,'Cashew nut','In Kerala, in the last one decade, there has been a continuous and considerable decline in both area under cultivation as well as production of cashew','spicesCashewnutThumb.jpg'),
(10,'Rubber','Rubber cultivation in plantation is a systematic agriculture in which first seeds have been raised in a nursery. When the rubber seedlings are about 5 cm in diameter buds from high yielding clones are grafted on to the seedlings. This grafted section forms the main part of the tree.','rubber.jpg'),
(11,'Coffee','Coffee production, cultivation of the coffee plant, usually done in large commercial operations. The plant, a tropical evergreen shrub or small tree of African origin (genus Coffea, family Rubiaceae), is grown for its seeds, or beans, which are roasted, ground, and sold for brewing coffee','spicesCoffeeThumb.jpg'),
(15,'Paddy','The amount of water, the quality of the soil, the amount of daylight and the gentle winds that are characteristic of this region of Kerala all combine to produce perfect conditions for rice to thrive. A paddy field is a flooded parcel of farmland for growing rice','spicesPaddyThumb.jpg'),
(16,'Tea','Tea requires a moderately hot and humid climate. Climate influences yield, crop distribution and quality. Therefore, before cultivating tea in a new area, the suitability of the climate is the first point to be considered. Tea grows best on well-drained fertile acid soil on high lands.','spicesTeaThumb.jpg'),
(18,'Chilli','Chilli requires warm and humid climate for its best growth and dry weather during the maturation of fruits. It grows in wide range of altitudes ranging from sea level unto nearly 2100 m above MSL. It is generally a cold weather crop but can be grown throughout the year under irrigation.','spicesChilliThumb.jpg'),
(19,'Ginger','The best time for planting ginger is during the first fortnight of May with the receipt of pre -monsoon showers. Under irrigated conditions, it can be planted well in advance during the middle of February or early March.','spicesGingerRoot.jpg');

/*Table structure for table `details` */

DROP TABLE IF EXISTS `details`;

CREATE TABLE `details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cropid` int(11) DEFAULT NULL,
  `temp` double DEFAULT NULL,
  `humidity` double DEFAULT NULL,
  `wet` int(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

/*Data for the table `details` */

insert  into `details`(`id`,`cropid`,`temp`,`humidity`,`wet`) values 
(8,6,0.6,0.72,1),
(9,8,0.28,0.89,1),
(10,9,0.25,0.55,0),
(11,10,0.5,0.5,1),
(12,11,0.4,0.7,1),
(13,15,0.35,0.85,1),
(14,16,0.2,0.34,0),
(15,19,0.3,0.5,1),
(16,6,0.6,0.72,1),
(17,6,0.6,0.72,1);

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
  `Phone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Farmer_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `farmer` */

insert  into `farmer`(`Farmer_ID`,`Login_ID`,`First_Name`,`Last_Name`,`Place`,`Post`,`Pin`,`District`,`Phone`) values 
(1,4,'Farmer','a','place','post',654321,'district',2147483647),
(2,6,'raj','k','k','k',672345,'kerlaa',8234567890),
(3,9,'anu','k','clts','clt po',678890,'kerala',8923456780),
(4,10,'bbb','nn','xx','hh',621325,'vg',9870890842),
(5,12,'vishnu','k','clt','clt',678908,'kozhikode',8956890890);

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `Feedback_ID` int(11) NOT NULL AUTO_INCREMENT,
  `From_ID` int(11) DEFAULT NULL,
  `To_ID` int(11) DEFAULT NULL,
  `Feedback` varchar(300) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`Feedback_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`Feedback_ID`,`From_ID`,`To_ID`,`Feedback`,`Date`) values 
(1,2,1,'feedback\r\n','2022-01-12'),
(2,2,1,'kkkk','2022-01-14'),
(3,13,1,'','2022-01-20'),
(4,13,1,'xcvv','2022-01-20');

/*Table structure for table `krishibhavan` */

DROP TABLE IF EXISTS `krishibhavan`;

CREATE TABLE `krishibhavan` (
  `Krishibhavan_ID` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) DEFAULT NULL,
  `PLace` varchar(30) DEFAULT NULL,
  `Post` varchar(30) DEFAULT NULL,
  `Pin` varchar(30) DEFAULT NULL,
  `District` varchar(30) DEFAULT NULL,
  `Phone` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Krishibhavan_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `krishibhavan` */

insert  into `krishibhavan`(`Krishibhavan_ID`,`lid`,`PLace`,`Post`,`Pin`,`District`,`Phone`) values 
(1,3,'mananthavadhy','mananthavady','670363','wayanad',9876543210),
(2,5,'place','post','123456','district',9123456780);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_Name` varchar(20) DEFAULT NULL,
  `Password` varchar(20) DEFAULT NULL,
  `Type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`login_ID`),
  UNIQUE KEY `User_Name` (`User_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_ID`,`User_Name`,`Password`,`Type`) values 
(1,'admin','123','admin'),
(2,'shop','123','shop'),
(3,'krishibhavan','123','krishibhavan'),
(4,'farmer','123','farmer'),
(5,'krishibhavan2','123','krishibhavan'),
(6,'anu','123','farmer'),
(9,'anus','123','farmer'),
(10,'amm','123456','farmer'),
(11,'Manathavadi','Mananthavadi','krishibhavan'),
(12,'vis','123456','farmer'),
(13,'malll','123','shop');

/*Table structure for table `notification` */

DROP TABLE IF EXISTS `notification`;

CREATE TABLE `notification` (
  `Notification_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Notification` varchar(30) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`Notification_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `notification` */

insert  into `notification`(`Notification_ID`,`Notification`,`Date`) values 
(1,'Notification','2022-01-12'),
(2,'Notification','2022-01-12'),
(3,'shop','2022-01-19'),
(4,'safa','2022-01-19'),
(5,'gfhgfhgh','2022-01-20');

/*Table structure for table `order_equipment` */

DROP TABLE IF EXISTS `order_equipment`;

CREATE TABLE `order_equipment` (
  `Order_Equipment_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Equipment_ID` int(11) DEFAULT NULL,
  `Farmer_ID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Bill_Equipment_ID` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`Order_Equipment_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `order_equipment` */

insert  into `order_equipment`(`Order_Equipment_ID`,`Equipment_ID`,`Farmer_ID`,`Date`,`Bill_Equipment_ID`,`quantity`) values 
(1,1,6,'2022-01-13',1,2),
(2,1,6,'2022-01-13',2,4),
(3,1,6,'2022-01-13',2,2),
(4,1,6,'2022-01-13',3,2),
(5,1,6,'2022-01-13',4,4),
(6,3,6,'2022-01-20',5,2),
(7,1,6,'2022-01-20',5,1);

/*Table structure for table `order_product` */

DROP TABLE IF EXISTS `order_product`;

CREATE TABLE `order_product` (
  `Order_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Product_ID` int(11) DEFAULT NULL,
  `Shop_ID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Quantity` varchar(20) DEFAULT NULL,
  `Bill_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Order_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `order_product` */

insert  into `order_product`(`Order_ID`,`Product_ID`,`Shop_ID`,`Date`,`Quantity`,`Bill_ID`) values 
(1,3,13,'2022-01-20','2','1'),
(2,2,13,'2022-01-20','1','2'),
(3,1,13,'2022-01-20','2','3'),
(4,2,13,'2022-01-20','1','3'),
(5,2,13,'2022-01-20','2','3'),
(6,1,13,'2022-01-20','3','4'),
(7,3,13,'2022-01-20','2','4');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `Product_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Farmer_ID` int(11) DEFAULT NULL,
  `Product_Name` varchar(30) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Image` varchar(500) DEFAULT NULL,
  `Quantity` varchar(25) DEFAULT NULL,
  `Price` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Product_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`Product_ID`,`Farmer_ID`,`Product_Name`,`Description`,`Image`,`Quantity`,`Price`) values 
(1,4,'product1','description','storage_0000-6B66_Download_1.jpg','50','15'),
(2,4,'product2','description','storage_0000-6B66_Download_2.jpg','45','30'),
(3,10,'wheet','vghh','storage_0000-6B66_Download_2.jpg','230','10');

/*Table structure for table `result` */

DROP TABLE IF EXISTS `result`;

CREATE TABLE `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `humidity` float DEFAULT NULL,
  `temperature` float DEFAULT NULL,
  `wet` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `result` */

insert  into `result`(`id`,`uid`,`date`,`time`,`humidity`,`temperature`,`wet`) values 
(1,2,'0000-00-00','00:00:05',1,1,1),
(5,5,'0000-00-00','00:00:06',5,2,0);

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
  `Phone` bigint(20) DEFAULT NULL,
  `Email` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`Shop_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `shop` */

insert  into `shop`(`Shop_ID`,`Login_ID`,`Shop_Name`,`PLace`,`Post`,`Pin`,`District`,`Phone`,`Email`) values 
(1,2,'shop','place','post',654321,'district',2147483647,'email@gmail.com'),
(2,13,'mall','clt','clt',657896,'koxhikode',8934789068,'mall@gmail.com');

/*Table structure for table `upload_news` */

DROP TABLE IF EXISTS `upload_news`;

CREATE TABLE `upload_news` (
  `News_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Image` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`News_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `upload_news` */

insert  into `upload_news`(`News_ID`,`Image`,`Description`,`Date`) values 
(1,'1.png','newsss','2022-01-20');

/*Table structure for table `upload_post` */

DROP TABLE IF EXISTS `upload_post`;

CREATE TABLE `upload_post` (
  `Post_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Image` varchar(50) DEFAULT NULL,
  `Description` varchar(30) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  PRIMARY KEY (`Post_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `upload_post` */

insert  into `upload_post`(`Post_ID`,`Image`,`Description`,`Date`) values 
(1,'bd8df126-9cb3-4470-ad59-ae2cb861229c.jpg','description\r\n\r\n','2022-01-12'),
(2,'IMG_0749.JPG','\r\n','2022-01-20'),
(3,'mainlogo.MP4','cdvdf','2022-01-20');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
