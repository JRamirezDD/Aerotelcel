SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `openSkyTestDB`;
USE `openSkyTestDB`;
--
-- Database: `openSkyTestDB`
--

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE IF NOT EXISTS flights (
    id INT AUTO_INCREMENT NOT NULL,
    icao24 varchar(255),
    callsign varchar(255),
    origin_country varchar(255),
    time_position TIMESTAMP,
    last_contact TIMESTAMP,
    longitude FLOAT,
    latitude FLOAT,
    baro_altitude FLOAT,
    on_ground BOOLEAN,
    velocity FLOAT,
    true_track FLOAT,
    vertical_rate FLOAT,
    sensors varchar(255),
    geo_altitude FLOAT,
    squawk varchar(255),
    spi BOOLEAN,
    position_source INT, 
    category INT,
    PRIMARY KEY (id)
)