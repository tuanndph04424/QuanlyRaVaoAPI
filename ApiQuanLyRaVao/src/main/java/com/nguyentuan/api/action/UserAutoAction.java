package com.nguyentuan.api.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyentuan.api.model.UserModel;
import com.nguyentuan.api.serverlogic.UserServerLogic;
import com.nguyentuan.api.serverlogicImpl.*;

@RestController
public class UserAutoAction {
	@Autowired
	UserServerLogicImpl abc;

	@RequestMapping(value = "checklogin/{id}/{password}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String checklogin(@PathVariable("id") String id, @PathVariable("password") String password)
			throws JsonProcessingException {

		UserModel userModel = new UserModel();
		ObjectMapper obj = new ObjectMapper();

		return obj.writeValueAsString(abc.checkUser(id, password));
	}

}
