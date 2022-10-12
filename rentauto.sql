/*
SQLyog Community
MySQL - 10.4.24-MariaDB : Database - rentauto
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rentauto` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `rentauto`;

/*Table structure for table `automobil` */

DROP TABLE IF EXISTS `automobil`;

CREATE TABLE `automobil` (
  `AutomobilID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(50) NOT NULL,
  `Vlasnik` varchar(50) NOT NULL,
  `Opis` varchar(50) NOT NULL,
  `CenaPoDanu` double NOT NULL,
  `TipAutomobilaID` bigint(20) NOT NULL,
  PRIMARY KEY (`AutomobilID`),
  KEY `ModelID` (`TipAutomobilaID`),
  CONSTRAINT `automobil_ibfk_1` FOREIGN KEY (`TipAutomobilaID`) REFERENCES `model` (`ModelID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `automobil` */

LOCK TABLES `automobil` WRITE;

insert  into `automobil`(`AutomobilID`,`Naziv`,`Vlasnik`,`Opis`,`CenaPoDanu`,`TipAutomobilaID`) values 
(1,'Skoda','Bogdan Jelic','Fabia',15,5),
(2,'Fiat','Nikola Knezevic','500',20,1),
(3,'Mercedes','Ivan Djordjevic','G klasa',60,3),
(4,'Volkswagen','Djordje Smiljanic','Passat',50,4),
(5,'Peugeot','Mateja Krstic','207',15,2),
(6,'Lamboghini','George West','Hurracan',80,1);

UNLOCK TABLES;

/*Table structure for table `iznajmljivanje` */

DROP TABLE IF EXISTS `iznajmljivanje`;

CREATE TABLE `iznajmljivanje` (
  `IznajmljivanjeID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DatumOd` date NOT NULL,
  `DatumDo` date NOT NULL,
  `Iznos` double NOT NULL,
  `Cena` double NOT NULL,
  `KlijentID` bigint(20) NOT NULL,
  `KorisnikID` bigint(20) NOT NULL,
  `AutomobilID` bigint(20) NOT NULL,
  PRIMARY KEY (`IznajmljivanjeID`,`KlijentID`,`AutomobilID`),
  KEY `KlijentID` (`KlijentID`),
  KEY `KorisnikID` (`KorisnikID`),
  KEY `AutomobilID` (`AutomobilID`),
  CONSTRAINT `iznajmljivanje_ibfk_1` FOREIGN KEY (`KlijentID`) REFERENCES `klijent` (`KlijentID`),
  CONSTRAINT `iznajmljivanje_ibfk_2` FOREIGN KEY (`KorisnikID`) REFERENCES `korisnik` (`KorisnikID`),
  CONSTRAINT `iznajmljivanje_ibfk_3` FOREIGN KEY (`AutomobilID`) REFERENCES `automobil` (`AutomobilID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `iznajmljivanje` */

LOCK TABLES `iznajmljivanje` WRITE;

insert  into `iznajmljivanje`(`IznajmljivanjeID`,`DatumOd`,`DatumDo`,`Iznos`,`Cena`,`KlijentID`,`KorisnikID`,`AutomobilID`) values 
(1,'2022-09-24','2022-09-27',70,25,1,1,5),
(2,'2022-09-30','2022-10-05',100,25,3,2,2),
(3,'2022-10-13','2022-10-19',200,25,6,1,3);

UNLOCK TABLES;

/*Table structure for table `klijent` */

DROP TABLE IF EXISTS `klijent`;

CREATE TABLE `klijent` (
  `KlijentID` bigint(20) NOT NULL AUTO_INCREMENT,
  `JMBG` bigint(20) NOT NULL,
  `Ime` varchar(50) NOT NULL,
  `Drzava` varchar(50) NOT NULL,
  PRIMARY KEY (`KlijentID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `klijent` */

LOCK TABLES `klijent` WRITE;

insert  into `klijent`(`KlijentID`,`JMBG`,`Ime`,`Drzava`) values 
(1,1234567897899,'Marko Markovic','Srbija'),
(2,1236544569877,'Ivan Ivanovic','Srbija'),
(3,7896541233215,'Marija Gordic','Crna Gora'),
(4,6547899871235,'Ivana Petrovic','Hrvatska'),
(5,4563214568454,'Petar Ivic','Srbija'),
(6,1236547896541,'Lewis Hamilton','Great Britain'),
(7,3214568974652,'Ngolo Kante','France');

UNLOCK TABLES;

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `KorisnikID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ImeKorisnika` varchar(50) NOT NULL,
  `PrezimeKorisnika` varchar(50) NOT NULL,
  `KorisnickoIme` varchar(50) NOT NULL,
  `KorisnickaLozinka` varchar(50) NOT NULL,
  PRIMARY KEY (`KorisnikID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `korisnik` */

LOCK TABLES `korisnik` WRITE;

insert  into `korisnik`(`KorisnikID`,`ImeKorisnika`,`PrezimeKorisnika`,`KorisnickoIme`,`KorisnickaLozinka`) values 
(1,'Bogdan','Jelic','a','a'),
(2,'Nikola','Popovic','b','b');

UNLOCK TABLES;

/*Table structure for table `model` */

DROP TABLE IF EXISTS `model`;

CREATE TABLE `model` (
  `ModelID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(50) NOT NULL,
  `Opis` varchar(50) NOT NULL,
  PRIMARY KEY (`ModelID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `model` */

LOCK TABLES `model` WRITE;

insert  into `model`(`ModelID`,`Naziv`,`Opis`) values 
(1,'Kupe','3 vrata, sportski'),
(2,'Hecbek','5 vrata, putnicki'),
(3,'Dzip','5 vrata, 4x4'),
(4,'Limuzina','5 vrata, porodicni'),
(5,'Karavan','5 vrata, porodicni');

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
