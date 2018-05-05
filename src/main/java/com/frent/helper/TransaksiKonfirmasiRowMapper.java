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

import com.frent.model.TransaksiKonfirmasi;

public class TransaksiKonfirmasiRowMapper implements RowMapper<TransaksiKonfirmasi> {

	@Override
	public TransaksiKonfirmasi mapRow(ResultSet arg0, int arg1) throws SQLException {
		TransaksiKonfirmasi konfirmasi = new TransaksiKonfirmasi();
		konfirmasi.setConfirmation_id(arg0.getInt("confirmation_id"));
		konfirmasi.setConfirmation_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("confirmation_date").getTime())));
		konfirmasi.setConfirmation_image(arg0.getString("confirmation_image"));
		konfirmasi.setConfirmation_bank(arg0.getInt("confirmation_bank"));
		return konfirmasi;
	}

}
