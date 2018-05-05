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

public class TransaksiVendorHelperRowMapper implements RowMapper<TransaksiVendorHelper> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public TransaksiVendorHelper mapRow(ResultSet arg0, int arg1) throws SQLException {
		TransaksiVendorHelper helper = new TransaksiVendorHelper();
		helper.setId(arg0.getString("trxpinjam_id"));
		helper.setTanggal(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("trxpinjam_tanggalmulai").getTime())));
		helper.setUnit(arg0.getString("unit_name"));
		helper.setEmail(arg0.getString("user_email"));
		helper.setIdcard(arg0.getString("user_idcard"));
		helper.setFirstname(arg0.getString("user_frontname"));
		helper.setLastname(arg0.getString("user_lastname"));
		helper.setPhone(arg0.getString("user_photo"));
		helper.setPhoto_idcard(arg0.getString("user_photo_idcard"));
		helper.setJk(arg0.getString("user_jk"));
		helper.setTanggallahir(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("user_tanggallahir").getTime())));
		helper.setPhone(arg0.getString("user_phone"));
		helper.setAddress(arg0.getString("user_address"));
		return helper;
	}

}
