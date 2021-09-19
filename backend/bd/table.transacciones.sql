CREATE TABLE `transacciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `monto` int NOT NULL,
  `tipo` int NOT NULL COMMENT '0: Retiro\\\\n1:Deposito',
  `rut` varchar(10) NOT NULL,
  `rut_relacionado` varchar(10) DEFAULT NULL COMMENT 'Rut del recepto/enviador en caso que sea transferencia',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idtransaccion_UNIQUE` (`id`),
  KEY `rut_idx` (`rut`),
  CONSTRAINT `rut` FOREIGN KEY (`rut`) REFERENCES `usuarios` (`rut`)
) 