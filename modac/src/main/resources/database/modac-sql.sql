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

drop database `modac`;

CREATE SCHEMA IF NOT EXISTS `modac` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

USE `modac` ;

-- -----------------------------------------------------
-- Table `modac`.`membership`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`membership` (
  `grade` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`grade`))
ENGINE = InnoDB;

INSERT INTO `modac`.`membership` values ('bronze');
INSERT INTO `modac`.`membership` values ('silver');
INSERT INTO `modac`.`membership` values ('gold');


-- -----------------------------------------------------
-- Table `modac`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`users` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(30) NOT NULL,
  `id` VARCHAR(20) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `single_theme` VARCHAR(45) NULL,
  `total_second` INT NULL DEFAULT 0,
  `membership_grade` VARCHAR(20) NULL DEFAULT 'bronze',
  PRIMARY KEY (`seq`),
  INDEX `fk_users_membership1_idx` (`membership_grade` ASC) VISIBLE,
  CONSTRAINT `fk_users_membership1`
    FOREIGN KEY (`membership_grade`)
    REFERENCES `modac`.`membership` (`grade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`categories` (
  `name` VARCHAR(30) NOT NULL DEFAULT '기타',
  `template_filepath` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;

INSERT INTO `modac`.`categories` values ('알고리즘', 'algo test path');
INSERT INTO `modac`.`categories` values ('CS', 'CS test path');
INSERT INTO `modac`.`categories` values ('개발', '개발 test path');
INSERT INTO `modac`.`categories` values ('기획', '기획 test path');
INSERT INTO `modac`.`categories` values ('기타','기타 test path');

-- -----------------------------------------------------
-- Table `modac`.`todos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`todos` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `users_seq` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `status` INT NOT NULL DEFAULT 0,
  `total_second` VARCHAR(15) NULL DEFAULT '0',
  `categories_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_todos_users_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_todos_categories1_idx` (`categories_name` ASC) VISIBLE,
  CONSTRAINT `fk_todos_users`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_todos_categories1`
    FOREIGN KEY (`categories_name`)
    REFERENCES `modac`.`categories` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`articles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`articles` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(50) NOT NULL,
  `filepath` VARCHAR(150) NULL,
  `registered_time` DATETIME NULL DEFAULT now(),
  `public_type` INT NOT NULL,
  `view_count` INT NULL DEFAULT 0,
  `like_count` INT NULL DEFAULT 0,
  `comment_count` INT NULL DEFAULT 0,
  `total_second` INT NOT NULL,
  `users_seq` INT NOT NULL,
  `categories_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_articles_users1_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_articles_categories1_idx` (`categories_name` ASC) VISIBLE,
  CONSTRAINT `fk_articles_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articles_categories1`
    FOREIGN KEY (`categories_name`)
    REFERENCES `modac`.`categories` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


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
    REFERENCES `modac`.`articles` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_likes_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`comments` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(100) NOT NULL,
  `registered_time` DATETIME NULL DEFAULT now(),
  `articles_seq` INT NOT NULL,
  `users_seq` INT NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_comment_articles1_idx` (`articles_seq` ASC) VISIBLE,
  INDEX `fk_comment_users1_idx` (`users_seq` ASC) VISIBLE,
  CONSTRAINT `fk_comment_articles1`
    FOREIGN KEY (`articles_seq`)
    REFERENCES `modac`.`articles` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`chat_rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`chat_rooms` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `last_log_key` INT NULL,
  `last_log_time` DATETIME NULL,
  PRIMARY KEY (`seq`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`rooms` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NOT NULL,
  `description` VARCHAR(100) NULL,
  `max_size` INT NULL DEFAULT 6,
  `multi_theme` VARCHAR(30) NULL,
  `public_type` INT NOT NULL,
  `invitation_code` VARCHAR(20) NULL,
  `users_seq` INT NOT NULL,
  `chat_rooms_seq` INT NOT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_rooms_users1_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_rooms_chat_rooms1_idx` (`chat_rooms_seq` ASC) VISIBLE,
  CONSTRAINT `fk_rooms_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rooms_chat_rooms1`
    FOREIGN KEY (`chat_rooms_seq`)
    REFERENCES `modac`.`chat_rooms` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`guests`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`guests` (
  `entrance_time` DATETIME NULL DEFAULT now(),
  `users_seq` INT NOT NULL,
  `rooms_seq` INT NOT NULL,
  INDEX `fk_guests_users1_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_guests_rooms1_idx` (`rooms_seq` ASC) VISIBLE,
  PRIMARY KEY (`rooms_seq`, `users_seq`),
  CONSTRAINT `fk_guests_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_guests_rooms1`
    FOREIGN KEY (`rooms_seq`)
    REFERENCES `modac`.`rooms` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


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
  CONSTRAINT `fk_following_users1`
    FOREIGN KEY (`from_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_following_users2`
    FOREIGN KEY (`to_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_following_chat_rooms1`
    FOREIGN KEY (`chat_rooms_seq`)
    REFERENCES `modac`.`chat_rooms` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`chat_logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`chat_logs` (
  `key` INT NOT NULL AUTO_INCREMENT,
  `users_seq` INT NOT NULL,
  `chat_rooms_seq` INT NOT NULL,
  `send_time` DATETIME NULL DEFAULT now(),
  PRIMARY KEY (`key`),
  INDEX `fk_chat_logs_users1_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_chat_logs_chat_rooms1_idx` (`chat_rooms_seq` ASC) VISIBLE,
  CONSTRAINT `fk_chat_logs_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chat_logs_chat_rooms1`
    FOREIGN KEY (`chat_rooms_seq`)
    REFERENCES `modac`.`chat_rooms` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`notices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`notices` (
  `seq` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(30) NULL,
  `time` DATETIME NULL DEFAULT now(),
  `status` INT NULL DEFAULT 0,
  `users_seq` INT NOT NULL,
  `followings_seq` INT NULL,
  `likes_seq` INT NULL,
  `comments_seq` INT NULL,
  PRIMARY KEY (`seq`),
  INDEX `fk_notices_users1_idx` (`users_seq` ASC) VISIBLE,
  INDEX `fk_notices_followings1_idx` (`followings_seq` ASC) VISIBLE,
  INDEX `fk_notices_likes1_idx` (`likes_seq` ASC) VISIBLE,
  INDEX `fk_notices_comments1_idx` (`comments_seq` ASC) VISIBLE,
  CONSTRAINT `fk_notices_users1`
    FOREIGN KEY (`users_seq`)
    REFERENCES `modac`.`users` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notices_followings1`
    FOREIGN KEY (`followings_seq`)
    REFERENCES `modac`.`followings` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notices_likes1`
    FOREIGN KEY (`likes_seq`)
    REFERENCES `modac`.`likes` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_notices_comments1`
    FOREIGN KEY (`comments_seq`)
    REFERENCES `modac`.`comments` (`seq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `modac`.`musics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `modac`.`musics` (
  `name` VARCHAR(50) NULL,
  `filepath` VARCHAR(50) NULL,
  `seq` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`seq`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
