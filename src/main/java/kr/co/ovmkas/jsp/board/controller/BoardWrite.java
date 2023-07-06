package kr.co.ovmkas.jsp.board.controller;

import static kr.co.ovmkas.jsp.util.ParamSolver.isLogin;

import java.io.IOException;
import java.lang.reflect.Member;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import kr.co.ovmkas.jsp.domain.Board;
import kr.co.ovmkas.jsp.domain.Criteria;
import kr.co.ovmkas.jsp.service.BoardService;
import kr.co.ovmkas.jsp.service.BoardServiceImpl;
import kr.co.ovmkas.jsp.util.ParamSolver;


@WebServlet("/board/write")
public class BoardWrite extends HttpServlet{
	private BoardService boardService = new BoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!isLogin(req)) {
			resp.sendRedirect(req.getContextPath() + "/member/signin?href="+ URLEncoder.encode(req.getRequestURI(), "utf-8"));
			return ;
		}
			Criteria criteria = ParamSolver.getParams(req, Criteria.class);
			req.setAttribute("cri", criteria); 
			System.out.println(req.getAttribute("cri"));
			req.getRequestDispatcher("/WEB-INF/jsp/board/write.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria criteria = ParamSolver.getParams(req, Criteria.class);
		Board board = ParamSolver.getParams(req, Board.class);
		System.out.println(board);
		req.setAttribute("board", boardService.register(board));
		
		resp.sendRedirect(req.getContextPath()+"/board/"+ (criteria.getCategory()==2 ? "notice" : "free"));
	}
	
	
}
