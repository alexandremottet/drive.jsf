SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `drivejsf`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drivejsf`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(200) NULL,
  `email` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `drivejsf`.`blob`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drivejsf`.`blob` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hash` VARCHAR(200) NULL,
  `data` BLOB NULL,
  `counter` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `hash_UNIQUE` (`hash` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `drivejsf`.`document`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drivejsf`.`document` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `parentid` INT NULL,
  `ownerid` INT NULL,
  `blobid` INT NULL,
  `name` VARCHAR(200) NULL,
  `uri` TEXT NULL,
  PRIMARY KEY (`id`),
  INDEX `blobid_idx` (`blobid` ASC),
  INDEX `ownerid_idx` (`ownerid` ASC),
  INDEX `parentid_idx` (`parentid` ASC),
  CONSTRAINT `blobid`
    FOREIGN KEY (`blobid`)
    REFERENCES `drivejsf`.`blob` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `ownerid`
    FOREIGN KEY (`ownerid`)
    REFERENCES `drivejsf`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `parentid`
    FOREIGN KEY (`parentid`)
    REFERENCES `drivejsf`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `drivejsf`.`share`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `drivejsf`.`share` (
  `idshare` INT NOT NULL AUTO_INCREMENT,
  `docid` INT NULL,
  `userid` INT NULL,
  PRIMARY KEY (`idshare`),
  INDEX `userid_idx` (`userid` ASC),
  INDEX `docid_idx` (`docid` ASC),
  CONSTRAINT `userid`
    FOREIGN KEY (`userid`)
    REFERENCES `drivejsf`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `docid`
    FOREIGN KEY (`docid`)
    REFERENCES `drivejsf`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
