-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 25-04-2025 a las 21:09:41
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_restaurante`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Categorias`
--

CREATE TABLE `Categorias` (
  `id_categoria` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `imagen` varchar(100) DEFAULT 'imagenes/categoria.png'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Categorias`
--

INSERT INTO `Categorias` (`id_categoria`, `nombre`, `descripcion`, `imagen`) VALUES
(1, ' Bebidas', 'fefefefef', 'imagenes/a37f7bc5-097d-4bb7-b1f6-98487cfd8570.jpeg'),
(11, 'Postres', 'as', 'imagenes/04d568da2f74985c50d3016b91592e40.jpg'),
(12, 'Ensaladas', 'asdd', 'imagenes/04d568da2f74985c50d3016b91592e40.jpg'),
(13, 'Especiales', 'sddddd', 'imagenes/04d568da2f74985c50d3016b91592e40.jpg'),
(14, 'Vinos', 'ererre', 'imagenes/thumb-1920-1371979.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Clientes`
--

CREATE TABLE `Clientes` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `telefono` char(12) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Clientes`
--

INSERT INTO `Clientes` (`id_cliente`, `nombre`, `direccion`, `telefono`, `email`) VALUES
(1, 'Samuel Taylor', '692 Lark Street', '838-620-2558', 'taylor2@yahoo.com'),
(2, 'Cheryl Stevens', '770 State Street', '838-776-7349', 'cheryls73@mail.com'),
(3, 'Albert Cole', '998 Rush Street', '312-576-6784', 'albert09@mail.com'),
(4, 'Gregory Griffin', '384 Wall Street', '213-516-5173', 'griffin72@icloud.com'),
(5, 'Shawn Ramirez', '490 Grape Street', '213-913-8552', 'rs8@icloud.com'),
(6, 'Ethel Long', '613 Ridgewood Road', '330-727-8690', 'ethell305@gmail.com'),
(7, 'Keith Morris', '688 Ridgewood Road', '330-920-2283', 'keimorris5@icloud.com'),
(8, 'Lucille Howard', '895 Flatbush Ave', '718-667-4869', 'howardluci6@gmail.com'),
(9, 'Christopher Rodriguez', '113 North Michigan Ave', '312-138-6810', 'rodriguezchristopher@mail.com'),
(10, 'Antonio Sullivan', '658 Grape Street', '213-665-2500', 'sulan@outlook.com'),
(11, 'Antonio Rose', '517 Flatbush Ave', '718-484-7954', 'antonioro825@gmail.com'),
(12, 'Jessica Castro', '733 Alameda Street', '213-834-1949', 'castrojessica@gmail.com'),
(13, 'Mildred Green', '557 Central Avenue', '838-178-1999', 'mildredgr@icloud.com'),
(14, 'Harold Sanders', '280 S Broadway', '213-229-1095', 'sandersharold3@outlook.com'),
(15, 'Florence Marshall', '683 State Street', '838-584-5593', 'florencemarshall@icloud.com'),
(16, 'Elizabeth Ryan', '73 Bank Street', '212-465-0187', 'elizabethryan@gmail.com'),
(17, 'Sean Young', '470 Rush Street', '312-726-1978', 'youngsean@gmail.com'),
(18, 'Dawn Collins', '768 Collier Road', '330-065-7177', 'dawncollins6@mail.com'),
(19, 'Andrew Rice', '214 West Market Street', '330-813-2917', 'arice55@outlook.com'),
(20, 'Janet Mendez', '380 Flatbush Ave', '718-402-1307', 'mendezj@gmail.com'),
(21, 'Travis Jimenez', '605 Rush Street', '312-255-0194', 'jiment1961@gmail.com'),
(22, 'Ruby Flores', '80 Broadway', '838-453-1262', 'rubyflores@hotmail.com'),
(23, 'Eric Ford', '814 Alameda Street', '213-292-9372', 'ericfo@icloud.com'),
(24, 'Cheryl Bailey', '682 Sky Way', '213-902-7244', 'bailey314@icloud.com'),
(25, 'Lucille Nguyen', '516 1st Ave', '718-105-0538', 'nguyen6@outlook.com'),
(26, 'Steve Murray', '925 Central Avenue', '838-583-5177', 'stevemurra@outlook.com'),
(27, 'Andrea Ford', '414 West Houston Street', '212-641-0310', 'fordandr@icloud.com'),
(28, 'Mark Henderson', '103 Fern Street', '330-476-6743', 'hmark@gmail.com'),
(29, 'Anita Green', '563 S Broadway', '213-293-6007', 'greena@hotmail.com'),
(30, 'Stephanie Bryant', '452 North Michigan Ave', '312-905-5983', 'bryastep@outlook.com'),
(31, 'Clifford Alexander', '24 Bank Street', '212-087-5579', 'clifforda8@outlook.com'),
(32, 'Randall Castillo', '156 Tremont Road', '614-834-3657', 'castillorandall@yahoo.com'),
(33, 'Donald Ramirez', '525 Sky Way', '213-426-2191', 'ramirezdonald@hotmail.com'),
(34, 'Jerry Bell', '643 Central Avenue', '838-321-1101', 'bejerr@yahoo.com'),
(35, 'Micheal Ellis', '325 East Alley', '614-553-1235', 'ellismich@icloud.com'),
(36, 'Richard Young', '874 Wicklow Road', '614-837-0561', 'richard96@gmail.com'),
(37, 'Philip Cooper', '459 Broadway', '838-040-7543', 'philipc1011@hotmail.com'),
(38, 'Francisco Brown', '806 1st Ave', '718-621-1294', 'bfrancisco@gmail.com'),
(39, 'Linda Henry', '565 Central Avenue', '838-663-8743', 'lindahenr@gmail.com'),
(40, 'Donald Kim', '803 Wooster Street', '212-564-5941', 'kdona89@icloud.com'),
(41, 'Sheila Bell', '46 Figueroa Street', '213-970-6417', 'bellsheila203@gmail.com'),
(42, 'Michelle Robertson', '560 Canal Street', '212-719-3850', 'robertson10@yahoo.com'),
(43, 'Justin Robinson', '214 Flatbush Ave', '718-665-5660', 'robijustin@icloud.com'),
(44, 'Ann Miller', '517 Nostrand Ave', '718-971-1096', 'man4@outlook.com'),
(45, 'Mike Washington', '758 East Cooke Road', '614-423-9282', 'mikewash@mail.com'),
(46, 'Pauline Hughes', '617 Pedway', '312-811-9976', 'paulihughes@yahoo.com'),
(47, 'Leonard Romero', '214 Figueroa Street', '213-202-2082', 'leonardrome@outlook.com'),
(48, 'Linda Jimenez', '218 Canal Street', '212-440-7571', 'lindaji@yahoo.com'),
(49, 'Monica Young', '845 S Broadway', '213-941-6885', 'monica5@outlook.com'),
(50, 'Eleanor Simmons', '511 Tremont Road', '614-835-5411', 'simmons15@gmail.com'),
(51, 'Glenn Russell', '44 S Broadway', '213-562-7291', 'glennru@outlook.com'),
(52, 'Ethel Patterson', '155 Diplomacy Drive', '614-235-5815', 'ethelpatte64@yahoo.com'),
(53, 'Debbie Palmer', '953 Alameda Street', '213-735-2230', 'debpalm@icloud.com'),
(54, 'Wayne Reynolds', '615 Central Avenue', '838-202-7836', 'reynolds4@mail.com'),
(55, 'Donna Washington', '95 Sky Way', '213-929-1940', 'dw203@gmail.com'),
(56, 'Jean Brooks', '662 East Cooke Road', '614-102-8126', 'brooksjea1961@icloud.com'),
(57, 'Troy Harrison', '409 Columbia St', '718-047-7246', 'harrisontroy@gmail.com'),
(58, 'Virginia Crawford', '946 Rush Street', '312-170-5475', 'crawford715@outlook.com'),
(59, 'Alfred Gibson', '65 Figueroa Street', '213-270-7105', 'alfrg@icloud.com'),
(60, 'Peter Gray', '107 State Street', '838-864-8036', 'gray312@gmail.com'),
(61, 'William Collins', '140 Nostrand Ave', '718-788-8605', 'cowilliam7@gmail.com'),
(62, 'Rose Brooks', '642 S Broadway', '213-629-5672', 'brooks80@outlook.com'),
(63, 'Tracy Powell', '10 Wicklow Road', '614-746-7335', 'tp51@icloud.com'),
(64, 'Troy Meyer', '406 Rush Street', '312-123-1111', 'troy5@gmail.com'),
(65, 'Troy Johnson', '12 Tremont Road', '614-227-1211', 'trjohnson@icloud.com'),
(66, 'Jean Cox', '821 West Houston Street', '212-577-2262', 'jecox@icloud.com'),
(67, 'Mario Anderson', '301 East Alley', '614-609-7644', 'marioa@gmail.com'),
(68, 'Bryan Hamilton', '82 Rush Street', '312-212-4204', 'bryanh1@icloud.com'),
(69, 'Dennis Hernandez', '88 East Cooke Road', '614-624-1985', 'hedennis@mail.com'),
(70, 'Heather Hughes', '658 East Cooke Road', '614-278-7565', 'heathughes705@icloud.com'),
(71, 'Andrew Rivera', '299 Pedway', '312-137-9423', 'andreri@outlook.com'),
(72, 'Leslie Ortiz', '405 Ridgewood Road', '330-657-1453', 'leslieortiz4@mail.com'),
(73, 'Steve Ward', '240 Wicklow Road', '614-043-8294', 'ward3@outlook.com'),
(74, 'David Ryan', '97 East Cooke Road', '614-183-9657', 'ryandavi913@outlook.com'),
(75, 'Randy Washington', '899 Alameda Street', '213-583-7440', 'randywashington@gmail.com'),
(76, 'Sarah Kim', '333 State Street', '838-025-4339', 'sarahk7@outlook.com'),
(77, 'Ashley Vasquez', '44 Rush Street', '312-875-2330', 'vasquashley@icloud.com'),
(78, 'Richard Cox', '151 Central Avenue', '838-757-1429', 'richardc@outlook.com'),
(79, 'Bryan Taylor', '429 North Michigan Ave', '312-925-1839', 'tabrya2001@yahoo.com'),
(80, 'David Nguyen', '7 Alameda Street', '213-965-2782', 'davidnguy1101@icloud.com'),
(81, 'Melvin Mitchell', '254 Riverview Road', '330-845-9281', 'melvinmi@gmail.com'),
(82, 'Alfred Boyd', '270 Central Avenue', '838-741-3763', 'aboy@outlook.com'),
(83, 'Ethel Rivera', '571 Central Avenue', '838-966-8522', 'ethelr@yahoo.com'),
(84, 'Justin Lewis', '304 Central Avenue', '838-253-3233', 'justinlewis@outlook.com'),
(85, 'Mark Rogers', '797 1st Ave', '718-707-4081', 'rogers5@gmail.com'),
(86, 'Nicole Bryant', '505 Ridgewood Road', '330-336-4221', 'bryant10@icloud.com'),
(87, 'Melvin Davis', '89 Pedway', '312-791-2273', 'md1996@icloud.com'),
(88, 'Kathleen Woods', '91 Pedway', '312-132-0664', 'woodskath@gmail.com'),
(89, 'Barbara Perez', '682 Alameda Street', '213-002-7465', 'barbaraperez611@hotmail.com'),
(90, 'Ann Lee', '778 Pedway', '312-512-0599', 'annle@hotmail.com'),
(91, 'Joan Gray', '703 Central Avenue', '838-658-9272', 'grayjoan3@gmail.com'),
(92, 'Bryan Murray', '677 West Houston Street', '212-093-0640', 'brym@icloud.com'),
(93, 'Bradley Clark', '643 Diplomacy Drive', '614-460-9111', 'clarkbradley@icloud.com'),
(94, 'Cynthia Howard', '773 1st Ave', '718-038-3448', 'howard716@gmail.com'),
(95, 'Pauline Simpson', '59 Canal Street', '212-102-5851', 'spauline@icloud.com'),
(96, 'Roy Davis', '78 West Market Street', '330-144-5877', 'davisroy10@icloud.com'),
(97, 'Wanda Ramos', '360 East Alley', '614-963-8929', 'rwa1@gmail.com'),
(98, 'Chad Rogers', '754 Riverview Road', '330-622-2696', 'chadro@hotmail.com'),
(99, 'Carolyn Tucker', '794 Central Avenue', '838-093-8166', 'tucarol9@icloud.com'),
(100, 'Johnny Nichols', '159 Ridgewood Road', '330-232-0003', 'johnnnicho1118@outlook.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DetOrden`
--

CREATE TABLE `DetOrden` (
  `id_detalle_orden` int(11) NOT NULL,
  `id_orden` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Disparadores `DetOrden`
--
DELIMITER $$
CREATE TRIGGER `CALCU_ORDENES_PRECIOS` AFTER INSERT ON `DetOrden` FOR EACH ROW BEGIN
    DECLARE total DECIMAL(10,2);
		
    SELECT SUM(p.precio * do.cantidad) INTO total
    FROM DetOrden do
    JOIN Productos p ON do.id_producto = p.id_producto
    WHERE do.id_orden = NEW.id_orden;
    
    UPDATE Ordenes
    SET total = total
    WHERE id_orden = NEW.id_orden;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DetProducto`
--

CREATE TABLE `DetProducto` (
  `id_detalle` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_insumo` int(11) NOT NULL,
  `cantidad_necesitada` int(11) NOT NULL,
  `fecha_caducidad` date DEFAULT NULL,
  `observaciones` text DEFAULT NULL,
  `descripcion` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Empleados`
--

CREATE TABLE `Empleados` (
  `id_empleado` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `curp` char(16) DEFAULT NULL,
  `rfc` char(10) DEFAULT NULL,
  `sueldo` decimal(10,3) DEFAULT NULL,
  `puesto` varchar(50) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `nss` varchar(13) DEFAULT NULL,
  `horario` varchar(100) DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `contrasena` varchar(32) NOT NULL DEFAULT 'admin123'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Empleados`
--

INSERT INTO `Empleados` (`id_empleado`, `nombre`, `curp`, `rfc`, `sueldo`, `puesto`, `telefono`, `nss`, `horario`, `fecha_ingreso`, `contrasena`) VALUES
(1, 'Julia Torres', 'ETTUJS9VQ0R0SPJX', '7TVGG2PE5F', 6565.897, ' Cocineroa ', '+86 769-129-3261', '5007736645', 'lunes - viernes de 00 a 00 horas', '2023-05-20', 'admin123'),
(2, 'Emma Lopez', 'SZWUOSLMPLVO9TPO', '6GA835QPE5', 6130.425, ' Sous Chef ', '+81 80-0975-1553', '9813778144', 'lunes - viernes de 00 a 00 horas', '2023-10-26', 'admin123'),
(3, 'Crystal Graham', 'OFWZGHOZW5IAYGMH', 'KATWTU6UN8', 5512.092, ' Encargado de Marketing', '+44 5322 719244', '3759583581', 'lunes - viernes de 00 a 00 horas', '2023-02-26', 'admin123'),
(4, 'Theresa Martinez', 'HNUN25NOIZXQXC2W', 'DJOVEKZIRZ', 4012.367, ' Host/Hostess Anfitrión/a ', '+81 66-435-5825', '9900190898', 'lunes - viernes de 00 a 00 horas', '2024-12-30', 'admin123'),
(5, 'Gloria Hernandez', 'FZPSGN25BD79O96Y', '7L3TSRZFJ6', 4850.425, ' Chef Ejecutivo ', '+81 66-833-0773', '7773801386', 'lunes - viernes de 00 a 00 horas', '2023-02-06', 'admin123'),
(6, 'Antonio Garza', 'XCDOKSBO4RYT7XPL', 'SD10S88AQU', 4988.789, ' Bartender ', '+44 (1865) 45 0629', '8553007904', 'lunes - viernes de 00 a 00 horas', '2024-11-29', 'admin123'),
(7, 'Raymond Roberts', 'RHOL5K87ITO91VSK', '7SVLK8VAVY', 5244.263, ' Sous Chef ', '+81 52-511-5599', '9432349297', 'lunes - viernes de 00 a 00 horas', '2024-04-19', 'admin123'),
(8, 'Bernard Baker', 'FZCSXK4OFWK5V5MJ', 'XOXFK3ZGI4', 4278.015, ' Camareroa ', '+1 718-043-4963', '5798814584', 'lunes - viernes de 00 a 00 horas', '2025-01-07', 'admin123'),
(9, 'Michael Patterson', 'PDQTFXP8NSUO6QMW', '4SLRKR60WW', 6282.547, ' Sumiller ', '+81 90-5352-6111', '6457345667', 'lunes - viernes de 00 a 00 horas', '2024-05-13', 'admin123'),
(10, 'Bryan Harrison', 'CBLI0DO1MOQZGM1B', 'IG0OF69DSD', 7471.352, ' Encargado de Compras o Almacén ', '+81 52-568-9626', '3870321244', 'lunes - viernes de 00 a 00 horas', '2025-02-09', 'admin123'),
(11, 'Charlotte Richardson', 'UBLOFARD003W6C4O', 'KI67PBGUQ2', 4215.209, ' Lavaplatos ', '+86 164-2032-7381', '2566678268', 'lunes - viernes de 00 a 00 horas', '2024-12-02', 'admin123'),
(12, 'Tracy Cooper', 'TEXZIAP9TH7YEPKI', '54A3IPE5CQ', 4508.482, ' Lavaplatos ', '+1 212-087-9388', '6213429620', 'lunes - viernes de 00 a 00 horas', '2024-01-03', 'admin123'),
(13, 'Pamela Mitchell', 'QNYK59OT3PVB2484', 'E3E9JW19DA', 6650.938, ' Sumiller ', '+81 11-362-1909', '7244589209', 'lunes - viernes de 00 a 00 horas', '2024-03-24', 'admin123'),
(14, 'Jamie Parker', 'HYVOFH8AEYI1H68Q', 'WVVNAP7PYN', 7366.519, ' Chef de Partie ', '+86 172-0510-3489', '4343077803', 'lunes - viernes de 00 a 00 horas', '2023-08-26', 'admin123'),
(15, 'Leroy Marshall', 'UFZOCZ51PN3MCG0F', 'A2GAVEM79S', 5383.860, ' Sumiller ', '+44 7971 497893', '5935174563', 'lunes - viernes de 00 a 00 horas', '2023-02-17', 'admin123'),
(16, 'Alexander Tran', 'XZAO84A7IWP866GP', '4OGOXIBAEH', 7036.085, ' Sumiller ', '+86 755-1876-6094', '5189641701', 'lunes - viernes de 00 a 00 horas', '2024-11-18', 'admin123'),
(17, 'Diane Stewart', 'HDFU42MBV0X2512B', 'BL3ZL7CU2Q', 6602.178, ' Bartender ', '+86 28-664-9809', '0410487712', 'lunes - viernes de 00 a 00 horas', '2023-03-13', 'admin123'),
(18, 'Julia Fisher', 'ARHVHMQ5OK2DZ0GJ', 'FCFCHWKMGJ', 5521.630, ' Lavaplatos ', '+1 838-857-1547', '7036936665', 'lunes - viernes de 00 a 00 horas', '2025-01-26', 'admin123'),
(19, 'Marilyn Gibson', 'PWBNXRRCVTVSHPWZ', '6AUPI1LM13', 7497.016, ' Cocineroa ', '+1 614-069-3026', '2057360387', 'lunes - viernes de 00 a 00 horas', '2023-03-30', 'admin123'),
(20, 'Esther Burns', 'HILQSKOPGTMW8HNO', 'BOF9QZ22IR', 4195.665, ' Encargado de Marketing', '+1 614-787-5669', '5380898374', 'lunes - viernes de 00 a 00 horas', '2024-08-29', 'admin123'),
(21, 'Michael Garcia', 'PRGOXNOMR9LQOTGD', 'SOQZJNLPDY', 5233.440, ' Host/Hostess Anfitrión/a ', '+86 191-4789-6385', '6144885768', 'lunes - viernes de 00 a 00 horas', '2023-11-14', 'admin123'),
(22, 'Rhonda Meyer', 'TVKC509RWGIT11F7', 'IG9A8U8MNU', 4584.369, ' Sous Chef ', '+44 5291 297582', '3222357762', 'lunes - viernes de 00 a 00 horas', '2024-03-17', 'admin123'),
(23, 'Sean Cook', 'SNUJ4EIOPCCUDUP6', 'B68KPND4RD', 6021.389, ' Pasteleroa ', '+1 213-497-0778', '1833945914', 'lunes - viernes de 00 a 00 horas', '2023-09-14', 'admin123'),
(24, 'Carolyn Rose', 'LRWRMW253PPQ3S7C', '63JV5V0XFZ', 5081.976, ' Encargado de Marketing', '+81 74-326-5231', '0145588803', 'lunes - viernes de 00 a 00 horas', '2023-10-27', 'admin123'),
(25, 'Joan Phillips', 'RDIM4AT5V465EV7F', 'ZZ64YASJ3H', 4205.388, ' Bartender ', '+81 90-3112-1129', '3797969363', 'lunes - viernes de 00 a 00 horas', '2023-11-25', 'admin123'),
(26, 'Theresa Gardner', 'ZRTR15BLV3ESZWZO', 'HYKOTA9XM4', 6591.416, ' Host/Hostess Anfitrión/a ', '+1 312-282-7945', '4137382228', 'lunes - viernes de 00 a 00 horas', '2024-12-11', 'admin123'),
(27, 'Diane Foster', 'ZTIZ8H50W4WP35UT', 'TSBE5TWKL2', 5642.998, ' Camareroa ', '+1 718-546-1120', '3799696589', 'lunes - viernes de 00 a 00 horas', '2024-09-09', 'admin123'),
(28, 'Mary Shaw', 'GQBYKV2QQBDM8TWX', 'TTJXXL9DQM', 4945.138, 'Gerente General ', '+86 136-4264-0644', '2582071883', 'lunes - viernes de 00 a 00 horas', '2023-10-23', 'admin123'),
(29, 'Tracy Alexander', 'IPFRSIJHN5PG1X6K', '4CSZ056ORH', 6369.183, ' Camareroa ', '+44 (121) 740 9290', '2550156548', 'lunes - viernes de 00 a 00 horas', '2023-11-07', 'admin123'),
(30, 'Juanita Sanders', 'QORNVKW9WGHE7PNT', '2P6YSO95RB', 7342.118, 'Gerente General ', '+86 165-7049-9754', '9331492932', 'lunes - viernes de 00 a 00 horas', '2023-02-25', 'admin123'),
(31, 'Roger Harrison', 'KPATL405EUVEGULO', 'NH1FNF1HOQ', 7838.851, ' Camareroa ', '+44 (20) 9092 9986', '4765178926', 'lunes - viernes de 00 a 00 horas', '2024-04-10', 'admin123'),
(32, 'Elizabeth Roberts', 'XWARSWW9QZMDD3UK', 'HZLLFH7FZ5', 5023.122, ' Chef Ejecutivo ', '+86 28-1179-7247', '7689434203', 'lunes - viernes de 00 a 00 horas', '2024-09-15', 'admin123'),
(33, 'Wendy Rivera', 'WICHXGJGCA090JMZ', 'F8P11MPNQP', 7348.063, ' Lavaplatos ', '+1 212-094-4009', '9789992967', 'lunes - viernes de 00 a 00 horas', '2023-10-15', 'admin123'),
(34, 'Francisco Adams', 'JTIONM5ALU68CATI', 'WUI2OV9EAX', 7506.854, ' Chef Ejecutivo ', '+1 718-000-8314', '3851116780', 'lunes - viernes de 00 a 00 horas', '2023-02-03', 'admin123'),
(35, 'Curtis Bailey', 'GWTZKNBJ7QA90XZ4', 'HUS0H71M7J', 7441.245, ' Lavaplatos ', '+44 (116) 192 1007', '0472385301', 'lunes - viernes de 00 a 00 horas', '2024-05-08', 'admin123'),
(36, 'Valerie Roberts', 'ZAWQ1V2YQ7630PTQ', '7BYKSVBS56', 5228.958, ' Gerente de Sala ', '+44 7853 345576', '9005295059', 'lunes - viernes de 00 a 00 horas', '2024-02-25', 'admin123'),
(37, 'Melissa Thomas', 'ZXUSE96I5PGJ3YJB', 'ZDEFUVIAK6', 4280.621, ' Encargado de Compras o Almacén ', '+81 74-871-5872', '9399592214', 'lunes - viernes de 00 a 00 horas', '2024-01-04', 'admin123'),
(38, 'Harold Graham', 'NNTVY50E90GU398V', '4Z5YA420J9', 4494.250, ' Encargado de Marketing', '+44 (1223) 62 1571', '9213237202', 'lunes - viernes de 00 a 00 horas', '2024-09-12', 'admin123'),
(39, 'Ray Ferguson', 'AIHQ0PNDDOKRYDK6', '2Q51TK9KGW', 6790.379, 'Gerente General ', '+1 614-572-1512', '5996628514', 'lunes - viernes de 00 a 00 horas', '2023-10-05', 'admin123'),
(40, 'Amy Gonzalez', 'LIJSBUVULKUQGLT4', 'NLRX59MGQJ', 4296.164, ' Ayudante de Cocina ', '+44 (151) 861 4523', '5753584681', 'lunes - viernes de 00 a 00 horas', '2024-11-21', 'admin123'),
(41, 'Brian James', 'GIFD81H3ZRDTZZTQ', 'FM8R9QEVHB', 7016.211, ' Lavaplatos ', '+1 614-430-3782', '8865931529', 'lunes - viernes de 00 a 00 horas', '2023-10-21', 'admin123'),
(42, 'Manuel Stone', 'TKMI98LX6HSJSYY6', '1XQLV5JJ9H', 6439.292, ' Ayudante de Cocina ', '+1 213-159-9307', '9619733266', 'lunes - viernes de 00 a 00 horas', '2023-06-28', 'admin123'),
(43, 'Jesse Ward', 'ETVAUU2K6YEJRHEY', '1GB75A2WJ0', 7662.468, ' Camareroa ', '+86 167-9664-1512', '9343821744', 'lunes - viernes de 00 a 00 horas', '2024-05-23', 'admin123'),
(44, 'Denise Rogers', 'NKJZV1HF00H90UI8', '0XFIQ7QRXR', 4857.678, ' Sous Chef ', '+81 80-6648-7978', '4521897858', 'lunes - viernes de 00 a 00 horas', '2024-07-18', 'admin123'),
(45, 'Ricky Walker', 'WTAC7T14CGVTK7IK', 'EZPWXOZVPQ', 4337.256, ' Chef de Partie ', '+44 5343 890350', '0199959375', 'lunes - viernes de 00 a 00 horas', '2023-10-30', 'admin123'),
(46, 'Tony Flores', 'TNRL7GZSBPPPXLKF', 'F9XQ0NK885', 4706.834, ' Ayudante de Cocina ', '+86 170-8819-2134', '0196318228', 'lunes - viernes de 00 a 00 horas', '2024-06-18', 'admin123'),
(47, 'Ruth Cook', 'KPFJYM93OX4DI95N', 'VXEL6WD50P', 5218.792, ' Sous Chef ', '+81 70-9670-4039', '2253543216', 'lunes - viernes de 00 a 00 horas', '2025-01-19', 'admin123'),
(48, 'Stephanie Gibson', 'VILI8P3Y8X3SHCPH', 'WYWHS9YNL0', 7598.953, 'Gerente General ', '+44 (20) 2659 8248', '7444822930', 'lunes - viernes de 00 a 00 horas', '2025-01-07', 'admin123'),
(49, 'Sarah Grant', 'FRFM7QKEX5PKPAEI', 'HG58348YZR', 5814.163, ' Pasteleroa ', '+81 80-4706-3776', '4920801740', 'lunes - viernes de 00 a 00 horas', '2024-05-30', 'admin123'),
(50, 'Elizabeth Evans', 'ZEJCZZ8RZR2T6NKX', 'L0WYRDY9XS', 6429.786, ' Ayudante de Cocina ', '+86 137-5549-3566', '8752841442', 'lunes - viernes de 00 a 00 horas', '2023-01-31', 'admin123'),
(51, 'Debra Thompson', 'DORSRNXJJR9K82FW', '6J4SRDVAN0', 6572.555, ' Pasteleroa ', '+1 838-090-7043', '5502082238', 'lunes - viernes de 00 a 00 horas', '2023-09-10', 'admin123'),
(52, 'Joseph Medina', 'NHOY6ZPUHP7Y8QXI', 'SSZUU1IFBF', 5433.523, ' Gerente de Sala ', '+1 838-294-4765', '5546740081', 'lunes - viernes de 00 a 00 horas', '2023-05-01', 'admin123'),
(53, 'Tammy Mills', 'DWOFFY7H9LA0LZKU', 'VS29IV1KY9', 4886.022, ' Encargado de Compras o Almacén ', '+44 (20) 0825 4665', '4674923866', 'lunes - viernes de 00 a 00 horas', '2023-05-05', 'admin123'),
(54, 'Judy Williams', 'EWKES7W2DDBUJI6U', 'WEF5D0AK4I', 4834.072, ' Ayudante de Cocina ', '+81 11-035-6660', '4669857511', 'lunes - viernes de 00 a 00 horas', '2023-12-07', 'admin123'),
(55, 'Ralph Gonzalez', 'NJHFZ2FV9RZMNO8G', 'V0INRDQFGC', 7876.775, ' Pasteleroa ', '+1 614-887-5370', '4846469558', 'lunes - viernes de 00 a 00 horas', '2024-11-03', 'admin123'),
(56, 'Shirley Weaver', 'ZELXO0OMWWUSRE1U', '6APCU7CF6V', 4318.859, ' Camareroa ', '+86 143-1884-7818', '6596176965', 'lunes - viernes de 00 a 00 horas', '2024-04-10', 'admin123'),
(57, 'Mark Nichols', 'IGZHRZYRBYBUSRR3', 'BPCJKVW3MZ', 5457.466, ' Pasteleroa ', '+1 838-099-5258', '7696779930', 'lunes - viernes de 00 a 00 horas', '2024-07-28', 'admin123'),
(58, 'Marcus Ford', 'ODXCMF2RABUJ1TKT', '6L86MPIZWK', 7600.034, ' Host/Hostess Anfitrión/a ', '+44 5398 384931', '0033514926', 'lunes - viernes de 00 a 00 horas', '2023-04-30', 'admin123'),
(59, 'Carlos Gray', 'HCOGVZDJGSILNIF8', 'N7RMWEGEYT', 7104.672, ' Chef Ejecutivo ', '+86 28-966-7101', '5044598478', 'lunes - viernes de 00 a 00 horas', '2023-03-21', 'admin123'),
(60, 'Ray Hughes', 'WMZQ0JNHPZVDJ2NI', '450II5P252', 5927.961, ' Host/Hostess Anfitrión/a ', '+44 (1865) 77 5305', '5676470496', 'lunes - viernes de 00 a 00 horas', '2023-02-27', 'admin123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Insumos`
--

CREATE TABLE `Insumos` (
  `id_insumo` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `descripcion` text NOT NULL,
  `observciones` text DEFAULT NULL,
  `id_proveedor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Mesas`
--

CREATE TABLE `Mesas` (
  `id_mesa` int(11) NOT NULL,
  `capacidad` int(11) NOT NULL,
  `tipo` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Mesas`
--

INSERT INTO `Mesas` (`id_mesa`, `capacidad`, `tipo`) VALUES
(1, 3, 'Premiere'),
(2, 5, 'Normal'),
(3, 3, 'Normal'),
(4, 13, 'Lujo'),
(5, 2, 'Basica'),
(6, 11, 'Lujo'),
(7, 20, 'Buffet');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Ordenes`
--

CREATE TABLE `Ordenes` (
  `id_orden` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `descripcion` text NOT NULL,
  `notas` varchar(50) DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_mesa` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Productos`
--

CREATE TABLE `Productos` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `id_categoria` int(11) NOT NULL,
  `imagen` varchar(100) DEFAULT 'imagenes/producto.png'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `Productos`
--

INSERT INTO `Productos` (`id_producto`, `nombre`, `precio`, `descripcion`, `id_categoria`, `imagen`) VALUES
(3, 'errere', 23.00, 'fegegee', 1, 'imagenes/a37f7bc5-097d-4bb7-b1f6-98487cfd8570.jpeg'),
(4, 'ffefe', 12.00, 'dwfw', 11, 'imagenes/f35a3a762b8661b3c7fd98e3e3cb29e513b845f5 (1).png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Proveedores`
--

CREATE TABLE `Proveedores` (
  `id_proveedor` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `nota` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Reservaciones`
--

CREATE TABLE `Reservaciones` (
  `id_reservacion` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ReservacionMesa`
--

CREATE TABLE `ReservacionMesa` (
  `id_rm` int(11) NOT NULL,
  `id_reservacion` int(11) NOT NULL,
  `id_mesa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Categorias`
--
ALTER TABLE `Categorias`
  ADD PRIMARY KEY (`id_categoria`);

--
-- Indices de la tabla `Clientes`
--
ALTER TABLE `Clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `DetOrden`
--
ALTER TABLE `DetOrden`
  ADD PRIMARY KEY (`id_detalle_orden`),
  ADD KEY `fk_orden_do` (`id_orden`),
  ADD KEY `fk_productos_do` (`id_producto`);

--
-- Indices de la tabla `DetProducto`
--
ALTER TABLE `DetProducto`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `fk_productos` (`id_producto`),
  ADD KEY `fk_insumos` (`id_insumo`);

--
-- Indices de la tabla `Empleados`
--
ALTER TABLE `Empleados`
  ADD PRIMARY KEY (`id_empleado`);

--
-- Indices de la tabla `Insumos`
--
ALTER TABLE `Insumos`
  ADD PRIMARY KEY (`id_insumo`),
  ADD KEY `fk_proveedores` (`id_proveedor`);

--
-- Indices de la tabla `Mesas`
--
ALTER TABLE `Mesas`
  ADD PRIMARY KEY (`id_mesa`);

--
-- Indices de la tabla `Ordenes`
--
ALTER TABLE `Ordenes`
  ADD PRIMARY KEY (`id_orden`),
  ADD KEY `fk_clientes_or` (`id_cliente`),
  ADD KEY `fk_mesas_or` (`id_mesa`),
  ADD KEY `fk_empleados_or` (`id_empleado`);

--
-- Indices de la tabla `Productos`
--
ALTER TABLE `Productos`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `fk_categorias` (`id_categoria`);

--
-- Indices de la tabla `Proveedores`
--
ALTER TABLE `Proveedores`
  ADD PRIMARY KEY (`id_proveedor`);

--
-- Indices de la tabla `Reservaciones`
--
ALTER TABLE `Reservaciones`
  ADD PRIMARY KEY (`id_reservacion`);

--
-- Indices de la tabla `ReservacionMesa`
--
ALTER TABLE `ReservacionMesa`
  ADD PRIMARY KEY (`id_rm`),
  ADD KEY `fk_resrvacion` (`id_reservacion`),
  ADD KEY `fk_mesas` (`id_mesa`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Categorias`
--
ALTER TABLE `Categorias`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `Clientes`
--
ALTER TABLE `Clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT de la tabla `DetOrden`
--
ALTER TABLE `DetOrden`
  MODIFY `id_detalle_orden` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `DetProducto`
--
ALTER TABLE `DetProducto`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Empleados`
--
ALTER TABLE `Empleados`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `Insumos`
--
ALTER TABLE `Insumos`
  MODIFY `id_insumo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Mesas`
--
ALTER TABLE `Mesas`
  MODIFY `id_mesa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `Ordenes`
--
ALTER TABLE `Ordenes`
  MODIFY `id_orden` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Productos`
--
ALTER TABLE `Productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `Proveedores`
--
ALTER TABLE `Proveedores`
  MODIFY `id_proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Reservaciones`
--
ALTER TABLE `Reservaciones`
  MODIFY `id_reservacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ReservacionMesa`
--
ALTER TABLE `ReservacionMesa`
  MODIFY `id_rm` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `DetOrden`
--
ALTER TABLE `DetOrden`
  ADD CONSTRAINT `fk_orden_do` FOREIGN KEY (`id_orden`) REFERENCES `Ordenes` (`id_orden`),
  ADD CONSTRAINT `fk_productos_do` FOREIGN KEY (`id_producto`) REFERENCES `Productos` (`id_producto`);

--
-- Filtros para la tabla `DetProducto`
--
ALTER TABLE `DetProducto`
  ADD CONSTRAINT `fk_insumos` FOREIGN KEY (`id_insumo`) REFERENCES `Insumos` (`id_insumo`),
  ADD CONSTRAINT `fk_productos` FOREIGN KEY (`id_producto`) REFERENCES `Productos` (`id_producto`);

--
-- Filtros para la tabla `Insumos`
--
ALTER TABLE `Insumos`
  ADD CONSTRAINT `fk_proveedores` FOREIGN KEY (`id_proveedor`) REFERENCES `Proveedores` (`id_proveedor`);

--
-- Filtros para la tabla `Ordenes`
--
ALTER TABLE `Ordenes`
  ADD CONSTRAINT `fk_clientes_or` FOREIGN KEY (`id_cliente`) REFERENCES `Clientes` (`id_cliente`),
  ADD CONSTRAINT `fk_empleados_or` FOREIGN KEY (`id_empleado`) REFERENCES `Empleados` (`id_empleado`),
  ADD CONSTRAINT `fk_mesas_or` FOREIGN KEY (`id_mesa`) REFERENCES `Mesas` (`id_mesa`);

--
-- Filtros para la tabla `Productos`
--
ALTER TABLE `Productos`
  ADD CONSTRAINT `fk_categorias` FOREIGN KEY (`id_categoria`) REFERENCES `Categorias` (`id_categoria`);

--
-- Filtros para la tabla `ReservacionMesa`
--
ALTER TABLE `ReservacionMesa`
  ADD CONSTRAINT `fk_mesas` FOREIGN KEY (`id_mesa`) REFERENCES `Mesas` (`id_mesa`),
  ADD CONSTRAINT `fk_resrvacion` FOREIGN KEY (`id_reservacion`) REFERENCES `Reservaciones` (`id_reservacion`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
