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

import com.frent.model.Kategori;
import com.frent.model.Lokasi;
import com.frent.model.Paket;
import com.frent.model.Unit;
import com.frent.model.Vendor;
import com.frent.model.VendorLokasi;
import com.frent.model.VendorUnit;

public class CariRowMapper implements RowMapper<VendorUnit> {

	@Override
	public VendorUnit mapRow(ResultSet arg0, int arg1) throws SQLException {
		Vendor vendor = new Vendor();
		vendor.setVendor_id(arg0.getInt("vendor_id"));
		vendor.setVendor_name(arg0.getString("vendor_name"));
		vendor.setVendor_logo(arg0.getString("vendor_logo"));
		vendor.setVendor_policy(arg0.getString("vendor_policy"));

		Kategori kategori = new Kategori();
		kategori.setCategories_id(arg0.getInt("categories_id"));
		
		Unit unit = new Unit();
		unit.setUnit_id(arg0.getInt("unit_id"));
		unit.setUnit_thumbnail(arg0.getString("unit_thumbnail"));
		unit.setUnit_name(arg0.getString("unit_name"));
		unit.setUnit_baggage(arg0.getInt("unit_baggage"));
		unit.setUnit_passenger(arg0.getInt("unit_passenger"));
		unit.setKategori(kategori);

		Lokasi lokasi = new Lokasi();
		lokasi.setLocation_id(arg0.getInt("vendorloc_id"));
		lokasi.setLocation_detail(arg0.getString("location_detail"));

		VendorLokasi vendorLokasi = new VendorLokasi();
		vendorLokasi.setVendorloc_id(arg0.getInt("vendorloc_locationid"));
		vendorLokasi.setLokasi(lokasi);

		Paket paket = new Paket();
		paket.setPaket_id(arg0.getInt("paket_id"));
		paket.setPaket_name(arg0.getString("paket_name"));
		paket.setPaket_jumlahjam(arg0.getInt("paket_jumlahjam"));

		VendorUnit vendorUnit = new VendorUnit();
		vendorUnit.setVendorunit_id(arg0.getInt("vendorunit_id"));
		vendorUnit.setVendorunit_driver(arg0.getInt("vendorunit_driver") == 1 ? true : false);
		vendorUnit.setVendorunit_bbm(arg0.getInt("vendorunit_bbm") == 1 ? true : false);
		vendorUnit.setVendorunit_tollparking(arg0.getInt("vendorunit_tollparking") == 1 ? true : false);
		vendorUnit.setVendorunit_nominal(arg0.getInt("vendorunit_nominal"));
		vendorUnit.setVendor(vendor);
		vendorUnit.setPaket(paket);
		vendorUnit.setUnit(unit);
		vendorUnit.setVendorLokasi(vendorLokasi);
		return vendorUnit;
	}

}
