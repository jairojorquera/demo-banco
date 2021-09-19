CREATE TABLE `sesiones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(500) NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rut_usuario` varchar(10) NOT NULL,
  `accion_fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `activa` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `usuario_sesion_idx` (`rut_usuario`),
  CONSTRAINT `usuario_sesion` FOREIGN KEY (`rut_usuario`) REFERENCES `usuarios` (`rut`)
) 