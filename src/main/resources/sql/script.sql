CREATE SCHEMA IF NOT EXISTS `user_admin` ;

CREATE TABLE IF NOT EXISTS `user_admin`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
PRIMARY KEY (`user_id`),
UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

CREATE TABLE `user_admin`.`project` (
`project_id` INT NOT NULL AUTO_INCREMENT,
`project_name` VARCHAR(45) NULL,
`week_duration` DATE NOT NULL,
user_id int,
PRIMARY KEY (project_id),
FOREIGN KEY (user_id) REFERENCES user(user_id));


