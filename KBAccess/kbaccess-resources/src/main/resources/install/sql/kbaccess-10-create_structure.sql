SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

-- -----------------------------------------------------
-- Table `access_level`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `access_level` (
  `ID_ACCESS_LEVEL` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CD_ACCESS_LEVEL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `DESCRIPTION` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `LABEL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `RANK` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_ACCESS_LEVEL`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `account` (
  `ID_ACCOUNT` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `ACTIVATED` BIT(1) NOT NULL ,
  `ACTIVATION_TOKEN` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `EMAIL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `FIRST_NAME` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `LAST_NAME` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `PASSWORD` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `SUBSCRIPTION_DATE` DATETIME NOT NULL ,
  `URL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `ID_ACCESS_LEVEL` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`ID_ACCOUNT`) ,
  INDEX `FK__account__access_level` (`ID_ACCESS_LEVEL` ASC) ,
  UNIQUE INDEX `EMAIL_UNIQUE` (`EMAIL` ASC) ,
  CONSTRAINT `FK__account__access_level`
    FOREIGN KEY (`ID_ACCESS_LEVEL` )
    REFERENCES `access_level` (`ID_ACCESS_LEVEL` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `reference`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference` (
  `ID_REFERENCE` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CD_REFERENCE` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `DESCRIPTION` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `LABEL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL ,
  `RANK` INT(11) NOT NULL ,
  `URL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `COUNTRY` VARCHAR(255) NOT NULL ,
  `INFO_MAX_DEPTH` INT(2) NOT NULL ,
  `TEST_MAX_DEPTH` INT(2) NOT NULL ,
  PRIMARY KEY (`ID_REFERENCE`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `reference_level`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference_level` (
  `ID_REFERENCE_LEVEL` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CD_REFERENCE_LEVEL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `DESCRIPTION` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `LABEL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `RANK` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_REFERENCE_LEVEL`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `reference_depth`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference_depth` (
  `ID_REFERENCE_DEPTH` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CD_REFERENCE_DEPTH` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `DEPTH` INT(1) NULL DEFAULT NULL ,
  `RANK` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_REFERENCE_DEPTH`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `reference_info`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference_info` (
  `ID_REFERENCE_INFO` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `ID_REFERENCE_INFO_PARENT` BIGINT(20) NULL DEFAULT NULL ,
  `CD_REFERENCE_INFO` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `LABEL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `RANK` INT(11) NULL DEFAULT NULL ,
  `ID_REFERENCE_DEPTH` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`ID_REFERENCE_INFO`) ,
  CONSTRAINT `FK__reference_info__reference_depth`
    FOREIGN KEY (`ID_REFERENCE_DEPTH` )
    REFERENCES `reference_depth` (`ID_REFERENCE_DEPTH` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__reference_info__reference_info`
    FOREIGN KEY (`ID_REFERENCE_INFO_PARENT` )
    REFERENCES `reference_info` (`ID_REFERENCE_INFO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `reference_test`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference_test` (
  `ID_REFERENCE_TEST` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CD_REFERENCE_TEST` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `DESCRIPTION` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `LABEL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `RANK` INT(11) NOT NULL ,
  `URL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `ID_REFERENCE_LEVEL` BIGINT(20) NULL DEFAULT NULL,
  `ID_REFERENCE` BIGINT(20) NULL DEFAULT NULL,
  `ID_REFERENCE_INFO` BIGINT(20) NULL DEFAULT NULL,
  `ID_REFERENCE_DEPTH` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`ID_REFERENCE_TEST`) ,
  INDEX `FK__reference_test__reference` (`ID_REFERENCE` ASC) ,
  INDEX `FK__reference_test__reference_level` (`ID_REFERENCE_LEVEL` ASC) ,
  INDEX `FK__reference_test__reference_info` (`ID_REFERENCE_INFO` ASC) ,
  INDEX `FK__reference_test__reference_depth` (`ID_REFERENCE_DEPTH` ASC) ,
  CONSTRAINT `FK__reference_test__reference`
    FOREIGN KEY (`ID_REFERENCE` )
    REFERENCES `reference` (`ID_REFERENCE` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK__reference_test__reference_level`
    FOREIGN KEY (`ID_REFERENCE_LEVEL` )
    REFERENCES `reference_level` (`ID_REFERENCE_LEVEL` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK__reference_test__reference_info`
    FOREIGN KEY (`ID_REFERENCE_INFO` )
    REFERENCES `reference_info` (`ID_REFERENCE_INFO` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK__reference_test__reference_depth`
    FOREIGN KEY (`ID_REFERENCE_DEPTH` )
    REFERENCES `reference_depth` (`ID_REFERENCE_DEPTH` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `result`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `result` (
  `ID_RESULT` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CD_RESULT` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `DESCRIPTION` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `LABEL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `RANK` INT(11) NOT NULL ,
  PRIMARY KEY (`ID_RESULT`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `webarchive`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `webarchive` (
  `ID_WEBARCHIVE` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CREATION_DATE` DATETIME NOT NULL ,
  `DESCRIPTION` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `LOCAL_URL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `RANK` INT(11) NOT NULL ,
  `SCOPE` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `URL` VARCHAR(255) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL ,
  `ID_ACCOUNT` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`ID_WEBARCHIVE`) ,
  INDEX `FK__webarchive__account` (`ID_ACCOUNT` ASC) ,
  CONSTRAINT `FK__webarchive__account`
    FOREIGN KEY (`ID_ACCOUNT` )
    REFERENCES `account` (`ID_ACCOUNT` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `testcase`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `testcase` (
  `ID_TESTCASE` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `CREATION_DATE` DATETIME NOT NULL ,
  `DESCRIPTION` VARCHAR(5000) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NULL DEFAULT NULL ,
  `RANK` INT(11) NOT NULL ,
  `ID_ACCOUNT` BIGINT(20) NOT NULL ,
  `ID_WEBARCHIVE` BIGINT(20) NOT NULL ,
  `ID_RESULT` BIGINT(20) NOT NULL ,
  `ID_REFERENCE_TEST` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`ID_TESTCASE`) ,
  INDEX `FK__testcase__webarchive` (`ID_WEBARCHIVE` ASC) ,
  INDEX `FK__testcase__account` (`ID_ACCOUNT` ASC) ,
  INDEX `FK__testcase__result` (`ID_RESULT` ASC) ,
  INDEX `FK__testcase__reference__test` (`ID_REFERENCE_TEST` ASC) ,
  CONSTRAINT `FK__testcase__account`
    FOREIGN KEY (`ID_ACCOUNT` )
    REFERENCES `account` (`ID_ACCOUNT` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK__testcase__webarchive`
    FOREIGN KEY (`ID_WEBARCHIVE` )
    REFERENCES `webarchive` (`ID_WEBARCHIVE` )
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK__testcase__result`
    FOREIGN KEY (`ID_RESULT` )
    REFERENCES `result` (`ID_RESULT` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__testcase__reference__test`
    FOREIGN KEY (`ID_REFERENCE_TEST` )
    REFERENCES `reference_test` (`ID_REFERENCE_TEST` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `reference_test_reference_test`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference_test_reference_test` (
  `ID_REFERENCE_TEST_1` BIGINT(20) NOT NULL,
  `ID_REFERENCE_TEST_2` BIGINT(20) NOT NULL,
  PRIMARY KEY (`ID_REFERENCE_TEST_1`, `ID_REFERENCE_TEST_2`) ,
  INDEX `FK__reference_test__reference_test__1` (`ID_REFERENCE_TEST_1` ASC) ,
  INDEX `FK__reference_test__reference_test__2` (`ID_REFERENCE_TEST_2` ASC) ,
  CONSTRAINT `FK__reference_test__reference_test__1`
    FOREIGN KEY (`ID_REFERENCE_TEST_1` )
    REFERENCES `reference_test` (`ID_REFERENCE_TEST` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK__reference_test__reference_test__2`
    FOREIGN KEY (`ID_REFERENCE_TEST_2` )
    REFERENCES `reference_test` (`ID_REFERENCE_TEST` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

