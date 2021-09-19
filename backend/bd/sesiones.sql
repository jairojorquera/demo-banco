CREATE TABLE `banco`.`sesiones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `token` VARCHAR(500) NOT NULL,
  `fecha` DATETIME NOT NULL DEFAULT  NOW(),
  `rut_usuario` VARCHAR(10) NOT NULL,
  `accion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `usuario_sesion_idx` (`rut_usuario` ASC) VISIBLE,
  CONSTRAINT `usuario_sesion`
    FOREIGN KEY (`rut_usuario`)
    REFERENCES `banco`.`usuarios` (`rut`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
