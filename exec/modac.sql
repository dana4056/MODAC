-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema modac
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema modac
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `modac` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `modac` ;

-- -----------------------------------------------------
-- Table `modac`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`categories` (
  `name` VARCHAR(30) NOT NULL DEFAULT '기타',
  `template_filepath` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`users` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(30) NOT NULL,
  `id` VARCHAR(20) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `cat_skin` INT NULL DEFAULT NULL,
  `single_theme` VARCHAR(45) NULL DEFAULT NULL,
  `total_second` INT NULL DEFAULT '0',
  `membership_level` VARCHAR(20) NULL DEFAULT 'BRONZE_LV1',
  `point` INT NULL DEFAULT '0',
  `max_point` INT NULL DEFAULT '50',
  PRIMARY KEY (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`articles` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `filepath` VARCHAR(150) NULL DEFAULT NULL,
  `registered_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `public_type` INT NOT NULL,
  `view_count` INT NULL DEFAULT '0',
  `like_count` INT NULL DEFAULT '0',
  `comment_count` INT NULL DEFAULT '0',
  `total_second` INT NOT NULL,
  `users_seq` INT NOT NULL,
  `categories_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_articles_users1_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_articles_categories1_idx` (`categories_name` ASC) VISIBLE,
  CONSTRAINT `fk_articles_categories1`
    FOREIGN KEY (`categories_name`)
    REFERENCES `modac`.`categories` (`name`),
  CONSTRAINT `fk_articles_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 160
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`chat_rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`chat_rooms` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `last_message_seq` VARCHAR(150) NULL DEFAULT NULL,
  `last_message_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 123
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`chat_messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`chat_messages` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `users_seq` INT NOT NULL,
  `chat_rooms_seq` INT NOT NULL,
  `send_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `message` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_chat_logs_users1_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_chat_logs_chat_rooms1_idx` (`chat_rooms_seq` ASC) VISIBLE,
  CONSTRAINT `fk_chat_logs_chat_rooms1`
    FOREIGN KEY (`chat_rooms_seq`)
    REFERENCES `modac`.`chat_rooms` (`seq`),
  CONSTRAINT `fk_chat_logs_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`comments` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(100) NOT NULL,
  `registered_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `articles_seq` INT NOT NULL,
  `users_seq` INT NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_comment_articles1_idx` (`articles_seq` ASC) VISIBLE,
  INDEX `fk_comment_users1_idx` (`users_seq` ASC) VISIBLE,
  CONSTRAINT `fk_comment_articles1`
    FOREIGN KEY (`articles_seq`)
    REFERENCES `modac`.`articles` (`seq`),
  CONSTRAINT `fk_comment_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`followings`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`followings` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `from_seq` INT NOT NULL,
  `to_seq` INT NOT NULL,
  `chat_rooms_seq` INT NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_following_users1_idx` (`from_seq` ASC) VISIBLE,
  INDEX `fk_following_users2_idx` (`to_seq` ASC) VISIBLE,
  INDEX `fk_following_chat_rooms1_idx` (`chat_rooms_seq` ASC) VISIBLE,
  CONSTRAINT `fk_following_chat_rooms1`
    FOREIGN KEY (`chat_rooms_seq`)
    REFERENCES `modac`.`chat_rooms` (`seq`),
  CONSTRAINT `fk_following_users1`
    FOREIGN KEY (`from_seq`)
    REFERENCES `modac`.`users` (`seq`),
  CONSTRAINT `fk_following_users2`
    FOREIGN KEY (`to_seq`)
    REFERENCES `modac`.`users` (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 33
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`likes` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `users_seq` INT NOT NULL,
  `articles_seq` INT NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_likes_articles1_idx` (`articles_seq` ASC) VISIBLE,
  INDEX `fk_likes_users1_idx` (`users_seq` ASC) VISIBLE,
  CONSTRAINT `fk_likes_articles1`
    FOREIGN KEY (`articles_seq`)
    REFERENCES `modac`.`articles` (`seq`),
  CONSTRAINT `fk_likes_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`musics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`musics` (
  `name` VARCHAR(50) NULL DEFAULT NULL,
  `filepath` VARCHAR(50) NULL DEFAULT NULL,
  `seq` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`seq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`notifications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`notifications` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `registered_time` DATETIME NOT NULL,
  `is_read` TINYINT(1) NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `articles_seq` INT NULL DEFAULT NULL,
  `from_users_seq` INT NULL DEFAULT NULL,
  `to_users_seq` INT NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_notification_articles1_idx` (`articles_seq` ASC) VISIBLE,
  INDEX `fk_notification_users1_idx` (`from_users_seq` ASC) VISIBLE,
  INDEX `fk_notification_users2_idx` (`to_users_seq` ASC) VISIBLE,
  CONSTRAINT `fk_notification_articles1`
    FOREIGN KEY (`articles_seq`)
    REFERENCES `modac`.`articles` (`seq`),
  CONSTRAINT `fk_notification_users1`
    FOREIGN KEY (`from_users_seq`)
    REFERENCES `modac`.`users` (`seq`),
  CONSTRAINT `fk_notification_users2`
    FOREIGN KEY (`to_users_seq`)
    REFERENCES `modac`.`users` (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 45
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`rooms` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NOT NULL,
  `description` VARCHAR(100) NULL DEFAULT NULL,
  `max_size` INT NULL DEFAULT '6',
  `current_size` INT NULL DEFAULT '1',
  `multi_theme` VARCHAR(30) NULL DEFAULT NULL,
  `public_type` INT NOT NULL,
  `invitation_code` VARCHAR(20) NULL DEFAULT NULL,
  `users_seq` INT NOT NULL,
  `chat_rooms_seq` INT NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_rooms_users1_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_rooms_chat_rooms1_idx` (`chat_rooms_seq` ASC) VISIBLE,
  CONSTRAINT `fk_rooms_chat_rooms1`
    FOREIGN KEY (`chat_rooms_seq`)
    REFERENCES `modac`.`chat_rooms` (`seq`),
  CONSTRAINT `fk_rooms_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 102
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`participants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`participants` (
  `rooms_seq` INT NOT NULL,
  `users_seq` INT NOT NULL,
  `nickname` VARCHAR(20) NULL DEFAULT NULL,
  `status` INT NULL DEFAULT NULL,
  `attend` INT NULL DEFAULT NULL,
  `cat_skin` INT NULL DEFAULT NULL,
  `categories_name` VARCHAR(30) NULL DEFAULT NULL,
  `registered_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`rooms_seq`, `users_seq`),
  INDEX `fk_participants_rooms1_idx` (`rooms_seq` ASC) VISIBLE,
  CONSTRAINT `fk_participants_rooms1`
    FOREIGN KEY (`rooms_seq`)
    REFERENCES `modac`.`rooms` (`seq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `modac`.`todos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`todos` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `users_seq` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `status` INT NOT NULL DEFAULT '0',
  `total_second` VARCHAR(15) NULL DEFAULT '0',
  `categories_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_todos_users_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_todos_categories1_idx` (`categories_name` ASC) VISIBLE,
  CONSTRAINT `fk_todos_categories1`
    FOREIGN KEY (`categories_name`)
    REFERENCES `modac`.`categories` (`name`),
  CONSTRAINT `fk_todos_users`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`))
ENGINE = InnoDB
AUTO_INCREMENT = 243
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
