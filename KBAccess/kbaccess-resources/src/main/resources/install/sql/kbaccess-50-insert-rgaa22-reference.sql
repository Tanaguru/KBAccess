
/* 
 * Insertion of Rgaa 2.2 reference
 */
 
SET FOREIGN_KEY_CHECKS=0;


--
-- Content of table reference
-- 

INSERT IGNORE INTO `reference`(`ID_REFERENCE`, `CD_REFERENCE`, `LABEL`, `RANK`, `URL`, `COUNTRY`, `INFO_MAX_DEPTH`, `TEST_MAX_DEPTH`) VALUES
(2, 'Rgaa22', 'Rgaa 2.2.1', 11, 'http://www.rgaa.net/', 'france', '+1', '-1');


--
-- Content of table `reference_level`
--
INSERT IGNORE INTO `reference_level` (`ID_REFERENCE_LEVEL`, `CD_REFERENCE_LEVEL`, `DESCRIPTION`, `LABEL`, `RANK`) VALUES
(4, 'Rgaa22-A', NULL, 'A', 13),
(5, 'Rgaa22-AA', NULL, 'AA', 14),
(6, 'Rgaa22-AAA', NULL, 'AAA', 15);

-- 
-- Content of table reference_depth
--
INSERT IGNORE INTO `reference_depth`(`ID_REFERENCE_DEPTH`, `CD_REFERENCE_DEPTH`, `DEPTH`, `RANK`) VALUES
(4, 'Rgaa22-depth+1', 1, 4),
(5, 'Rgaa22-depth-1', -1, 5);

--
-- Content of table reference_info
--
INSERT IGNORE INTO `reference_info` (`ID_REFERENCE_INFO`, `CD_REFERENCE_INFO`, `LABEL`, `RANK`, `ID_REFERENCE_DEPTH`) VALUES
(14, 'Rgaa22-01', '1', 14, 4),
(15, 'Rgaa22-02', '2', 15, 4),
(16, 'Rgaa22-03', '3', 16, 4),
(17, 'Rgaa22-04', '4', 17, 4),
(18, 'Rgaa22-05', '5', 18, 4),
(19, 'Rgaa22-06', '6', 19, 4),
(20, 'Rgaa22-07', '7', 20, 4),
(21, 'Rgaa22-08', '8', 21, 4),
(22, 'Rgaa22-09', '9', 22, 4),
(23, 'Rgaa22-10', '10', 23, 4),
(24, 'Rgaa22-11', '11', 24, 4),
(25, 'Rgaa22-12', '12', 25, 4);

