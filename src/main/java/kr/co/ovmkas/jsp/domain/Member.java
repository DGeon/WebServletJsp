package kr.co.ovmkas.jsp.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String id;
	private String pw;
	private String name;
	private Date regdate;
	
	/**
	 * 필요 없을 시 제거
	 * @param id
	 * @param pw
	 */
	public Member (String id, String pw) {
		this.id = id;
		this.pw = pw;
	}
}
