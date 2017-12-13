-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le :  mer. 13 déc. 2017 à 01:26
-- Version du serveur :  5.6.35
-- Version de PHP :  7.0.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet1fx`
--

-- --------------------------------------------------------

--
-- Structure de la table `agences`
--

CREATE TABLE `agences` (
  `id` int(11) NOT NULL,
  `code` int(11) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `agences`
--

INSERT INTO `agences` (`id`, `code`, `nom`, `tel`, `adresse`) VALUES
(1, 1, 'Agence Scat Urbam', '777293282', 'Dakar'),
(2, 2, 'Agence Bourguiba', '777293282', 'Dakar Parcel'),
(3, 3, 'Agence Koutal', '339876787', 'Koutal, kaolock'),
(4, 4, 'Agence Pikine', '33809098', 'Pikine'),
(5, 5, 'Agence Touba', '76098765', 'Touba'),
(6, 6, 'Agence KOUY', '7788654', 'Japon'),
(7, 7, 'Agence Louga', '339245436', 'Louba 1'),
(8, 8, 'Agence Laye', '771800510', 'PA'),
(9, 9, 'Agenge Tamba', '335654532', 'Dakar');

-- --------------------------------------------------------

--
-- Structure de la table `agence_user_compte`
--

CREATE TABLE `agence_user_compte` (
  `idagence` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idcompte` int(11) NOT NULL,
  `date_creation` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `noms` varchar(80) NOT NULL,
  `adresse` varchar(40) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `noms`, `adresse`, `tel`, `email`) VALUES
(1, 'Pape Ndour', 'Kaolack1', '777293282', 'pacodiazs1@hotmail.com'),
(2, 'Moussa Ndiaye', 'Koutal', '776545655', 'moussa1@hotmail.com'),
(5, 'Mamadou Cisse', 'Pikine 3', '774569876', 'mamadou@hotmail.com'),
(6, 'Adam Ndour', 'Keur Massar', '775679876', 'adamndour@hotmail.com'),
(7, 'Mignane Ndaiye', 'Scat Urbam', '775643234', 'mignane@hotmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `comptebanque`
--

CREATE TABLE `comptebanque` (
  `id` int(11) NOT NULL,
  `solde` double NOT NULL,
  `decouvert` double NOT NULL,
  `typecompte` varchar(10) NOT NULL,
  `idclient` int(11) NOT NULL,
  `tauxrenumeration` double NOT NULL,
  `numerocompte` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `comptebanque`
--

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `noms` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profil` varchar(255) DEFAULT NULL,
  `etat` int(11) NOT NULL DEFAULT '1',
  `id_agence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `noms`, `login`, `password`, `profil`, `etat`, `id_agence`) VALUES
(1, 'Modou Thiam', 'modou', '1122', NULL, 1, 2),
(2, 'Moussa Ndour', 'moussa', '1122', '', 0, 5),
(3, 'Modou Cisse', 'modoucisse', '1122', '', 1, 1),
(4, 'Pape Samba Ndour', 'admin', 'admin', 'Gestionnaire', 1, 5),
(5, 'PaMoussa Ndiaye', 'moussapa', '1122', 'Caissier', 1, 11),
(6, 'Ameth Mbodj', 'ameth', '1122', 'Chef d\'agence', 1, 13);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `agences`
--
ALTER TABLE `agences`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `agence_user_compte`
--
ALTER TABLE `agence_user_compte`
  ADD KEY `idagence` (`idagence`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idcompte` (`idcompte`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `comptebanque`
--
ALTER TABLE `comptebanque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idclient` (`idclient`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_agence` (`id_agence`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `agences`
--
ALTER TABLE `agences`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `comptebanque`
--
ALTER TABLE `comptebanque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `agence_user_compte`
--
ALTER TABLE `agence_user_compte`
  ADD CONSTRAINT `agence_user_compte_ibfk_1` FOREIGN KEY (`idcompte`) REFERENCES `comptebanque` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `agence_user_compte_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `agence_user_compte_ibfk_3` FOREIGN KEY (`idagence`) REFERENCES `agences` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `comptebanque`
--
ALTER TABLE `comptebanque`
  ADD CONSTRAINT `comptebanque_ibfk_1` FOREIGN KEY (`idclient`) REFERENCES `client` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_agence`) REFERENCES `agences` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
