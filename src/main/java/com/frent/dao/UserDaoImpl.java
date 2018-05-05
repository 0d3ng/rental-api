package com.frent.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.frent.helper.UserRowMapper;
import com.frent.helper.UserUpdate;
import com.frent.model.User;

/**
 * @author odeng
 * 
 */

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}

	// private Logger logger = Logger.getLogger(UserDaoImpl.class);

	private final String LOGIN = "SELECT `user_id`,`user_email`,`user_password`,`user_idcard`,`user_frontname`,`user_lastname`,`user_photo`,`user_photo_idcard`,`user_jk`,`user_tanggallahir`,`user_phone`,`user_address`,`user_active`,`user_verification`,`user_apps`,`user_token`,user_groupid,user_type FROM `web_user` "
			+ "WHERE user_email=? " + "AND user_password=?";
	private final String GET_USER_BY_EMAIL = "SELECT `user_id`,`user_email`,`user_password`,`user_idcard`,`user_frontname`,`user_lastname`,`user_photo`,`user_photo_idcard`,`user_jk`,`user_tanggallahir`,`user_phone`,`user_address`,`user_active`,`user_verification`,`user_apps`,`user_token`,user_groupid,user_type FROM `web_user` "
			+ "WHERE user_email=?";
	private final String PROFILE = "SELECT `user_id`,`user_email`,`user_password`,`user_idcard`,`user_frontname`,`user_lastname`,`user_photo`,`user_photo_idcard`,`user_jk`,`user_tanggallahir`,`user_phone`,`user_address`,`user_active`,`user_verification`,`user_apps`,`user_token`,user_groupid,user_type FROM `web_user` "
			+ "WHERE user_id=?";
	private final String REQUEST_TOKEN = "SELECT `user_id`,`user_email`,`user_password`,`user_idcard`,`user_frontname`,`user_lastname`,`user_photo`,`user_photo_idcard`,`user_jk`,`user_tanggallahir`,`user_phone`,`user_address`,`user_active`,`user_verification`,`user_apps`,`user_token`,user_groupid,user_type FROM `web_user` "
			+ "WHERE user_id=? " + "AND user_token=?";
	private final String INSERT = "INSERT INTO `web_user` (`user_email`,`user_password`,`user_idcard`,`user_frontname`,`user_lastname`,`user_photo`,`user_photo_idcard`,`user_jk`,`user_tanggallahir`,`user_phone`,`user_address`,`user_active`,`user_verification`,`user_apps`,user_type,`user_token`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String UPDATE = "UPDATE `web_user` SET `user_email`=?,`user_password`=?,`user_idcard`=?,`user_frontname`=?,`user_lastname`=?,`user_photo`=?,`user_photo_idcard`=?,`user_jk`=?,`user_tanggallahir`=?,`user_phone`=?,`user_address`=?,`user_active`=?,`user_verification`=?,`user_apps`=?,`user_token`=? "
			+ "WHERE `user_id`=?";
	private final String UPDATE_PROFILE = "UPDATE `web_user` SET `user_email`=?,`user_idcard`=?,`user_frontname`=?,`user_lastname`=?,`user_jk`=?,`user_tanggallahir`=?,`user_phone`=?,`user_address`=? "
			+ "WHERE `user_id`=?";
	private final String UPDATE_FOTO_PROFIL = "UPDATE `web_user` SET `user_photo`=? "
			+ "WHERE `user_id`=?";
	private final String UPDATE_FOTO_ID_CARD = "UPDATE `web_user` SET `user_photo_idcard`=? "
			+ "WHERE `user_id`=?";

	@Override
	public User doLogin(String useremail, String userpassword) {
		List<User> users = getJdbcTemplate().query(LOGIN, new Object[] { useremail, userpassword },
				new UserRowMapper());
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public boolean doInsert(User user) {
		int update = getJdbcTemplate().update(INSERT,
				new Object[] { user.getUser_email(), user.getUser_password(), user.getUser_idcard(),
						user.getUser_frontname(), user.getUser_lastname(), user.getUser_photo(),
						user.getUser_photo_idcard(), user.getUser_jk(), user.getUser_tanggallahir(),
						user.getUser_phone(), user.getUser_address(), "1", "0", "M", "M", user.getUser_token() });
		return update > 0 ? true : false;
	}

	@Override
	public boolean doUpdate(User user) {
		int update = getJdbcTemplate().update(UPDATE,
				new Object[] { user.getUser_email(), user.getUser_password(), user.getUser_idcard(),
						user.getUser_frontname(), user.getUser_lastname(), user.getUser_photo(),
						user.getUser_photo_idcard(), user.getUser_jk(), user.getUser_tanggallahir(),
						user.getUser_phone(), user.getUser_address(), user.isUser_active() ? "1" : "0",
						user.isUser_verification() ? "1" : "0", "M", user.getUser_token(), user.getUser_id() });
		return update > 0 ? true : false;
	}

	@Override
	public User doRequestToken(String userId, String token) {
		List<User> users = getJdbcTemplate().query(REQUEST_TOKEN, new Object[] { userId, token }, new UserRowMapper());
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User doRequestProfile(String userId) {
		List<User> users = getJdbcTemplate().query(PROFILE, new Object[] { userId }, new UserRowMapper());
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	@Override
	public User doGetUserId(String email) {
		List<User> users = getJdbcTemplate().query(GET_USER_BY_EMAIL, new Object[] { email }, new UserRowMapper());
		if (users == null || users.isEmpty()) {
			return null;
		} else {
			return users.get(0);
		}
	}

	/* (non-Javadoc)
	 * @see com.frent.dao.UserDao#doUpdateProfile(com.frent.helper.UserUpdate)
	 */
	@Override
	public boolean doUpdateProfile(UserUpdate user) {
		int update = getJdbcTemplate().update(UPDATE_PROFILE,
				new Object[] { user.getEmail(), user.getIdCard(),
						user.getNamaDepan(), user.getNamaBelakang(), user.getJenisKelamin(),
						user.getTanggalLahir(), user.getTelfon(), user.getAlamat(),
						user.getId() });
		return update > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.frent.dao.UserDao#doUpdateFotoProfile(long)
	 */
	@Override
	public boolean doUpdateFotoProfile(User user) {
		int update = getJdbcTemplate().update(UPDATE_FOTO_PROFIL,
				new Object[] { user.getUser_photo(),user.getUser_id()});
		return update > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.frent.dao.UserDao#doUpdateFotoIdCard(long)
	 */
	@Override
	public boolean doUpdateFotoIdCard(User user) {
		int update = getJdbcTemplate().update(UPDATE_FOTO_ID_CARD,
				new Object[] { user.getUser_photo_idcard(),user.getUser_id()});
		return update > 0 ? true : false;
	}

}
