
/*
** NOTE: This script does not modify the testcase table
**	 except for the constraints and engine part.
*/


-- ============================================================================
-- Rename tables
-- ============================================================================

RENAME TABLE `have` TO `testcase_nomenclature_element`;

-- ============================================================================
-- Drop constraints
-- ============================================================================
ALTER TABLE `criterion`
	DROP FOREIGN KEY `FK_CRITERION_REFERENCE` ,
	DROP FOREIGN KEY `FK_CRITERION_THEME`
	;

ALTER TABLE `test`
	DROP FOREIGN KEY `FK_TEST_LEVEL` ,
	DROP FOREIGN KEY `FK_TEST_CRITERION`
	;

ALTER TABLE `webarchive`
	DROP FOREIGN KEY `fk_webarchive_account`
	;

ALTER TABLE `account`
	DROP FOREIGN KEY `FK_ACCOUNT_ACCESS_LEVEL`
	;

ALTER TABLE `nomenclature_element`
	DROP FOREIGN KEY `FK_NOMENCLATURE_ELEMENT_NOMENCLATURE`
	;	

ALTER TABLE `testcase_nomenclature_element`
	DROP FOREIGN KEY `FK_HAVE_TESTCASE`;

ALTER TABLE `testcase`
	DROP FOREIGN KEY `FK_TESTCASE_RESULT` ,
        DROP FOREIGN KEY `FK_TESTCASE_TEST` ,
        DROP FOREIGN KEY `FK_TESTCASE_ACCOUNT` ,
        DROP FOREIGN KEY `FK_TESTCASE_WEBARCHIVE`
	;

-- ============================================================================
-- Change engine and id
-- ============================================================================

ALTER TABLE `reference`
	MODIFY COLUMN `ID_REFERENCE` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `level`
	MODIFY COLUMN `ID_LEVEL` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `theme`
	MODIFY COLUMN `ID_THEME` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `criterion`
	MODIFY COLUMN `ID_CRITERION` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `test`
	MODIFY COLUMN `ID_TEST` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `result`
	MODIFY COLUMN `ID_RESULT` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `webarchive`
	MODIFY COLUMN `ID_WEBARCHIVE` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `account`
	MODIFY COLUMN `ID_ACCOUNT` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `nomenclature`
	MODIFY COLUMN `ID_NOMENCLATURE` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `nomenclature_element`
	MODIFY COLUMN `ID_NOMENCLATURE_ELEMENT` BIGINT NOT NULL AUTO_INCREMENT ,
	ENGINE = InnoDB
	;

ALTER TABLE `testcase_nomenclature_element`
	MODIFY COLUMN `ID_TESTCASE`
		BIGINT NOT NULL ,
	MODIFY COLUMN `ID_NOMENCLATURE_ELEMENT`
		BIGINT NOT NULL ,
	ENGINE = InnoDB
	;

ALTER TABLE `testcase`
	MODIFY COLUMN `ID_TESTCASE` BIGINT NOT NULL AUTO_INCREMENT,
	ENGINE = InnoDB
	;

-- ============================================================================
-- Create new tables
-- ============================================================================


-- ----------------------------------------------------------------------------
-- Table access_level
-- ----------------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `access_level` (
	`ID_ACCESS_LEVEL` BIGINT NOT NULL AUTO_INCREMENT ,
	`CD_ACCESS_LEVEL` VARCHAR(255) NOT NULL ,
	`LABEL` VARCHAR(255) NULL ,
	`DESCRIPTION` VARCHAR(255) NULL ,
	`PRIORITY` INT NOT NULL ,
	PRIMARY KEY (`ID_ACCESS_LEVEL`) ,
	UNIQUE INDEX `unique_access_level_code` (`CD_ACCESS_LEVEL` ASC)
	) ENGINE = InnoDB;

-- ----------------------------------------------------------------------------
-- Table criterion_criterion
-- ----------------------------------------------------------------------------

