
-- ============================================================================
-- Insert values
-- ============================================================================

-- ----------------------------------------------------------------------------
-- Update access levels
-- ----------------------------------------------------------------------------

INSERT INTO `access_level`
    (ID_ACCESS_LEVEL, CD_ACCESS_LEVEL, LABEL, DESCRIPTION, PRIORITY) VALUES
    (1, 'admin',   'administrator', 'rwx ugo', 1),
    (2, 'moder',   'moderator',     'rwx ug',  2),
    (3, 'contrib', 'contributor',   'r-x',     3),
    (4, 'anony',   'anonymous',     'r',       99)
    ON DUPLICATE KEY UPDATE
      	CD_ACCESS_LEVEL=VALUES(CD_ACCESS_LEVEL),
      	LABEL=VALUES(LABEL),
      	DESCRIPTION=VALUES(DESCRIPTION),
      	PRIORITY=VALUES(PRIORITY)
    ;

-- ----------------------------------------------------------------------------
-- Update results
-- ----------------------------------------------------------------------------

UPDATE `result`
    SET CD_RESULT = 'Undefined', LABEL = 'Undefined'
    WHERE CD_RESULT = 'NMI'
    ;

-- ============================================================================
-- Generate values
-- ============================================================================

-- ----------------------------------------------------------------------------
-- Add the level to the criterion
-- ----------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS `add_criterion_level`;

delimiter |
CREATE PROCEDURE `add_criterion_level`()
BEGIN

  DECLARE done INT DEFAULT 0;

  DECLARE v_id_criterion, id_level_min, id_level_max INT DEFAULT 0;
  DECLARE criterion CURSOR FOR
      SELECT `c`.`ID_CRITERION`, MIN(t.ID_LEVEL), MAX(t.ID_LEVEL)
      FROM criterion AS c LEFT JOIN test AS t ON t.`ID_CRITERION` = c.`ID_CRITERION`
      GROUP BY c.`ID_CRITERION`
      ;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN criterion;
  criterion_loop: LOOP

    FETCH criterion INTO v_id_criterion, id_level_min, id_level_max;
    IF done THEN
    	LEAVE criterion_loop;
    END IF;

    IF id_level_min < id_level_max THEN
      SIGNAL SQLSTATE '45000'
          SET MESSAGE_TEXT = 'The database may be corrupted : the tests of a criterion have different levels'
          ;
    END IF;

    UPDATE `criterion` SET `ID_LEVEL` = id_level_min
        WHERE `ID_CRITERION` = v_id_criterion;

  END LOOP;

END |
delimiter ;

CALL add_criterion_level();
DROP PROCEDURE `add_criterion_level`;

-- ----------------------------------------------------------------------------
-- Generate test_result
-- ----------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS `generate_test_result`;

delimiter |
CREATE PROCEDURE `generate_test_result`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE v_id_test, v_id_result INT;
  DECLARE id_excluded_result INT DEFAULT 4;
  DECLARE test CURSOR FOR SELECT `ID_TEST` FROM test;
  DECLARE result CURSOR FOR SELECT `ID_RESULT` FROM result;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN test;
  test_loop: LOOP

    FETCH test INTO v_id_test;
    IF done THEN
      LEAVE test_loop;
    END IF;

    OPEN result;
    result_loop: LOOP

      FETCH result INTO v_id_result;
      IF done THEN
        LEAVE result_loop;
      END IF;

      IF v_id_result <> id_excluded_result THEN

        INSERT IGNORE INTO `test_result`
            (`ID_TEST`, `ID_RESULT`) VALUES
            (v_id_test, v_id_result)
            ;

      END IF;

    END LOOP;
    CLOSE result;
    SELECT 0 INTO done;

  END LOOP;
  CLOSE test;

END |
delimiter ;

CALL generate_test_result();
DROP PROCEDURE generate_test_result;

-- ----------------------------------------------------------------------------
-- Generate account subscription date
-- ----------------------------------------------------------------------------

DROP PROCEDURE IF EXISTS `create_account_subscription_date`;

delimiter |
CREATE PROCEDURE `create_account_subscription_date`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE v_id_account INT DEFAULT 0;
  DECLARE sub_date DATETIME DEFAULT NULL;
  DECLARE account CURSOR FOR
      SELECT a.ID_ACCOUNT, IFNULL(MIN(tc.CREATION_DATE), DATE('2010-08-01'))
      FROM account AS a
      LEFT JOIN testcase AS tc ON tc.ID_ACCOUNT = a.ID_ACCOUNT
      GROUP BY a.ID_ACCOUNT
      ;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN account;
  account_loop: LOOP

    FETCH account INTO v_id_account, sub_date;
    IF done THEN
      LEAVE account_loop;
    END IF;

    UPDATE `account`
        SET SUBSCRIPTION_DATE = sub_date
        WHERE `ID_ACCOUNT` = v_id_account;

  END LOOP;
  CLOSE account;

END |
delimiter ;

CALL create_account_subscription_date();
DROP PROCEDURE `create_account_subscription_date`;

-- ----------------------------------------------------------------------------
-- Externalize access_level
-- ----------------------------------------------------------------------------


UPDATE `account` SET ACCESS_LEVEL = 'administrator'
    WHERE ACCESS_LEVEL = 'administrateur'; 

UPDATE `account` SET ACCESS_LEVEL = 'contributor'
    WHERE ACCESS_LEVEL = 'contributeur';

UPDATE `account` AS a SET ID_ACCESS_LEVEL =
    (SELECT al.ID_ACCESS_LEVEL
     FROM access_level AS al
     WHERE al.LABEL = a.ACCESS_LEVEL
    );
 
