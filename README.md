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

CREATE TABLE `subcategories` ( <br />
  `id` int NOT NULL AUTO_INCREMENT, <br />
  `title` varchar(45) NOT NULL, <br />
  `categories_id` int DEFAULT NULL, <br />
  PRIMARY KEY (`id`), <br />
  FOREIGN KEY (`categories_id`) REFERENCES `categories` (`id`) <br />
); <br />

CREATE TABLE `dishes` ( <br />
  `id` int NOT NULL AUTO_INCREMENT, <br />
  `title` varchar(45) NOT NULL, <br />
  `price` int DEFAULT NULL, <br />
  `subcategories_id` int DEFAULT NULL, <br />
  PRIMARY KEY (`id`), <br />
  FOREIGN KEY (`subcategories_id`) REFERENCES `subcategories` (`id`) <br />
); <br />