CREATE  TABLE IF NOT EXISTS `criterion_criterion` (
  `ID_CRITERION_CRITERION` BIGINT NOT NULL AUTO_INCREMENT,
  `ID_CRITERION_FROM` BIGINT NOT NULL ,
  `ID_CRITERION_TO` BIGINT NOT NULL ,
  PRIMARY KEY (`ID_CRITERION_CRITERION`) ,
  INDEX `fk_criterion_has_criterion_criterion1` (`ID_CRITERION_FROM` ASC) ,
  INDEX `fk_criterion_has_criterion_criterion2` (`ID_CRITERION_TO` ASC) ,
  UNIQUE INDEX `unique_criterion_criterion_pair`
    (`ID_CRITERION_FROM` ASC, `ID_CRITERION_TO` ASC) ,
  CONSTRAINT `fk_criterion_has_criterion_criterion1`
    FOREIGN KEY (`ID_CRITERION_FROM` )
    REFERENCES `criterion` (`ID_CRITERION` )
    ON UPDATE NO ACTION
    ON DELETE CASCADE,
  CONSTRAINT `fk_criterion_has_criterion_criterion2`
    FOREIGN KEY (`ID_CRITERION_TO` )
    REFERENCES `criterion` (`ID_CRITERION` )
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- ----------------------------------------------------------------------------
-- Table test_result
-- ----------------------------------------------------------------------------
CREATE  TABLE IF NOT EXISTS `test_result` (
  `ID_TEST_RESULT` BIGINT NOT NULL AUTO_INCREMENT,
  `ID_TEST` BIGINT NOT NULL ,
  `ID_RESULT` BIGINT NOT NULL ,
  PRIMARY KEY (`ID_TEST_RESULT`) ,
  INDEX `fk_test_result_test1` (`ID_TEST` ASC) ,
  INDEX `fk_test_result_result1` (`ID_RESULT` ASC) ,
  UNIQUE INDEX `unique_test_result` (`ID_TEST`, `ID_RESULT`) ,
  CONSTRAINT `fk_test_result_test1`
    FOREIGN KEY (`ID_TEST` )
    REFERENCES `test` (`ID_TEST` )
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_test_result_result1`
    FOREIGN KEY (`ID_RESULT` )
    REFERENCES `result` (`ID_RESULT` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- ----------------------------------------------------------------------------
-- Table testcase_test_result
-- ----------------------------------------------------------------------------

CREATE  TABLE IF NOT EXISTS `testcase_test_result` (
  `ID_TESTCASE` BIGINT NOT NULL ,
  `ID_TEST_RESULT` BIGINT NOT NULL ,
  PRIMARY KEY (`ID_TESTCASE`, `ID_TEST_RESULT`) ,
  INDEX `fk_testcase_test_result_testcase1` (`ID_TESTCASE` ASC) ,
  INDEX `fk_testcase_test_result_test_result1` (`ID_TEST_RESULT` ASC) ,
  CONSTRAINT `fk_testcase_test_result_testcase1`
    FOREIGN KEY (`ID_TESTCASE` )
    REFERENCES `testcase` (`ID_TESTCASE` )
    ON DELETE CASCADE
    ON UPDATE CASCADE ,
  CONSTRAINT `fk_testcase_test_result_test_result1`
    FOREIGN KEY (`ID_TEST_RESULT` )
    REFERENCES `test_result` (`ID_TEST_RESULT` )
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- ============================================================================
-- Apply other changes and create the constraints
-- ============================================================================

-- ----------------------------------------------------------------------------
-- Table reference
-- ----------------------------------------------------------------------------
ALTER TABLE `reference`
	ADD COLUMN `CD_CRITERION_LEVEL_NAME` VARCHAR(45) NOT NULL ,
	ADD COLUMN `CD_TEST_LEVEL_NAME` VARCHAR(45) NOT NULL ,
	ADD UNIQUE INDEX `unique_reference_code` (`CD_REFERENCE`)
	;

-- ----------------------------------------------------------------------------
-- Table level
-- ----------------------------------------------------------------------------

-- No other changes

-- ----------------------------------------------------------------------------
-- Table theme
-- ----------------------------------------------------------------------------

-- No other changes

-- ----------------------------------------------------------------------------
-- Table criterion
-- ----------------------------------------------------------------------------
ALTER TABLE `criterion`
	MODIFY COLUMN `ID_REFERENCE` BIGINT NOT NULL ,
	MODIFY COLUMN `ID_THEME` BIGINT NOT NULL ,
	-- null values are allowed until the end of the upgrade
	ADD COLUMN `ID_LEVEL` BIGINT NULL,
	ADD UNIQUE INDEX `unique_criterion_code` (`CD_CRITERION`) ,
	ADD CONSTRAINT `fk_criterion_reference1`
		FOREIGN KEY `fk_criterion_reference1` (`ID_REFERENCE`)
		REFERENCES `reference` (`ID_REFERENCE`)
		ON UPDATE CASCADE
		ON DELETE CASCADE ,
	ADD CONSTRAINT `fk_criterion_theme1`
		FOREIGN KEY `fk_criterion_theme1` (`ID_THEME`)
		REFERENCES `theme` (`ID_THEME`)
		ON UPDATE CASCADE
		ON DELETE CASCADE ,
	ADD CONSTRAINT `fk_criterion_level1`
		FOREIGN KEY `fk_criterion_level1` (`ID_LEVEL`)
		REFERENCES `level` (`ID_LEVEL`)
		ON UPDATE CASCADE
		ON DELETE CASCADE
	;

-- ----------------------------------------------------------------------------
-- Table test
-- ----------------------------------------------------------------------------
ALTER TABLE `test`
        -- The column level will be drop once the criterion table content
        -- has been updated (see file 50-*).
	MODIFY COLUMN `ID_CRITERION` BIGINT NOT NULL ,
	MODIFY COLUMN `ID_LEVEL` BIGINT NOT NULL ,
	ADD UNIQUE INDEX `unique_test_code` (`CD_TEST`) ,
	ADD CONSTRAINT `fk_test_criterion1`
		FOREIGN KEY `fk_test_criterion1` (`ID_CRITERION`)
		REFERENCES `criterion` (`ID_CRITERION`)
		ON UPDATE CASCADE
		ON DELETE CASCADE ,
	ADD CONSTRAINT `fk_test_level1`
		FOREIGN KEY `fk_test_level1` (`ID_LEVEL`)
		REFERENCES `level` (`ID_LEVEL`)
		ON UPDATE RESTRICT
		ON DELETE RESTRICT
	;

-- ----------------------------------------------------------------------------
-- Table result
-- ----------------------------------------------------------------------------

-- No other changes

-- ----------------------------------------------------------------------------
-- Table webarchive
-- ----------------------------------------------------------------------------

ALTER TABLE `webarchive`
	MODIFY COLUMN `ID_ACCOUNT` BIGINT NOT NULL,
	MODIFY COLUMN `DESCRIPTION` VARCHAR(2048) DEFAULT NULL,
	CHANGE COLUMN `DATE_C` `CREATION_DATE` DATETIME NOT NULL,
	ADD CONSTRAINT `fk_webarchive_account1`
		FOREIGN KEY `fk_webarchive_account1` (`ID_ACCOUNT`)
		REFERENCES `account` (`ID_ACCOUNT`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
	;

-- ----------------------------------------------------------------------------
-- Table account
-- ----------------------------------------------------------------------------

ALTER TABLE `account`
	DROP INDEX `EMAIL` ,
	-- allow null values until the end of the upgrade
	ADD COLUMN `ID_ACCESS_LEVEL` BIGINT NULL ,
	ADD COLUMN `ACTIVATION_TOKEN` VARCHAR(255) NULL ,
	-- allow null values until the end of the upgrade
	ADD COLUMN `SUBSCRIPTION_DATE` TIMESTAMP NULL ,
  CHANGE COLUMN `NAME` `LAST_NAME` VARCHAR(255) NULL ,
  CHANGE COLUMN `FIRSTNAME` `FIRST_NAME` VARCHAR(255) NULL ,
	MODIFY `ACTIVATED` BIT(1) NOT NULL ,
	ADD UNIQUE INDEX `unique_account_email` (`EMAIL`) ,
	ADD CONSTRAINT `fk_account_access_level1`
		FOREIGN KEY `fk_account_access_level1` (`ID_ACCESS_LEVEL`)
		REFERENCES `access_level` (`ID_ACCESS_LEVEL`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
	;


-- ----------------------------------------------------------------------------
-- Table nomenclature
-- ----------------------------------------------------------------------------

-- No other changes

-- ----------------------------------------------------------------------------
-- Table nomenclature_element
-- ----------------------------------------------------------------------------

ALTER TABLE `nomenclature_element`
	MODIFY COLUMN `ID_NOMENCLATURE` BIGINT NOT NULL ,
	ADD CONSTRAINT `fk_nomenclature_element_nomenclature1`
		FOREIGN KEY (`ID_NOMENCLATURE`)
		REFERENCES `nomenclature` (`ID_NOMENCLATURE`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
	;

-- ----------------------------------------------------------------------------
-- Table testcase_nomenclature_element
-- ----------------------------------------------------------------------------

ALTER TABLE `testcase_nomenclature_element`
	ADD CONSTRAINT `fk_testcase_nomenclature_element_testcase1`
		FOREIGN KEY (`ID_TESTCASE`)
		REFERENCES `testcase` (`ID_TESTCASE`)
		ON UPDATE CASCADE
		ON DELETE CASCADE ,
	ADD CONSTRAINT `fk_testcase_nomenclature_element_nomenclature_element1`
		FOREIGN KEY (`ID_NOMENCLATURE_ELEMENT`)
		REFERENCES `nomenclature_element` (`ID_NOMENCLATURE_ELEMENT`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
	;

