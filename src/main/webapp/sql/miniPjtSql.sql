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

SELECT * FROM member WHERE user_id = ? AND user_pwd = sha1(md5(?));

SELECT m.*, u.new_filename FROM member m, uploadedfile u WHERE m.user_img = u.file_id and user_id = ? AND user_pwd = sha1(md5(?));

UPDATE member SET user_point = userPoint + ? WHERE user_id = ? 

SELECT * FROM pointlog WHERE user_id = ?


CREATE TABLE `distinctao`.`board` (
  `board_no` INT NOT NULL,
  `writer` VARCHAR(8) NULL,
  `title` VARCHAR(45) NOT NULL,
  `post_date` DATETIME NULL DEFAULT now(),
  `content` VARCHAR(1000) NOT NULL,
  `read_count` INT NULL DEFAULT 0,
  `like_count` INT NULL DEFAULT 0,
  `ref` INT NULL DEFAULT NULL,
  `step` INT NULL DEFAULT 0,
  `ref_order` INT NULL DEFAULT 0,
  `isDelete` VARCHAR(1) NULL DEFAULT 'N',
  PRIMARY KEY (`board_no`),
  INDEX `board_writer_FK_idx` (`writer` ASC) VISIBLE,
  CONSTRAINT `board_writer_FK`
    FOREIGN KEY (`writer`)
    REFERENCES `distinctao`.`member` (`user_id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION);


ALTER TABLE `distinctao`.`uploadedfile` 
ADD COLUMN `board_no` INT NULL DEFAULT NULL AFTER `file_size`,
ADD COLUMN `base64String` LONGTEXT NULL DEFAULT NULL AFTER `board_no`,
CHANGE COLUMN `original_filename` `original_filename` VARCHAR(100) NULL DEFAULT NULL ,
CHANGE COLUMN `new_filename` `new_filename` VARCHAR(80) NULL DEFAULT NULL ,
ADD INDEX `boare_no_FK_idx` (`board_no` ASC) VISIBLE;
;
ALTER TABLE `distinctao`.`uploadedfile` 
ADD CONSTRAINT `uploadedfile_boare_no_FK`
  FOREIGN KEY (`board_no`)
  REFERENCES `distinctao`.`board` (`board_no`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


-- 게시판 전체 글 목록 가져오기
SELECT * FROM board ORDER BY board_no DESC;

SELECT MAX(board_no) + 1 as nextRef FROM board ; 

INSERT INTO uploadedfile (original_filename, ext, new_filename, file_size, board_no) VALUES (?, ?, ?, ?, (SELECT MAX(b.board_no) + 1 as board_no FROM board b));

INSERT INTO board (writer, title, post_date, content, ref) VALUES (?, ?, ?, ?, (SELECT MAX(b.board_no) + 1 as board_no FROM board b));

Alter table member auto_increment = 1;
Alter table board auto_increment = 1;
Alter table uploadedfile auto_increment = 4;
Alter table pointlog auto_increment = 1;

SELECT * FROM readcountprocess WHERE read_no =? AND ip_addr = ?;

show table status Where name = 'uploadedfile';

SELECT TimestampDIFF(hour, read_time, now()) as diff_hour FROM readcountprocess WHERE ip_addr = '127.0.0.1' AND read_no = 1;

SELECT TimestampDIFF(hour, (Select read_time  FROM readcountprocess WHERE ip_addr = '127.0.0.1' AND read_no = 1), now());


INSERT INTO readcountprocess (ip_addr, board_no) values (?, ?);
 
update readcountprocess set read_time = now() WHERE ip_addr = ? AND board_no =?;

-- n번 글의 조회수 증가 쿼리문

update board set read_count = read_count + 1 where board_no = ?;

SELECT b.* , u.new_filename FROM board b INNER JOIN uploadedfile u ON b.board_no = u.board_no WHERE board_no = ?;

UPDATE board SET title = ?, content = ? WHERE board_no = ?;

UPDATE uploadedfile SET original_filename = ?, ext = ?, new_filename = ?, file_size = ?, base64String = ? WHERE board_no = ?;

-------------------------- 답글 처리 ----------------------------------

SELECT * FROM board ORDER BY ref DESC, ref_order;

-- 답글을 board table에 등록
-- pRef == ref 이고, pRefOrder < refOrder 인 행에 대해서  refOrder = refOrder +1로 업데이트

update board set ref_order = ref_order + 1 WHERE ref = ? AND ref_order > ?;
-- 새로 등록되는 답글 isert => ref = pRed, step = pStep + 1, refOrder = pRefOrder + 1
Insert into board (writer, title, content, ref, step, ref_order)  values (?, ?, ?, ?, ?, ?);

SELECT step, ref_order from board where board_no = ?;

update board set ref_order = ref_order + 1 where ref_order = (Select ref_order from board where board_no = 1) < ref_order = (Select c.ref_order from board p, board c where p.board_no = c.ref);

UPDATE uploadedfile SET original_filename = 'arrow.png', ext = '.png', new_filename = 'board_uploads/arrow.png', file_size = '9365', base64String = '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' WHERE board_no = 4;

---------------------- 페이징 처리 ----------------------------
-- top five
SELECT * FROM board ORDER BY read_count desc limit 5;
SELECT count(*) FROM board; 

-- 한페이지 당 보여줄 글의 갯수 (5)
-- 총 글의 갯수
--  => 전체 페이지 갯수 -- 
-- (글의 총 갯수 / 한페이지 당 글의 갯수) + 1 => 나누어 떨어지지 않으면 한페이지를 더해주어야함

-- 보여주기 시작할 row Index 번호
-- 1 page => limit [보여주기 시작할 row index 번호, 보여줄 row 갯수]
SELECT * FROM board ORDER BY ref desc, ref_order limit 0, 5;
SELECT * FROM board ORDER BY ref desc, ref_order limit 5, 5;
SELECT * FROM board ORDER BY ref desc, ref_order limit 10, 5;
SELECT * FROM board ORDER BY ref desc, ref_order limit 15, 5;

SELECT * FROM board ORDER BY ref desc, ref_order limit  ? , 5;

--------- 페이징 블럭처리 --------
-- 1,2 / 3,4/ 5,6/ 7,8/
-- 1) 한개의 블럭에 몇개의 페이지를 보여줄 것인지 (pageCntPerBlock) : 2
-- => 전체 페이징 블럭의 갯수 ? 전체 페이지 / 블럭pageCntPerBlock갯수  (+ 1)

-- 2) 현재 페이지가 속한 페이징 블럭 번호
-- 현재 페이지가 2 => 1번 블럭
-- 현재 페이지가 1 => 1번 블럭
-- 현재 페이지가 5 => 3번 블럭

-- 3) 현재 페이징 블럭 시작 페이지 번호 == (블럭 번호 - 1 ) * pageCntPerBlock  + 1 

-- 4) 현재 페이징 블럭 끝 페이지 번호 == (블럭 번호 / pageCntPerBlock)


----------------- 검색어 + 유형 처리 -----------------------



