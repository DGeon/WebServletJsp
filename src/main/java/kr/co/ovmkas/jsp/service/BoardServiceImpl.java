package kr.co.ovmkas.jsp.service;

import java.util.List;

import kr.co.ovmkas.jsp.dao.BoardDao;
import kr.co.ovmkas.jsp.domain.Board;
import kr.co.ovmkas.jsp.domain.Criteria;

public class BoardServiceImpl implements BoardService{
	private BoardDao dao = new BoardDao();

	@Override
	public Long register(Board board) {
		Long bno = dao.insert(board);
		return bno;
	}

	@Override
	public Board get(Long bno) {
		Board board = dao.selectOne(bno);
		return board;
	}

	@Override
	public List<Board> list(Criteria cri) {
		
		return dao.selectList(cri);
	}

	@Override
	public void modify(Board board) {
		dao.update(board);
	}

	@Override
	public void remove(Long bno) {
		dao.deleteReply(bno);
		dao.delete(bno);
	}

	@Override
	public int listCount(Criteria cri) {
		return dao.selectListCount(cri);
	}
}
