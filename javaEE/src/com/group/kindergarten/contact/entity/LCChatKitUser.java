package com.group.kindergarten.contact.entity;

public class LCChatKitUser {

	private String userId;
	private String name;
	private String avatarUrl;

	public LCChatKitUser(String userId, String userName, String avatarUrl) {
		this.userId = userId;
		this.avatarUrl = avatarUrl;
		this.name = userName;
	}

	public LCChatKitUser() {

	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public String getName() {
		return null == name ? "" : name;
	}

	@Override
	public int hashCode() {
		int hashCode = (null == userId) ? 0 : userId.hashCode();
		hashCode = 73 * hashCode + ((null == avatarUrl) ? 0 : avatarUrl.hashCode());
		hashCode = 73 * hashCode + ((null == name) ? 0 : name.hashCode());
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj || !(obj instanceof LCChatKitUser)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		LCChatKitUser other = (LCChatKitUser) obj;
		if (null != this.userId && !this.userId.equals(other.userId)) {
			return false;
		} else if (null == this.userId && null != other.userId) {
			return false;
		}
		if (null != this.avatarUrl && !this.avatarUrl.equals(other.avatarUrl)) {
			return false;
		} else if (null == this.avatarUrl && null != other.avatarUrl) {
			return false;
		}
		if (null != this.name && !this.name.equals(other.name)) {
			return false;
		} else if (null == this.name && null != other.name) {
			return false;
		}
		return true;
	}
}
