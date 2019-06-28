-- -----------------------------------------------------
-- Table `jwt`
-- -----------------------------------------------------

-- -----------------------------------------------------
-- MySql DB
-- -----------------------------------------------------
CREATE TABLE `jwt` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '데이터아이디',
  `uid` VARCHAR(40) COLLATE utf8mb4_bin NOT NULL COMMENT '직원아이디',
  `issuer` VARCHAR(100) COLLATE utf8mb4_bin NOT NULL COMMENT '발급자(kong에서 받은 credential key)',
  `use_yn` CHAR(1) COLLATE utf8mb4_bin NOT NULL COMMENT '사용여부',
  `created_time` DATETIME NOT NULL COMMENT '생성일시',
  `modified_time` DATETIME NULL COMMENT '수정일시',
  PRIMARY KEY (`id`),
  KEY `IX_UID` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT = 'JWT정보';
