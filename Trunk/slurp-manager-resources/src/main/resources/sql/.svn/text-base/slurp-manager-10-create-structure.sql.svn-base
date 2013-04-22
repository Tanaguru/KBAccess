SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE `slurp` ;

-- -----------------------------------------------------
-- Table `webresource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webresource` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `webresource` (
  `webresource_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `webresource_url` VARCHAR(255) NOT NULL ,
  `webresource_description` VARCHAR(2048) NULL DEFAULT NULL ,
  `webresource_date_c` DATETIME NOT NULL ,
  `webresource_priority` INT(3) NOT NULL ,
  PRIMARY KEY (`webresource_id`) ,
  UNIQUE INDEX `webresource_url_UNIQUE` (`webresource_url` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DELIMITER $$
DROP TRIGGER IF EXISTS slurp.trigger_webresource_insert$$
USE `slurp`$$
-- Trigger DDL Statements
CREATE TRIGGER trigger_webresource_insert BEFORE INSERT ON `webresource`
    FOR EACH ROW SET NEW.webresource_date_c = NOW()$$
DELIMITER ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `webarchive`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webarchive` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `webarchive` (
  `webarchive_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `webarchive_url` VARCHAR(255) NOT NULL ,
  `webarchive_description` VARCHAR(2048) NULL DEFAULT NULL ,
  `webarchive_date_c` DATETIME NOT NULL ,
  `webarchive_id_webresource` BIGINT(20) NOT NULL ,
  `webarchive_scope` VARCHAR(255) NOT NULL ,
  `webarchive_priority` INT(3) NOT NULL ,
  PRIMARY KEY (`webarchive_id`) ,
  INDEX `FK_webarchive_webresource` (`webarchive_id_webresource` ASC) ,
  CONSTRAINT `fk_webarchive_webresource`
    FOREIGN KEY (`webarchive_id_webresource` )
    REFERENCES `webresource` (`webresource_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `languagetag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `languagetag` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `languagetag` (
  `languagetag_id` INT NOT NULL AUTO_INCREMENT ,
  `languagetag_value` VARCHAR(255) NOT NULL ,
  `languagetag_description` VARCHAR(255) NULL ,
  `languagetag_rank` INT ,
  PRIMARY KEY (`languagetag_id`) ,
  UNIQUE INDEX `languagetag_id_UNIQUE` (`languagetag_id` ASC, `languagetag_value` ASC) ,
  UNIQUE INDEX `languagetag_value_UNIQUE` (`languagetag_value` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
PACK_KEYS = DEFAULT;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `webresource_has_languagetag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webresource_has_languagetag` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `webresource_has_languagetag` (
  `webresource_webresource_id` BIGINT(20) NOT NULL ,
  `languagetag_languagetag_id` INT NOT NULL ,
  PRIMARY KEY (`webresource_webresource_id`, `languagetag_languagetag_id`) ,
  INDEX `fk_webresource_has_languagetag_languagetag1` (`languagetag_languagetag_id` ASC) ,
  CONSTRAINT `fk_webresource_has_languagetag_webresource1`
    FOREIGN KEY (`webresource_webresource_id` )
    REFERENCES `webresource` (`webresource_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_webresource_has_languagetag_languagetag1`
    FOREIGN KEY (`languagetag_languagetag_id` )
    REFERENCES `languagetag` (`languagetag_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nomenclature`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nomenclature` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `nomenclature` (
  `nomenclature_id` INT NOT NULL ,
  `nomenclature_cd` VARCHAR(255) NOT NULL ,
  `nomenclature_value` VARCHAR(2048) NOT NULL ,
  `nomenclature_description` VARCHAR(2048) NULL ,
  `nomenclature_rank` INT NULL ,
  `nomenclature_nomenclature_id` INT NULL ,
  PRIMARY KEY (`nomenclature_id`) ,
  UNIQUE INDEX `nomenclature_cd_UNIQUE` (`nomenclature_cd` ASC) ,
  INDEX `fk_nomenclature_nomenclature1` (`nomenclature_nomenclature_id` ASC) ,
  CONSTRAINT `fk_nomenclature_nomenclature1`
    FOREIGN KEY (`nomenclature_nomenclature_id` )
    REFERENCES `nomenclature` (`nomenclature_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `nomenclature_element`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nomenclature_element` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `nomenclature_element` (
  `nomenclature_element_id` INT NOT NULL ,
  `nomenclature_element_value` VARCHAR(2048) NOT NULL ,
  `nomenclature_element_rank` INT NULL ,
  `nomenclature_element_nomenclature_id` INT NOT NULL ,
  PRIMARY KEY (`nomenclature_element_id`) ,
  INDEX `fk_nomenclature_element_nomenclature1` (`nomenclature_element_nomenclature_id` ASC) ,
  CONSTRAINT `fk_nomenclature_element_nomenclature1`
    FOREIGN KEY (`nomenclature_element_nomenclature_id` )
    REFERENCES `nomenclature` (`nomenclature_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `webresource_has_nomenclature_element`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `webresource_has_nomenclature_element` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `webresource_has_nomenclature_element` (
  `webresource_webresource_id` BIGINT(20) NOT NULL ,
  `nomenclature_element_nomenclature_element_id` INT NOT NULL ,
  PRIMARY KEY (`webresource_webresource_id`, `nomenclature_element_nomenclature_element_id`) ,
  INDEX `fk_webresource_has_nomenclature_element_nomenclature_element1` (`nomenclature_element_nomenclature_element_id` ASC) ,
  CONSTRAINT `fk_webresource_has_nomenclature_element_webresource1`
    FOREIGN KEY (`webresource_webresource_id` )
    REFERENCES `webresource` (`webresource_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_webresource_has_nomenclature_element_nomenclature_element1`
    FOREIGN KEY (`nomenclature_element_nomenclature_element_id` )
    REFERENCES `nomenclature_element` (`nomenclature_element_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
