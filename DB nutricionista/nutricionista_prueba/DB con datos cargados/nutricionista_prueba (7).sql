-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-10-2023 a las 03:56:48
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `nutricionista_prueba`
--
CREATE DATABASE IF NOT EXISTS `nutricionista_prueba` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `nutricionista_prueba`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comida`
--

CREATE TABLE `comida` (
  `idComida` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `detalle` varchar(100) NOT NULL,
  `cantCalorias` int(11) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `comida`
--

INSERT INTO `comida` (`idComida`, `nombre`, `detalle`, `cantCalorias`, `estado`) VALUES
(1, 'Pechuga', 'Al horno', 401, 1),
(2, 'Ensalada Natural', 'Sin sal y aceite', 50, 1),
(3, 'Carne con Papas', 'Hervido', 800, 1),
(4, 'Pechuga de Pavo', 'Al horno', 123, 1),
(5, 'Milanesa', 'Frita', 450, 0),
(6, 'Pure de calabazas', 'Hervido con Jugo de pollo', 500, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dieta`
--

CREATE TABLE `dieta` (
  `idDieta` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `fechaInicial` date NOT NULL,
  `pesoInicial` double NOT NULL,
  `fechaFinal` date NOT NULL,
  `pesoFinal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dieta`
--

INSERT INTO `dieta` (`idDieta`, `nombre`, `idPaciente`, `fechaInicial`, `pesoInicial`, `fechaFinal`, `pesoFinal`) VALUES
(1, 'Mediterranea', 1, '2022-10-07', 65.5, '2023-01-07', 75.5),
(2, 'Vegetariana', 2, '2022-01-10', 82, '2022-05-10', 74),
(18, 'Paleo', 1, '2023-01-08', 70, '2023-08-07', 76),
(22, 'Paleo', 1, '2023-08-09', 72, '2024-08-10', 82),
(24, 'Paleo', 1, '2025-08-17', 75, '2034-08-10', 82),
(49, 'Detox', 2, '2022-10-12', 82.3, '2023-01-09', 74.3),
(53, 'Detox', 2, '2023-01-10', 82.3, '2023-02-11', 74.3),
(54, 'Detox', 2, '2023-02-12', 82.3, '2023-03-11', 74.3),
(56, 'Detox', 2, '2023-03-22', 82.3, '2023-04-03', 74.3),
(57, 'Detox', 2, '2023-12-02', 82.3, '2023-12-30', 74.3),
(59, 'Detox', 2, '2023-12-31', 82.3, '2024-01-12', 74.3),
(60, 'Detox', 2, '2024-01-13', 82.3, '2025-01-12', 74.3),
(61, 'Proteica', 4, '2023-10-12', 90, '2023-12-08', 80),
(62, 'Proteica', 4, '2023-12-09', 81, '2024-04-17', 73),
(63, 'Hipocalórica', 5, '2022-09-21', 65, '2023-03-23', 70),
(64, 'Mediterranea', 7, '2023-10-28', 40, '2026-10-28', 70),
(65, 'Vegetariana', 12, '2023-10-28', 35, '2025-10-19', 45),
(66, 'DETOX', 13, '2023-10-29', 67, '2023-10-29', 67.3),
(67, 'Hipocalorica', 5, '2023-04-10', 71, '2023-09-20', 78),
(69, 'MEDITERRANEA', 11, '2023-04-14', 60, '2023-09-25', 70),
(70, 'KETO', 9, '2023-03-23', 54, '2023-08-28', 61),
(71, 'KETO', 8, '2023-01-10', 90, '2023-05-10', 80);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dieta_comida`
--

CREATE TABLE `dieta_comida` (
  `idDietaComida` int(11) NOT NULL,
  `idComida` int(11) NOT NULL,
  `idDieta` int(11) NOT NULL,
  `horario` enum('DESAYUNO','ALMUERZO','MERIENDA','CENA','SNACK') NOT NULL,
  `porcion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `dieta_comida`
--

INSERT INTO `dieta_comida` (`idDietaComida`, `idComida`, `idDieta`, `horario`, `porcion`) VALUES
(1, 1, 1, 'ALMUERZO', 300),
(2, 2, 60, 'DESAYUNO', 200),
(4, 3, 62, 'ALMUERZO', 500),
(7, 3, 60, 'ALMUERZO', 350);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_peso`
--

CREATE TABLE `historial_peso` (
  `idHistorialPeso` int(11) NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `peso` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `historial_peso`
--

INSERT INTO `historial_peso` (`idHistorialPeso`, `idPaciente`, `fecha`, `peso`) VALUES
(1, 1, '2023-10-04', 71.5),
(2, 1, '2023-10-03', 70),
(3, 2, '2023-10-05', 80),
(4, 1, '2022-04-17', 58.8),
(5, 2, '2023-09-07', 78),
(6, 2, '2023-10-07', 82),
(7, 4, '2023-10-07', 84),
(8, 5, '2022-09-21', 65),
(9, 5, '2023-01-21', 67),
(10, 5, '2023-03-23', 68.5),
(11, 5, '2023-08-29', 78),
(12, 7, '2023-10-28', 40),
(13, 12, '2023-10-28', 35),
(14, 13, '2023-10-29', 67),
(15, 11, '2023-04-14', 60),
(16, 11, '2023-05-14', 62),
(17, 11, '2023-05-24', 62.7),
(18, 9, '2023-03-23', 54),
(19, 8, '2023-01-10', 90);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `idPaciente` int(11) NOT NULL,
  `dni` int(11) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `domicilio` varchar(100) NOT NULL,
  `telefono` varchar(100) NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`idPaciente`, `dni`, `apellido`, `nombre`, `domicilio`, `telefono`, `estado`) VALUES
(1, 20333444, 'Giraldo', 'Romualdo', 'Calle 321', '11 30032002', 1),
(2, 40666777, 'Lang', 'Rodrigo', 'Calle Baja 321', '11 50054044', 1),
(4, 70888999, 'Moicano', 'Sergio', 'Calle Pobre 123', '11 90092002', 1),
(5, 12555777, 'Lata', 'Ezequiel', 'Calle Cien 123', '11 3456 3455', 1),
(6, 20333555, 'Centurion', 'Ariel', 'Calle Brava 123', '11 99998888', 1),
(7, 12345678, 'Romero', 'Sergio', 'Calle Central Diagonal 1234', '11 9999 8888', 1),
(8, 87654321, 'Bucachi', 'Leopoldo', 'Callle Cuadrada 1234', '11 7777 6666', 1),
(9, 33444555, 'Sosa', 'Abril', 'Calle Cerca 123', '11 9009 3003', 1),
(10, 21333111, 'Paolo', 'Pablo', 'Calle Street Húgo 123', '11 3003 2017', 0),
(11, 33444777, 'Marci', 'Javier', 'Calle 888', '11 3003 2004', 1),
(12, 20444555, 'Sosa', 'Agustín', 'Calle 1234', '11 2222 3333', 1),
(13, 22222223, 'Roma', 'Ricardo', 'Calle Rotulo 3333', '11 2323 2323', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comida`
--
ALTER TABLE `comida`
  ADD PRIMARY KEY (`idComida`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `dieta`
--
ALTER TABLE `dieta`
  ADD PRIMARY KEY (`idDieta`),
  ADD UNIQUE KEY `idPaciente` (`idPaciente`,`fechaInicial`);

--
-- Indices de la tabla `dieta_comida`
--
ALTER TABLE `dieta_comida`
  ADD PRIMARY KEY (`idDietaComida`),
  ADD UNIQUE KEY `idComida` (`idComida`,`idDieta`,`horario`),
  ADD KEY `idDieta` (`idDieta`);

--
-- Indices de la tabla `historial_peso`
--
ALTER TABLE `historial_peso`
  ADD PRIMARY KEY (`idHistorialPeso`),
  ADD UNIQUE KEY `idPaciente` (`idPaciente`,`fecha`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`idPaciente`),
  ADD UNIQUE KEY `dni` (`dni`),
  ADD UNIQUE KEY `telefono` (`telefono`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comida`
--
ALTER TABLE `comida`
  MODIFY `idComida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `dieta`
--
ALTER TABLE `dieta`
  MODIFY `idDieta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT de la tabla `dieta_comida`
--
ALTER TABLE `dieta_comida`
  MODIFY `idDietaComida` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `historial_peso`
--
ALTER TABLE `historial_peso`
  MODIFY `idHistorialPeso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `idPaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `dieta`
--
ALTER TABLE `dieta`
  ADD CONSTRAINT `dieta_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`);

--
-- Filtros para la tabla `dieta_comida`
--
ALTER TABLE `dieta_comida`
  ADD CONSTRAINT `dieta_comida_ibfk_1` FOREIGN KEY (`idComida`) REFERENCES `comida` (`idComida`),
  ADD CONSTRAINT `dieta_comida_ibfk_2` FOREIGN KEY (`idDieta`) REFERENCES `dieta` (`idDieta`);

--
-- Filtros para la tabla `historial_peso`
--
ALTER TABLE `historial_peso`
  ADD CONSTRAINT `historial_peso_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `paciente` (`idPaciente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
