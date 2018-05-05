/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frent.model.Setting;

public class SettingRowMapper implements RowMapper<Setting> {

	public Setting mapRow(ResultSet arg0, int arg1) throws SQLException {
		Setting setting = new Setting();
		setting.setId(arg0.getInt("setting_id"));
		setting.setKeterangan(arg0.getString("setting_ket"));
		setting.setNama(arg0.getString("setting_nama"));
		setting.setTipe(arg0.getString("setting_tipe"));
		setting.setValue(arg0.getString("setting_value"));
		return setting;
	}

}
