package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateController
 */
@WebServlet("/update.no")
public class NoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST 인코딩 1)
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기
		// 뽑아야 하는 값 : 제목, 내용(제목 중복 O, 내용 O) + PK는 게시글 번호
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		String noticeTitle = request.getParameter("noticetitle");
		String noticeContent = request.getParameter("content");
		
		// 3) 가공
		Notice n = new Notice();
		n.setNoticeNo(noticeNo);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		
		// 4) Service단으로 토스
		int result = new NoticeService().updateNotice(n);
		
		// 5) 결과값에 따라 응답페이지 지정
		if(result > 0) { // 성공 => 해당 글 상세보기 페이지로 응답 뷰 지정 + alert 띄어주자
									// localhost:8801/jsp/detail.no?nno=XX
			
			request.getSession().setAttribute("alertMsg", "공지사항 수정 성공~");
			response.sendRedirect(request.getContextPath() + "/list.no?cpage=1");
			
		} else {
			request.setAttribute("errorMsg", "공지사항 수정 실패!");
			request.getRequestDispatcher("views/common/errorpage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
