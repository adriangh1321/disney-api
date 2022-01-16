-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: disney
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `character_movie`
--

DROP TABLE IF EXISTS `character_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `character_movie` (
  `movie_id` bigint NOT NULL,
  `character_id` bigint NOT NULL,
  KEY `FKjau8aiv8i9bj6grbth11feq6e` (`character_id`),
  KEY `FKpxsq3iu73p2nbfpgfbxaqlvco` (`movie_id`),
  CONSTRAINT `FKjau8aiv8i9bj6grbth11feq6e` FOREIGN KEY (`character_id`) REFERENCES `characters` (`id`),
  CONSTRAINT `FKpxsq3iu73p2nbfpgfbxaqlvco` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `character_movie`
--

LOCK TABLES `character_movie` WRITE;
/*!40000 ALTER TABLE `character_movie` DISABLE KEYS */;
INSERT INTO `character_movie` VALUES (3,2),(3,3),(1,7),(1,5),(10,15),(10,16),(2,2),(2,3),(9,13),(9,14),(9,1),(11,4);
/*!40000 ALTER TABLE `character_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `characters`
--

DROP TABLE IF EXISTS `characters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `characters` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(100) NOT NULL,
  `weight` int NOT NULL,
  `story` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `characters`
--

LOCK TABLES `characters` WRITE;
/*!40000 ALTER TABLE `characters` DISABLE KEYS */;
INSERT INTO `characters` VALUES (1,94,_binary '\0','/src/main/resources/static/img/character-MickeyMouse.png','Mickey Mouse',20,'Mickey Mouse is a cartoon character created in 1928 by Walt Disney, and is the mascot of The Walt Disney Company. An anthropomorphic mouse who typically wears red shorts, large yellow shoes, and white gloves, Mickey is one of the world\'s most recognizable fictional characters.'),(2,25,_binary '\0','/src/main/resources/static/img/character-Woody.png','Woody',1,'Sheriff Woody is a fictional pullstring cowboy rag doll who appears in the Disney/Pixar Toy Story franchise. In the movies, Woody is one of the main protagonist characters along with Buzz Lightyear.'),(3,28,_binary '\0','/src/main/resources/static/img/character-BuzzLightyear.png','Buzz Lightyear',2,'Buzz Lightyear is a fictional character in the Toy Story franchise created by Disney and Pixar. He is a toy Space Ranger superhero according to the movies and an action figure in the franchise.'),(4,16,_binary '\0','/src/main/resources/static/img/character-TroyBolton.jpg','Troy Bolton',60,'Troy Bolton is the protagonist of the first three films. Depicted as one of East High\'s most popular students, Troy is also the captain of the school\'s varsity basketball team, a position he earned in his junior year after joining the team as a sophomore.'),(5,1,_binary '\0','/src/main/resources/static/img/character-Simba.png','Simba',30,'Simba is the protagonist of Disney\'s The Lion King franchise. Introduced in the 1994 film The Lion King, Walt Disney Animation\'s 32nd animated feature, the character subsequently appears in The Lion King II: Simba\'s Pride (1998) and The Lion King 1½ (2004) as well as the 2019 remake of the original film.'),(7,10,_binary '\0','/src/main/resources/static/img/character-Pumba.png','Pumba',200,'Pumbaa is a male warthog. He is the adoptive uncle of Bunga and Simba. When Simba fled the Pride Lands, he nearly died from exposure in the desert. However, Pumbaa and his best friend, Timon, rescued the cub and revived him in their jungle home. They proceeded to teach Simba the Hakuna Matata lifestyle, and he adopted it readily. Over the seasons, the three became close friends'),(13,10,_binary '\0','/src/main/resources/static/img/character-DonaldDuck.png','Donald Duck',20,'Donald Fauntleroy Duck is a cartoon character created by The Walt Disney Company. Donald is an anthropomorphic white duck with a yellow-orange bill, legs, and feet. He typically wears a sailor shirt and cap with a bow tie.'),(14,20,_binary '\0','/src/main/resources/static/img/character-Goofy.jpg','Goofy',30,'Goofy is a cartoon character created by The Walt Disney Company. Goofy is a tall, anthropomorphic dog who typically wears a turtle neck and vest, with pants, shoes, white gloves, and a tall hat originally designed as a rumpled fedora. Goofy is a close friend of Mickey Mouse and Donald Duck. He is normally characterized as hopelessly clumsy and dim-witted, yet this interpretation is not always definitive; occasionally Goofy is shown as intuitive and clever, albeit in his own unique, eccentric way.'),(15,7,_binary '\0','/src/main/resources/static/img/character-Nemo.png','Nemo',2,'Nemo, a small fish, much loved and protected by his father, is lost outside the great barrier reef of Australia, after being captured by this reef, Nemo will end up in a fish tank in Sydney.'),(16,12,_binary '\0','/src/main/resources/static/img/character-Dory.jpg','Dory',3,'It is a very funny blue and black surgeon fish (Paracanthurus hepatus). He suffers from anterograde amnesia or short-term memory loss. Accompany Marlin in the search for her son. She is optimistic, clumsy, funny, hyperactive with out eyes and has a free spirit.');
/*!40000 ALTER TABLE `characters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genres` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `deleted` bit(1) DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genres`
--

LOCK TABLES `genres` WRITE;
/*!40000 ALTER TABLE `genres` DISABLE KEYS */;
INSERT INTO `genres` VALUES (1,_binary '\0','/src/main/resources/static/img/genre-Animation.png','Animation 2D'),(2,_binary '\0','/src/main/resources/static/img/genre-Animation3D.jpg','Animation 3D'),(3,_binary '\0','/src/main/resources/static/img/genre-Musical.jpg','Musical');
/*!40000 ALTER TABLE `genres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `creation_date` date NOT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `genre_id` bigint DEFAULT NULL,
  `image` varchar(255) NOT NULL,
  `rating` int DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjp8fsy8a0kkmdi04i81v05c6a` (`genre_id`),
  CONSTRAINT `FKjp8fsy8a0kkmdi04i81v05c6a` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'1994-06-15',_binary '\0',1,'/src/main/resources/static/img/movie-LionKing.jpg',4,'Lion King'),(2,'1996-03-14',_binary '\0',1,'/src/main/resources/static/img/movie-ToyStory.jpg',4,'Toy Story'),(3,'1999-12-02',_binary '\0',2,'/src/main/resources/static/img/movie-ToyStory2.jpg',3,'Toy Story 2'),(9,'2004-08-03',_binary '\0',1,'/src/main/resources/static/img/movie-LosTresMosqueteros.png',4,'Mickey, Donald y Goofy: Los Tres Mosqueteros'),(10,'2003-07-03',_binary '\0',2,'/src/main/resources/static/img/movie-FindingNemo.jpg',5,'Finding Nemo'),(11,'2006-01-20',_binary '\0',3,'/src/main/resources/static/img/movie-HighSchoolMusical.jpg',4,'High School Musical');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) NOT NULL,
  `account_non_locked` bit(1) NOT NULL,
  `credentials_non_expired` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (8,_binary '',_binary '',_binary '',_binary '','11111111','test_user@gmail.com'),(9,_binary '',_binary '',_binary '',_binary '','22222222','myuser@gmail.com'),(10,_binary '',_binary '',_binary '',_binary '','12345678','user@gmail.com');
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

-- Dump completed on 2022-01-12 12:42:56
