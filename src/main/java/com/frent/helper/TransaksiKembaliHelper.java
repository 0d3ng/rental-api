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

import com.frent.model.TransaksiKembali;

public class TransaksiKembaliHelper implements RowMapper<TransaksiKembali>{

	@Override
	public TransaksiKembali mapRow(ResultSet arg0, int arg1) throws SQLException {
		TransaksiKembali kembali = new TransaksiKembali();
		kembali.setId(arg0.getLong("kembali_id"));
		kembali.setImage(arg0.getString("kembali_image"));
		kembali.setNote(arg0.getString("kembali_note"));
		kembali.setTanggal(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("kembali_date").getTime())));
		kembali.setTransaksiId(arg0.getString("kembali_pinjamid"));
		kembali.setUserId(arg0.getLong("kembali_userid"));
		return kembali;
	}

}
