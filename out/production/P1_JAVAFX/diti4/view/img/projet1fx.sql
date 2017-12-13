
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


CREATE TABLE `agence_user_compte` (
  `idagence` int(11) NOT NULL,
  `iduser` int(11) NOT NULL,
  `idcompte` int(11) NOT NULL,
  `date_creation` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `noms` varchar(80) NOT NULL,
  `adresse` varchar(40) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `comptebanque` (
  `id` int(11) NOT NULL,
  `solde` double NOT NULL,
  `decouvert` double NOT NULL,
  `typecompte` varchar(10) NOT NULL,
  `idclient` int(11) NOT NULL,
  `tauxrenumeration` double NOT NULL,
  `numerocompte` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `noms` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `profil` varchar(255) DEFAULT NULL,
  `etat` int(11) NOT NULL DEFAULT '1',
  `id_agence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




ALTER TABLE `agences`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `agence_user_compte`
  ADD KEY `idagence` (`idagence`),
  ADD KEY `iduser` (`iduser`),
  ADD KEY `idcompte` (`idcompte`);


ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `comptebanque`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idclient` (`idclient`);


ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_agence` (`id_agence`);

ALTER TABLE `agences`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

ALTER TABLE `comptebanque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;


