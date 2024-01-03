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

SELECT * from member where user_id = ?;

INSERT INTO MEMBER (user_id, user_pwd, user_email)
values (?, ?, ?); 