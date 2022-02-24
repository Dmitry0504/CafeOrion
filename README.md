# **Cafe Orion**

Cafe automation system is designed to make things easier.
To get started, you need to create a database. I used MySQL.
<br />
## Commands for creating database tables. 
CREATE DATABASE `cafe`; <br />
USE cafe; <br />
CREATE TABLE `categories` ( <br />
  `id` int NOT NULL AUTO_INCREMENT, <br />
  `title` varchar(45) NOT NULL, <br />
  'version' int DEFAULT '0', <br />
  PRIMARY KEY (`id`) <br />
); <br />

CREATE TABLE `subcategories` ( <br />
  `id` int NOT NULL AUTO_INCREMENT, <br />
  `title` varchar(45) NOT NULL, <br />
  `categories_id` int DEFAULT NULL, <br />
  `version` int DEFAULT '0', <br />
  PRIMARY KEY (`id`), <br />
  FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) <br />
); <br />

CREATE TABLE `dishes` ( <br />
  `id` int NOT NULL AUTO_INCREMENT, <br />
  `title` varchar(45) NOT NULL, <br />
  `price` int DEFAULT NULL, <br />
  `subcategories_id` int DEFAULT NULL, <br />
  `version` int DEFAULT '0', <br />
  PRIMARY KEY (`id`), <br />
  FOREIGN KEY (`subcategories_id`) REFERENCES `subcategories` (`id`) <br />
); <br />

CREATE TABLE `users` ( <br />
`id` int NOT NULL AUTO_INCREMENT, <br />
`username` varchar(15) NOT NULL, <br />
`password` varchar(85) DEFAULT NULL, <br />
`enabled` tinyint(1) NOT NULL, <br />
`role` varchar(15) NOT NULL, <br />
`version` int DEFAULT '0', <br />
PRIMARY KEY (`id`) <br />
); <br />

CREATE TABLE `orders` ( <br />
`id` int NOT NULL AUTO_INCREMENT, <br />
`dish_id` int DEFAULT NULL, <br />
`user_id` int DEFAULT NULL, <br />
`status` varchar(15) DEFAULT NULL, <br />
`order_time` timestamp NULL DEFAULT NULL, <br />
`version` int DEFAULT '0', <br />
PRIMARY KEY (`id`), <br />
FOREIGN KEY (`dish_id`) REFERENCES `dishes` (`id`), <br />
FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) <br />
); <br />
