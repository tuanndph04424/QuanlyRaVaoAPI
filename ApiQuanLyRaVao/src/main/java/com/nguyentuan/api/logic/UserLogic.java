package com.nguyentuan.api.logic;

import java.util.List;

import com.nguyentuan.api.entity.UserEntity;
import com.nguyentuan.api.model.UserModel;

public interface UserLogic {
	
	UserModel checkUser(String username, String pass);
	
	

}
