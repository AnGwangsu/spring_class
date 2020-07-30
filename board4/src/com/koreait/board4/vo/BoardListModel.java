package com.koreait.board4.vo;

public class BoardListModel extends BoardVO{
	private String userNm;
	private int likeUser;
	private int starIdx;
	private int endIdx;

	public int getStarIdx() {
		return starIdx;
	}

	public void setStarIdx(int starIdx) {
		this.starIdx = starIdx;
	}

	public int getEndIdx() {
		return endIdx;
	}

	public void setEndIdx(int endIdx) {
		this.endIdx = endIdx;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public int getLikeUser() {
		return likeUser;
	}

	public void setLikeUser(int likeUser) {
		this.likeUser = likeUser;
	}
	
	
}
