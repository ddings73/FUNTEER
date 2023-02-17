CREATE DATABASE  IF NOT EXISTS `funteer_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `funteer_db`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: funteer_db
-- ------------------------------------------------------
-- Server version	8.0.28

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `alarm_entity`
--

DROP TABLE IF EXISTS `alarm_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `is_read` bit(1) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm_entity`
--

LOCK TABLES `alarm_entity` WRITE;
/*!40000 ALTER TABLE `alarm_entity` DISABLE KEYS */;
INSERT INTO `alarm_entity` VALUES (9,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(10,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(13,'팔로우 하신 단체녹산봉사단의 새로운 펀딩이 오픈되었습니다.',_binary '\0','/funding/detail/5','becoding96@gmail.com'),(15,'팔로우 하신 단체녹산봉사단의 새로운 펀딩이 오픈되었습니다.',_binary '\0','/funding/detail/6','becoding96@gmail.com'),(17,'팔로우 하신 단체녹산봉사단의 새로운 펀딩이 오픈되었습니다.',_binary '\0','/funding/detail/7','becoding96@gmail.com'),(19,'팔로우 하신 단체녹산봉사단의 새로운 펀딩이 오픈되었습니다.',_binary '\0','/funding/detail/8','becoding96@gmail.com'),(21,'팔로우 하신 단체녹산봉사단의 새로운 펀딩이 오픈되었습니다.',_binary '\0','/funding/detail/18','becoding96@gmail.com'),(23,'팔로우 하신 단체녹산봉사단의 새로운 펀딩이 오픈되었습니다.',_binary '\0','/funding/detail/20','becoding96@gmail.com'),(25,'팔로우 하신 단체녹산봉사단의 새로운 펀딩이 오픈되었습니다.',_binary '\0','/funding/detail/31','becoding96@gmail.com'),(27,'팔로우 하신 단체녹산봉사단의 새로운 펀딩이 오픈되었습니다.',_binary '\0','/funding/detail/32','becoding96@gmail.com'),(29,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(30,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(32,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(33,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(35,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(36,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(38,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(39,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(41,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(42,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(44,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(45,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(47,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(48,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(50,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','admincoach@ssafy.com'),(51,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','adminconsultant204@ssafy.com'),(52,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','yamyambuk04@gmail.com'),(53,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','admincoach@ssafy.com'),(54,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','adminconsultant204@ssafy.com'),(56,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(57,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(58,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','yamyambuk04@gmail.com'),(59,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','admincoach@ssafy.com'),(60,'새로운 펀딩이 생성되었습니다.',_binary '\0','admin/funding','adminconsultant204@ssafy.com'),(63,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','admincoach@ssafy.com'),(64,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','adminconsultant204@ssafy.com'),(66,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','admincoach@ssafy.com'),(67,'새로운 단체가 승인을 기다리고 있습니다.',_binary '\0','/admin/team','adminconsultant204@ssafy.com'),(68,'신호희망봉사단 단체의 라이브 방송이 시작되었습니다.',_binary '\0','/subscribeLiveRoom/신호희망봉사단','becoding96@gmail.com');
/*!40000 ALTER TABLE `alarm_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attach`
--

DROP TABLE IF EXISTS `attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attach` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attach`
--

