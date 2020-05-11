package com.sist.dao;

import lombok.Getter;
import lombok.Setter;


public class ReserveTheaterVO {
	private int tno;
	private String tname;
	private String tloc;
	private String tdate;
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getTloc() {
		return tloc;
	}
	public void setTloc(String tloc) {
		this.tloc = tloc;
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	
	
}
