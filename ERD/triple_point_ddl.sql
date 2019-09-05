-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb`;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8;
SHOW WARNINGS;
USE `mydb`;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
    `user_id` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`user_id`)
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mydb`.`place`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`place`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mydb`.`place` (
    `place_id` VARCHAR(255) NOT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`place_id`)
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mydb`.`review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`review`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mydb`.`review` (
    `review_id` VARCHAR(255) NOT NULL COMMENT 'event.review_id : uuid, abuse 가능성 있음.',
    `user_user_id` VARCHAR(255) NOT NULL,
    `place_place_id` VARCHAR(255) NOT NULL,
    `content` VARCHAR(255) NULL,
    `attach_photo_ids` VARCHAR(255) NULL,
    `status_type` VARCHAR(25) NOT NULL COMMENT 'NORMAL\nDELETE',
    `created_at` TIMESTAMP NOT NULL DEFAULT now(),
    `modified_at` TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`review_id`),
    CONSTRAINT `fk_review_user1`
        FOREIGN KEY (`user_user_id`)
            REFERENCES `mydb`.`user`(`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_review_place1`
        FOREIGN KEY (`place_place_id`)
            REFERENCES `mydb`.`place`(`place_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_review_user1_idx` ON `mydb`.`review`(`user_user_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_review_place1_idx` ON `mydb`.`review`(`place_place_id` ASC);

SHOW WARNINGS;
CREATE UNIQUE INDEX `UIDX_USER_PLACE` ON `mydb`.`review`(`user_user_id` ASC, `place_place_id` ASC);

SHOW WARNINGS;
CREATE INDEX `IDX_PLACE_STATUS` ON `mydb`.`review`(`place_place_id` ASC, `status_type` ASC) COMMENT 'for checking reviewed place or not';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mydb`.`review_point_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`review_point_detail`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mydb`.`review_point_detail` (
    `review_review_id` VARCHAR(255) NOT NULL,
    `content_point` INT NOT NULL,
    `attach_photo_point` INT NOT NULL,
    `first_review_point` INT NOT NULL,
    `total_point` BIGINT NOT NULL,
    PRIMARY KEY (`review_review_id`),
    CONSTRAINT `fk_review_point_detail_review1`
        FOREIGN KEY (`review_review_id`)
            REFERENCES `mydb`.`review`(`review_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mydb`.`review_point_detail_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`review_point_detail_history`;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mydb`.`review_point_detail_history` (
    `review_point_detail_history_id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `review_point_detail_review_review_id` VARCHAR(255) NOT NULL,
    `user_user_id` VARCHAR(255) NOT NULL,
    `content_point` INT NULL,
    `attach_photo_point` INT NULL,
    `first_review_point` INT NULL,
    `total_point` BIGINT NULL,
    `created_at` TIMESTAMP NOT NULL DEFAULT now(),
    PRIMARY KEY (`review_point_detail_history_id`),
    CONSTRAINT `fk_review_point_detail_history_review_point_detail1`
        FOREIGN KEY (`review_point_detail_review_review_id`)
            REFERENCES `mydb`.`review_point_detail`(`review_review_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_review_point_detail_history_user1`
        FOREIGN KEY (`user_user_id`)
            REFERENCES `mydb`.`user`(`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `fk_review_point_detail_history_review_point_detail1_idx` ON `mydb`.`review_point_detail_history`(`review_point_detail_review_review_id` ASC);

SHOW WARNINGS;
CREATE INDEX `fk_review_point_detail_history_user1_idx` ON `mydb`.`review_point_detail_history`(`user_user_id` ASC);

SHOW WARNINGS;

SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
