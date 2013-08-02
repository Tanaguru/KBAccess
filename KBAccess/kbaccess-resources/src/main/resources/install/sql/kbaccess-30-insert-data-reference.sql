
SET FOREIGN_KEY_CHECKS=0;


--
-- Content of table `access_level`
--

INSERT IGNORE INTO `access_level` (`ID_ACCESS_LEVEL`, `CD_ACCESS_LEVEL`, `DESCRIPTION`, `LABEL`, `RANK`) VALUES
(1, 'admin', 'rwx ugo', 'administrator', 1),
(2, 'moder', 'rwx ug', 'moderator', 2),
(3, 'contrib', 'r-x', 'contributor', 3),
(4, 'anony', 'r', 'anonymous', 99);

--
-- Content of table `result`
--
INSERT IGNORE INTO `result` (`ID_RESULT`, `CD_RESULT`, `DESCRIPTION`, `LABEL`, `RANK`) VALUES
(1, 'passed', NULL, 'Validé', 1),
(2, 'failed', NULL, 'Invalidé', 2),
(3, 'na', NULL, 'NA', 3);

SET FOREIGN_KEY_CHECKS=1;

