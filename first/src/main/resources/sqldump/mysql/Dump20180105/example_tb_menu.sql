-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: example
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_menu` (
  `IDX` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_NAME` varchar(45) NOT NULL,
  `MENU_ENAME` varchar(45) NOT NULL,
  `PARENT_IDX` int(11) NOT NULL DEFAULT '0',
  `MENU_IDX` int(11) NOT NULL DEFAULT '1',
  `ICON` varchar(45) NOT NULL DEFAULT '',
  `CREA_DTM` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREA_ID` varchar(45) NOT NULL,
  `LINK` varchar(200) DEFAULT '',
  `USE_YN` char(1) NOT NULL DEFAULT 'Y',
  `AUTHORITY` varchar(15) NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (`IDX`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES (1,'메인','Main',0,0,'fa fa-star','2017-11-30 14:24:33','admin','/main/main.do','Y','ROLE_USER'),(2,'커뮤니티','Cummunity',1,0,'fa fa-comments-o','2017-11-30 14:33:10','admin','/community/openBoardList.do','Y','ROLE_USER'),(3,'관리자 페이지','Management',99,0,'fa fa-gear','2017-11-30 14:39:58','admin','/admin/admin','Y','ROLE_ADMIN'),(4,'예제','Example',2,0,'fa fa-pencil','2017-11-30 14:46:28','admin','/example','Y','ROLE_USER'),(5,'Sub1','Sub1',2,1,'fa fa-pencil','2017-11-30 14:46:28','admin','/example/sub1','Y','ROLE_USER'),(6,'Sub2','Sub2',2,2,'fa fa-pencil','2017-11-30 14:46:28','admin','/example/sub2','Y','ROLE_USER'),(7,'Sub3','Sub3',2,2,'fa fa-pencil','2017-11-30 14:46:28','admin','/example/sub3','Y','ROLE_USER');
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-05 16:58:55
