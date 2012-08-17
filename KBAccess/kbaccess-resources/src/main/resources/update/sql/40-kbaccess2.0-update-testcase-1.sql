
/*
** NOTE: There, the table testcase should be in an intermediate
**       state in which we have nullable assocaition with both
**       test and criterion.
*/

-- ----------------------------------------------------------------------------
-- setp 01 - create testcase associated with criterion
-- ----------------------------------------------------------------------------

-- testcase with a nmi result are droped
DELETE FROM `testcase` WHERE ID_RESULT = 4; -- 4 = 'Undefined' = previously nmi

DROP PROCEDURE IF EXISTS `create_testcase_criterion`;

delimiter |
CREATE PROCEDURE `create_testcase_criterion`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE tc_desc VARCHAR(2048);
  DECLARE tc_title VARCHAR(255);
  DECLARE v_id_criterion, v_id_webarchive, v_id_testcase INT;
  DECLARE v_id_account INT;
  DECLARE tc_count, tc_priority INT;
  DECLARE tc_creation_date, oth_date DATETIME;
  DECLARE testcase CURSOR FOR
      SELECT t.ID_CRITERION, tc.ID_WEBARCHIVE, tc.ID_TESTCASE,
             tc.ID_ACCOUNT, tc.DESCRIPTION, tc.TITLE, tc.PRIORITY,
             tc.CREATION_DATE
      FROM testcase AS tc
      JOIN test AS t ON t.ID_TEST = tc.ID_TEST
      WHERE tc.ID_CRITERION IS NULL
      ;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  -- create temporary table
  CREATE TABLE IF NOT EXISTS `testcase-tmp42` LIKE `testcase`;
  DELETE FROM `testcase-tmp42`;

  OPEN testcase;
  testcase_loop: LOOP

    FETCH testcase INTO v_id_criterion, v_id_webarchive, v_id_testcase,
        v_id_account, tc_desc, tc_title, tc_priority, tc_creation_date;

    IF done THEN
      LEAVE testcase_loop;
    END IF;

    SELECT COUNT(*), tc.CREATION_DATE INTO tc_count, oth_date
        FROM `testcase-tmp42` AS tc
        WHERE tc.ID_CRITERION = v_id_criterion
        AND tc.ID_WEBARCHIVE = v_id_webarchive
        ;
  
    IF tc_count = 1 THEN

      UPDATE `testcase-tmp42` SET ID_CRITERION = v_id_criterion
          WHERE ID_TESTCASE = v_id_testcase;

      IF oth_date > tc_creation_date THEN

        UPDATE `testcase-tmp42` SET CREATION_DATE = tc_creation_date
            WHERE ID_TESTCASE = v_id_testcase;

      END IF;

    ELSE

      INSERT INTO `testcase-tmp42`
          (ID_RESULT, ID_ACCOUNT, ID_WEBARCHIVE,
           DESCRIPTION, TITLE, PRIORITY, CREATION_DATE,
           ID_CRITERION) VALUES
          (4 /* = 'Undefined' */, v_id_account, v_id_webarchive,
           tc_desc, tc_title, tc_priority, tc_creation_date,
           v_id_criterion)
          ;

    END IF;

  END LOOP;
  CLOSE testcase;

  -- drop temporary table
  INSERT INTO `testcase`
             (`ID_RESULT`, `ID_TEST`, `ID_ACCOUNT`, `ID_WEBARCHIVE`,
              `DESCRIPTION`, `TITLE`, `PRIORITY`, `CREATION_DATE`,
              `ID_CRITERION`)
      SELECT  `ID_RESULT`, `ID_TEST`, `ID_ACCOUNT`, `ID_WEBARCHIVE`,
              `DESCRIPTION`, `TITLE`, `PRIORITY`, `CREATION_DATE`,
              `ID_CRITERION`
          FROM `testcase-tmp42` 
      ;
   DROP TABLE `testcase-tmp42`;

