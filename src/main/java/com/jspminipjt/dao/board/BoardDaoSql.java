package com.jspminipjt.dao.board;

public class BoardDaoSql {
	public static final int LOGIN = 5;
	public static final int SIGNIN =100;
	public static final int WRITE_BOARD = 10;
	public static final int WRITE_ANSWER = 5;
	
//	public static final String SELECT_BY_USERID = "SELECT * FROM member WHERE user_id = ?";
	public static final String SELECT_ALL_IMAGES = "SELECT u.*, m.user_id FROM uploadedfile u, member m WHERE u.file_id = m.user_img";	
	public static final String INSERT_UPLOADEDFILE_BOARD = "INSERT INTO uploadedfile (original_filename, ext, new_filename, file_size, board_no, base64String) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String INSERT_UPLOADEDFILE = "INSERT INTO uploadedfile (original_filename, ext, new_filename, file_size) VALUES (?, ?, ?, ?)";
//	public static final String SELECT_FILE_ID = "SELECT file_id FROM uploadedfile WHERE new_filename = ?";
//	public static final String INSERT_MEMBER_IMG = "INSERT INTO member (user_id, user_pwd, user_email, user_img, user_point) values (?, sha1(md5(?)), ?, ?, ?)";
//	public static final String INSERT_MEMBER = "INSERT INTO member (user_id, user_pwd, user_email, user_point) values (?, sha1(md5(?)), ?, ?)";
	public static final String INSERT_POINTLOG = "INSERT INTO pointlog (point_type, change_point, user_id) values (?, ?, ?)";
	public static final String UPDATE_POINT_WRITE = "UPDATE member SET user_point = user_point + ? WHERE user_id = ?";
	public static final String SELECT_BOARD_REF = "SELECT MAX(board_no) + 1 as board_no FROM board";
	
	public static final String INSERT_BOARD_CONTENT = "INSERT INTO board (writer, title, content, ref) VALUES (?, ?, ?, (SELECT MAX(b.board_no) + 1 as board_no FROM board b))";
	public static final String SELECT_ALL_BOARD_LIST = "SELECT * FROM board ORDER BY board_no DESC"; 
	public static final String SELECT_BOARD_NO = "SHOW TABLE STATUS WHERE name = 'board';";
	public static final String SELECT_BOARD_READCNT = "SELECT * FROM readcountprocess WHERE ip_addr = ? AND read_no =?";
	public static final String SELECT_READ_HOUR_BY_NO = "SELECT TimestampDIFF(hour, read_time, now()) as diff_hour FROM readcountprocess WHERE ip_addr = ? AND read_no = ?";
	public static final String INSERT_READTIME = "INSERT INTO readcountprocess (ip_addr, board_no) values (?, ?)";
	public static final String UPDATE_READTIME = "update readcountprocess set read_time = now() WHERE ip_addr = ? AND board_no =?";
	public static final String UPDATE_READCOUNT = "update board set read_count = read_count + 1 where board_no = ?";
	
}
