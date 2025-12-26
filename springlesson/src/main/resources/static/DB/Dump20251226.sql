CREATE DATABASE  IF NOT EXISTS `sushi` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sushi`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sushi
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `product_id` bigint unsigned NOT NULL,
  `quantity` int unsigned NOT NULL,
  `unit_price` int unsigned NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_cart_user` (`user_id`),
  KEY `fk_cart_product` (`product_id`),
  CONSTRAINT `fk_cart_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sort_order` int unsigned NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'にぎり',1,'2025-12-26 09:39:32','2025-12-26 09:39:32'),(2,'軍艦',2,'2025-12-26 09:39:32','2025-12-26 09:39:32'),(3,'その他',3,'2025-12-26 09:39:32','2025-12-26 09:39:32');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint unsigned NOT NULL,
  `product_id` bigint unsigned NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `unit_price` int unsigned NOT NULL,
  `quantity` int unsigned NOT NULL,
  `subtotal_amount` int unsigned NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_order_items_order` (`order_id`),
  KEY `fk_order_items_product` (`product_id`),
  CONSTRAINT `fk_order_items_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_order_items_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `order_number` varchar(50) NOT NULL,
  `order_datetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(20) NOT NULL,
  `total_amount` int unsigned NOT NULL,
  `shipping_recipient_name` varchar(100) NOT NULL,
  `shipping_postal_code` varchar(10) NOT NULL,
  `shipping_prefecture` varchar(50) NOT NULL,
  `shipping_city` varchar(100) NOT NULL,
  `shipping_address_line1` varchar(255) NOT NULL,
  `shipping_address_line2` varchar(255) DEFAULT NULL,
  `shipping_phone_number` varchar(20) DEFAULT NULL,
  `delivery_date` date NOT NULL,
  `delivery_time_slot` varchar(20) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_number` (`order_number`),
  KEY `fk_orders_user` (`user_id`),
  CONSTRAINT `fk_orders_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categories` (
  `product_id` bigint unsigned NOT NULL,
  `category_id` bigint unsigned NOT NULL,
  `sort_order` int unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`product_id`,`category_id`),
  KEY `fk_pc_category` (`category_id`),
  CONSTRAINT `fk_pc_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `fk_pc_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,1,11),(12,1,12),(13,1,13),(14,1,14),(15,1,15),(16,1,16),(17,1,17),(18,1,18),(19,1,19),(20,1,20),(21,2,1),(22,2,2),(23,2,3),(24,2,4),(25,2,5),(26,2,6),(27,2,7),(28,3,1),(29,3,2),(30,3,3);
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(160) NOT NULL,
  `description` text,
  `price` int unsigned NOT NULL,
  `calories` int unsigned DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `allergy_info` varchar(255) DEFAULT NULL,
  `ingredient_info` text,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'まぐろ','定番の赤身まぐろ。さっぱりとした味わい。',180,100,'/images/products/maguro.png','なし','まぐろ、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(2,'中とろ','ほどよい脂のりの中とろ。',280,130,'/images/products/chutoro.jpg','なし','まぐろ、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(3,'大とろ','とろける食感の大とろ。',480,180,'/images/products/otoro.jpg','なし','まぐろ、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(4,'サーモン','脂ののった人気のサーモン。',220,140,'/images/products/salmon.png','さけ','サーモン、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(5,'いか','歯ごたえのあるいか。',180,90,'/images/products/ika.png','いか','いか、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(6,'たこ','旨みのある蒸したこ。',200,95,'/images/products/tako.jpg','なし','たこ、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(7,'えび','甘みのあるボイルえび。',220,110,'/images/products/ebi.png','えび','えび、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(8,'甘えび','とろりとした甘えび。',260,120,'/images/products/amaebi.jpg','えび','甘えび、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(9,'あじ','さっぱりした青魚の定番。',180,105,'/images/products/aji.jpg','なし','あじ、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(10,'さば','脂ののったしめさば。',200,140,'/images/products/saba.jpg','なし','さば、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(11,'たい','上品な味わいの白身。',180,85,'/images/products/tai.jpg','なし','たい、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(12,'ひらめ','淡白で旨みのある白身魚。',200,80,'/images/products/hirame.jpg','なし','ひらめ、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(13,'かんぱち','コクのある味わい。',220,120,'/images/products/kanpachi.png','なし','かんぱち、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(14,'ぶり','脂がのった旬の味。',240,150,'/images/products/buri.jpg','なし','ぶり、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(15,'こはだ','江戸前寿司の定番。',180,100,'/images/products/kohada.jpg','なし','こはだ、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(16,'玉子','甘めの厚焼き玉子。',160,110,'/images/products/tamago.png','卵','卵、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(17,'いくら','ぷちぷち食感のいくら。',380,160,'/images/products/ikura.png','いくら','いくら、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(18,'うに','濃厚な甘みのうに。',520,190,'/images/products/uni.png','なし','うに、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(19,'穴子','ふっくら煮上げた穴子。',420,200,'/images/products/anago.png','なし','穴子、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(20,'ほたて','甘みのある貝柱。',260,120,'/images/products/hotate.jpg','なし','ほたて、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(21,'赤貝','コリコリとした食感。',300,110,'/images/products/akagai.png','なし','赤貝、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(22,'つぶ貝','歯ごたえの良い貝。',260,105,'/images/products/tsubugai.png','なし','つぶ貝、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52'),(23,'ねぎとろ','まぐろとねぎの相性抜群。',220,150,'/images/products/negitoro.jpg','なし','まぐろ、ねぎ、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(24,'鉄火巻','まぐろを使った定番巻物。',300,180,'/images/products/tekkamaki.jpg','なし','まぐろ、海苔、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(25,'かっぱ巻','きゅうりのさっぱり巻。',160,90,'/images/products/kappamaki.jpg','なし','きゅうり、海苔、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(26,'納豆巻','納豆の旨みが楽しめる巻物。',180,140,'/images/products/nattomaki.jpg','大豆','納豆、海苔、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(27,'とびっこ','プチプチ食感の魚卵。',240,130,'/images/products/tobikko.jpg','なし','とびっこ、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(28,'しらす','釜揚げしらすの優しい味。',200,110,'/images/products/shirasu.jpg','なし','しらす、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(29,'炙りサーモン','香ばしく炙ったサーモン。',260,160,'/images/products/aburi_salmon.jpg','さけ','サーモン、米',1,'2025-12-26 09:39:00','2025-12-26 09:39:00'),(30,'えんがわ','脂の旨みが際立つえんがわ。',300,170,'/images/products/engawa.png','なし','えんがわ、米',1,'2025-12-26 09:39:00','2025-12-26 10:37:52');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `product_id` bigint unsigned NOT NULL,
  `rating` tinyint unsigned NOT NULL,
  `comment` text,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_reviews_user` (`user_id`),
  KEY `fk_reviews_product` (`product_id`),
  CONSTRAINT `fk_reviews_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `fk_reviews_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (2,2,1,3,'マグロは新鮮で美味しかったですが、もう少し量があると嬉しいです。',0,'2025-12-26 10:08:46','2025-12-26 10:08:46'),(4,4,2,3,'口の中で溶けました！',0,'2025-12-26 13:48:59','2025-12-26 13:48:59');
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_roles_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN','管理者権限','2025-12-23 11:15:06','2025-12-23 11:15:06'),(2,'ROLE_USER','一般ユーザー権限','2025-12-23 11:15:06','2025-12-23 11:15:06');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_addresses`
--

DROP TABLE IF EXISTS `user_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_addresses` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint unsigned NOT NULL,
  `recipient_name` varchar(100) NOT NULL,
  `postal_code` varchar(10) NOT NULL,
  `prefecture` varchar(50) NOT NULL,
  `city` varchar(100) NOT NULL,
  `address_line1` varchar(255) NOT NULL,
  `address_Line2` varchar(255) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `is_default` tinyint(1) NOT NULL DEFAULT '0',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_user_addresses_user` (`user_id`),
  CONSTRAINT `fk_user_addresses_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_addresses`
--

LOCK TABLES `user_addresses` WRITE;
/*!40000 ALTER TABLE `user_addresses` DISABLE KEYS */;
INSERT INTO `user_addresses` VALUES (1,2,'藤田　弘就','7310523','a','a','a','',NULL,1,'2025-12-23 12:01:05','2025-12-23 12:01:05'),(2,4,'藤田　弘就','7310523','w','w','w','',NULL,1,'2025-12-23 14:00:32','2025-12-23 14:00:32'),(3,5,'a','7310523','a','a','a','',NULL,1,'2025-12-23 14:49:38','2025-12-23 14:49:38'),(4,6,'藤田　弘就','7310523','a','a','a','a',NULL,1,'2025-12-23 17:28:20','2025-12-23 17:28:20'),(5,8,'藤田　弘就','7310523','x','x','x','',NULL,1,'2025-12-25 17:32:33','2025-12-25 17:32:33'),(6,9,'藤田　弘就','7310523','a','a','a','',NULL,1,'2025-12-25 17:36:07','2025-12-25 17:36:07');
/*!40000 ALTER TABLE `user_addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `dm_opt_in` tinyint(1) NOT NULL DEFAULT '0',
  `withdraw_flag` tinyint(1) NOT NULL DEFAULT '0',
  `last_login_at` datetime DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role_id` bigint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `fk_users_roles` (`role_id`),
  CONSTRAINT `fk_users_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'fujitahironari1019@gmail.com','$2a$10$vgF9t55HnNO5oAVwm3s3Yu7YP/nL2vJePSRKSO3/uCzurrDgKy3JK','藤田　弘就','',0,0,'2025-12-23 12:01:05','2025-12-23 12:01:05','2025-12-23 12:01:05',NULL),(4,'motoyasu0212@gmail.com','$2a$10$yUUdFQQuF34cgUK9AK0mve2YvnKfI6Xqz/A1/zijtDFmA//JLAQOu','藤田　弘就','',0,0,'2025-12-23 14:00:33','2025-12-23 14:00:32','2025-12-23 14:00:32',NULL),(5,'tamudoku22@i.softbank.jp','$2a$10$YYA1b/.w.e/zhZZgHjpWuuNWXT5aFA7iaHlRiic8xLfCEbcA2.HBa','a','',1,0,'2025-12-23 14:49:38','2025-12-23 14:49:38','2025-12-23 14:49:38',NULL),(6,'tamudoku22@outlook.jp','$2a$10$jyuofIf9AXI.FOU3i09Hj.gJpFM2gRgd4pME6xV8n.nFyjyZ4O/Eu','藤田　弘就','',0,0,'2025-12-23 17:28:20','2025-12-23 17:28:20','2025-12-23 17:28:20',2),(8,'fujitahironari1019@yahoo.co.jp','$2a$10$ScJoDXvr3UY2wrtFCrh.N.TD1rAObpBTiyt1XScm/92/RnnBgcodq','藤田　弘就','',1,0,'2025-12-25 17:32:34','2025-12-25 17:32:33','2025-12-25 17:32:33',2),(9,'aaaaaa@gmail.com','$2a$10$YqQV6bojn/1rT39tHjGDDeueQGnEqLNHCqhMssw6pGgKqKIoRkuf2','藤田　弘就','',1,0,'2025-12-25 17:36:08','2025-12-25 17:36:07','2025-12-25 17:36:07',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-26 14:49:21
