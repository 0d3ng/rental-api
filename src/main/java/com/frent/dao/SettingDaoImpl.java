/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.frent.helper.SettingRowMapper;
import com.frent.model.Setting;

@Repository
public class SettingDaoImpl extends JdbcDaoSupport implements SettingDao {

	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String GET_ALL = "SELECT " + "  `setting_id`," + "  `setting_nama`," + "  `setting_value`,"
			+ "  `setting_ket`," + "  `setting_tipe` " + "FROM" + "  `web_setting`";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.odeng.id.springboot.dao.SettingDao#settings()
	 */
	public HashMap<String, String> settings() {
		HashMap<String, String> map = new HashMap<>();
		List<Setting> query = getJdbcTemplate().query(GET_ALL, new SettingRowMapper());
		for (Setting setting : query) {
			map.put(setting.getNama(), setting.getValue());
		}
		return map;
	}

	@Override
	public List<Setting> getSettings() {
		return getJdbcTemplate().query(GET_ALL, new SettingRowMapper());
	}

}
