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

import com.frent.model.Vendor;

public class VendorRowMapper implements RowMapper<Vendor> {

	@Override
	public Vendor mapRow(ResultSet arg0, int arg1) throws SQLException {
		Vendor vendor = new Vendor();
		vendor.setVendor_id(arg0.getInt("vendor_id"));
		vendor.setVendor_name(arg0.getString("vendor_name"));
		vendor.setVendor_logo(arg0.getString("vendor_logo"));
		vendor.setVendor_address(arg0.getString("vendor_address"));
		vendor.setVendor_phone(arg0.getString("vendor_phone"));
		vendor.setVendor_email(arg0.getString("vendor_email"));
		vendor.setVendor_website(arg0.getString("vendor_website"));
		vendor.setVendor_tahunberdiri(arg0.getInt("vendor_tahunberdiri"));
		vendor.setVendor_pendiri(arg0.getString("vendor_pendiri"));
		vendor.setVendor_detail(arg0.getString("vendor_detail"));
		vendor.setVendor_bankdetail(arg0.getString("vendor_bankdetail"));
		vendor.setVendor_policy(arg0.getString("vendor_policy"));
		vendor.setVendor_latitude(arg0.getString("vendor_latitude"));
		vendor.setVendor_longitude(arg0.getString("vendor_longitude"));
		return vendor;
	}

}