END |
delimiter ;

CALL create_testcase_criterion();
DROP PROCEDURE create_testcase_criterion;

-- ----------------------------------------------------------------------------
-- setp 02 - create association between testcase and test_result
-- ----------------------------------------------------------------------------


DROP PROCEDURE IF EXISTS `create_testcase_test_result`;

delimiter |
CREATE PROCEDURE `create_testcase_test_result`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE v_id_test, v_id_criterion, v_id_webarchive, v_id_result INT;
  DECLARE v_id_testcase INT;
  DECLARE testcase CURSOR FOR
      SELECT t.ID_TEST, t.ID_CRITERION, tc.ID_WEBARCHIVE, tc.ID_RESULT
      FROM testcase AS tc
      JOIN test AS t ON t.ID_TEST = tc.ID_TEST
      WHERE tc.ID_CRITERION IS NULL
      ;
  
  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN testcase;
  testcase_loop: LOOP

    FETCH testcase INTO v_id_test, v_id_criterion, v_id_webarchive, v_id_result;
    IF done THEN
      LEAVE testcase_loop;
    END IF;

    INSERT IGNORE INTO testcase_test_result (ID_TESTCASE, ID_TEST_RESULT) VALUES
        ((SELECT tc.ID_TESTCASE
              FROM testcase AS tc
              WHERE tc.ID_CRITERION = v_id_criterion
                    AND tc.ID_WEBARCHIVE = v_id_webarchive),
         (SELECT tr.ID_TEST_RESULT
              FROM test_result AS tr
              WHERE tr.ID_RESULT = v_id_result AND tr.ID_TEST = v_id_test)
        );

  END LOOP;
  CLOSE testcase;

END |
delimiter ;

CALL create_testcase_test_result();
DROP PROCEDURE create_testcase_test_result;

-- ----------------------------------------------------------------------------
-- setp 03 - delete testcase <-> test association
-- ----------------------------------------------------------------------------
ALTER TABLE `testcase`
    DROP FOREIGN KEY `fk_testcase_test1`,
    DROP COLUMN ID_TEST
    ;

DELETE FROM `testcase`
    WHERE ID_CRITERION IS NULL
    ;

-- ----------------------------------------------------------------------------
-- setp 04 - compute testcase result
-- ----------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS `update_testcase_result`;

delimiter |
CREATE PROCEDURE `update_testcase_result`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE v_id_testcase INT DEFAULT 0;
  DECLARE id_failed, id_undefined INT DEFAULT 0;
  DECLARE testcase CURSOR FOR
      SELECT tc.ID_TESTCASE
      FROM testcase AS tc
      ;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  SELECT ID_RESULT INTO id_failed FROM result WHERE CD_RESULT = 'Failed';
  SELECT ID_RESULT INTO id_undefined FROM result WHERE CD_RESULT = 'Undefined';

  OPEN testcase;
  testcase_loop: LOOP

    FETCH testcase INTO v_id_testcase;
    IF done THEN
      LEAVE testcase_loop;
    END IF;

    IF (SELECT COUNT(*)
        FROM testcase_test_result AS ttr
        JOIN test_result AS tr ON ttr.ID_TEST_RESULT = tr.ID_TEST_RESULT
        WHERE ttr.ID_TESTCASE = v_id_testcase
              AND tr.ID_RESULT = id_failed
        ) = 0 THEN

      UPDATE `testcase` SET ID_RESULT = id_undefined
          WHERE ID_TESTCASE = v_id_testcase;
    
    ELSE

      UPDATE `testcase` SET ID_RESULT = id_failed
          WHERE ID_TESTCASE = v_id_testcase;

    END IF;

  END LOOP;
  CLOSE testcase;

END |
delimiter ;

CALL update_testcase_result();
DROP PROCEDURE update_testcase_result;

-- ----------------------------------------------------------------------------
-- setp 05 - set testcase title
-- ----------------------------------------------------------------------------


