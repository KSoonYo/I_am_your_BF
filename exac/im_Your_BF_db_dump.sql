CREATE DATABASE  IF NOT EXISTS `ssafy_web_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ssafy_web_db`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: i6b107.p.ssafy.io    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `app_basic`
--

DROP TABLE IF EXISTS `app_basic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_basic` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `location` varchar(100) NOT NULL,
  `ref_word` varchar(20) DEFAULT NULL,
  `mean` longtext,
  `part` varchar(10) NOT NULL,
  `word` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_basic`
--

LOCK TABLES `app_basic` WRITE;
/*!40000 ALTER TABLE `app_basic` DISABLE KEYS */;
INSERT INTO `app_basic` VALUES (1,'basic/3331.mp4','',NULL,'동사','안녕하세요'),(2,'basic/8265.mp4','',NULL,'명사','우리'),(3,'basic/8265.mp4','',NULL,'명사','저희'),(4,'basic/11845.mp4','',NULL,'명사','당신'),(5,'basic/7028.mp4','',NULL,'명사','친구'),(6,'basic/15778.mp4','',NULL,'명사','시스템'),(7,'basic/11290.mp4','',NULL,'명사','수어'),(8,'basic/10533.mp4','',NULL,'명사','통역'),(9,'basic/7422.mp4','',NULL,'명사','또'),(10,'basic/9192.mp4','',NULL,'명사','자막'),(11,'basic/15587.mp4','',NULL,'명사','실시간 처리'),(12,'basic/3667.mp4','',NULL,'명사','권리'),(13,'basic/9380.mp4','',NULL,'동사','지키다'),(14,'basic/15587.mp4',NULL,NULL,'명사','실시간처리');
/*!40000 ALTER TABLE `app_basic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_finger`
--

DROP TABLE IF EXISTS `app_finger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_finger` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `location` varchar(100) NOT NULL,
  `ref_word` longtext,
  `mean` longtext,
  `part` varchar(10) NOT NULL,
  `word` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_finger`
--

