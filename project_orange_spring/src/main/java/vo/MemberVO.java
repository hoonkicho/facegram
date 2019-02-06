package vo;

public class MemberVO {
	private int idx; 		//회원인덱스
	private String name; 	//회원이름 	
	private String id; 		//회원ID	
	private String pw; 		//회원PW
	private int age; 		//회원나이
	private String phone; 	//회원전화번호
	private int authority;	//회원 권한 일반:1 관리자2
	public MemberVO() {}

	public MemberVO(int idx, String name, String id, String pw, int age, String phone, int authority) {
		this.idx = idx;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.age = age;
		this.phone = phone;
		this.authority = authority;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
}
