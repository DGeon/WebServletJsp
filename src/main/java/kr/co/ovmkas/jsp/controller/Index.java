package kr.co.ovmkas.jsp.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ovmkas.jsp.domain.Member;
import kr.co.ovmkas.jsp.service.MemberService;
import kr.co.ovmkas.jsp.service.MemberServiceImpl;
import kr.co.ovmkas.jsp.util.ParamSolver;

@WebServlet("/index")
public class Index extends HttpServlet{
	MemberService memberService = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(req, resp);
	}

	/**
	 * index 에 login부분에 대한 Login
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Member member = ParamSolver.getParams(req, Member.class);
//		memberService.get(member);
//		
//		if(!ParamSolver.isLogin(req)) {
//			req.getSession().setAttribute("member", memberService.get(member));
//		}
//		resp.sendRedirect(req.getContextPath()+"/index");
		
		Member member = ParamSolver.getParams(req, Member.class);
		memberService.get(member);
		req.setCharacterEncoding("utf-8");
		String msg="";
		String redirectStr = req.getContextPath();
		System.out.println(memberService.get(member));
		if(memberService.get(member)==null) {
			msg="아이디 혹은 비밀번호가 잘 못 입력되었습니다.";
			msg = URLEncoder.encode(msg, "utf-8");
			redirectStr += "/index?loginFail=" + msg;
		}else {
			req.getSession().setAttribute("member", memberService.get(member));
			redirectStr+="/index";
		}
		resp.sendRedirect(redirectStr);
	}
	
}
