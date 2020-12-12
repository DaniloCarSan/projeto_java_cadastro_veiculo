-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 12-Out-2020 às 04:19
-- Versão do servidor: 10.4.14-MariaDB
-- versão do PHP: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `carros`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cars`
--

CREATE TABLE `cars` (
  `CAR_CODE` int(10) UNSIGNED NOT NULL,
  `CAR_YEAR` char(4) NOT NULL,
  `CAR_CHASSI` varchar(45) NOT NULL,
  `CAR_PLAQUE` char(20) NOT NULL,
  `CAR_MODEL_CODE` int(10) UNSIGNED NOT NULL,
  `CAR_MANUFACTURER_CODE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `cars`
--

INSERT INTO `cars` (`CAR_CODE`, `CAR_YEAR`, `CAR_CHASSI`, `CAR_PLAQUE`, `CAR_MODEL_CODE`, `CAR_MANUFACTURER_CODE`) VALUES
(18, '3030', '123456', 'QWE-4343', 2, 3),
(19, '2345', '1234567', 'QWE-2335', 1, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `manufacturers`
--

CREATE TABLE `manufacturers` (
  `MANUFACTURER_CODE` int(11) NOT NULL,
  `MANUFACTURER_NAME` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `manufacturers`
--

INSERT INTO `manufacturers` (`MANUFACTURER_CODE`, `MANUFACTURER_NAME`) VALUES
(1, 'Chevrolete'),
(3, 'Fiat'),
(2, 'Wolksvagem');

-- --------------------------------------------------------

--
-- Estrutura da tabela `models`
--

CREATE TABLE `models` (
  `MODEL_CODE` int(10) UNSIGNED NOT NULL,
  `MODEL_NAME` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `models`
--

INSERT INTO `models` (`MODEL_CODE`, `MODEL_NAME`) VALUES
(1, 'Golf'),
(2, 'Opala'),
(3, 'Estrada'),
(4, 'F-250');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`CAR_CODE`),
  ADD UNIQUE KEY `CARRO_CODIGO_UNIQUE` (`CAR_CODE`),
  ADD UNIQUE KEY `CARRO_CHASSI_UNIQUE` (`CAR_CHASSI`),
  ADD UNIQUE KEY `CARRO_PLACA_UNIQUE` (`CAR_PLAQUE`),
  ADD KEY `fk_carrros_modelo1_idx` (`CAR_MODEL_CODE`),
  ADD KEY `fk_carrros_fabricante1_idx` (`CAR_MANUFACTURER_CODE`);

--
-- Índices para tabela `manufacturers`
--
ALTER TABLE `manufacturers`
  ADD PRIMARY KEY (`MANUFACTURER_CODE`),
  ADD UNIQUE KEY `FABRICANTE_NOME_UNIQUE` (`MANUFACTURER_NAME`),
  ADD UNIQUE KEY `MANUFACTURER_CODE_UNIQUE` (`MANUFACTURER_CODE`);

--
-- Índices para tabela `models`
--
ALTER TABLE `models`
  ADD PRIMARY KEY (`MODEL_CODE`),
  ADD UNIQUE KEY `MODELO_CODIGO_UNIQUE` (`MODEL_CODE`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `cars`
--
ALTER TABLE `cars`
  MODIFY `CAR_CODE` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de tabela `manufacturers`
--
ALTER TABLE `manufacturers`
  MODIFY `MANUFACTURER_CODE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `models`
--
ALTER TABLE `models`
  MODIFY `MODEL_CODE` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `fk_carrros_fabricante1` FOREIGN KEY (`CAR_MANUFACTURER_CODE`) REFERENCES `manufacturers` (`MANUFACTURER_CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_carrros_modelo1` FOREIGN KEY (`CAR_MODEL_CODE`) REFERENCES `models` (`MODEL_CODE`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
