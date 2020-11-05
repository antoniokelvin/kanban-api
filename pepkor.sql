-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.18 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for pepkor
CREATE DATABASE IF NOT EXISTS `pepkor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pepkor`;

-- Dumping structure for table pepkor.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pepkor.user: ~3 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `name`, `email`) VALUES
	(1, 'Test user', 'testuser@fake.com'),
	(2, 'Second Test user', 'second@fake.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table pepkor.work_item
CREATE TABLE IF NOT EXISTS `work_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` mediumtext NOT NULL,
  `description` text,
  `status` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table pepkor.work_item: ~12 rows (approximately)
/*!40000 ALTER TABLE `work_item` DISABLE KEYS */;
INSERT INTO `work_item` (`id`, `title`, `description`, `status`, `user_id`) VALUES
	(1, 'Test task one', 'old descrption removed', 0, 6),
	(2, 'Another tash', 'shadghas asdgasdasd', 1, 2),
	(3, 'third finished task', 'Completed task', 0, 1);
/*!40000 ALTER TABLE `work_item` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
