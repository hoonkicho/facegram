package vo;

public class MessageVO {
	private int idx;
	private int sendIdx;		//보내는사람인덱스
	private String sendId;		//보내는사람ID
	private String receivedId;	//받는사람ID
	private String msg;			//메세지내용
	
	public MessageVO() {
	}

	public MessageVO(int idx, int sendIdx, String sendId, String receivedId, String msg) {
		this.idx = idx;
		this.sendIdx = sendIdx;
		this.sendId = sendId;
		this.receivedId = receivedId;
		this.msg = msg;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getSendIdx() {
		return sendIdx;
	}

	public void setSendIdx(int sendIdx) {
		this.sendIdx = sendIdx;
	}

	public String getSendId() {
		return sendId;
	}
	
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getReceivedId() {
		return receivedId;
	}

	public void setReceivedId(String receivedId) {
		this.receivedId = receivedId;
	}

	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
