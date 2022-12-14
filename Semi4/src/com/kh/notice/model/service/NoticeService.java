package com.kh.notice.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

	public int insertNotice(Notice n) {

		Connection conn = getConnection();

		int result = new NoticeDao().insertNotice(conn, n);

		if (result > 0) { // 성공
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;

	}
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCount(conn);
		// SELECT문의 결과는 ResultSet
		// 상식적으로 생각해보면 게시글의 총 갯수는 정수형
		
		close(conn);
		
		return listCount;
		
	}

	public ArrayList<Notice> selectNoticeList(PageInfo pi) {

		Connection conn = getConnection();

		ArrayList<Notice> list = new NoticeDao().selectNoticeList(conn, pi);

		close(conn);

		return list;

	}

	public int updateNotice(Notice n) {
		Connection conn = getConnection();

		int result = new NoticeDao().updateNotice(conn, n);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;
	}

	public int deleteNotice(int noticeNo) {

		Connection conn = getConnection();

		int result = new NoticeDao().deleteNotice(conn, noticeNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);

		return result;

	}

	public Notice selectNotice(int noticeNo) {
		Connection conn = getConnection();

		Notice n = new NoticeDao().selectNotice(conn, noticeNo);

		close(conn);

		return n;
	}
}
