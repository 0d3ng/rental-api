/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.frent.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet arg0, int arg1) throws SQLException {
		User user = new User();
		user.setUser_id(arg0.getLong("user_id"));
		user.setUser_email(arg0.getString("user_email"));
		user.setUser_password(arg0.getString("user_password"));
		user.setUser_idcard(arg0.getString("user_idcard"));
		user.setUser_frontname(arg0.getString("user_frontname"));
		user.setUser_lastname(arg0.getString("user_lastname"));
		user.setUser_photo(arg0.getString("user_photo"));
		user.setUser_photo_idcard(arg0.getString("user_photo_idcard"));
		user.setUser_jk(arg0.getString("user_jk"));
		user.setUser_tanggallahir(arg0.getDate("user_tanggallahir") == null ? null
				: new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(new Date(arg0.getDate("user_tanggallahir").getTime())));
		user.setUser_phone(arg0.getString("user_phone"));
		user.setUser_address(arg0.getString("user_address"));
		user.setUser_active(arg0.getString("user_active").equals("1") ? true : false);
		user.setUser_verification(arg0.getString("user_verification").equals("1") ? true : false);
		user.setUser_apps(arg0.getString("user_apps"));
		user.setUser_token(arg0.getString("user_token"));
		user.setUser_groupid(arg0.getInt("user_groupid"));
		user.setUser_type(arg0.getString("user_type"));
		return user;
	}

}
