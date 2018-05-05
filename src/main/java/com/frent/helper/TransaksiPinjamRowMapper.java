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

import com.frent.model.TransaksiPinjam;
import com.frent.model.Unit;
import com.frent.model.User;
import com.frent.model.Vendor;
import com.frent.model.VendorLokasi;
import com.frent.model.VendorUnit;

public class TransaksiPinjamRowMapper implements RowMapper<TransaksiPinjam> {

	@Override
	public TransaksiPinjam mapRow(ResultSet arg0, int arg1) throws SQLException {
		TransaksiPinjam pinjam = new TransaksiPinjam();
		User user = new User();
		VendorLokasi vendorLokasi = new VendorLokasi();
		VendorUnit vendorUnit = new VendorUnit();
		Vendor vendor = new Vendor();
		vendor.setVendor_name(arg0.getString("vendor_name"));
		Unit unit = new Unit();
		unit.setUnit_name(arg0.getString("unit_name"));
		user.setUser_id(arg0.getLong("trxpinjam_userid"));
		pinjam.setUser(user);
		vendorLokasi.setVendorloc_id(arg0.getInt("trxpinjam_vendorlocationid"));
		pinjam.setVendorLokasi(vendorLokasi);
		vendorUnit.setVendorunit_id(arg0.getInt("trxpinjam_vendorlocationid"));
		vendorUnit.setVendor(vendor);
		vendorUnit.setUnit(unit);
		pinjam.setVendorUnit(vendorUnit);
		pinjam.setTrxpinjam_id(arg0.getString("trxpinjam_id"));
		pinjam.setTrxpinjam_tanggalmulai(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("trxpinjam_tanggalmulai").getTime())));
		pinjam.setTrxpinjam_tanggalselesai(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("trxpinjam_tanggalselesai").getTime())));
		pinjam.setTrxpinjam_detail(arg0.getString("trxpinjam_detail"));
		pinjam.setTrxpinjam_specialrequest(arg0.getString("trxpinjam_specialrequest"));
		pinjam.setTrxpinjam_totalunit(arg0.getInt("trxpinjam_totalunit"));
		pinjam.setTrxpinjam_totalnominal(arg0.getInt("trxpinjam_totalnominal"));
		pinjam.setTrxpinjam_date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(arg0.getDate("trxpinjam_date").getTime())));
		return pinjam;
	}

}
