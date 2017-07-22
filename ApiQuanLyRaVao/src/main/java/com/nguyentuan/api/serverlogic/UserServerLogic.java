package com.nguyentuan.api.serverlogic;

import java.util.List;

import com.nguyentuan.api.entity.UserEntity;
import com.nguyentuan.api.model.UserModel;

public interface UserServerLogic {
	
	UserModel checkUser(String username, String pass);
	
	

}
