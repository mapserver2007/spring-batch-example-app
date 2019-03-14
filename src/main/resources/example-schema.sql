CREATE SCHEMA IF NOT EXISTS `hop` DEFAULT CHARACTER SET utf8 ;
USE `hop` ;

DROP TABLE IF EXISTS `hop`.`t01_example` ;

CREATE TABLE IF NOT EXISTS `hop`.`t01_example` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t01_example
(
    created_at, updated_at, name
)
VALUES
(
    NOW(), NOW(), 'test1'
);

INSERT INTO t01_example
(
    created_at, updated_at, name
)
VALUES
(
    NOW(), NOW(), 'test2'
);