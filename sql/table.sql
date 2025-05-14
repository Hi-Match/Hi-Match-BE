-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema thanksyouplz
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema thanksyouplz
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `thanksyouplz` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `thanksyouplz` ;

-- -----------------------------------------------------
-- Table `thanksyouplz`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`company` (
  `company_no` BIGINT NOT NULL AUTO_INCREMENT,
  `company_address` VARCHAR(255) NULL DEFAULT NULL,
  `company_code` VARCHAR(30) NULL DEFAULT NULL,
  `company_create` DATETIME(6) NOT NULL,
  `company_description` TINYTEXT NULL DEFAULT NULL,
  `company_employee` VARCHAR(50) NULL DEFAULT NULL,
  `company_id` VARCHAR(100) NOT NULL,
  `company_imga` VARCHAR(1000) NULL DEFAULT NULL,
  `company_imgb` VARCHAR(1000) NULL DEFAULT NULL,
  `company_imgc` VARCHAR(1000) NULL DEFAULT NULL,
  `company_industry` VARCHAR(30) NULL DEFAULT NULL,
  `company_license` VARCHAR(100) NULL DEFAULT NULL,
  `company_logo` VARCHAR(1000) NULL DEFAULT NULL,
  `company_mail` VARCHAR(30) NOT NULL,
  `company_manager_name` VARCHAR(100) NULL DEFAULT NULL,
  `company_name` VARCHAR(100) NOT NULL,
  `company_pw` VARCHAR(255) NOT NULL,
  `company_phone` VARCHAR(20) NULL DEFAULT NULL,
  `company_refresh_token` VARCHAR(2000) NULL DEFAULT NULL,
  `company_url` VARCHAR(500) NULL DEFAULT NULL,
  `company_update` DATETIME(6) NULL DEFAULT NULL,
  PRIMARY KEY (`company_no`),
  UNIQUE INDEX `UKq79ooswnnmx4kafhpltwr6441` (`company_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1547
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`job_posting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`job_posting` (
  `posting_no` BIGINT NOT NULL AUTO_INCREMENT,
  `posting_content` LONGTEXT NULL DEFAULT NULL,
  `posting_create` DATETIME(6) NULL DEFAULT NULL,
  `posting_deadline` DATETIME(6) NULL DEFAULT NULL,
  `posting_education` VARCHAR(50) NULL DEFAULT NULL,
  `posting_experience` VARCHAR(50) NULL DEFAULT NULL,
  `posting_is_finish` BIT(1) NULL DEFAULT NULL,
  `posting_location` VARCHAR(100) NULL DEFAULT NULL,
  `posting_part` VARCHAR(20) NULL DEFAULT NULL,
  `posting_sal` VARCHAR(100) NULL DEFAULT NULL,
  `posting_title` VARCHAR(255) NULL DEFAULT NULL,
  `posting_type` VARCHAR(100) NULL DEFAULT NULL,
  `posting_update` DATETIME(6) NULL DEFAULT NULL,
  `posting_work_end_time` TIME NULL DEFAULT NULL,
  `posting_work_start_time` TIME NULL DEFAULT NULL,
  `posting_work_type` VARCHAR(100) NULL DEFAULT NULL,
  `company_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`posting_no`),
  INDEX `FKjmgxh4ukcg0wk1sxp4djkcc8s` (`company_no` ASC) VISIBLE,
  CONSTRAINT `FKjmgxh4ukcg0wk1sxp4djkcc8s`
    FOREIGN KEY (`company_no`)
    REFERENCES `thanksyouplz`.`company` (`company_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`member` (
  `member_no` BIGINT NOT NULL AUTO_INCREMENT,
  `member_code` VARCHAR(20) NULL DEFAULT NULL,
  `member_code_rate` VARCHAR(1000) NULL DEFAULT NULL,
  `member_code_time` DATETIME(6) NULL DEFAULT NULL,
  `member_company_address` VARCHAR(200) NULL DEFAULT NULL,
  `member_company_contract` VARCHAR(20) NULL DEFAULT NULL,
  `member_company_part` VARCHAR(200) NULL DEFAULT NULL,
  `member_description` LONGTEXT NULL DEFAULT NULL,
  `member_file` VARCHAR(3000) NULL DEFAULT NULL,
  `member_id` VARCHAR(100) NULL DEFAULT NULL,
  `member_join_date` DATETIME(6) NOT NULL,
  `member_mail` VARCHAR(100) NOT NULL,
  `member_name` VARCHAR(100) NOT NULL,
  `member_nickname` VARCHAR(100) NOT NULL,
  `member_pass` VARCHAR(2000) NOT NULL,
  `member_phone` VARCHAR(100) NOT NULL,
  `member_random` VARCHAR(100) NOT NULL,
  `member_refresh_token` VARCHAR(2000) NULL DEFAULT NULL,
  `member_social_id` VARCHAR(200) NULL DEFAULT NULL,
  `member_suitability` VARCHAR(20) NULL DEFAULT NULL,
  `member_test_date` DATETIME(6) NULL DEFAULT NULL,
  `member_update` DATETIME(6) NULL DEFAULT NULL,
  `member_social_type` ENUM('GOOGLE', 'KAKAO', 'NAVER', 'NORMAL') NULL DEFAULT NULL,
  PRIMARY KEY (`member_no`),
  UNIQUE INDEX `UK4rw879c4q7wrgi3v64cy73b17` (`member_id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`application` (
  `application_no` BIGINT NOT NULL AUTO_INCREMENT,
  `application_address` VARCHAR(200) NULL DEFAULT NULL,
  `application_alarm` BIT(1) NULL DEFAULT NULL,
  `application_ambition` VARCHAR(255) NULL DEFAULT NULL,
  `application_army_date` DATETIME(6) NULL DEFAULT NULL,
  `application_army_end` DATETIME(6) NULL DEFAULT NULL,
  `application_army_part` ENUM('AIR_FORCE', 'ARMY', 'COAST_GUARD', 'FIRE_SERVICE', 'KATUSA', 'MARINES', 'NAVY', 'OTHER', 'POLICE_SERVICE', 'PUBLIC_SERVICE', 'RIOT_POLICE', 'TECH_SERVICE') NULL DEFAULT NULL,
  `application_army_type` ENUM('APPLICABLE', 'EXEMPT', 'NON_APPLICABLE') NULL DEFAULT NULL,
  `application_birthday` VARCHAR(200) NULL DEFAULT NULL,
  `application_create` DATETIME(6) NULL DEFAULT NULL,
  `application_date` DATETIME(6) NULL DEFAULT NULL,
  `application_disability` VARCHAR(100) NULL DEFAULT NULL,
  `application_disability_type` VARCHAR(30) NULL DEFAULT NULL,
  `application_end_date` DATETIME(6) NULL DEFAULT NULL,
  `application_engname` VARCHAR(30) NULL DEFAULT NULL,
  `application_gender` ENUM('F', 'M') NULL DEFAULT NULL,
  `application_grade` INT NULL DEFAULT NULL,
  `application_img` VARCHAR(2000) NULL DEFAULT NULL,
  `application_mail` VARCHAR(255) NULL DEFAULT NULL,
  `application_name` VARCHAR(50) NOT NULL,
  `application_pf` BIT(1) NULL DEFAULT NULL,
  `application_portfolio` VARCHAR(2000) NULL DEFAULT NULL,
  `application_result` BIT(1) NULL DEFAULT NULL,
  `application_rewarding_patriotism` VARCHAR(20) NULL DEFAULT NULL,
  `application_status` ENUM('FAIL', 'FINAL_PASS', 'PROGRESS', 'RESUME_PASS', 'SUBMIT', 'TOTAL') NULL DEFAULT NULL,
  `application_tel` VARCHAR(30) NULL DEFAULT NULL,
  `application_title` VARCHAR(255) NOT NULL,
  `application_update` DATETIME(6) NULL DEFAULT NULL,
  `member_no` BIGINT NULL DEFAULT NULL,
  `posting_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`application_no`),
  INDEX `FKmyi3ykxbbevrk98rh1gnlqmr7` (`member_no` ASC) VISIBLE,
  INDEX `FKlsh6tfhhtojjhex5lcptbak2l` (`posting_no` ASC) VISIBLE,
  CONSTRAINT `FKlsh6tfhhtojjhex5lcptbak2l`
    FOREIGN KEY (`posting_no`)
    REFERENCES `thanksyouplz`.`job_posting` (`posting_no`),
  CONSTRAINT `FKmyi3ykxbbevrk98rh1gnlqmr7`
    FOREIGN KEY (`member_no`)
    REFERENCES `thanksyouplz`.`member` (`member_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`application_award`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`application_award` (
  `a_awa_no` BIGINT NOT NULL AUTO_INCREMENT,
  `a_awa_competition_name` VARCHAR(50) NULL DEFAULT NULL,
  `a_awa_content` VARCHAR(255) NULL DEFAULT NULL,
  `a_awa_date` DATETIME(6) NULL DEFAULT NULL,
  `a_awa_organ` VARCHAR(50) NULL DEFAULT NULL,
  `a_awa_title` VARCHAR(50) NOT NULL,
  `application_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`a_awa_no`),
  INDEX `FKn9aqneo727qi976yx2efgbbdp` (`application_no` ASC) VISIBLE,
  CONSTRAINT `FKn9aqneo727qi976yx2efgbbdp`
    FOREIGN KEY (`application_no`)
    REFERENCES `thanksyouplz`.`application` (`application_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`application_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`application_certificate` (
  `a_cer_no` BIGINT NOT NULL AUTO_INCREMENT,
  `a_cer_date` DATETIME(6) NULL DEFAULT NULL,
  `a_cer_expire` DATETIME(6) NULL DEFAULT NULL,
  `a_cer_issuing_authority` VARCHAR(100) NULL DEFAULT NULL,
  `a_cer_title` VARCHAR(100) NOT NULL,
  `application_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`a_cer_no`),
  INDEX `FK8xxrkydkaceyg5c8hmxpdw7p8` (`application_no` ASC) VISIBLE,
  CONSTRAINT `FK8xxrkydkaceyg5c8hmxpdw7p8`
    FOREIGN KEY (`application_no`)
    REFERENCES `thanksyouplz`.`application` (`application_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`application_education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`application_education` (
  `a_edu_no` BIGINT NOT NULL AUTO_INCREMENT,
  `a_edu_end_date` DATETIME(6) NULL DEFAULT NULL,
  `a_edu_organ` VARCHAR(255) NULL DEFAULT NULL,
  `a_edu_start_date` DATETIME(6) NULL DEFAULT NULL,
  `a_edu_time` INT NULL DEFAULT NULL,
  `a_edu_title` VARCHAR(255) NOT NULL,
  `a_edu_content` VARCHAR(255) NULL DEFAULT NULL,
  `application_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`a_edu_no`),
  INDEX `FKoixauchx3nvqwgr9e41ofnp37` (`application_no` ASC) VISIBLE,
  CONSTRAINT `FKoixauchx3nvqwgr9e41ofnp37`
    FOREIGN KEY (`application_no`)
    REFERENCES `thanksyouplz`.`application` (`application_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`application_experience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`application_experience` (
  `a_exp_no` BIGINT NOT NULL AUTO_INCREMENT,
  `a_exp_achievement` LONGTEXT NULL DEFAULT NULL,
  `a_exp_company_name` VARCHAR(100) NOT NULL,
  `a_exp_end_date` DATETIME(6) NULL DEFAULT NULL,
  `a_exp_is_current` BIT(1) NULL DEFAULT NULL,
  `a_exp_part` LONGTEXT NULL DEFAULT NULL,
  `a_exp_position` VARCHAR(100) NOT NULL,
  `a_exp_start_date` DATETIME(6) NOT NULL,
  `application_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`a_exp_no`),
  INDEX `FKlmuk0fgdj3imajw2nmp6a2q6b` (`application_no` ASC) VISIBLE,
  CONSTRAINT `FKlmuk0fgdj3imajw2nmp6a2q6b`
    FOREIGN KEY (`application_no`)
    REFERENCES `thanksyouplz`.`application` (`application_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`application_school`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`application_school` (
  `a_sch_no` BIGINT NOT NULL AUTO_INCREMENT,
  `a_sch_admission_date` DATETIME(6) NULL DEFAULT NULL,
  `a_sch_degree` VARCHAR(255) NULL DEFAULT NULL,
  `a_sch_gpa` DECIMAL(3,2) NULL DEFAULT NULL,
  `a_sch_graduation_date` DATETIME(6) NULL DEFAULT NULL,
  `a_sch_lev` BIGINT NULL DEFAULT NULL,
  `a_sch_major` VARCHAR(100) NULL DEFAULT NULL,
  `a_sch_minor` VARCHAR(100) NULL DEFAULT NULL,
  `a_sch_multiple` VARCHAR(100) NULL DEFAULT NULL,
  `a_sch_name` VARCHAR(100) NOT NULL,
  `a_sch_part` ENUM('ASSOCIATE_DEGREE', 'BACHELOR_DEGREE', 'DOCTORAL_DEGREE', 'GED', 'HIGH_SCHOOL', 'MASTER_DEGREE') NULL DEFAULT NULL,
  `a_sch_standard_gpa` DECIMAL(3,2) NULL DEFAULT NULL,
  `application_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`a_sch_no`),
  INDEX `FKbo5wi8pdrvny3ql9ecos4sbci` (`application_no` ASC) VISIBLE,
  CONSTRAINT `FKbo5wi8pdrvny3ql9ecos4sbci`
    FOREIGN KEY (`application_no`)
    REFERENCES `thanksyouplz`.`application` (`application_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`bookmark`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`bookmark` (
  `book_mark_no` BIGINT NOT NULL AUTO_INCREMENT,
  `bookmark_date` DATETIME(6) NULL DEFAULT NULL,
  `member_no` BIGINT NULL DEFAULT NULL,
  `posting_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`book_mark_no`),
  INDEX `FKh9x5xqkxqmp9k9j06geim3uj4` (`member_no` ASC) VISIBLE,
  INDEX `FKlodbyao7nslqkksnr6wy4oe0` (`posting_no` ASC) VISIBLE,
  CONSTRAINT `FKh9x5xqkxqmp9k9j06geim3uj4`
    FOREIGN KEY (`member_no`)
    REFERENCES `thanksyouplz`.`member` (`member_no`),
  CONSTRAINT `FKlodbyao7nslqkksnr6wy4oe0`
    FOREIGN KEY (`posting_no`)
    REFERENCES `thanksyouplz`.`job_posting` (`posting_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 33
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`company_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`company_log` (
  `company_no` BIGINT NOT NULL AUTO_INCREMENT,
  `company_address` VARCHAR(255) NULL DEFAULT NULL,
  `company_code` VARCHAR(30) NULL DEFAULT NULL,
  `company_create` DATETIME(6) NOT NULL,
  `company_delete` DATETIME(6) NULL DEFAULT NULL,
  `company_description` TINYTEXT NULL DEFAULT NULL,
  `company_employee` VARCHAR(50) NULL DEFAULT NULL,
  `company_id` VARCHAR(100) NOT NULL,
  `company_industry` VARCHAR(30) NULL DEFAULT NULL,
  `company_license` VARCHAR(100) NULL DEFAULT NULL,
  `company_logo` VARCHAR(255) NULL DEFAULT NULL,
  `company_mail` VARCHAR(30) NOT NULL,
  `company_manager_name` VARCHAR(100) NULL DEFAULT NULL,
  `company_name` VARCHAR(100) NOT NULL,
  `company_pass` VARCHAR(255) NOT NULL,
  `company_phone` VARCHAR(20) NULL DEFAULT NULL,
  `company_refresh_token` VARCHAR(2000) NULL DEFAULT NULL,
  PRIMARY KEY (`company_no`),
  UNIQUE INDEX `UKod42s3ttdqum0s1xviwmjtwc1` (`company_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`company_questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`company_questions` (
  `question_no` BIGINT NOT NULL AUTO_INCREMENT,
  `question_length` INT NOT NULL,
  `question_title` VARCHAR(255) NOT NULL,
  `posting_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`question_no`),
  INDEX `FKp26v1ku7l4shn5y6nsn1r8hjd` (`posting_no` ASC) VISIBLE,
  CONSTRAINT `FKp26v1ku7l4shn5y6nsn1r8hjd`
    FOREIGN KEY (`posting_no`)
    REFERENCES `thanksyouplz`.`job_posting` (`posting_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`company_tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`company_tag` (
  `c_tag_no` BIGINT NOT NULL AUTO_INCREMENT,
  `c_tag_name` VARCHAR(30) NOT NULL,
  `company_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`c_tag_no`),
  INDEX `FKk775c0eoo75v08ccyncwg6bjo` (`company_no` ASC) VISIBLE,
  CONSTRAINT `FKk775c0eoo75v08ccyncwg6bjo`
    FOREIGN KEY (`company_no`)
    REFERENCES `thanksyouplz`.`company` (`company_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 134
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`cover_letter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`cover_letter` (
  `cover_letter_no` BIGINT NOT NULL AUTO_INCREMENT,
  `cover_content` TINYTEXT NULL DEFAULT NULL,
  `cover_length` INT NULL DEFAULT NULL,
  `cover_question` VARCHAR(255) NOT NULL,
  `application_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`cover_letter_no`),
  INDEX `FKp27e8f5j4mvgiacyv93fhymuy` (`application_no` ASC) VISIBLE,
  CONSTRAINT `FKp27e8f5j4mvgiacyv93fhymuy`
    FOREIGN KEY (`application_no`)
    REFERENCES `thanksyouplz`.`application` (`application_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`cover_letter_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`cover_letter_category` (
  `cover_letter_no` BIGINT NOT NULL AUTO_INCREMENT,
  `cover_category_title` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`cover_letter_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`member_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`member_log` (
  `member_no` BIGINT NOT NULL AUTO_INCREMENT,
  `member_code` VARCHAR(20) NULL DEFAULT NULL,
  `member_code_rate` VARCHAR(1000) NULL DEFAULT NULL,
  `member_company_address` VARCHAR(200) NULL DEFAULT NULL,
  `member_company_contract` VARCHAR(20) NULL DEFAULT NULL,
  `member_company_part` VARCHAR(200) NULL DEFAULT NULL,
  `member_delete_date` DATETIME(6) NULL DEFAULT NULL,
  `member_file` VARCHAR(3000) NULL DEFAULT NULL,
  `member_id` VARCHAR(100) NULL DEFAULT NULL,
  `member_join_date` DATETIME(6) NOT NULL,
  `member_mail` VARCHAR(100) NOT NULL,
  `member_name` VARCHAR(100) NOT NULL,
  `member_nickname` VARCHAR(100) NOT NULL,
  `member_pass` VARCHAR(2000) NOT NULL,
  `member_phone` VARCHAR(100) NOT NULL,
  `member_random` VARCHAR(100) NOT NULL,
  `member_refresh_token` VARCHAR(2000) NULL DEFAULT NULL,
  `member_social_id` VARCHAR(200) NULL DEFAULT NULL,
  `member_test_date` DATETIME(6) NULL DEFAULT NULL,
  `member_social_type` ENUM('GOOGLE', 'KAKAO', 'NAVER', 'NORMAL') NULL DEFAULT NULL,
  PRIMARY KEY (`member_no`),
  UNIQUE INDEX `UKru4viq7s4mntpgegvltlyyynk` (`member_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`personal_test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`personal_test` (
  `per_no` BIGINT NOT NULL AUTO_INCREMENT,
  `per_question` VARCHAR(255) NOT NULL,
  `per_type` ENUM('A', 'B') NOT NULL,
  PRIMARY KEY (`per_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 221
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`resume`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`resume` (
  `resume_no` BIGINT NOT NULL AUTO_INCREMENT,
  `resume_address` VARCHAR(200) NULL DEFAULT NULL,
  `resume_ambition` VARCHAR(255) NULL DEFAULT NULL,
  `resume_army_date` DATETIME(6) NULL DEFAULT NULL,
  `resume_army_end` DATETIME(6) NULL DEFAULT NULL,
  `resume_army_part` ENUM('AIR_FORCE', 'ARMY', 'COAST_GUARD', 'FIRE_SERVICE', 'KATUSA', 'MARINES', 'NAVY', 'OTHER', 'POLICE_SERVICE', 'PUBLIC_SERVICE', 'RIOT_POLICE', 'TECH_SERVICE') NULL DEFAULT NULL,
  `resume_army_type` ENUM('APPLICABLE', 'EXEMPT', 'NON_APPLICABLE') NULL DEFAULT NULL,
  `resume_birthday` VARCHAR(200) NULL DEFAULT NULL,
  `resume_date` DATETIME(6) NULL DEFAULT NULL,
  `resume_disability` VARCHAR(100) NULL DEFAULT NULL,
  `resume_disability_type` VARCHAR(30) NULL DEFAULT NULL,
  `resume_engname` VARCHAR(30) NULL DEFAULT NULL,
  `resume_gender` ENUM('F', 'M') NULL DEFAULT NULL,
  `resume_img` VARCHAR(2000) NULL DEFAULT NULL,
  `resume_mail` VARCHAR(255) NULL DEFAULT NULL,
  `resume_name` VARCHAR(50) NOT NULL,
  `resume_portfolio` VARCHAR(2000) NULL DEFAULT NULL,
  `resume_rewarding_patriotism` VARCHAR(20) NULL DEFAULT NULL,
  `resume_tel` VARCHAR(30) NULL DEFAULT NULL,
  `resume_title` VARCHAR(255) NOT NULL,
  `resume_update` DATETIME(6) NULL DEFAULT NULL,
  `member_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`resume_no`),
  INDEX `FKlq9o6he4xjgil78hk5l7fsaif` (`member_no` ASC) VISIBLE,
  CONSTRAINT `FKlq9o6he4xjgil78hk5l7fsaif`
    FOREIGN KEY (`member_no`)
    REFERENCES `thanksyouplz`.`member` (`member_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`resume_award`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`resume_award` (
  `awa_no` BIGINT NOT NULL AUTO_INCREMENT,
  `awa_competition_name` VARCHAR(50) NULL DEFAULT NULL,
  `awa_content` VARCHAR(255) NULL DEFAULT NULL,
  `awa_date` DATETIME(6) NULL DEFAULT NULL,
  `awa_organ` VARCHAR(50) NULL DEFAULT NULL,
  `awa_title` VARCHAR(50) NOT NULL,
  `resume_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`awa_no`),
  INDEX `FKid49p5j4x3weqnijax79eobkt` (`resume_no` ASC) VISIBLE,
  CONSTRAINT `FKid49p5j4x3weqnijax79eobkt`
    FOREIGN KEY (`resume_no`)
    REFERENCES `thanksyouplz`.`resume` (`resume_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`resume_certificate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`resume_certificate` (
  `cer_no` BIGINT NOT NULL AUTO_INCREMENT,
  `cer_date` DATETIME(6) NULL DEFAULT NULL,
  `cer_expire` DATETIME(6) NULL DEFAULT NULL,
  `cer_issuing_authority` VARCHAR(100) NULL DEFAULT NULL,
  `cer_title` VARCHAR(100) NOT NULL,
  `resume_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`cer_no`),
  INDEX `FKcem8dytk31ss7n0q42c30fri6` (`resume_no` ASC) VISIBLE,
  CONSTRAINT `FKcem8dytk31ss7n0q42c30fri6`
    FOREIGN KEY (`resume_no`)
    REFERENCES `thanksyouplz`.`resume` (`resume_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`resume_education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`resume_education` (
  `edu_no` BIGINT NOT NULL AUTO_INCREMENT,
  `edu_content` VARCHAR(255) NULL DEFAULT NULL,
  `edu_end_date` DATETIME(6) NULL DEFAULT NULL,
  `edu_organ` VARCHAR(255) NULL DEFAULT NULL,
  `edu_start_date` DATETIME(6) NULL DEFAULT NULL,
  `edu_time` INT NULL DEFAULT NULL,
  `edu_title` VARCHAR(255) NOT NULL,
  `resume_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`edu_no`),
  INDEX `FKnoskc8lfw1ftas8u0gm6hetu1` (`resume_no` ASC) VISIBLE,
  CONSTRAINT `FKnoskc8lfw1ftas8u0gm6hetu1`
    FOREIGN KEY (`resume_no`)
    REFERENCES `thanksyouplz`.`resume` (`resume_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`resume_experience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`resume_experience` (
  `exp_no` BIGINT NOT NULL AUTO_INCREMENT,
  `exp_achievement` LONGTEXT NULL DEFAULT NULL,
  `exp_company_name` VARCHAR(100) NOT NULL,
  `exp_end_date` DATETIME(6) NULL DEFAULT NULL,
  `exp_is_current` BIT(1) NULL DEFAULT NULL,
  `exp_part` LONGTEXT NULL DEFAULT NULL,
  `exp_position` VARCHAR(100) NOT NULL,
  `exp_start_date` DATETIME(6) NOT NULL,
  `resume_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`exp_no`),
  INDEX `FKm6gwixlgyn5nmxx9wtr2o0fh3` (`resume_no` ASC) VISIBLE,
  CONSTRAINT `FKm6gwixlgyn5nmxx9wtr2o0fh3`
    FOREIGN KEY (`resume_no`)
    REFERENCES `thanksyouplz`.`resume` (`resume_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`resume_school`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`resume_school` (
  `sch_no` BIGINT NOT NULL AUTO_INCREMENT,
  `sch_admission_date` DATETIME(6) NULL DEFAULT NULL,
  `sch_degree` VARCHAR(255) NULL DEFAULT NULL,
  `sch_gpa` DECIMAL(3,2) NULL DEFAULT NULL,
  `sch_graduation_date` DATETIME(6) NULL DEFAULT NULL,
  `sch_lev` BIGINT NULL DEFAULT NULL,
  `sch_major` VARCHAR(100) NULL DEFAULT NULL,
  `sch_minor` VARCHAR(100) NULL DEFAULT NULL,
  `sch_multiple` VARCHAR(100) NULL DEFAULT NULL,
  `sch_name` VARCHAR(100) NOT NULL,
  `sch_part` ENUM('ASSOCIATE_DEGREE', 'BACHELOR_DEGREE', 'DOCTORAL_DEGREE', 'GED', 'HIGH_SCHOOL', 'MASTER_DEGREE') NULL DEFAULT NULL,
  `sch_standard_gpa` DECIMAL(3,2) NULL DEFAULT NULL,
  `resume_no` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`sch_no`),
  INDEX `FKgmiun6nd520q9pcus4ulnjcd5` (`resume_no` ASC) VISIBLE,
  CONSTRAINT `FKgmiun6nd520q9pcus4ulnjcd5`
    FOREIGN KEY (`resume_no`)
    REFERENCES `thanksyouplz`.`resume` (`resume_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `thanksyouplz`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `thanksyouplz`.`tag` (
  `tag_no` BIGINT NOT NULL AUTO_INCREMENT,
  `tag_name` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`tag_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 93
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
