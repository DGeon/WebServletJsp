package kr.co.ovmkas.jsp.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Board {
	private Long bno;
	@NonNull
	private String title;
	@NonNull
	private String content;
	@NonNull
	private String writer;
	private Date regDate;
	private String updatadate;
	private int hitcount;
	private int category;
	private int replyCnt;
}
