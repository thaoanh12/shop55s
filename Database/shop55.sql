CREATE DATABASE  IF NOT EXISTS `shop55` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shop55`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: shop55
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `customer_address` text,
  `commune` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `nation` varchar(100) DEFAULT NULL,
  `address_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `addresses_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_detail_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `cart_status` int DEFAULT '0',
  `total_amount` decimal(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_detail_id` (`product_detail_id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_name` varchar(100) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `category_status` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Áo Polo',NULL,1),(2,'Áo sơ mi',NULL,0),(3,'Áo khoác',NULL,0),(4,'Quần đùi',NULL,0),(5,'Quần âu',NULL,0),(6,'Quần bò',NULL,0);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colors` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `color_code` varchar(10) DEFAULT NULL,
  `color_name` varchar(100) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `color_status` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'#4983D0','Xanh',NULL,0),(2,'color2','Đỏ',NULL,0),(3,'color3','Tím',NULL,0),(4,'color4','Vàng',NULL,0);
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_images`
--

DROP TABLE IF EXISTS `comment_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_images` (
  `comment_id` bigint NOT NULL,
  `url` text,
  PRIMARY KEY (`comment_id`),
  CONSTRAINT `comment_images_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_images`
--

LOCK TABLES `comment_images` WRITE;
/*!40000 ALTER TABLE `comment_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` bigint DEFAULT NULL,
  `order_detail_id` bigint DEFAULT NULL,
  `note` text,
  `rate` int DEFAULT NULL,
  `comment_status` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `order_detail_id` (`order_detail_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customer_code` varchar(10) DEFAULT NULL,
  `full_name` varchar(300) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `avata` text,
  `customer_status` int DEFAULT '0',
  `passcode` text,
  `crate_date` date DEFAULT NULL,
  `phone_number` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customers_unique` (`phone_number`),
  UNIQUE KEY `customer_code` (`customer_code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (26,'kh507','Nuyễn Đinh Kiên','kien123@gmail.com','2024-02-18',NULL,0,'12345',NULL,'1234567890'),(27,'NV735153','Nguyễn Văn B','kienndph29147@fpt.edu.vn','2024-02-25',NULL,0,'$2a$10$B68pNNpkzFtFXmyOzMILleb4NHync3/Ph7FswcXq7orloDjJtdfpK',NULL,'1111111113');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discounts`
--

DROP TABLE IF EXISTS `discounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discounts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount_code` varchar(10) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `discount_status` int DEFAULT '0',
  `discount_type` int NOT NULL DEFAULT '0',
  `discount_value` double DEFAULT NULL,
  `maximum_discount_amount` decimal(20,2) DEFAULT '0.00',
  `describe` text,
  `quantity` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `discount_code` (`discount_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discounts`
--

LOCK TABLES `discounts` WRITE;
/*!40000 ALTER TABLE `discounts` DISABLE KEYS */;
INSERT INTO `discounts` VALUES (1,'kkkkk',NULL,NULL,1,10,0.00,NULL,0),(5,'CCCC','2024-02-13',0,1,100000,0.00,NULL,0),(6,'FFFFFF','2024-02-24',1,1,12,0.00,NULL,0);
/*!40000 ALTER TABLE `discounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `employee_code` varchar(10) DEFAULT NULL,
  `full_name` varchar(300) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `employee_address` text,
  `commune` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `nation` varchar(100) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `avata` text,
  `employee_status` int DEFAULT '0',
  `passcode` text,
  `role_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_code` (`employee_code`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (13,'root000001','Nguyễn Văn M','nguyenvana000@gmail.com','0999999999','số 43 - thôn Hạ','Xã Liên Trung','Huyện Đan Phượng','Thành phố Hà Nội','Việt Nam','2023-12-19','https://ps.w.org/user-avatar-reloaded/assets/icon-256x256.png?rev=2540745',0,'$2a$10$DoZSlnQ3hSCz5rrl4aJ9mexVm1RFyYG59xLfHAQdHXJjCXi3NAAZS',2),(14,'root000002','Nguyễn Văn C','nguyenvanc000@gmail.com','0999999996','số 43 - thôn Hạ','Xã Liên Trung','Huyện Đan Phượng','Thành phố Hà Nội','Việt Nam','2023-12-19','https://ps.w.org/user-avatar-reloaded/assets/icon-256x256.png?rev=2540745',1,'$2a$10$fLMqtsl8mwx2aYG2Cr4Y3eLp.E6wZi0g5lHWmhvYrmlcYLvjkn91O',4),(17,'root000003','Nguyễn Văn b','nguyenvanb000@gmail.com','0999999995','số 43 - thôn Hạ','Xã Liên Trung','Huyện Đan Phượng','Thành phố Hà Nội','Việt Nam','2023-12-19','https://ps.w.org/user-avatar-reloaded/assets/icon-256x256.png?rev=2540745',0,'$2a$10$fLMqtsl8mwx2aYG2Cr4Y3eLp.E6wZi0g5lHWmhvYrmlcYLvjkn91O',4),(56,'NVGl78wQQF','Nguyễn Văn Vởi','nguyendinhkien772@gmail.com','0969609242','số 43 - thôn Hạ','Xã Liên Trung','Huyện Đan Phượng','Thành phố Hà Nội','Việt Nam','2024-01-04',NULL,0,'$2a$10$EnPsBFmBPxticuPuOFSH.OeKdIhxX.SZ2jwiO3dafBCUG.8gjZoQ.',4),(57,'NVGl78wQQ1','Nguyễn Văn Vởi','nguyenvanc001@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,4),(58,'NVGl78wQQ2','Nguyễn Văn Vởi','nguyenvanc002@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,4),(59,'NVGl78wQQ3','Nguyễn Văn Vởi','nguyenvanc003@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,4),(60,'NVGl78wQQ4','Nguyễn Văn Vởi','nguyenvanc004@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,4),(61,'NVGl78wQQ5','Nguyễn Văn Vởi','nguyenvanc005@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,4),(62,'NVGl78wQQ6','Nguyễn Văn Vởi','nguyenvanc006@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,4),(65,'NV559539','Nguyen dinh kien','nguyendinhkien1907206@gmail.com','0987654321','So 43','Xã Liên Trung','Huyện Đan Phượng','Thành phố Hà Nội','Việt Nam','2024-01-30',NULL,0,'$2a$10$jlX5IPoh3hMCHuY3LYNOxeLLA3i4NnTpP8NJ8XCuBwUk7UMK9ua36',4),(66,'NV440176','kien','hoangngoctu57@gmail.com','0987654321','số 43','Xã Phú Cường','Thành phố Hưng Yên','Tỉnh Hưng Yên','Việt Nam','2024-01-31',NULL,0,'$2a$10$tvxgFrM98QjwaLBvm85R9u/4f12nXaQIjOtYsSsa8eDfKcZtyLQGi',4);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fabrics`
--

DROP TABLE IF EXISTS `fabrics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fabrics` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fabric_name` varchar(100) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `fabric_status` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fabrics`
--

LOCK TABLES `fabrics` WRITE;
/*!40000 ALTER TABLE `fabrics` DISABLE KEYS */;
INSERT INTO `fabrics` VALUES (1,'cotton',NULL,0);
/*!40000 ALTER TABLE `fabrics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint DEFAULT NULL,
  `product_detail_id` bigint DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `quantity` bigint NOT NULL,
  `order_detail_status` bigint NOT NULL,
  `total_amount` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_detail_id` (`product_detail_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (15,11,1,'2024-02-19 00:13:26',1,0,200000),(16,12,1,'2024-02-19 00:14:09',2,0,400000),(19,12,1,'2024-02-23 14:32:10',4,0,800000),(20,13,1,'2024-02-23 14:33:22',1,0,200000),(21,17,1,'2024-02-23 14:34:57',5,0,1000000),(22,18,1,'2024-02-23 14:36:25',3,0,600000),(23,21,1,'2024-02-23 14:36:25',1,0,200000),(24,17,1,'2024-02-23 14:36:25',1,0,200000),(25,19,1,'2024-02-23 14:36:25',9,0,1800000),(26,48,1,'2024-02-23 14:36:25',2,0,400000),(27,25,1,'2024-02-23 14:36:25',5,0,1000000),(28,48,1,'2024-02-23 14:36:32',8,0,1600000),(29,24,1,'2024-02-23 14:36:32',6,0,1200000),(30,47,1,'2024-02-23 14:36:32',1,0,200000),(31,46,1,'2024-02-23 14:36:32',9,0,1800000),(32,45,1,'2024-02-23 14:36:32',5,0,1000000),(33,44,1,'2024-02-23 14:36:32',4,0,800000),(34,43,1,'2024-02-23 14:36:36',7,0,1400000),(35,42,1,'2024-02-23 14:36:36',2,0,400000),(36,41,1,'2024-02-23 14:36:36',8,0,1600000),(37,40,1,'2024-02-23 14:36:36',2,0,400000),(38,39,1,'2024-02-23 14:36:36',5,0,1000000),(39,38,1,'2024-02-23 14:36:36',6,0,1200000),(40,37,1,'2024-02-23 14:36:39',6,0,1200000),(41,36,1,'2024-02-23 14:36:39',1,0,200000),(42,35,1,'2024-02-23 14:36:39',6,0,1200000),(43,34,1,'2024-02-23 14:36:39',1,0,200000),(44,33,1,'2024-02-23 14:36:39',8,0,1600000),(45,32,1,'2024-02-23 14:36:39',9,0,1800000),(46,31,1,'2024-02-23 14:36:45',9,0,1800000),(47,30,1,'2024-02-23 14:36:45',1,0,200000),(48,29,1,'2024-02-23 14:36:45',8,0,1600000),(49,28,1,'2024-02-23 14:36:45',4,0,800000),(50,27,1,'2024-02-23 14:36:45',9,0,1800000),(51,26,1,'2024-02-23 14:36:45',0,0,0),(52,25,1,'2024-02-23 14:37:50',9,0,1800000),(53,24,1,'2024-02-23 14:37:50',7,0,1400000),(54,23,1,'2024-02-23 14:37:50',5,0,1000000),(55,22,1,'2024-02-23 14:37:50',7,0,1400000),(56,21,1,'2024-02-23 14:37:50',2,0,400000),(57,20,1,'2024-02-23 14:37:50',5,0,1000000),(58,19,1,'2024-02-23 14:37:50',4,0,800000),(59,17,1,'2024-02-23 14:37:50',0,0,0),(60,16,1,'2024-02-23 14:37:50',7,0,1400000),(61,15,1,'2024-02-23 14:37:50',8,0,1600000),(62,14,1,'2024-02-23 14:37:50',2,0,400000),(63,13,1,'2024-02-23 14:37:50',6,0,1200000),(64,12,1,'2024-02-23 14:37:50',4,0,800000),(65,11,1,'2024-02-23 14:37:50',2,0,400000),(66,48,1,'2024-02-23 14:37:50',5,0,1000000),(67,47,1,'2024-02-23 14:37:50',8,0,1600000),(68,46,1,'2024-02-23 14:37:50',7,0,1400000),(69,45,1,'2024-02-23 14:37:50',8,0,1600000),(70,44,1,'2024-02-23 14:38:13',8,0,1600000),(71,43,1,'2024-02-23 14:38:13',7,0,1400000),(72,42,1,'2024-02-23 14:38:13',1,0,200000),(73,41,1,'2024-02-23 14:38:13',1,0,200000),(74,41,1,'2024-02-23 14:38:13',0,0,0),(75,39,1,'2024-02-23 14:38:13',7,0,1400000),(76,38,1,'2024-02-23 14:38:13',4,0,800000),(77,37,1,'2024-02-23 14:38:13',0,0,0),(78,36,1,'2024-02-23 14:38:13',7,0,1400000),(79,35,1,'2024-02-23 14:38:13',3,0,600000),(80,34,1,'2024-02-23 14:38:13',8,0,1600000),(81,33,1,'2024-02-23 14:38:13',2,0,400000),(82,32,1,'2024-02-23 14:38:13',4,0,800000),(83,31,1,'2024-02-23 14:38:13',2,0,400000),(84,30,1,'2024-02-23 14:38:13',8,0,1600000),(85,29,1,'2024-02-23 14:38:13',6,0,1200000),(86,28,1,'2024-02-23 14:38:13',3,0,600000),(87,27,1,'2024-02-23 14:38:13',3,0,600000),(88,26,1,'2024-02-23 14:38:25',9,0,1800000),(89,25,1,'2024-02-23 14:38:25',6,0,1200000),(90,24,1,'2024-02-23 14:38:25',4,0,800000),(91,23,1,'2024-02-23 14:38:25',1,0,200000),(92,22,1,'2024-02-23 14:38:25',5,0,1000000),(93,21,1,'2024-02-23 14:38:25',6,0,1200000),(94,20,1,'2024-02-23 14:38:25',2,0,400000),(95,19,1,'2024-02-23 14:38:25',9,0,1800000),(96,18,1,'2024-02-23 14:38:25',7,0,1400000),(97,17,1,'2024-02-23 14:38:25',6,0,1200000),(98,16,1,'2024-02-23 14:38:25',2,0,400000),(99,15,1,'2024-02-23 14:38:25',6,0,1200000),(100,14,1,'2024-02-23 14:38:25',4,0,800000),(101,13,1,'2024-02-23 14:38:25',2,0,400000),(102,12,1,'2024-02-23 14:38:25',2,0,400000),(103,11,1,'2024-02-23 14:38:25',1,0,200000),(104,48,1,'2024-02-23 14:38:25',6,0,1200000),(105,47,1,'2024-02-23 14:38:25',9,0,1800000),(106,46,1,'2024-02-23 14:38:46',5,0,1000000),(107,45,1,'2024-02-23 14:38:46',9,0,1800000),(108,44,1,'2024-02-23 14:38:46',9,0,1800000),(109,43,1,'2024-02-23 14:38:46',7,0,1400000),(110,42,1,'2024-02-23 14:38:46',10,0,2000000),(111,41,1,'2024-02-23 14:38:46',3,0,600000),(112,40,1,'2024-02-23 14:38:46',4,0,800000),(113,39,1,'2024-02-23 14:38:46',7,0,1400000),(114,38,1,'2024-02-23 14:38:46',5,0,1000000),(115,37,1,'2024-02-23 14:38:46',8,0,1600000),(116,36,1,'2024-02-23 14:38:46',6,0,1200000),(117,35,1,'2024-02-23 14:38:46',6,0,1200000),(118,34,1,'2024-02-23 14:38:46',3,0,600000),(119,33,1,'2024-02-23 14:38:46',10,0,2000000),(120,32,1,'2024-02-23 14:38:46',4,0,800000),(121,31,1,'2024-02-23 14:38:46',5,0,1000000),(122,30,1,'2024-02-23 14:38:46',6,0,1200000),(123,29,1,'2024-02-23 14:38:46',5,0,1000000),(126,49,1,'2024-02-23 20:24:23',3,0,300000),(127,50,1,'2024-02-23 21:01:40',3,0,300000),(129,52,12,'2024-02-26 22:07:00',1,0,200000),(134,68,1,'2024-03-01 13:18:53',1,0,200000),(135,68,2,'2024-03-01 13:19:38',2,0,420000),(136,68,12,'2024-03-01 17:40:58',1,0,200000);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_code` varchar(10) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  `employee_id` bigint DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `pay_date` datetime DEFAULT NULL,
  `note` text,
  `order_status` int DEFAULT '0',
  `discount_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  KEY `employee_id` (`employee_id`),
  KEY `discount_id` (`discount_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`discount_id`) REFERENCES `discounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (11,'HD246',NULL,13,'2024-02-18 00:00:00','2024-02-23 14:13:14',NULL,1,NULL,0),(12,'HD32186',NULL,13,'2024-02-18 22:06:42','2024-02-23 14:13:14',NULL,1,NULL,0),(13,'HD23257',NULL,13,'2024-02-22 18:52:41','2024-02-23 14:13:14',NULL,1,NULL,0),(14,'HD23254',NULL,13,'2023-01-22 18:52:41','2023-01-22 18:52:41',NULL,1,NULL,0),(15,'HD23255',NULL,13,'2023-01-23 18:52:41','2023-01-23 18:52:41',NULL,1,NULL,0),(16,'HD23258',NULL,13,'2023-01-24 18:52:41','2023-01-24 18:52:41',NULL,1,NULL,0),(17,'HD23250',NULL,13,'2023-01-25 18:52:41','2023-01-25 18:52:41',NULL,1,NULL,0),(18,'HD23259',NULL,13,'2023-01-26 18:52:41','2023-01-26 18:52:41',NULL,1,NULL,0),(19,'HD23251',NULL,13,'2023-02-22 18:52:41','2023-02-22 18:52:41',NULL,1,NULL,0),(20,'HD23252',NULL,13,'2023-02-24 18:52:41','2023-02-24 18:52:41',NULL,1,NULL,0),(21,'HD23253',NULL,13,'2023-02-25 18:52:41','2023-02-25 18:52:41',NULL,1,NULL,0),(22,'HD23256',NULL,13,'2023-02-26 18:52:41','2023-02-26 18:52:41',NULL,1,NULL,0),(23,'HD23207',NULL,13,'2023-02-27 18:52:41','2023-02-27 18:52:41',NULL,1,NULL,0),(24,'HD23217',NULL,13,'2023-03-24 18:52:41','2023-03-24 18:52:41',NULL,1,NULL,0),(25,'HD23227',NULL,13,'2023-03-25 18:52:41','2023-03-25 18:52:41',NULL,1,NULL,0),(26,'HD23237',NULL,13,'2023-03-28 18:52:41','2023-03-28 18:52:41',NULL,1,NULL,0),(27,'HD23247',NULL,13,'2023-04-24 18:52:41','2023-04-24 18:52:41',NULL,1,NULL,0),(28,'HD23267',NULL,13,'2023-04-27 18:52:41','2023-04-27 18:52:41',NULL,1,NULL,0),(29,'HD23277',NULL,13,'2023-04-30 18:52:41','2023-04-30 18:52:41',NULL,1,NULL,0),(30,'HD23287',NULL,13,'2023-04-30 18:52:41','2023-04-30 18:52:41',NULL,1,NULL,0),(31,'HD23297',NULL,13,'2023-05-24 18:52:41','2023-05-24 18:52:41',NULL,1,NULL,0),(32,'HD23057',NULL,13,'2023-05-26 18:52:41','2023-05-26 18:52:41',NULL,1,NULL,0),(33,'HD23157',NULL,13,'2023-05-29 18:52:41','2023-05-29 18:52:41',NULL,1,NULL,0),(34,'HD23357',NULL,13,'2023-06-24 18:52:41','2023-06-24 18:52:41',NULL,1,NULL,0),(35,'HD23457',NULL,13,'2023-06-27 18:52:41','2023-06-27 18:52:41',NULL,1,NULL,0),(36,'HD23557',NULL,13,'2023-06-28 18:52:41','2023-06-28 18:52:41',NULL,1,NULL,0),(37,'HD23657',NULL,13,'2023-08-24 18:52:41','2023-08-24 18:52:41',NULL,1,NULL,0),(38,'HD23757',NULL,13,'2023-08-02 18:52:41','2023-08-02 18:52:41',NULL,1,NULL,0),(39,'HD23287',NULL,13,'2023-08-20 18:52:41','2023-08-20 18:52:41',NULL,1,NULL,0),(40,'HD23057',NULL,13,'2023-09-24 18:52:41','2023-09-24 18:52:41',NULL,1,NULL,0),(41,'HD21257',NULL,13,'2023-09-27 18:52:41','2023-09-27 18:52:41',NULL,1,NULL,0),(42,'HD11257',NULL,13,'2023-09-26 18:52:41','2023-09-26 18:52:41',NULL,1,NULL,0),(43,'HD31257',NULL,13,'2023-10-27 18:52:41','2023-10-27 18:52:41',NULL,1,NULL,0),(44,'HD41257',NULL,13,'2023-10-25 18:52:41','2023-10-25 18:52:41',NULL,1,NULL,0),(45,'HD51257',NULL,13,'2023-10-29 18:52:41','2023-10-29 18:52:41',NULL,1,NULL,0),(46,'HD61257',NULL,13,'2023-11-27 18:52:41','2023-11-27 18:52:41',NULL,1,NULL,0),(47,'HD71257',NULL,13,'2023-11-26 18:52:41','2023-11-26 18:52:41',NULL,1,NULL,0),(48,'HD81257',NULL,13,'2023-12-27 18:52:41','2023-12-27 18:52:41',NULL,1,NULL,0),(49,'HD71457',NULL,13,'2024-02-23 20:10:04','2024-02-23 20:24:35',NULL,1,NULL,0),(50,'HD86369',NULL,13,'2024-02-23 21:01:31','2024-02-25 21:14:40',NULL,1,NULL,0),(52,'HD54250',NULL,13,'2024-02-26 22:06:31','2024-02-26 22:08:07',NULL,1,NULL,0),(68,'HD39599',NULL,13,'2024-02-29 13:16:52',NULL,NULL,0,NULL,0),(69,'HD70889',NULL,13,'2024-03-01 17:44:35',NULL,NULL,0,NULL,0),(70,'HD48888',NULL,13,'2024-03-03 21:14:28',NULL,NULL,0,NULL,0),(71,'HD78638',NULL,13,'2024-03-03 21:14:31',NULL,NULL,0,NULL,0),(72,'HD67005',NULL,13,'2024-03-03 21:14:32',NULL,NULL,0,NULL,0),(73,'HD96306',NULL,13,'2024-03-03 21:14:33',NULL,NULL,0,NULL,0),(74,'HD21706',NULL,13,'2024-03-03 21:14:35',NULL,NULL,0,NULL,0),(75,'HD83514',NULL,13,'2024-03-03 21:14:36',NULL,NULL,0,NULL,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_detail_code` varchar(10) DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `color_id` bigint DEFAULT NULL,
  `size_id` bigint DEFAULT NULL,
  `bar_code` text,
  `quantity` int DEFAULT NULL,
  `thumbnail` text,
  `product_detail_status` int DEFAULT '0',
  `price` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_detail_code` (`product_detail_code`),
  KEY `product_id` (`product_id`),
  KEY `color_id` (`color_id`),
  KEY `size_id` (`size_id`),
  CONSTRAINT `product_detail_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `product_detail_ibfk_2` FOREIGN KEY (`color_id`) REFERENCES `colors` (`id`),
  CONSTRAINT `product_detail_ibfk_3` FOREIGN KEY (`size_id`) REFERENCES `sizes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,'SP782-1-1',1,1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1708592884/top3cvvrwjot1jrbxbuj.png',10,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1708593957/dcmzzxcmys94mubuz1ab.png',0,200000),(2,'spd2',1,1,4,NULL,5,'https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/465194/item/vngoods_53_465194.jpg?width=750',0,210000),(3,'spd3',1,1,5,NULL,6,'https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/465194/item/vngoods_53_465194.jpg?width=750',0,230000),(4,'spd4',1,1,6,NULL,6,'https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/465194/item/vngoods_53_465194.jpg?width=750',0,260000),(6,'spd5',1,1,7,NULL,5,'https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/465194/item/vngoods_53_465194.jpg?width=750',0,200000),(7,'SP-Xanh-M',2,1,1,NULL,5,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707361432/kunc30omlntn9gzqyvib.png',0,300000),(8,'spd6',1,2,1,NULL,2,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707361432/kunc30omlntn9gzqyvib.png',0,100000),(9,'spd7',1,2,4,NULL,3,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707361432/kunc30omlntn9gzqyvib.png',0,500000),(10,'spd8',1,3,5,NULL,4,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707361432/kunc30omlntn9gzqyvib.png',0,420000),(11,'spd10',1,4,7,NULL,5,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707361432/kunc30omlntn9gzqyvib.png',0,320000),(12,'SP993-1-1',1,1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707545853/d2fszs46rl5by2pcwrgv.png',4,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707545851/olsmq8lnqeadkyly8nqm.png',0,200000),(13,'SP344-2-7',1,2,7,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1709475842/vkmb5ztj1qssorltvpwg.png',10,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1709475834/ntzicpwjz3gvodxtl4oi.jpg',0,200000);
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_images`
--

DROP TABLE IF EXISTS `product_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `path_image` text,
  `image_status` int DEFAULT '0',
  `product_detail_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_detail_id` (`product_detail_id`),
  CONSTRAINT `product_images_ibfk_1` FOREIGN KEY (`product_detail_id`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_images`
--

LOCK TABLES `product_images` WRITE;
/*!40000 ALTER TABLE `product_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_code` varchar(10) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `made_in` varchar(100) DEFAULT NULL,
  `product_describe` text,
  `category_id` bigint DEFAULT NULL,
  `fabric_id` bigint DEFAULT NULL,
  `thumbnail` text,
  `weight` varchar(255) DEFAULT NULL,
  `create_date` datetime(6) DEFAULT NULL,
  `product_status` int DEFAULT '0',
  `price` double DEFAULT NULL,
  `update_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_code` (`product_code`),
  KEY `category_id` (`category_id`),
  KEY `fabric_id` (`fabric_id`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `products_ibfk_2` FOREIGN KEY (`fabric_id`) REFERENCES `fabrics` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'sp2811625','Ao polo kaki','Việt Nam','siêu đẹp',1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1708592764/bc0lo6abzqvbfu00galf.png','0.3','2024-02-22 16:06:05.777000',0,NULL,NULL),(2,'sp6025778','1111','','11',1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707407162/y7xwzi8pqhciptkzqasv.png','11','2024-02-08 22:46:03.369000',0,NULL,NULL),(3,'sp1877976','1111','Viêt nam','11',1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707407212/eouqgjcufybn26ylbhc4.png','11','2024-02-08 22:46:52.687000',0,NULL,NULL),(4,'sp6993707','1111','111','11',1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1706941542/yc55gq3k0rxa7ltuep5t.jpg','11','2024-02-03 00:00:00.000000',0,NULL,NULL),(5,'sp1202600','Ha ha','Việt Nam','rất đẹp',1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707541004/hrlc8i7n1uldrh7rnpds.jpg','0.3','2024-02-10 11:56:45.829000',0,NULL,NULL),(6,'sp7001318','Ha ha','Albania','rất đẹp',1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707541247/tki3bbr1timlhqsduugj.png','11','2024-02-10 12:00:48.890000',0,NULL,NULL),(7,'sp1436988','1111','Andorra','rất đẹp',1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1707541558/j8vqv0izad1psqutjxs2.png','0.3','2024-02-10 12:06:00.361000',0,NULL,NULL),(8,'sp748993','1111','Albania','rất đẹp',1,1,'http://res.cloudinary.com/dbxcrlj9c/image/upload/v1708578680/dvfwgxuox3s7npltkr4p.png','0.3','2024-02-22 12:11:21.444000',0,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` enum('ROLE_CUSTOMER','ROLE_ADMIN','ROLE_STAFF') DEFAULT NULL,
  `role_sattus` int DEFAULT '0',
  `role_code` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (2,'ROLE_ADMIN',0,'role2'),(4,'ROLE_STAFF',0,'role1');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sizes`
--

DROP TABLE IF EXISTS `sizes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sizes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `size_code` varchar(10) DEFAULT NULL,
  `size_name` varchar(100) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `sizes_status` int DEFAULT '0',
  `update_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sizes`
--

LOCK TABLES `sizes` WRITE;
/*!40000 ALTER TABLE `sizes` DISABLE KEYS */;
INSERT INTO `sizes` VALUES (1,'size1','M','2023-01-01',0,NULL),(4,'size2','S','2024-01-09',0,NULL),(5,'size3','L','2024-01-09',0,NULL),(6,'size4','XL','2024-01-09',0,NULL),(7,'siz3','2XL','2024-01-09',0,NULL);
/*!40000 ALTER TABLE `sizes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-03 21:46:50
