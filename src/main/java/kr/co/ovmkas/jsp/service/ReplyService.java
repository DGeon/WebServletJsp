package kr.co.ovmkas.jsp.service;

import java.util.List;

import kr.co.ovmkas.jsp.domain.Reply;

public interface ReplyService {
	
	/**
	 * 댓글등록
	 * @param reply
	 * @return
	 */
	Long register(Reply reply);
	
	/**
	 * 특정 게시판 댓글 리스트
	 * @param bno
	 * @return
	 */
	List<Reply> list(Long bno);
	
	/**
	 * 단일 댓글 조회
	 * @param rno
	 * @return
	 */
	Reply get(Long rno);
	
	/**
	 * 댓글 삭제
	 * @param bno
	 * @return
	 */
	int remove(Long bno);
}
