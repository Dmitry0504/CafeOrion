# Cafe Orion

Cafe automation system is designed to make things easier.
To get started, you need to create a database. I used MySQL.

# Commands for creating database tables. 

CREATE DATABASE `cafe`;
USE cafe;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `subcategories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `categories_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`)
);

CREATE TABLE `dishes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `price` int DEFAULT NULL,
  `subcategories_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`subcategories_id`) REFERENCES `subcategories` (`id`)
);


