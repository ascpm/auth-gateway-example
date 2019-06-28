-- -----------------------------------------------------
-- Table `jwt`
-- -----------------------------------------------------

-- -----------------------------------------------------
-- H2 DB
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jwt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '데이터아이디',
    `uid` VARCHAR(40) NOT NULL COMMENT '직원아이디',
    `issuer` VARCHAR(100) NOT NULL COMMENT '발급자(kong에서 받은 credential key)',
    `use_yn` CHAR(1) NOT NULL COMMENT '사용여부',
    `created_time` DATETIME NOT NULL COMMENT '생성일시',
    `modified_time` DATETIME NULL COMMENT '수정일시',
    PRIMARY KEY (`id`)
);

COMMENT ON TABLE `jwt` IS 'JWT정보';
CREATE INDEX IF NOT EXISTS `IDX_UID` ON `jwt` (`uid`);
