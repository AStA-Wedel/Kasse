SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `kasse` ;
CREATE SCHEMA IF NOT EXISTS `kasse` DEFAULT CHARACTER SET utf8 ;
USE `kasse` ;

-- -----------------------------------------------------
-- Table `kasse`.`tax_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`tax_category` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`tax_category` (
  `tax_category_name` CHAR(4) NOT NULL ,
  `tax_revision` SMALLINT UNSIGNED NOT NULL ,
  `tax_rate` FLOAT NULL ,
  PRIMARY KEY (`tax_category_name`, `tax_revision`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `tax_category_UNIQUE` ON `kasse`.`tax_category` (`tax_category_name` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`article` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`article` (
  `article_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `article_revision` SMALLINT UNSIGNED NOT NULL ,
  `article_name` VARCHAR(100) NOT NULL ,
  `price` INT UNSIGNED NOT NULL ,
  `tax_category_name` CHAR(4) NOT NULL ,
  `tax_revision` SMALLINT UNSIGNED NOT NULL ,
  `enabled` TINYINT(1) NOT NULL DEFAULT true ,
  PRIMARY KEY (`article_id`, `article_revision`) ,
  CONSTRAINT `fk_article_tax_category1`
    FOREIGN KEY (`tax_category_name` , `tax_revision` )
    REFERENCES `kasse`.`tax_category` (`tax_category_name` , `tax_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kasse`.`article` (`article_id` ASC) ;

CREATE INDEX `fk_article_tax_category1_idx` ON `kasse`.`article` (`tax_category_name` ASC, `tax_revision` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`lecturer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`lecturer` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`lecturer` (
  `lecturer_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `prename` VARCHAR(45) NULL ,
  `surname` VARCHAR(45) NULL ,
  `title` VARCHAR(20) NULL ,
  PRIMARY KEY (`lecturer_id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `lecturer_id_UNIQUE` ON `kasse`.`lecturer` (`lecturer_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`script`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`script` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`script` (
  `script_id` INT UNSIGNED NOT NULL ,
  `script_revision` SMALLINT UNSIGNED NOT NULL ,
  `lecturer_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`script_id`, `script_revision`) ,
  CONSTRAINT `fk_pk_script_id`
    FOREIGN KEY (`script_id` , `script_revision` )
    REFERENCES `kasse`.`article` (`article_id` , `article_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_script_lecturer1`
    FOREIGN KEY (`lecturer_id` )
    REFERENCES `kasse`.`lecturer` (`lecturer_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kasse`.`script` (`script_id` ASC) ;

CREATE INDEX `id_idx` ON `kasse`.`script` (`script_id` ASC, `script_revision` ASC) ;

CREATE INDEX `fk_script_lecturer1_idx` ON `kasse`.`script` (`lecturer_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`packet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`packet` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`packet` (
  `packet_id` INT UNSIGNED NOT NULL ,
  `packet_revision` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`packet_id`, `packet_revision`) ,
  CONSTRAINT `fk_pk_packet_id`
    FOREIGN KEY (`packet_id` , `packet_revision` )
    REFERENCES `kasse`.`article` (`article_id` , `article_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `id_idx` ON `kasse`.`packet` (`packet_id` ASC, `packet_revision` ASC) ;

CREATE UNIQUE INDEX `packet_id_UNIQUE` ON `kasse`.`packet` (`packet_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`stuff`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`stuff` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`stuff` (
  `stuff_id` INT UNSIGNED NOT NULL ,
  `stuff_revision` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`stuff_id`, `stuff_revision`) ,
  CONSTRAINT `fk_pk_stuff_id`
    FOREIGN KEY (`stuff_id` , `stuff_revision` )
    REFERENCES `kasse`.`article` (`article_id` , `article_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kasse`.`stuff` (`stuff_id` ASC) ;

CREATE INDEX `id_idx` ON `kasse`.`stuff` (`stuff_id` ASC, `stuff_revision` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`packet_part`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`packet_part` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`packet_part` (
  `packet_id` INT UNSIGNED NOT NULL ,
  `packet_revision` SMALLINT UNSIGNED NOT NULL ,
  `article_id` INT UNSIGNED NOT NULL ,
  `article_revision` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`packet_id`, `packet_revision`, `article_id`, `article_revision`) ,
  CONSTRAINT `fk_packet_has_article_packet1`
    FOREIGN KEY (`packet_id` , `packet_revision` )
    REFERENCES `kasse`.`packet` (`packet_id` , `packet_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_packet_has_article_article1`
    FOREIGN KEY (`article_id` , `article_revision` )
    REFERENCES `kasse`.`article` (`article_id` , `article_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_packet_has_article_article1_idx` ON `kasse`.`packet_part` (`article_id` ASC, `article_revision` ASC) ;

CREATE INDEX `fk_packet_has_article_packet1_idx` ON `kasse`.`packet_part` (`packet_id` ASC, `packet_revision` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`script_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`script_data` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`script_data` (
  `script_id` INT UNSIGNED NOT NULL ,
  `script_revision` SMALLINT UNSIGNED NOT NULL ,
  `file_number` TINYINT UNSIGNED NOT NULL ,
  `filename` VARCHAR(100) NULL ,
  `data` LONGBLOB NULL ,
  PRIMARY KEY (`script_id`, `script_revision`, `file_number`) ,
  CONSTRAINT `fk_script_data_script1`
    FOREIGN KEY (`script_id` , `script_revision` )
    REFERENCES `kasse`.`script` (`script_id` , `script_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_script_data_script1_idx` ON `kasse`.`script_data` (`script_id` ASC, `script_revision` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`category` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`category` (
  `category_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `category_name` VARCHAR(100) NULL ,
  PRIMARY KEY (`category_id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `category_id_UNIQUE` ON `kasse`.`category` (`category_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`category_mapping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`category_mapping` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`category_mapping` (
  `category_id` INT UNSIGNED NOT NULL ,
  `article_id` INT UNSIGNED NOT NULL ,
  `article_revision` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`category_id`, `article_id`, `article_revision`) ,
  CONSTRAINT `fk_category_has_article_category1`
    FOREIGN KEY (`category_id` )
    REFERENCES `kasse`.`category` (`category_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_category_has_article_article1`
    FOREIGN KEY (`article_id` , `article_revision` )
    REFERENCES `kasse`.`article` (`article_id` , `article_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_category_has_article_article1_idx` ON `kasse`.`category_mapping` (`article_id` ASC, `article_revision` ASC) ;

CREATE INDEX `fk_category_has_article_category1_idx` ON `kasse`.`category_mapping` (`category_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`study_program`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`study_program` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`study_program` (
  `program_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `program_name` VARCHAR(45) NOT NULL ,
  `degree` VARCHAR(45) NOT NULL ,
  `short_name` VARCHAR(10) NOT NULL ,
  PRIMARY KEY (`program_id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `program_id_UNIQUE` ON `kasse`.`study_program` (`program_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`study_program_script_mapping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`study_program_script_mapping` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`study_program_script_mapping` (
  `program_id` INT UNSIGNED NOT NULL ,
  `script_id` INT UNSIGNED NOT NULL ,
  `script_revision` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`program_id`, `script_id`, `script_revision`) ,
  CONSTRAINT `fk_study_program_has_script_study_program1`
    FOREIGN KEY (`program_id` )
    REFERENCES `kasse`.`study_program` (`program_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_study_program_has_script_script1`
    FOREIGN KEY (`script_id` , `script_revision` )
    REFERENCES `kasse`.`script` (`script_id` , `script_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_study_program_has_script_script1_idx` ON `kasse`.`study_program_script_mapping` (`script_id` ASC, `script_revision` ASC) ;

CREATE INDEX `fk_study_program_has_script_study_program1_idx` ON `kasse`.`study_program_script_mapping` (`program_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`person_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`person_group` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`person_group` (
  `group_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `bank_account_mandatory` TINYINT(1) NULL DEFAULT false ,
  `address_mandatory` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`group_id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `name_UNIQUE` ON `kasse`.`person_group` (`name` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`person` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`person` (
  `ldap_name` VARCHAR(20) NOT NULL ,
  `revision` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `surname` VARCHAR(45) NULL ,
  `prename` VARCHAR(45) NULL ,
  `matr_no` INT NULL ,
  `is_admin` TINYINT(1) NOT NULL DEFAULT false ,
  `group_id` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`revision`, `ldap_name`) ,
  CONSTRAINT `fk_person_person_group1`
    FOREIGN KEY (`group_id` )
    REFERENCES `kasse`.`person_group` (`group_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `ldap_name_UNIQUE` ON `kasse`.`person` (`ldap_name` ASC) ;

CREATE INDEX `fk_person_person_group1_idx` ON `kasse`.`person` (`group_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`bank_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`bank_account` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`bank_account` (
  `person_ldap_name` VARCHAR(20) NOT NULL ,
  `account_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `primary` TINYINT(1) NOT NULL DEFAULT false ,
  `account_owner` VARCHAR(100) NOT NULL ,
  `account_number` VARCHAR(45) NOT NULL ,
  `account_blz` VARCHAR(50) NOT NULL ,
  `deprecated` TINYINT(1) NULL DEFAULT false ,
  `date_of_creation` DATE NULL ,
  PRIMARY KEY (`account_id`, `person_ldap_name`) ,
  CONSTRAINT `fk_bank_account_person1`
    FOREIGN KEY (`person_ldap_name` )
    REFERENCES `kasse`.`person` (`ldap_name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bank_account_person1_idx` ON `kasse`.`bank_account` (`person_ldap_name` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`accounting_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`accounting_category` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`accounting_category` (
  `category_id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `category_name` VARCHAR(45) NULL ,
  PRIMARY KEY (`category_id`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `category_id_UNIQUE` ON `kasse`.`accounting_category` (`category_id` ASC) ;

CREATE UNIQUE INDEX `category_name_UNIQUE` ON `kasse`.`accounting_category` (`category_name` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`receipt`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`receipt` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`receipt` (
  `reciept_id` INT NOT NULL AUTO_INCREMENT ,
  `creation_datetime` DATETIME NOT NULL ,
  `cent_amount` INT NOT NULL ,
  `payed_cash` TINYINT(1) NOT NULL DEFAULT true ,
  `reason` TEXT NOT NULL ,
  `issuer_ldap_name` VARCHAR(20) NOT NULL ,
  `issuer_revision` SMALLINT UNSIGNED NOT NULL ,
  `category_id` INT UNSIGNED NOT NULL ,
  `cancelled` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`reciept_id`) ,
  CONSTRAINT `fk_reciept_person1`
    FOREIGN KEY (`issuer_ldap_name` , `issuer_revision` )
    REFERENCES `kasse`.`person` (`ldap_name` , `revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_receipt_accounting_category1`
    FOREIGN KEY (`category_id` )
    REFERENCES `kasse`.`accounting_category` (`category_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `reciept_id_UNIQUE` ON `kasse`.`receipt` (`reciept_id` ASC) ;

CREATE INDEX `fk_reciept_person1_idx` ON `kasse`.`receipt` (`issuer_ldap_name` ASC, `issuer_revision` ASC) ;

CREATE INDEX `fk_receipt_accounting_category1_idx` ON `kasse`.`receipt` (`category_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`shortsell`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`shortsell` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`shortsell` (
  `article_id` INT UNSIGNED NOT NULL ,
  `article_revision` SMALLINT UNSIGNED NOT NULL ,
  `date` DATE NOT NULL ,
  `category_id` INT UNSIGNED NOT NULL ,
  `count` INT UNSIGNED NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`article_id`, `article_revision`, `date`, `category_id`) ,
  CONSTRAINT `fk_shortsell_article1`
    FOREIGN KEY (`article_id` , `article_revision` )
    REFERENCES `kasse`.`article` (`article_id` , `article_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_shortsell_accounting_category1`
    FOREIGN KEY (`category_id` )
    REFERENCES `kasse`.`accounting_category` (`category_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_shortsell_article1_idx` ON `kasse`.`shortsell` (`article_id` ASC, `article_revision` ASC) ;

CREATE INDEX `fk_shortsell_accounting_category1_idx` ON `kasse`.`shortsell` (`category_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`bill_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`bill_order` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`bill_order` (
  `bill_id` INT UNSIGNED NOT NULL ,
  `receipient_revision` SMALLINT UNSIGNED NOT NULL ,
  `receiptpient_ldap_name` VARCHAR(20) NOT NULL ,
  `issuer_revision` SMALLINT UNSIGNED NOT NULL ,
  `issuer_ldap_name` VARCHAR(20) NOT NULL ,
  `datetime_of_creation` DATETIME NOT NULL ,
  `payed_cash` TINYINT(1) NOT NULL ,
  `state` ENUM('ordered','paid','fetched') NOT NULL DEFAULT 'ordered' ,
  `cancelled` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`bill_id`) ,
  CONSTRAINT `fk_bill_person1`
    FOREIGN KEY (`receipient_revision` , `receiptpient_ldap_name` )
    REFERENCES `kasse`.`person` (`revision` , `ldap_name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bill_person2`
    FOREIGN KEY (`issuer_revision` , `issuer_ldap_name` )
    REFERENCES `kasse`.`person` (`revision` , `ldap_name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `bill_id_UNIQUE` ON `kasse`.`bill_order` (`bill_id` ASC) ;

CREATE INDEX `fk_bill_person1_idx` ON `kasse`.`bill_order` (`receipient_revision` ASC, `receiptpient_ldap_name` ASC) ;

CREATE INDEX `fk_bill_person2_idx` ON `kasse`.`bill_order` (`issuer_revision` ASC, `issuer_ldap_name` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`bill_contains_article`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`bill_contains_article` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`bill_contains_article` (
  `bill_id` INT UNSIGNED NOT NULL ,
  `article_id` INT UNSIGNED NOT NULL ,
  `article_revision` SMALLINT UNSIGNED NOT NULL ,
  `count` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `printed` TINYINT(1) NOT NULL DEFAULT false ,
  PRIMARY KEY (`bill_id`, `article_id`, `article_revision`) ,
  CONSTRAINT `fk_bill_has_article_bill1`
    FOREIGN KEY (`bill_id` )
    REFERENCES `kasse`.`bill_order` (`bill_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bill_has_article_article1`
    FOREIGN KEY (`article_id` , `article_revision` )
    REFERENCES `kasse`.`article` (`article_id` , `article_revision` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_bill_has_article_article1_idx` ON `kasse`.`bill_contains_article` (`article_id` ASC, `article_revision` ASC) ;

CREATE INDEX `fk_bill_has_article_bill1_idx` ON `kasse`.`bill_contains_article` (`bill_id` ASC) ;


-- -----------------------------------------------------
-- Table `kasse`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kasse`.`address` ;

CREATE  TABLE IF NOT EXISTS `kasse`.`address` (
  `owner_ldap_name` VARCHAR(20) NOT NULL ,
  `owner_revision` SMALLINT UNSIGNED NOT NULL ,
  `street` VARCHAR(100) NOT NULL ,
  `zipcode` CHAR(5) NOT NULL ,
  `town` VARCHAR(100) NOT NULL ,
  `streetnumber` CHAR(10) NOT NULL ,
  PRIMARY KEY (`owner_ldap_name`, `owner_revision`) ,
  CONSTRAINT `fk_address_person1`
    FOREIGN KEY (`owner_revision` , `owner_ldap_name` )
    REFERENCES `kasse`.`person` (`revision` , `ldap_name` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `person_ldap_name_UNIQUE` ON `kasse`.`address` (`owner_ldap_name` ASC) ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
