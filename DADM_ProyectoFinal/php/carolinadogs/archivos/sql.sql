

-- -----------------------------------------------------
-- Schema caroli41_dogs
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema caroli41_dogs
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Table `caroli41_dogs`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`usuarios` (
  `idusuarios` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `apellidos` VARCHAR(100) NULL,
  `correo` VARCHAR(100) NULL,
  `sexo` CHAR(2) NULL,
  `contrasena` VARCHAR(200) NULL,
  `tipo` INT NULL,
  `activo` INT NULL,
  `fecha_creacion` DATETIME NULL,
  `ultima_conexion` DATETIME NULL,
  `estado` VARCHAR(100) NULL,
  `municipio` VARCHAR(100) NULL,
  PRIMARY KEY (`idusuarios`))
 ;


-- -----------------------------------------------------
-- Table `caroli41_dogs`.`RazaMascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`RazaMascota` (
  `idRazaMascota` INT NOT NULL AUTO_INCREMENT,
  `tipo` CHAR(10) NULL,
  PRIMARY KEY (`idRazaMascota`))
 ;


-- -----------------------------------------------------
-- Table `caroli41_dogs`.`tipo_mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`tipo_mascota` (
  `idtipo_mascota` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idtipo_mascota`))
 ;


-- -----------------------------------------------------
-- Table `caroli41_dogs`.`mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`mascota` (
  `idmascota` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `sexo` VARCHAR(45) NULL,
  `edad` VARCHAR(45) NULL,
  `tipo_mascota_idtipo_mascota` INT NOT NULL,
  PRIMARY KEY (`idmascota`),
  INDEX `fk_mascota_tipo_mascota1_idx` (`tipo_mascota_idtipo_mascota` ASC),
  CONSTRAINT `fk_mascota_tipo_mascota1`
    FOREIGN KEY (`tipo_mascota_idtipo_mascota`)
    REFERENCES `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
 ;


-- -----------------------------------------------------
-- Table `caroli41_dogs`.`citasMedicas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`citasMedicas` (
  `idcitasMedicas` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idcitasMedicas`))
 ;


-- -----------------------------------------------------
-- Table `caroli41_dogs`.`cuidados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`cuidados` (
  `idcuidados` INT NOT NULL,
  `comidas` VARCHAR(45) NULL,
  `bebidas` VARCHAR(45) NULL,
  PRIMARY KEY (`idcuidados`))
 ;


-- -----------------------------------------------------
-- Table `caroli41_dogs`.`recordatorios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`recordatorios` (
  `idrecordatorios` INT NOT NULL,
  PRIMARY KEY (`idrecordatorios`))
 ;


-- -----------------------------------------------------
-- Table `caroli41_dogs`.`mascota_usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`mascota_usuarios` (
  `idmascota_usuarios` INT NOT NULL,
  `usuarios_idusuarios` INT NOT NULL,
  `mascota_idmascota` INT NOT NULL,
  `fecha_agregado` DATETIME NULL,
  PRIMARY KEY (`idmascota_usuarios`),
  INDEX `fk_mascota_usuarios_usuarios_idx` (`usuarios_idusuarios` ASC),
  INDEX `fk_mascota_usuarios_mascota1_idx` (`mascota_idmascota` ASC),
  CONSTRAINT `fk_mascota_usuarios_usuarios`
    FOREIGN KEY (`usuarios_idusuarios`)
    REFERENCES `caroli41_dogs`.`usuarios` (`idusuarios`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mascota_usuarios_mascota1`
    FOREIGN KEY (`mascota_idmascota`)
    REFERENCES `caroli41_dogs`.`mascota` (`idmascota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
 ;



-- -----------------------------------------------------
-- Data for table `caroli41_dogs`.`usuarios`
-- -----------------------------------------------------
START TRANSACTION;
USE `caroli41_dogs`;
INSERT INTO `caroli41_dogs`.`usuarios` (`idusuarios`, `nombre`, `apellidos`, `correo`, `sexo`, `contrasena`, `tipo`, `activo`, `fecha_creacion`, `ultima_conexion`, `estado`, `municipio`) VALUES (1, 'Ana Carolina', 'Mondragon Rangel', 'ancamondragonra@ittepic.edu.mx', '1', 'ae66099a971283caaccd860e2cfb50eab33b1ca1', 1, 1, NULL, NULL, 'Nayarit', 'Tepic');

COMMIT;
