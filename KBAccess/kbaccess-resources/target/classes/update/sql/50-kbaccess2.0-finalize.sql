


-- ----------------------------------------------------------------------------
-- Put the test table in its final state
-- ----------------------------------------------------------------------------

ALTER TABLE `test`
    DROP FOREIGN KEY `fk_test_level1`,
    DROP COLUMN `ID_LEVEL`
    ;

-- ----------------------------------------------------------------------------
-- Put the testcase table in its final state
-- ----------------------------------------------------------------------------

ALTER TABLE `testcase`
    MODIFY `ID_CRITERION` BIGINT NOT NULL ,
    MODIFY `TITLE` VARCHAR(255) NOT NULL ,
    ADD UNIQUE INDEX `unique_testcase_criterion_webarchive`
        (`ID_CRITERION`, `ID_WEBARCHIVE`)
    ;

-- ----------------------------------------------------------------------------
-- Put the table criterion in its final state
-- ----------------------------------------------------------------------------

ALTER TABLE `criterion`
	MODIFY COLUMN `ID_LEVEL` BIGINT NOT NULL
	;

-- ----------------------------------------------------------------------------
-- Put the table account in its final state
-- ----------------------------------------------------------------------------

ALTER TABLE `account`
	DROP COLUMN `ACCESS_LEVEL` ,
	MODIFY COLUMN `ID_ACCESS_LEVEL` BIGINT NOT NULL
	;

