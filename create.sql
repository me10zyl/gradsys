CREATE DATABASE  IF NOT EXISTS `graduationsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `graduationsystem`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: zyl-me.xicp.net    Database: graduationsystem
-- ------------------------------------------------------
-- Server version	5.5.40-0ubuntu0.12.04.1

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
-- Table structure for table `duty`
--

DROP TABLE IF EXISTS `duty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `duty` (
  `subject_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  PRIMARY KEY (`subject_id`,`teacher_id`),
  KEY `FK_被负责` (`teacher_id`),
  CONSTRAINT `FK_被负责` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`),
  CONSTRAINT `FK_负责` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duty`
--

LOCK TABLES `duty` WRITE;
/*!40000 ALTER TABLE `duty` DISABLE KEYS */;
INSERT INTO `duty` VALUES (3,2),(7,2),(12,2),(13,2),(14,2),(16,2),(3,3),(4,3),(5,3),(3,5),(6,5),(7,6),(8,6),(11,6),(13,6),(8,7),(9,7),(10,7),(11,7),(12,7);
/*!40000 ALTER TABLE `duty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `func`
--

DROP TABLE IF EXISTS `func`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `func` (
  `func_id` int(11) NOT NULL AUTO_INCREMENT,
  `func_name` varchar(50) NOT NULL,
  PRIMARY KEY (`func_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `func`
--

LOCK TABLES `func` WRITE;
/*!40000 ALTER TABLE `func` DISABLE KEYS */;
INSERT INTO `func` VALUES (1,'/Subject/SeeSubject');
/*!40000 ALTER TABLE `func` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predom`
--

DROP TABLE IF EXISTS `predom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `predom` (
  `userGroup_id` int(11) NOT NULL,
  `func_id` int(11) NOT NULL,
  PRIMARY KEY (`userGroup_id`,`func_id`),
  KEY `FK_有2` (`func_id`),
  CONSTRAINT `FK_有` FOREIGN KEY (`userGroup_id`) REFERENCES `usergroup` (`userGroup_id`),
  CONSTRAINT `FK_有2` FOREIGN KEY (`func_id`) REFERENCES `func` (`func_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predom`
--

LOCK TABLES `predom` WRITE;
/*!40000 ALTER TABLE `predom` DISABLE KEYS */;
/*!40000 ALTER TABLE `predom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) DEFAULT NULL,
  `userGroup_id` int(11) NOT NULL,
  `student_num` varchar(30) NOT NULL,
  `student_name` varchar(12) DEFAULT NULL,
  `student_gender` char(2) DEFAULT NULL,
  `student_grade` char(4) DEFAULT NULL,
  `student_major` varchar(50) DEFAULT NULL,
  `student_telphone` varchar(11) DEFAULT NULL,
  `student_password` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `num_UQ` (`student_num`),
  KEY `FK_belong` (`userGroup_id`),
  KEY `FK_select` (`subject_id`),
  CONSTRAINT `FK_belong` FOREIGN KEY (`userGroup_id`) REFERENCES `usergroup` (`userGroup_id`),
  CONSTRAINT `FK_select` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,9,1,'12310320304','曾艺伦','男','12级','软件工程','12608069399','123123'),(2,5,1,'12310320320','郭珊','女','12级','软件工程','18380438069','123321');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_title` varchar(100) NOT NULL,
  `subject_description` varchar(10240) DEFAULT NULL,
  PRIMARY KEY (`subject_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (3,'毕业设计管理系统','毕业设计管理系统毕业设计管理系统毕业设计管理系统毕业设计管理系统毕业设计管理系统毕业设计管理系统毕业设计管理系统毕业设计管理系统毕业设计管理系统毕业设计管理系统'),(4,'二手交易APP','二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP二手交易APP'),(5,'成都美食网站','非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃非常非常好吃'),(6,'成都生活圈','成都生活圈很好玩成都生活圈很好玩成都生活圈很好玩成都生活圈很好玩成都生活圈很好玩成都生活圈很好玩成都生活圈很好玩成都生活圈很好玩'),(7,'Disco-u-rage论坛系统','好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦好麻烦'),(8,'HTML5飞机大战','飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊飞机大战啊'),(9,'WebSocket五子棋','好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩好玩'),(10,'你画我猜','我猜我猜我猜我猜我猜我猜我猜我猜我猜我猜我猜我猜我猜我猜我猜我猜我猜'),(11,'曾艺伦嘿嘿猪','音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器音乐播放器'),(12,'视频播放器','视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器视频播放器'),(13,'全民灰机大战','请输入题目详情请输入题目详情请输入题目详情请输入题目详情请输入题目详情请输入题目详情请输入题目详情请输入题目详情请输入题目详情请输入题目详情请输入题目详情请输入题目详情'),(14,'毕业生设计管理系统','嘿嘿额和IEhi额hi额'),(15,'我的天','wqwdw'),(16,'H啊哈哈哈','阿法迪三发发送的');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `userGroup_id` int(11) NOT NULL,
  `teacher_num` varchar(30) NOT NULL,
  `teacher_name` varchar(20) DEFAULT NULL,
  `teacher_gender` char(2) DEFAULT NULL,
  `teacher_telephone` varchar(11) DEFAULT NULL,
  `teacher_password` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE KEY `num_UQ` (`teacher_num`),
  KEY `FK_belong2` (`userGroup_id`),
  CONSTRAINT `FK_belong2` FOREIGN KEY (`userGroup_id`) REFERENCES `usergroup` (`userGroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (2,2,'611033','张小华','男','123456789','123123'),(3,2,'611034','仲宝才','男','123656789','123123'),(5,2,'611035','张轶','男','123656789','123123'),(6,2,'611036','段恩泽','男','1236616789','123123'),(7,2,'611037','黄波','男','13788886666','123123');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroup` (
  `userGroup_id` int(11) NOT NULL AUTO_INCREMENT,
  `userGroup_name` varchar(20) NOT NULL,
  PRIMARY KEY (`userGroup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup`
--

LOCK TABLES `usergroup` WRITE;
/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
INSERT INTO `usergroup` VALUES (1,'学生'),(2,'老师');
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-25 14:37:01
