CREATE USER 'std'@'localhost' IDENTIFIED BY 'std';


CREATE DATABASE  IF NOT EXISTS `std` /*!40100 DEFAULT CHARACTER SET latin1 */;

GRANT ALL PRIVILEGES ON std.* TO 'std'@'localhost';

FLUSH PRIVILEGES;

USE `std`;

--
-- Table structure for table `cousine`
--

DROP TABLE IF EXISTS `cousine`;

CREATE TABLE `cousine` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `store_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjlfidudl1gwqem0flrlomvlcl` (`store_id`),
  CONSTRAINT `FKjlfidudl1gwqem0flrlomvlcl` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


--
-- Table structure for table `product_order`
--

DROP TABLE IF EXISTS `product_order`;

CREATE TABLE `product_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `price` decimal(19,2) DEFAULT NULL,
  `quantity` decimal(19,2) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf3mf8lkjkusnfxb32rew7wkbx` (`order_id`),
  KEY `FKh73acsd9s5wp6l0e55td6jr1m` (`product_id`),
  CONSTRAINT `FKf3mf8lkjkusnfxb32rew7wkbx` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `FKh73acsd9s5wp6l0e55td6jr1m` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cousine_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrdeaidvchc804eoywh3y2hr6s` (`cousine_id`),
  CONSTRAINT `FKrdeaidvchc804eoywh3y2hr6s` FOREIGN KEY (`cousine_id`) REFERENCES `cousine` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `last_update` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK59mx981vt9udoga067ma5reif` (`customer_id`),
  CONSTRAINT `FK59mx981vt9udoga067ma5reif` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;


-- INITIAL DATA


INSERT INTO std.cousine(id, name) values(1, 'Italian');

INSERT INTO std.store (`id`,`address`,`name`, `cousine_id`) VALUES (1, '123 Convent Garden', 'Super Pizza Store', 1);

INSERT INTO  `std`.`product`
(`description`,
`name`,
`price`,
`store_id`) VALUES ('The best one..', 'Pizza Calabrezza', '19.88', 1);


INSERT INTO  `std`.`product`
(`description`,
`name`,
`price`,
`store_id`) VALUES ('Delicious pizza..', 'Pizza Portuguese', '20.00', 1);


INSERT INTO `std`.`customer`
(
`email`,
`name`,
`password`)
VALUES ('tiago@std.com', 'Tiago', '1234');

commit;

