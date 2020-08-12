package com.springbook.board.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	public int join(UserVO uvo);
	public UserVO selUser(UserVO uvo);
	public int updUser(UserVO uvo);
	
}

