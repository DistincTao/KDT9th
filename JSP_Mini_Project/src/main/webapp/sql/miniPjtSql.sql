use distinctao;

CREATE TABLE `distinctao`.`member` (
  `user_id` VARCHAR(8) NOT NULL,
  `user_pwd` VARCHAR(50) NOT NULL,
  `user_email` VARCHAR(50) NULL DEFAULT 'null',
  `regdate` DATETIME NOT NULL DEFAULT now(),
  `user_img` VARCHAR(50) NULL DEFAULT 'null',
  `user_point` INT(12) NULL DEFAULT 0,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE);
  
 ALTER TABLE `distinctao`.`member` 
   ADD COLUMN `is_admin` VARCHAR(1) NULL DEFAULT 'N' AFTER `user_point`;

CREATE TABLE `distinctao`.`uploadedfile` (
  `file_id` INT(10) NOT NULL AUTO_INCREMENT,
  `original_filename` VARCHAR(100) NOT NULL,
  `ext` VARCHAR(5) NULL,
  `new_filename` VARCHAR(80) NOT NULL,
  `fileSize` INT NULL,
  PRIMARY KEY (`file_id`));

CREATE TABLE `distinctao`.`pointpolicy` (
  `point_type` VARCHAR(50) NOT NULL,
  `each_point` INT NULL,
  PRIMARY KEY (`point_type`));
  
  CREATE TABLE `distinctao`.`pointlog` (
  `pointlog_no` INT NOT NULL,
  `action_date` DATETIME NOT NULL DEFAULT now(),
  `point_type` VARCHAR(50) NULL,
  `change_point` INT(11) NULL,
  `user_id` VARCHAR(8) NULL,
  PRIMARY KEY (`pointlog_no`),
  INDEX `point_type_FK_idx` (`point_type` ASC) VISIBLE,
  INDEX `user_id_FK_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `point_type_FK`
    FOREIGN KEY (`point_type`)
    REFERENCES `distinctao`.`pointpolicy` (`point_type`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `user_id_FK`
    FOREIGN KEY (`user_id`)
    REFERENCES `distinctao`.`member` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);


INSERT INTO `distinctao`.`pointpolicy` (`point_type`, `each_point`) VALUES ('signin', '100');
INSERT INTO `distinctao`.`pointpolicy` (`point_type`, `each_point`) VALUES ('login', '5');
INSERT INTO `distinctao`.`pointpolicy` (`point_type`, `each_point`) VALUES ('write_board', '10');
INSERT INTO `distinctao`.`pointpolicy` (`point_type`, `each_point`) VALUES ('write_comment', '5');

SELECT * from member where user_id = ?;

INSERT INTO MEMBER (user_id, user_pwd, user_email)
values (?, ?, ?); 

-- INSERT INTO uploadedfile (original_filename, ext, new_filename, fileSize) VALUES (?, ?, ?, ?)

-- 현재 업로드된 파일의 file_no를 select 해와서 반환
-- SELECT file_id FROM uploadedfile WHERE new_filename = ?

-- INSERT INTO member (user_id, user_pwd, user_email, user_img, user_point) values (?, sha1(md5(?)), ?, ?, ?) -- pwd 는 이중 암호화 => 복호화는 불가 => 실제 상황에서 비번 재설정으로 진행

-- INSERT INTO pointlog (point_type, change_point, user_id) values (? , ?, ?)

SELECT user_id, user_email, regdate, user_img, user_point FROM member

SELECT * FROM pointlog;

SELECT u.*, m.user_id FROM uploadedfile u, member m WHERE u.file_id = m.user_img;

SELECT * FROM member WHERE user_id = ? AND user_pwd = sha1(md5(?))

