

-- ----------------------------------------------------------------------------
-- Table access_level
-- ----------------------------------------------------------------------------
INSERT IGNORE INTO `access_level`
	(ID_ACCESS_LEVEL, CD_ACCESS_LEVEL, LABEL, DESCRIPTION, PRIORITY) VALUES
	(1, 'admin',   'administrator', 'rwx ugo',  1),
	(2, 'moder',   'moderator',     'rwx ug',   2),
	(3, 'contrib', 'contributor',   'r-x',      3),
	(4, 'anony',   'anonymous',     'r',       99)
	;

-- ----------------------------------------------------------------------------
-- Table result
-- ----------------------------------------------------------------------------
INSERT IGNORE INTO `result`
	(ID_RESULT, CD_RESULT, LABEL, DESCRIPTION, PRIORITY) VALUES
	(1, 'Passed', 'Passed', '', 1),
	(2, 'Failed', 'Failed', '', 2),
	(3, 'NA', 'Not applicable', '', 3),
	(4, 'Undefined', 'Undefined', '', 4)
	;

-- ----------------------------------------------------------------------------
-- Table test_result
-- ----------------------------------------------------------------------------
delimiter |
CREATE PROCEDURE `generate_test_result`()
BEGIN

  DECLARE done INT DEFAULT 0;
  DECLARE id_test, id_result INT DEFAULT 0;
  DECLARE id_exluded_result INT DEFAULT 4;
  DECLARE test CURSOR FOR SELECT ID_TEST FROM test;
  DECLARE result CURSOR FOR SELECT ID_RESULT FROM result;

  DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET done = 1;

  OPEN test;
  test_loop: LOOP

    FETCH test INTO id_test;
    IF done THEN
      LEAVE test_loop;
    END IF;

    OPEN result;
    result_loop: LOOP

      FETCH result INTO id_result;
      IF done THEN
        LEAVE result_loop;
      END LOOP;

      IF id_result <> id_excluded_result THEN

        INSERT IGNORE INTO `test_result`
            (ID_TEST, ID_RESULT) VALUES
            (@id_test, @id_result)
            ;

      END IF;

    END LOOP;
    CLOSE result;
    done = 0;

  END LOOP;
  CLOSE test;

END |
delimiter ;

CALL generate_test_result();
DROP PROCEDURE generate_test_result;