--
-- Content of table reference_test
--
INSERT IGNORE INTO `reference_test` (`ID_REFERENCE_TEST`, `CD_REFERENCE_TEST`, `DESCRIPTION`, `LABEL`, `RANK`, `URL`, `ID_REFERENCE_LEVEL`, `ID_REFERENCE`, `ID_REFERENCE_INFO`, `ID_REFERENCE_DEPTH`) VALUES
(572, 'Rgaa22-0101', '', '1.1', 572, 'http://rgaa.net/1-1-Absence-de-cadres-non-titres.html', 4, 2, 14, 5),
(573, 'Rgaa22-0102', '', '1.2', 573, 'http://rgaa.net/Pertinence-des-titres-donnes-aux.html', 4, 2, 14, 5),
(574, 'Rgaa22-0201', '', '2.1', 574, 'http://rgaa.net/Presence-d-un-autre-moyen-que-la.html', 4, 2, 15, 5),
(575, 'Rgaa22-0202', '', '2.2', 575, 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,4.html', 4, 2, 15, 5),
(576, 'Rgaa22-0203', '', '2.3', 576, 'http://rgaa.net/Presence-d-un-moyen-de.html', 4, 2, 15, 5),
(577, 'Rgaa22-0204', '', '2.4', 577, 'http://rgaa.net/Presence-d-un-moyen-de,6.html', 4, 2, 15, 5),
(578, 'Rgaa22-0205', '', '2.5', 578, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du.html', 5, 2, 15, 5),
(579, 'Rgaa22-0206', '', '2.6', 579, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,8.html', 5, 2, 15, 5),
(580, 'Rgaa22-0207', '', '2.7', 580, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,9.html', 5, 2, 15, 5),
(581, 'Rgaa22-0208', '', '2.8', 581, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,10.html', 5, 2, 15, 5),
(582, 'Rgaa22-0209', '', '2.9', 582, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,11.html', 5, 2, 15, 5),
(583, 'Rgaa22-0210', '', '2.10', 583, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,12.html', 5, 2, 15, 5),
(584, 'Rgaa22-0211', '', '2.11', 584, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,13.html', 6, 2, 15, 5),
(585, 'Rgaa22-0212', '', '2.12', 585, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,14.html', 6, 2, 15, 5),
(586, 'Rgaa22-0213', '', '2.13', 586, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,15.html', 6, 2, 15, 5),
(587, 'Rgaa22-0214', '', '2.14', 587, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,16.html', 6, 2, 15, 5),
(588, 'Rgaa22-0215', '', '2.15', 588, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,17.html', 6, 2, 15, 5),
(589, 'Rgaa22-0216', '', '2.16', 589, 'http://rgaa.net/Valeur-du-rapport-de-contraste-du,18.html', 6, 2, 15, 5),
(590, 'Rgaa22-0301', '', '3.1', 590, 'http://rgaa.net/Possibilite-d-identifier-les.html', 4, 2, 16, 5),
(591, 'Rgaa22-0302', '', '3.2', 591, 'http://rgaa.net/Presence-d-information-prealable.html', 4, 2, 16, 5),
(592, 'Rgaa22-0303', '', '3.3', 592, 'http://rgaa.net/Positionnement-correct-des.html', 4, 2, 16, 5),
(593, 'Rgaa22-0304', '', '3.4', 593, 'http://rgaa.net/Regroupement-d-elements-de.html', 4, 2, 16, 5),
(594, 'Rgaa22-0305', '', '3.5', 594, 'http://rgaa.net/Absence-d-element-fieldset-sans.html', 4, 2, 16, 5),
(595, 'Rgaa22-0306', '', '3.6', 595, 'http://rgaa.net/Pertinence-du-contenu-de-l-element.html', 4, 2, 16, 5),
(596, 'Rgaa22-0307', '', '3.7', 596, 'http://rgaa.net/Regroupement-d-elements-option.html', 4, 2, 16, 5),
(597, 'Rgaa22-0308', '', '3.8', 597, 'http://rgaa.net/Presence-d-un-attribut-label-sur-l.html', 4, 2, 16, 5),
(598, 'Rgaa22-0309', '', '3.9', 598, 'http://rgaa.net/Pertinence-du-contenu-de-l.html', 4, 2, 16, 5),
(599, 'Rgaa22-0310', '', '3.10', 599, 'http://rgaa.net/Absence-d-element-de-formulaire.html', 4, 2, 16, 5),
(600, 'Rgaa22-0311', '', '3.11', 600, 'http://rgaa.net/Absence-d-element-de-formulaire,29.html', 4, 2, 16, 5),
(601, 'Rgaa22-0312', '', '3.12', 601, 'http://rgaa.net/Pertinence-des-etiquettes-d.html', 4, 2, 16, 5),
(602, 'Rgaa22-0313', '', '3.13', 602, 'http://rgaa.net/Presence-d-informations-ou-de.html', 5, 2, 16, 5),
(603, 'Rgaa22-0314', '', '3.14', 603, 'http://rgaa.net/Presence-de-mecanismes-permettant.html', 5, 2, 16, 5),
(604, 'Rgaa22-0315', '', '3.15', 604, 'http://rgaa.net/Presence-de-mecanismes-permettant,33.html', 6, 2, 16, 5),
(605, 'Rgaa22-0316', '', '3.16', 605, 'http://rgaa.net/Presence-d-une-page-d-aide-ou-d-un.html', 6, 2, 16, 5),
(606, 'Rgaa22-0401', '', '4.1', 606, 'http://rgaa.net/Presence-de-l-attribut-alt.html', 4, 2, 17, 5),
(607, 'Rgaa22-0402', '', '4.2', 607, 'http://rgaa.net/Pertinence-de-l-alternative.html', 4, 2, 17, 5),
(608, 'Rgaa22-0403', '', '4.3', 608, 'http://rgaa.net/Pertinence-de-l-alternative,37.html', 4, 2, 17, 5),
(609, 'Rgaa22-0404', '', '4.4', 609, 'http://rgaa.net/Pertinence-de-l-alternative,38.html', 4, 2, 17, 5),
(610, 'Rgaa22-0405', '', '4.5', 610, 'http://rgaa.net/Pertinence-de-l-alternative-vide.html', 4, 2, 17, 5),
(611, 'Rgaa22-0406', '', '4.6', 611, 'http://rgaa.net/Longueur-du-contenu-des.html', 4, 2, 17, 5),
(612, 'Rgaa22-0407', '', '4.7', 612, 'http://rgaa.net/Existence-d-une-description-longue.html', 4, 2, 17, 5),
(613, 'Rgaa22-0408', '', '4.8', 613, 'http://rgaa.net/Pertinence-de-la-description.html', 4, 2, 17, 5),
(614, 'Rgaa22-0409', '', '4.9', 614, 'http://rgaa.net/Presence-de-l-attribut-longdesc.html', 4, 2, 17, 5),
(615, 'Rgaa22-0410', '', '4.10', 615, 'http://rgaa.net/Presence-d-une-information-de.html', 4, 2, 17, 5),
(616, 'Rgaa22-0411', '', '4.11', 616, 'http://rgaa.net/Coherence-dans-l-identification.html', 5, 2, 17, 5),
(617, 'Rgaa22-0501', '', '5.1', 617, 'http://rgaa.net/Acces-a-une-information.html', 4, 2, 18, 5),
(618, 'Rgaa22-0502', '', '5.2', 618, 'http://rgaa.net/Presence-de-la-transcription.html', 4, 2, 18, 5),
(619, 'Rgaa22-0503', '', '5.3', 619, 'http://rgaa.net/Pertinence-de-la-transcription.html', 4, 2, 18, 5),
(620, 'Rgaa22-0504', '', '5.4', 620, 'http://rgaa.net/Presence-d-une-description-audio.html', 4, 2, 18, 5),
(621, 'Rgaa22-0505', '', '5.5', 621, 'http://rgaa.net/Pertinence-de-la-description-audio.html', 4, 2, 18, 5),
(622, 'Rgaa22-0506', '', '5.6', 622, 'http://rgaa.net/Possibilite-de-controler-l.html', 4, 2, 18, 5),
(623, 'Rgaa22-0507', '', '5.7', 623, 'http://rgaa.net/Presence-d-une-description-audio,52.html', 6, 2, 18, 5),
(624, 'Rgaa22-0508', '', '5.8', 624, 'http://rgaa.net/Presence-d-une-description-audio,53.html', 5, 2, 18, 5),
(625, 'Rgaa22-0509', '', '5.9', 625, 'http://rgaa.net/Presence-du-sous-titrage.html', 4, 2, 18, 5),
(626, 'Rgaa22-0510', '', '5.10', 626, 'http://rgaa.net/Pertinence-du-sous-titrage.html', 4, 2, 18, 5),
(627, 'Rgaa22-0511', '', '5.11', 627, 'http://rgaa.net/Presence-d-une-alternative-aux.html', 4, 2, 18, 5),
(628, 'Rgaa22-0512', '', '5.12', 628, 'http://rgaa.net/Presence-d-une-alternative-aux,57.html', 4, 2, 18, 5),
(629, 'Rgaa22-0513', '', '5.13', 629, 'http://rgaa.net/Absence-d-elements-provoquant-des.html', 4, 2, 18, 5),
(630, 'Rgaa22-0514', '', '5.14', 630, 'http://rgaa.net/Absence-de-code-javascript,59.html', 4, 2, 18, 5),
(631, 'Rgaa22-0515', '', '5.15', 631, 'http://rgaa.net/Absence-de-mise-en-forme.html', 4, 2, 18, 5),
(632, 'Rgaa22-0516', '', '5.16', 632, 'http://rgaa.net/Compatibilite-des-elements.html', 4, 2, 18, 5),
(633, 'Rgaa22-0517', '', '5.17', 633, 'http://rgaa.net/Absence-totale-de-changements.html', 6, 2, 18, 5),
(634, 'Rgaa22-0518', '', '5.18', 634, 'http://rgaa.net/Presence-du-sous-titrage,63.html', 5, 2, 18, 5),
(635, 'Rgaa22-0519', '', '5.19', 635, 'http://rgaa.net/Absence-de-l-element-blink.html', 4, 2, 18, 5),
(636, 'Rgaa22-0520', '', '5.20', 636, 'http://rgaa.net/Absence-d-elements-provoquant-des,65.html', 4, 2, 18, 5),
(637, 'Rgaa22-0521', '', '5.21', 637, 'http://rgaa.net/Absence-de-code-javascript,66.html', 4, 2, 18, 5),
(638, 'Rgaa22-0522', '', '5.22', 638, 'http://rgaa.net/Absence-de-mise-en-forme,67.html', 4, 2, 18, 5),
(639, 'Rgaa22-0523', '', '5.23', 639, 'http://rgaa.net/Absence-d-element-marquee.html', 4, 2, 18, 5),
(640, 'Rgaa22-0524', '', '5.24', 640, 'http://rgaa.net/Absence-d-elements-affichant-des.html', 4, 2, 18, 5),
(641, 'Rgaa22-0525', '', '5.25', 641, 'http://rgaa.net/Absence-de-code-javascript,70.html', 4, 2, 18, 5),
(642, 'Rgaa22-0526', '', '5.26', 642, 'http://rgaa.net/Absence-de-mise-en-forme,71.html', 4, 2, 18, 5),
(643, 'Rgaa22-0527', '', '5.27', 643, 'http://rgaa.net/Independance-du-peripherique-d.html', 4, 2, 18, 5),
(644, 'Rgaa22-0528', '', '5.28', 644, 'http://rgaa.net/Presence-d-une-alternative-aux,73.html', 4, 2, 18, 5),
(645, 'Rgaa22-0529', '', '5.29', 645, 'http://rgaa.net/Absence-d-elements-declenchant-la.html', 4, 2, 18, 5),
(646, 'Rgaa22-0530', '', '5.30', 646, 'http://rgaa.net/Absence-d-element-bgsound.html', 4, 2, 18, 5),
(647, 'Rgaa22-0531', '', '5.31', 647, 'http://rgaa.net/Presence-de-version-en-langue-des.html', 6, 2, 18, 5),
(648, 'Rgaa22-0532', '', '5.32', 648, 'http://rgaa.net/Pertinence-de-la-version-en-langue.html', 6, 2, 18, 5),
(649, 'Rgaa22-0533', '', '5.33', 649, 'http://rgaa.net/Niveau-sonore-de-la-piste-de.html', 6, 2, 18, 5),
(650, 'Rgaa22-0534', '', '5.34', 650, 'http://rgaa.net/Presence-d-un-mecanisme-pour.html', 6, 2, 18, 5),
(651, 'Rgaa22-0601', '', '6.1', 651, 'http://rgaa.net/Acces-aux-ens-textuels-doublant.html', 4, 2, 19, 5),
(652, 'Rgaa22-0602', '', '6.2', 652, 'http://rgaa.net/Presence-d-un-avertissement.html', 4, 2, 19, 5),
(653, 'Rgaa22-0603', '', '6.3', 653, 'http://rgaa.net/Presence-d-un-avertissement,82.html', 4, 2, 19, 5),
(654, 'Rgaa22-0604', '', '6.4', 654, 'http://rgaa.net/Presence-d-un-avertissement,83.html', 4, 2, 19, 5),
(655, 'Rgaa22-0605', '', '6.5', 655, 'http://rgaa.net/Absence-d-ouverture-de-nouvelles.html', 4, 2, 19, 5),
(656, 'Rgaa22-0606', '', '6.6', 656, 'http://rgaa.net/Absence-de-piege-lors-de-la.html', 4, 2, 19, 5),
(657, 'Rgaa22-0607', '', '6.7', 657, 'http://rgaa.net/Absence-d-element-meta-provoquant.html', 4, 2, 19, 5),
(658, 'Rgaa22-0608', '', '6.8', 658, 'http://rgaa.net/Absence-de-code-javascript.html', 4, 2, 19, 5),
(659, 'Rgaa22-0609', '', '6.9', 659, 'http://rgaa.net/Absence-d-elements-provoquant-un.html', 4, 2, 19, 5),
(660, 'Rgaa22-0610', '', '6.10', 660, 'http://rgaa.net/Absence-d-element-meta-provoquant,89.html', 4, 2, 19, 5),
(661, 'Rgaa22-0611', '', '6.11', 661, 'http://rgaa.net/Absence-de-code-javascript,90.html', 4, 2, 19, 5),
(662, 'Rgaa22-0612', '', '6.12', 662, 'http://rgaa.net/Absence-d-elements-provoquant-une.html', 4, 2, 19, 5),
(663, 'Rgaa22-0613', '', '6.13', 663, 'http://rgaa.net/Possibite-d-identifier-la.html', 4, 2, 19, 5),
(664, 'Rgaa22-0614', '', '6.14', 664, 'http://rgaa.net/Possibite-d-identifier-la,93.html', 6, 2, 19, 5),
(665, 'Rgaa22-0615', '', '6.15', 665, 'http://rgaa.net/Coherence-de-la-destination-ou-de.html', 4, 2, 19, 5),
(666, 'Rgaa22-0616', '', '6.16', 666, 'http://rgaa.net/Absence-de-ens-sans-intitule.html', 4, 2, 19, 5),
(667, 'Rgaa22-0617', '', '6.17', 667, 'http://rgaa.net/Presence-d-une-page-contenant-le.html', 5, 2, 19, 5),
(668, 'Rgaa22-0618', '', '6.18', 668, 'http://rgaa.net/Coherence-du-plan-du-site.html', 5, 2, 19, 5),
(669, 'Rgaa22-0619', '', '6.19', 669, 'http://rgaa.net/Presence-d-un-acces-a-la-page.html', 5, 2, 19, 5),
(670, 'Rgaa22-0620', '', '6.20', 670, 'http://rgaa.net/Presence-d-un-fil-d-ariane.html', 6, 2, 19, 5),
(671, 'Rgaa22-0621', '', '6.21', 671, 'http://rgaa.net/Presence-de-menus-ou-de-barres-de.html', 5, 2, 19, 5),
(672, 'Rgaa22-0622', '', '6.22', 672, 'http://rgaa.net/Coherence-de-la-position-des-menus.html', 5, 2, 19, 5),
(673, 'Rgaa22-0623', '', '6.23', 673, 'http://rgaa.net/Coherence-de-la-presentation-des.html', 5, 2, 19, 5),
(674, 'Rgaa22-0624', '', '6.24', 674, 'http://rgaa.net/Navigation-au-clavier-dans-un.html', 4, 2, 19, 5),
(675, 'Rgaa22-0625', '', '6.25', 675, 'http://rgaa.net/Presence-d-un-avertissement,104.html', 6, 2, 19, 5),
(676, 'Rgaa22-0626', '', '6.26', 676, 'http://rgaa.net/Presence-des-informations-de.html', 4, 2, 19, 5),
(677, 'Rgaa22-0627', '', '6.27', 677, 'http://rgaa.net/Presence-des-informations-de-poids.html', 4, 2, 19, 5),
(678, 'Rgaa22-0628', '', '6.28', 678, 'http://rgaa.net/Presence-des-informations-de,107.html', 4, 2, 19, 5),
(679, 'Rgaa22-0629', '', '6.29', 679, 'http://rgaa.net/Presence-de-regroupement-pour-les.html', 4, 2, 19, 5),
(680, 'Rgaa22-0630', '', '6.30', 680, 'http://rgaa.net/Presence-d-un-basage-permettant.html', 4, 2, 19, 5),
(681, 'Rgaa22-0631', '', '6.31', 681, 'http://rgaa.net/Presence-de-ens-d-evitement-ou-d.html', 4, 2, 19, 5),
(682, 'Rgaa22-0632', '', '6.32', 682, 'http://rgaa.net/Coherence-des-ens-d-evitement-ou.html', 4, 2, 19, 5),
(683, 'Rgaa22-0633', '', '6.33', 683, 'http://rgaa.net/Ordre-des-liens-d-evitement-ou-d.html', 5, 2, 19, 5),
(684, 'Rgaa22-0634', '', '6.34', 684, 'http://rgaa.net/Presence-d-un-moteur-de-recherche.html', 5, 2, 19, 5),
(685, 'Rgaa22-0635', '', '6.35', 685, 'http://rgaa.net/Possibilite-de-naviguer-facilement.html', 5, 2, 19, 5),
(686, 'Rgaa22-0636', '', '6.36', 686, 'http://rgaa.net/Presence-d-une-indication-de-la.html', 6, 2, 19, 5),
(687, 'Rgaa22-0701', '', '7.1', 687, 'http://rgaa.net/Absence-de-generation-de-contenus.html', 4, 2, 20, 5),
(688, 'Rgaa22-0702', '', '7.2', 688, 'http://rgaa.net/Absence-d-alteration-de-la.html', 4, 2, 20, 5),
(689, 'Rgaa22-0703', '', '7.3', 689, 'http://rgaa.net/Lisibilite-des-informations.html', 4, 2, 20, 5),
(690, 'Rgaa22-0704', '', '7.4', 690, 'http://rgaa.net/Absence-d-espaces-utilises-pour.html', 4, 2, 20, 5),
(691, 'Rgaa22-0705', '', '7.5', 691, 'http://rgaa.net/Absence-de-definition-d-une.html', 5, 2, 20, 5),
(692, 'Rgaa22-0706', '', '7.6', 692, 'http://rgaa.net/Possibilite-de-remplacer-les.html', 5, 2, 20, 5),
(693, 'Rgaa22-0707', '', '7.7', 693, 'http://rgaa.net/Possibilite-de-remplacer-les,122.html', 6, 2, 20, 5),
(694, 'Rgaa22-0708', '', '7.8', 694, 'http://rgaa.net/Absence-d-attributs-ou-d-elements.html', 4, 2, 20, 5),
(695, 'Rgaa22-0709', '', '7.9', 695, 'http://rgaa.net/Absence-d-elements-HTML-utilises-a.html', 4, 2, 20, 5),
(696, 'Rgaa22-0710', '', '7.10', 696, 'http://rgaa.net/Maintien-de-la-distinction.html', 4, 2, 20, 5),
(697, 'Rgaa22-0711', '', '7.11', 697, 'http://rgaa.net/Absence-de-suppression-de-l-effet.html', 4, 2, 20, 5),
(698, 'Rgaa22-0712', '', '7.12', 698, 'http://rgaa.net/Absence-de-justification-du-texte.html', 6, 2, 20, 5),
(699, 'Rgaa22-0713', '', '7.13', 699, 'http://rgaa.net/Lisibilite-du-document-en-cas-d.html', 5, 2, 20, 5),
(700, 'Rgaa22-0714', '', '7.14', 700, 'http://rgaa.net/Absence-d-unites-absolues-ou-de.html', 5, 2, 20, 5),
(701, 'Rgaa22-0715', '', '7.15', 701, 'http://rgaa.net/Absence-d-apparition-de-barre-de.html', 6, 2, 20, 5),
(702, 'Rgaa22-0716', '', '7.16', 702, 'http://rgaa.net/Largeur-des-blocs-de-textes.html', 6, 2, 20, 5),
(703, 'Rgaa22-0717', '', '7.17', 703, 'http://rgaa.net/Valeur-de-l-espace-entre-les.html', 6, 2, 20, 5),
(704, 'Rgaa22-0718', '', '7.18', 704, 'http://rgaa.net/Restitution-correcte-dans-les.html', 4, 2, 20, 5),
(705, 'Rgaa22-0801', '', '8.1', 705, 'http://rgaa.net/Mise-a-jour-des-alternatives-aux.html', 4, 2, 21, 5),
(706, 'Rgaa22-0802', '', '8.2', 706, 'http://rgaa.net/Universalite-du-gestionnaire-d.html', 4, 2, 21, 5),
(707, 'Rgaa22-0803', '', '8.3', 707, 'http://rgaa.net/Universalite-des-gestionnaires-d.html', 4, 2, 21, 5),
(708, 'Rgaa22-0804', '', '8.4', 708, 'http://rgaa.net/Possibilite-de-desactiver-toute.html', 6, 2, 21, 5),
(709, 'Rgaa22-0805', '', '8.5', 709, 'http://rgaa.net/Absence-de-changements-de-contexte.html', 4, 2, 21, 5),
(710, 'Rgaa22-0806', '', '8.6', 710, 'http://rgaa.net/Ordre-d-acces-au-clavier-aux.html', 4, 2, 21, 5),
(711, 'Rgaa22-0807', '', '8.7', 711, 'http://rgaa.net/Utilisation-correcte-du-role-des.html', 4, 2, 21, 5),
(712, 'Rgaa22-0808', '', '8.8', 712, 'http://rgaa.net/Presence-d-une-alternative-au-code.html', 4, 2, 21, 5),
(713, 'Rgaa22-0809', '', '8.9', 713, 'http://rgaa.net/Absence-de-suppression-du-focus.html', 4, 2, 21, 5),
(714, 'Rgaa22-0810', '', '8.10', 714, 'http://rgaa.net/Absence-de-limite-de-temps-pour.html', 6, 2, 21, 5),
(715, 'Rgaa22-0811', '', '8.11', 715, 'http://rgaa.net/Absence-de-perte-d-informations.html', 6, 2, 21, 5),
(716, 'Rgaa22-0812', '', '8.12', 716, 'http://rgaa.net/Presence-d-une-alternative-au-code,145.html', 4, 2, 21, 5),
(717, 'Rgaa22-0813', '', '8.13', 717, 'http://rgaa.net/Accessibilite-des-contenus.html', 4, 2, 21, 5),
(718, 'Rgaa22-0901', '', '9.1', 718, 'http://rgaa.net/Presence-de-la-declaration-d.html', 4, 2, 22, 5),
(719, 'Rgaa22-0902', '', '9.2', 719, 'http://rgaa.net/Conformite-de-la-position-de-la.html', 4, 2, 22, 5),
(720, 'Rgaa22-0903', '', '9.3', 720, 'http://rgaa.net/Conformite-syntaxique-de-la.html', 4, 2, 22, 5),
(721, 'Rgaa22-0904', '', '9.4', 721, 'http://rgaa.net/Validite-du-code-HTML-XHTML-au.html', 4, 2, 22, 5),
(722, 'Rgaa22-0905', '', '9.5', 722, 'http://rgaa.net/Absence-de-composants-obsoletes.html', 4, 2, 22, 5),
(723, 'Rgaa22-0906', '', '9.6', 723, 'http://rgaa.net/Presence-d-un-titre-dans-la-page.html', 4, 2, 22, 5),
(724, 'Rgaa22-0907', '', '9.7', 724, 'http://rgaa.net/Pertinence-du-titre-de-la-page.html', 4, 2, 22, 5),
(725, 'Rgaa22-0908', '', '9.8', 725, 'http://rgaa.net/Presence-d-une-langue-de.html', 4, 2, 22, 5),
(726, 'Rgaa22-1001', '', '10.1', 726, 'http://rgaa.net/Presence-d-au-moins-un-titre-de.html', 4, 2, 23, 5),
(727, 'Rgaa22-1002', '', '10.2', 727, 'http://rgaa.net/Pertinence-du-contenu-des-titres.html', 4, 2, 23, 5),
(728, 'Rgaa22-1003', '', '10.3', 728, 'http://rgaa.net/Absence-d-interruption-dans-la.html', 4, 2, 23, 5),
(729, 'Rgaa22-1004', '', '10.4', 729, 'http://rgaa.net/Presence-d-une-hierarchie-de.html', 4, 2, 23, 5),
(730, 'Rgaa22-1005', '', '10.5', 730, 'http://rgaa.net/Absence-de-simulation-visuelle-de.html', 4, 2, 23, 5),
(731, 'Rgaa22-1006', '', '10.6', 731, 'http://rgaa.net/Utilisation-systematique-de-listes.html', 4, 2, 23, 5),
(732, 'Rgaa22-1007', '', '10.7', 732, 'http://rgaa.net/Balisage-correct-des-listes-de.html', 4, 2, 23, 5),
(733, 'Rgaa22-1008', '', '10.8', 733, 'http://rgaa.net/Balisage-correct-des-citations.html', 4, 2, 23, 5),
(734, 'Rgaa22-1009', '', '10.9', 734, 'http://rgaa.net/Balisage-correct-des-abreviations.html', 6, 2, 23, 5),
(735, 'Rgaa22-1010', '', '10.10', 735, 'http://rgaa.net/Balisage-correct-des-acronymes.html', 6, 2, 23, 5),
(736, 'Rgaa22-1011', '', '10.11', 736, 'http://rgaa.net/Pertinence-de-la-version-non.html', 6, 2, 23, 5),
(737, 'Rgaa22-1012', '', '10.12', 737, 'http://rgaa.net/Pertinence-de-la-version-complete.html', 6, 2, 23, 5),
(738, 'Rgaa22-1013', '', '10.13', 738, 'http://rgaa.net/Accessibilite-des-documents.html', 4, 2, 23, 5),
(739, 'Rgaa22-1101', '', '11.1', 739, 'http://rgaa.net/Presence-des-balises-th-pour.html', 4, 2, 24, 5),
(740, 'Rgaa22-1102', '', '11.2', 740, 'http://rgaa.net/Presence-d-une-relation-entre-les.html', 4, 2, 24, 5),
(741, 'Rgaa22-1103', '', '11.3', 741, 'http://rgaa.net/Presence-d-une-relation-entre-les,170.html', 4, 2, 24, 5),
(742, 'Rgaa22-1104', '', '11.4', 742, 'http://rgaa.net/Absence-des-elements-propres-aux.html', 4, 2, 24, 5),
(743, 'Rgaa22-1105', '', '11.5', 743, 'http://rgaa.net/Absence-de-tableaux-de-donnees-ou.html', 4, 2, 24, 5),
(744, 'Rgaa22-1106', '', '11.6', 744, 'http://rgaa.net/Linearisation-correcte-des.html', 4, 2, 24, 5),
(745, 'Rgaa22-1107', '', '11.7', 745, 'http://rgaa.net/Presence-d-un-titre-pour-les.html', 4, 2, 24, 5),
(746, 'Rgaa22-1108', '', '11.8', 746, 'http://rgaa.net/Presence-d-un-resume-pour-les.html', 4, 2, 24, 5),
(747, 'Rgaa22-1109', '', '11.9', 747, 'http://rgaa.net/Pertinence-du-titre-du-tableau-de.html', 4, 2, 24, 5),
(748, 'Rgaa22-1110', '', '11.10', 748, 'http://rgaa.net/Pertinence-du-resume-du-tableau-de.html', 4, 2, 24, 5),
(749, 'Rgaa22-1201', '', '12.1', 749, 'http://rgaa.net/Presence-de-l-indication-des.html', 5, 2, 25, 5),
(750, 'Rgaa22-1202', '', '12.2', 750, 'http://rgaa.net/Presence-de-l-indication-des,179.html', 5, 2, 25, 5),
(751, 'Rgaa22-1203', '', '12.3', 751, 'http://rgaa.net/Equivalence-de-l-information-mise.html', 4, 2, 25, 5),
(752, 'Rgaa22-1204', '', '12.4', 752, 'http://rgaa.net/Presence-de-liens-ou-de.html', 6, 2, 25, 5),
(753, 'Rgaa22-1205', '', '12.5', 753, 'http://rgaa.net/Absence-de-syntaxes-cryptiques-par.html', 4, 2, 25, 5),
(754, 'Rgaa22-1206', '', '12.6', 754, 'http://rgaa.net/Presence-d-informations-sur-les.html', 6, 2, 25, 5),
(755, 'Rgaa22-1207', '', '12.7', 755, 'http://rgaa.net/Presence-d-un-moyen-de,184.html', 4, 2, 25, 5),
(756, 'Rgaa22-1208', '', '12.8', 756, 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,185.html', 4, 2, 25, 5),
(757, 'Rgaa22-1209', '', '12.9', 757, 'http://rgaa.net/Presence-d-un-autre-moyen-que-la,186.html', 4, 2, 25, 5),
(758, 'Rgaa22-1210', '', '12.10', 758, 'http://rgaa.net/Utilisation-d-un-style-de.html', 6, 2, 25, 5);


SET FOREIGN_KEY_CHECKS=1;

