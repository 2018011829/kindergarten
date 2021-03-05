package com.group.kindergarten.contact.entity;

/**
 * 好友请求
 * 
 * @author Aimer
 *
 */
public class RequestEntity {
	private int id;
	private int ReqUser;// 发出请求者
	private int RespUser;// 被请求者
	private int status;// 请求状态 0：无应答 1：接收 2：拒绝
	private String message;// 备注信息
	public String remark;// 给对方的备注

	public RequestEntity() {
		super();
	}

	public RequestEntity(int id, int reqUser, int respUser, int statu, String message, String remark) {
		super();
		this.id = id;
		ReqUser = reqUser;
		RespUser = respUser;
		this.status = statu;
		this.message = message;
		this.remark = remark;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReqUser() {
		return ReqUser;
	}

	public void setReqUser(int reqUser) {
		ReqUser = reqUser;
	}

	public int getRespUser() {
		return RespUser;
	}

	public void setRespUser(int respUser) {
		RespUser = respUser;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int statu) {
		this.status = statu;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "RequestEntity [id=" + id + ", ReqUser=" + ReqUser + ", RespUser=" + RespUser + ", status=" + status
				+ ", message=" + message + ", remark=" + remark + "]";
	}

}
