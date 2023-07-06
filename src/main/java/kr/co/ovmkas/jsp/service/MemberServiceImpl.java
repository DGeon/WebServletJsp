package kr.co.ovmkas.jsp.service;

import java.util.List;

import kr.co.ovmkas.jsp.dao.MemberDao;
import kr.co.ovmkas.jsp.domain.Member;

public class MemberServiceImpl implements MemberService{
	private MemberDao dao = new MemberDao();
	
	@Override
	public void register(Member member) {
		dao.insert(member);
	}

	@Override
	public void modify(Member member) {
		dao.update(member);
	}

	@Override
	public void remove(Member member) {
		dao.updateBoard(member);
		dao.updateReply(member);
		dao.delete(member);
	}

	@Override
	public int login(Member member) {
		member = dao.findBy(member);
//		if (member == null) {
//			// 로그인 실패(아이디 없음)
//			return 2;
//		} else if (!member.getPw().equals("a1234")) {
//			//로그인 실패 비밀번호 다름
//			return 3;
//		} else {
//			//로그인성공
//			return 1;
//		}
		return 1;
	}

	@Override
	public Member get(Member member) {
		return dao.findBy(member);
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}
	
}
