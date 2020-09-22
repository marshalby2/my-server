-- MySQL dump 10.13  Distrib 8.0.21, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: my_server
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `label` varchar(100) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `icon` varchar(200) NOT NULL,
  `parent_id` int DEFAULT '1',
  `remark` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `sort` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id`, `label`, `url`, `icon`, `parent_id`, `remark`, `create_time`, `update_time`, `sort`) VALUES (1,'根节点','/root','el-icon-lx-home',-1,'根节点','2020-09-12 17:52:04','2020-09-15 11:53:25',0),(2,'首页','/dashboard','el-icon-lx-home',1,'首页','2020-09-12 17:52:38',NULL,0),(3,'系统管理','','el-icon-setting',1,'系统管理','2020-09-12 18:03:16',NULL,1),(4,'用户管理','/user','el-icon-user-solid',3,'用户管理','2020-09-12 18:04:28',NULL,1),(5,'菜单管理','/menu','el-icon-menu',3,'菜单管理','2020-09-12 18:05:17',NULL,2),(6,'常见功能','/common','el-icon-platform-eleme',1,'','2020-09-14 18:44:02','2020-09-14 18:44:02',3),(7,'makdown','/markdown','el-icon-edit-outline',6,'','2020-09-14 18:45:44','2020-09-14 18:45:44',0),(8,'文件上传','/upload','el-icon-folder-add',6,'','2020-09-14 18:46:48','2020-09-14 18:46:48',2),(9,'基础表格','/table','el-icon-s-fold',6,NULL,'2020-09-15 14:30:04','2020-09-15 14:30:04',3),(10,'自定义图标','/icon','el-icon-platform-eleme',6,NULL,'2020-09-16 19:06:30','2020-09-16 19:06:30',4),(11,'标签页','/tabs','el-icon-price-tag',6,NULL,'2020-09-16 19:10:05','2020-09-16 19:10:05',4),(13,'国际化','/i18n','el-icon-s-promotion',6,NULL,'2020-09-16 19:17:10','2020-09-16 19:17:10',7),(14,'编辑器','/editor','el-icon-edit-outline',6,NULL,'2020-09-16 19:20:49','2020-09-16 19:20:49',8),(16,'角色管理','/role','el-icon-s-check',3,NULL,'2020-09-19 17:29:26','2020-09-19 17:29:26',3);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `code` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `remark`, `create_time`, `update_time`, `code`) VALUES (1,'管理员','管理员','2020-09-14 16:46:17',NULL,'ROLE_ADMIN'),(2,'普通用户','普通用户','2020-09-14 16:48:49',NULL,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `menu_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` (`id`, `role_id`, `menu_id`) VALUES (14,2,2),(15,2,6),(16,2,7),(17,2,8),(18,2,9),(19,2,10),(20,2,11),(21,2,13),(22,2,14),(23,1,2),(24,1,3),(25,1,4),(26,1,5),(27,1,16),(28,1,6),(29,1,7),(30,1,8),(31,1,9),(32,1,11);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `avatar` varchar(500) NOT NULL,
  `nick_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  `enable` int DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `avatar`, `nick_name`, `email`, `remark`, `enable`, `create_time`, `update_time`) VALUES (1,'admin','$2a$10$9rn68teCYTOvOYzsGGvYxOjrc2DchNBS3Bgr6TARpqDwVgROVhv0a','https://img.hacpai.com/avatar/1524822749398?1524822750520&timestamp=1600567683318','string','','',1,'2020-09-19 17:11:00','2020-09-19 14:20:21'),(11,'Tom','$2a$10$totTTcutvSObu/y7YZrI.O2BwPozikglUWCQTfkX8yMRy7aFogqk.','https://lin-xin.gitee.io/images/post/node3.png','tom123','tom@qq.com',NULL,1,'2020-09-21 14:13:09','2020-09-21 14:13:09');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES (7,10,2),(9,11,2),(12,1,1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-22 18:07:12
