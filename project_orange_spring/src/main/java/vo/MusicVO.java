package vo;

public class MusicVO {
	private int idx; 
	private String m_title;		//게시물제목
	private String m_content; 	//게시물내용
	private String m_musicfile;	//음악파일
	private String m_image; 	//이미지파일	
	private String m_date;		//게시물작성일
	private String name;		//작성자이름
	private int m_goodpoint;	//게시물좋아요
	private int memberidx;		//회원인덱스
	private String memberid;	//회원아이디	
	public MusicVO() {}
	
	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getMemberidx() {
		return memberidx;
	}
	public void setMemberidx(int memberidx) {
		this.memberidx = memberidx;
	}
	
	public int getM_goodpoint() {
		return m_goodpoint;
	}
	public void setM_goodpoint(int m_goodpoint) {
		this.m_goodpoint = m_goodpoint;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getM_title() {
		return m_title;
	}
	public void setM_title(String m_title) {
		this.m_title = m_title;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public String getM_musicfile() {
		return m_musicfile;
	}
	public void setM_musicfile(String m_musicfile) {
		this.m_musicfile = m_musicfile;
	}
	public String getM_image() {
		return m_image;
	}
	public void setM_image(String m_image) {
		this.m_image = m_image;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
}
