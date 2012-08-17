SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';


-- -----------------------------------------------------
-- Table `reference`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `reference` (
  `ID_REFERENCE` INT NOT NULL ,
  `CD_REFERENCE` VARCHAR(45) NOT NULL ,
  `CD_CRITERION_LEVEL_NAME` VARCHAR(45) NOT NULL ,
  `CD_TEST_LEVEL_NAME` VARCHAR(45) NOT NULL ,
  `LABEL` VARCHAR(255) NULL ,
  `DESCRIPTION` VARCHAR(2048) NULL ,
  `URL` VARCHAR(2048) NULL ,
  `PRIORITY` INT NOT NULL ,
  PRIMARY KEY (`ID_REFERENCE`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `level`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `level` (
  `ID_LEVEL` INT NOT NULL ,
  `CD_LEVEL` VARCHAR(255) NOT NULL ,
  `LABEL` VARCHAR(255) NULL ,
  `DESCRIPTION` VARCHAR(2048) NULL ,
  `PRIORITY` INT NOT NULL ,
  PRIMARY KEY (`ID_LEVEL`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `theme`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `theme` (
  `ID_THEME` INT NOT NULL ,
  `CD_THEME` VARCHAR(255) NOT NULL ,
  `LABEL` VARCHAR(255) NULL ,
  `DESCRIPTION` VARCHAR(2048) NULL ,
  `PRIORITY` INT NOT NULL ,
  PRIMARY KEY (`ID_THEME`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `criterion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `criterion` (
  `ID_CRITERION` INT NOT NULL ,
  `CD_CRITERION` VARCHAR(255) NOT NULL ,
  `LABEL` VARCHAR(255) NULL ,
  `URL` VARCHAR(2048) NULL ,
  `PRIORITY` INT NOT NULL ,
  `ID_LEVEL` INT NOT NULL ,
  `ID_REFERENCE` INT NOT NULL ,
  `ID_THEME` INT NOT NULL ,
  PRIMARY KEY (`ID_CRITERION`) ,
  INDEX `fk_criterion_level1` (`ID_LEVEL` ASC) ,
  INDEX `fk_criterion_reference1` (`ID_REFERENCE` ASC) ,
  INDEX `fk_criterion_theme1` (`ID_THEME` ASC) ,
  CONSTRAINT `fk_criterion_level1`
    FOREIGN KEY (`ID_LEVEL` )
    REFERENCES `level` (`ID_LEVEL` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_criterion_reference1`
    FOREIGN KEY (`ID_REFERENCE` )
    REFERENCES `reference` (`ID_REFERENCE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_criterion_theme1`
    FOREIGN KEY (`ID_THEME` )
    REFERENCES `theme` (`ID_THEME` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Entity level 2 of the Buisness model. Should be rename since its contain the AW2.1 Criterion, the RGAA Tests and the WCAG Success Criteria.';


-- -----------------------------------------------------
-- Table `test`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test` (
  `ID_TEST` INT NOT NULL ,
  `CD_TEST` VARCHAR(255) NOT NULL ,
  `LABEL` VARCHAR(255) NULL ,
  `DESCRIPTION` VARCHAR(2048) NULL ,
  `URL` VARCHAR(2048) NULL ,
  `PRIORITY` INT NOT NULL ,
  `ID_CRITERION` INT NOT NULL ,
  PRIMARY KEY (`ID_TEST`) ,
  INDEX `fk_test_criterion1` (`ID_CRITERION` ASC) ,
  CONSTRAINT `fk_test_criterion1`
    FOREIGN KEY (`ID_CRITERION` )
    REFERENCES `criterion` (`ID_CRITERION` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Entity of level 3 of the Buisness model. Should be rename.';


-- -----------------------------------------------------
-- Table `resultat`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `resultat` (
  `ID_RESULTAT` INT NOT NULL ,
  `CD_RESULT` VARCHAR(255) NOT NULL ,
  `LABEL` VARCHAR(45) NULL ,
  `DESCRIPTION` VARCHAR(2048) NULL ,
  `PRIORITY` INT(3) NOT NULL ,
  PRIMARY KEY (`ID_RESULTAT`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `webarchive`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `webarchive` (
  `ID_WEBARCHIVE` INT NOT NULL ,
  `URL` VARCHAR(4096) NOT NULL ,
  `LOCAL_URL` VARCHAR(4096) NOT NULL ,
  `SCOPE` VARCHAR(45) NOT NULL ,
  `DESCRIPTION` VARCHAR(2048) NULL ,
  `PRIORITY` INT NOT NULL ,
  `CREATION_DATE` DATETIME NOT NULL ,
  PRIMARY KEY (`ID_WEBARCHIVE`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `access_level`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `access_level` (
  `ID_ACCESS_LEVEL` INT NOT NULL ,
  `CD_ACCESS_LEVEL` VARCHAR(255) NOT NULL ,
  `LABEL` VARCHAR(255) NULL ,
  `DESCRIPTION` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID_ACCESS_LEVEL`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `account`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `account` (
  `ID_ACCOUNT` INT NOT NULL ,
  `EMAIL` VARCHAR(255) NOT NULL ,
  `PASSWORD` VARCHAR(255) NOT NULL ,
  `LAST_NAME` VARCHAR(255) NULL ,
  `FIRST_NAME` VARCHAR(255) NULL ,
  `URL` VARCHAR(255) NULL ,
  `ACTIVATED` BIT(1) NOT NULL ,
  `ACTIVATION_TOKEN` VARCHAR(255) NULL ,
  `ID_ACCESS_LEVEL` INT NOT NULL ,
  PRIMARY KEY (`ID_ACCOUNT`) ,
  INDEX `fk_account_access_level1` (`ID_ACCESS_LEVEL` ASC) ,
  CONSTRAINT `fk_account_access_level1`
    FOREIGN KEY (`ID_ACCESS_LEVEL` )
    REFERENCES `access_level` (`ID_ACCESS_LEVEL` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testcase`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `testcase` (
  `ID_TESTCASE` INT NOT NULL ,
  `TITLE` VARCHAR(255) NOT NULL ,
  `DESCRIPTION` VARCHAR(2048) NULL ,
  `PRIORITY` INT NOT NULL ,
  `CREATION_DATE` DATETIME NOT NULL ,
  `ID_RESULTAT` INT NOT NULL ,
  `ID_WEBARCHIVE` INT NOT NULL ,
  `ID_ACCOUNT` INT NOT NULL ,
  `ID_CRITERION` INT NOT NULL ,
  PRIMARY KEY (`ID_TESTCASE`) ,
  INDEX `fk_testcase_resultat1` (`ID_RESULTAT` ASC) ,
  INDEX `fk_testcase_webarchive1` (`ID_WEBARCHIVE` ASC) ,
  INDEX `fk_testcase_account1` (`ID_ACCOUNT` ASC) ,
  INDEX `fk_testcase_criterion1` (`ID_CRITERION` ASC) ,
  CONSTRAINT `fk_testcase_resultat1`
    FOREIGN KEY (`ID_RESULTAT` )
    REFERENCES `resultat` (`ID_RESULTAT` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_testcase_webarchive1`
    FOREIGN KEY (`ID_WEBARCHIVE` )
    REFERENCES `webarchive` (`ID_WEBARCHIVE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_testcase_account1`
    FOREIGN KEY (`ID_ACCOUNT` )
    REFERENCES `account` (`ID_ACCOUNT` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_testcase_criterion1`
    FOREIGN KEY (`ID_CRITERION` )
    REFERENCES `criterion` (`ID_CRITERION` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `test_result`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test_result` (
  `ID_TEST_RESULT` INT NOT NULL ,
  `ID_TEST` INT NOT NULL ,
  `ID_RESULTAT` INT NOT NULL ,
  PRIMARY KEY (`ID_TEST_RESULT`) ,
  INDEX `fk_micro_testcase_test1` (`ID_TEST` ASC) ,
  INDEX `fk_micro_testcase_resultat1` (`ID_RESULTAT` ASC) ,
  CONSTRAINT `fk_micro_testcase_test1`
    FOREIGN KEY (`ID_TEST` )
    REFERENCES `test` (`ID_TEST` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_micro_testcase_resultat1`
    FOREIGN KEY (`ID_RESULTAT` )
    REFERENCES `resultat` (`ID_RESULTAT` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Simple association of a test and its result.\n\nIt is associated with the testcase though a n:m relation because a testcase about a criterion may also contain the tests\' results of this criterion.';


-- -----------------------------------------------------
-- Table `nomenclature`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `nomenclature` (
  `ID_NOMENCLATURE` INT NOT NULL ,
  `CD_NOMENCLATURE` VARCHAR(255) NOT NULL ,
  `DESCRIPTION` VARCHAR(2048) NULL ,
  `LABEL` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID_NOMENCLATURE`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nomenclature_element`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `nomenclature_element` (
  `ID_NOMENCLATURE_ELEMENT` INT NOT NULL ,
  `CD_NOMENCLATURE_ELEMENT` VARCHAR(255) NOT NULL ,
  `LABEL` VARCHAR(255) NULL ,
  `ID_NOMENCLATURE` INT NOT NULL ,
  PRIMARY KEY (`ID_NOMENCLATURE_ELEMENT`) ,
  INDEX `fk_nomenclature_element_nomenclature1` (`ID_NOMENCLATURE` ASC) ,
  CONSTRAINT `fk_nomenclature_element_nomenclature1`
    FOREIGN KEY (`ID_NOMENCLATURE` )
    REFERENCES `nomenclature` (`ID_NOMENCLATURE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `testcase_nomenclature_element`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `testcase_nomenclature_element` (
  `ID_TESTCASE` INT NOT NULL ,
  `ID_NOMENCLATURE_ELEMENT` INT NOT NULL ,
  PRIMARY KEY (`ID_TESTCASE`, `ID_NOMENCLATURE_ELEMENT`) ,
  INDEX `fk_testcase_has_nomenclature_element_nomenclature_element1` (`ID_NOMENCLATURE_ELEMENT` ASC) ,
  CONSTRAINT `fk_testcase_has_nomenclature_element_testcase1`
    FOREIGN KEY (`ID_TESTCASE` )
    REFERENCES `testcase` (`ID_TESTCASE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_testcase_has_nomenclature_element_nomenclature_element1`
    FOREIGN KEY (`ID_NOMENCLATURE_ELEMENT` )
    REFERENCES `nomenclature_element` (`ID_NOMENCLATURE_ELEMENT` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `criterion_criterion`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `criterion_criterion` (
  `ID_CRITERION_CRITERION` INT NOT NULL ,
  `ID_CRITERION_FROM` INT NOT NULL ,
  `ID_CRITERION_TO` INT NOT NULL ,
  PRIMARY KEY (`ID_CRITERION_CRITERION`) ,
  INDEX `fk_criterion_has_criterion_criterion2` (`ID_CRITERION_TO` ASC) ,
  UNIQUE INDEX `fk_unique_pair` (`ID_CRITERION_FROM` ASC, `ID_CRITERION_TO` ASC) ,
  CONSTRAINT `fk_criterion_has_criterion_criterion1`
    FOREIGN KEY (`ID_CRITERION_FROM` )
    REFERENCES `criterion` (`ID_CRITERION` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_criterion_has_criterion_criterion2`
    FOREIGN KEY (`ID_CRITERION_TO` )
    REFERENCES `criterion` (`ID_CRITERION` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Its purpose is to contain the association between the level 2 entities of the buisness model across several frames of reference.';


-- -----------------------------------------------------
-- Table `testcase_test_result`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `testcase_test_result` (
  `ID_TESTCASE` INT NOT NULL ,
  `ID_TEST_RESULT` INT NOT NULL ,
  PRIMARY KEY (`ID_TESTCASE`, `ID_TEST_RESULT`) ,
  INDEX `fk_testcase_has_test_result_test_result1` (`ID_TEST_RESULT` ASC) ,
  CONSTRAINT `fk_testcase_has_test_result_testcase1`
    FOREIGN KEY (`ID_TESTCASE` )
    REFERENCES `testcase` (`ID_TESTCASE` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_testcase_has_test_result_test_result1`
    FOREIGN KEY (`ID_TEST_RESULT` )
    REFERENCES `test_result` (`ID_TEST_RESULT` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
