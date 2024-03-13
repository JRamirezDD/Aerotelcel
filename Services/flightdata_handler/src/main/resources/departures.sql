-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: openskytestdb
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `departures`
--

DROP TABLE IF EXISTS `departures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departures` (
                              `callsign` varchar(255) NOT NULL,
                              `arrival_airport_candidates_count` int NOT NULL,
                              `departure_airport_candidates_count` varchar(255) DEFAULT NULL,
                              `est_arrival_airport` varchar(255) DEFAULT NULL,
                              `est_arrival_airport_horiz_distance` int NOT NULL,
                              `est_arrival_airport_vert_distance` int NOT NULL,
                              `est_departure_airport` varchar(255) DEFAULT NULL,
                              `est_departure_airport_horiz_distance` int NOT NULL,
                              `est_departure_airport_vert_distance` int NOT NULL,
                              `first_seen` datetime(6) DEFAULT NULL,
                              `icao24` varchar(255) DEFAULT NULL,
                              `last_seen` datetime(6) DEFAULT NULL,
                              `airport` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`callsign`),
                              KEY `FKe9qedcu6p5jxu4874ymuif3ti` (`airport`),
                              CONSTRAINT `FKe9qedcu6p5jxu4874ymuif3ti` FOREIGN KEY (`airport`) REFERENCES `mainairportdb` (`iata`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departures`
--

LOCK TABLES `departures` WRITE;
/*!40000 ALTER TABLE `departures` DISABLE KEYS */;
INSERT INTO `departures` VALUES ('AAL2996',1,'8','KJFK',12425,152515,'MMMX',121421,8625261,'2024-03-13 07:20:00.000000','c4ko15','2024-03-13 11:59:00.000000','MEX'),('AMX402',1,'9','KJFK',51248,1294817,'MMMX',1284712,1241207,'2024-03-13 15:11:00.000000','p9bh56','2024-03-13 19:30:00.000000','MEX'),('AMX404',1,'7','KJFK',63253,91837,'MMMX',2158419,1258213,'2024-03-13 23:19:00.000000','k1op98','2024-03-13 04:25:00.000000','MEX'),('AMX408',1,'13','KJFK',125,524,'MMMX',4215154,1551424,'2024-03-13 13:20:00.000000','b4jk12','2024-03-13 17:04:00.000000','MEX'),('AVA005',12,'13','SKBO',0,0,'KMIA',21402142,12414124,'2024-03-10 06:50:05.000000','a1ac91','2024-03-10 07:20:05.000000','MIA'),('DAL624',3,'1','KJFK',13,3,'MMMX',214214,5524141,'2024-03-13 08:50:06.000000','a3ag98','2024-03-13 02:00:00.000000','MEX');
/*!40000 ALTER TABLE `departures` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-13  0:36:26