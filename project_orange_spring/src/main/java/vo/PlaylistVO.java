package vo;

public class PlaylistVO {
	private int idx;
	private int memberidx;		//회원인덱스
	private int musicidx;		//게시물인덱스
	private String m_musicfile;	//음악파일
	
	
	public PlaylistVO() {}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getMemberidx() {
		return memberidx;
	}

	public void setMemberidx(int memberidx) {
		this.memberidx = memberidx;
	}

	public int getMusicidx() {
		return musicidx;
	}

	public void setMusicidx(int musicidx) {
		this.musicidx = musicidx;
	}

	public String getM_musicfile() {
		return m_musicfile;
	}

	public void setM_musicfile(String m_musicfile) {
		this.m_musicfile = m_musicfile;
	}
}
