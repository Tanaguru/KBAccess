--
-- Data migration script
-- Source data version : KBAccess 2.1
-- Target data version : KBAccess 2.2
--
-- Replace 'migrate_21' with 2.1 database name
-- example : sed -i 's/migrate_21./kbaccess_21./g' kbaccess-migrate-from-2.1-to-2.2.sql
-- Replace 'migrate_22' with 2.2 database name
-- example : sed -i 's/migrate_22./kbaccess_22./g' kbaccess-migrate-from-2.1-to-2.2.sql

SET FOREIGN_KEY_CHECKS=0;

-- BEGIN OF MIGRATION

--
-- Account
-- 

INSERT IGNORE INTO `migrate_22`.`account`
SELECT * 
FROM `migrate_21`.`account`;


--
-- Webarchive
-- 

INSERT IGNORE INTO `migrate_22`.`webarchive`
SELECT * 
FROM `migrate_21`.`webarchive`;


--
-- Testcase
--
INSERT IGNORE INTO `migrate_22`.`testcase`(`ID_TESTCASE`, `CREATION_DATE`, `DESCRIPTION`, `RANK`, `ID_ACCOUNT`, `ID_WEBARCHIVE`, `ID_RESULT`, `ID_REFERENCE_TEST`)  
SELECT tc.`ID_TESTCASE`, tc.`CREATION_DATE`, tc.`DESCRIPTION`, tc.`PRIORITY`, tc.`ID_ACCOUNT`, tc.`ID_WEBARCHIVE`, tc.`ID_RESULT`, tr.`ID_TEST`
FROM `migrate_21`.`testcase` tc, `migrate_21`.`testcase_test_result` tctr, `migrate_21`.`test_result` tr
WHERE tc.`ID_TESTCASE` = tctr.`ID_TESTCASE`
AND tctr.`ID_TEST_RESULT` = tr.`ID_TEST_RESULT`;

--
-- END OF MIGRATION
--
SET FOREIGN_KEY_CHECKS=1;





