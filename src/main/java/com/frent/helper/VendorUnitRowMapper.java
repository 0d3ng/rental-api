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

import com.frent.model.VendorUnit;

public class VendorUnitRowMapper implements RowMapper<VendorUnit> {

	@Override
	public VendorUnit mapRow(ResultSet arg0, int arg1) throws SQLException {
		VendorUnit vendorUnit = new VendorUnit();
		vendorUnit.setVendorunit_bbm(arg0.getString("vendorunit_bbm").equals("1")?true:false);
		vendorUnit.setVendorunit_driver(arg0.getString("vendorunit_driver").equals("1")?true:false);
		vendorUnit.setVendorunit_id(arg0.getInt("vendorunit_id"));
		vendorUnit.setVendorunit_nominal(arg0.getInt("vendorunit_nominal"));
		vendorUnit.setVendorunit_tollparking(arg0.getString("vendorunit_tollparking").equals("1")?true:false);
		return vendorUnit;
	}

}