LOCK TABLES `app_finger` WRITE;
/*!40000 ALTER TABLE `app_finger` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_finger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_number`
--

DROP TABLE IF EXISTS `app_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_number` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `location` varchar(100) NOT NULL,
  `ref_word` longtext,
  `mean` longtext,
  `part` varchar(10) NOT NULL,
  `word` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_number`
--

LOCK TABLES `app_number` WRITE;
/*!40000 ALTER TABLE `app_number` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_number` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group`
--

DROP TABLE IF EXISTS `auth_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group`
--

LOCK TABLES `auth_group` WRITE;
/*!40000 ALTER TABLE `auth_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_group_permissions`
--

DROP TABLE IF EXISTS `auth_group_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_group_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_group_permissions`
--

LOCK TABLES `auth_group_permissions` WRITE;
/*!40000 ALTER TABLE `auth_group_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_group_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_permission`
--

DROP TABLE IF EXISTS `auth_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_permission` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content_type_id` int NOT NULL,
  `codename` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`),
  CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_permission`
--

LOCK TABLES `auth_permission` WRITE;
/*!40000 ALTER TABLE `auth_permission` DISABLE KEYS */;
INSERT INTO `auth_permission` VALUES (1,'Can add conference',1,'add_conference'),(2,'Can change conference',1,'change_conference'),(3,'Can delete conference',1,'delete_conference'),(4,'Can view conference',1,'view_conference'),(5,'Can add conference category',2,'add_conferencecategory'),(6,'Can change conference category',2,'change_conferencecategory'),(7,'Can delete conference category',2,'delete_conferencecategory'),(8,'Can view conference category',2,'view_conferencecategory'),(9,'Can add conference history',3,'add_conferencehistory'),(10,'Can change conference history',3,'change_conferencehistory'),(11,'Can delete conference history',3,'delete_conferencehistory'),(12,'Can view conference history',3,'view_conferencehistory'),(13,'Can add user',4,'add_user'),(14,'Can change user',4,'change_user'),(15,'Can delete user',4,'delete_user'),(16,'Can view user',4,'view_user'),(17,'Can add basic',5,'add_basic'),(18,'Can change basic',5,'change_basic'),(19,'Can delete basic',5,'delete_basic'),(20,'Can view basic',5,'view_basic'),(21,'Can add finger',6,'add_finger'),(22,'Can change finger',6,'change_finger'),(23,'Can delete finger',6,'delete_finger'),(24,'Can view finger',6,'view_finger'),(25,'Can add number',7,'add_number'),(26,'Can change number',7,'change_number'),(27,'Can delete number',7,'delete_number'),(28,'Can view number',7,'view_number'),(29,'Can add log entry',8,'add_logentry'),(30,'Can change log entry',8,'change_logentry'),(31,'Can delete log entry',8,'delete_logentry'),(32,'Can view log entry',8,'view_logentry'),(33,'Can add permission',9,'add_permission'),(34,'Can change permission',9,'change_permission'),(35,'Can delete permission',9,'delete_permission'),(36,'Can view permission',9,'view_permission'),(37,'Can add group',10,'add_group'),(38,'Can change group',10,'change_group'),(39,'Can delete group',10,'delete_group'),(40,'Can view group',10,'view_group'),(41,'Can add user',11,'add_user'),(42,'Can change user',11,'change_user'),(43,'Can delete user',11,'delete_user'),(44,'Can view user',11,'view_user'),(45,'Can add content type',12,'add_contenttype'),(46,'Can change content type',12,'change_contenttype'),(47,'Can delete content type',12,'delete_contenttype'),(48,'Can view content type',12,'view_contenttype'),(49,'Can add session',13,'add_session'),(50,'Can change session',13,'change_session'),(51,'Can delete session',13,'delete_session'),(52,'Can view session',13,'view_session');
/*!40000 ALTER TABLE `auth_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_groups`
--

DROP TABLE IF EXISTS `auth_user_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user_groups` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `group_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`),
  CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_groups`
--

LOCK TABLES `auth_user_groups` WRITE;
/*!40000 ALTER TABLE `auth_user_groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_user_user_permissions`
--

DROP TABLE IF EXISTS `auth_user_user_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user_user_permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`),
  CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user_user_permissions`
--

LOCK TABLES `auth_user_user_permissions` WRITE;
/*!40000 ALTER TABLE `auth_user_user_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user_user_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference`
--

DROP TABLE IF EXISTS `conference`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference` (
  `conference_serial` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`conference_serial`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference`
--

LOCK TABLES `conference` WRITE;
/*!40000 ALTER TABLE `conference` DISABLE KEYS */;
INSERT INTO `conference` VALUES (1,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-01-31 22:23:41.787000','a.jpg','대전_1반수업1','aa'),(2,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-07 10:24:54.570000','a.jpg','대전_1반수업2','aa'),(6,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:34:54.580000','a.jpg','대전_1반수업3','aa'),(7,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:06.090000','a.jpg','대전_1반수업4','aa'),(8,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:07.190000','a.jpg','대전_1반수업5','aa'),(9,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:07.743000','a.jpg','대전_1반수업6','aa'),(10,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:08.263000','a.jpg','대전_1반수업7','aa'),(11,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:08.671000','a.jpg','대전_1반수업8','aa'),(12,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:09.048000','a.jpg','대전_1반수업9','aa'),(13,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:09.938000','a.jpg','대전_1반수업10','aa'),(14,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:17.944000','a.jpg','대전_1반수업11','aa'),(15,'안녕하세요',NULL,_binary '','12345','2022-02-09 14:35:18.354000','a.jpg','대전_1반수업12','aa'),(16,'qweqw',NULL,_binary '','','2022-02-09 18:00:58.573000','a.jpg','qwe','asd'),(17,'asdasdasdsad',NULL,_binary '','123456','2022-02-11 14:44:30.058000','a.jpg','qweqwe','asd'),(18,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:36.832000','a.jpg','대전_1반수업','aa'),(19,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:37.744000','a.jpg','대전_1반수업','aa'),(20,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:38.172000','a.jpg','대전_1반수업','aa'),(21,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:38.544000','a.jpg','대전_1반수업','aa'),(22,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:38.923000','a.jpg','대전_1반수업','aa'),(23,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:39.250000','a.jpg','대전_1반수업','aa'),(24,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:39.563000','a.jpg','대전_1반수업','aa'),(25,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:39.854000','a.jpg','대전_1반수업','aa'),(26,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:40.155000','a.jpg','대전_1반수업','aa'),(27,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:40.449000','a.jpg','대전_1반수업','aa'),(28,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:40.655000','a.jpg','대전_1반수업','aa'),(29,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:40.700000','a.jpg','대전_1반수업','aa'),(30,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:40.985000','a.jpg','대전_1반수업','aa'),(31,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:41.258000','a.jpg','대전_1반수업','aa'),(32,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:41.474000','a.jpg','대전_1반수업','aa'),(33,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:41.719000','a.jpg','대전_1반수업','aa'),(34,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:41.939000','a.jpg','대전_1반수업','aa'),(35,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:42.179000','a.jpg','대전_1반수업','aa'),(36,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:42.417000','a.jpg','대전_1반수업','aa'),(37,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:42.624000','a.jpg','대전_1반수업','aa'),(38,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:42.857000','a.jpg','대전_1반수업','aa'),(39,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:43.096000','a.jpg','대전_1반수업','aa'),(40,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:43.305000','a.jpg','대전_1반수업','aa'),(41,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:43.518000','a.jpg','대전_1반수업','aa'),(42,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:43.746000','a.jpg','대전_1반수업','aa'),(43,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:43.948000','a.jpg','대전_1반수업','aa'),(44,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:44.149000','a.jpg','대전_1반수업','aa'),(45,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:44.367000','a.jpg','대전_1반수업','aa'),(46,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:44.583000','a.jpg','대전_1반수업','aa'),(47,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:44.804000','a.jpg','대전_1반수업','aa'),(48,'오늘은 간단히 jpa에대해서 배워보기로 해요',NULL,_binary '','12345','2022-02-13 19:07:45.053000','a.jpg','대전_1반수업','aa');
/*!40000 ALTER TABLE `conference` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_category`
--

DROP TABLE IF EXISTS `conference_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_category`
--

LOCK TABLES `conference_category` WRITE;
/*!40000 ALTER TABLE `conference_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `conference_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conference_history`
--

DROP TABLE IF EXISTS `conference_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conference_history` (
  `histroy_serial` bigint NOT NULL AUTO_INCREMENT,
  `enter_time` datetime(6) DEFAULT NULL,
  `exit_time` datetime(6) DEFAULT NULL,
  `conference_serial` bigint DEFAULT NULL,
  `user_serial` bigint DEFAULT NULL,
  PRIMARY KEY (`histroy_serial`),
  KEY `FKrtmko8x9ipf7s8yroted2tyyf` (`conference_serial`),
  KEY `FK18naa2tdh292s0g8d2nxnhdbv` (`user_serial`),
  CONSTRAINT `FK18naa2tdh292s0g8d2nxnhdbv` FOREIGN KEY (`user_serial`) REFERENCES `user` (`user_serial`),
  CONSTRAINT `FKrtmko8x9ipf7s8yroted2tyyf` FOREIGN KEY (`conference_serial`) REFERENCES `conference` (`conference_serial`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conference_history`
--

LOCK TABLES `conference_history` WRITE;
/*!40000 ALTER TABLE `conference_history` DISABLE KEYS */;
INSERT INTO `conference_history` VALUES (2,NULL,NULL,1,1);
/*!40000 ALTER TABLE `conference_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (8,'admin','logentry'),(5,'app','basic'),(1,'app','conference'),(2,'app','conferencecategory'),(3,'app','conferencehistory'),(6,'app','finger'),(7,'app','number'),(4,'app','user'),(10,'auth','group'),(9,'auth','permission'),(11,'auth','user'),(12,'contenttypes','contenttype'),(13,'sessions','session');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` VALUES (1,'contenttypes','0001_initial','2022-02-03 07:02:28.394010'),(2,'auth','0001_initial','2022-02-03 07:02:29.423975'),(3,'admin','0001_initial','2022-02-03 07:02:29.643975'),(4,'admin','0002_logentry_remove_auto_add','2022-02-03 07:02:29.655974'),(5,'admin','0003_logentry_add_action_flag_choices','2022-02-03 07:02:29.669009'),(6,'app','0001_initial','2022-02-03 07:02:29.786009'),(7,'contenttypes','0002_remove_content_type_name','2022-02-03 07:02:29.956974'),(8,'auth','0002_alter_permission_name_max_length','2022-02-03 07:02:30.264986'),(9,'auth','0003_alter_user_email_max_length','2022-02-03 07:02:30.332985'),(10,'auth','0004_alter_user_username_opts','2022-02-03 07:02:30.357065'),(11,'auth','0005_alter_user_last_login_null','2022-02-03 07:02:30.491536'),(12,'auth','0006_require_contenttypes_0002','2022-02-03 07:02:30.503482'),(13,'auth','0007_alter_validators_add_error_messages','2022-02-03 07:02:30.526512'),(14,'auth','0008_alter_user_username_max_length','2022-02-03 07:02:30.680471'),(15,'auth','0009_alter_user_last_name_max_length','2022-02-03 07:02:30.834310'),(16,'auth','0010_alter_group_name_max_length','2022-02-03 07:02:30.887447'),(17,'auth','0011_update_proxy_permissions','2022-02-03 07:02:30.918456'),(18,'auth','0012_alter_user_first_name_max_length','2022-02-03 07:02:31.275427'),(19,'sessions','0001_initial','2022-02-03 07:02:31.379692');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_serial` bigint NOT NULL AUTO_INCREMENT,
  `assign` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `report` int NOT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_serial`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'mo_ah','2022-02-07 10:22:52.031000',NULL,'$2a$10$ooyuawjFtjOufZ7EXoiDHufIa8uSnWCnANyyC7Ilc8XtuskBBnPKm',0,NULL,NULL,'yourEmail@naver.com','ssafy_web','your_name'),(4,'mo_ah','2022-02-08 14:16:33.598000',NULL,'$2a$10$g8fRUt70kl3qH0vBabF7luw0tB7NpMPiVGBfnZETKEKcsnYhazHAK',0,NULL,NULL,'yourEmail@naver.com','aa','your_name'),(5,'mo_ah','2022-02-09 14:01:03.955000',NULL,'$2a$10$pv7ITy6pZ/9YIe9saC.OAOvivcTJspAnbPBHX5RgmfNPq4mnwNbe2',0,NULL,NULL,'gkstmdgns422@naver.com','qwe','한승훈'),(6,'mo_ah','2022-02-09 15:10:17.978000',NULL,'$2a$10$bCV4XFTRjcUeQvgjrHjZluLKTXgfSy5y5HsLdRxafBsD1q.M68Ze6',0,NULL,NULL,'asd@gmail.com','asd','한승훈'),(7,'mo_ah','2022-02-17 22:12:24.338000',NULL,'$2a$10$EI0a2fbVznLRW5SnHqBX6e/dAsPbTbwLr.IW1SmFlRpZjO2PTcU9G',0,NULL,NULL,'mo_ah@naver.com','ssafy','ssafy');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-17 22:27:25
