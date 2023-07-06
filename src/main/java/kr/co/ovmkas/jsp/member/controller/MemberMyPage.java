package kr.co.ovmkas.jsp.member.controller;

import static kr.co.ovmkas.jsp.util.ParamSolver.isLogin;

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

@WebServlet("/member/myPage")
public class MemberMyPage extends HttpServlet{
	private MemberService memberService = new MemberServiceImpl();
	
	/**
	 * Mypage 이동
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(!isLogin(req)) {
			resp.sendRedirect(req.getContextPath() + "/member/signin?href="+ URLEncoder.encode(req.getRequestURI(), "utf-8"));
			return ;
		}
		req.getRequestDispatcher("/WEB-INF/jsp/member/myPage.jsp").forward(req, resp);
	}

	/**
	 * form에 있는 input을 가지고 Member회원정보 수정
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(ParamSolver.isLogin(req)) {
			Member member = ParamSolver.getParams(req, Member.class);
			memberService.modify(member);
			req.getSession().setAttribute("member", memberService.get(member));
			resp.sendRedirect(req.getContextPath() + "/index");
		}
	}
}
