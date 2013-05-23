-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le : Ven 10 Mai 2013 à 17:10
-- Version du serveur: 5.5.31
-- Version de PHP: 5.3.10-1ubuntu3.6

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- --------------------------------------------------------

--
-- Structure de la table `access_level`
--

CREATE TABLE IF NOT EXISTS `access_level` (
  `ID_ACCESS_LEVEL` bigint(20) NOT NULL AUTO_INCREMENT,
  `CD_ACCESS_LEVEL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_ACCESS_LEVEL`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `ID_ACCOUNT` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACTIVATED` bit(1) DEFAULT NULL,
  `ACTIVATION_TOKEN` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FIRST_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SUBSCRIPTION_DATE` datetime DEFAULT NULL,
  `URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ID_ACCESS_LEVEL` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_ACCOUNT`),
  KEY `FKB9D38A2D79BD8B1` (`ID_ACCESS_LEVEL`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=527 ;

-- --------------------------------------------------------

--
-- Structure de la table `criterion`
--

CREATE TABLE IF NOT EXISTS `criterion` (
  `ID_CRITERION` bigint(20) NOT NULL AUTO_INCREMENT,
  `CD_CRITERION` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY` int(11) NOT NULL,
  `URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ID_LEVEL` bigint(20) NOT NULL,
  `ID_REFERENCE` bigint(20) NOT NULL,
  `ID_THEME` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_CRITERION`),
  KEY `FK16E20EA1135AE268` (`ID_REFERENCE`),
  KEY `FK16E20EA1A7612F5A` (`ID_LEVEL`),
  KEY `FK16E20EA1B943964` (`ID_THEME`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=134 ;

-- --------------------------------------------------------

--
-- Structure de la table `criterion_criterion`
--

CREATE TABLE IF NOT EXISTS `criterion_criterion` (
  `ID_CRITERION_CRITERION` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_CRITERION_FROM` bigint(20) NOT NULL,
  `ID_CRITERION_TO` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_CRITERION_CRITERION`),
  UNIQUE KEY `unique_criterion_criterion_pair` (`ID_CRITERION_FROM`,`ID_CRITERION_TO`),
  KEY `fk_criterion_has_criterion_criterion1` (`ID_CRITERION_FROM`),
  KEY `fk_criterion_has_criterion_criterion2` (`ID_CRITERION_TO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `level`
--

CREATE TABLE IF NOT EXISTS `level` (
  `ID_LEVEL` bigint(20) NOT NULL AUTO_INCREMENT,
  `CD_LEVEL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_LEVEL`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Structure de la table `nomenclature`
--

CREATE TABLE IF NOT EXISTS `nomenclature` (
  `ID_NOMENCLATURE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CD_NOMENCLATURE` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(2048) DEFAULT NULL,
  `LABEL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_NOMENCLATURE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `nomenclature_element`
--

CREATE TABLE IF NOT EXISTS `nomenclature_element` (
  `ID_NOMENCLATURE_ELEMENT` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_NOMENCLATURE` bigint(20) NOT NULL,
  `CD_NOMENCLATURE_ELEMENT` varchar(255) NOT NULL,
  `LABEL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_NOMENCLATURE_ELEMENT`),
  KEY `FK_NOMENCLATURE_ELEMENT_NOMENCLATURE` (`ID_NOMENCLATURE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `reference`
--

CREATE TABLE IF NOT EXISTS `reference` (
  `ID_REFERENCE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CD_REFERENCE` varchar(255) COLLATE utf8_bin NOT NULL,
  `CD_CRITERION_LEVEL_NAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `CD_TEST_LEVEL_NAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_REFERENCE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Structure de la table `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `ID_RESULT` bigint(20) NOT NULL AUTO_INCREMENT,
  `CD_RESULT` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY` int(11) NOT NULL,
  PRIMARY KEY (`ID_RESULT`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Structure de la table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `ID_TEST` bigint(20) NOT NULL AUTO_INCREMENT,
  `CD_TEST` varchar(255) COLLATE utf8_bin NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ID_CRITERION` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_TEST`),
  KEY `FK364492A624E614` (`ID_CRITERION`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=306 ;

-- --------------------------------------------------------

--
-- Structure de la table `testcase`
--

CREATE TABLE IF NOT EXISTS `testcase` (
  `ID_TESTCASE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATION_DATE` datetime NOT NULL,
  `DESCRIPTION` varchar(5000) COLLATE utf8_bin DEFAULT NULL,
  `PRIORITY` int(11) NOT NULL,
  `TITLE` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_ACCOUNT` bigint(20) NOT NULL,
  `ID_CRITERION` bigint(20) NOT NULL,
  `ID_RESULT` bigint(20) NOT NULL,
  `ID_WEBARCHIVE` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_TESTCASE`),
  KEY `FKBBAC26C2F092FE09` (`ID_WEBARCHIVE`),
  KEY `FKBBAC26C221C70E28` (`ID_RESULT`),
  KEY `FKBBAC26C2253FB5A` (`ID_ACCOUNT`),
  KEY `FKBBAC26C2A624E614` (`ID_CRITERION`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1118 ;

-- --------------------------------------------------------

--
-- Structure de la table `testcase_nomenclature_element`
--

CREATE TABLE IF NOT EXISTS `testcase_nomenclature_element` (
  `ID_NOMENCLATURE_ELEMENT` bigint(20) NOT NULL,
  `ID_TESTCASE` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_NOMENCLATURE_ELEMENT`,`ID_TESTCASE`),
  KEY `FK_HAVE_TESTCASE` (`ID_TESTCASE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `testcase_test_result`
--

CREATE TABLE IF NOT EXISTS `testcase_test_result` (
  `ID_TESTCASE` bigint(20) NOT NULL,
  `ID_TEST_RESULT` bigint(20) NOT NULL,
  KEY `FK95F90A6D39C5649E` (`ID_TEST_RESULT`),
  KEY `ID_TESTCASE` (`ID_TESTCASE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `test_result`
--

CREATE TABLE IF NOT EXISTS `test_result` (
  `ID_TEST_RESULT` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_RESULT` bigint(20) DEFAULT NULL,
  `ID_TEST` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_TEST_RESULT`),
  KEY `FKEF1E986A21C70E28` (`ID_RESULT`),
  KEY `FKEF1E986A1F107812` (`ID_TEST`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=1221 ;

-- --------------------------------------------------------

--
-- Structure de la table `theme`
--

CREATE TABLE IF NOT EXISTS `theme` (
  `ID_THEME` bigint(20) NOT NULL AUTO_INCREMENT,
  `CD_THEME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_THEME`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=14 ;

-- --------------------------------------------------------

--
-- Structure de la table `webarchive`
--

CREATE TABLE IF NOT EXISTS `webarchive` (
  `ID_WEBARCHIVE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATION_DATE` datetime NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOCAL_URL` varchar(255) COLLATE utf8_bin NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `SCOPE` varchar(255) COLLATE utf8_bin NOT NULL,
  `URL` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_ACCOUNT` bigint(20) NOT NULL,
  PRIMARY KEY (`ID_WEBARCHIVE`),
  KEY `FKA4BF6EEE253FB5A` (`ID_ACCOUNT`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=381 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FKB9D38A2D79BD8B1` FOREIGN KEY (`ID_ACCESS_LEVEL`) REFERENCES `access_level` (`ID_ACCESS_LEVEL`);

--
-- Contraintes pour la table `criterion`
--
ALTER TABLE `criterion`
  ADD CONSTRAINT `FK16E20EA1135AE268` FOREIGN KEY (`ID_REFERENCE`) REFERENCES `reference` (`ID_REFERENCE`),
  ADD CONSTRAINT `FK16E20EA1A7612F5A` FOREIGN KEY (`ID_LEVEL`) REFERENCES `level` (`ID_LEVEL`),
  ADD CONSTRAINT `FK16E20EA1B943964` FOREIGN KEY (`ID_THEME`) REFERENCES `theme` (`ID_THEME`);

--
-- Contraintes pour la table `criterion_criterion`
--
ALTER TABLE `criterion_criterion`
  ADD CONSTRAINT `fk_criterion_has_criterion_criterion1` FOREIGN KEY (`ID_CRITERION_FROM`) REFERENCES `criterion` (`ID_CRITERION`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_criterion_has_criterion_criterion2` FOREIGN KEY (`ID_CRITERION_TO`) REFERENCES `criterion` (`ID_CRITERION`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `nomenclature_element`
--
ALTER TABLE `nomenclature_element`
  ADD CONSTRAINT `fk_nomenclature_element_nomenclature1` FOREIGN KEY (`ID_NOMENCLATURE`) REFERENCES `nomenclature` (`ID_NOMENCLATURE`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `test`
--
ALTER TABLE `test`
  ADD CONSTRAINT `FK364492A624E614` FOREIGN KEY (`ID_CRITERION`) REFERENCES `criterion` (`ID_CRITERION`);

--
-- Contraintes pour la table `testcase`
--
ALTER TABLE `testcase`
  ADD CONSTRAINT `FKBBAC26C221C70E28` FOREIGN KEY (`ID_RESULT`) REFERENCES `result` (`ID_RESULT`),
  ADD CONSTRAINT `FKBBAC26C2253FB5A` FOREIGN KEY (`ID_ACCOUNT`) REFERENCES `account` (`ID_ACCOUNT`),
  ADD CONSTRAINT `FKBBAC26C2A624E614` FOREIGN KEY (`ID_CRITERION`) REFERENCES `criterion` (`ID_CRITERION`),
  ADD CONSTRAINT `FKBBAC26C2F092FE09` FOREIGN KEY (`ID_WEBARCHIVE`) REFERENCES `webarchive` (`ID_WEBARCHIVE`);

--
-- Contraintes pour la table `testcase_nomenclature_element`
--
ALTER TABLE `testcase_nomenclature_element`
  ADD CONSTRAINT `fk_testcase_nomenclature_element_nomenclature_element1` FOREIGN KEY (`ID_NOMENCLATURE_ELEMENT`) REFERENCES `nomenclature_element` (`ID_NOMENCLATURE_ELEMENT`) ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_testcase_nomenclature_element_testcase1` FOREIGN KEY (`ID_TESTCASE`) REFERENCES `testcase` (`ID_TESTCASE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `testcase_test_result`
--
ALTER TABLE `testcase_test_result`
  ADD CONSTRAINT `FK95F90A6D39C5649E` FOREIGN KEY (`ID_TEST_RESULT`) REFERENCES `test_result` (`ID_TEST_RESULT`),
  ADD CONSTRAINT `testcase_test_result_ibfk_1` FOREIGN KEY (`ID_TESTCASE`) REFERENCES `testcase` (`ID_TESTCASE`) ON DELETE CASCADE;

--
-- Contraintes pour la table `test_result`
--
ALTER TABLE `test_result`
  ADD CONSTRAINT `FKEF1E986A1F107812` FOREIGN KEY (`ID_TEST`) REFERENCES `test` (`ID_TEST`),
  ADD CONSTRAINT `FKEF1E986A21C70E28` FOREIGN KEY (`ID_RESULT`) REFERENCES `result` (`ID_RESULT`);

--
-- Contraintes pour la table `webarchive`
--
ALTER TABLE `webarchive`
  ADD CONSTRAINT `FKA4BF6EEE253FB5A` FOREIGN KEY (`ID_ACCOUNT`) REFERENCES `account` (`ID_ACCOUNT`);
SET FOREIGN_KEY_CHECKS=1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
