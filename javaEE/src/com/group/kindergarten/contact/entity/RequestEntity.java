package com.group.kindergarten.contact.entity;
/**
 * ��������
 * @author Aimer
 *
 */
public class RequestEntity {
	private int id;
	private int ReqUser;// ����������
	private int RespUser;// ��������
	private int statu;// ����״̬ 0����Ӧ�� 1������ 2���ܾ�

	public RequestEntity() {
		super();
	}

	public RequestEntity(int id, int reqUser, int respUser, int statu) {
		super();
		this.id = id;
		ReqUser = reqUser;
		RespUser = respUser;
		this.statu = statu;
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

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	@Override
	public String toString() {
		return "RequestEntity [id=" + id + ", ReqUser=" + ReqUser + ", RespUser=" + RespUser + ", statu=" + statu + "]";
	}

}
