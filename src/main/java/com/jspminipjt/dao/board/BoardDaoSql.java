package com.jspminipjt.dao.board;

public class BoardDaoSql {
	public static final int LOGIN = 5;
	public static final int SIGNIN =100;
	public static final int WRITE_BOARD = 10;
	public static final int WRITE_REPLY = 5;
	
	public static final String SELECT_ALL_IMAGES = "SELECT u.*, m.user_id FROM uploadedfile u, member m WHERE u.file_id = m.user_img";	
	public static final String INSERT_UPLOADEDFILE_BOARD = "INSERT INTO uploadedfile (original_filename, ext, new_filename, file_size, board_no, base64String) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String INSERT_UPLOADEDFILE = "INSERT INTO uploadedfile (original_filename, ext, new_filename, file_size) VALUES (?, ?, ?, ?)";
	public static final String INSERT_POINTLOG = "INSERT INTO pointlog (point_type, change_point, user_id) values (?, ?, ?)";
	public static final String UPDATE_POINT_WRITE = "UPDATE member SET user_point = user_point + ? WHERE user_id = ?";
	public static final String SELECT_BOARD_REF = "SELECT MAX(board_no) + 1 as board_no FROM board";
	
	public static final String INSERT_BOARD_CONTENT = "INSERT INTO board (writer, title, content, ref) VALUES (?, ?, ?, (SELECT MAX(b.board_no) + 1 as board_no FROM board b))";
	public static final String SELECT_ALL_BOARD_LIST = "SELECT * FROM board ORDER BY ref DESC, ref_order"; 
	public static final String SELECT_BOARD_NO = "SHOW TABLE STATUS WHERE name = 'board';";
	public static final String SELECT_BOARD_READCNT = "SELECT * FROM readcountprocess WHERE ip_addr = ? AND read_no =?";
	public static final String SELECT_READ_HOUR_BY_NO = "SELECT TimestampDIFF(hour, read_time, now()) as diff_hour FROM readcountprocess WHERE ip_addr = ? AND read_no = ?";
	public static final String INSERT_READTIME = "INSERT INTO readcountprocess (ip_addr, board_no) values (?, ?)";
	public static final String UPDATE_READTIME = "update readcountprocess set read_time = now() WHERE ip_addr = ? AND board_no =?";
	public static final String UPDATE_READCOUNT = "update board set read_count = read_count + 1 where board_no = ?";
	public static final String SELECT_BY_BOARD_NO = "SELECT * FROM board WHERE board_no = ?";
	public static final String DELET_BOARD = "UPDATE board SET isDelete = 'Y' WHERE board_no = ?";
	public static final String UPDATE_BOARD = "UPDATE board SET title = ?, content = ? WHERE board_no = ?";
	public static final String UPDATE_UPLOADEDFILE = "UPDATE uploadedfile SET original_filename = ?, ext = ?, new_filename = ?, file_size = ?, base64String = ? WHERE board_no = ?";
	
	public static final String INSERT_REPLY = "INSERT INTO board (writer, title, content, ref, step, ref_order) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_REF_ORDER = "UPDATE board SET ref_order = ref_order + 1 WHERE ref = ? AND ref_order > ?";
	public static final String DELETE_UPLOADEDFILE = "DELETE FROM uploadedfile WHERE board_no =?";
	public static final String SELECT_TOTALPOST = "SELECT count(*) AS total_post FROM board";
	public static final String SELECT_ALL_BOARD_BY_PAGING = "SELECT * FROM board ORDER BY ref DESC, ref_order LIMIT ? , ?";

	public static final String SELECT_TOTALPOST_SEARCH = "SELECT count(*) AS total_post FROM board WHERE ";
	public static final String SELECT_SEARCH_BOARD_BY_PAGING = "SELECT * FROM board WHERE ";
	public static final String UPDATE_LIKECOUNT = "update board set like_count = like_count + 1 where board_no = ?";
	public static final String INSERT_LIKECOUNT_LOG_ID = "INSERT INTO likecount (board_no, user_id) values(?, ?)";
	public static final String SELECT_LIKECOUNTLOG = "SELECT count(*) AS likecount FROM likecount WHERE board_no = ? AND user_id = ?";
	public static final String SELECT_LIKECOUNT = "SELECT count(*) AS like_count FROM likecount WHERE board_no = ?";
	public static final String SELECT_LIKECOUNTLOG_LIST = "SELECT * FROM likecount WHERE board_no = ? AND user_id = ?";

	public static final String DELETE_LIKECOUNTLOG = "DELETE FROM likecount WHERE board_no = ? AND user_id = ?";
	public static final String UPDATE_LIKECOUNT_MINUS = "update board set like_count = like_count - 1 where board_no = ?";

}
