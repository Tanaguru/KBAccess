
/*
** NOTE: This script put the table testcase in an intermediate
**       state  filled, then allowing us to update the data inside the
**       testcase table
*/

ALTER TABLE `testcase`
	/*
	** FK already droped by the previous script
	DROP FOREIGN KEY `FK_TESTCASE_RESULT`,
	DROP FOREIGN KEY `FK_TESTCASE_TEST`,
	DROP FOREIGN KEY `FK_TESTCASE_ACCOUNT`,
	DROP FOREIGN KEY `FK_TESTCASE_WEBARCHIVE`,
	*/
	DROP COLUMN `CD_TESTCASE`,
	DROP COLUMN `NUM_TC`,
  DROP COLUMN `DELETED`,
	MODIFY COLUMN `ID_RESULT` BIGINT NOT NULL,
	-- null values allowed for the update
	MODIFY COLUMN `ID_TEST` BIGINT NULL,
	MODIFY COLUMN `ID_ACCOUNT` BIGINT NOT NULL,
	MODIFY COLUMN `ID_WEBARCHIVE` BIGINT NOT NULL,
	-- null values allowed for the update
	CHANGE `LABEL` `TITLE` VARCHAR(255) NULL,
	CHANGE `DATE_C` `CREATION_DATE` DATETIME NOT NULL,
	-- null values allowed for the update
	ADD COLUMN `ID_CRITERION` BIGINT NULL,
	ADD CONSTRAINT `fk_testcase_account1`
		FOREIGN KEY (`ID_ACCOUNT`)
		REFERENCES `account` (`ID_ACCOUNT`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	ADD CONSTRAINT `fk_testcase_criterion1`
		FOREIGN KEY (`ID_CRITERION`)
		REFERENCES `criterion` (`ID_CRITERION`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	ADD CONSTRAINT `fk_testcase_result1`
		FOREIGN KEY (`ID_RESULT`)
		REFERENCES `result` (`ID_RESULT`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	-- kept for the update
	ADD CONSTRAINT `fk_testcase_test1`
		FOREIGN KEY (`ID_TEST`)
		REFERENCES `test` (`ID_TEST`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT,
	ADD CONSTRAINT `fk_testcase_webarchive1`
		FOREIGN KEY (`ID_WEBARCHIVE`)
		REFERENCES `webarchive` (`ID_WEBARCHIVE`)
		ON UPDATE CASCADE
		ON DELETE RESTRICT
	;