LOCK TABLES `attach` WRITE;
/*!40000 ALTER TABLE `attach` DISABLE KEYS */;
INSERT INTO `attach` VALUES (1,'OTHER','medal.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/e5ad36af-55f8-4c08-befd-ea142d1e0348medal.png','2023-02-15 05:21:33.401063'),(2,'VMS','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/11ade07c-5027-4c18-9e91-72f5372fe4052.pdf','2023-02-15 05:44:49.690542'),(3,'PERFORM','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/e33f8503-043e-4784-99aa-49355e24e6842.pdf','2023-02-15 05:44:49.690759'),(4,'VMS','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/029460c9-2ba4-42d2-b863-1b2266765d562.pdf','2023-02-15 05:44:59.606853'),(5,'PERFORM','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/4b0d1563-9875-4b58-be92-0b412adfa99e2.pdf','2023-02-15 05:44:59.606861'),(6,'LIVE','ses_Ia0Zw4C4eH.mp4','https://funteer.s3.ap-northeast-2.amazonaws.com/live/dccfe0d0-2bbb-401b-abc2-54786444f48cses_Ia0Zw4C4eH.mp4','2023-02-15 06:25:47.669307'),(7,'VMS','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/578684b8-424f-4696-9d5e-a0c8eafc59bb2.pdf','2023-02-15 06:52:15.497595'),(8,'PERFORM','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/4ebc351f-c195-4bf0-a4b9-34447c7f19952.pdf','2023-02-15 06:52:15.497784'),(9,'VMS','VMS.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/00ad0011-58f1-4752-aada-ef2adb1ec135VMS.pdf','2023-02-15 06:55:23.107780'),(10,'PERFORM','봉사실적.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/6106de28-24d5-425d-97da-c693085b9aed%EB%B4%89%EC%82%AC%EC%8B%A4%EC%A0%81.pdf','2023-02-15 06:55:23.107788'),(11,'OTHER','planet_funteer.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/02743aaa-1cd2-4fea-b633-92cafe222c64planet_funteer.png','2023-02-15 06:56:43.248622'),(12,'OTHER','튀르키예.png','https://funteer.s3.ap-northeast-2.amazonaws.com/donation/03101ccb-9000-47fa-bb7f-feeb11b04f12%ED%8A%80%EB%A5%B4%ED%82%A4%EC%98%88.png','2023-02-15 07:00:13.097558'),(14,'LIVE','ses_HChpdFLtgC.mp4','https://funteer.s3.ap-northeast-2.amazonaws.com/live/7fc37b5e-554d-48ab-9674-ba06a82975f7ses_HChpdFLtgC.mp4','2023-02-15 07:10:09.470412'),(15,'OTHER','KakaoTalk_20230214_105317362_05.jpg','https://funteer.s3.ap-northeast-2.amazonaws.com/user/fb242b38-3a99-40aa-b2a6-29ddeb7ba184KakaoTalk_20230214_105317362_05.jpg','2023-02-15 12:55:52.383283'),(16,'OTHER','화면 캡처 2023-02-15 215945.png','https://funteer.s3.ap-northeast-2.amazonaws.com/donation/8c55873e-dde9-4407-9297-5cd5d58a9190%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202023-02-15%20215945.png','2023-02-15 13:12:27.674114'),(17,'VMS','1.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/100ae8f4-c516-43f7-a034-00d5fd3c288b1.pdf','2023-02-15 15:36:10.325950'),(18,'PERFORM','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/ee4f91d0-9d7b-4e8c-88b7-d50c5e827f222.pdf','2023-02-15 15:36:10.325968'),(19,'VMS','VMS 위촉 임명장.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/ddd063d1-aaab-46be-b4fe-589ccf21e313VMS%20%EC%9C%84%EC%B4%89%20%EC%9E%84%EB%AA%85%EC%9E%A5.pdf','2023-02-15 18:03:49.485798'),(20,'PERFORM','봉사 실적.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/b5c83d24-b9ef-4374-a63b-b6b6af2e4f11%EB%B4%89%EC%82%AC%20%EC%8B%A4%EC%A0%81.pdf','2023-02-15 18:03:49.485805'),(23,'VMS','VMS 위촉 임명장.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/1cbf9385-7e93-423f-9a1e-5472f4201267VMS%20%EC%9C%84%EC%B4%89%20%EC%9E%84%EB%AA%85%EC%9E%A5.pdf','2023-02-15 18:51:53.909144'),(24,'PERFORM','봉사 실적.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/74789455-d2fe-4580-a595-c74ed73268ec%EB%B4%89%EC%82%AC%20%EC%8B%A4%EC%A0%81.pdf','2023-02-15 18:51:53.909151'),(25,'OTHER','애옹.jfif','https://funteer.s3.ap-northeast-2.amazonaws.com/user/77b77bad-22cd-4b83-8a23-c47c47291ff3%EC%95%A0%EC%98%B9.jfif','2023-02-15 19:08:37.370073'),(26,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/1782fdb0-e662-4ae4-b196-32f1ff2a8b39default-profile.png','2023-02-16 01:16:30.497753'),(27,'VMS','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/b0a52b14-d3cd-4692-9736-09b8a3e51c2a2.pdf','2023-02-16 01:16:30.788786'),(28,'PERFORM','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/74f64d00-81fe-44dc-b4a1-5c8043bab2d22.pdf','2023-02-16 01:16:30.788798'),(29,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/cae880df-1273-418c-8c4e-d6997c5d3351default-profile.png','2023-02-16 04:32:48.818372'),(30,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/52a4dd07-40f0-4dff-8f37-dee41ca1fac5default-profile.png','2023-02-16 04:40:26.484505'),(31,'VMS','VMS_위촉_임명장.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/708d5cf2-dea6-4600-9aaa-57891f79a646VMS_%EC%9C%84%EC%B4%89_%EC%9E%84%EB%AA%85%EC%9E%A5.pdf','2023-02-16 04:40:26.680475'),(32,'PERFORM','냥멍봉사단_봉사_실적.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/fa70d582-f097-4b5a-9dbf-79333ba7fedb%EB%83%A5%EB%A9%8D%EB%B4%89%EC%82%AC%EB%8B%A8_%EB%B4%89%EC%82%AC_%EC%8B%A4%EC%A0%81.pdf','2023-02-16 04:40:26.680483'),(33,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/4e1955e8-7184-494e-b467-07fe8c384713default-profile.png','2023-02-16 04:43:06.126883'),(34,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/b3fd1585-da66-428f-aee0-1cb7d532f79adefault-profile.png','2023-02-16 04:44:36.285205'),(35,'VMS','VMS_위촉_임명장.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/7b4cddbf-bd67-4b28-a793-60e6ecf62263VMS_%EC%9C%84%EC%B4%89_%EC%9E%84%EB%AA%85%EC%9E%A5.pdf','2023-02-16 04:44:36.492659'),(36,'PERFORM','봉사_실적.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/3068a420-acf0-41c6-93e0-67b4c7f2bf93%EB%B4%89%EC%82%AC_%EC%8B%A4%EC%A0%81.pdf','2023-02-16 04:44:36.492665'),(37,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/3128f2bf-2f41-4ac1-8f8e-7c0cf8d9a7acdefault-profile.png','2023-02-16 04:50:55.351873'),(38,'VMS','VMS_위촉_임명장.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/367a2437-dffa-428c-8049-497af6d8df68VMS_%EC%9C%84%EC%B4%89_%EC%9E%84%EB%AA%85%EC%9E%A5.pdf','2023-02-16 04:50:55.609947'),(39,'PERFORM','봉사_실적.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/51945773-98c1-4644-b60b-61c6153d4f51%EB%B4%89%EC%82%AC_%EC%8B%A4%EC%A0%81.pdf','2023-02-16 04:50:55.609953'),(40,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/5979253a-5a7f-4c15-bf12-43355cb7b6c0default-profile.png','2023-02-16 04:53:17.468171'),(41,'LIVE','ses_UHAqQTu2Ag.mp4','https://funteer.s3.ap-northeast-2.amazonaws.com/live/9ce4d8a2-4650-4b23-b1d6-f96e71141bf2ses_UHAqQTu2Ag.mp4','2023-02-16 11:48:04.663941'),(42,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/7f3b0203-ed1f-404b-81d7-5ccf88f3e409default-profile.png','2023-02-16 13:27:14.251573'),(43,'VMS','VMS.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/37d6485c-cec4-4efc-895f-ebac29c15666VMS.pdf','2023-02-16 13:27:14.544667'),(44,'PERFORM','봉사실적.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/2bae2767-f7f8-4cf2-af66-bc99e6c11905%EB%B4%89%EC%82%AC%EC%8B%A4%EC%A0%81.pdf','2023-02-16 13:27:14.544680'),(47,'OTHER','기부금 영수증 양식.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/notice/f0e15104-3738-477b-974c-b9d451d74a32%EA%B8%B0%EB%B6%80%EA%B8%88%20%EC%98%81%EC%88%98%EC%A6%9D%20%EC%96%91%EC%8B%9D.pdf','2023-02-16 14:42:47.082938'),(48,'OTHER','시각장애.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/179ac74e-835c-4af5-b477-9e1f12a5307a%EC%8B%9C%EA%B0%81%EC%9E%A5%EC%95%A0.png','2023-02-16 15:15:12.530105'),(49,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/ce66c6ed-6a78-4350-a898-6e3884c84f95default-profile.png','2023-02-16 19:25:07.508455'),(50,'OTHER','펀딩 보고서 양식.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/notice/ccf5a8fd-6530-48f3-b4b3-29d856a9a43a%ED%8E%80%EB%94%A9%20%EB%B3%B4%EA%B3%A0%EC%84%9C%20%EC%96%91%EC%8B%9D.pdf','2023-02-17 00:09:30.413458'),(51,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/23ed07b5-6318-4091-9a25-09768e603821default-profile.png','2023-02-17 00:21:04.024389'),(52,'VMS','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/6fcc38e4-cedc-4df3-81be-49c94eab43ab2.pdf','2023-02-17 00:21:04.344276'),(53,'PERFORM','2.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/a9df6afe-3008-4904-8ba4-fa813fb10ed82.pdf','2023-02-17 00:21:04.344290'),(54,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/c8323ef2-6bb8-4843-bfdd-648fbbbfac77default-profile.png','2023-02-17 00:24:05.163490'),(55,'VMS','VMS.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/5f8c7dfa-f184-497c-b8b8-293131a52d94VMS.pdf','2023-02-17 00:24:05.526868'),(56,'PERFORM','봉사실적.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/09e37469-44ee-49db-b3d3-3ffa473cdf27%EB%B4%89%EC%82%AC%EC%8B%A4%EC%A0%81.pdf','2023-02-17 00:24:05.526879'),(113,'OTHER','얼은.jfif','https://funteer.s3.ap-northeast-2.amazonaws.com/user/115484d2-5d9b-4ee4-9c79-e8629070bea5%EC%96%BC%EC%9D%80.jfif','2023-02-17 01:02:23.068893'),(114,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/29863b9d-9acf-482e-86ea-ee2c5afb2f7edefault-profile.png','2023-02-17 01:07:45.244482'),(115,'VMS','VMS_위촉임명장.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/0173b06b-9ec3-451a-957b-21daba466d1fVMS_%EC%9C%84%EC%B4%89%EC%9E%84%EB%AA%85%EC%9E%A5.pdf','2023-02-17 01:07:45.588346'),(116,'PERFORM','봉사_실적_확인서.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/e52054b0-2ffa-49e5-96f5-39f3cd4ba062%EB%B4%89%EC%82%AC_%EC%8B%A4%EC%A0%81_%ED%99%95%EC%9D%B8%EC%84%9C.pdf','2023-02-17 01:07:45.588380'),(117,'OTHER','default-profile.png','https://funteer.s3.ap-northeast-2.amazonaws.com/user/cce943f3-4730-4782-aa61-4892f7e22714default-profile.png','2023-02-17 01:13:02.366160'),(118,'VMS','VMS_위촉임명장.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/52bafbd1-b0d5-46f8-a9e6-ee680c08f9feVMS_%EC%9C%84%EC%B4%89%EC%9E%84%EB%AA%85%EC%9E%A5.pdf','2023-02-17 01:13:02.603600'),(119,'PERFORM','봉사_실적_확인서.pdf','https://funteer.s3.ap-northeast-2.amazonaws.com/teamFile/e1d2363d-ba52-4085-b80e-81b614801279%EB%B4%89%EC%82%AC_%EC%8B%A4%EC%A0%81_%ED%99%95%EC%9D%B8%EC%84%9C.pdf','2023-02-17 01:13:02.603655');
/*!40000 ALTER TABLE `attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `badge`
--

DROP TABLE IF EXISTS `badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `badge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `badge_img_path` text NOT NULL,
  `post_group` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `badge`
--

LOCK TABLES `badge` WRITE;
/*!40000 ALTER TABLE `badge` DISABLE KEYS */;
INSERT INTO `badge` VALUES (1,'총 누적 금액 1,000,000원','나는야 기부왕 짱짱','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EC%B4%9D+%EB%88%84%EC%A0%81+%EA%B8%88%EC%95%A1+1%2C000%2C000%ED%9A%8D%EB%93%9D.svg',NULL),(2,'펀딩참여 5회 이상','나는야 펀린이','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%ED%8E%80%EB%94%A9%EC%B0%B8%EC%97%AC+5%ED%9A%8C+%EC%9D%B4%EC%83%81.svg','FUNDING'),(3,'펀딩참여 10회 이상','나는야 펀른이','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%ED%8E%80%EB%94%A9%EC%B0%B8%EC%97%AC+10%ED%9A%8C+%EC%9D%B4%EC%83%81.svg','FUNDING'),(4,'펀딩참여 30회 이상','나는야 이 구역 펀딩 대장','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%ED%8E%80%EB%94%A9%EC%B0%B8%EC%97%AC+30%ED%9A%8C+%EC%9D%B4%EC%83%81.svg','FUNDING'),(5,'펀딩참여 금액 5만원 이상','나는야 펀린이2','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%ED%8E%80%EB%94%A9%EC%B0%B8%EC%97%AC+%EA%B8%88%EC%95%A1+5%EB%A7%8C%EC%9B%90+%EC%9D%B4%EC%83%81.svg','FUNDING'),(6,'펀딩참여 금액 10만원 이상','나는야 펀른이2','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%ED%8E%80%EB%94%A9%EC%B0%B8%EC%97%AC+%EA%B8%88%EC%95%A1+10%EB%A7%8C%EC%9B%90+%EC%9D%B4%EC%83%81.svg','FUNDING'),(7,'펀딩참여 금액 30만원 이상','나는야 이 구역 펀딩 대장2','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%ED%8E%80%EB%94%A9%EC%B0%B8%EC%97%AC+%EA%B8%88%EC%95%A1+10%EB%A7%8C%EC%9B%90+%EC%9D%B4%EC%83%81.svg','FUNDING'),(8,'얼리버드가입자 선착 50명','일찍 일어나는 새','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EC%96%BC%EB%A6%AC%EB%B2%84%EB%93%9C+%EA%B0%80%EC%9E%85%EC%9E%90+%EC%84%A0%EC%B0%A9+50%EB%AA%85.svg',NULL),(9,'모금참여 5회 이상','나는야 모린이','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EB%AA%A8%EA%B8%88%EC%B0%B8%EC%97%AC+5%ED%9A%8C+%EC%9D%B4%EC%83%81.svg','DONATION'),(10,'모금참여 10회 이상','나는야 모른이','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EB%AA%A8%EA%B8%88%EC%B0%B8%EC%97%AC+10%ED%9A%8C+%EC%9D%B4%EC%83%81.svg','DONATION'),(11,'모금참여 30회 이상','나는야 이 구역 모금 대장','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EB%AA%A8%EA%B8%88%EC%B0%B8%EC%97%AC+30%ED%9A%8C+%EC%9D%B4%EC%83%81.svg','DONATION'),(12,'모금참여 금액 5만원 이상','나는야 모린이2','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EB%AA%A8%EA%B8%88%EC%B0%B8%EC%97%AC+%EA%B8%88%EC%95%A1+5%EB%A7%8C%EC%9B%90+%EC%9D%B4%EC%83%81.svg','DONATION'),(13,'모금참여 금액 10만원 이상','나는야 모른이2','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EB%AA%A8%EA%B8%88%EC%B0%B8%EC%97%AC+%EA%B8%88%EC%95%A1+10%EB%A7%8C%EC%9B%90+%EC%9D%B4%EC%83%81.svg','DONATION'),(14,'모금참여 금액 30만원 이상','나는야 이 구역 모금 대장2','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EB%AA%A8%EA%B8%88%EC%B0%B8%EC%97%AC+%EA%B8%88%EC%95%A1+10%EB%A7%8C%EC%9B%90+%EC%9D%B4%EC%83%81.svg','DONATION'),(15,'성공펀딩 수 5회','나는야 성공1','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EC%84%B1%EA%B3%B5%ED%8E%80%EB%94%A9+%EC%88%98+5%ED%9A%8C.svg',NULL),(16,'성공펀딩 수 10 회','나는야 성공2','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EC%84%B1%EA%B3%B5%ED%8E%80%EB%94%A9+%EC%88%98+10%ED%9A%8C.svg',NULL),(17,'성공펀딩 수 20회','나는야 성공3','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EC%84%B1%EA%B3%B5%ED%8E%80%EB%94%A9+%EC%88%98+30%ED%9A%8C.svg',NULL),(19,'총 누적 금액 1,000,000 획득','나는야 기부왕 짱짱','https://funteer.s3.ap-northeast-2.amazonaws.com/Badge/%EC%B4%9D+%EB%88%84%EC%A0%81+%EA%B8%88%EC%95%A1+1%2C000%2C000%ED%9A%8D%EB%93%9D.svg',NULL);
/*!40000 ALTER TABLE `badge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'아동'),(2,'노인'),(3,'동물'),(4,'장애인'),(5,'환경');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint DEFAULT NULL,
  `charge_date` datetime(6) DEFAULT NULL,
  `pay_imp_uid` varchar(255) DEFAULT NULL,
  `possible_refund` int NOT NULL,
  `refund_reason` varchar(255) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfrjcbdrpuvqkkjb21giqpugdw` (`member_id`),
  CONSTRAINT `FKfrjcbdrpuvqkkjb21giqpugdw` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge`
--

LOCK TABLES `charge` WRITE;
/*!40000 ALTER TABLE `charge` DISABLE KEYS */;
INSERT INTO `charge` VALUES (1,5000,'2023-02-15 07:03:16.105445','imp_710545079957',1,NULL,15),(2,20000,'2023-02-16 09:19:23.841355','imp_497128963770',1,NULL,2),(3,10000,'2023-02-16 11:43:44.293554','imp_171795269264',1,NULL,2),(4,5000,'2023-02-16 17:53:09.547621','imp_335928567876',1,NULL,24);
/*!40000 ALTER TABLE `charge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `funding_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo17m4oo5g64optmpc6ra4jhsu` (`funding_id`),
  KEY `FKmrrrpi513ssu63i2783jyiv9m` (`member_id`),
  CONSTRAINT `FKmrrrpi513ssu63i2783jyiv9m` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKo17m4oo5g64optmpc6ra4jhsu` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'봉사활동 화이팅이에요','2023-02-15 08:51:31.486096',5,13),(2,'힘내세요 ㅜㅜ','2023-02-15 19:08:21.113722',36,24),(4,'응원합니다.','2023-02-15 19:09:30.745391',1,24),(5,'응원합니다.','2023-02-15 19:09:45.759076',11,24),(6,'응원합니다.','2023-02-15 19:09:57.663122',19,24),(7,'응원합니다 !!!','2023-02-17 00:52:27.101907',11,1),(8,'좋은 취지의 봉사인 것 같아요 !','2023-02-17 00:52:43.507223',9,1),(9,'힘내세요 ㅠㅠ !!!!','2023-02-17 00:52:52.360409',36,1),(10,'저희 할머니가 생각나요 !','2023-02-17 00:53:03.542624',49,1),(11,'여행 꼭 갔으면 좋겠어요 !~~~','2023-02-17 00:53:45.089516',12,1),(12,'팡이 귀엽네요','2023-02-17 00:54:22.578255',54,24),(13,'댕댕이 귀여워요','2023-02-17 00:54:49.757867',48,24),(14,'응원합니다.','2023-02-17 00:55:02.546392',49,24),(15,'응원합니다.','2023-02-17 00:55:23.595518',22,24),(16,'응원합니다.','2023-02-17 00:55:23.692411',22,24),(17,'응원합니다.','2023-02-17 00:55:34.694488',21,24),(18,'응원합니다.','2023-02-17 00:55:41.649469',16,24),(19,'응원합니다.','2023-02-17 00:55:47.707963',15,24),(20,'응원합니다.','2023-02-17 00:55:54.912605',9,24),(21,'응원합니다.','2023-02-17 00:56:05.534230',12,24),(24,'산책하고 있는 강아지가 귀엽네용','2023-02-17 00:56:30.604631',48,1),(25,'응원합니다.','2023-02-17 00:56:45.773522',39,24),(26,'응원합니다','2023-02-17 01:02:06.819973',49,104),(27,'강아지들이 행복해하는 모습이 보기좋아요!','2023-02-17 01:03:35.923821',48,104);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `donation`
--

DROP TABLE IF EXISTS `donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donation` (
  `amount` bigint NOT NULL,
  `current_amount` bigint DEFAULT NULL,
  `donation_id` bigint NOT NULL AUTO_INCREMENT,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mgjw293t5f07ab2btgsafl2kc` (`donation_id`),
  CONSTRAINT `FKag8smc9cfqq7efwdeenejviia` FOREIGN KEY (`id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `donation`
--

LOCK TABLES `donation` WRITE;
/*!40000 ALTER TABLE `donation` DISABLE KEYS */;
INSERT INTO `donation` VALUES (15000000,407200,1,'2023-02-15','2023-02-15',4),(10000000,2056200,2,NULL,'2023-02-15',8);
/*!40000 ALTER TABLE `donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq`
--

DROP TABLE IF EXISTS `faq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faq` (
  `faq_id` bigint NOT NULL AUTO_INCREMENT,
  `group_or_person` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_41q5x9vgj3vs2mwww53ykiews` (`faq_id`),
  CONSTRAINT `FKo7hevaieo28eo92aonxveecb5` FOREIGN KEY (`id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq`
--

LOCK TABLES `faq` WRITE;
/*!40000 ALTER TABLE `faq` DISABLE KEYS */;
INSERT INTO `faq` VALUES (1,0,24),(2,0,25),(3,0,26),(4,1,27),(5,1,28),(6,0,29),(7,1,30),(8,0,31),(9,1,32);
/*!40000 ALTER TABLE `faq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `checked` bit(1) DEFAULT NULL,
  `member_id` bigint NOT NULL,
  `team_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKla8lvflaauks5sw7s0u44q6x0` (`member_id`),
  KEY `FKdbiknqr7dwpru4akh3ilwpdc5` (`team_id`),
  CONSTRAINT `FKdbiknqr7dwpru4akh3ilwpdc5` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKla8lvflaauks5sw7s0u44q6x0` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,_binary '',1,12),(2,_binary '',1,9),(3,_binary '',24,9),(4,_binary '',24,23),(5,_binary '',24,8),(6,_binary '',24,35),(7,_binary '',2,46),(8,_binary '',2,9),(9,_binary '',2,8);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funding`
--

DROP TABLE IF EXISTS `funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funding` (
  `current_funding_amount` bigint DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `funding_description` text,
  `funding_id` bigint NOT NULL AUTO_INCREMENT,
  `reject_comment` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `id` bigint NOT NULL,
  `category_id` bigint DEFAULT NULL,
  `report_id` bigint DEFAULT NULL,
  `team_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ccuydwbybgyj2l0bviv4hseg1` (`funding_id`),
  KEY `FKkbxd35ur7pg4ubyq4j3v3i1fq` (`category_id`),
  KEY `FKltvhux0ojdjfqakrqxsl8dhkp` (`report_id`),
  KEY `FK73jpdnc1hxs40yir0mjcfcmyl` (`team_id`),
  CONSTRAINT `FK73jpdnc1hxs40yir0mjcfcmyl` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKdcup6gumxpl57dqhnyxqwncrf` FOREIGN KEY (`id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKkbxd35ur7pg4ubyq4j3v3i1fq` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKltvhux0ojdjfqakrqxsl8dhkp` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funding`
--

LOCK TABLES `funding` WRITE;
/*!40000 ALTER TABLE `funding` DISABLE KEYS */;
INSERT INTO `funding` VALUES (10000,'2023-03-10','100명의 어린이와 함께 가로수를 보호하려고 합니다.',1,NULL,'2023-02-20',1,5,NULL,9),(0,'2023-03-09','따뜻한 손길이 따뜻한 영양죽이 되어 장애인분들께 큰 도움이 됩니다.',2,NULL,'2023-02-23',2,4,NULL,8),(300012,'2023-02-23','을숙도 공원에서 쓰레기 줍기',3,NULL,'2023-02-17',3,5,NULL,11),(1500266470,'2023-02-16','장애인 복지',4,NULL,'2023-02-10',5,4,NULL,12),(0,'2023-03-15','OFF-ON 교육 콘텐츠 개발',5,NULL,'2023-02-17',9,5,NULL,9),(0,'2023-02-25','1년에 단 한 번뿐인 생일, 누구에게나 특별한 날일까요?',6,NULL,'2023-02-17',10,2,NULL,9),(0,'2023-03-04','지역아동센터, 아동들의 안전한 사랑의 울타리',7,NULL,'2023-02-17',11,1,NULL,9),(0,'2023-02-26','장애인과 가족들의 소중한 추억을 쌓는 데 도움을 주세요',8,NULL,'2023-02-17',12,4,NULL,9),(0,'2023-03-04','병들고 죽어가는 우리의 가로수',9,NULL,'2023-02-17',13,5,NULL,8),(0,'2023-03-09','퐁당퐁당 환경지키미 EM흙공 던지깅',10,NULL,'2023-02-17',14,5,NULL,8),(300,'2023-03-05','키오스크 앞에서 당당해지고 싶어요',11,NULL,'2023-02-17',15,2,NULL,8),(0,'2023-03-15','코로나19로 바뀐 특수교육 대상 아동·청소년들과 가족의 외로운 일상',12,NULL,'2023-02-17',16,1,NULL,8),(0,'2023-02-28','야생동물과의 공존을 위하여',13,NULL,'2023-02-17',17,3,NULL,23),(0,'2023-03-28','동물학대를 방지하고, 학대받는 동물들을 구조합니다',14,NULL,'2023-03-01',18,3,NULL,23),(0,'2023-02-27','버려진 동물들에게 새로운 삶을 제공해주기 위하여 당신의 도움이 필요해요',15,NULL,'2023-02-18',19,3,NULL,23),(0,'2023-05-30','보호소를 표방하는 펫숍들이 아닌 동물과 더불어 살아가는 삶을 만들어요',16,NULL,'2023-04-02',20,3,NULL,23),(0,'2023-02-17','보호받지 못하는 야생동물들을 위하여 인식개선과 현장 모니터링등의 활동을',17,NULL,'2023-02-17',21,3,NULL,23),(0,'2023-03-31','어르신들이 하루하루 아름다운 추억을 품으며 행복할 수 있도록 ...',18,NULL,'2023-02-17',22,2,NULL,9),(0,'2023-02-28','노인 취약계층 대상 주거환경개선 지원이 시급합니다.',19,NULL,'2023-02-17',23,2,NULL,9),(30,'2023-02-23','강원 산불 구호금 펀딩입니다.',20,NULL,'2023-02-17',36,5,NULL,9),(0,'2023-02-23','강원 산불 구호금 펀딩입니다.',21,NULL,'2023-02-16',37,5,NULL,9),(0,'2023-02-23','강원 산불 구호금 펀딩입니다.',22,NULL,'2023-02-16',38,5,NULL,9),(0,'2023-02-23','유기견 들에게 사료를 기부합니다.',23,NULL,'2023-02-16',39,3,NULL,9),(0,'2023-03-15','강아지들의 주거환경 개선 및 산책 활동',24,'test용임당','2023-02-25',41,3,NULL,9),(0,'2023-04-03','강아지들의 주거환경 개선 및 산책 활동',25,NULL,'2023-02-20',42,3,NULL,9),(0,'2023-03-06','강아지들의 주거환경 개선 및 산책 활동',26,'똑같은 거 있음요','2023-02-20',43,3,NULL,9),(500,'2023-02-27','강아지들의 주거환경 개선 및 산책 활동',27,NULL,'2023-02-18',44,3,NULL,9),(0,'2023-03-06','강아지들의 주거환경 개선 및 산책 활동',28,'똑같은 거 있음','2023-02-20',45,3,NULL,9),(0,'2023-02-28','강아지',29,'sdfsdfs','2023-02-24',46,3,NULL,9),(0,'2023-02-28','아이를 임신 중일 때 배우자를 잃었고 아이만을 보며 살고 있습니다.',30,'sfsdfsdf','2023-02-18',47,1,NULL,8),(0,'2023-02-28','강아지들의 주거환경 개선 및 산책 활동',31,NULL,'2023-02-17',48,3,NULL,9),(34,'2023-02-25','생일, 누구와 함께 보내시나요?',32,NULL,'2023-02-17',49,2,NULL,9),(0,'2023-02-18','희망공원의 환경을 미화합니다',33,NULL,'2023-02-17',50,5,NULL,9),(0,'2023-03-12','시각장애인 모두가 점자를 읽을 줄 안다는 색안경!',34,NULL,'2023-02-19',51,4,NULL,35),(0,'2023-03-12','위험을 인지하지 못해요',35,NULL,'2023-03-05',52,4,NULL,35),(2091000,'2023-02-16','우리 아이들에게 팡이와 함께 행복들 전달해주세요!',36,NULL,'2023-02-10',54,1,NULL,35),(0,'2023-02-28','치아 결손으로 식사가 어려운 분들이 많습니다',37,NULL,'2023-02-18',55,4,NULL,9),(0,'2023-03-10','턱없이 부족한 정부 지원, 아토피 어린이에게 보습제를 지원해 주세요.',38,NULL,'2023-02-21',56,1,NULL,9),(0,'2023-03-14',' \"어르신 식사는 하셨어요? 필요하신 건 없으세요?\" ',39,NULL,'2023-02-24',57,4,NULL,9),(4700000,'2023-02-16','쓰레기 대청소로 집도, 마음도 환하게',40,NULL,'2023-02-01',58,2,NULL,46),(0,'2023-03-02','어르신의 세월만큼 낡아버린 주방 가전들',41,NULL,'2023-02-24',59,2,NULL,9);
/*!40000 ALTER TABLE `funding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gift`
--

DROP TABLE IF EXISTS `gift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gift` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint DEFAULT NULL,
  `gift_date` datetime(6) DEFAULT NULL,
  `live_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9f4pap4skt35qdlddf68jw3kh` (`live_id`),
  KEY `FKkc0fi6necdyfsc3hqoskjub5j` (`user_id`),
  CONSTRAINT `FK9f4pap4skt35qdlddf68jw3kh` FOREIGN KEY (`live_id`) REFERENCES `live` (`id`),
  CONSTRAINT `FKkc0fi6necdyfsc3hqoskjub5j` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift`
--

LOCK TABLES `gift` WRITE;
/*!40000 ALTER TABLE `gift` DISABLE KEYS */;
INSERT INTO `gift` VALUES (1,100000,'2023-02-15 06:22:58.556015',1,2),(2,50000,'2023-02-15 06:23:07.181894',1,2),(3,9000000,'2023-02-15 06:23:14.880901',1,2),(4,5000,'2023-02-15 06:23:21.924210',1,2),(5,1232,'2023-02-15 07:09:11.401359',4,15),(6,222222,'2023-02-15 07:09:17.565964',4,15),(7,123123,'2023-02-15 07:09:22.967334',4,15),(8,123123,'2023-02-15 07:09:28.974062',4,15),(9,12323,'2023-02-15 07:09:34.961370',4,15),(10,123123,'2023-02-15 07:09:47.774339',4,15),(11,2323,'2023-02-15 07:09:57.756125',4,15),(12,5000,'2023-02-15 12:11:49.791944',6,2),(15,1000,'2023-02-16 14:53:57.639726',17,2);
/*!40000 ALTER TABLE `gift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hashtag`
--

DROP TABLE IF EXISTS `hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hashtag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hashtag`
--

LOCK TABLES `hashtag` WRITE;
/*!40000 ALTER TABLE `hashtag` DISABLE KEYS */;
INSERT INTO `hashtag` VALUES (1,'tags');
/*!40000 ALTER TABLE `hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (146);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live`
--

DROP TABLE IF EXISTS `live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `end_time` datetime(6) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `attach_id` bigint DEFAULT NULL,
  `funding_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKggafmx98ctyk0ia0ijfwtb48o` (`attach_id`),
  KEY `FK4p5v86nqv45kcycw9gaocs77c` (`funding_id`),
  CONSTRAINT `FK4p5v86nqv45kcycw9gaocs77c` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`),
  CONSTRAINT `FKggafmx98ctyk0ia0ijfwtb48o` FOREIGN KEY (`attach_id`) REFERENCES `attach` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live`
--

LOCK TABLES `live` WRITE;
/*!40000 ALTER TABLE `live` DISABLE KEYS */;
INSERT INTO `live` VALUES (1,'2023-02-17 01:27:05.279967','ses_PE6hvK649i','2023-02-17 01:26:50.614905',6,1),(4,'2023-02-15 07:30:21.026184','ses_Gh6VzG2B6k','2023-02-15 07:08:32.695217',NULL,3),(5,'2023-02-15 07:10:06.781310','ses_HChpdFLtgC','2023-02-15 07:08:32.963376',14,5),(6,'2023-02-15 12:22:40.987407','ses_ANTnJsg3jS','2023-02-15 12:01:00.880517',NULL,2),(7,'2023-02-15 12:46:00.788754','ses_DBNN9xUU9z','2023-02-15 12:45:15.934169',NULL,2),(10,'2023-02-16 09:20:17.200427','ses_Xj539SuSiy','2023-02-16 02:32:55.563035',NULL,13),(11,'2023-02-16 10:58:57.107088','ses_DQSiJtuIc4','2023-02-16 10:58:23.265540',NULL,44),(12,'2023-02-16 11:48:02.443133','ses_UHAqQTu2Ag','2023-02-16 11:46:54.616305',41,12),(13,'2023-02-16 13:02:20.232423','ses_Xqa5bJjza5','2023-02-16 12:59:16.227708',NULL,9),(14,'2023-02-16 13:04:09.668714','ses_T02rkNOVxG','2023-02-16 13:02:20.275955',NULL,10),(15,'2023-02-16 13:04:12.969432','ses_VDbPrPuzHM','2023-02-16 13:04:09.708327',NULL,11),(16,'2023-02-16 13:47:47.954253','ses_O2RlBy1Odx','2023-02-16 13:04:55.715449',NULL,22),(17,'2023-02-16 16:45:59.150090','ses_G23M61QOP0','2023-02-16 14:53:02.509341',NULL,15),(18,'2023-02-17 01:07:17.629195','ses_LS2HAoQnfp','2023-02-17 01:03:11.803502',NULL,58),(19,'2023-02-17 00:56:40.951888','ses_I7sZWgQjpz','2023-02-17 00:50:22.149884',NULL,49),(20,NULL,'ses_YU0pDcGH41','2023-02-17 01:26:08.670152',NULL,54);
/*!40000 ALTER TABLE `live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `display` bit(1) NOT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hh9kg6jti4n1eoiertn2k6qsc` (`nickname`),
  CONSTRAINT `FK4myur39bhn9oj6a9k52t2fmry` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (_binary '\0','김보경',1),(_binary '\0','비니비니빔',2),(_binary '\0','Admin',3),(_binary '','별빛봉사단',4),(_binary '\0','띵수',13),(_binary '\0','배고픈사람',14),(_binary '','방봉빔',15),(_binary '\0','백준봉',24),(_binary '','코치님_개인',38),(_binary '','컨설턴트님_개인',40),(_binary '','컨설턴트님_관리자',43),(_binary '','김김섭섭',45),(_binary '\0','안명수',104);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `notice_id` bigint NOT NULL AUTO_INCREMENT,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i4ufimu1uhcor0oxerxir5ejx` (`notice_id`),
  CONSTRAINT `FK7k5rd7n2o9f342u32pdo3dkob` FOREIGN KEY (`id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,6),(2,33),(3,35),(4,53);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint DEFAULT NULL,
  `pay_date` datetime(6) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf9sjrg0lgq3107csmvu7norq6` (`post_id`),
  KEY `FK4spfnm9si9dowsatcqs5or42i` (`user_id`),
  CONSTRAINT `FK4spfnm9si9dowsatcqs5or42i` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKf9sjrg0lgq3107csmvu7norq6` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,5000,'2023-02-15 07:03:48.417648',5,15),(2,100000,'2023-02-15 07:04:46.266850',5,13),(3,100000,'2023-02-15 07:04:46.331491',3,15),(4,100000,'2023-02-15 07:05:26.300835',3,13),(5,12,'2023-02-15 07:05:56.521594',3,15),(6,100000,'2023-02-15 07:07:24.750964',3,13),(7,700000000,'2023-02-15 07:07:54.110832',5,13),(8,800000000,'2023-02-15 07:08:09.198697',5,13),(9,100000,'2023-02-15 07:10:57.294413',4,15),(10,100000,'2023-02-15 07:10:57.397036',4,15),(11,200000,'2023-02-15 07:11:08.347465',4,15),(12,1000,'2023-02-15 07:11:16.512639',4,13),(13,1200,'2023-02-15 07:11:19.112208',4,15),(14,5000,'2023-02-15 12:27:40.739386',4,2),(15,1000000,'2023-02-15 13:16:40.702724',8,1),(16,20000,'2023-02-15 13:17:30.710918',8,1),(17,100000,'2023-02-15 17:37:06.467131',5,1),(18,2500,'2023-02-16 00:23:11.192686',8,1),(19,100000,'2023-02-16 00:23:16.932490',8,1),(20,3500,'2023-02-16 00:23:32.419249',8,1),(21,10000,'2023-02-16 00:23:54.237825',5,1),(22,10000,'2023-02-16 00:23:57.922493',5,1),(23,10000,'2023-02-16 00:24:00.498236',5,1),(24,10000,'2023-02-16 00:24:03.120910',5,1),(25,10000,'2023-02-16 00:24:06.744625',5,1),(26,10000,'2023-02-16 02:12:51.469794',1,2),(27,10000,'2023-02-16 02:27:21.928678',5,2),(28,800000,'2023-02-16 02:37:06.878343',8,2),(29,500,'2023-02-16 07:34:48.735850',5,2),(30,100,'2023-02-16 08:40:23.751703',5,2),(31,100,'2023-02-16 09:19:41.373967',5,2),(32,500,'2023-02-16 10:57:32.798601',44,2),(33,300,'2023-02-16 11:44:05.975736',5,2),(34,100000,'2023-02-16 11:46:39.212371',8,9),(35,270,'2023-02-16 13:47:21.112732',5,2),(36,27300,'2023-02-16 13:48:58.110815',8,2),(37,1400,'2023-02-16 13:49:30.110802',8,2),(38,300,'2023-02-16 14:35:42.396539',15,2),(39,200,'2023-02-16 14:52:21.975952',5,2),(40,1500,'2023-02-16 14:54:43.559711',8,2),(41,34,'2023-02-16 16:45:17.387839',49,1),(42,0,'2023-02-16 19:25:57.439963',52,45),(43,30,'2023-02-16 23:40:17.833707',36,2),(44,200000,'2023-02-17 01:22:21.028331',58,24),(45,1800000,'2023-02-17 01:22:49.856074',54,24),(46,200000,'2023-02-17 01:24:18.577915',54,24),(47,91000,'2023-02-17 01:33:18.450442',54,2);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` text,
  `hit` int DEFAULT NULL,
  `post_group` varchar(255) NOT NULL,
  `post_type` varchar(255) NOT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `thumbnail` text,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'<h1>병들고 죽어가는 우리의 가로수</h1><p>여러분은 가로수를 잘 살펴본 적이 있나요? 우리가 평소 길을 걷거나 차를 타고 지나가는 대부분의 도로에는 가로수가 있습니다. 가로수는 주로 도시의 미관을 위해 심지만, 가로수 역시 녹색식물이기 때문에 탄소 저감 및 저장 능력이 뛰어나다는 특징을 가지고 있습니다. 그래서 가로수는 도심의 이산화탄소를 제거하는 숨은 영웅과 같은 역할을 하기도 합니다. 하지만 모든 가로수가 건강하게 성장하지는 못합니다. 때로는 병이 들어 노랗게 변색되며 아프고, 바싹 말라 죽기도 하는 가로수. 도심의 가로수는 왜 아플까요?</p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/7fcd7d76-0bf0-4e9d-9308-0ce40ee0e3c8image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><h1><strong>우리의 가로수는 왜 아픈가요?</strong></h1><p>가로수가 죽거나 생육 부진을 겪는 데에는 다양한 원인이 있습니다. 첫 번째, 담배꽁초와 음식물, 독한 성분이 들어 있는 쓰레기들을 내 집 앞, 내 점포, 그리고 쓰레기통에 올바르게 버려야 함에도 불구하고 무단으로 가로수에 투기하고 배출하기 때문입니다. 버려진 쓰레기에서 발생하는 오염물질은 가로수와 가로수의 흙이 고스란히 흡수하게 되지요. 두 번째, 추운 겨울 날씨를 피해 병충해가 가로수의 줄기를 파고 들면 나무가 병들게 됩니다. 병충해는 겨우내 가로수를 갉아먹고 봄이 되면 산란을 통해 개체 수를 늘리는데 이 과정에서 많은 가로수가 피해를 입습니다. 마지막으로 울타리형으로 심어진 가로수를 시민들이 밟아 나무의 생육 환경이 훼손되기 때문입니다. 목적지까지 돌아가는 길에 번거로움을 느낀 이들이 작은 가지형 나무들을 밟아 샛길을 만드는 과정에서 많은 가로수가 성장하지 못하고 죽어갑니다.</p><h1><strong>꼬물꼬물 가로수 지킴이를 응원해 주세요!</strong></h1><p>환경실천연합회는 100명의 어린이와 함께 가로수를 보호하려고 합니다. 어린이들은 이론 교육과 체험 교육, 현장 교육 이렇게 총 3가지 활동으로 가로수 보호에 참여하게 되는데요. 이론 교육에서는 어린이들이 도심 속의 생태계와 가로수의 중요성, 환경보호의 필요성에 대해 배우게 됩니다. 이후 쓸모가 없어 버려지는 산업 폐기물인 양말목과 쓸모를 잃고 버려지는 폐목재를 활용하여 가로수의 옷과 팻말을 만드는 체험 교육에 참여합니다. 마지막으로 완성된 옷과 팻말을 가로수에게 직접 입히고 설치하기 위해 가로수를 방문하는 현장 교육으로 마무리됩니다. 이번 활동을 통해서 약 1,000여 그루의 가로수를 보호하게 됩니다. 어린이들이 양말목을 활용해서 꼬물꼬물 만든 옷은 겨울 동안 가로수의 생장점을 보호하고 병충해를 막아주는 역할을 하고, 알록달록 그림을 그려 넣은 팻말은 시민들이 담배꽁초나 쓰레기를 가로수에 무단으로 배출하지 않고 가로수를 밟지 않도록 막아주는 역할을 할 수 있습니다. 또한 가로수의 원활한 생육 환경이 조성되어 사업 이후에는 더 쑥쑥 자란 가로수를 볼 수 있게 될 것입니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/c9c2ab5c-f903-4104-ad3f-fed3d6bd04a9image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/b106681c-35a4-4e21-a35a-9b740275cc09image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><h1>프로젝트 일정</h1><p>- 3월 15일 봉사 활동 실행 예정</p>',42,'FUNDING','FUNDING_ACCEPT','2023-02-15 06:16:41.413579','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F7929a5df-bba6-42f3-ac85-423e3ff6a9a5691_946_3744.jpg','꼬물꼬물 도시의 나무 지킴이를 응원해 주세요!'),(2,'<h1>프로젝트 소개</h1><h5><strong>치아 결손으로 식사가 어려운 분들이 많습니다.</strong></h5><p><br></p><p>\"씹는 게 불편하니 끼니를 거르게 되고, 빈 속에 약을 먹게 돼.\"</p><p>\"맛있는 음식을 먹어본지가 언제 적인지 기억이 나지도 않아...\"</p><p><br></p><p>치아 결손으로 약 복용을 필수적으로 해야 하지만 식사가 불편해  끼니를 거르게 되고 빈 속에 약을 챙겨멱는 장애인분들이 계십니다. 치아가 불편해 인간의 기본적 욕구 중 하나인 식욕을 채우기에 힘든 상황인 장애인분들도 계십니다. 기본적인 끼니 해결이 어려운, 주변에 도움이 필요한 분들이 참 많습니다.</p><p><br></p><p>여러분들의 따뜻한 손길이 따뜻한 영양죽이 되어 장애인분들에게 큰 도움이 될 수 있습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/9140798b-273f-495a-b42e-06ee11bf0931image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/4dc4734d-4a23-4f1f-8fa4-a13221823d10image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h5>장애인 가정 및 독거세대인 분들은 매 끼니를 챙기는 것이 어렵습니다.</h5><p><br></p><p>치매에 걸린 남편, 그리고 일상생활에 도움이 필요한 성인이 된 중증 장애인 2명과 함께 생활하고 있는 장애인 세대가 있습니다. 기초생활수급자로 정부에서 일정의 도움을 받고 있지만, 비장애인 어머니 혼자서 온 가족의 매 끼니를 해결하기에는 어려움이 많은 것이 현실입니다.</p><p><br></p><p>가족들과 연락을 하지 않고 지내온 세월이 수 십년인 독거 장애인이 있습니다. 거동이 불편하여 매 끼니 따뜻한 밥을 차려먹기는 어렵고, 반찬을 사러 나가기도 힘이 듭니다. 대충 인스턴트식과 라면으로 끼니를 해결하고 있지만 영양을 보충하기에는 턱 없이 부족한 음식입니다.</p><p><br></p><p>여러분의 따뜻한 손길이 맛있는 밑반찬이 되어 장애인분들에게 큰 도움이 될 수 있습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/a69a9f82-8968-40e3-87ad-d7cb21b207a6image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><h1>장애인분들의 건강 회복을 위해 행복한 밥상을 선물해 주세요</h1><p>저희는 장애인분들과 그 가족의 행복, 그리고 지역사회의 변화를 위한 종합적인 장애인복지를 실천하고자 노력하고 있습니다.</p><p><br></p><p>장애인분들에게 일상 속에서 더 나은 환경을 함꼐 만들어 드리기 위해 치아 결손을 식사 지원이 필요한 장애인 세대에는 영양죽을, 건강한 끼니 해결을 위해 반찬 지원이 필요한 장애인가족 및 독거 장애인에게는 밑반찬도시락을 지원해 드리고자 합니다. </p>',8,'FUNDING','FUNDING_ACCEPT','2023-02-15 06:33:56.816251','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2Fe05bb10b-889b-46fe-8d4e-fd655d95ae75%EC%9E%A5%EC%95%A0%EC%9D%B8%EB%B3%B5%EC%A7%80.png','장애인분들의 건강을 위한 행복한 밥상을 선물해주세요'),(3,'<h1>프로젝트 소개</h1><p>프로젝트를 간단히 소개한다면?</p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/d5f67d58-3af8-4d97-9860-1fbe405d78c9%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202023-02-15%20031409.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p>이 프로젝트를 하면 어떤 효과를 발생시키나요?</p><p>이 프로젝트를 시작하게 된 배경이 무엇인가요 ?</p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>구체적인 항목으로 적어주세요.</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>0월 0일: 봉사활동 계획</p></li><li><p>0월 0일: 봉사활동 실행</p></li></ul>',20,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 06:59:08.724227','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2Fd6b96b13-0985-4d3f-8271-bbdfcc1881f2medal.png','함께 플로깅 해요 ~'),(4,'<h5>튀르키예와 시리아를 위해 여러분의 마음을 전해주세요.</h5><h5>튀르키예와 시리아를 위해 여러분의 마음을 전해주세요.</h5><h5><br></h5><h5>튀르키예와 시리아를 위해 여러분의 마음을 전해주세요.</h5><h5>튀르키예와 시리아를 위해 여러분의 마음을 전해주세요.</h5>',NULL,'DONATION','DONATION_CLOSE',NULL,NULL,'튀르키예-시리아 긴급 모금'),(5,'<h1>프로젝트 소개</h1><p>프로젝트를 간단히 소개한다면?</p><p>이 프로젝트를 하면 어떤 효과를 발생시키나요?</p><p>이 프로젝트를 시작하게 된 배경이 무엇인가요 ?</p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>구체적인 항목으로 적어주세요.</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>0월 0일: 봉사활동 계획</p></li><li><p>0월 0일: 봉사활동 실행</p></li></ul><p><br></p>',45,'FUNDING','FUNDING_COMPLETE','2023-02-10 07:00:40.455355','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F1d64775b-df54-4ef5-9cfc-b057738b5caf%EC%9E%A5%EC%95%A0%EC%9D%B8%EB%B3%B5%EC%A7%80.png','장애인 복지'),(6,'<h5>아래 첨부파일의 안내 사항을 참고하시고, 봉사 활동 종료 후 펀딩 상세 페이지에서 보고서를 작성해주시기 바랍니다.</h5><p><br></p><h5>감사합니다.</h5>',NULL,'ETC','NOTICE','2023-02-17 00:09:30.129536',NULL,'펀딩 보고서 안내문입니다.'),(7,'빨리 열렸으면 좋겠네요\r\n열린다면 바로 기부 참여하겠습니다.',NULL,'ETC','QNA','2023-02-15 13:03:43.453856',NULL,'자체기부는 언제 열리는 건가요?'),(8,'<p>지난 2월 6일 월요일, 새벽 4시 17분 규모 7.8의 대지진이 튀르키예 남부와 시리아 서북부를 뒤흔들었습니다. 해당 지진에 대해 지진학자들은 튀르키예에서 관측 기록된 지진 중 가장 큰 규모였다고 말합니다. 강진 이후에도 규모 4.0이 넘는 여진이 최소 100차례 이상 발생했고, 그로부터 12시간 뒤에는 규모 7.5의 지진이 또다시 발생했습니다. </p><p>지진 피해가 가장 큰 지역에도 많은 참전용사 분들과 가족들이 살고 계십니다. 저희 펀티어는 피해지역에 긴급하게 필요한 생필품과 식료품 등을 전달하고 참전용사 공간복지 지원금을 지원할 계획입니다. 하루아침에 사라져버린 집과 두려움 속에 떨고있는 시리아와 튀르키예 주민들에게 따뜻한 관심과 응원 부탁드립니다.</p>',NULL,'DONATION','DONATION_ACTIVE',NULL,NULL,'튀르키예-시리아긴급모금'),(9,'<h1>어느새 개관 14년을 맞은 광덕산환경교육센터</h1><p>(사)광덕산환경교육센터는 충청남도 천안 광덕산 자락에 위치하며, 2009년 비영리민간단체인 천안아산환경운동연합이 지속가능한녹색도시 운동을 위해 500여 명의 시민, 행정, 기업의 참여로 건립한 첫 종합형환경교육 전문기관입니다. 센터는 2009년 개관 이후 자연을 가르치는 일에 헌신적으로 앞장서 왔습니다. 그러나 개관 14년을 맞아 자연과 어우러진 공간과 시설들이 점점 세월을 지탱하기 어려워졌습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/f183446e-dd8d-407b-bf5c-3a2beaa14d0a%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202023-02-15%20223332.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h2><strong>OFF-ON 교육 콘텐츠 개발</strong></h2><p>현재 센터는 개관 14년을 맞아 낡고 기능을 상실한 공간(시설)과 콘텐츠를 개조하여 ‘생태전환 지구시민교육장’으로 거듭나고자 대수선 사업을 추진 중입니다. 본 제안은 대수선 사업의 일환으로 ㉠센터가 위치한 광덕산의 자연 공간(수변, 숲, 논과 밭)과 야생동식물(고라니, 청설모, 삵, 조류 등)을 실시간 관찰 카메라를 설치 운영하여 온라인으로 서비스하고, ㉡소규모 비대면 센터 방문자들을 위한 주요 야외 교육 공간별 QR 콘텐츠를 활용한 셀프 해설 프로그램을 위한 개발, 적용하고자 합니다.</p><p><br></p><h1>주요내용 및 추진방법</h1><p><br></p><p>1. 광덕산 생태 모니터링 센터는 수많은 동식물이 살고있는 교육장소인 \'숲\'으로 둘러쌓여 있습니다. 하지만 이곳의 생태환경을 시간과 장소에 구애받지 않고 모니터링을 할 수 있는 장치가 없습니다. 시민들에게 광덕산 생태계를 알리고 SNS와 유튜브 채널 컨텐츠를 활성화하기 위해 무인센서 카메라를 설치하려고 합니다. 낮, 야간 상관없이 고화질 촬영이 가능한 기종을 설치할 거예요. 카메라는 동식물이 자주 관찰되는 장소에 둘 거예요. 주 1회에 촬영된 클립본을 정리해서 편집한 뒤 유튜브에 업로드할 계획입니다. 이 영상들은 센터 방문객을 대상으로 더욱 재밌고 생생한 교육 자료가 될 것이라 기대합니다. ※ 무인센서 카메라와 관련한 개인정보보호의 문제는 - 산 주인에게 사전에 녹화 관련 허가를 요청한다. - 촬영 관련한 안내정보가 충분히 기재된 팻말을 설치한다. (촬영목적, 촬영범위, 관리처 및 연락처) - 카메라 시야는 사람 무릎 높이로 각도를 설정한다. 차량이나 사람 통행이 잦은 길이 아닌 가능한 야생동물이 지나다닐 법한 외진 곳에 설치한다. - 녹화본은 같이가치 사업 담당자 및 대표만 열람 가능하며 제3자 정보(차량, 얼굴, 목소리, 행동 등)등이 녹화된 부분이 있을 경우 삭제한다. 제3자 정보가 담긴 영상의 백업 및 제3자 공유 역시 철저히 금지한다. - 영상 제작을 위한 편집 과정에서도 제3자 정보(차량, 얼굴, 목소리, 행동 등)등이 담길 경우 여지가 남지 않도록 모자이크(또는 음성변조) 처리하거나 완전히 잘라낸다. 의 계획을 통해서 개인정보보호가 잘 이루어질 수 있도록 하겠습니다.</p><p><br></p><p>2. QR코드 소규모 셀프 투어 구축 센터를 궁금해 하는 소규모 방문자들도 부담없이 시설을 둘러볼 수 있는 셀프 투어를 구축하려고 합니다. 현재 견학 목적의 방문객 문의가 조금씩 증가하고 있어 직원의 안내가 필요하나 일정을 조율하기가 매우 빠듯한 상황입니다. 또한 소규모가 아닌 단체 방문 비중이 높습니다. 혼자거나, 둘이어도 편안한 마음으로 센터를 들러주셨으면 하는 마음이 있습니다. 소규모 셀프 투어를 위해서 우선 시설 곳곳에 QR코드를 설치가 필요합니다. 비나 눈에 영향을 받지 않는 내수성을 지닌 재질로 QR코드 판넬을 제작합니다. 오감(五感)을 느낄 수 있는 장소 위주로 설치해서 방문객들이 센터 주위의 환경을 각인하고 스스로 깨닫는 생태교육이 이루어지도록 유도할 거예요. QR코드와 연동할 수 있는 컨텐츠도 제작합니다. 센터 시설 설명은 음성, 생태와 관련해서는 사진과 동영상을 위주로 구성됩니다. 모든 컨텐츠는 홈페이지 ‘생태 자료실’ 업로드하여 QR코드 확인 시 홈페이지로 이동할 수 있도록 합니다. 여러분의 참여로 지역 중심의 비대면 환경교육 컨텐츠가 잘 만들어질 수 있도록 많은 관심 부탁드립니다!</p><p><br></p><p><br></p>',16,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 13:38:54.888326','','지역 중심의 비대면 환경교육 컨텐츠 만들기'),(10,'<p>모든 사람에게 일 년에 한 번씩 돌아오는 특별한 날, 바로 \'생일\'입니다. 하지만 홀로 살고 있는 어르신들에게 생일은 특별한 날이 아닌 그저 \'평범한 날\'이기만 합니다. 가족들과 떨어져 지내거나 왕래 없이 홀로 살고 있는 독거 어르신들은 생일을 맞이해도 축하해 줄 사람이 없습니다. 독거 어르신들에게 생신은 어느 때와 다를 것 없는 평범한 하루가 되어버렸습니다. 어쩌면 더욱 쓸쓸하고, 외로운 날이기도 합니다. “이제는 생일이 언제인지도 잊고 산다.” “생일을 챙겨본지도 오래 되었어.” 생일을 챙겨 본지가 언제인지도 기억이 나지 않을 만큼 혼자 보내는 생일이 익숙해 졌습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/8780a29a-3685-4e1b-8b93-620fce35a254image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p>생신 축하합니다~ 사랑하는 어르신, 생신 축하합니다♩♪♬</p><p>이번 생일도 홀로 지내셔야 할 어르신들을 위해 특별한 생신파티를 준비하려고 합니다. 케이크에 촛불을 켜고 노래도 불러드리고 생신 선물도 드리며, 어르신들의 생신날이 평범한 날이 아닌 특별한 하루로 기억될 수 있도록 어르신들에게 추억을 만들어 드리고 싶습니다. 어르신들의 작은 행복에 힘을 보탤 수 있도록, 어르신들의 소외감을 덜어드릴 수 있도록, 단 한 번뿐인 생일을 평범한 날이 아닌 특별한 날로 기억될 수 있도록 말입니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/f1c8da6f-1832-4024-bc09-4e713ee1e553image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p>특별한 생신날을 만들어 드릴 수 있도록 힘을 보태주세요!</p><p>더 이상 어르신들께서 홀로 생신을 보내시지 않도록 생신잔치를 위한 모금을 진행하고자 합니다. 중구노인복지관에서는 모금된 기부금을 통해 총 16명을 대상으로 매달 해당 생신 대상자에게 소중한 추억을 선물해 드리고 싶습니다. 각 가정을 방문하여 케이크와 생신 선물을 전달해 드리고 생신날 찍은 사진을 인화하여 액자로 제작해 선물해 드리고자 합니다. 후원자님의 따뜻한 마음으로 어르신들의 생신이 평범한 날이 아닌, \'특별한 하루\'로 기억될 수 있도록 함께해 주세요. 어르신을 위한 생일 노래가 울려 퍼질 수 있도록 따뜻한 손길을 건네주세요!</p>',15,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 14:56:10.602649','','어르신들의 생신이 특별한 날로 기억될 수 있도록'),(11,'<p>지역아동센터, 아동들의 안전한 사랑의 울타리</p><p>2011년 3월 송정 한 모퉁이에 지역아동센터를 개소하였습니다. 처음 시작은 개인시설로 시작해 10여 년을 넘게 아이들과 함께 뒹굴며 아동들의 보금자리로 지냈습니다. 지속적인 월세 인상으로 인해 센터를 이전하였지만, 월세 부담은 여전히 센터의 과제로 남아 있습니다. 작년 6월 우리 센터는 개인시설에서 사회적협동조합으로 전환하여 운영 중에 있습니다. 그럼에도 불구하고 임대료는 법인에서 해결해주지 못하기에, 여전히 센터장이 또는 자체 후원금으로 조달하고 있습니다. 지역아동센터가 법제화 된 이후 20살 청년이 된 지역아동센터는 이제야 조금씩 나아져, 몇몇 지역에 국한되지만 사회복지사들의 처우가 조금은 안정을 찾아가고 있습니다. 그러나 아직도 센터의 월세는 운영비나 보조금으로 해결할 수 없는 현실입니다. 우리 송정지역아동센터는 일반 주택 2층을 임대하여 아동들과 함께 학습도 하고 놀이 지도도 하며, 때로는 야외 체험학습도 하면서 아름다운 공동체를 이루어가고 있습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/c8befa9c-edff-402a-8ac3-e4fda5ae1ee7image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/1d1b2f70-f6d2-40d7-a549-d0d7b41b0174image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p>꿈과 희망을 주는 지역아동센터가 되기를</p><p>해운대구에 속해있는 송정동은 여러모로 열악한 것이 많습니다. 학교도 송정초등학교 하나, 중학교, 고등학교는 아예 없습니다. 우선돌봄 아동을 돌보는 아동센터도 유일하게 저희 송정지역아동센터 하나밖에 없습니다. 힘겨운 지역에서 우리 센터는 꿈과 희망을 주는 사랑의 공동체를 이루어 가고자 노력하고 있습니다. 작년에는 그동안 코로나로 야외활동을 진행하지 못하다 3년만에 워터파크에서 1일 캠프도 진행할 수 있었습니다. 아이들이 얼마나 좋아하던지요. 올해도 캠프로 어디를 가고 싶은지 물어보니, 역시나 워터파크라고 대답하는 우리 아이들에게 안정된 센터를 만들어 주고 싶습니다. 아동들에게 꿈과 희망을 선물하는 지역아동센터가 될 수 있도록 힘찬 응원과 후원을 부탁 드립니다. 아이들이 즐거워야 저희 교사들도 즐겁고 힘이 납니다. 긴 글 읽어주셔서 감사합니다.</p>',16,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 15:08:29.649028','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F5f6221c8-c876-46b9-b902-10cba1e4fdbc%EC%95%84%EB%8F%99%EC%84%BC%ED%84%B0.png','센터 아동들의 안락한 생활을 위해 월세를 지원해 주세요'),(12,'<p>장애인에게 너무나 높은 여행장벽</p><p>한국장애인단체총연맹과 장애인삶 패널조사(2021년, 5,008명 장애인 대상) 결과에 따르면 지난해 장애인의 여행 경험은 ‘여행 다녀온 적 없음’이 86.5%로 나타났으며, 이중 국내 여행을 다녀온 적이 있는 장애인은 12.6%, 해외여행은 1.5%에 불과했습니다. 장애인의 여행경험이 낮은 이유로는 ‘경비 부족’을 비롯해 이동의 접근성 문제, 숙박문제 등의 다양한 장벽이 존재하기 때문이었습니다. 최근 “장애인 관광은 소외된 영역”이라며 “적극적인 제도 도입을 위한 컨트롤타워가 마련돼야 한다”고 강조하며, 컨트롤타워 설치로 지속가능한 정책과 환경을 만들어 장애인의 관광 욕구를 충족하고, 이를 뒷받침할 수 있는 법적 근거로 ‘장애인문화체육관광진흥법’제정을 병행돼야 한다는 목소리를 높이고 있습니다. 이처럼 변화하려는 복지 정책이 반가움에 불구하고 여전히 너무나 높은 여행 장벽에 부딪히고 있는 장애인과 그 가족들이 있습니다.</p><p><br></p><p>사랑하는 가족과 함께하는 생애 첫 가족여행을 꿈꿔요</p><p>최00(뇌병변장애)씨의 소원은 가족들과 함께 여행을 가는 것입니다. ‘TV에서 제주도 여행을 떠나는 가족을 보았어요 너무 가보고 싶고 부러웠어요. 그래서 엄마랑 약속했어요. 다음에는 우리도 여행을 가자고’ 최00씨는 뇌병변장애로 인해 휠체어 없이는 이동이 불가능하며, 가족의 도움 없이는 외출할 수가 없습니다. 지체장애를 가진 아버지는 떨어져 살고 있고, 어머니는 희귀난치병 베체트병(만성 염증성 질환)을 앓고 있음에도 가족생계의 어려움이 걱정되어 치료를 받으며 일을 하고 있습니다. 더욱이 중학생 동생은 류머티스관절염으로 인해 어머니의 손길이 매번 닿을 수 밖에 없습니다. 이 모든 상황속에서 어머니 혼자 모든 식구를 케어 하는 것이 버겁기만 합니다. 현실이란 벽은 가족여행을 꾸리는 계획조차 할 수 없는 상황입니다. 최00씨의 어머니는 ‘우리 딸과 약속했어요. 경제적으로 아주 어렵지만 딸 소원을 이룰 수 있도록, 다음에는 무슨 일이 있더라도 꼭 같이 여행을 갈 거예요’</p><ul><li><p><br></p></li><li><p><br></p></li><li><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/7707c87b-046e-4e14-80a0-293ebd060941image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p></li></ul><p><br></p><p>마음모아 함께 응원부탁드려요~!</p><p>비행의 여정을 우리 기관이 돕고자 합니다. 코로나 팬데믹 상황에서 발달장애인 ․ 뇌병변장애인 자녀를 둔 부모님들은 더욱 많은 양육의 부담을 안고 자녀들을 돌보고 있습니다. 답답한 현실에서 가슴을 열고 밖으로 나가고 싶은 장애인과 그 가족의 욕구를 충족시키고자 하며, 멋진 가족여행을 선사해주고 싶은 마음이 큽니다. 장애인 가족의 미소를 찾아주세요! 장애인 가족이 마음껏 날아오르는 비행을 선물해주세요!</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/f7d460f7-f8d5-48d0-9d1e-0277b23a69f4image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p>',11,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 15:14:28.944967','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F2a3c8418-37db-4113-9a2b-c56e09298a35%EC%9E%A5%EC%95%A0%EC%9D%B8%EB%B3%B5%EC%A7%80.png','우리가족의 \'비\'상한 여\'행\' (비행)'),(13,'<p>병들고 죽어가는 우리의 가로수</p><p>여러분은 가로수를 잘 살펴본 적이 있나요? 우리가 평소 길을 걷거나 차를 타고 지나가는 대부분의 도로에는 가로수가 있습니다. 가로수는 주로 도시의 미관을 위해 심지만, 가로수 역시 녹색식물이기 때문에 탄소 저감 및 저장 능력이 뛰어나다는 특징을 가지고 있습니다. 그래서 가로수는 도심의 이산화탄소를 제거하는 숨은 영웅과 같은 역할을 하기도 합니다. 하지만 모든 가로수가 건강하게 성장하지는 못합니다. 때로는 병이 들어 노랗게 변색되며 아프고, 바싹 말라 죽기도 하는 가로수. 도심의 가로수는 왜 아플까요?</p><p><br></p><p>우리의 가로수는 왜 아픈가요?</p><p>가로수가 죽거나 생육 부진을 겪는 데에는 다양한 원인이 있습니다. 첫 번째, 담배꽁초와 음식물, 독한 성분이 들어 있는 쓰레기들을 내 집 앞, 내 점포, 그리고 쓰레기통에 올바르게 버려야 함에도 불구하고 무단으로 가로수에 투기하고 배출하기 때문입니다. 버려진 쓰레기에서 발생하는 오염물질은 가로수와 가로수의 흙이 고스란히 흡수하게 되지요. 두 번째, 추운 겨울 날씨를 피해 병충해가 가로수의 줄기를 파고 들면 나무가 병들게 됩니다. 병충해는 겨우내 가로수를 갉아먹고 봄이 되면 산란을 통해 개체 수를 늘리는데 이 과정에서 많은 가로수가 피해를 입습니다. 마지막으로 울타리형으로 심어진 가로수를 시민들이 밟아 나무의 생육 환경이 훼손되기 때문입니다. 목적지까지 돌아가는 길에 번거로움을 느낀 이들이 작은 가지형 나무들을 밟아 샛길을 만드는 과정에서 많은 가로수가 성장하지 못하고 죽어갑니다.</p><p>꼬물꼬물 가로수 지킴이를 응원해 주세요!</p><p>환경실천연합회는 100명의 어린이와 함께 가로수를 보호하려고 합니다. 어린이들은 이론 교육과 체험 교육, 현장 교육 이렇게 총 3가지 활동으로 가로수 보호에 참여하게 되는데요. 이론 교육에서는 어린이들이 도심 속의 생태계와 가로수의 중요성, 환경보호의 필요성에 대해 배우게 됩니다. 이후 쓸모가 없어 버려지는 산업 폐기물인 양말목과 쓸모를 잃고 버려지는 폐목재를 활용하여 가로수의 옷과 팻말을 만드는 체험 교육에 참여합니다. 마지막으로 완성된 옷과 팻말을 가로수에게 직접 입히고 설치하기 위해 가로수를 방문하는 현장 교육으로 마무리됩니다. 이번 활동을 통해서 약 1,000여 그루의 가로수를 보호하게 됩니다. 어린이들이 양말목을 활용해서 꼬물꼬물 만든 옷은 겨울 동안 가로수의 생장점을 보호하고 병충해를 막아주는 역할을 하고, 알록달록 그림을 그려 넣은 팻말은 시민들이 담배꽁초나 쓰레기를 가로수에 무단으로 배출하지 않고 가로수를 밟지 않도록 막아주는 역할을 할 수 있습니다. 또한 가로수의 원활한 생육 환경이 조성되어 사업 이후에는 더 쑥쑥 자란 가로수를 볼 수 있게 될 것입니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/e27a47f2-69df-4392-ae76-b55eec85aedcimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/bef5fa4e-ca27-4f9c-9341-19e3c03db532image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p>',4,'FUNDING','FUNDING_WAIT','2023-02-15 15:23:12.923068','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F9a9d6ecb-e327-488d-9f5f-0acb3c3782fb%EC%95%84%EB%8F%99%EC%84%BC%ED%84%B0.png','꼬물꼬물 도시의 나무 지킴이를 응원해 주세요!'),(14,'<p>색깔을 잃고 있는 지역 하천</p><p>지친 사람들의 쉴 곳, 동식물들의 생명이 피어나는 곳 등 다양한 모습으로 우리 곁에 있으며 사계절의 변화를 보여주는 곳. 바로 \'지역 하천\'입니다. 날마다 새로운 얼굴을 보여주는 지역 하천이지만, 지난해 장마로 하천이 범람한 후 보여진 하천의 속은 우울하기만 했습니다. 무분별한 쓰레기 투척으로 몸살을 앓고 있었고, 푸르른 강과 녹음(綠陰)의 색을 잃어가고 있었습니다. 그러나 하천의 원래 색깔을 기억하고, 기대하고 있는 평범한 시민들이 있습니다. 그들이 모여 쓰레기와 오염으로 가득한 지역 하천의 원래 색깔을 찾아주고자 노력하고 있습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/b964f4cb-4672-4e8d-9e2b-e7d8044fa04cimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p>집 나간 쓰레기 찾아요!</p><p>집 나간 쓰레기를 찾습니다. 쓰레기가 있어야 할 곳은 쓰레기통이죠. 아직도 집을 찾지 못하고 아무렇게나 버려진 쓰레기들을 찾습니다! 쾌적한 공기를 내뿜을 지역 하천의 산책로를 위해, 물고기의 이동 통로인 깨끗한 천(川)을 위해 봉사자들과 함께 걸으면서 쓰레기를 모으려 합니다. 저희가 걸어갔던 길은 자연인 척 숨어 있던 쓰레기 하나 없는 길가가 될 것입니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/287ddef4-0242-40be-a003-7990383add92image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p>튕겨지지 않는 이상한 공, 흙공</p><p>야구공, 축구공, 농구공 등 우리가 알고 있는 동글동글한 공들은 통통, 튕겨집니다. 하지만 여기에 튕겨지지 않는 이상한 공이 있습니다. 바로 ‘흙공’이라 불리는 공입니다. 흙공은 EM용액과 황토를 배합하여 만들어진 것인데, EM이란 ‘유용한 미생물’이라는 뜻입니다. 이렇게 만들어진 흙공을 하천에 던지면 흙 속에 있던 미생물들이 분해되어 수질이 정화되고 우리의 코를 막게 한 악취가 점차 사라지게 됩니다. 오염으로 가득했던 지역 하천이 햇빛에 비치는 반짝이는 물결로 바뀔 것입니다.</p><p><br></p><p>우리의 노력이 필요합니다</p><p>이 모든 건 한 번의 걸음과 손짓이 아닌 지속적인 행동이 필요합니다. 사람들의 발길이 늘어나는 여름, 우리는 EM흙공 5,000개를 제작하여, 지역 하천의 회복을 위해 한 번 더 움직이기로 하였습니다. 날이 더워지면 시원한 곳을 찾아 밖으로 나오는 사람이 많아지는 것을 대비하여 현수막을 제작해 메시지를 전달하고, 시민들과 하천 주변을 걸으며 플로깅을 진행합니다. 그리고 하천을 향해 EM흙공을 던져 하천이 스스로 아름다움을 유지할 수 있도록 돕습니다. 우리 아이들이 깨끗한 환경에서 살아가기를 희망하는 분들, 지역 하천의 회복과 나아가 전세계 녹색 환경을 기대하시는 분들의 많은 관심과 동참을 부탁드립니다.</p>',6,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 15:27:05.646996','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F31755fc3-583e-40a2-8ab7-f0c712a133af%EC%95%84%EB%8F%99%EC%84%BC%ED%84%B0.png','퐁당퐁당 환경지키미 EM흙공 던지깅'),(15,'<p>\"키오스크 앞에서 당당해지고 싶어요.\"</p><p>이진순 할머니(71세)는 \"기계(키오스크)앞에만 서면 심장이 두근거리고 눈앞이 깜깜해져.\" 라고 말씀하시며 긴 한 숨을 내쉽니다. 평소 햄버거 매장 앞을 지나갈 때마다 창 밖으로 보이는 젊은이들의 햄버거 먹는 모습이 너무 맛있어 보여 큰 맘 먹고 가게로 들어가 키오스크 앞에 섰습니다. 그러나 한동안 기계 앞에서 쩔쩔매다 결국 아무것도 주문하지 못한 채 발걸음을 돌려야만 했습니다. 메뉴 선택 항목이 너무 많고 버튼을 잘못 눌러 몇 번이고 첫 화면으로 되돌아가 당황스럽고 창피한 데다, 뒤에 늘어선 줄을 보니 마치 본인이 피해를 주는 것 같은 미안함에 자리를 비켜준 것입니다. 그 이후로는 매장 밖에서 보고 키오스크가 있으면 아무리 먹고 싶은 것이어도 들어가지 않는다고 하시는데요. 부산광역시 수영구에 위치한 \'부산문해교육협회\'는 60대에서 80대의 어르신 약 100여 명 정도가 회원으로 계십니다. 이 분들이 엘리베이터도 없는 낡은 건물 3층을 매일 다리 두드리며 올라오시는 이유는 바로 \'여자이기 때문에 못 배운 한\'과 \'그 옛날 매일이 고되고, 가난한 삶으로 인해 공부를 할 수 없었지만 이제라도 배울 수 있다\'는 공부에 대한 열망 때문입니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/ca5a1076-98ef-480e-8395-321eb30212baimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p>편리한 디지털 세상, \"모두에게 편리한 거 맞습니까?\"</p><p>코로나19는 우리의 소비 문화에 급격한 변화를 가져왔습니다. 이러한 변화는 무인화, 자동화와 함께 스마트폰의 보편화를 넘어 각종 온라인 예매, 무인 편의점과 키오스크 주문 등 디지털 환경을 모르면 아무것도 할 수 없는 결과를 가져왔습니다. 손바닥 만한 핸드폰 하나로 모든 것을 이룰 수 있는 요즘, 직접 은행에 가지 않고도 모바일로 간편하게 업무를 볼 수 있고, 무언가를 사거나 주문하기 위해 매장에 가면 점원이 아닌 주문 기계를 심심치 않게 만나게 됩니다. 이것은 분명 매장을 운영하는 업주에게는 인건비를 줄여주니 매장의 효율적 운영의 방편이 되고, 일부 소비자는 편리하게 주문이 가능해 장점일 수 있습니다.</p><p>\"터치가 무슨 말?\" 편리함이 불편한 사람들</p><p>\'고문기계\', 바로 어르신들이 \'키오스크\'를 부르는 말입니다. 디지털 유전자를 가지고 태어난다고 할 정도로 요즘 아이들은 태어날 때무터 화면을 쓸어내려 잠금을 풀지만, 어르신들에게 터치는 매우 어색하고 낯선 동작입니다. 이것이 커다란 화면을 보면서 여러 선택지를 읽고 골라야 하는 키오스크만 있는 가게 대신에 직원에게 몇 마디의 말로 주문할 수 있는 매장을 찾는 이유이기도 합니다. 그렇다면 어르신들이 키오스크를 두려워하는 이유는 무엇일까요? 바로 사람이 아닌 기계와 대화한다는 점입니다. 혹여라도 엉뚱한 것을 결재할까 봐 겁이 나는데 주위 물어볼 사람이 없어서 주문을 못하고 매장을 나오기도 하고, 더욱이 뒤에 줄이 서 있으면 마음은 급해지고, 나때문에 방해가 되는 건 아닌지 미안해서 시도조차 하지 않는 경우가 대부분입니다.</p><p><br></p>',11,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 15:31:06.290135','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F283797ca-d82d-45b8-ac00-5283d02e6f9f%ED%82%A4%EC%98%A4%EC%8A%A4%ED%81%AC.jpg','나도 키오스크로 주문하고 싶어요'),(16,'<p>코로나19로 바뀐 특수교육 대상 아동·청소년들과 가족의 외로운 일상</p><p>김00이는 초등학교 4학년으로 학교에서 친구들과 잘 어울리지 못하고, 수업 시간에도 선생님의 말씀을 잘 이해하지 못합니다. 아버지는 농사일로 바꾸시고, 어머니는 베트남 분으로 한국말을 잘 하지 못합니다. 이러한 김00이는 현재 특수교육 대상자로 등록되어 있지만 부모님은 아이를 위해 어떻게 해야 하는지 알지 못합니다. 이러한 어려움 속에 있는 아동들에게는 다양한 경험과 또래 집단들과의 교류를 통해 사회성을 향상 시키고 사회 구성원으로서 살아갈 수 있는 기본 규칙들을 배워야 할 시기에 코로나와 경제 활동을 하는 부모들로 인해 방치된 채 생활하고 있습니다. 이는 대상 아동 청소년 뿐만 아니라 가족 구성원 전체가 양육 스트레스와 코로나 블루로 어려움을 겪으며 가족들 간의 애착 관계가 약해지는 이중고로 이어지고 있으며, 또래 아동들보다 많은 관심과 지원이 필요하나 전문적인 지원을 받지 못한 체 어려운 여건 속에서 살아가고 있습니다</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/3619240c-173a-4503-b999-c967803fce9eimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p>농어촌지역 특수교육아동들에 대한 부족한 인프라로 인해 소외되어 가는 우리 아이들</p><p>남해군은 천혜의 자연환경을 가진 자연의 보고라고 하지만 섬이라는 특수성과 총 인구 수(42,958명) 중 65세 이상 인구 비율이 37.8%(16,258명)으로 초고령 사회의 기준인 넘어선 농어촌 지역이며, 재정 자립도가 낮아 포괄적 복지보다는 대부분의 복지 정책이 노인에게 맞추어져 있으며, 이로 인해 학생들을(총 인구 대비 7%) 위한 교육, 문화, 여가 등의 인프라는 타 지역에 비해 열악할 수 밖에 없는 실정입니다. 이러한 열악한 상황 속에서 살아가는 남해군 내 특수교육 대상 아동은 92명으로(총 인구 대비 0.1%, 2022년 9월 현재) 상대적 무관심과 복지 인프라 부족에 따른 배움의 기회 또한 박탈 당하고 있으며, 코로나19 펜데믹으로 인해 그나마 지원 받던 서비스들도 줄어들거나 받지 못하는 상황에 놓여있습니다. 최근 어려운 여건 속에서도 복지 서비스가 활성화 되고 있다고는 하지만 특수교육 대상 아동들에 대한 지원은 교육지원청에서 진행하는 프로그램 외에는 전무한 실정입니다.</p><p>남해지역 특수교육 아동·청소년들에게 배움의 기회를 제공할 수 있도록 함께 해주세요.</p><p>특수교육 대상 아동들 대부분은 전문적이며 지속적인 지원과 학습을 지원한다면 또래 학생들과 함께 생활하고 자라나 지역사회의 일원으로 살아가는데 아무런 문제가 없는 경우가 많이 있습니다. 또한 장애가 의심된다면 장애인 복지 전문 기관인 본 복지관에서 초기 상담부터 장애 등록까지 지원할 수 있어 특수교육 대상 아동들에게 종합적인 지원이 가능하여, 자라나는 아동 청소년들에게 희망을 줄 수 있을 것으로 생각됩니다. 이에 남해지역 특수교육 대상 아동 5명을 대상으로 아래 내용으로 사업을 진행하고자 합니다. * 월 2회 만들기 키트 제작 - 동영상을 제작 후 만들기 키트와 동영상을 전달하여 대상 아동들이 가정에서 보호자와 함께 만들어 보는 시간을 통해 다양한 경험을 획득하고, 가족관의 관계 개선의 효과를 통해 인지 능력 및 심리적 안정을 도모 * 연 2회 학습지 지원 - 아동의 학령과 학습 수준에 맞춰 학습지를 지원하고 담당 사회복지사가 정기적으로 가정을 방문하여 아동의 학습 과제 진행 사항을 점검하고, 틀린 문제에 대해서는 답을 알려주며 기초학습 능력을 향상하여 학교 생활의 적응력을 높일 수 있도록 지원 *CACV 종합 진로 직업적성검사 - 아동에게 자신의 직업적성을 알수 있는 검사를 통해 미래에 대한 희망을 가지고 생활 할 수 있도록 지원 *부모 상담 - 보호자와 아동의 가정 생활에 대한 상담을 통해 양육 스트레스 감소와 올바른 양육의 기회 제공 *담당 교사 상담 - 아동들이 학교 생활 적응력을 높이고 보다 체계적이고 다각적인 지원을 위한 체계 구축 많은 분들의 참여와 관심 부탁드립니다!</p>',15,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 15:36:35.139859','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F8f52ae2a-f439-4832-b220-27249cef7dc43619240c-173a-4503-b999-c967803fce9eimage.png','남해지역의 특수 교육 대상 아이들을 응원해주세요!'),(17,'<h3>헐값에 팔린 북극여우</h3><p>어느 날 동물권행동 카라가 운영하는 길고양이 급식소 인근에 북극여우가 나타났습니다. 아름답고 풍성한 털을 가지고 따뜻한 공원 잔디밭에 웅크리고 있던 북극여우. 툰트라 지대에 살고 있어야 할 여우가 어쩌다 서울 한복판에 나타나게 된 것일까요? 카라가 여우를 구조소식을 알리자 그의 보호자를 자처하는 사람이 나타났습니다. 그는 품종 고양이를 구매하려다 희귀동물샵에서 북극여우를 저렴하게 분양받았다고 했고, 마당에 풀어놓았다가 여우를 잃어버렸다고 이야기했습니다. 여우는 복잡한 도심에서 8차선 도로 두 개를 가로지르며 숲이 울창한 도시공원에 도착하게 된 것입니다. 우리는 북극여우를 해외 생추어리로 보내려 했지만, 법적으로 북극여우의 소유권은 구매자에게 있기에 여우를 보호자에게 돌려보낼 수밖에 없었습니다. 북극여우는 앞으로 그 생태와 본능에 적합하지 않은 환경 속에서 계속 살아가야 합니다.</p><ul><li><p>어깨가 상한 지 오래되었던 북극여우의 상태</p></li><li><p>포획틀에 들어간 북극여우</p></li><li><p>병원으로 이동한 북극여우</p></li><li><p>여우가 거주하던 마당 환경</p></li><li><p>어깨가 상한 지 오래되었던 북극여우의 상태</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/7fa7cad1-9825-43c8-8276-8d66b7676de2%EB%B6%81%EA%B7%B9%EC%97%AC%EC%9A%B0.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p></li><li><p>포획틀에 들어간 북극여우</p></li></ul><p><br></p><h3>사람에게는 교감, 동물에게는 폭력</h3><p>사실 북극여우 외에도 수많은 야생동물이 한국 사회의 사각지대 곳곳에서 고통받고 있습니다. 야생에서 포획된 동물들은 정체모를 사육장에 갇혀 강제로 임신과 출산을 반복하고, 그 새끼들은 동물원이나 샵으로 가게 됩니다. 한국에 있는 숱한 동물원과 체험동물원, 동물카페 등의 동물들도 모두 그런 과정을 거쳤습니다. 지금 이 순간에도 드넓은 하늘을 날아다니는 독수리나 바다를 헤엄치는 돌고래, 초원을 가로지르는 코끼리 등 수많은 동물들이 자유를 박탈당한 채 유리벽 안에 전시되어 있습니다. 동물원과 동물카페 등 많은 업체들은 동물에 대한 진실을 가리고 ‘동물들과 교감할 수 있다’고 마케팅을 하지만, 과연 이 ‘교감’이 동물들에게도 기쁜 만남일까요? 동물들은 누구에게도 소유되지 않고 자유로운 삶을 살아갈 때 존엄한 생명으로 행복할 수 있습니다. 전시된 동물을 ‘야생으로 돌아가면 바로 도태되지 않겠느냐’고 걱정하시는 분들이 있지만, ‘야생동물 생추어리’라는 대안이 또 있으니까요. 드넓은 자연공원 속에서 사람의 도움을 조금씩 받는 동물들은 생존능력을 회복해 야생으로 돌아가기도 합니다. 동물들은 고장난 물건이 아니라 경이로운 생명이니까요.</p><ul><li><p>제주도 점보빌리지에서 쇼를 하는 코끼리들</p></li><li><p>체험동물원에서 플라스틱을 갉아먹는 수달</p></li><li><p>사람에게 먹이를 구걸해야 하는 동물원 동물들</p></li><li><p>윙컷을 당해 하늘을 비행하지 못하는 왕부리새</p></li><li><p>구조되어 생추어리로 간 코끼리들</p></li><li><p>구조되어 자유로운 무리생활을 하는 코끼리</p></li><li><p>제주도 점보빌리지에서 쇼를 하는 코끼리들</p></li></ul><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/3b3262f1-6c74-4b62-a06e-1c5a0d1a453e%EC%88%98%EB%8B%AC.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><ul><li><p>체험동물원에서 플라스틱을 갉아먹는 수달</p></li></ul><p><br></p><h3>야생동물과의 공존을 위해서</h3><p>동물권행동 카라의 활동가들은 야생동물이 자연 속에서 살아가기 위해 일하고 있습니다. 법과 제도를 바꾸고, 사람들의 인식을 변화시키고, 우리 문화를 보다 윤택하게 이끌어갈 수 있도록요. 교육과 문화, 법정책 개선 등의 활동이 계속된다면 우리 사회의 생명감수성 역시 자연스럽게 성장할 것입니다. 여러분도 야생동물들이 자신의 삶을 살아갈 수 있는 데 함께해 주지 않으시겠어요? 사람들의 동물의 서식지를 파괴하지 않도록, 동물을 납치해 사고팔지 않도록, 동물을 학대하는 것이 교감이라고 생각하지 않도록 카라를 후원하고 세상을 바꾸어 주세요!</p><ul><li><p>코끼리 생추어리에 대한 이야기 시간</p></li><li><p>카라 동물영화제에서의 관객과의 대화</p></li><li><p>제주도에서 진행한 코끼리쇼 불매 피케팅</p></li><li><p>제주 선흘동물테마파크 철회 기자회견</p></li><li><p>동물원 동물들을 소재로 한 청소년 동물권 교육</p></li><li><p>코끼리 생추어리에 대한 이야기 시간</p></li></ul><p><strong>관련링크</strong></p><ul><li><p><a href=\"https://www.ekara.org/activity/wild/read/12169\">설악산 케이블카 사업 최종 중단까지의 경과 그리고 의의</a></p></li><li><p><a href=\"https://www.ekara.org/activity/wild/read/12209\">도심 속 공원에 나타난 북극여우, \'닉\'</a></p></li><li><p><a href=\"https://www.ekara.org/activity/wild/read/11281\">동물원・수족관법 개정의 주요 쟁점들</a></p></li></ul>',2,'FUNDING','FUNDING_WAIT','2023-02-15 16:03:18.755072','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2Fa9ce4817-199b-4187-93ca-3099932f0d44thumbnail01.png','야생동물, 우리 함께 살아요!'),(18,'<h3>백주대낮에 발생한 끔찍한 학대사건</h3><p>4월 중순, 젖먹이 새끼들 앞에서 어미 개 한 마리를 목 매달아 도살하는 참혹한 사건이 발생했습니다. 제보자집 건너편 공장지역에서 개 비명소리를 듣고 현장으로 뛰어갔고, 허겁지겁 목이 매달린 개를 부여잡고 소리치며 도살 행위를 중지시켰습니다. 그 직후 피를 토하던 어미 개를 데리고 병원으로 달려갔지만 어미는 결국 숨을 거두고 말았습니다. 억울하게 죽은 어미 개의 젖은 한참 불어 있었습니다. 현장에는 2개월령 어린 새끼들과 또 다른 어미개 \'디아나\'가 남겨져 있었습니다. 제보자는 개들이 목전에서 어미개의 죽음을 목격했으며, 학대자가 개들에게 먹잇감으로 토막난 고양이 사체를 던져놨다며 증거사진을 제시했습니다. 제보자가 해당 사건을 경찰과 지자체에 신고했으나, 현장에 출동한 경찰은 사체를 그냥 두고 가라고 하는 등 엽기적 사건을 심각하게 여기지 않았습니다. 해당 지자체에서도 다시는 동물을 키우지 않겠다는 각서를 서류로 꼭 받아 달라는 제보자의 요청을 들어 주지 않았습니다. 지역 과 지자체 모두 이토록 잔혹한 동물학대 범죄 행위를 두고도 소극적 태도로 일관하였습니다.</p><ul><li><p>꼬질꼬질하고 기죽은 아기 강아지들</p></li><li><p>학대현장에 매여 있던 또 다른 어미개 \'디아나\'</p></li><li><p>열악한 환경에서 방치되어 있던 강아지들</p></li><li><p>구조 직후, 야윈 몸으로 새끼들을 돌보던 모습</p></li><li><p>꼬질꼬질하고 기죽은 아기 강아지들</p></li></ul><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/9cf09600-5085-43a5-9e81-7de69ec6f2a8%EC%9C%A0%EA%B8%B0%EA%B2%AC01.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><ul><li><p>학대현장에 매여 있던 또 다른 어미개 \'디아나\'</p></li></ul><p><br></p><h3>사람에게 박탈당하는 동물의 생명</h3><p>카라의 활동가들은 현장으로 달려가 개들의 보호자와 합의해 남은 개들을 모두 구조했습니다. 다섯 마리 새끼들은 생후 7-8주령 정도의 연령으로 한창 엄마가 필요한 나이의 수컷들. 디아나는 무척 말라서 갈비뼈가 다 보이는 몸으로도 죽은 어미 개의 새끼들까지 거둬 포동포동하게 키워내고 있었습니다. 새끼들은 처음에는 기죽은 듯 하다가 곧장 사람을 따르며 관심을 갈구하게 되었고요. 디아나 또한 다정한 성품으로 사람을 잘 따릅니다. 현재 어미 개의 목을 매단 ‘보호자’에 대해서는 카라가 동물보호법 위반으로 고발을 한 상태입니다. 하지만 이 사건의 책임을 과연 보호자에게만 물어야 하는 것일까요? 반려견의 복지를 보장하지 않는 제도, 개를 길러서 잡아먹는 것이 개인의 자유라는 인식, 동물을 생명이 있는 존재가 아니라 누군가의 소유물로 보는 현행법이 어미 개의 존엄과 목숨을 빼앗았습니다. 이와 같은 사회적 상황에서 억울하게 목숨을 잃어야 했던 것은 어미 개 뿐만이 아닙니다. 수많은 누렁이, 백구, 유기견, 누군가의 반려견들이 잘못된 한 끼 보신을 위해 목숨을 내놓아야 했습니다. 우리 사회가 변하지 않는 이상, 동물들은 헛되이 목숨을 잃게 됩니다.</p><ul><li><p>손길이 필요한 생명들</p></li><li><p>\"방치도 학대다!\" 애니멀호딩으로 고통받는 동물들</p></li><li><p>유기견 발생이 허용되는 사회 구조, 그 결과물</p></li><li><p>열악한 환경에서 \'고기\'가 되기 위해 태어난 강아지</p></li><li><p>방치되어 길러지는 소형견</p></li><li><p>피부병에 걸려 버려진 번식장의 푸들들</p></li><li><p>손길이 필요한 생명들</p></li></ul><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/18c44ba3-6fe7-40bb-b487-8a2482cb255f%EC%9C%A0%EA%B8%B0%EA%B2%AC02.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><ul><li><p>\"방치도 학대다!\" 애니멀호딩으로 고통받는 동물들</p></li></ul><h3>세상의 변화를 함께 만들어 주세요</h3><p>다행스럽게도 어미 개의 도살 사건에 수많은 사람들이 관심을 가져주었습니다. 학대자의 강력한 처벌을 요청하는 서명 캠페인에 일 주일만에 1만 1천여 명의 시민들이 함께했고, 많은 언론매체에서 학대사건을 보도했습니다. 현재 카라는 이런 학대사건을 막을 수 있도록 ‘누렁이법’을 만들라는 기자회견을 한 후 후속 활동을 이어가고 있는 중입니다. 우리 사회가 조금씩 바뀌어 가고 있음을 느낍니다. 지난 해에는 ‘개를 전기로 도살하는 것’이 유죄라는 대법원의 판결이 있었고, 국내의 거대한 개 가축시장 중 하나인 부산 가축시장이 문을 닫게 되었습니다. 동물학대에 대한 유죄판결 사례도 점차 늘어나고 있습니다. 카라는 동물과 더불어 공존하는 사회를 위한 기틀을 다지기 위한 법정책 활동, 우리 사회의 생명감수성이 자라날 수 있도록 다양한 교육문화 캠페인을 진행하고 있습니다. 더불어 구조한 동물들이 행복하게 살아갈 수 있도록 물심양면으로 애쓰는 중입니다. 모쪼록 카라의 활동을 응원해 주세요. 그리고 동물학대 없는 세상이 가능하도록 함께 힘을 모아주시길 부탁드립니다.</p><p><br></p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/fb4da3b8-f501-40b6-9ff2-a3f772e41e57%EC%9C%A0%EA%B8%B0%EA%B2%AC03.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><ul><li><p>구조 후 일 주일, 활짝 웃게 된 디아나</p></li></ul>',0,'FUNDING','FUNDING_WAIT','2023-02-15 16:09:26.780373','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F4c421255-50f7-4403-95fb-e520b568f7ac%EC%9C%A0%EA%B8%B0%EA%B2%AC01.png','동물학대 없는 사회를 함께 만들어요'),(19,'<h3>구조 후, 일 년만에 웃게 된 도래</h3><p>도래는 전라남도 보성군 벌교읍의 한 개농장에서 구조되었습니다. 도래는 뜬장 위에서 음식물쓰레기를 먹으며 하루하루 죽음에 가까워지고 있었고, 기적적으로 우리를 만나 살게 되었습니다. 구조 당시 봉사자들이 깔아준 지푸라기 위에 앉아 있다 눈이 마주치자 엉거주춤 일어서서 사람들을 바라보았던 도래. 도래는 푸석푸석하고 야위었던 도래는 참 겁이 많고 소심한 개였습니다. 도래는 사람들이 제 친구들을 때리고, 끌고 나와 도살하던 것을 보았던 개입니다. 그런 도래에게 우리 또한 무서운 사람들이었습니다. 도래는 개농장에서 벗어난 후에도 사람을 가까이에 두지 않았습니다. 사람이 곁에 있으면 시선을 피하며 어쩔 줄 몰라했습니다. 하지만 일 년간의 살뜰함 보살핌으로 도래는 달라졌습니다. 적당히 살집도 붙고, 털에도 윤기가 흐르고, 이제는 사람을 바라보며 웃을 수 있게 된 도래가 무척 기특하고 고맙습니다.</p><ul><li><p>구조 후 2주가 지나서 다시 만났을 때의 모습</p></li><li><p>2020년 2월의 도래. 더봄센터 입주를 앞두고 있다.</p></li><li><p>2019년 3월, 개농장에서 처음 만났을 때의 도래</p></li><li><p>눈이 마주치자 엉거주춤 일어서서 경계하던 모습</p></li><li><p>구조 당시 사람을 싫어하면서도 반항도 못 하던 도래</p></li><li><p>뜬장에서 나온 후 친구들과 함께 이동을 기다리는 모습</p></li><li><p>구조 후 2주가 지나서 다시 만났을 때의 모습</p></li></ul><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/976231e8-b82d-4144-84e4-18b1f8be0251%EC%9C%A0%EA%B8%B0%EA%B2%AC04.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><ul><li><p>2020년 2월의 도래. 더봄센터 입주를 앞두고 있다.</p></li></ul><h3>사회로 다시 돌아가기 위한 연습</h3><p>학대를 당해도 그저 사람이 좋아 꼬리를 흔들고 행복해하는 개들도 있지만, 개농장이나 애니멀호더의 집 등 다양한 학대현장에서 구조된 개들은 대부분 사람을 많이 경계하고 두려워합니다. 그런 동물들은 그저 구조한다고 해서 행복하고 건강한 개가 되지 않습니다. 개들을 돌보는 사람들이 필요하고, 그 애들이 살아가는 데 필요한 자본이 필요합니다. 많은 사람들의 사랑과 도움이 없다면 개들은 죽는 그 날까지 사람들을 두려워하며 살아가게 됩니다. 변화하는 시간에 차이가 좀 있을 뿐이지, 사람이 잘하면 동물들은 자연스럽게 응답합니다. 사람이 위험하기만 한 존재가 아니라는 것을 이해하는 것, 간식을 건네면 집어먹는 것, 목줄이나 리드줄을 하는 것, 함께 산책을 나가는 것… 수많은 연습 끝에 개들은 사회로 다시 돌아갈 준비를 하게 됩니다. 사람과 살아가는 것을 기꺼워하게 된 개들은 처음에 구조했던 개와는 전혀 다른, 아주 반짝거리는 얼굴을 하고 있습니다.</p><ul><li><p>사람에게 마음을 여는 중인 삼식이</p></li><li><p>카라 더봄센터에서 입양을 기다리는 토르</p></li><li><p>산책 연습을 하는 마트와 보미</p></li><li><p>사회화 완료! 입양을 기다리고 있는 복돌이</p></li><li><p>사람에게 마음을 여는 중인 삼식이</p></li></ul><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/82d867e6-6f35-43f3-bb14-be5c9831fbe9%EC%9C%A0%EA%B8%B0%EA%B2%AC05.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><ul><li><p>카라 더봄센터에서 입양을 기다리는 토르</p></li></ul><p><br></p><h3>당신이 참여하는 순간, 변화는 시작됩니다</h3><p>카라의 경우 평균적으로 200~250여 마리 동물들을 보호하고 있습니다. 이들은 안전한 삶을 보장받지만, 우리가 손 대지 못하는 동물들의 사정은 여의치 않습니다. 당장 지자체 보호소에는 10만 마리의 동물들이 입소합니다. 그들은 공고기한 내에 가족을 찾지 못하면 죽게 됩니다. 교통사고를 당했거나 질병에 걸렸어도 치료받지 못합니다. 사회화 교육은 꿈도 못 꾸는 곳이 대부분입니다. 근본적으로 유기동물 수를 줄일 수 있도록 동물을 함부로 사고 팔지 못하게 하고, 반려동물 등록제가 좀 더 실효성 있도록 많은 사회문화적 장치가 마련되어야 합니다. 카라는 동물권 교육과 정책활동을 기반으로 동물과 더불어 살아가기 위한 다양한 캠페인을 진행하고 있습니다. 더불어 콘텐츠를 제작하고 배포함으로서 동물권의 이슈를 알리고, 구조된 동물들의 사연과 상황을 알리며 입양가족을 찾아가고 있습니다. 사회를 변화시키고, 동물들에게 희망을 줄 수 있도록 도와주세요. 사회가 저버렸던 동물들이 다시 정당한 사회구성원으로서 살아갈 수 있도록 함께 해주시기를 부탁드립니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/841433aa-0c7b-4763-a04a-f81a2c63ee89%EC%9C%A0%EA%B8%B0%EA%B2%AC06.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p>',4,'FUNDING','FUNDING_ACCEPT','2023-02-15 16:14:01.086301','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F842695ac-d2ce-4be7-93a5-5085229d924d%EC%9C%A0%EA%B8%B0%EA%B2%AC04.png','또 다른 사회구성원, 버려진 동물에게 희망을'),(20,'<h3>‘보호소’라는 이름의 신종 펫숍들</h3><p>모두가 입을 모아 ‘사지 말고 입양하세요’라고 말하는 세상입니다. 한 해 유기동물은 10만 마리 이상 발생하고 있고, 우리 사회의 사각지대에는 사실 그보다 더 많은 동물들이 위기에 처해 있으니까요. 동물들의 처지에 공감하고, 연민하고, 그들이 제 삶을 살아가길 바라는 마음은 무척 자연스럽고 귀한 것입니다. 하지만 이런 순수한 마음을 이용하는 펫숍들이 생기고 있습니다. 이들은 ‘유기견 보호소’를 표방하면서 번식장에서 태어난 어린 동물들을 판매합니다. 어느 가정집에서 파양하게 된 동물을 비싼 파양비와 함께 받고, 동물은 방치하기도 합니다. 이 펫샵들은 ‘보호소’라는 이름을 쓰고 있지만 사실은 동물을 돈벌이 수단으로서 동물을 이용하고 있습니다.</p><p><br></p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/0ea51920-76ca-4859-8506-8457a4d783f6%EC%9C%A0%EA%B8%B0%EA%B2%AC07.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><ul><li><p>늦은 밤까지 펫숍에 진열된 2개월령 강아지</p></li></ul><p><br></p><h3>자본이 힘인 사회, 우리가 해야 할 일</h3><p>포털에 ‘유기견 입양’을 검색하면 보호소를 표방하는 펫숍들의 홈페이지가 우후죽순 나타납니다. 광고비를 지급하면 우선적으로 사이트를 노출시킬 수 있기에 자본력 있는 펫숍들은 검색 키워드를 선점해서 펫숍을 더 적극적으로 홍보하고 있습니다. 그 결과 번식장의 동물들은 도움을 구할 데 없이 더 고통 받고, 동물들은 이른 나이에 매매되고 있습니다. 정작 관심의 손길이 필요한 보호소들은 더 소외된 곳으로 밀려납니다. 카라의 활동가들은 사람들을 기만하고 동물의 고통을 증대시키는 이 구조에 심각한 문제의식을 느낍니다. 우리는 오래 전부터 동물을 상품으로 전락시켜온 경매장을 폐쇄하고 동물 생산업과 판매업에 대해 더 강하게 규제할 것을 요구해왔습니다.</p><p><br></p><p><br></p><p>최근에는 국가의 동물보호 방임 속에 나타난 ‘사설보호소’의 기준을 정립하고, 기준에 부합하는 사설보호소는 지자체에 신고하거나 등록하여 여러 지원을 받을 수 있도록 하는 정책을 제안하고 있습니다. 국가의 동물보호를 바로세움과 동시에 도움이 필요한 보호소를 지원하는 것이 주된 목적이며, 부가적으로는 ‘보호소’라는 이름으로 동물을 학대하는 애니멀 호더와 마케팅을 하는 펫샵을 규제하는 효과도 볼 수 있습니다. 카라의 활동가들은 동물들이 처한 현실을 알리고, 이 현실을 바꾸기 위해서는 어떻게 해야 하는지 보다 많은 사람들에게 알리는 활동도 함께 하고 있습니다. 활발하게 SNS를 운영하고, 동물들의 사연을 정리해 영상으로 만들거나 에세이를 써 기고를 하기도 합니다. 캠페인 홍보에 있어서 가장 중요한 가치 중 하나는 투명성이고, 우리가 쓰고 올리는 게시물이 과장이나 논리적 비약 없이 세상을 바꿀 수 있는 계기가 되길 바라는 마음으로 활동을 하는 중입니다.</p><p><br></p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/5dcc5106-d07b-4fb5-85d5-106835c74d0d%EC%9C%A0%EA%B8%B0%EB%AC%9801.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><ul><li><p>카라가 운영하는 보호소에서 지내는 장애묘들</p></li></ul><p><br></p><h3>동물과 더불어 사는 삶을 같이 만들어요</h3><p>카라의 활동가들은 동물들을 구조하고, 치료하고, 삶을 되찾아주며 큰 기쁨을 느낍니다. 하지만 그것보다는 우선 도움이 필요한 동물이 없기를 바랍니다. 동물을 팔지도, 사지도 않는 사회는 우리의 힘으로 만들어낼 수 있습니다. 동물을 착취해서 몸집을 불려나가는 펫숍에 맞서 싸울 수 있도록 여러분의 힘을 함께 모아주세요.</p>',0,'FUNDING','FUNDING_WAIT','2023-02-15 16:19:00.726452','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F127796a4-598d-40d4-a0fe-cb85ae6b4978%EC%9C%A0%EA%B8%B0%EA%B2%AC07.png','동물이 돈벌이 수단이 아닌 사회를 같이 만들어요'),(21,'<h1>사라져 가는 야생동물, 하지만 보호의 사각지대가 있어요</h1><p>세계자연기금(WWF)이 발표한 ‘지구생명보고서 2020’에 따르면 1970년과 2016년 사이에 야생동물 개체군의 규모가 평균 68%나 감소했습니다. 50년도 안 되는 짧은 시간에 전 세계의 야생동물 중 3분의 2가 사라진 것입니다. 우리나라에 살고 있는 멸종위기생물은 1989년 92종에서 지속적으로 증가해 2017년 기준 267종에 달합니다. 이중 절멸위기에 처한 멸종위기1급 포유류는 사향노루, 산양, 여우, 반달가슴곰 등 12종이 있습니다. 하지만 야생동식물특별보호구역은 한 곳(진양호 일원, 약26.2㎢) 뿐입니다. 보호구역에 살지 않는 야생동물은 \'보호의 사각지대\'에 놓여있습니다. 녹색연합은 이 가운데 산양이 살고있는 울진삼척지역과 사향노루가 살고있는 강원도 비무장지대 인근 지역에 집중합니다. ▲산양은 멸종위기1급 야생동물로, 울진·삼척 지역은 산양 최남단 집단 서식지입니다. 이 지역의 생태계가 건강하게 유지되어야 국내 산양 개체 수 증가, 유전적 다양성 증진 등이 이루어질 수 있습니다. 하지만 개발로 인해 서식지가 파괴되고 밀렵으로 인한 위협에 노출되어 있습니다. ▲사향노루는 고급 약재와 향수의 원료로 쓰이는 ‘사향’을 노린 남획과 밀렵으로 생존에 위협을 받고 있어 국내외적으로 보호가 시급한 종입니다. 이에 사향노루는 환경부 지정 멸종위기 야생생물 Ⅰ급, 문화재청 지정 천연기념물 216호에 해당하며 국가적색목록 위급(CR), 국제자연보전연맹(IUCN) 취약(VU) 등급으로 지정되어 있습니다. 과거 전국에 서식하던 사향노루는 현재 강원도 비무장지대 인근 30여 개체만이 남은 것으로 알려져있습니다. 환경부가 2018년부터 민통선 이남 지역에 사향노루가 서식한다는 사실을 확인한 바 있지만 관련된 아무런 연구나 조치가 이뤄지지 않은 상황입니다.</p><p><br></p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/344a80fa-a040-4f08-9d39-18a9a413c3f0%EC%82%B0%EC%96%91.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><ul><li><p>멸종위기 야생동물 산양</p></li></ul><p><br></p><h2>유리벽에 부딪히는 새를 시민이 직접 구합니다</h2><p>환경부와 국립생태원이 2018년도에 발표한 \'인공구조물에 의한 야생조류 폐사방지 대책 수립\' 보고서에 따르면, 우리나라에서 투명 유리창에 부딪혀 죽는 새가 한 해 800만 마리에 달합니다. 바꿔 말하면 하루 평균 약 2만여 마리의 새가 사람이 만든 인공구조물인 유리창에 부딪혀 목숨을 잃는다는 이야기입니다. 녹색연합은 2019년부터 \'새친구\' 캠페인을 통해 유리창 새 충돌 사고를 줄이기 위한 활동을 이어왔습니다. 그 결과, 세계적인 철새 도래지로 유명한 천수만 가까이 있는 649번 지방도 구간에서 유리벽에 부딪혀 죽는 새들을 연간 100여마리 이상 살릴 수 있었습니다. 또한 제도적인 변화도 일어나기 시작했습니다. 지자체에서 새충돌 방지 조례를 제정하거나 자체 저감조치를 시행하기 시작했습니다. 국회에서도 조류충돌 문제에 귀를 기울이기 시작했고요! 올해도 녹색연합은 아무것도 모른채 인간 때문에 죽어야 하는 새를 살리러 새친구와 함께 현장으로 출동합니다. 새 충돌 문제에 대한 교육 프로그램을 운영하고, 홍보 활동을 활발히 진행해 새 충돌 현황에 대한 인식을 높여 나가겠습니다. \'새친구\'와 함께 새 충돌 현장을 방문하여 직접 충돌저감 조치(스티커 부착)를 적용하겠습니다. 시민들과 함께 지속적인 모니터링으로 향후 관련 정책 개선을 위한 데이터 마련에 힘쓰겠습니다.</p>',15,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 16:21:34.772731','','보호받지 못하는 야생동물, 시민이 보호합니다!'),(22,'<h1>오늘의 기억도 영원히 사라지게 하는 \'치매\'</h1><p>치매는 오늘의 기억도 영원히 사라지게 하는 무섭고도 슬픈 병입니다. 누구에게나 찾아올 수 있지만 대부분 대비하기 어려운 치매, 어르신들이 하루하루 아름다운 추억을 품으며 행복할 수 있도록 지켜드리고 싶습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/66b73280-1cdc-4fea-a46b-c5ef046ff2d4image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/8c5c205f-d109-42d1-b011-900776674507image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/5ed6c43d-5a5d-4a4a-9190-f1b67a2e7ad3image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/b085ea39-f3ca-410f-a686-c735e479070eimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/6faf5b94-47a8-4527-b990-1a3e8c3092c0image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/ef0d6065-5f77-487d-9866-fda8c04be95cimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/153375b4-601d-4e44-83aa-f7aeba2393e5image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h1><strong>보답하고 싶은 사랑</strong></h1><p>갑작스럽게 찾아온 치매, 어머니는 더 이상 집에 오는 길도 나이도 심지어 이름도 잃어버리는 일상을 겪게 되었습니다. 갑자기 변한 어머니를 감당하기가 버거워 버럭 화를 내버리곤 하지만, 아무 것도 모르던 어린 시절 사랑으로 키워주셨던 어머니를 생각하면 눈물이 앞을 가립니다. 어린 시절부터 지금까지 사랑으로 지켜주셨던 어머니의 마음에 이제는 보답하고 싶습니다.</p><p><br></p><h1><strong>예방과 조기 발견이 중요한 치매</strong></h1><p>어르신들이 가장 두려워 하는 병인 치매, 예방과 조기발견이 매우 중요합니다. 신내종합사회복지관에서는 인지기능 저하가 우려되는 저소득, 1인 가구, 경증 치매어르신들에게 치매예방 교육, 인지기능 향상 활동, 관계 형성 및 우울 해소를 위한 추억 활동을 지원하고자 합니다. 아름다운 기억을 쌓아 추억이 된다면 어르신이 더욱 행복한 나날을 보낼 수 있지 않을까요?</p><p><br></p><h2>함께하는 활동이 행복한 추억으로 남을 수 있도록, 어르신들의 하루하루를 응원해 주세요!</h2>',15,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 17:45:35.025417','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F01694291-6bf2-4960-903d-09027f593965%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202023-02-16%20024030.png','이제는 제가 지켜드릴게요'),(23,'<h1>노인들의 노후된 집을 안락하고 안전한 집으로 만드는 기적</h1><p>우리 사회는 고령화 시대를 넘어서서 초고령화 시대를 바라보고 있습니다. 빠르게 진행되고 있는 고령화 사회에서 노인은 어떠한 삶을 누릴 때 만족감을 갖게 될까요? 바로 돌봄이 제공되며 안락한 공간에서 거주할 때 만족감이 높다고 합니다. 이러한 문제를 해결하기 위해 한국해비타트는 여러분의 관심이 절실히 필요합니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/c0738136-b60c-4e0d-8c2c-aa01fcf567c1image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/20bfaf68-b130-4f5f-9bd1-bc97a92c090bimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/02c55265-a034-4367-9ecf-10c114fa5abcimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h1>노인 취약계층 대상 주거환경개선 지원이 시급합니다.</h1><p>노인들은 점점 활동성이 떨어지고 기력이 없어지면서, 자연스럽게 바깥활동보다는 집에서 보내는 시간이 많아집니다. 요즘 같은 코로나19 시기에 노인들은 더 많은 시간을 집에서 보낼 수밖에 없는 환경에 처했습니다. 이렇게 하루 중 집에서 보내는 시간이 길어지면서 노후된 주택에 거주하시는 분들은 위험에 노출되는 경우가 많이 있습니다. 곰팡이가 건강에 안좋다는 걸 알면서도 바꾸지 못하시는 노인 세대, 오래된 천장이 무너질까 혹은 노출된 전기선이 위험하지는 않을까라는 생각을 하며 집에 계시는 노인 세대 대부분은 경제적으로 어렵거나 도와줄 사람이 없어 집을 고치지 못하고 거주하고 있습니다. 이러한 문제를 해결하기 위해 한국해비타트는 2020년부터 같이가치 모금을 통해 고령화된 지역에 살고 있는 노인 가구를 대상으로 주거환경개선사업을 진행하고 있습니다. 2020년에는 천안 남산지구 내 25세대 노인 가구를 대상으로, 2021년에는 춘천지역에 있는 6세대를 대상으로 주거환경개선사업을 진행하며 노인분들에게 쾌적한 주거환경을 제공하고 그들의 경제적, 심리적 불안감을 해결하는 데 기여하고 있습니다.</p><p><br></p><p><br></p><h1>노인들의 열악한 집을 안락하고 안전하게 바꿔드리겠습니다!</h1><p>이번 모금을 통해 저희는 열악한 주거환경에 거주하시는 저소득 어르신 2세대를 선정하여, 따뜻하고 안락한 공간을 제공하고 싶습니다. 주거환경개선을 통해 삶에 만족감을 드리고 집에서 생활하시는 동안 불편함 없는 그런 거주 공간. 안전하고 어르신들의 안전하고 편리한 집에 거주하실 수 있도록 함께해주세요!</p>',2,'FUNDING','FUNDING_WAIT','2023-02-15 17:51:16.090214','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2Fb630ef1d-397e-44e9-ba56-ff21539063dd%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202023-02-16%20024831.png','노인들에게 안전한 주거환경을 제공해주세요'),(24,'마일리지는 펀딩 참여, 봉사 활동 라이브 후원(기프트), 기부에 사용할 수 있습니다.',NULL,'ETC','FAQ','2023-02-15 18:28:54.415354',NULL,'마일리지는 어디에 사용할 수 있나요?'),(25,'VMS의 위촉 임명장 실물 사진으로 판단합니다. 또한 펀딩을 주최하려면 최근 1년 이내의 봉사 실적이 있어야 합니다.',NULL,'ETC','FAQ','2023-02-15 18:28:50.122085',NULL,'펀딩을 주최하는 단체는 어떻게 인증받나요?'),(26,'기부는 저희 서비스에서 자체적으로 개설하여 회원들의 마일리지를 후원받아 진행됩니다.\n펀딩은 단체 회원이 개설하며, 개인 회원들의 펀딩 참여로 진행됩니다.',NULL,'ETC','FAQ','2023-02-15 18:28:58.420744',NULL,'FUNTEER에서 기부와 펀딩은 어떻게 다른가요?'),(27,'기부는 저희 서비스에서 자체적으로 개설하여 회원들의 마일리지를 후원받아 진행됩니다. 펀딩은 단체 회원이 개설하며, 개인 회원들의 펀딩 참여로 진행됩니다.',NULL,'ETC','FAQ','2023-02-15 18:29:11.474665',NULL,'FUNTEER에서 기부와 펀딩은 어떻게 다른가요?'),(28,'우측 상단의 프로필 이미지를 클릭하시면 프로필 페이지로 가실 수 있습니다. 정보 수정을 통하여 VMS 파일과 봉사 실적 파일에 대한 내용을 보완하여 다시 제출해주시면 됩니다.',NULL,'ETC','FAQ','2023-02-15 18:29:05.252349',NULL,'인증되지 않아 펀딩을 생성할 수 없다는 메세지가 출력됩니다.'),(29,'인증된 단체가 펀딩을 개설하고 개인 회원들의 참여로 최소 금액이 달성되면,\n단체의 라이브 중계 방송과 함께 봉사 활동이 진행됩니다.\n진행 후 단체의 보고서 제출 및 검토가 완료되면, 펀딩이 종료됩니다.',NULL,'ETC','FAQ','2023-02-15 18:31:03.552188',NULL,'펀딩 절차가 어떻게 되나요?'),(30,'인증된 단체가 펀딩을 개설하고 개인 회원들의 참여로 최소 금액이 달성되면,\n단체의 라이브 중계 방송과 함께 봉사 활동이 진행됩니다.\n진행 후 단체의 보고서 제출 및 검토가 완료되면, 펀딩이 종료됩니다.',NULL,'ETC','FAQ','2023-02-15 18:31:10.480934',NULL,'펀딩 절차가 어떻게 되나요?'),(31,'단체의 마일리지에 적립되며, 단체는 해당 마일리지를 개인 회원과 마찬가지로\n기부와 펀딩, 라이브 후원(기프트)에 사용할 수 있습니다.',NULL,'ETC','FAQ','2023-02-15 18:32:41.225691',NULL,'라이브 방송 중 후원금은 어떻게 사용되나요?'),(32,'단체의 마일리지에 적립되며, 단체는 해당 마일리지를 개인 회원과 마찬가지로\n기부와 펀딩, 라이브 후원(기프트)에 사용할 수 있습니다.\n보유하신 마일리지는 단체 프로필에서 확인 가능합니다.',NULL,'ETC','FAQ','2023-02-15 18:45:49.369829',NULL,'라이브 방송 중 후원금은 어디에 사용할 수 있나요?'),(33,'<p>안녕하세요. FUNTEER입니다.</p><p><br></p><p>FUNTEER와 함께 세상을 바꾸는 일에 동참해주셔서 감사드립니다!&nbsp;</p><p>올 한 해동안 FUNTEER에서 기부한&nbsp;기부 금액에 대한 연말정산을 위해 기부금 영수증 발급 방법을 안내해 드립니다.</p><p><br></p><p><strong>■&nbsp;&nbsp;기부금 영수증 발급 대상</strong></p><p>2022년 1월 1일부터 2022년 12월 31일까지 단체로 기부금 전달이 완료된 모금함에 기부한 직접 기부 내역</p><p><br></p><p><strong>■&nbsp;기부금 영수증 신청 기간</strong></p><p><br></p><p><strong>2022년 2월 6일(화) ~ 2023년 3월 6일(월) 까지</strong></p><p><br></p><p>※&nbsp;모금함에 기부한 날짜가 아닌 해당 모금함이 종료되어 단체로 기부금이 전달된 날짜 기준이므로,&nbsp;2022년 12월에 기부했어도 모금함 정산이 진행되지 않은 경우에는 2023년 기부금 영수증 발급 대상이 됩니다.</p><p>※&nbsp;일부 기부금 영수증 발급 불가 모금함에 기부한 경우 영수증 발급 대상에서 제외되며, 참여기부금(응원, 댓글, 공유)은&nbsp;실제 결제하신 내역이 아니기 때문에 개인에게 기부금 영수증 발급이 불가능합니다.</p><p><br></p><p><strong>■&nbsp;기부금 영수증 신청 기간 및 발급 방식</strong></p><p>2022년 기부금 영수증 신청 기간 내 기부금 영수증을 신청한 기부자님의 경우, 본인의 신청 일자에 따라 확인 방법이 달라요.</p><p><br></p><p><strong>1) 2023년&nbsp;2월 17일 23시 59분까지 신청 시</strong></p><p>국세청에 자동으로 등록되며 2023년 2월 20일부터 국세청 연말정산 간소화 서비스에서 확인할 수 있습니다.</p><p><br></p><p><strong>2) 2023년&nbsp;2월 18일부터 3월 6일까지 신청 시</strong></p><p>모금함 별 영수증 발급 단체에서 기부금 영수증 발급 후 이메일 또는 우편으로 발송됩니다</p><p>※ 이메일의 경우 근무일 기준 2~3일 정도, 우편 발송의 경우 1주일 정도 예상</p><p><br></p><p><strong>■ 기부금 영수증 신청 방법</strong></p><p>1:1 문의 &gt; 양식 작성 후 제출</p>',NULL,'ETC','NOTICE','2023-02-16 14:42:47.018273',NULL,'2022년 기부금영수증 신청하세요!'),(34,'단체 인증 빨리 부탁드립니다.',NULL,'ETC','QNA','2023-02-15 18:44:28.047895',NULL,'단체 인증 빨리 부탁드립니다.'),(35,'<h5>안녕하세요. FUNTEER입니다.</h5><h5><br></h5><h5>2023년 2월 17일 15시 30분부터 긴급 서비스 정기 점검을 진행 중입니다.</h5><h5>이용에 불편을 드린 점 진심으로 사과 드리며, 정상적인 서비스 재개를 위해 최선을 다하도록 하겠습니다.</h5><h5><br></h5><h5>- 점검 시간 : 2023.2.17. 15:30 ~ 17:30</h5><h5><br></h5><h5>* 점검 기간 중 서비스 결제 오류 등의 문제가 발생한 사용자 분께서는, FUNTEER 고객센터로 연락 부탁드립니다.</h5><h5><br></h5><h5>서비스 이용에 불편을 드린 점 대단히 죄송합니다.</h5>',NULL,'ETC','NOTICE','2023-02-17 00:09:01.909662',NULL,'서비스 점검 안내'),(36,'<h1>프로젝트 소개</h1><p>오늘 아침, 전국민이 강원도 산불 사고 소식으로 하루를 시작하였습니다.</p><p>내 집 앞까지 불길이 번져 숨도 쉴 수 없다는 고성과 속초를 비롯한 강원 지역의 주민분들은 얼마나 놀라셨을까요?</p><p><strong>공포와 슬픔, 감히 예상할 수조차 없습니다.&nbsp;</strong></p><p><strong>삶의 터전이 한순간에 잿더미가 되었습니다.&nbsp;</strong>역대급 국가 재난으로, 고성과 속초 인근의 공무원, 소방관, 국군장병과 경찰뿐 아니라 국민들까지 화재 진압과 사후 지원에 총력을 다하고 있습니다.</p><p>기업 역시 한 순간에 집을 잃은 이재민분들을 위한 생필품과 대피소 설치 등 구호 지원에 힘을 쓰고 있습니다.</p><p><br></p><p>누군가에는 추억의 여행지이지만 누군가에게는 삶의 터전입니다.</p><p>누군가에게는 매일 친구와 함께 걷던 곳이고, 저녁이면 가족들이 모이는 집이 있는 곳입니다.</p><p><strong>저희는 그동안 충분히 많은 소셜/캠페인 프로젝트들을 통해서 와디즈 펀딩이 또 하나의 미디어로서 기능을 할 수 있다고 믿고 있습니다.&nbsp;</strong></p><p><br></p><p><br></p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>피해 주민들에게 식료품, 임시 거처 제공</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>2월 16일: 봉사활동 계획</p></li><li><p>2월 26일: 봉사활동 실행</p></li></ul>',16,'FUNDING','FUNDING_IN_PROGRESS','2023-02-15 19:05:59.091575','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F6a251838-8a74-4fa3-9fd0-2eded255f9341.png','국가 재난 사태 \'강원 산불\' 구호'),(37,'<h1>프로젝트 소개</h1><p>오늘 아침, 전국민이 강원도 산불 사고 소식으로 하루를 시작하였습니다.</p><p>내 집 앞까지 불길이 번져 숨도 쉴 수 없다는 고성과 속초를 비롯한 강원 지역의 주민분들은 얼마나 놀라셨을까요?</p><p><strong>공포와 슬픔, 감히 예상할 수조차 없습니다.&nbsp;</strong></p><p><strong>삶의 터전이 한순간에 잿더미가 되었습니다.&nbsp;</strong>역대급 국가 재난으로, 고성과 속초 인근의 공무원, 소방관, 국군장병과 경찰뿐 아니라 국민들까지 화재 진압과 사후 지원에 총력을 다하고 있습니다.</p><p>기업 역시 한 순간에 집을 잃은 이재민분들을 위한 생필품과 대피소 설치 등 구호 지원에 힘을 쓰고 있습니다.</p><p><br></p><p>누군가에는 추억의 여행지이지만 누군가에게는 삶의 터전입니다.</p><p>누군가에게는 매일 친구와 함께 걷던 곳이고, 저녁이면 가족들이 모이는 집이 있는 곳입니다.</p><p><strong>저희는 그동안 충분히 많은 소셜/캠페인 프로젝트들을 통해서 와디즈 펀딩이 또 하나의 미디어로서 기능을 할 수 있다고 믿고 있습니다.&nbsp;</strong></p><p><br></p><p><br></p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>피해 주민들에게 식료품, 임시 거처 제공</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>2월 16일: 봉사활동 계획</p></li><li><p>2월 26일: 봉사활동 실행</p></li></ul>',0,'FUNDING','FUNDING_WAIT','2023-02-15 19:05:59.272747','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F6a251838-8a74-4fa3-9fd0-2eded255f9341.png','국가 재난 사태 \'강원 산불\' 구호'),(38,'<h1>프로젝트 소개</h1><p>오늘 아침, 전국민이 강원도 산불 사고 소식으로 하루를 시작하였습니다.</p><p>내 집 앞까지 불길이 번져 숨도 쉴 수 없다는 고성과 속초를 비롯한 강원 지역의 주민분들은 얼마나 놀라셨을까요?</p><p><strong>공포와 슬픔, 감히 예상할 수조차 없습니다.&nbsp;</strong></p><p><strong>삶의 터전이 한순간에 잿더미가 되었습니다.&nbsp;</strong>역대급 국가 재난으로, 고성과 속초 인근의 공무원, 소방관, 국군장병과 경찰뿐 아니라 국민들까지 화재 진압과 사후 지원에 총력을 다하고 있습니다.</p><p>기업 역시 한 순간에 집을 잃은 이재민분들을 위한 생필품과 대피소 설치 등 구호 지원에 힘을 쓰고 있습니다.</p><p><br></p><p>누군가에는 추억의 여행지이지만 누군가에게는 삶의 터전입니다.</p><p>누군가에게는 매일 친구와 함께 걷던 곳이고, 저녁이면 가족들이 모이는 집이 있는 곳입니다.</p><p><strong>저희는 그동안 충분히 많은 소셜/캠페인 프로젝트들을 통해서 와디즈 펀딩이 또 하나의 미디어로서 기능을 할 수 있다고 믿고 있습니다.&nbsp;</strong></p><p><br></p><p><br></p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>피해 주민들에게 식료품, 임시 거처 제공</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>2월 16일: 봉사활동 계획</p></li><li><p>2월 26일: 봉사활동 실행</p></li></ul>',0,'FUNDING','FUNDING_WAIT','2023-02-15 19:05:59.429044','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F6a251838-8a74-4fa3-9fd0-2eded255f9341.png','국가 재난 사태 \'강원 산불\' 구호'),(39,'<h1>프로젝트 소개</h1><p>2017년 1월부터 8월까지 전국에서 구조된 유기동물은 총 5만5천마리,</p><p>그 중에서 약 30% 이상은 안락사를 당했고,</p><p>20%는 각종 질병으로 인하여 사망을 했다고 합니다.</p><p>반려견 10마리 중 약 3마리가 주인에게 버려지고 있습니다.</p><p>안락사를 시키지 않는 사설 보호소는 언제나 물품 부족에 시달립니다.</p><p><br></p><p>우리의 작은 관심이 유기견에게는 큰 희망이 됩니다.</p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>사료 기부</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>2월 16일: 봉사활동 계획</p></li><li><p>2월 25일: 봉사활동 실행</p></li></ul>',9,'FUNDING','FUNDING_ACCEPT','2023-02-15 19:16:09.680267','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F0c52ba20-284a-4739-b020-d197436a04fa2.png','사료 기부 - 유기견을 도와주세요.'),(40,'펀딩을 통해 봉사를 간접 경험하는 것은 매우 뿌듯한 경험이었습니다.\r\n여러 단체의 봉사 인증 라이브를 보며 봉사에 직접 참여하고 싶어졌습니다.\r\n어떻게 하면 봉사에 참여할 수 있나요 ?',NULL,'ETC','QNA','2023-02-16 01:04:30.614547',NULL,'봉사에 참여하고 싶습니다.'),(41,'<p>사람들의 관심을 기다리는 철창 속 소중한 생명들</p><p>오늘날 우리는 함께 생활하며 감정을 나누는 반려동물 가족을 주위에서 쉽게 볼 수 있습니다. 늘어나는 반려동물의 수와 함께 유기 동물의 수 또한 함께 증가했습니다. 정부 통계에 따르면 연간 10만 마리 이상의 유기견이 발생하며, 지난 해에는 약 11만 2천 마리의 유기동물이 발생했다고 합니다. 이 수많은 유기견들은 따뜻한 마음을 가진 소수의 봉사자들 덕분에 지낼 곳과 먹거리를 제공받고 있지만, 하루의 대부분을 철창 속에 갇혀 살고 있으며 이들 중 절반가량이 열악한 환경 속에서 안락사 혹은 자연사를 당합니다. 그렇다면 우리가 어떻게 이 아이들에게 도움의 손길을 내 수 있을까요?</p><p><br></p><p><strong><img src=\"\" contenteditable=\"false\"><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/8a22962a-1d3c-4498-8760-4cce164a2eb5%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%842.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"></strong><br></p><p><img src=\"\" contenteditable=\"false\"><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/508b530f-aef0-4d88-b6b0-8f8d43af06f0%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%843.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p>우리나라의 주거 형태 특성상 덩치가 큰 리트리버나 진돗개보다는 소형견을 입양하려는 경향이 큽니다. 따라서 대형견은 소형견에 비해 국내에서는 입양이 원활하게 이루어지지 않아 대부분 해외 입양을 보내는 경우가 많습니다. 이때 비행기 화물로 입양을 보내면 300만 원에 육박하는 돈이 들지만, 승객의 수하물로 가게 되면 비용이 전자의 10%밖에 들지 않습니다. 그렇기에 해외의 새로운 보금자리로 개들을 보내기 위해서는 본인이 해외로 나가면서 개들도 함께 이동 시켜 줄 해외 이동 봉사자가 많이 필요한 상황입니다. 국제 청년센터는 자체 해외 네트워크를 비롯하여 미국, 캐나다의 한인 커뮤니티, 현지 대학의 한인 유학생회, 여행사 등을 통해 해외 이동 봉사자를 모집하여 국내에서는 가족을 만나지 못한 아이들의 새로운 가족을 찾기 위한 여정을 함께 하고자 합니다.</p><p><br></p><p><img src=\"\" contenteditable=\"false\"><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/a1098e29-b418-45e2-a46f-567382b878e2%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%841.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p>',6,'FUNDING','FUNDING_REJECT','2023-02-16 01:32:30.541657','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F2473a13d-83df-43f3-806a-e73d761a4eed%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80.jpg','귀여운 댕댕이들에게 더 좋은 환경을 선물해주세요!'),(42,'<p>사람들의 관심을 기다리는 철창 속 소중한 생명들</p><p>오늘날 우리는 함께 생활하며 감정을 나누는 반려동물 가족을 주위에서 쉽게 볼 수 있습니다. 늘어나는 반려동물의 수와 함께 유기 동물의 수 또한 함께 증가했습니다. 정부 통계에 따르면 연간 10만 마리 이상의 유기견이 발생하며, 지난 해에는 약 11만 2천 마리의 유기동물이 발생했다고 합니다. 이 수많은 유기견들은 따뜻한 마음을 가진 소수의 봉사자들 덕분에 지낼 곳과 먹거리를 제공받고 있지만, 하루의 대부분을 철창 속에 갇혀 살고 있으며 이들 중 절반가량이 열악한 환경 속에서 안락사 혹은 자연사를 당합니다. 그렇다면 우리가 어떻게 이 아이들에게 도움의 손길을 내 수 있을까요?</p><p><br></p><p><strong><img src=\"\" contenteditable=\"false\"><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/0e3d8133-f209-4b3a-906c-0ca6541352f5%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%842.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"></strong><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/b64005ab-aa85-4c5e-864c-1fc7c5d6f846%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%843.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"\" contenteditable=\"false\"><br></p><p>우리나라의 주거 형태 특성상 덩치가 큰 리트리버나 진돗개보다는 소형견을 입양하려는 경향이 큽니다. 따라서 대형견은 소형견에 비해 국내에서는 입양이 원활하게 이루어지지 않아 대부분 해외 입양을 보내는 경우가 많습니다. 이때 비행기 화물로 입양을 보내면 300만 원에 육박하는 돈이 들지만, 승객의 수하물로 가게 되면 비용이 전자의 10%밖에 들지 않습니다. 그렇기에 해외의 새로운 보금자리로 개들을 보내기 위해서는 본인이 해외로 나가면서 개들도 함께 이동 시켜 줄 해외 이동 봉사자가 많이 필요한 상황입니다. 국제 청년센터는 자체 해외 네트워크를 비롯하여 미국, 캐나다의 한인 커뮤니티, 현지 대학의 한인 유학생회, 여행사 등을 통해 해외 이동 봉사자를 모집하여 국내에서는 가족을 만나지 못한 아이들의 새로운 가족을 찾기 위한 여정을 함께 하고자 합니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/f249114f-47ce-4af3-a864-552bd5801f2c%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%841.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"\" contenteditable=\"false\"><br></p>',10,'FUNDING','FUNDING_ACCEPT','2023-02-16 01:59:57.385617','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F267862c9-ebe4-4a89-a508-6cac8c901b64%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80.jpg','귀여운 댕댕이들에게 더 좋은 환경을 선물해주세요!'),(43,'<p>사람들의 관심을 기다리는 철창 속 소중한 생명들</p><p>오늘날 우리는 함께 생활하며 감정을 나누는 반려동물 가족을 주위에서 쉽게 볼 수 있습니다. 늘어나는 반려동물의 수와 함께 유기 동물의 수 또한 함께 증가했습니다. 정부 통계에 따르면 연간 10만 마리 이상의 유기견이 발생하며, 지난 해에는 약 11만 2천 마리의 유기동물이 발생했다고 합니다. 이 수많은 유기견들은 따뜻한 마음을 가진 소수의 봉사자들 덕분에 지낼 곳과 먹거리를 제공받고 있지만, 하루의 대부분을 철창 속에 갇혀 살고 있으며 이들 중 절반가량이 열악한 환경 속에서 안락사 혹은 자연사를 당합니다. 그렇다면 우리가 어떻게 이 아이들에게 도움의 손길을 내 수 있을까요?</p><p><br></p><p><strong><img src=\"\" contenteditable=\"false\"></strong><br></p><p><img src=\"\" contenteditable=\"false\"><br></p><p>우리나라의 주거 형태 특성상 덩치가 큰 리트리버나 진돗개보다는 소형견을 입양하려는 경향이 큽니다. 따라서 대형견은 소형견에 비해 국내에서는 입양이 원활하게 이루어지지 않아 대부분 해외 입양을 보내는 경우가 많습니다. 이때 비행기 화물로 입양을 보내면 300만 원에 육박하는 돈이 들지만, 승객의 수하물로 가게 되면 비용이 전자의 10%밖에 들지 않습니다. 그렇기에 해외의 새로운 보금자리로 개들을 보내기 위해서는 본인이 해외로 나가면서 개들도 함께 이동 시켜 줄 해외 이동 봉사자가 많이 필요한 상황입니다. 국제 청년센터는 자체 해외 네트워크를 비롯하여 미국, 캐나다의 한인 커뮤니티, 현지 대학의 한인 유학생회, 여행사 등을 통해 해외 이동 봉사자를 모집하여 국내에서는 가족을 만나지 못한 아이들의 새로운 가족을 찾기 위한 여정을 함께 하고자 합니다.</p><p><br></p><p><img src=\"\" contenteditable=\"false\"><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/3b1b8040-361b-406b-abc7-fe128015b704%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%843.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/78cbcc8b-ffe7-45e3-9112-ee94c2d58412%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%841.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p>',4,'FUNDING','FUNDING_REJECT','2023-02-16 02:25:06.431647','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F50fe2a74-0c20-4d62-be6d-4f6c2aa55f9a%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80.jpg','귀여운 댕댕이들에게 더 좋은 환경을 선물해주세요!'),(44,'<p>람들의 관심을 기다리는 철창 속 소중한 생명들</p><p>오늘날 우리는 함께 생활하며 감정을 나누는 반려동물 가족을 주위에서 쉽게 볼 수 있습니다. 늘어나는 반려동물의 수와 함께 유기 동물의 수 또한 함께 증가했습니다. 정부 통계에 따르면 연간 10만 마리 이상의 유기견이 발생하며, 지난 해에는 약 11만 2천 마리의 유기동물이 발생했다고 합니다. 이 수많은 유기견들은 따뜻한 마음을 가진 소수의 봉사자들 덕분에 지낼 곳과 먹거리를 제공받고 있지만, 하루의 대부분을 철창 속에 갇혀 살고 있으며 이들 중 절반가량이 열악한 환경 속에서 안락사 혹은 자연사를 당합니다. 그렇다면 우리가 어떻게 이 아이들에게 도움의 손길을 내 수 있을까요?</p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/5cc39dda-31ab-453b-8d1d-a03220b968aa%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%843.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><strong><img src=\"\" contenteditable=\"false\"></strong><br></p><p><img src=\"\" contenteditable=\"false\"><br></p><p>우리나라의 주거 형태 특성상 덩치가 큰 리트리버나 진돗개보다는 소형견을 입양하려는 경향이 큽니다. 따라서 대형견은 소형견에 비해 국내에서는 입양이 원활하게 이루어지지 않아 대부분 해외 입양을 보내는 경우가 많습니다. 이때 비행기 화물로 입양을 보내면 300만 원에 육박하는 돈이 들지만, 승객의 수하물로 가게 되면 비용이 전자의 10%밖에 들지 않습니다. 그렇기에 해외의 새로운 보금자리로 개들을 보내기 위해서는 본인이 해외로 나가면서 개들도 함께 이동 시켜 줄 해외 이동 봉사자가 많이 필요한 상황입니다. 국제 청년센터는 자체 해외 네트워크를 비롯하여 미국, 캐나다의 한인 커뮤니티, 현지 대학의 한인 유학생회, 여행사 등을 통해 해외 이동 봉사자를 모집하여 국내에서는 가족을 만나지 못한 아이들의 새로운 가족을 찾기 위한 여정을 함께 하고자 합니다.</p>',11,'FUNDING','FUNDING_REJECT','2023-02-16 08:26:10.939024','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F617b33f2-7dfe-47b9-88e7-34afcabe6a4f%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80.jpg','귀여운 댕댕이들에게 더 좋은 환경을 선물해주세요!'),(45,'<p><br></p>',0,'FUNDING','FUNDING_REJECT','2023-02-16 09:16:34.520367','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2Fe0482f2b-9930-409a-a18a-87c8b5debb6b%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80.jpg','귀여운 댕댕이들에게 더 좋은 환경을 선물해주세요!'),(46,'<h1>프로젝트 소개</h1><p>프로젝트를 간단히 소개한다면?</p><p>이 프로젝트를 하면 어떤 효과를 발생시키나요?</p><p>이 프로젝트를 시작하게 된 배경이 무엇인가요 ?</p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>구체적인 항목으로 적어주세요.</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>0월 0일: 봉사활동 계획</p></li><li><p>0월 0일: 봉사활동 실행</p></li></ul><p><br></p>',2,'FUNDING','FUNDING_REJECT','2023-02-16 11:41:10.049748','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F150d55e3-508b-4b12-acc9-b8e1e70eb480%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80.jpg','강아지'),(47,'<p>임신 6개월 차에 교통사고로 남편을 잃은 진희네</p><p>가정에 찾아온 행운, 진희(가명). 어머님은 진희를 소중히 품으며 하루하루 평범한 일상을 보내고 있었습니다. 그러던 어느 날, 가정의 평화와 행복을 깨트리는 소식이 들려옵니다. 교통사고를 당해 그 자리에서 사망했다는 남편의 소식.. 임신 6개월째에 접어들고 있던 어머님은 한순간에 사랑하는 남편을 잃었습니다. 진희를 그렇게 기다리던 진희 아버님은 아이의 탄생조차 지켜보지 못하고 떠나셨습니다. 사고 이후로 급격한 변화를 맞이한 가정 상황에 어머니는 아픔을 진정시킬 시간도 없이 홀로 모든 걸 책임지셔야 했습니다. 그리고 진희가 16살이 된 지금, 지적장애 3급과 ADHD, 사춘기로 힘들어하고 있는 아이를 지켜보면서 이 모든 게 그때의 사고로 아이를 잘 챙기지 못한 본인의 잘못이라고 생각하는 어머님의 마음에는 심한 상처가 났습니다. 진희 어머님과 같이 정신적인 충격과 트라우마를 껴안게 됐지만 남겨진 아이들과 홀로 책임져야 할 생활고로 인해 마음의 상처를 돌아보지 못한 어머니들과 아버지의 부재와 어머니와의 대화 부족으로 또 다른 상처를 받고 있는 아이들이 있습니다. 시간이 아무리 지났어도 나아질 기미가 없는 마음속 상처, 영원히 치료할 수 없는 걸까요?</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/83594204-c56e-40a1-bf86-505de8d25000image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p>서로를 생각하는 \"가족“</p><p>배우자의 교통사고라는 정서적으로 심각한 일을 겪었음에도 본인보다는 아이들을 생각하며 생계를 위해 밤낮없이 일을 이어오신 어머님들이 대부분입니다. 그러다 보니 트라우마가 상처를 내고 곪게 만들어도 스스로를 둘러볼 시간과 여유조차 없었습니다. 본인 마음을 추스르기보단 가정이 무너지지 않게, 아이들이 잘 클 수 있게 지키는 게 그저 최선이라 생각하며 달려왔습니다. 아이들 또한 하고 싶은 말도, 힘든 일도 많지만 고생하는 어머니를 위해 말을 아끼게 됐습니다. 하지만 시간이 지난 지금, 잘 살피지 못한 마음의 상처는 덧나고 그로 인해 서로 간의 소통조차 어려워지는 것 같습니다. 몸은 자랐어도 마음은 아직도 아버지를 잃은 과거에 남아있는 아이들은 점점 더 마음을 닫고 그런 아이들을 바라보며 어머님들은 오늘도 무엇이 맞는 것인지 답답하고 그저 미안한 마음뿐입니다. 실제로 지난 번 심리치료를 지원받으셨던 대부분의 어머님들이 자녀와의 갈등 해결에 큰 어려움을 호소하였습니다.</p><p>교통사고로 트라우마를 갖게 된 피해가정을 도와주세요</p><p>실제로 트라우마는 단순한 상처가 아니라고 합니다. 소중한 가족을 잃은 슬픔을 잊고 생활을 이어나간다는 게 어찌 쉬울 수 있을까요? 제대로 살펴주지 않고 충분한 애도의 과정을 거치지 못한 감정은 결국 우울증을 비롯하여 부모와 자녀 관계까지 부정적인 영향을 주는 아픔이라고 합니다. 힘들고 어려운 상황 속에서도 오직 내 아이만 생각하며 한순간도 쉬지 않고 달려오셨지만 항상 가슴 깊숙이 사고와 관련된 트라우마를 안고 계시는 교통사고 피해가정을 대상으로 심리치료를 지원하고자 합니다. 음주운전, 무면허, 신호위반 등.. 타인의 잘못으로 가정의 평화를 잃은 교통사고 피해가정을 위해 따뜻한 응원과 나눔을 실천해 주세요. 모아주신 후원금은 교통사고 피해가정의 심리치료를 위해 100% 사용될 예정입니다.</p>',0,'FUNDING','FUNDING_REJECT','2023-02-16 13:35:59.330953','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F5f41c102-5f31-49af-a9c5-cf1082ecde4c%EA%B5%90%ED%86%B5%EC%82%AC%EA%B3%A0.png','교통사고 피해가정의 트라우마 치료를 도와주세요.'),(48,'<p>사람들의 관심을 기다리는 철창 속 소중한 생명들</p><p>오늘날 우리는 함께 생활하며 감정을 나누는 반려동물 가족을 주위에서 쉽게 볼 수 있습니다. 늘어나는 반려동물의 수와 함께 유기 동물의 수 또한 함께 증가했습니다. 정부 통계에 따르면 연간 10만 마리 이상의 유기견이 발생하며, 지난 해에는 약 11만 2천 마리의 유기동물이 발생했다고 합니다. 이 수많은 유기견들은 따뜻한 마음을 가진 소수의 봉사자들 덕분에 지낼 곳과 먹거리를 제공받고 있지만, 하루의 대부분을 철창 속에 갇혀 살고 있으며 이들 중 절반가량이 열악한 환경 속에서 안락사 혹은 자연사를 당합니다. 그렇다면 우리가 어떻게 이 아이들에게 도움의 손길을 내 수 있을까요?</p><p> </p><p> </p><p>우리나라의 주거 형태 특성상 덩치가 큰 리트리버나 진돗개보다는 소형견을 입양하려는 경향이 큽니다. 따라서 대형견은 소형견에 비해 국내에서는 입양이 원활하게 이루어지지 않아 대부분 해외 입양을 보내는 경우가 많습니다. 이때 비행기 화물로 입양을 보내면 300만 원에 육박하는 돈이 들지만, 승객의 수하물로 가게 되면 비용이 전자의 10%밖에 들지 않습니다. 그렇기에 해외의 새로운 보금자리로 개들을 보내기 위해서는 본인이 해외로 나가면서 개들도 함께 이동 시켜 줄 해외 이동 봉사자가 많이 필요한 상황입니다. 국제 청년센터는 자체 해외 네트워크를 비롯하여 미국, 캐나다의 한인 커뮤니티, 현지 대학의 한인 유학생회, 여행사 등을 통해 해외 이동 봉사자를 모집하여 국내에서는 가족을 만나지 못한 아이들의 새로운 가족을 찾기 위한 여정을 함께 하고자 합니다.</p><p> </p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/ca7b3db5-9a86-4cba-9082-a3c497246ad3%ED%99%9C%EB%8F%99%EC%82%AC%EC%A7%843.jpg\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p>',21,'FUNDING','FUNDING_IN_PROGRESS','2023-02-16 13:43:16.538429','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2Ff105eb4e-0e3e-4c06-b0e0-d80c681ebf87%EB%8C%80%ED%91%9C%EC%9D%B4%EB%AF%B8%EC%A7%80.jpg','귀여운 댕댕이들에게 더 좋은 환경을 선물해주세요!'),(49,'<p>가족, 친구, 연인과 함께 즐겁고 행복한 시간을 보내는 생일. 하지만 홀로 계시는 어르신들에게 생일은 어떤 의미일까요? “생일? 난 내 생일 안챙긴지 오래됐다...” “생일날 주변에서 챙겨주는 사람도 없고 외롭다.” 이렇듯 어르신들에게 생일은 그저 외롭고 평소처럼 홀로 지내는 날입니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/350cc857-3f1d-41b4-a492-bd347bda456eimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/c982cb08-afa2-48f1-a6f3-c6a9259d02c5image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p>어르신 생신! 행복한 날로 만들어주세요</p><p>매월 어르신 가정에 방문하여 생일을 챙겨드리는 일! 소소한 일이지만 어르신들에게는 큰 행복으로 다가왔습니다. “자식도 이렇게 안 하는데 챙겨줘서 고마워~ 자식보다 낫네.”, “이렇게 행복한 시간을 가질 수 있게 해줘서 고마워~” 2021년 카카오같이가치 모금을 통해 여러분의 따뜻한 마음을 전달하여 어르신의 환한 미소와 감동을 안겨드릴 수 있었습니다. 여러분의 따뜻한 마음이 담긴 모금액으로 올해도 홀몸 어르신의 가정에 케이크, 생신 선물, 자원봉사자가 만든 따뜻한 음식을 전해드리며 생신을 축하드리고자 합니다. 후원자 여러분의 따뜻한 손길로 홀몸 어르신들의 생신을 축하해주세요!</p>',22,'FUNDING','FUNDING_IN_PROGRESS','2023-02-16 14:48:54.431704','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F428f8995-8d73-4461-908f-242faa24bfe1%EB%85%B8%EC%9D%B8%EC%83%9D%EC%9D%BC.png','홀몸 어르신의 생신상 우리의 손으로 만들어가요!'),(50,'<h1>오염되고 있는 지구</h1><h5>요즈음 우리 지구가 오염되고 있습니다. 지구가 오염되면 우리 에게도 큰 피해가 있을수있습니다. </h5><h5>아무리 자연이 맑더라도 미래는 모릅니다. </h5><h5>우리 지구를 위해 모금합시다. </h5><h5>나중에 우리지구와 우리환경을 위해 기부할 예정입니다. </h5><h5>모금 열심히 또 해주세요</h5><p><br></p><p><br></p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/fdb20c5b-5bbb-41c1-9c4e-8f91134f6d49%ED%9D%AC%EB%A7%9D%EA%B3%B5%EC%9B%90.jfif\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h5>자연이 죽어가고 자연이 죽어가면 동물이 죽어가고 동물이 죽어가면 사람이 죽어갑니다. </h5><h5>그런 자연을 위해 모금해도 되지 않을까요?</h5>',4,'FUNDING','FUNDING_ACCEPT','2023-02-16 15:07:19.003679','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F0becd595-7830-40a2-bb02-6c5bba2ccf72%ED%9D%AC%EB%A7%9D%EA%B3%B5%EC%9B%90.jfif','녹산희망공원 환경미화'),(51,'<p><br></p><p>2022년 11월 기준 광주광역시에는 7,150명의 시각장애인이 등록되어 있으며(출처: 광주광역시시청 홈페이지) 이 중 900여 명이 사)광주광역시시각장애인연합회를 이용하고 계십니다. 요즘에는 선천적인 요인보다는 사고, 질병, 산업재해 등 후천적인 요인으로 인해 장애를 갖게 되는 경우가 많으며 이를 뒷받침 하듯 장애 원인의 88%가 후천적 요인(출처: KBS NEWS 2018.04.19.)이라는 보도도 있었습니다. 그렇다면 여러분은 ‘시각장애인이라면 모두가 점자를 읽을 줄 안다?’ 고 생각하시나요? 답은 아닙니다. 시각장애인 10명 중 1~2명 만이 점자를 습득하고 있으며, 본 연합회 등록 이용자 중 65세 이상 노령층이 40.5%를 차지하고 있어 고령의 이용자가 많음을 알 수 있습니다. 고령의 이용자들이 점자를 배우고자 교육에 참여하지만, 점자는 학습하는 것 자체가 복잡하고 어려울뿐더러 손끝의 감각이 둔해지고 기억력이 저하됨에 따라 점자를 습득하는 것은 매우 어렵고 더딥니다. 우리는 수많은 정보와 다양한 매체 속에 살아가고 있지만, 기술이 발전하고 문명의 혜택이 많아질수록 시각장애 고령 계층은 각종 정보의 수혜로부터 소외되고 있습니다.</p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/74ed46e9-02b3-41a5-b19d-e8ec26c60445image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/acc41569-ee3f-4967-9816-6ab96c7098c1image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p>자유롭게 독서할 권리를 보장받지 못하는 시각장애 노인을 위한 기틀 마련!</p><p>“오디오북 청취가 늘었다(출처: IT Chosun 2020.08.12.), 오디오북 베스트셀러 순위(출처: 뉴스핌 2022.01.06.)”, 그리고 전자기기가 있으면 청취할 수 있는 어플까지... 비장애인의 경우 시공간의 구애를 받지 않고 신간 또는 전공책, 원하는 도서, 베스트셀러 등 욕구에 맞는 서비스를 즉각적으로 이용할 수 있는 반면, 시각장애 고령 계층은 원하는 책을 듣고 싶어도 들을 수 있는 여건이 뒷받침되지 않습니다. 자유롭게 독서 할 권리를 보장받지 못하는 시각장애 고령 계층을 위해 “음성도서 청취용 CD겸 MP3플레이어”를 선물하여 독서에 대한 갈증을 해소하여 독서문화 활성화를 위한 기틀을 마련하고자 합니다. 독서의 기쁨과 가치를 위해 함께 동참해주세요! 점자책은 촉각으로 감지하는 속도가 느리기 때문에 눈으로 글을 읽거나 대면낭독에 비해 읽기 속도가 많이 떨어집니다. 이에 시각장애 어르신의 경우 어려운 점자책보다 음성 도서를 선호하여 점자도서관에서는 다양한 장르의 오디오북을 제작.제공하여 무료한 삶을 의미 있게, 소소한 일상을 보다 활력 있게 보내도록 지원해드리고자 합니다. 독서로 인한 기쁨이 초석이 되어 가치를 알아가는 발판이 되었으면 합니다. 마음으로 보는 시각장애인에게 독서가 더해진다면 풍성한 세상이 되지 않을까요? ‘귀로 책 읽는 시대’에 시각장애 어르신들의 독서권 보장을 위해 함께 동참해 주시고, 많은 응원 부탁드립니다!</p>',2,'FUNDING','FUNDING_ACCEPT','2023-02-16 15:14:23.877733','','시각장애 어르신에게 [특별한 음성]을 선물해주세요.'),(52,'<p>위험을 인지하지 못해요</p><p>우리 일상 속에 편리하게 사용하는 멀티탭. 하지만 노후된 멀티탭은 한 순간에 삶의 터전을 잃게 만들 수도 있습니다. 멀티탭도 주기적으로 교체해야 된다는 사실, 알고 계시나요? 한해 4만 건의 화재사고 중 상당 부분이 우리가 사용하는 전기 화재로 그 수는 무려 557건(전체 25.8%)에 달합니다. [소방본부 발표 / 2022년 화재발생 통계] - 전체 2,157건 중 주거시설(526건, 24.4%), 산업시설(418건, 19.4%), 차량(286건, 13.3%), 임야(137건, 6.4%), 기타 야외, 도로 등(258건, 12.%) - 화재 원인은 부주의가 937건(43.4%), 전기 557건(25.8%), 기계 307건(14.2%) - 인평피해는 사망 16명, 부상 70명이며, 전체 86명 중 절반인 43명(사망 10명, 부상 33명)이 주거시설에서 발생</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/f7f88fd2-95e1-4c4c-ba3c-cea5cc7df742image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p>티가 안나요</p><p>해마다 많은 멀티탭 전기사고가 발생하는데도 아직 많은 사람이 멀티탭의 위험성을 인지하지 못한 채 낡고 오래된 것을 교체하지 않고 사용하고 있는 경우가 많습니다. 주거시설의 화재의 경우 원인이 다양하겠지만, 콘센트 및 노후 멀티탭에서 발생하는 화재로 생활 터전을 잃기도 합니다. 최근 단양군 관내에서도 노후 멀티탭으로 인한 화재가 발생(22년, 단양읍 식당 화재)하기도 하였는데요. 특히, 일상생활의 장애가 있어 화재 발생 시 스스로 초기 진화가 어려운 장애인의 집에 화재가 날 경우 매우 위험한 상황에 놓이기도 합니다. 우리집 가장 작은 소화기, 새 멀티탭으로 교체해 주세요. 장애인 가정에 오래 되고 낡은 멀티탭을 교체하여 한 순간에 삶의 터전을 잃을 수 있는 먹티탭 전기 화재로 부터 안전한 생활을 할 수 있도록 멀티탭 교체 사업을 진행하고자 합니다.</p><p>멀티탭 교체 사업의 활동계획</p><p>매년 단양소방소에서 관련 교육 및 홍보사업을 진행하고 있지만, 재가장애인 가정마다 방문하여 교육을 하기에는 어려운 실정입니다. 그래서 우리 복지관에서는 복지관 방화관리자 및 지역사회 전기안전 기업과 협력하여 재가장애인의 가정에 있는 『위험한 불씨(노후 멀티탭)』 를 사전에 확인 후 교체하고, 소방교육까지 진행하여 화재 취약 환경을 개선하고자 합니다.</p>',4,'FUNDING','FUNDING_ACCEPT','2023-02-16 15:24:41.433238','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F41e1e38a-5b22-4139-bf27-1b46495701e8%ED%99%94%EC%9E%AC.png','우리가 잘 모르는 위험한 불씨를 잡아주세요'),(53,'<h5>라이브는 펀딩 완료 후 봉사 활동을 중계하기 위한 실시간 방송을 뜻합니다.</h5><h5>펀딩이 완료되면 봉사 활동과 함께 의무적으로 진행되어야 하며,</h5><h5>단체의 1인 이상이 중점적인 활동 내용을 라이브 내용에 포함시켜야 합니다.</h5><p><br></p><h5>봉사 대상 또는 제 3자가 라이브 방송에 노출될 시</h5><h5>봉사 주최 단체에서 자체적으로 동의를 구하셔야 합니다.</h5><h5><br></h5><h5>라이브를 시청하는 회원에게 후원(기프트)을 받을 수 있으며,</h5><h5>이는 단체의 마일리지에 합산됩니다.</h5><h5>기프트 형태로 받은 마일리지는 출금이 불가능하며, 이후 펀딩과 기부에 사용 가능합니다.</h5><h5><br></h5><h5>감사합니다.</h5>',NULL,'ETC','NOTICE','2023-02-17 00:02:48.898373',NULL,'라이브 규칙 안내'),(54,'<h1>프로젝트 소개</h1><p><strong>따뜻한 집안에서 사랑하는 사람들과</strong> 시간을 보내는 것은 생각만 해도 편안해지지 않나요?</p><p>쌀쌀한 날씨를 뚫고, 맘껏 눈싸움을 한 뒤,</p><p>집에 들어와 노곤하고 따뜻하게 간식들을 먹는 게 저에겐 아마 가장 큰 행복이죠 :)</p><p>이렇게 포근한 겨울을 싫어하는 사람은 없을 것이에요!</p><p><br></p><p>제가 가장 사랑하는 계절을 <strong>더 많은 아이들</strong>과 함께 누릴 수 있도록 펀딩을 열어보게 되었어요.</p><p>사실 11월쯤에 보육원 아이들에게 이쁜 크리스마스 선물을 안겨주려고 했지만,</p><p><strong>아쉽게도 펀딩 성공을 못 하게 되었죠 :(</strong></p><p>비록 크리스마스 선물을 못 안겨주었지만,</p><p>남은 겨울을 따뜻하고 행복하게 보낼 수 있도록 펀딩을 다시 열어 보아요.</p><p>보육원 아이들이 따뜻한 공간에서 행복하게 겨울을 지낼 수 있게 도와주실 수 있죠? ^^</p><p><strong>남은 겨울, 그리고 매년 다가올 매서운 겨울을 다 같이 포근하게 지내요 :)</strong></p><p><br></p><p><strong>팡이가 아이들의</strong></p><p><strong>포근한 겨울을 지킬 수 있게 도와주세요!</strong></p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p><strong>제작 비용을 제외한 모든 펀딩 금액은 아이들의 안전하고 행복한 겨울 물품을 위해 사용됩니다:)</strong></p><p>서포터님들이 키트를 펀딩하신 펀딩 금으로, 아이들한테 선물이 나누어집니다.</p><p>구체적으로, <strong>이든아이빌</strong>에 있는 아이들에게 펀딩 금으로 선물을 줄 것이에요!&nbsp;</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>2월 17일: 봉사활동 계획</p></li><li><p>2월 22일: 봉사활동 실행</p></li></ul>',15,'FUNDING','FUNDING_COMPLETE','2023-02-16 15:41:44.840735','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F04b5176f-d212-4fe9-8dba-46605c293f51%EC%83%88.png','팡이와 함께 아이들에게 행복을 전달해주세요!'),(55,'<h1>프로젝트 소개</h1><h3>치아 결손으로 식사가 어려운 분들이 많습니다</h3><p><br></p><p>\"씹는게 불편하니 끼니를 거르게 되고, 빈 속에 약을 먹게 돼.\" </p><p>\"맛있는 음식을 먹어본지가 언제 적인지 기억이 나지도 않아...\" </p><p><br></p><p>치아 결손으로 약 복용을 필수적으로 해야 하지만 식사가 불편해 끼니를 거르게 되고 빈 속에 약을 챙겨먹는 장애인분들이 계십니다. </p><p>치아가 불편해 인간의 기본적 욕구 중 하나인 식욕을 채우기에 힘든 상황인 장애인분들도 계십니다. 기본적인 끼니 해결이 어려운, 주변에 도움이 필요한 분들이 참 많습니다. </p><p>여러분의 따뜻한 손길이 따뜻한 영양죽이 되어 장애인분들에게 큰 도움이 될 수 있습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/6e16a5aa-c80a-4507-ade7-20232e196c1a%EC%82%AC%EC%A7%841.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h3>장애인 가정 및 독거세대인 분들은 매 끼니를 챙기는 것이 어렵습니다</h3><p>치매에 걸린 남편, 그리고 일상생활에 도움이 필요한 성인이 된 중증 장애인 2명과 함께 생활하고 있는 장애인 세대가 있습니다. </p><p>기초생활수급자로 정부에서 일정의 도움을 받고 있기는 하지만, 비장애인 어머니 혼자서 온 가족의 매 끼니를 해결하기에 어려움이 많은 것이 현실입니다. </p><p>가족들과 연락을 하지 않고 지내온 세월이 수 십년인 독거 장애인이 있습니다. 거동이 불편하여 매 끼니 따뜻한 밥을 차려먹기는 어렵고, 반찬을 사러 나가기도 힘이 듭니다.</p><p> 대충 인스턴트식과 라면으로 끼니를 해결하고 있지만 영양을 보충하기에는 턱 없이 부족한 음식입니다. 여러분의 따뜻한 손길이 맛있는 밑반찬이 되어 장애인분들에게 큰 도움이 될 수 있습니다.</p><p><br></p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/655539ac-3fb7-4c63-b502-4061bb12d0fa%EB%B0%A52.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><h1>프로젝트 예산</h1><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>죽 지원 15,000원*30주*5세대</p></li><li><p>밑반찬 지원 15,000원*30주*10세대</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>&lt; 사업기간 : 2023. 06. 01 ~ 2023. 12. 31 &gt;</p></li></ul>',0,'FUNDING','FUNDING_ACCEPT','2023-02-16 16:55:01.199757','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F36e17177-1eaa-4f5b-8d9e-52c738c36e32%EB%B0%A5.png','장애인들을 위한 \'행복한 밥상을\' 선물해 주세요'),(56,'<h1>프로젝트 소개</h1><h3>\"괜찮아, 네 잘못이 아니야\"</h3><p>어느 한 중증 아토피 피부를 가진 분은 아토피에 대해 이렇게 이야기를 합니다. \"죽을 병은 아니지만 죽어야 낫는 병\". 아주 이상한 병이라고요. </p><p>가려울 때는 불타고 있는 나무 장작을 댄 느낌이고, 피가 줄줄 나는데도 뼈가 보이겠다는 생각이 들 정도로 계속 긁게 되는게 바로 아토피 피부염이라고요. </p><p>남들이 보면 정말 아무것도 아닌 가벼운 피부 트러블로만 보이지만, 겪어보지 않은 사람은 감히 상상할 수 없는 매일 매순간을 가려움과의 전쟁속에서 살고 있습니다. </p><p>긁는 것을 멈추면 참을 수 없는 고통이 시작되는 이상하고도 무서운 질병이 바로 \'아토피\' 입니다. </p><p>성인들도 참기 힘든 가려움을 어린 아이들은 어떻게 견뎌야 만 할까요?</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/7025f5a8-0aee-4bb0-8890-c6fc1e988cfd%EC%95%84%ED%86%A0%ED%94%BC1.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p><br></p><h3>턱없이 부족한 정부 지원, 아토피 어린이에게 보습제를 지원해 주세요.</h3><p>아토피 피부염은 주변의 환경에 민감하게 영향을 받고 있습니다. 대기질 즉 미세먼지와 온도, 습도, 꽃가루 등이 그 대표적인 것이지요. </p><p>특히 건조한 겨울철이 되면 아토피 피부염을 앓고 있는 어린이들은 죽을 것 같은 가려움의 고통에서 감당할 수 없는 시간들로 하루하루 힘겹게 버티어 내고 있습니다. </p><p>보통 일반 성인이 사용하는 보습제 사용량과는 비교가 안되는 일반인의 15~20배에 이르는 많은 양의 보습제를 매일 수시로 전신에 발라주어야 하고, 그 양은 매주 250g 보습제 하나를 다 써야 할 정도로 어마어마합니다. 소외된 가정의 열악한 환경 속에서 삶은 하루하루도 힘에 겨운데, 가정 형편으로 인해 건조한 겨울철 가장 기본적 치료 방법인 보습제 하나도 제대로 바를 수 없어 악화와 호전을 반복하며 겪게 되는 스트레스와 그를 통해 치료마저 포기하는 마음이 생기지 않도록 여러분의 도움이 절실히 필요합니다. </p><p>꾸준하게 보습제를 발라주는 것만으로도 죽을 것 같은 가려움의 고통에서 벗어날 수 있습니다. 우리 모두의 관심이 필요합니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/e8273089-c2f9-47a2-9371-4155c1d2b0c2%EC%95%84%ED%86%A0%ED%94%BC2.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h3>우리 모두의 관심과 사랑, 그리고 위로가 필요합니다.</h3><p>특별히 잘못한 것이 있어서 생기는 질환이 아님에도 마치 내가 무엇인가를 잘못하여 친구들과는 다른 내가 되어버린 것 같은 죄책감을 느끼며 살아가지 않도록 우리 모두의 따뜻한 사랑과 지원이 절실히 필요합니다. 아토피 피부염 어린이들에게 따뜻하게 손을 내밀어 주세요. </p><p>그리고 말해주세요. \"괜찮아, 네 잘못이 아니야\" 라고.</p><p><br></p><h3>보습제 지원, 기부금 사용 계획</h3><p>보습제 지원 기부금은 아토피 피부염 진단을 받거나 전문가의 소견서를 받은 어린이를 대상으로 취약계층 가정에 보습제를 지원하는데 사용하게 됩니다. </p><p>아토피 피부염 어린이들이 끔찍한 가려움의 고통 속에서 조금이라도 편안하게 살 수 있도록 많은 지지와 응원을 부탁드립니다.</p><h1>프로젝트 예산</h1><p><br></p><p>펀딩으로 모금된 금액을 어디에 사용 예정인지 구체적으로 지출 항목으로 적어 주세요.</p><ul><li><p>보습제 구매(1가족 당 5월~12월 지원) 40만원x50가정</p></li></ul><h1>프로젝트 일정</h1><p>아래의 양식을 참고하여 작성해보세요.</p><ul><li><p>&lt; 사업기간 : 2023. 03. 17 ~ 2023. 04. 30 &gt;</p></li></ul>',0,'FUNDING','FUNDING_ACCEPT','2023-02-16 17:01:23.597039','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2Ff898ab20-c43e-4896-82a1-05ed5ad3c858%EC%95%84%EB%8F%991.png','죽을 것 같은 가려움, 아토피 어린이들에게 보습제를'),(57,'<h1>프로젝트 소개</h1><h3>아무도 찾지 않는 작은 집</h3><p>아무도 찾지 않는 6평 남짓 작은 방에서 홀로 지내고 있는 김ㅇㅇ 어르신. 어르신은 정기적으로 방문하는 사회복지사의 발걸음이 반갑기만 합니다.</p><p> \"어르신 식사는 하셨어요? 필요하신 건 없으세요?\" 라는 물음에 고개만 끄덕이며 방문만으로도 고마워하는 어르신은, 4살 때 뇌수막염으로 기관 절개를 하여 목소리가 제대로 나오지 않습니다. </p><p>어두운 방에서 평소 마주할 상대가 없어 누군가와 대화하고 싶은 마음에 오늘도 단어 하나로 힘겹게 입을 떼십니다.</p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/6ec35158-b60b-4bb8-adea-10b5a568a7e0%ED%95%A0%EB%B6%80%EC%A7%801.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h3>밑바닥이 보이는 쌀통</h3><p>올해 70살인 어르신은 소아마비로 오른쪽 손발의 경직성 마비가 심합니다. 40년 전 부모님과 사별 후 자립하여 살고 있으나 십이지장 천공이 재발하며 4번의 큰 수술을 겪고 심신이 매우 약해진 상태입니다. 좁은 집안에서 왼쪽으로 힘을 주어 움직이지만 이동조차 쉽지 않고, 심하게 굽은 오른팔로 인해 작은 물건 하나 옮기는 것도 벅차기만 합니다. </p><p>오랜 시간 홀로 지내며 누군가의 도움 없이 하루하루를 견뎌내고 계시지만 밑바닥이 보이는 쌀통을 보면 어르신의 걱정거리가 무엇인지, 어르신의 삶의 무게를 가늠할 수 있습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/95a39a33-03fd-46cf-bd2b-cf4c6fc1f9a2%ED%95%A0%EB%B6%80%EC%A7%802.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><h3>햇살 가득 채워 주세요!</h3><p>쌀에는 기본적인 영양소가 풍부해 끼니만 잘 챙겨 먹어도 건강을 유지할 수 있지만 대부분의 저소득장애인 가정에서는 신체적, 경제적 문제로 인해 기본적인 영양소조차 섭취하지 못한 채 심각한 영양 불균형으로 어려움을 겪고 계십니다. </p><p>적은 생계비로 하루 하루 생활하기도 힘들지만 정기적인 치료로 인한 병원비와 약제비, 아끼고 아껴 난방비와 식료품을 구입하고 나면 수중에 남는 돈은 얼마 되지 않습니다. </p><p>한파로 인해 유난히 힘든 겨울, 저소득 장애인 가정에 따뜻한 겨울을 보낼 수 있도록 쌀을 지원하고자 합니다. </p><p>텅 빈 쌀통 때문에 더욱 추운 겨울을 보내시지 않도록 뜻 깊은 나눔에 함께 동참해 주세요. 여러분들의 참여가 저소득 장애인에게 따뜻한 햇살이 됩니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/7e34d0ab-d0c0-448f-931b-f205e5d08798%EC%8C%80%ED%86%B5.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><h1>프로젝트 예산</h1><ul><li><p>쌀(10kg) 35,000원×150명</p></li></ul><h1>프로젝트 일정</h1><p>&lt; 사업기간 : 2023. 04. 29 ~ 2023. 05. 31 &gt;</p>',7,'FUNDING','FUNDING_ACCEPT','2023-02-16 17:06:18.166092','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F2fdf37b1-bc7f-47d0-a798-3cfa513479e2%EC%8C%801.png','텅 빈 쌀통, 따스한 햇살로 채워주세요!'),(58,'<h1>취약계층 어르신들의 소원</h1><p>“죽기 전에 하루라도 깨끗한 집에서 살아보면 좋겠네.” 문짝이 떨어져 나간 싱크대, 고장난 수전, 주저앉은 싱크대, 물이 새는 싱크대, 곰팡이로 얼룩진 벽지, 집안 곳곳에 나 있는 쥐구멍, 오래되고 삭아서 부서지는 장판 등 취약계층 어르신들의 주거환경은 너무나도 열악합니다. 그래서 깨끗한 주거 공간에서 살아보고 싶은 것이 소원이 되어버렸습니다. 주거환경이 안락하고 깨끗해야 정신적·육체적 건강이 유지되고, 윤택한 생활을 할수 있으며, 행복도 누릴수 있습니다. 취약계층 어르신들의 작은 소원을 2023년 모두모아봉사대와 기부천사님들이 함께 이뤄드렸으면 좋겠습니다.</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/7e58adf2-1130-43fd-be80-689ca00fda75image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><h1><strong>쓰레기 대청소로 집도, 마음도 환하게</strong></h1><p>정신 질환 초기 단계 어르신과 신체 유약한 어르신들 대부분은 쓰레기를 버리지 못하고 주거 공간에 쌓아두고 있는 경우가 많습니다. 필요한 살림살이 인 것 마냥, 가족인 것 마냥 마음에서부터 버리지 못해 결국은 쓰레기가 잠자리까지 침범해 버려 두 다리를 뻗고 쉴 수 있는 공간 조차 없이 방치되어 쓰레기로 인한 악취와 벌레 등으로 건강 우려가 심각한 상황입니다. 정신적, 신체적으로 유약한 어르신들이 스스로 청소를 하기에는 매우 어렵기 때문에 도움의 손길이 절실히 필요합니다.</p><p><br></p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/4e5685f1-8fee-458a-9ea1-02a68e7c7382image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p><br></p><h1>낡은 주거환경 개선으로 어르신의 근심 덜기</h1><p>깨끗한 주거환경을 제공하면 어르신들의 삶이 달라집니다. 낡은 싱크대, 오래되고 삭은 장판, 곰팡이 핀 벽지, 고장난 전등만 교체해도 죽기 전에 이런 집에서 살아본다며 어르신들은 대만족 하십니다. 싱크대를 새것으로 바꾸니 없던 입맛도 돌아오겠다며 좋아하십니다. 2023년 한해도 어르신들의 삶이 존중받을 수 있도록 관심과 응원 부탁드립니다. 어르신들께 행복한 노후를 선물해 주세요!!</p><p><br></p><p><br></p><p><br></p><p><br></p>',12,'FUNDING','FUNDING_COMPLETE','2023-02-17 00:27:37.282841','https://funteer.s3.ap-northeast-2.amazonaws.com/thumbnails/%2F3f3baad8-1fed-4926-951c-acb4e9a9ce3f%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202023-02-17%20092431.png','하루라도 깨끗한 집에서 살아보면 좋겠네'),(59,'<p>올해로 여든이 넘은 홍순덕 어르신은 류마티스 관절염으로 열 손가락의 마디가 모두 구부러졌습니다. 험한 세월 속에 홀로 남겨진 일상에서 외로운 삶이지만 밥심으로라도 버티기 위해 아픈 몸을 일으켜 밥을 짓습니다. 어르신의 세월만큼 낡고 고장 난 주방 가전과 도구들은 사용을 못 한지 오래된 것들이 대부분인데, 그나마 쓸만한 냄비 하나에 먹다 남은 찌개를 펄펄 끓여 허기를 채워봅니다. 냉기가 돌지 않고 틈이 벌어진 냉장고에서는 음식물 냄새를 맡고 나타난 초파리들이 들락날락하며 어르신을 성가시게 합니다. “다른 건 몰라도 냉장고는 바꾸고 싶어. 냉장고 물이 새서 바닥에 흥건하니까 여간 불편한 게 아니야..”</p><p><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/1e554186-4254-4c20-9f38-8e39d9e61577image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/682cc42d-74d2-4000-b734-fd31fda22f44image.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><img src=\"https://funteer.s3.ap-northeast-2.amazonaws.com/FundingDetailFiles/c3a78093-f6f7-4799-9f5e-b3fb1baf353cimage.png\" alt=\"fundingContents이미지\" contenteditable=\"false\"><br></p><p><br></p><p>손상 된 조리 도구의 위험</p><p>식품의약품안전처 보도자료(2019)에 따르면, 코팅이 벗겨진 조리 도구에서 알루미늄 용출량 증가 경향이 확인되어 새제품으로 교체할 것을 권고하였습니다. 복지관에서 사례관리와 밑반찬서비스로 만나 뵙고 있는 취약계층 어르신 댁에 방문하여 주방용품을 살펴보니, 코팅이 벗겨진 것 뿐만 아니라 오래되어 형태도 찌그러진 낡은 냄비와 프라이팬을 사용하는 가정이 상당수 있었습니다. 도마의 경우 식중독을 일으키는 살모넬라균 감염에 취약한데, 어르신들이 주로 사용하는 나무 도마는 갈라지고 곰팡이가 생긴 경우도 있었습니다. 주방 도구는 주기적으로 교체해야 하지만 어르신들은 넉넉하지 못한 주머니 탓에 아직 쓸만하다며 계속 사용하고 계셨습니다.</p><p>어르신들에게 안전한 조리 도구와 주방 가전을 선물해주세요!</p><p>경제적인 어려움으로 식생활의 ‘불편’과 ‘위험’을 감수하고 있는 소외된 이웃들이 우리 주변에 많이 있습니다. 이에 태화기독교사회복지관에서는 지역사회 취약계층 어르신들의 주방 환경 개선을 돕고자 합니다. 기본 조리 도구인 프라이팬, 냄비, 도마를 새로 구입하여 주방 위생과 중금속 노출의 위험을 줄이는데 보탬이 되고자 합니다. 또한 교체가 시급한 고장 난 냉장고, 화재의 위험을 줄이는 인덕션, 냄비 밥 대신 전기밥솥, 간편식을 데울 수 있는 전자레인지를 도움이 꼭 필요한 어르신 가정에 지원해 드리고 안전하게 사용하실 수 있도록 자주 살피겠습니다. 어르신들의 주름진 얼굴에 웃음 꽃을 피울 수 있도록 마음을 모아 후원에 동참해 주시길 부탁 드립니다.</p>',2,'FUNDING','FUNDING_WAIT','2023-02-17 00:34:18.795316','','위험천만 노후 된 주방에서 어르신을 지켜주세요!');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_attach`
--

DROP TABLE IF EXISTS `post_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_attach` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `attach_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1nsmoj0r9l4i522urarqhcxp3` (`attach_id`),
  KEY `FK722y9lttntd2rlgvat0scdu9t` (`post_id`),
  CONSTRAINT `FK1nsmoj0r9l4i522urarqhcxp3` FOREIGN KEY (`attach_id`) REFERENCES `attach` (`id`),
  CONSTRAINT `FK722y9lttntd2rlgvat0scdu9t` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_attach`
--

LOCK TABLES `post_attach` WRITE;
/*!40000 ALTER TABLE `post_attach` DISABLE KEYS */;
INSERT INTO `post_attach` VALUES (1,12,4),(3,16,8),(8,47,33),(9,50,6);
/*!40000 ALTER TABLE `post_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_hashtag`
--

DROP TABLE IF EXISTS `post_hashtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_hashtag` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hashtag_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrohvofkvcdt18d3pfk1ch9gm5` (`hashtag_id`),
  KEY `FKrk684kfi072l3a8e810ule20s` (`post_id`),
  CONSTRAINT `FKrk684kfi072l3a8e810ule20s` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `FKrohvofkvcdt18d3pfk1ch9gm5` FOREIGN KEY (`hashtag_id`) REFERENCES `hashtag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_hashtag`
--

LOCK TABLES `post_hashtag` WRITE;
/*!40000 ALTER TABLE `post_hashtag` DISABLE KEYS */;
INSERT INTO `post_hashtag` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,5),(5,1,9),(6,1,10),(7,1,11),(8,1,12),(9,1,13),(10,1,14),(11,1,15),(12,1,16),(13,1,17),(14,1,18),(15,1,19),(16,1,20),(17,1,21),(18,1,22),(19,1,23),(20,1,36),(21,1,37),(22,1,38),(23,1,39),(24,1,41),(25,1,42),(26,1,43),(27,1,44),(28,1,45),(29,1,46),(30,1,47),(31,1,48),(32,1,49),(33,1,50),(34,1,51),(35,1,52),(36,1,54),(37,1,55),(38,1,56),(39,1,57),(40,1,58),(41,1,59);
/*!40000 ALTER TABLE `post_hashtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qna` (
  `qna_id` bigint NOT NULL AUTO_INCREMENT,
  `respond` bit(1) NOT NULL,
  `id` bigint NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fip1fmygipgc2icse6kviw0y5` (`qna_id`),
  KEY `FK22kn9bnirhpqdv8ibyyo28nkr` (`user_id`),
  CONSTRAINT `FK22kn9bnirhpqdv8ibyyo28nkr` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKlhfhgjk7uf8enfw5hrmrlwlik` FOREIGN KEY (`id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (1,_binary '',7,2),(2,_binary '',34,35),(3,_binary '',40,1);
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply`
--

DROP TABLE IF EXISTS `reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reply` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `qna_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK17d6c6upfkj2vi2wv50lx1r2l` (`qna_id`),
  CONSTRAINT `FK17d6c6upfkj2vi2wv50lx1r2l` FOREIGN KEY (`qna_id`) REFERENCES `qna` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply`
--

LOCK TABLES `reply` WRITE;
/*!40000 ALTER TABLE `reply` DISABLE KEYS */;
INSERT INTO `reply` VALUES (1,'관심주셔서 감사합니다.\n최대한 빠른 시일 내에 준비하도록 하겠습니다.','2023-02-15 13:06:35.896006',7),(2,'현재 제출물 검토 중에 있습니다. 감사합니다.','2023-02-15 18:45:06.740743',34),(3,'저희는 봉사 플랫폼이 아닌 기부형 펀딩 크라우드를 운영하는 사이트 입니다.\n단체에 직접 문의 하셔야합니다.','2023-02-16 01:06:38.047260',40);
/*!40000 ALTER TABLE `reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `reject_reason` varchar(255) DEFAULT NULL,
  `funding_id` bigint DEFAULT NULL,
  `recipt_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKln3x7nsn3niu75x5bnvlcdfxx` (`funding_id`),
  KEY `FKmoo67ttdvgvsqoxcfvbwng6ru` (`recipt_id`),
  CONSTRAINT `FKln3x7nsn3niu75x5bnvlcdfxx` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`),
  CONSTRAINT `FKmoo67ttdvgvsqoxcfvbwng6ru` FOREIGN KEY (`recipt_id`) REFERENCES `attach` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_detail`
--

DROP TABLE IF EXISTS `report_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `report_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1wd9naj55he1wmvmwipia3aly` (`report_id`),
  CONSTRAINT `FK1wd9naj55he1wmvmwipia3aly` FOREIGN KEY (`report_id`) REFERENCES `report` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_detail`
--

LOCK TABLES `report_detail` WRITE;
/*!40000 ALTER TABLE `report_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `report_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `target_money`
--

DROP TABLE IF EXISTS `target_money`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `target_money` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` int NOT NULL,
  `target_money_type` varchar(255) DEFAULT NULL,
  `funding_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbps4pvdlec6fsccjt2p48hkog` (`funding_id`),
  CONSTRAINT `FKbps4pvdlec6fsccjt2p48hkog` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target_money`
--

LOCK TABLES `target_money` WRITE;
/*!40000 ALTER TABLE `target_money` DISABLE KEYS */;
INSERT INTO `target_money` VALUES (1,1500000,'LEVEL_ONE',1),(2,5000000,'LEVEL_TWO',1),(3,10000000,'LEVEL_THREE',1),(4,500000,'LEVEL_ONE',2),(5,1200000,'LEVEL_TWO',2),(6,2000000,'LEVEL_THREE',2),(7,100000,'LEVEL_ONE',3),(8,200000,'LEVEL_TWO',3),(9,300000,'LEVEL_THREE',3),(10,100000,'LEVEL_ONE',5),(11,1500000,'LEVEL_TWO',5),(12,1500000000,'LEVEL_THREE',5),(13,1000000,'LEVEL_ONE',9),(14,3000000,'LEVEL_TWO',9),(15,7500000,'LEVEL_THREE',9),(16,300000,'LEVEL_ONE',10),(17,500000,'LEVEL_TWO',10),(18,1500000,'LEVEL_THREE',10),(19,2000000,'LEVEL_ONE',11),(20,6000000,'LEVEL_TWO',11),(21,10000000,'LEVEL_THREE',11),(22,3000000,'LEVEL_ONE',12),(23,5000000,'LEVEL_TWO',12),(24,8000000,'LEVEL_THREE',12),(25,3000000,'LEVEL_ONE',13),(26,5000000,'LEVEL_TWO',13),(27,10000000,'LEVEL_THREE',13),(28,1000000,'LEVEL_ONE',14),(29,3000000,'LEVEL_TWO',14),(30,5000000,'LEVEL_THREE',14),(31,1000000,'LEVEL_ONE',15),(32,3000000,'LEVEL_TWO',15),(33,7000000,'LEVEL_THREE',15),(34,1000000,'LEVEL_ONE',16),(35,1500000,'LEVEL_TWO',16),(36,3000000,'LEVEL_THREE',16),(37,300000,'LEVEL_ONE',17),(38,500000,'LEVEL_TWO',17),(39,5000000,'LEVEL_THREE',17),(40,3000000,'LEVEL_ONE',18),(41,4000000,'LEVEL_TWO',18),(42,5000000,'LEVEL_THREE',18),(43,814000,'LEVEL_ONE',19),(44,1000000,'LEVEL_TWO',19),(45,5000000,'LEVEL_THREE',19),(46,246000,'LEVEL_ONE',20),(47,1000000,'LEVEL_TWO',20),(48,1500000,'LEVEL_THREE',20),(49,1600000,'LEVEL_ONE',21),(50,1700000,'LEVEL_TWO',21),(51,2300000,'LEVEL_THREE',21),(52,1225000,'LEVEL_ONE',22),(53,1775000,'LEVEL_TWO',22),(54,3685000,'LEVEL_THREE',22),(55,847300,'LEVEL_ONE',23),(56,3000000,'LEVEL_TWO',23),(57,15000000,'LEVEL_THREE',23),(58,10000000,'LEVEL_ONE',36),(59,30000000,'LEVEL_TWO',36),(60,50000000,'LEVEL_THREE',36),(61,10000000,'LEVEL_ONE',37),(62,30000000,'LEVEL_TWO',37),(63,50000000,'LEVEL_THREE',37),(64,10000000,'LEVEL_ONE',38),(65,30000000,'LEVEL_TWO',38),(66,50000000,'LEVEL_THREE',38),(67,10000000,'LEVEL_ONE',39),(68,20000000,'LEVEL_TWO',39),(69,30000000,'LEVEL_THREE',39),(70,500000,'LEVEL_ONE',41),(71,1000000,'LEVEL_TWO',41),(72,3000000,'LEVEL_THREE',41),(73,500000,'LEVEL_ONE',42),(74,1000000,'LEVEL_TWO',42),(75,3000000,'LEVEL_THREE',42),(76,500000,'LEVEL_ONE',43),(77,1000000,'LEVEL_TWO',43),(78,3000000,'LEVEL_THREE',43),(79,500000,'LEVEL_ONE',44),(80,1000000,'LEVEL_TWO',44),(81,3000000,'LEVEL_THREE',44),(82,500000,'LEVEL_ONE',45),(83,1000000,'LEVEL_TWO',45),(84,3000000,'LEVEL_THREE',45),(85,500000,'LEVEL_ONE',46),(86,1000000,'LEVEL_TWO',46),(87,3000000,'LEVEL_THREE',46),(88,300000,'LEVEL_ONE',47),(89,500000,'LEVEL_TWO',47),(90,1000000,'LEVEL_THREE',47),(91,500000,'LEVEL_ONE',48),(92,1000000,'LEVEL_TWO',48),(93,3000000,'LEVEL_THREE',48),(94,200000,'LEVEL_ONE',49),(95,300000,'LEVEL_TWO',49),(96,500000,'LEVEL_THREE',49),(97,3000,'LEVEL_ONE',50),(98,10000,'LEVEL_TWO',50),(99,30000,'LEVEL_THREE',50),(100,700000,'LEVEL_ONE',51),(101,1000000,'LEVEL_TWO',51),(102,1300000,'LEVEL_THREE',51),(103,300000,'LEVEL_ONE',52),(104,500000,'LEVEL_TWO',52),(105,1000000,'LEVEL_THREE',52),(106,500000,'LEVEL_ONE',54),(107,1000000,'LEVEL_TWO',54),(108,2000000,'LEVEL_THREE',54),(109,2250000,'LEVEL_ONE',55),(110,4500000,'LEVEL_TWO',55),(111,6000000,'LEVEL_THREE',55),(112,20000000,'LEVEL_ONE',56),(113,40000000,'LEVEL_TWO',56),(114,60000000,'LEVEL_THREE',56),(115,5250000,'LEVEL_ONE',57),(116,10500000,'LEVEL_TWO',57),(117,13000000,'LEVEL_THREE',57),(118,2000000,'LEVEL_ONE',58),(119,3000000,'LEVEL_TWO',58),(120,4500000,'LEVEL_THREE',58),(121,6000000,'LEVEL_ONE',59),(122,9300000,'LEVEL_TWO',59),(123,11000000,'LEVEL_THREE',59);
/*!40000 ALTER TABLE `target_money` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `target_money_detail`
--

DROP TABLE IF EXISTS `target_money_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `target_money_detail` (
  `id` bigint NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `target_money_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd8uaq119a37s1nkhejgq7fvtv` (`target_money_id`),
  CONSTRAINT `FKd8uaq119a37s1nkhejgq7fvtv` FOREIGN KEY (`target_money_id`) REFERENCES `target_money` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `target_money_detail`
--

LOCK TABLES `target_money_detail` WRITE;
/*!40000 ALTER TABLE `target_money_detail` DISABLE KEYS */;
INSERT INTO `target_money_detail` VALUES (1,'참여기관 모집용 홍보비(문자, 팩스, 우편 발송 등) 50,000원/회×15회=750,000원',1),(2,'교육 참여 기관 및 가로수 보호 활동지 사전답사, 활동 진행 유류비 30,000원/회×5회/곳×5곳=750,000원',1),(3,'환경교육 진행 강사비 50,000원/시간×3시간/명×2명/회×5회=1,500,000원',2),(4,'참여자 착용 안전용품 및 소모품 구입비 (팔토시, 장갑, 색연필, 물감, 손소독제, 붓, 활동용 깔개 등) 20,000원/명×100명=2,000,000원',2),(5,'활동 물품 구매비 (양말목, 폐목재 재활용 팻말, 친환경 목재 표면 도포제 등) 50,000원/명×100명=5,000,000원',3),(6,'영양죽 구입',4),(7,'반찬거리 재료 구입',5),(8,'조리 시설 대여',5),(9,'영양제 구입',6),(10,'쓰레기봉투 구매',7),(11,'청소용구 구매',7),(12,'밥 차',8),(13,'도시락',8),(14,'삼겹살',9),(15,'ㄴㄴ',10),(16,'ㄴㄴ',11),(17,'ㄴㄴ',12),(18,'야생동물 촬영을 위한 무인센서 카메라 구입 (4대)',13),(19,'셀프 투어 가이드북 제작 (400mmX250mm, 양면컬러, 200부)',13),(20,'셀프 투어를 위한 QR코드 컨텐츠 제작 (음성 녹음 / 1.5분X20건)',14),(21,'셀프 투어를 위한 QR코드 우드판넬 제작 (20개)',14),(22,'셀프 투어를 위한 QR코드 컨텐츠 제작 (동영상)',15),(23,'어르신들 생일 케이크 구매',16),(24,'어르신들 생일 축하 파티 장소 대여',17),(25,'어르신들 문화생활 (연극 비용)',18),(26,'센터 내 기본적인 인테리어 공사',19),(27,'24시간 보육교사 채용',20),(28,'새로운 보금자리 계약',21),(29,'생필품 구매',21),(30,'휠체어용 버스 대여',22),(31,'안전요원 채용',23),(32,'숙박 시설',24),(33,'식대',24),(34,'가로수 옷 설치',25),(35,'가로수 쓰레기 처리 비용',26),(36,'쓰레기 투기 막는 가로수 팻말 설치',27),(37,'쓰레기 처리 비용',28),(38,'하천까지 버스 대여 비용',29),(39,'EM흙공 제작',29),(40,'흙공 추가 제작',30),(41,'지역 확대에 따른 교통 비용',30),(42,'교육 장소 대여',31),(43,'교육용 컴퓨터 대여 비용',32),(44,'선생님 추가 채용',33),(45,'교육용 랩탑 제공',33),(46,'단체 교육 시설 대여',34),(47,'교재비 및 학용품 구입',35),(48,'온라인 교육 제공',36),(49,'다양한 과목 교육',36),(50,'야생동물 보호를 위한 동물권 교육',37),(51,'동물보호를 위한 책자 등 인쇄물',38),(52,'동물 사육시설 조사 및 미디어자료 제작',39),(53,'구조물품 구입, 장비 대여 및 관리비',40),(54,'피학대동물 구호 이동비',41),(55,'동물학대 방지 리플릿 인쇄비',42),(56,'미디어콘텐츠 제작',43),(57,'아카이브북 원고료',44),(58,'동물권 이슈 캠페인 영상 제작',45),(59,'유기동물 입양 음원 제작',46),(60,' 입양파티 및 캠페인 홍보',46),(61,'현장 촬영장비',47),(62,'\'입양ON 펫샵OFF\' 캠페인 리플릿 등 홍보물 제작',48),(63,'야생동물 탐사단',49),(64,'야생조류 충돌 시민참여 모니터링',50),(65,'멸종위기 야생동물 서식지 모니터링 및 취재',51),(66,'두뇌훈련 강사비 75,000원*1명*13회',52),(67,'두뇌훈련 재료비 25,000원*10명*1회',52),(68,'치매교육 다과비 5,000원*11명*1회',53),(69,'힐링원예 강사비 85,000원*1명*13회',54),(70,'힐링원예 재료비 10,000원*10명*13회',54),(71,'지원 가정 물품 지원',55),(72,'사무관리비',56),(73,'세대 공사비, 실사비, 인건비',57),(74,'음식 및 물 기부',58),(75,'침구류 제공',59),(76,'침구류 및 식료품 추가 제공',60),(77,'음식 및 물 기부',61),(78,'침구류 제공',62),(79,'침구류 및 식료품 추가 제공',63),(80,'음식 및 물 기부',64),(81,'침구류 제공',65),(82,'침구류 및 식료품 추가 제공',66),(83,'사료 기부',67),(84,'사료 추가 기부',68),(85,'사료 추가 기부',69),(86,'강아지 사료 및 간식 구입',70),(87,'강아지용 방한 제품 구입',71),(88,'강아지 케이지 구입',72),(89,'강아지 미용 비용',72),(90,'강아지 사료 구매',73),(91,'강아지 방한용품 구입',74),(92,'튼튼한 강아지 케이지 구매',75),(93,'강아지 미용',75),(94,'강아지용 방한 제품',76),(95,'강아지 청소 제품',77),(96,'강아지 케이지 구입',78),(97,'사료',79),(98,'방한 제품',80),(99,'새로운 케이지 및 미용',81),(100,'사료',82),(101,'목줄',83),(102,'케이지',84),(103,'사료',85),(104,'목줄',86),(105,'케이지',87),(106,'아이와 엄마의 상담비',88),(107,'아이의 보육비',89),(108,'지역 보육교사비',90),(109,'사료',91),(110,'방한 제품',92),(111,'케이지',93),(112,'상차림비용',94),(113,'생필품 및 선물',95),(114,'어르신 문화생활 비용(연극)',96),(115,'종량제 봉투 구매',97),(116,'환경미화 관련 포스터 제작',98),(117,'환경미화 교육활동',99),(118,'가장 기본적인 기능 구현된 제품',100),(119,'사용자 편의를 위한 음성 인식 기술 추가',101),(120,'기기 보험',102),(121,'기기 사용법 배우는 교실에 참여',102),(122,'인터넷 화재 교육 이수',103),(123,'멀티탭 화재 방지 기기 구입',104),(124,'지자체 화재 교육 진행',105),(125,'아동 선물',106),(126,'아동 선물',107),(127,'아동 선물',108),(128,'죽 지원 15,000원*30주*5세대',109),(129,'죽 지원 15,000원*30주*5세대',110),(130,'밑반찬 지원 15,000원*30주*10세대',110),(131,'죽 지원 15,000원*30주*10세대',111),(132,'밑반찬 지원 15,000원*30주*20세대',111),(133,'보습제 구매(1가족 당 5월~12월 지원) 40만원x50가정',112),(134,'보습제 구매(1가족 당 5월~12월 지원) 40만원x100가정',113),(135,'보습제 구매(1가족 당 5월~12월 지원) 40만원x150가정',114),(136,'쌀(10kg) 35,000원×150명',115),(137,'쌀(10kg) 35,000원×300명',116),(138,'쌀(10kg) 35,000원×170명',117),(139,'싱크대 제작(2세대)',118),(140,'벽지,장판 교체(3세대)',119),(141,'부재료(생필품지원, 청소도구 등)',120),(142,'봉사자 간식비',120),(143,'가스레인지 및 기본적인 조리 기기',121),(144,'조리용 냄비 구입',122),(145,'청소업체 비용',123);
/*!40000 ALTER TABLE `target_money_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `description` varchar(255) DEFAULT NULL,
  `last_activity` datetime(6) DEFAULT NULL,
  `total_funding_amount` bigint DEFAULT NULL,
  `id` bigint NOT NULL,
  `team_banner` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqwmg5lta6pgsmop2mxgqqvwhu` (`team_banner`),
  CONSTRAINT `FKi2ii4nnkgbamscnbpjdhg1aac` FOREIGN KEY (`id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqwmg5lta6pgsmop2mxgqqvwhu` FOREIGN KEY (`team_banner`) REFERENCES `attach` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (NULL,'2023-02-15 06:02:35.283511',0,8,NULL),('모두가 함께하는 미래를 위해 항상 노력하는 녹산 봉사단입니다.\r\n매주 토요일 봉사를 진행합니다. \r\n봉사에 참여하고 싶으신 분은 010-1111-1111 로 연락해주시길 바랍니다.','2023-02-15 06:02:38.946043',0,9,NULL),(NULL,'2023-02-15 06:56:38.623952',300000,11,NULL),(NULL,'2023-02-15 06:56:33.588345',0,12,NULL),(NULL,'2023-02-16 06:56:33.588345',0,23,NULL),('모든 인권을 소중히 여기고 봉사하는 마음을 가지고 있습니다.\r\n특히 장애인들의 복지에 관심을 가지고 있는 단체입니다.','2023-02-16 01:18:25.467253',0,35,NULL),(NULL,'2023-02-16 01:18:06.599890',0,36,NULL),(NULL,'2023-02-16 01:17:44.775532',0,37,NULL),(NULL,'2023-02-16 04:54:23.152227',0,39,NULL),(NULL,'2023-02-16 04:54:19.862976',0,41,NULL),(NULL,NULL,0,42,NULL),(NULL,'2023-02-16 13:44:52.261829',0,44,NULL),(NULL,NULL,0,46,NULL),(NULL,NULL,0,47,NULL),(NULL,NULL,0,105,NULL),(NULL,NULL,0,106,NULL);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_attach`
--

DROP TABLE IF EXISTS `team_attach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_attach` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `confirm` bit(1) NOT NULL,
  `attach_id` bigint DEFAULT NULL,
  `team_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhwux48crfyr95rewk9ey2vg61` (`attach_id`),
  KEY `FKk2q9tmrgy0pod8qe8dx1ewo2g` (`team_id`),
  CONSTRAINT `FKhwux48crfyr95rewk9ey2vg61` FOREIGN KEY (`attach_id`) REFERENCES `attach` (`id`),
  CONSTRAINT `FKk2q9tmrgy0pod8qe8dx1ewo2g` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_attach`
--

LOCK TABLES `team_attach` WRITE;
/*!40000 ALTER TABLE `team_attach` DISABLE KEYS */;
INSERT INTO `team_attach` VALUES (1,_binary '\0',2,8),(2,_binary '\0',3,8),(3,_binary '\0',4,9),(4,_binary '\0',5,9),(5,_binary '\0',7,11),(6,_binary '\0',8,11),(7,_binary '\0',9,12),(8,_binary '\0',10,12),(9,_binary '\0',17,23),(10,_binary '\0',18,23),(11,_binary '\0',19,35),(12,_binary '\0',20,35),(13,_binary '\0',23,36),(14,_binary '\0',24,36),(15,_binary '\0',27,37),(16,_binary '\0',28,37),(17,_binary '\0',31,39),(18,_binary '\0',32,39),(19,_binary '\0',35,41),(20,_binary '\0',36,41),(21,_binary '\0',38,42),(22,_binary '\0',39,42),(23,_binary '\0',43,44),(24,_binary '\0',44,44),(25,_binary '\0',52,46),(26,_binary '\0',53,46),(27,_binary '\0',55,47),(28,_binary '\0',56,47),(29,_binary '\0',115,105),(30,_binary '\0',116,105),(31,_binary '\0',118,106),(32,_binary '\0',119,106);
/*!40000 ALTER TABLE `team_attach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiZXhwIjoxNjc2NjgwNzMzfQ.CmJvWQUlpLG7GuFf4NP2sgnXO_703vVtDvWbTNEpmurpWTUbkPS43ZTxLGTqx3za88DdtW_FxAdxmy2u9Tv7zw'),(2,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiZXhwIjoxNjc2NjgyMzIxfQ.7JbqxmKg1YeBsOJH67Gj9rfEuzYyrE4EXNncfqJ_fOSwh4ROglWA89WT0beMll-yN-ikPnLXhi3NLz5KNdbhPg'),(13,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMyIsImV4cCI6MTY3NjY4Mjc4OH0.hGod31bXx8k4Ul-uzmrANnVazxpSrs6tvITDo522lCvBUFUfraHTU-InUuhwR1llixyDiq8YOc4J-auXcxj1cQ'),(14,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxNCIsImV4cCI6MTY3NjUzMDU3OH0.A0t0TLOsppVWSQxNO5oWrPAOyMczjafNsy1qA5QEz1X8OwsrnARl-wG6-V-WrevU8GZ1U6qyJpxaWE0MUxre9w'),(24,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyNCIsImV4cCI6MTY3NjY4Mjg3M30.ML0-cCKheibMPDxF-LABNtt5xLtLFYMajgcVtkkQAlLDXhJqHOGKI-0v-IKX_8qxFUqj9ovdmlQ5szXUHIizuQ'),(35,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzNSIsImV4cCI6MTY3NjY4MzE0M30.7BIHf32nBSGz6C5gME4Ecwo9mSnO7fBSUzX_zX1obDFTUexBQl3E4HhF3eCWOtHcDSBcUz0FGRlKi1huAaKjHw'),(36,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzNiIsImV4cCI6MTY3NjU3MzUxNH0.W5iwYZxthui2XBvZiE4Ldv2O3_3HpES9k3jK7Gy4r_qV1UB6K1ykwkJu1YJQLJNA7cqCl1Q6fi-zHiyhn_Ef_g'),(41,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0MSIsImV4cCI6MTY3NjYxMTA5M30.6Y6xGUEMPq_tCczR_YC5jR4FSQHmOIlIkSUxCyytc2v6OVNGIuUqAjeXcXjhoSZcqGVc2Inmsw6pnifdWJ9g7w'),(45,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NSIsImV4cCI6MTY3NjY2NDEyM30.eXsOSew2H2NcEGj6LofwauQd0Gxhb_vshxBLpBABXeAqktDs21fR1vNeKxZJ-raJ2MN4YisF_xCtYS3q319p5Q'),(46,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NiIsImV4cCI6MTY3NjY4MjE3M30.6pLDGH4K_7Q-cyRYF1E56Pr09nuPlEQ_yJG8z_Zc_MTLh0SphE2LJvPmF_GTjSU3Z_LrZ74Fw4ycflq0umS5iA'),(47,'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NyIsImV4cCI6MTY3NjY3OTg0NX0.3cMOe1dWNd7HL0Ns_2Ek5ImM72oqhZLZIaAkoBBzudWFQQTqpDc6C2XX6Uxn1qCAeFb5R8kpcezXdh3lxgizgg');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `money` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `reg_date` datetime(6) DEFAULT NULL,
  `total_pay_amount` bigint DEFAULT NULL,
  `user_type` varchar(255) NOT NULL,
  `profile_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_589idila9li6a4arw1t8ht1gx` (`phone`),
  KEY `FKhmr6ljm9ksvqfhhmwi13vvta3` (`profile_id`),
  CONSTRAINT `FKhmr6ljm9ksvqfhhmwi13vvta3` FOREIGN KEY (`profile_id`) REFERENCES `attach` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'bbookng@gmail.com',8723966,'김보경','{bcrypt}$2a$10$kzBDt7dgW6ybHGMKkXpHtelwbuITMByEGueRvEH8GkkMF.A8Il8pe','010-8956-6525','2023-02-15 05:20:46.967435',NULL,'NORMAL',1),(2,'99rlthdqls@naver.com',5000,'비니비니빔','{bcrypt}$2a$10$VYi/b/k2vZKznS/uCtaUtuAcF/.xmvAfTV7mF/d25HOjOQUetMAr.','010-1231-2312','2023-02-15 05:24:24.496681',NULL,'NORMAL',NULL),(3,'yamyambuk04@gmail.com',0,'관리자','{bcrypt}$2a$10$8mf3iaOHqliIwSDFDyemCuwIs3wyXbeeAQEisEj3MmcVer0ncR6cy','010-8956-6526','2023-02-15 05:28:23.953365',NULL,'ADMIN',NULL),(4,'bbookng@naver.com',0,'별빛봉사단','{bcrypt}$2a$10$Q/XKOCBoF1i6/QJcnRSJeOdvrzvlM/Tha7/7hqmJdDNTADTMa31Ha','010-1111-2222','2023-02-15 05:39:49.473461',NULL,'NORMAL',NULL),(8,'rlthdqls@kookmin.ac.kr',6000,'국민봉사단','{bcrypt}$2a$10$8t6fi1dYlQfM.MpJSooGXuSeumbKAT7m3LecMAoCU7k/tQB7yfwPq','010-1231-2333','2023-02-15 05:44:49.451288',NULL,'TEAM',NULL),(9,'siged21077@laserlip.com',9070000,'녹산봉사단','{bcrypt}$2a$10$jm9c9t9xCT8d9vlCv9bY5uqSGanDXIjSb3nWMApgJh/ugeTKm53Ky','010-3333-4444','2023-02-15 05:44:59.354883',NULL,'TEAM',15),(11,'bbookng@pusan.ac.kr',607469,'practicePT','{bcrypt}$2a$10$4palkketoIWUH73dfv.cuuuGr53lcK1BOep2lnm58Im9bgFbYhQGe','010-2342-3544','2023-02-15 06:52:15.185669',NULL,'TEAM',NULL),(12,'yaxil70689@laserlip.com',0,'부울경짱','{bcrypt}$2a$10$eE0Im.Yewn85mNDjDQlzKe3Oq7VShRR2mDO1pJoZL6twrN.fZkQMy','010-1234-1255','2023-02-15 06:55:22.919763',NULL,'TEAM',NULL),(13,'ddings7303@gmail.com',9000,'안명수','{bcrypt}$2a$10$otIZI5QicCxXc2nj.sPyq.9egxV1H2RANc8EJB2Y67ihz6mFT.f1C','010-6286-7397','2023-02-15 06:56:17.597489',NULL,'NORMAL',NULL),(14,'gedaveg190@otanhome.com',0,'김김승승섭섭','{bcrypt}$2a$10$TKFriDpbTaaJSuAckrmZvOf3obeSlcKKZvOZMRkP2X0eNb6uR99ee','010-7777-7777','2023-02-15 06:56:18.491195',NULL,'NORMAL',NULL),(15,'dkdpdl12311@gmail.com',391319,'김진호','{bcrypt}$2a$10$JSsdRQ8G08GMBBUqzouafOSsC9Ivpilp8IGjmtG95tJI.8T9TdRfu','010-9805-7346','2023-02-15 06:56:29.940913',NULL,'NORMAL',11),(23,'mabadi5260@laserlip.com',0,'녹산동물보호단체','{bcrypt}$2a$10$fo6fbC2lEBJPfUOsDJh3DOWIVPH/QJQEMTmVfBSYhEoCojg5CK4Fm','010-1773-3549','2023-02-15 15:36:10.028244',NULL,'TEAM',NULL),(24,'becoding96@gmail.com',800000,'백준봉','{bcrypt}$2a$10$LG9/kfNk0Xm/TyaqB.argOzixY2/n/6iEc5orj1QdK6b3OrNJGN.m','010-2992-6824','2023-02-15 17:51:34.730301',NULL,'NORMAL',25),(35,'bjb2992@naver.com',0,'신호희망봉사단','{bcrypt}$2a$10$MIbS7L/fNzjedoogRqIZXOUtb190vKHRMR/zzs7VPIJjHXmaVO9by','010-2992-6823','2023-02-15 18:03:49.207824',NULL,'TEAM',48),(36,'begin96955@mustbeit.com',0,'강서희망봉사단','{bcrypt}$2a$10$gxPDJp1RSRbo7vzBOc812.7coNr8typ1S0g92/67sjUbGXCPwvfTq','010-2992-6822','2023-02-15 18:51:53.726150',NULL,'TEAM',NULL),(37,'yonogin971@laserlip.com',0,'what','{bcrypt}$2a$10$DE2h3KKhbyq1JVeqPO3AGutOBg3wPysPr8eADOjt4t/gQpsRQ7JR.','010-2222-3333','2023-02-16 01:16:30.498302',NULL,'TEAM',26),(38,'jidera5502@mirtox.com',0,'코치님_개인','{bcrypt}$2a$10$QBJTdm9q8vQncP55cMbTNOkDAbGHyMm1FXj6O5EGDfVVuo9argAVS','010-2222-2222','2023-02-16 04:32:48.818736',NULL,'NORMAL',29),(39,'tayabo9910@mirtox.com',0,'코치님_단체','{bcrypt}$2a$10$k/M07AsgHMNS4cKGApYqWuLUN.5SYJApmOUeTLHaHa6zDgDq9tpmi','010-2222-2223','2023-02-16 04:40:26.484522',NULL,'TEAM',30),(40,'consultant204@ssafy.com',0,'컨설턴트님_개인','{bcrypt}$2a$10$KovAndSJKL4hlzWFh.rC0Oqq162A/Ox.dTiPEMOGM6IzXoIee7H/y','010-1111-1111','2023-02-16 04:43:06.126911',NULL,'NORMAL',33),(41,'teamconsultant204@ssafy.com',0,'컨설턴트님_단체','{bcrypt}$2a$10$9FdQC7m2SpT3oifUTdhnpOGzGRWpB/Wlkr21xbBD9ulY3Ak0fice2','010-1111-1112','2023-02-16 04:44:36.285237',NULL,'TEAM',34),(42,'admincoach@ssafy.com',0,'코치님_관리자','{bcrypt}$2a$10$1wGBVEppU0UfUJa.JJccE.NwqgpQ9QSk8B020XRDp2J/YaAfAqXaW','010-1111-1113','2023-02-16 04:50:55.351888',NULL,'ADMIN',37),(43,'adminconsultant204@ssafy.com',0,'컨설턴트님_관리자','{bcrypt}$2a$10$m96zd819ArYNDA24g4witu/29hYsnVvUqWRVDOgFIH8bPEUenp2Wi','010-1111-1114','2023-02-16 04:53:17.468185',NULL,'ADMIN',40),(44,'jekate6620@laserlip.com',0,'냥멍봉사단','{bcrypt}$2a$10$Ix4DjYdSpsIpt5pLPx9ZpumzSsD69DboxXZ/CxHbcaGVABuDOM.6O','010-1234-1223','2023-02-16 13:27:14.251837',NULL,'TEAM',42),(45,'sub9707@naver.com',0,'김승섭','{bcrypt}$2a$10$lo6Ok732u0bCpZx.fPUk2OyVeSf8qfbTcQsRkaDE2XQojirs7ufSO','010-9404-3714','2023-02-16 19:25:07.508964',NULL,'NORMAL',49),(46,'ysflower1@gmail.com',0,'부산대학교입학알림단','{bcrypt}$2a$10$MbozJxpw6DseSUJ2mV/Sz.tK1ufiiP4wSZDR4p3kLiakMFCwSyUuu','010-0606-0808','2023-02-17 00:21:04.024729',NULL,'TEAM',51),(47,'songb1443@gmail.com',0,'예삐네모금','{bcrypt}$2a$10$TQ/lohHIDoHXYFWZ0iqBGe9s0ak9B8ZbNbMNjsqbX3Q1U7UHNcs.e','010-4521-7845','2023-02-17 00:24:05.163703',NULL,'TEAM_WAIT',54),(104,'elfn7397@naver.com',0,'안명수','{bcrypt}$2a$10$vbbnBx19Ofb/XHHDQTuNtu/Hz/AFXZtISCX3r2SHJMKrLsYzrvc/K',NULL,'2023-02-17 01:00:11.606212',NULL,'KAKAO',113),(105,'zhqp2992@gmail.com',0,'싸피봉사단','{bcrypt}$2a$10$wcn5BCkYM6bPAXdoE6psG.AEhL5dPvHjmGfbLu2FR2P9EJHTTnVvG','010-2222-2424','2023-02-17 01:07:45.245038',NULL,'TEAM_WAIT',114),(106,'bjb2992@kakao.com',0,'푸른나무봉사단','{bcrypt}$2a$10$2lXWls2q31Y7vpCDksbo2epJgpz88ghmaFJPz6pO/lKAaWROlmFam','010-3333-6824','2023-02-17 01:13:02.366180',NULL,'TEAM_WAIT',117);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_badge`
--

DROP TABLE IF EXISTS `user_badge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_badge` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `achieve` bit(1) NOT NULL DEFAULT b'0',
  `badge_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjqx9n26pk9mqf1qo8f7xvvoq9` (`badge_id`),
  KEY `FK2jw9fpotmmbda07k27qc9t2ul` (`user_id`),
  CONSTRAINT `FK2jw9fpotmmbda07k27qc9t2ul` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKjqx9n26pk9mqf1qo8f7xvvoq9` FOREIGN KEY (`badge_id`) REFERENCES `badge` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=354 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_badge`
--

LOCK TABLES `user_badge` WRITE;
/*!40000 ALTER TABLE `user_badge` DISABLE KEYS */;
INSERT INTO `user_badge` VALUES (1,_binary '',1,1),(2,_binary '',2,1),(3,_binary '',3,1),(4,_binary '\0',4,1),(5,_binary '',5,1),(6,_binary '\0',6,1),(7,_binary '\0',7,1),(8,_binary '\0',8,1),(9,_binary '',9,1),(10,_binary '',10,1),(11,_binary '',11,1),(12,_binary '',12,1),(13,_binary '\0',13,1),(14,_binary '\0',14,1),(15,_binary '\0',1,2),(16,_binary '',2,2),(17,_binary '',3,2),(18,_binary '\0',4,2),(19,_binary '',5,2),(20,_binary '',6,2),(21,_binary '\0',7,2),(22,_binary '\0',8,2),(23,_binary '',9,2),(24,_binary '',10,2),(25,_binary '',11,2),(26,_binary '',12,2),(27,_binary '\0',13,2),(28,_binary '\0',14,2),(29,_binary '\0',1,3),(30,_binary '\0',2,3),(31,_binary '\0',3,3),(32,_binary '\0',4,3),(33,_binary '\0',5,3),(34,_binary '\0',6,3),(35,_binary '\0',7,3),(36,_binary '\0',8,3),(37,_binary '\0',9,3),(38,_binary '\0',10,3),(39,_binary '\0',11,3),(40,_binary '\0',12,3),(41,_binary '\0',13,3),(42,_binary '\0',14,3),(43,_binary '\0',1,4),(44,_binary '\0',2,4),(45,_binary '\0',3,4),(46,_binary '\0',4,4),(47,_binary '\0',5,4),(48,_binary '\0',6,4),(49,_binary '\0',7,4),(50,_binary '\0',8,4),(51,_binary '\0',9,4),(52,_binary '\0',10,4),(53,_binary '\0',11,4),(54,_binary '\0',12,4),(55,_binary '\0',13,4),(56,_binary '\0',14,4),(84,_binary '\0',9,8),(85,_binary '\0',10,8),(86,_binary '\0',11,8),(87,_binary '\0',12,8),(88,_binary '\0',13,8),(89,_binary '\0',14,8),(90,_binary '\0',15,8),(91,_binary '\0',16,8),(92,_binary '\0',17,8),(93,_binary '',9,9),(94,_binary '',10,9),(95,_binary '\0',11,9),(96,_binary '\0',12,9),(97,_binary '\0',13,9),(98,_binary '\0',14,9),(99,_binary '\0',15,9),(100,_binary '\0',16,9),(101,_binary '\0',17,9),(102,_binary '\0',9,11),(103,_binary '\0',10,11),(104,_binary '\0',11,11),(105,_binary '\0',12,11),(106,_binary '\0',13,11),(107,_binary '\0',14,11),(108,_binary '\0',15,11),(109,_binary '\0',16,11),(110,_binary '\0',17,11),(111,_binary '\0',9,12),(112,_binary '\0',10,12),(113,_binary '\0',11,12),(114,_binary '\0',12,12),(115,_binary '\0',13,12),(116,_binary '\0',14,12),(117,_binary '\0',15,12),(118,_binary '\0',16,12),(119,_binary '\0',17,12),(120,_binary '',1,13),(121,_binary '',2,13),(122,_binary '',3,13),(123,_binary '',4,13),(124,_binary '',5,13),(125,_binary '\0',6,13),(126,_binary '\0',7,13),(127,_binary '\0',8,13),(128,_binary '\0',9,13),(129,_binary '\0',10,13),(130,_binary '\0',11,13),(131,_binary '\0',12,13),(132,_binary '\0',13,13),(133,_binary '\0',14,13),(134,_binary '\0',1,14),(135,_binary '\0',2,14),(136,_binary '\0',3,14),(137,_binary '\0',4,14),(138,_binary '\0',5,14),(139,_binary '\0',6,14),(140,_binary '\0',7,14),(141,_binary '\0',8,14),(142,_binary '\0',9,14),(143,_binary '\0',10,14),(144,_binary '\0',11,14),(145,_binary '\0',12,14),(146,_binary '\0',13,14),(147,_binary '\0',14,14),(148,_binary '\0',1,15),(149,_binary '',2,15),(150,_binary '',3,15),(151,_binary '\0',4,15),(152,_binary '\0',5,15),(153,_binary '\0',6,15),(154,_binary '\0',7,15),(155,_binary '\0',8,15),(156,_binary '',9,15),(157,_binary '',10,15),(158,_binary '',11,15),(159,_binary '\0',12,15),(160,_binary '\0',13,15),(161,_binary '\0',14,15),(162,_binary '\0',9,23),(163,_binary '\0',10,23),(164,_binary '\0',11,23),(165,_binary '\0',12,23),(166,_binary '\0',13,23),(167,_binary '\0',14,23),(168,_binary '\0',15,23),(169,_binary '\0',16,23),(170,_binary '\0',17,23),(171,_binary '',1,24),(172,_binary '',2,24),(173,_binary '',3,24),(174,_binary '',4,24),(175,_binary '\0',5,24),(176,_binary '\0',6,24),(177,_binary '\0',7,24),(178,_binary '\0',8,24),(179,_binary '\0',9,24),(180,_binary '\0',10,24),(181,_binary '\0',11,24),(182,_binary '\0',12,24),(183,_binary '\0',13,24),(184,_binary '\0',14,24),(185,_binary '\0',9,35),(186,_binary '\0',10,35),(187,_binary '\0',11,35),(188,_binary '\0',12,35),(189,_binary '\0',13,35),(190,_binary '\0',14,35),(191,_binary '\0',15,35),(192,_binary '\0',16,35),(193,_binary '\0',17,35),(194,_binary '\0',9,36),(195,_binary '\0',10,36),(196,_binary '\0',11,36),(197,_binary '\0',12,36),(198,_binary '\0',13,36),(199,_binary '\0',14,36),(200,_binary '\0',15,36),(201,_binary '\0',16,36),(202,_binary '\0',17,36),(203,_binary '\0',9,37),(204,_binary '\0',10,37),(205,_binary '\0',11,37),(206,_binary '\0',12,37),(207,_binary '\0',13,37),(208,_binary '\0',14,37),(209,_binary '\0',15,37),(210,_binary '\0',16,37),(211,_binary '\0',17,37),(212,_binary '\0',1,38),(213,_binary '\0',2,38),(214,_binary '\0',3,38),(215,_binary '\0',4,38),(216,_binary '\0',5,38),(217,_binary '\0',6,38),(218,_binary '\0',7,38),(219,_binary '\0',8,38),(220,_binary '\0',9,38),(221,_binary '\0',10,38),(222,_binary '\0',11,38),(223,_binary '\0',12,38),(224,_binary '\0',13,38),(225,_binary '\0',14,38),(226,_binary '\0',9,39),(227,_binary '\0',10,39),(228,_binary '\0',11,39),(229,_binary '\0',12,39),(230,_binary '\0',13,39),(231,_binary '\0',14,39),(232,_binary '\0',15,39),(233,_binary '\0',16,39),(234,_binary '\0',17,39),(235,_binary '\0',1,40),(236,_binary '\0',2,40),(237,_binary '\0',3,40),(238,_binary '\0',4,40),(239,_binary '\0',5,40),(240,_binary '\0',6,40),(241,_binary '\0',7,40),(242,_binary '\0',8,40),(243,_binary '\0',9,40),(244,_binary '\0',10,40),(245,_binary '\0',11,40),(246,_binary '\0',12,40),(247,_binary '\0',13,40),(248,_binary '\0',14,40),(249,_binary '\0',9,41),(250,_binary '\0',10,41),(251,_binary '\0',11,41),(252,_binary '\0',12,41),(253,_binary '\0',13,41),(254,_binary '\0',14,41),(255,_binary '\0',15,41),(256,_binary '\0',16,41),(257,_binary '\0',17,41),(258,_binary '\0',9,42),(259,_binary '\0',10,42),(260,_binary '\0',11,42),(261,_binary '\0',12,42),(262,_binary '\0',13,42),(263,_binary '\0',14,42),(264,_binary '\0',15,42),(265,_binary '\0',16,42),(266,_binary '\0',17,42),(267,_binary '\0',1,43),(268,_binary '\0',2,43),(269,_binary '\0',3,43),(270,_binary '\0',4,43),(271,_binary '\0',5,43),(272,_binary '\0',6,43),(273,_binary '\0',7,43),(274,_binary '\0',8,43),(275,_binary '\0',9,43),(276,_binary '\0',10,43),(277,_binary '\0',11,43),(278,_binary '\0',12,43),(279,_binary '\0',13,43),(280,_binary '\0',14,43),(281,_binary '\0',9,44),(282,_binary '\0',10,44),(283,_binary '\0',11,44),(284,_binary '\0',12,44),(285,_binary '\0',13,44),(286,_binary '\0',14,44),(287,_binary '\0',15,44),(288,_binary '\0',16,44),(289,_binary '\0',17,44),(290,_binary '\0',1,45),(291,_binary '\0',2,45),(292,_binary '\0',3,45),(293,_binary '\0',4,45),(294,_binary '\0',5,45),(295,_binary '\0',6,45),(296,_binary '\0',7,45),(297,_binary '\0',8,45),(298,_binary '\0',9,45),(299,_binary '\0',10,45),(300,_binary '\0',11,45),(301,_binary '\0',12,45),(302,_binary '\0',13,45),(303,_binary '\0',14,45),(304,_binary '\0',9,46),(305,_binary '\0',10,46),(306,_binary '\0',11,46),(307,_binary '\0',12,46),(308,_binary '\0',13,46),(309,_binary '\0',14,46),(310,_binary '\0',15,46),(311,_binary '\0',16,46),(312,_binary '\0',17,46),(313,_binary '\0',9,47),(314,_binary '\0',10,47),(315,_binary '\0',11,47),(316,_binary '\0',12,47),(317,_binary '\0',13,47),(318,_binary '\0',14,47),(319,_binary '\0',15,47),(320,_binary '\0',16,47),(321,_binary '\0',17,47),(322,_binary '\0',1,104),(323,_binary '\0',2,104),(324,_binary '\0',3,104),(325,_binary '\0',4,104),(326,_binary '\0',5,104),(327,_binary '\0',6,104),(328,_binary '\0',7,104),(329,_binary '\0',8,104),(330,_binary '\0',9,104),(331,_binary '\0',10,104),(332,_binary '\0',11,104),(333,_binary '\0',12,104),(334,_binary '\0',13,104),(335,_binary '\0',14,104),(336,_binary '\0',9,105),(337,_binary '\0',10,105),(338,_binary '\0',11,105),(339,_binary '\0',12,105),(340,_binary '\0',13,105),(341,_binary '\0',14,105),(342,_binary '\0',15,105),(343,_binary '\0',16,105),(344,_binary '\0',17,105),(345,_binary '\0',9,106),(346,_binary '\0',10,106),(347,_binary '\0',11,106),(348,_binary '\0',12,106),(349,_binary '\0',13,106),(350,_binary '\0',14,106),(351,_binary '\0',15,106),(352,_binary '\0',16,106),(353,_binary '\0',17,106);
/*!40000 ALTER TABLE `user_badge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish`
--

DROP TABLE IF EXISTS `wish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wish` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `checked` bit(1) DEFAULT NULL,
  `funding_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKry9hrewu3cl54e52w93lwx7va` (`funding_id`),
  KEY `FK70nrc4a6uvljrtemsn80eq1gd` (`member_id`),
  CONSTRAINT `FK70nrc4a6uvljrtemsn80eq1gd` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKry9hrewu3cl54e52w93lwx7va` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish`
--

LOCK TABLES `wish` WRITE;
/*!40000 ALTER TABLE `wish` DISABLE KEYS */;
INSERT INTO `wish` VALUES (1,_binary '',5,2),(2,_binary '',44,2),(3,_binary '',15,2),(4,_binary '',49,1),(5,_binary '',52,45),(7,_binary '',36,2),(8,_binary '',58,24),(9,_binary '',54,24),(10,_binary '',54,2);
/*!40000 ALTER TABLE `wish` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 10:40:33
