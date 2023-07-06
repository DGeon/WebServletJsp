package kr.co.ovmkas.jsp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Criteria {
	private int pageNumber = 1;
	private int amount = 10;
	private int category = 1;
	private String[] check;
	private String search ="";
	
	public Criteria(int pageNumber, int amount) {
		super();
		this.pageNumber = pageNumber;
		this.amount = amount;
	}
	public String getQueryStr() {
		String str = "";
		str += "amount=" + amount + "&category="+category;
		str += getCheckStr();
		return str;
	}
	
	public String getFullQueryStr() {
		String str ="pageNumber="+ pageNumber + "&";
		str += getQueryStr();
		return str;
	}
	
	public String getCheckStr() {
		String str ="";
		if(check != null) {
			for(String s: check) {
				str += "&check=" +s;
			}
			str += "&search=" + search;
		}
		return str;
	}
}
