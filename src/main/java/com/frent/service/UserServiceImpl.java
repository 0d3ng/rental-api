package com.frent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frent.dao.UserDao;
import com.frent.helper.UserUpdate;
import com.frent.model.User;

/**
 * @author odeng
 * 
 */

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	@Override
	public User doLogin(String useremail, String userpassword) {
		return userDao.doLogin(useremail,userpassword);
	}

	@Override
	public boolean doInsert(User user) {
		return userDao.doInsert(user);
	}

	@Override
	public boolean doUpdate(User user) {
		return userDao.doUpdate(user);
	}

	@Override
	public User doRequestToken(String userId, String token) {
		return userDao.doRequestToken(userId, token);
	}

	@Override
	public User doRequestProfile(String userId) {
		return userDao.doRequestProfile(userId);
	}

	@Override
	public User doGetUserId(String email) {
		return userDao.doGetUserId(email);
	}

	/* (non-Javadoc)
	 * @see com.frent.service.UserService#doUpdateUser(com.frent.helper.UserUpdate)
	 */
	@Override
	public boolean doUpdateProfile(UserUpdate user) {
		return userDao.doUpdateProfile(user);
	}

	/* (non-Javadoc)
	 * @see com.frent.service.UserService#doUpdateFotoProfile(com.frent.model.User)
	 */
	@Override
	public boolean doUpdateFotoProfile(User user) {
		return userDao.doUpdateFotoProfile(user);
	}

	/* (non-Javadoc)
	 * @see com.frent.service.UserService#doUpdateFotoIdCard(com.frent.model.User)
	 */
	@Override
	public boolean doUpdateFotoIdCard(User user) {
		return userDao.doUpdateFotoIdCard(user);
	}

}
