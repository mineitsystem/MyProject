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
-- Table structure for table `sample`
--

DROP TABLE IF EXISTS `sample`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sample` (
  `ID` varchar(16) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  `USE_YN` char(1) DEFAULT NULL,
  `REG_USER` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sample`
--

LOCK TABLES `sample` WRITE;
/*!40000 ALTER TABLE `sample` DISABLE KEYS */;
INSERT INTO `sample` VALUES ('SAMPLE-00001','Runtime Environment','Foundation Layer','Y','eGov'),('SAMPLE-00002','Runtime Environment','Persistence Layer','Y','eGov'),('SAMPLE-00003','Runtime Environment','Presentation Layer','Y','eGov'),('SAMPLE-00004','Runtime Environment','Business Layer','Y','eGov'),('SAMPLE-00005','Runtime Environment','Batch Layer','Y','eGov'),('SAMPLE-00006','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00007','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00008','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00009','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00010','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00011','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00012','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00013','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00014','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00015','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00016','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00017','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00018','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00019','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00020','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00021','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00022','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00023','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00024','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00025','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00026','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00027','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00028','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00029','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00030','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00031','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00032','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00033','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00034','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00035','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00036','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00037','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00038','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00039','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00040','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00041','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00042','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00043','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00044','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00045','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00046','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00047','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00048','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00049','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00050','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00051','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00052','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00053','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00054','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00055','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00056','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00057','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00058','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00059','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00060','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00061','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00062','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00063','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00064','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00065','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00066','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00067','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00068','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00069','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00070','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00071','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00072','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00073','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00074','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00075','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00076','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00077','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00078','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00079','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00080','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00081','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00082','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00083','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00084','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00085','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00086','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00087','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00088','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00089','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00090','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00091','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00092','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00093','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00094','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00095','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00096','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00097','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00098','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00099','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00100','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00101','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00102','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00103','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00104','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00105','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00106','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00107','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00108','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00109','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00110','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00111','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00112','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00113','Runtime Environment','Integration Layer','Y','eGov'),('SAMPLE-00114','Runtime Environment','Integration Layer','Y','eGov');
/*!40000 ALTER TABLE `sample` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-29 14:58:58
