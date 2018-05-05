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

import com.frent.model.TransaksiKomentar;

public class TransaksiKomentarRowMapper implements RowMapper<TransaksiKomentar> {

	@Override
	public TransaksiKomentar mapRow(ResultSet arg0, int arg1) throws SQLException {
		TransaksiKomentar testimoni = new TransaksiKomentar();
		testimoni.setTestimoni_id(arg0.getInt("testimoni_id"));
		testimoni.setTestimoni_content(arg0.getString("testimoni_content"));
		testimoni.setTestimoni_create(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("testimoni_create").getTime())));
		testimoni.setTestimoni_rate(arg0.getInt("testimoni_rate"));
		return testimoni;
	}

}
