package kr.co.ovmkas.jsp.board.controller;

import static kr.co.ovmkas.jsp.util.ParamSolver.isLogin;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ovmkas.jsp.domain.Board;
import kr.co.ovmkas.jsp.domain.Criteria;
import kr.co.ovmkas.jsp.service.BoardService;
import kr.co.ovmkas.jsp.service.BoardServiceImpl;
import kr.co.ovmkas.jsp.util.ParamSolver;


@WebServlet("/board/modify")
public class BoardModify extends HttpServlet{
	private BoardService boardService = new BoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = ParamSolver.getParams(req, Criteria.class);
		Board board = boardService.get(Long.valueOf(req.getParameter("bno")));
		req.setAttribute("cri", ParamSolver.getParams(req, Criteria.class));
		req.setAttribute("board", boardService.get(Long.valueOf(req.getParameter("bno"))));
		
		if(!isLogin(req)) {
			resp.sendRedirect(req.getContextPath() + "/member/signin?bno="+board.getBno() + "&" + criteria.getFullQueryStr());
			return ;
		}
		
		/*
		 * req.setAttribute("cri", ParamSolver.getParams(req, Criteria.class));
		 * req.setAttribute("board",
		 * boardService.get(Long.valueOf(req.getParameter("bno"))));//원래는 getParameter로
		 * req.getRequestDispatcher("/WEB-INF/jsp/board/modify.jsp").forward(req, resp);
		 */
		if(ParamSolver.isMine(req, board.getWriter())) {
			req.getRequestDispatcher("/WEB-INF/jsp/board/modify.jsp").forward(req, resp);
		}else {
			int msg =1;
			resp.sendRedirect("detail?bno=" + board.getBno() + "&" + criteria.getFullQueryStr() +"&msg="+ msg);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = ParamSolver.getParams(req, Criteria.class);
		Board board = ParamSolver.getParams(req, Board.class); 
		if(ParamSolver.isMine(req, board.getWriter())) {
			boardService.modify(board);
			resp.sendRedirect((criteria.getCategory()==2 ? "notice" : "free") + "?bno=" + board.getBno() +"&" + criteria.getFullQueryStr());
		}else {
			int msg =1;
			resp.sendRedirect("detail?bno=" + board.getBno() + "&" + criteria.getFullQueryStr() +"&msg="+ msg);
		}
	}
	
}
