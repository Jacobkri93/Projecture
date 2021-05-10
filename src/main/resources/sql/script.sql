CREATE SCHEMA IF NOT EXISTS `user_admin` ;

CREATE TABLE IF NOT EXISTS `user_admin`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(90) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
PRIMARY KEY (`id`),
UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);

CREATE TABLE `user_admin`.`project` (
`id` INT NOT NULL AUTO_INCREMENT,
`project_name` VARCHAR(45) NULL,
`week_duration` DATE NOT NULL,
ADD CONSTRAINT `user_id`
#     FEJL
    FOREIGN KEY (`user_id`)
    REFERENCES `user_admin`.`user` (`id`),
PRIMARY KEY (`id`));
