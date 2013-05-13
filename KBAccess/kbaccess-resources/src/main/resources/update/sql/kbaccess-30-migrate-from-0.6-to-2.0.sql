--
-- Data migration script
-- Source data version : KBAccess 0.6
-- Target data version : KBAccess 2.0
--
-- Replace 'migrate_06' with 0.6 database name
-- Replace 'migrate_20' with 2.0 database name

SET FOREIGN_KEY_CHECKS=0;

-- BEGIN OF MIGRATION

--
-- Account
-- In KBAccess 0.6 access levels are stored in a varchar directly in the account table
-- We first add a temporary column to store the KBAccess 0.6 access level
-- Then we update the ID_ACCESS_LEVEL column according to the ACCESS_LEVEL column
-- In KBAccess 0.6 there were only administrators and contributors
--

ALTER TABLE migrate_20.account
ADD COLUMN ACCESS_LEVEL varchar(255);

INSERT INTO migrate_20.account(ID_ACCOUNT, EMAIL, PASSWORD, LAST_NAME, FIRST_NAME, URL, ACTIVATED, ACCESS_LEVEL)
SELECT ID_ACCOUNT, EMAIL, PASSWORD, NAME, FIRSTNAME, URL, ACTIVATED, ACCESS_LEVEL
FROM migrate_06.account;

UPDATE migrate_20.account 
SET ID_ACCESS_LEVEL = 1
WHERE ACCESS_LEVEL = 'administrateur';

UPDATE migrate_20.account 
SET ID_ACCESS_LEVEL = 3
WHERE ACCESS_LEVEL = 'contributeur';

ALTER TABLE migrate_20.account
DROP COLUMN ACCESS_LEVEL;


--
-- Webarchive
-- After migrating the data we also need to remove the second occurence of "http://" in the LOCAL_URL 
--

INSERT INTO migrate_20.webarchive(ID_WEBARCHIVE, CREATION_DATE, DESCRIPTION, LOCAL_URL, PRIORITY, SCOPE, URL, ID_ACCOUNT)
SELECT ID_WEBARCHIVE, DATE_C, DESCRIPTION, LOCAL_URL, PRIORITY, SCOPE, URL, ID_ACCOUNT
FROM migrate_06.webarchive;

UPDATE migrate_20.webarchive
SET LOCAL_URL = REPLACE(LOCAL_URL, '/http://', '/');


--
-- Testcase
--

INSERT INTO migrate_20.testcase(ID_TESTCASE, CREATION_DATE, DESCRIPTION, PRIORITY, ID_CRITERION, ID_ACCOUNT, ID_RESULT, ID_WEBARCHIVE)
SELECT tc.ID_TESTCASE, tc.DATE_C, tc.DESCRIPTION, tc.PRIORITY, t.ID_CRITERION, tc.ID_ACCOUNT, tc.ID_RESULT, tc.ID_WEBARCHIVE
FROM migrate_06.testcase AS tc, migrate_06.test AS t
WHERE tc.ID_TEST = t.ID_TEST;


--
-- Testcase_test_result
--
INSERT INTO migrate_20.testcase_test_result(ID_TESTCASE, ID_TEST_RESULT)
SELECT tc.ID_TESTCASE, tr.ID_TEST_RESULT  
FROM migrate_06.testcase AS tc, migrate_20.test_result AS tr
WHERE tc.ID_TEST = tr.ID_TEST
AND tc.ID_RESULT = tr.ID_RESULT;


--
-- END OF MIGRATION
--
SET FOREIGN_KEY_CHECKS=1;


