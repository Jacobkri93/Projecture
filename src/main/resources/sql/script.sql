-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema useradmin
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema useradmin
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `useradmin` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema user_admin
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema user_admin
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `user_admin` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `useradmin` ;

-- -----------------------------------------------------
-- Table `useradmin`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `useradmin`.`item` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `price` DECIMAL(8,2) NULL DEFAULT NULL,
  `description` VARCHAR(120) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `useradmin`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `useradmin`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `role` VARCHAR(20) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `useradmin`.`wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `useradmin`.`wishlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL DEFAULT NULL,
  `item_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `FK_item_id_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `FK_item_id`
    FOREIGN KEY (`item_id`)
    REFERENCES `useradmin`.`item` (`id`),
  CONSTRAINT `FK_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `useradmin`.`users` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

USE `user_admin` ;

-- -----------------------------------------------------
-- Table `user_admin`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_admin`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `user_admin`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_admin`.`project` (
  `project_id` INT NOT NULL AUTO_INCREMENT,
  `project_name` VARCHAR(45) NULL DEFAULT NULL,
  `week_duration` INT NOT NULL,
  `user_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`project_id`),
  INDEX `user_id` (`user_id` ASC) VISIBLE,
  CONSTRAINT `project_ibfk_1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user_admin`.`user` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 34
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `user_admin`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_admin`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(150) NULL DEFAULT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `description_UNIQUE` (`description` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `user_admin`.`subtask`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_admin`.`subtask` (
  `subtask_id` INT NOT NULL AUTO_INCREMENT,
  `task_name` VARCHAR(45) NULL DEFAULT NULL,
  `project_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`subtask_id`),
  INDEX `project_id_idx` (`project_id` ASC) VISIBLE,
  CONSTRAINT `project_id`
    FOREIGN KEY (`project_id`)
    REFERENCES `user_admin`.`project` (`project_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `user_admin`.`subtaskrole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_admin`.`subtaskrole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hours` DECIMAL(6,2) NOT NULL,
  `subtask_id` INT NOT NULL,
  `taskrole_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `subtaskRole_idx` (`subtask_id` ASC) VISIBLE,
  INDEX `taskrole_idx` (`taskrole_id` ASC) VISIBLE,
  CONSTRAINT `subtask`
    FOREIGN KEY (`subtask_id`)
    REFERENCES `user_admin`.`subtask` (`subtask_id`),
  CONSTRAINT `taskrole_id`
    FOREIGN KEY (`taskrole_id`)
    REFERENCES `user_admin`.`role` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
