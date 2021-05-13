-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema user_admin
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema user_admin
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `user_admin` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `user_admin`;

-- -----------------------------------------------------
-- Table `user_admin`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_admin`.`user`
(
    `user_id`  INT         NOT NULL AUTO_INCREMENT,
    `email`    VARCHAR(90) NOT NULL,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 15
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `user_admin`.`project`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_admin`.`project`
(
    `project_id`    INT         NOT NULL AUTO_INCREMENT,
    `project_name`  VARCHAR(45) NULL DEFAULT NULL,
    `week_duration` DATE        NOT NULL,
    `user_id`       INT         NULL DEFAULT NULL,
    PRIMARY KEY (`project_id`),
    INDEX `user_id` (`user_id` ASC) VISIBLE,
    CONSTRAINT `project_ibfk_1`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_admin`.`user` (`user_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 2
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;



# CREATE SCHEMA IF NOT EXISTS `user_admin` ;
#
# CREATE TABLE IF NOT EXISTS `user_admin`.`user` (
#   `user_id` INT NOT NULL AUTO_INCREMENT,
#   `email` VARCHAR(90) NOT NULL,
#   `password` VARCHAR(45) NOT NULL,
# PRIMARY KEY (`user_id`),
# UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
#
# CREATE TABLE `user_admin`.`project` (
# `project_id` INT NOT NULL AUTO_INCREMENT,
# `project_name` VARCHAR(45) NULL,
# `week_duration` DATE NOT NULL,
# user_id int,
# PRIMARY KEY (project_id),
# FOREIGN KEY (user_id) REFERENCES user(user_id));