UPDATE `testcase` AS tc SET TITLE = CONCAT(
   'Critère Accessiweb ',
   -- criterion label
   (SELECT c.LABEL
    FROM criterion AS c
    WHERE c.ID_CRITERION = tc.ID_CRITERION
   ),
   ' ',
    -- result label
    (SELECT IF(r.ID_RESULT = 1,
               'validé',
               IF(r.ID_RESULT = 2,
                  'invalidé',
                  IF(r.ID_RESULT = 3,
                     'non applicable',
                     'indéterminé')))
     FROM result AS r
     WHERE r.ID_RESULT = tc.ID_RESULT
    ),
   ' sur le site ',
   -- url fqdn
   (SELECT SUBSTR(
              SUBSTR(w.URL,
                  IF(POSITION('//' IN w.URL) <> 0,
                      POSITION('//' IN w.URL) + 2,
                      LENGTH(w.URL)
                    )
                  ),
              1,
              IF(POSITION('/' IN IF(POSITION('//' IN w.URL) <> 0,
                                    SUBSTR(w.URL, POSITION('//' IN w.URL) + 2),
                                    w.URL)) <> 0,
                 POSITION('/' IN IF(POSITION('//' IN w.URL) <> 0,
                                    SUBSTR(w.URL, POSITION('//' IN w.URL) + 2),
                                    w.URL)) - 1,

                 LENGTH(w.URL)
                )
              )
     FROM webarchive as w
     WHERE w.ID_WEBARCHIVE = tc.ID_WEBARCHIVE
    )
   )
   WHERE 1;

/*

DROP PROCEDURE IF EXISTS `create_testcase_title`;

delimiter |
CREATE PROCEDURE `create_testcase_title`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE v_id_testcase INT DEFAULT 0;
  DECLARE label_criterion, label_result, url_webarchive VARCHAR(255) DEFAULT '';
  DECLARE dslash_pos INT DEFAULT 0;
  DECLARE end_fqdn_pos INT DEFAULT 0;
  DECLARE testcase CURSOR FOR
      SELECT tc.ID_TESTCASE, c.LABEL, r.LABEL, w.URL
      FROM testcase AS tc
      JOIN criterion AS c ON tc.ID_CRITERION = c.ID_CRITERION
      JOIN result AS r ON tc.ID_RESULT = r.ID_RESULT
      JOIN webarchive AS w ON tc.ID_WEBARCHIVE = w.ID_WEBARCHIVE
      WHERE tc.TITLE IS NULL
      ;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN testcase;
  testcase_loop: LOOP

    FETCH testcase
        INTO v_id_testcase, label_criterion, label_result, url_webarchive;
    IF done THEN
      LEAVE testcase_loop;
    END IF;

    SELECT POSITION('//' IN url_webarchive) INTO dslash_pos;
    IF (dslash_pos <> 0) AND (dslash_pos < LENGTH('https://')) THEN

      SELECT POSITION('/' IN SUBSTR(url_webarchive, dslash_pos + 2))
          INTO end_fqdn_pos;
      SELECT SUBSTR(
          url_webarchive,
          dslash_pos + 2,
          end_fqdn_pos - 1
          )
          INTO url_webarchive
          ;

    ELSE

      SELECT POSITION('/' IN url_webarchive) INTO end_fqdn_pos;
      IF end_fqdn_pos THEN

        SELECT SUBSTR(url_webarchive, 0, end_fqdn_pos -1) INTO url_webarchive;

      END IF;
      
    END IF;

    UPDATE `testcase`
        SET TITLE = CONCAT(
            label_result,
            ' du critère Accessiweb2.1 ',
            label_criterion,
            ' sur le site ',
            url_webarchive
            )
        WHERE ID_TESTCASE = v_id_testcase
        ;

  END LOOP;
  CLOSE testcase;

END |
delimiter ;

CALL create_testcase_title();
DROP PROCEDURE create_testcase_title;
*/

