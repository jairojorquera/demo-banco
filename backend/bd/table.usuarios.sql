CREATE TABLE `usuarios` (
  `rut` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `saldo` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`rut`)
) 