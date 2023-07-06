package kr.co.ovmkas.jsp.service;

import java.util.List;

import kr.co.ovmkas.jsp.dao.ReplyDao;
import kr.co.ovmkas.jsp.domain.Reply;

public class ReplyServiceImpl implements ReplyService{
	private ReplyDao dao = new ReplyDao();
	
	/**
	 * 댓글 등록
	 */
	@Override
	public Long register(Reply reply) {
		return (long)dao.insert(reply);
	}

	/**
	 * 특정 게시판 내에 댓글 리스트조회
	 */
	@Override
	public List<Reply> list(Long bno) {
		return dao.selectList(bno);
	}

	/**
	 *	댓글 단일 조회
	 */
	@Override
	public Reply get(Long rno) {
		return dao.selectOne(rno);
	}

	/**
	 * 댓글삭제
	 */
	@Override
	public int remove(Long bno) {
		return dao.delete(bno);
	}

}
