package kr.co.ovmkas.jsp.service;

import java.util.List;

import kr.co.ovmkas.jsp.domain.Member;
import kr.co.ovmkas.jsp.domain.Reply;

public interface MemberService {

	/**
	 * 회원가입 메소드
	 * @param member
	 */
	void register(Member member);
	
	/**
	 * 회원정보수정 메소드
	 * @param member
	 */
	void modify(Member member);

	/**
	 * 회원삭제 메소드
	 * @param member
	 */
	void remove(Member member);
	//로그인
	/**
	 * 회원조회(Login)
	 * 회원성공시 값을 1리턴 하며
	 * 아직 활용하지 못한 상태
	 * @param member
	 * @return
	 */
	int login(Member member);
	//조회
	/**
	 * 회원 조회(Login)
	 * @param member
	 * @return
	 */
	Member get(Member member);
	
	List<Member> list();
}
