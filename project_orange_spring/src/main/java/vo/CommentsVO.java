package vo;

public class CommentsVO {
	private int idx;
	private String c_name;		//댓글작성자이름
	private String c_content;	//댓글내용
	private String c_date;		//댓글작성일
	private int c_goodpoint;	//댓글좋아요
	private int musicidx;		//게시물인덱스
	private int memberidx;		//회원인덱스

	public CommentsVO() {}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public int getC_goodpoint() {
		return c_goodpoint;
	}

	public void setC_goodpoint(int c_goodpoint) {
		this.c_goodpoint = c_goodpoint;
	}

	public int getMusicidx() {
		return musicidx;
	}

	public void setMusicidx(int musicidx) {
		this.musicidx = musicidx;
	}
	public int getMemberidx() {
		return memberidx;
	}
	public void setMemberidx(int memberidx) {
		this.memberidx = memberidx;
	}
}
