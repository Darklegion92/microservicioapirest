CREATE DATABASE  IF NOT EXISTS `apirest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `apirest`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: apirest
-- ------------------------------------------------------
-- Server version	5.5.67-MariaDB

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
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulos` (
  `idarticulo` varchar(20) NOT NULL,
  `nombreArticulo` varchar(150) NOT NULL,
  `precioArticulo` double NOT NULL DEFAULT '0',
  `descuentoArticulo` int(11) NOT NULL DEFAULT '0',
  `cantidadArticulo` int(11) NOT NULL DEFAULT '0',
  `idMarca` varchar(8) NOT NULL DEFAULT 'null',
  `idFamilia` varchar(8) NOT NULL DEFAULT 'null',
  `idGrupo` varchar(8) NOT NULL DEFAULT 'null',
  PRIMARY KEY (`idarticulo`),
  UNIQUE KEY `idarticulo_UNIQUE` (`idarticulo`),
  KEY `fgk_articulomarca_idx` (`idMarca`),
  KEY `fgk_articulofamilia_idx` (`idFamilia`),
  KEY `fgk_articulogrupo_idx` (`idGrupo`),
  CONSTRAINT `fgk_articulofamilia` FOREIGN KEY (`idFamilia`) REFERENCES `familias` (`idfamilia`) ON DELETE CASCADE,
  CONSTRAINT `fgk_articulogrupo` FOREIGN KEY (`idGrupo`) REFERENCES `grupos` (`idgrupo`) ON DELETE CASCADE,
  CONSTRAINT `fgk_articulomarca` FOREIGN KEY (`idMarca`) REFERENCES `marcas` (`idmarca`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `familias`
--

DROP TABLE IF EXISTS `familias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `familias` (
  `idfamilia` varchar(8) NOT NULL,
  `nombreFamilia` varchar(120) NOT NULL,
  PRIMARY KEY (`idfamilia`),
  UNIQUE KEY `idfamilia_UNIQUE` (`idfamilia`),
  UNIQUE KEY `nombreFamilia_UNIQUE` (`nombreFamilia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `idgrupo` varchar(8) NOT NULL,
  `nombreGrupo` varchar(120) NOT NULL,
  `idFamilia` varchar(8) NOT NULL,
  PRIMARY KEY (`idgrupo`),
  UNIQUE KEY `idgrupo_UNIQUE` (`idgrupo`),
  KEY `fgk_familiaGrupo_idx` (`idFamilia`),
  CONSTRAINT `fgk_familiaGrupo` FOREIGN KEY (`idFamilia`) REFERENCES `familias` (`idfamilia`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcas` (
  `idmarca` varchar(8) NOT NULL,
  `nombreMarca` varchar(120) NOT NULL,
  PRIMARY KEY (`idmarca`),
  UNIQUE KEY `idmarca_UNIQUE` (`idmarca`),
  UNIQUE KEY `nombreMarca_UNIQUE` (`nombreMarca`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `puntosclientes`
--

DROP TABLE IF EXISTS `puntosclientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `puntosclientes` (
  `documentoCliente` varchar(18) NOT NULL,
  `acumulado` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`documentoCliente`),
  UNIQUE KEY `documentoCliente_UNIQUE` (`documentoCliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'apirest'
--

--
-- Dumping routines for database 'apirest'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-27 21:05:05
