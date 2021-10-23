create database test;

use test;

CREATE TABLE `user` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `monthly_salary` float NOT NULL,
  `monthly_expense` float NOT NULL,
  `created_date` date,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;