package kr.co.ovmkas.jsp.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.ovmkas.jsp.domain.Member;
import kr.co.ovmkas.jsp.service.MemberService;
import kr.co.ovmkas.jsp.service.MemberServiceImpl;
import kr.co.ovmkas.jsp.util.ParamSolver;

@WebServlet("/member/remove")
public class MemberRemove extends HttpServlet{
	private  MemberService memberService = new MemberServiceImpl();
	/**
	 * 회원 삭제 및 세션 초기화
	 * 회원삭제
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("삭제");
		if(ParamSolver.isLogin(req)) {
			Member member = (Member)req.getSession().getAttribute("member");
			System.out.println(member);
			memberService.remove(member);
			req.getSession().invalidate();
			resp.sendRedirect(req.getContextPath()+"/");
		}
	}
}
