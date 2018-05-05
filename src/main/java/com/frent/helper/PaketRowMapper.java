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

import com.frent.model.Paket;

public class PaketRowMapper implements RowMapper<Paket> {

	@Override
	public Paket mapRow(ResultSet arg0, int arg1) throws SQLException {
		Paket paket = new Paket();
		paket.setPaket_id(arg0.getInt("paket_id"));
		paket.setPaket_name(arg0.getString("paket_name"));
		paket.setPaket_jumlahjam(arg0.getInt("paket_jumlahjam"));
		return paket;
	}

}
