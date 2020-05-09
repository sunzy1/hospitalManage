package cm.pojo;

import java.sql.Date;

public class Medicine {
	// 编号mno：char(12)
	// 名称mname：nvarchar(50)
	// 服用方法mmode：nchar(2) 内服 或 外用
	// 功效mefficacy：nvarchar(50)
	// 录入日期 mdate：datetime(8)
	private int mid;
	private String mno;
	private String mname;
	private String mmode;
	private String mefficacy;
	private String mremark;
	private Date mdate;

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getMmode() {
		return mmode;
	}

	public void setMmode(String mmode) {
		this.mmode = mmode;
	}

	public String getMefficacy() {
		return mefficacy;
	}

	public void setMefficacy(String mefficacy) {
		this.mefficacy = mefficacy;
	}

	public String getMremark() {
		return mremark;
	}

	public void setMremark(String mremark) {
		this.mremark = mremark;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public Date getMdate() {
		return mdate;
	}

	@Override
	public String toString() {
		return "Medicine{" +
				"mid=" + mid +
				", mno='" + mno + '\'' +
				", mname='" + mname + '\'' +
				", mmode='" + mmode + '\'' +
				", mefficacy='" + mefficacy + '\'' +
				", mremark='" + mremark + '\'' +
				", mdate=" + mdate +
				'}';
	}
}
