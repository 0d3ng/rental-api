package com.frent.dao;

import com.frent.helper.UserUpdate;
import com.frent.model.User;

/**
 * @author odeng
 * 
 */

public interface UserDao {

	public User doLogin(String useremail, String userpassword);

	public User doRequestToken(String userId, String token);

	public User doRequestProfile(String userId);

	public boolean doInsert(User user);

	public boolean doUpdate(User user);

	public boolean doUpdateProfile(UserUpdate user);
	
	public boolean doUpdateFotoProfile(User user);
	
	public boolean doUpdateFotoIdCard(User user);

	public User doGetUserId(String email);
}
