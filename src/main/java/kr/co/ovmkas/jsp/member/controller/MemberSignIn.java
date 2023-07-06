package kr.co.ovmkas.jsp.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/member/signin")
public class MemberSignIn extends HttpServlet{
	private MemberService memberService = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/signin.jsp").forward(req, resp);
	}

	/**
	 * 회원가입시에 나타나는 화면
	 * DB에 있는 Member의 정보와 다르면 p태그에 출력
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Member member = ParamSolver.getParams(req, Member.class);
		memberService.get(member);
		req.setCharacterEncoding("utf-8");
		String msg="";
		String redirectStr = req.getContextPath()+"/";
		String href = req.getParameter("href");
		System.out.println("href:::::" + href);
		// if문 테스트 해볼것
		
		if(memberService.get(member)==null) {
			msg="아이디 혹은 비밀번호가 잘 못 입력되었습니다.";
			msg = URLEncoder.encode(msg, "utf-8");
			redirectStr += "member/signin?loginFail=" + msg;
//			req.getSession().setAttribute("member", memberService.get(member));
//			resp.sendRedirect(req.getContextPath()+"/index");
//			resp.sendRedirect(req.getContextPath()+"/member/signin");
		}else {
			if (href!=null) {
				redirectStr = href;
			}
			req.getSession().setAttribute("member", memberService.get(member));
			/* redirectStr+="/index"; */
//			resp.sendRedirect(req.getContextPath()+"/member/signin");
//			resp.setContentType("text/html; charset=UTF-8");
//		    PrintWriter out = resp.getWriter();
//		    out.println("<script> "
//		    		+ "let frm = document.getElementById('signinFrm');"
//		    		+ "frm.signinOutput.innerHTML='존재하지 않는 회원입니다';</script>");
//		    out.flush();
//		    resp.flushBuffer();
//		    out.close();
			
//			PrintWriter writer = new PrintWriter(System.out);
//			writer.print("<script>frm.signinOutput.innerHTML='존재하지 않는 사용자입니다'</script>");
		}
		resp.sendRedirect(redirectStr);
	}
	
}
