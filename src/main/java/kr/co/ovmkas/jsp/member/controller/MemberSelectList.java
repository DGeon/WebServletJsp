package kr.co.ovmkas.jsp.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.co.ovmkas.jsp.domain.Member;
import kr.co.ovmkas.jsp.service.MemberService;
import kr.co.ovmkas.jsp.service.MemberServiceImpl;

@WebServlet("/memberselect")
public class MemberSelectList extends HttpServlet{
	private MemberService memberService = new MemberServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Member> members = memberService.list();
		Gson gson = new Gson();
		String json = gson.toJson(members);
		resp.setContentType("application/json; charset=utf8;");
		resp.getWriter().print(json);
		System.out.println(json);
	}

}
