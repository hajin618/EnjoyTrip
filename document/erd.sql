-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrip_dhj
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `enjoytrip_dhj` ;

-- -----------------------------------------------------
-- Schema enjoytrip_dhj
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip_dhj` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `enjoytrip_dhj` ;

-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip_dhj`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`attraction_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `enjoytrip_dhj`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `enjoytrip_dhj`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip_dhj`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`attraction_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`attraction_description` (
  `content_id` INT NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip_dhj`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`attraction_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`attraction_detail` (
  `content_id` INT NOT NULL,
  `cat1` VARCHAR(3) NULL DEFAULT NULL,
  `cat2` VARCHAR(5) NULL DEFAULT NULL,
  `cat3` VARCHAR(9) NULL DEFAULT NULL,
  `created_time` VARCHAR(14) NULL DEFAULT NULL,
  `modified_time` VARCHAR(14) NULL DEFAULT NULL,
  `booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip_dhj`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`user` (
  `user_idx` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(10) NOT NULL,
  `user_id` VARCHAR(20) NOT NULL,
  `user_pwd` VARCHAR(400) NOT NULL,
  `user_email` VARCHAR(50) NOT NULL,
  `is_admin` CHAR(1) NULL DEFAULT 'N',
  `token` VARCHAR(1000) NULL,
  PRIMARY KEY (`user_idx`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`notice` (
  `notice_idx` INT NOT NULL AUTO_INCREMENT,
  `user_idx` INT NULL DEFAULT NULL,
  `notice_title` VARCHAR(100) NULL DEFAULT NULL,
  `notice_content` VARCHAR(1000) NULL DEFAULT NULL,
  `notice_create` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `notice_update` DATETIME NULL DEFAULT NULL,
  `notice_hit` INT NULL DEFAULT NULL,
  PRIMARY KEY (`notice_idx`),
  INDEX `FK_user_TO_notice` (`user_idx` ASC) VISIBLE,
  CONSTRAINT `FK_user_TO_notice`
    FOREIGN KEY (`user_idx`)
    REFERENCES `enjoytrip_dhj`.`user` (`user_idx`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`plan` (
  `plan_idx` INT NOT NULL AUTO_INCREMENT,
  `user_idx` INT NULL DEFAULT NULL,
  `plan_title` VARCHAR(50) NULL DEFAULT NULL,
  `plan_create` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `plan_update` DATETIME NULL DEFAULT NULL,
  `plan_like` INT NULL DEFAULT NULL,
  `plan_hit` INT NULL DEFAULT NULL,
  `plan_content` VARCHAR(1000) NULL,
  `plan_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`plan_idx`),
  INDEX `FK_user_TO_plan` (`user_idx` ASC) VISIBLE,
  CONSTRAINT `FK_user_TO_plan`
    FOREIGN KEY (`user_idx`)
    REFERENCES `enjoytrip_dhj`.`user` (`user_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`plan_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`plan_comment` (
  `plan_comment_idx` INT NOT NULL AUTO_INCREMENT,
  `user_idx` INT NULL DEFAULT NULL,
  `plan_idx` INT NULL DEFAULT NULL,
  `plan_comment_create` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `plan_comment_update` DATETIME NULL DEFAULT NULL,
  `plan_comment_content` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`plan_comment_idx`),
  INDEX `FK_user_TO_plan_comment` (`user_idx` ASC) VISIBLE,
  INDEX `FK_plan_TO_plan_comment` (`plan_idx` ASC) VISIBLE,
  CONSTRAINT `FK_plan_TO_plan_comment`
    FOREIGN KEY (`plan_idx`)
    REFERENCES `enjoytrip_dhj`.`plan` (`plan_idx`),
  CONSTRAINT `FK_user_TO_plan_comment`
    FOREIGN KEY (`user_idx`)
    REFERENCES `enjoytrip_dhj`.`user` (`user_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`plan_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`plan_detail` (
  `plan_idx` INT NOT NULL,
  `content_id` INT NOT NULL,
  `order` INT NOT NULL,
  PRIMARY KEY (`plan_idx`, `content_id`),
  INDEX `FK_attraction_info_TO_plan_detail` (`content_id` ASC) VISIBLE,
  CONSTRAINT `FK_attraction_info_TO_plan_detail`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip_dhj`.`attraction_info` (`content_id`),
  CONSTRAINT `FK_plan_TO_plan_detail`
    FOREIGN KEY (`plan_idx`)
    REFERENCES `enjoytrip_dhj`.`plan` (`plan_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`plan_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`plan_like` (
  `plan_idx` INT NOT NULL,
  `user_idx` INT NOT NULL,
  PRIMARY KEY (`plan_idx`, `user_idx`),
  INDEX `FK_user_TO_plan_like` (`user_idx` ASC) VISIBLE,
  CONSTRAINT `FK_plan_TO_plan_like`
    FOREIGN KEY (`plan_idx`)
    REFERENCES `enjoytrip_dhj`.`plan` (`plan_idx`),
  CONSTRAINT `FK_user_TO_plan_like`
    FOREIGN KEY (`user_idx`)
    REFERENCES `enjoytrip_dhj`.`user` (`user_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`review` (
  `review_idx` INT NOT NULL AUTO_INCREMENT,
  `user_idx` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `review_title` VARCHAR(100) NULL DEFAULT NULL,
  `review_content` VARCHAR(1000) NULL DEFAULT NULL,
  `review_create` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `review_update` DATETIME NULL DEFAULT NULL,
  `review_hit` INT NULL DEFAULT NULL,
  `review_like` INT NULL DEFAULT NULL,
  `review_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`review_idx`),
  INDEX `FK_user_TO_review` (`user_idx` ASC) VISIBLE,
  INDEX `FK_sido_TO_review` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `FK_sido_TO_review`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip_dhj`.`sido` (`sido_code`),
  CONSTRAINT `FK_user_TO_review`
    FOREIGN KEY (`user_idx`)
    REFERENCES `enjoytrip_dhj`.`user` (`user_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`review_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`review_comment` (
  `comment_idx` INT NOT NULL AUTO_INCREMENT,
  `user_idx` INT NOT NULL,
  `review_idx` INT NULL DEFAULT NULL,
  `review_comment_create` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `review_comment_update` DATETIME NULL DEFAULT NULL,
  `review_comment_content` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_idx`),
  INDEX `FK_user_TO_review_comment` (`user_idx` ASC) VISIBLE,
  INDEX `FK_review_TO_review_comment` (`review_idx` ASC) VISIBLE,
  CONSTRAINT `FK_review_TO_review_comment`
    FOREIGN KEY (`review_idx`)
    REFERENCES `enjoytrip_dhj`.`review` (`review_idx`),
  CONSTRAINT `FK_user_TO_review_comment`
    FOREIGN KEY (`user_idx`)
    REFERENCES `enjoytrip_dhj`.`user` (`user_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`review_image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`review_image` (
  `review_idx` INT,
  `image_num` INT NOT NULL AUTO_INCREMENT,
  `image_url` VARCHAR(10000) NULL DEFAULT NULL,
  `image_name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`image_num`),
  CONSTRAINT `FK_review_TO_review_image`
    FOREIGN KEY (`review_idx`)
    REFERENCES `enjoytrip_dhj`.`review` (`review_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`review_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`review_like` (
  `review_idx` INT NOT NULL,
  `user_idx` INT NOT NULL,
  PRIMARY KEY (`review_idx`, `user_idx`),
  INDEX `FK_user_TO_review_like` (`user_idx` ASC) VISIBLE,
  CONSTRAINT `FK_review_TO_review_like`
    FOREIGN KEY (`review_idx`)
    REFERENCES `enjoytrip_dhj`.`review` (`review_idx`),
  CONSTRAINT `FK_user_TO_review_like`
    FOREIGN KEY (`user_idx`)
    REFERENCES `enjoytrip_dhj`.`user` (`user_idx`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`missing_child`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`missing_child` (
  `child_idx` INT NOT NULL AUTO_INCREMENT,
  `file2` VARCHAR(200) NULL,
  `nm` VARCHAR(45) NULL,
  `sexdstnDscd` VARCHAR(20) NULL,
  `etcSpfeatr` VARCHAR(100) NULL,
  `age1` VARCHAR(20) NULL,
  `detailDate1` VARCHAR(100) NULL,
  `occrAdres` VARCHAR(200) NULL,
  PRIMARY KEY (`child_idx`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip_dhj`.`child_attraction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip_dhj`.`child_attraction` (
  `attraction_idx` INT NOT NULL AUTO_INCREMENT,
  `attraction_name` VARCHAR(100) NOT NULL,
  `category1` VARCHAR(45) NULL,
  `category2` VARCHAR(45) NULL,
  `category3` VARCHAR(45) NULL,
  `latitude` VARCHAR(100) NOT NULL,
  `longitude` VARCHAR(100) NOT NULL,
  `road_address` VARCHAR(100) NULL,
  `address` VARCHAR(100) NULL,
  `phone_number` VARCHAR(45) NULL,
  `homepage` VARCHAR(500) NULL,
  `closed_day` VARCHAR(300) NULL,
  `operating_time` VARCHAR(300) NULL,
  `nursing_room` VARCHAR(45) NULL,
  `stroller` VARCHAR(45) NULL,
  `kid_zone` VARCHAR(45) NULL,
  PRIMARY KEY (`attraction_idx`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
