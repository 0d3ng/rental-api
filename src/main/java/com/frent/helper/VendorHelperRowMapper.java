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

public class VendorHelperRowMapper implements RowMapper<VendorHelper> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public VendorHelper mapRow(ResultSet arg0, int arg1) throws SQLException {
		VendorHelper helper = new VendorHelper();
		helper.setId(arg0.getInt("vendor_id"));
		helper.setNama(arg0.getString("vendor_name"));
		helper.setLogo(arg0.getString("vendor_logo"));
		helper.setAlamat(arg0.getString("vendor_address"));
		helper.setTelfon(arg0.getString("vendor_phone"));
		helper.setEmail(arg0.getString("vendor_email"));
		helper.setWebsite(arg0.getString("vendor_website"));
		helper.setFoto(arg0.getString("vendorfoto_photo"));
		return helper;
	}

}
