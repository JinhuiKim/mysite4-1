package kr.ac.sungkyul.mysite.vo;

public class BoardVo extends UserVo {
	private Long no;
	private String title;
	private String content;
	private String regDate;
	private Long viewNo;
	private Long groupNo;
	private Long orderNo;
	private Long depth;
	private String userName;
	private Long userNo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getViewNo() {
		return viewNo;
	}
	public void setViewNo(Long viewNo) {
		this.viewNo = viewNo;
	}
	public Long getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(Long groupNo) {
		this.groupNo = groupNo;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}

	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", regDate=" + regDate + ", viewNo="
				+ viewNo + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth=" + depth + ", userName="
				+ userName + ", userNo=" + userNo + "]";
	}
	

	
	
	
}
