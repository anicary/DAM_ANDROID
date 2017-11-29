
-- -----------------------------------------------------
-- Schema caroli41_dogs
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema caroli41_dogs
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `caroli41_dogs` DEFAULT CHARACTER SET utf8 ;
USE `caroli41_dogs` ;

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
-- Table `caroli41_dogs`.`tipo_mascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`tipo_mascota` (
  `idtipo_mascota` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`idtipo_mascota`))
 ;


-- -----------------------------------------------------
-- Table `caroli41_dogs`.`razamascota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `caroli41_dogs`.`razamascota` (
  `idrazamascota` INT NOT NULL AUTO_INCREMENT,
  `nombre_raza` VARCHAR(200) NULL,
  `tipo_mascota_idtipo_mascota` INT NOT NULL,
  PRIMARY KEY (`idrazamascota`),
  INDEX `fk_razamascota_tipo_mascota1_idx` (`tipo_mascota_idtipo_mascota` ASC),
  CONSTRAINT `fk_razamascota_tipo_mascota1`
    FOREIGN KEY (`tipo_mascota_idtipo_mascota`)
    REFERENCES `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
  `razamascota_idrazamascota` INT NOT NULL,
  PRIMARY KEY (`idmascota`),
  INDEX `fk_mascota_tipo_mascota1_idx` (`tipo_mascota_idtipo_mascota` ASC),
  INDEX `fk_mascota_razamascota1_idx` (`razamascota_idrazamascota` ASC),
  CONSTRAINT `fk_mascota_tipo_mascota1`
    FOREIGN KEY (`tipo_mascota_idtipo_mascota`)
    REFERENCES `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mascota_razamascota1`
    FOREIGN KEY (`razamascota_idrazamascota`)
    REFERENCES `caroli41_dogs`.`razamascota` (`idrazamascota`)
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
INSERT INTO `caroli41_dogs`.`usuarios` (`idusuarios`, `nombre`, `apellidos`, `correo`, `sexo`, `contrasena`, `tipo`, `activo`, `fecha_creacion`, `ultima_conexion`, `estado`, `municipio`) VALUES (1, 'Ana Carolina', 'Mondragon Rangel', 'ancamondragonra@ittepic.edu.mx', 'F', 'ae66099a971283caaccd860e2cfb50eab33b1ca1', 1, 1, '2017-11-28 12:37:21', '2017-11-28 12:37:21', 'Nayarit', 'Tepic');
INSERT INTO `caroli41_dogs`.`usuarios` (`idusuarios`, `nombre`, `apellidos`, `correo`, `sexo`, `contrasena`, `tipo`, `activo`, `fecha_creacion`, `ultima_conexion`, `estado`, `municipio`) VALUES (2, 'Zulma', 'Isable', 'zuiscoronello@ittepic.edu.mx', 'F', '8cb2237d0679ca88db6464eac60da96345513964', 1, 1, '2017-11-28 12:37:21', '2017-11-28 12:37:21', 'Nayarit', 'Tepic');

COMMIT;


-- -----------------------------------------------------
-- Data for table `caroli41_dogs`.`tipo_mascota`
-- -----------------------------------------------------
START TRANSACTION;
USE `caroli41_dogs`;
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (1, 'Perro');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (2, 'Gato');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (3, 'Cuyo');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (4, 'Hamster');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (5, 'Conejo');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (6, 'Mini Pig');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (7, 'Peces');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (8, 'Reptiles');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (9, 'Aragnidos');
INSERT INTO `caroli41_dogs`.`tipo_mascota` (`idtipo_mascota`, `nombre`) VALUES (10, 'Insectos');

COMMIT;


-- -----------------------------------------------------
-- Data for table `caroli41_dogs`.`razamascota`
-- -----------------------------------------------------
START TRANSACTION;
USE `caroli41_dogs`;
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (1, 'Corgi galés de Cardigan', 1);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (2, 'Husky siberiano', 1);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (3, 'Pastor alemán', 1);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (4, 'San bernardo', 1);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (5, 'Golden retriever', 1);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (6, 'Caniche', 1);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (7, 'Maine Coon', 2);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (8, 'Siberiano', 2);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (9, 'Bosque de Noruega', 2);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (10, 'Mau egipcio', 2);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (11, 'Chartreux', 2);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (12, 'Chartreux', 2);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (13, 'Cuyo abisinio', 3);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (14, 'Cobaya americana', 3);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (15, 'Hámsteres enanos de Roborovski', 4);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (16, 'Mini Lop', 5);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (17, 'Lionhead', 5);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (18, 'Juliana', 6);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (19, 'Göttingen', 6);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (20, 'Gekco', 8);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (21, 'Salamandra', 8);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (22, 'Peces Gato:', 7);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (23, 'Peces espiga', 7);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (24, 'Araña de rincón o violinista', 9);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (25, 'Araña Hobo', 9);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (26, 'Tarántula', 9);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (27, 'hormigas', 10);
INSERT INTO `caroli41_dogs`.`razamascota` (`idrazamascota`, `nombre_raza`, `tipo_mascota_idtipo_mascota`) VALUES (28, 'Hemidípteros', 10);

COMMIT;
