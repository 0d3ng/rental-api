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

import com.frent.model.Lokasi;

public class LokasiRowMapper implements RowMapper<Lokasi> {

	@Override
	public Lokasi mapRow(ResultSet arg0, int arg1) throws SQLException {
		Lokasi lokasi = new Lokasi();
		lokasi.setLocation_id(arg0.getInt("location_id"));
		lokasi.setLocation_name(arg0.getString("location_name"));
		lokasi.setLocation_photo(arg0.getString("location_photo"));
		lokasi.setLocation_detail(arg0.getString("location_detail"));
		return lokasi;
	}

}
