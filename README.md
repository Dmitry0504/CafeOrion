# **Cafe Orion**

Cafe automation system is designed to make things easier.
To get started, you need to create a database. I used MySQL.
<br />
## Commands for creating database tables. 
<br />
CREATE DATABASE `cafe`; <br />
USE cafe; <br />
CREATE TABLE `categories` ( <br />
  `id` int NOT NULL AUTO_INCREMENT, <br />
  `title` varchar(45) NOT NULL, <br />
  PRIMARY KEY (`id`) <br />
); <br />

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


