/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.frent.helper.Pesanan;
import com.frent.helper.PesananRowMapper;
import com.frent.helper.VendorHelper;
import com.frent.helper.VendorHelperRowMapper;
import com.frent.helper.VendorRowMapper;
import com.frent.helper.VendorUnitRowMapper;
import com.frent.model.Vendor;
import com.frent.model.VendorUnit;

@Repository
public class VendorDaoImpl extends JdbcDaoSupport implements VendorDao {

	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String GETALL = "SELECT " + "  `vendor_id`," + "  `vendor_name`," + "  `vendor_logo`,"
			+ "  `vendor_address`," + "  `vendor_phone`," + "  `vendor_email`," + "  `vendor_website`,"
			+ "  `vendor_tahunberdiri`," + "  `vendor_pendiri`," + "  `vendor_detail`," + "  `vendor_bankdetail`,"
			+ "  `vendor_policy`," + "  `vendor_latitude`," + "  `vendor_longitude` " + "FROM" + "  `ref_vendor` ";
	private final String GET_BY_EMAIL = "SELECT `vendor_id`,`vendor_name`,`vendor_logo`,`vendor_address`,`vendor_phone`,`vendor_email`,`vendor_website`,`vendor_tahunberdiri`,`vendor_pendiri`,`vendor_detail`,`vendor_policy`,`vendor_latitude`,`vendor_longitude`,`vendorfoto_photo` FROM `ref_vendor` "
			+ "JOIN `ref_vendor_photo` ON `vendorfoto_vendorid`=`vendor_id` " + "WHERE `vendor_email`=?; ";
	private final String GET_VENDOR_UNIT_BY_ID = "SELECT " + "  `vendorunit_id`," + "  `vendorunit_unitid`,"
			+ "  `vendorunit_vendorid`," + "  `vendorunit_paketid`," + "  `vendorunit_driver`," + "  `vendorunit_bbm`,"
			+ "  `vendorunit_tollparking`," + "  `vendorunit_nominal`" + "FROM" + "  `ref_vendor_unit`"
			+ "  WHERE `vendorunit_id`=? ";

	private final String GET_PESAN_VENDOR_UNIT_BY_ID = "SELECT `vendorunit_driver`,`vendorunit_bbm`,`vendorunit_tollparking`,`vendorunit_nominal`,`unit_name`,`unit_thumbnail`,`unit_baggage`,`unit_passenger`,`vendor_name`,`paket_name`,`location_name` FROM `ref_vendor_unit` "
			+ "JOIN `ref_unit` ON `unit_id`=`vendorunit_unitid` "
			+ "JOIN `ref_vendor` ON `vendor_id`=`vendorunit_vendorid` "
			+ "JOIN `ref_vendor_location` ON vendor_id=`vendorloc_vendorid` "
			+ "JOIN `ref_location` ON `location_id`=`vendorloc_locationid` "
			+ "JOIN `ref_paket` ON `paket_id`=`vendorunit_paketid` " + "WHERE `vendorunit_id`=? AND `vendorloc_id`=?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.dao.VendorDao#vendors()
	 */
	@Override
	public List<Vendor> getVendors() {
		return getJdbcTemplate().query(GETALL, new VendorRowMapper());
	}

	@Override
	public VendorUnit getVendorUnitById(int id) {
		List<VendorUnit> list = getJdbcTemplate().query(GET_VENDOR_UNIT_BY_ID, new Object[] { id },
				new VendorUnitRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public Pesanan getVendorUnitPesanById(int idVendorUnit, int idVendorLocation) {
		List<Pesanan> list = getJdbcTemplate().query(GET_PESAN_VENDOR_UNIT_BY_ID,
				new Object[] { idVendorUnit, idVendorLocation }, new PesananRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public VendorHelper getVendorByEmail(String email) {
		List<VendorHelper> list = getJdbcTemplate().query(GET_BY_EMAIL, new Object[] { email }, new VendorHelperRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}

	}

}
