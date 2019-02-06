package vo;

public class MyfriendVO {
	private int idx;
	private	String myid;		//내아이디
	private String myname;		//내이름	
	private String friendname;	//상대방아이디
	private String friendid;	//상대방이름
	
	
	public MyfriendVO() {	}


	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getMyid() {
		return myid;
	}


	public void setMyid(String myid) {
		this.myid = myid;
	}


	public String getMyname() {
		return myname;
	}


	public void setMyname(String myname) {
		this.myname = myname;
	}


	public String getFriendname() {
		return friendname;
	}


	public void setFriendname(String friendname) {
		this.friendname = friendname;
	}


	public String getFriendid() {
		return friendid;
	}


	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}
	
	
	
}
