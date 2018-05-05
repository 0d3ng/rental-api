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

public class OrderRowMapper implements RowMapper<Pesanan> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public Pesanan mapRow(ResultSet arg0, int arg1) throws SQLException {
		Pesanan pesanan = new Pesanan();
		pesanan.setId(arg0.getString("trxpinjam_id"));
		pesanan.setTanggalmulai(arg0.getString("trxpinjam_tanggalmulai"));
		pesanan.setTanggalselesai(arg0.getString("trxpinjam_tanggalselesai"));
		pesanan.setDetail(arg0.getString("trxpinjam_detail"));
		pesanan.setSpecialrequest(arg0.getString("trxpinjam_specialrequest"));
		pesanan.setTotalunit(arg0.getInt("trxpinjam_totalunit"));
		pesanan.setTotalnominal(arg0.getInt("trxpinjam_totalnominal"));
		pesanan.setDate(arg0.getString("trxpinjam_date"));
		pesanan.setKodebayar(arg0.getInt("trxpinjam_kodebayar"));
		pesanan.setDriver(arg0.getInt("vendorunit_driver") == 1 ? true : false);
		pesanan.setBbm(arg0.getInt("vendorunit_bbm") == 1 ? true : false);
		pesanan.setTollparking(arg0.getInt("vendorunit_tollparking") == 1 ? true : false);
		pesanan.setNominal(arg0.getInt("vendorunit_nominal"));
		pesanan.setUnit(arg0.getString("unit_name"));
		pesanan.setThumbnail(arg0.getString("unit_thumbnail"));
		pesanan.setBaggage(arg0.getInt("unit_baggage"));
		pesanan.setPassenger(arg0.getInt("unit_passenger"));
		pesanan.setVendor(arg0.getString("vendor_name"));
		pesanan.setPaket(arg0.getString("paket_name"));
		pesanan.setLokasi(arg0.getString("location_name"));
		pesanan.setStatus(arg0.getString("confirmation_admin"));
		return pesanan;
	}

}
