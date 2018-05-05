/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.frent.helper.CariRowMapper;
import com.frent.helper.OrderRowMapper;
import com.frent.helper.Pesanan;
import com.frent.helper.TransaksiPinjamRowMapper;
import com.frent.helper.TransaksiVendorHelper;
import com.frent.helper.TransaksiVendorHelperRowMapper;
import com.frent.model.TransaksiPinjam;
import com.frent.model.VendorUnit;

@Repository
public class TransaksiPinjamDaoImpl extends JdbcDaoSupport implements TransaksiPinjamDao {

	private final Logger LOGGER = Logger.getLogger(getClass());
	
	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String INSERT = "INSERT INTO `trx_pinjam` (" + "  `trxpinjam_id`,`trxpinjam_userid`,"
			+ "  `trxpinjam_vendorlocationid`," + "  `trxpinjam_vendorunitid`," + "  `trxpinjam_tanggalmulai`,"
			+ "  `trxpinjam_tanggalselesai`," + "  `trxpinjam_detail`," + "  `trxpinjam_specialrequest`,"
			+ "  `trxpinjam_totalunit`," + "  `trxpinjam_totalnominal`,`trxpinjam_date`,trxpinjam_kodebayar) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
	private final String UPDATE = "UPDATE `trx_pinjam` " + "  SET `trxpinjam_vendorlocationid`=?,"
			+ "  `trxpinjam_vendorunitid`=?," + "  `trxpinjam_tanggalmulai`=?," + "  `trxpinjam_tanggalselesai`=?,"
			+ "  `trxpinjam_detail`=?," + "  `trxpinjam_specialrequest`=?," + "  `trxpinjam_totalcar`=?,"
			+ "  `trxpinjam_totalnominal`=?," + "  `trxpinjam_date`=? WHERE `trxpinjam_id`=?;";
	private final String GETALL = "SELECT	`trxpinjam_id`, " + "	`trxpinjam_userid`, "
			+ "	`trxpinjam_vendorlocationid`, " + "	`trxpinjam_vendorunitid`, " + "	`trxpinjam_tanggalmulai`, "
			+ "	`trxpinjam_tanggalselesai`, " + "	`trxpinjam_detail`, " + "	`trxpinjam_specialrequest`, "
			+ "	`trxpinjam_totalunit`, " + "	`trxpinjam_totalnominal`, "
			+ "	`trxpinjam_date`,trxpinjam_kodebayar,`vendor_name`,`unit_name`	 " + "	FROM " + "	`trx_pinjam` "
			+ "JOIN `ref_vendor_unit` ON `trxpinjam_vendorunitid`=`vendorunit_id` "
			+ "  JOIN `ref_vendor` ON `vendorunit_vendorid`=`vendor_id` "
			+ "  JOIN `ref_unit` ON `vendorunit_unitid`=`unit_id` "
			+ "  JOIN `ref_vendor_location` ON `vendorloc_vendorid`=`vendor_id` "
			+ "  JOIN `ref_location` ON `vendorloc_locationid`=`location_id` "
			+ "GROUP BY `trxpinjam_id` ORDER BY `trxpinjam_date`";
	private final String GET_BY_ID = "SELECT	`trxpinjam_id`, " + "	`trxpinjam_userid`, "
			+ "	`trxpinjam_vendorlocationid`, " + "	`trxpinjam_vendorunitid`, " + "	`trxpinjam_tanggalmulai`, "
			+ "	`trxpinjam_tanggalselesai`, " + "	`trxpinjam_detail`, " + "	`trxpinjam_specialrequest`, "
			+ "	`trxpinjam_totalunit`, " + "	`trxpinjam_totalnominal`, "
			+ "	`trxpinjam_date`,trxpinjam_kodebayar,`vendor_name`,`unit_name`	 " + "	FROM " + "	`trx_pinjam` "
			+ "JOIN `ref_vendor_unit` ON `trxpinjam_vendorunitid`=`vendorunit_id` "
			+ "  JOIN `ref_vendor` ON `vendorunit_vendorid`=`vendor_id` "
			+ "  JOIN `ref_unit` ON `vendorunit_unitid`=`unit_id` "
			+ "  JOIN `ref_vendor_location` ON `vendorloc_vendorid`=`vendor_id` "
			+ "  JOIN `ref_location` ON `vendorloc_locationid`=`location_id` " + "WHERE trxpinjam_id=?";
	private final String GET_BY_VENDOR_ID = "SELECT `trxpinjam_id`,`trxpinjam_tanggalmulai`,`unit_name`,`user_email`,`user_idcard`,`user_frontname`,`user_lastname`,`user_photo`,`user_photo_idcard`,`user_jk`,`user_tanggallahir`,`user_phone`,`user_address` FROM `ref_vendor_unit` "
			+ "JOIN `ref_unit` ON `unit_id`=`vendorunit_unitid` "
			+ "JOIN `trx_pinjam` ON `trxpinjam_vendorunitid`=`vendorunit_id` "
			+ "JOIN `web_user` ON `trxpinjam_userid`=`user_id` "
			+ "JOIN `trx_confirmation` ON `trxpinjam_id`=`confirmation_pinjamid` "
			+ "WHERE `trxpinjam_id` NOT IN(SELECT `cancel_pinjamid` FROM `trx_cancel`) "
			+ "AND `confirmation_admin`='1' "
			+ "AND `vendorunit_vendorid`=?";
	private final String GET_PINJAM_BY_USERID = "SELECT `trxpinjam_id`,`trxpinjam_tanggalmulai`,`trxpinjam_tanggalselesai`,`trxpinjam_detail`,`trxpinjam_specialrequest`,`trxpinjam_totalunit`,`trxpinjam_totalnominal`,`trxpinjam_date`,`trxpinjam_kodebayar`,`vendorunit_driver`,`vendorunit_bbm`,`vendorunit_tollparking`,`vendorunit_nominal`,`unit_name`,`unit_thumbnail`,`unit_baggage`,`unit_passenger`,`vendor_name`,`paket_name`,location_name,`confirmation_admin` FROM `ref_vendor_unit` "
			+ "JOIN `trx_pinjam` ON `trxpinjam_vendorunitid`=`vendorunit_id` "
			+ "LEFT JOIN `trx_confirmation` ON `trxpinjam_id`=`confirmation_pinjamid` "
			+ "JOIN `ref_unit` ON `unit_id`=`vendorunit_unitid` "
			+ "JOIN `ref_vendor` ON `vendor_id`=`vendorunit_vendorid` "
			+ "JOIN `ref_vendor_location` ON vendor_id=`vendorloc_vendorid` "
			+ "JOIN `ref_location` ON `location_id`=`vendorloc_locationid` "
			+ "JOIN `ref_paket` ON `paket_id`=`vendorunit_paketid` " + "WHERE `trxpinjam_userid`=?;";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.erental.dao.TransaksiPinjamDao#insert(com.erental.model.TransaksiPinjam)
	 */
	@Override
	public boolean insert(TransaksiPinjam pinjam) {
		return getJdbcTemplate().update(INSERT, new Object[] { pinjam.getTrxpinjam_id(), pinjam.getUser().getUser_id(),
				pinjam.getVendorLokasi().getVendorloc_id(), pinjam.getVendorUnit().getVendorunit_id(),
				pinjam.getTrxpinjam_tanggalmulai(), pinjam.getTrxpinjam_tanggalselesai(), pinjam.getTrxpinjam_detail(),
				pinjam.getTrxpinjam_specialrequest(), pinjam.getTrxpinjam_totalunit(),
				pinjam.getTrxpinjam_totalnominal(), pinjam.getTrxpinjam_date(), pinjam.getTrxpinjam_kodebayar() }) > 0
						? true
						: false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.dao.TransaksiPinjamDao#pinjams()
	 */
	@Override
	public List<TransaksiPinjam> pinjams() {
		return getJdbcTemplate().query(GETALL, new TransaksiPinjamRowMapper());
	}

	@Override
	public boolean update(TransaksiPinjam pinjam) {
		return getJdbcTemplate().update(UPDATE,
				new Object[] { pinjam.getVendorLokasi().getVendorloc_id(), pinjam.getVendorUnit().getVendorunit_id(),
						pinjam.getTrxpinjam_tanggalmulai(), pinjam.getTrxpinjam_tanggalselesai(),
						pinjam.getTrxpinjam_detail(), pinjam.getTrxpinjam_specialrequest(),
						pinjam.getTrxpinjam_totalunit(), pinjam.getTrxpinjam_totalnominal(), pinjam.getTrxpinjam_date(),
						pinjam.getTrxpinjam_id() }) > 0 ? true : false;
	}

	@Override
	public List<VendorUnit> getTransaksiPinjamByParam(String lokasi_id, String kategori_id, int stok, String startDate,
			String endDate, String unit_id, String vendor_id, boolean isDriven, boolean isBbm, boolean isTol,
			String paket_id, boolean sortByPrice) {
		String param = (!lokasi_id.equalsIgnoreCase("0") ? " AND vendorloc_locationid IN(" + lokasi_id + ")" : "")
				+ (!kategori_id.equalsIgnoreCase("0") ? " AND unit_categoriesid IN(" + kategori_id + ")" : "")
				+ " AND unitstock_stockakhir >=" + stok
				+ (!unit_id.equalsIgnoreCase("0") ? " AND unit_id IN(" + unit_id + ")" : "")
				+ (!vendor_id.equalsIgnoreCase("0") ? " AND vendor_id IN(" + vendor_id + ")"
						+ (isDriven ? " AND vendorunit_driver =" + 1 : "") + (isBbm ? " AND vendorunit_bbm = " + 1 : "")
						+ (isTol ? " AND vendorunit_tollparking =" + 1 : "") : "")
				+ (!paket_id.equalsIgnoreCase("0") ? " AND paket_id IN(" + paket_id + ") " : " ");

		String query = "SELECT unit_thumbnail," + "unit_name," + "vendorunit_driver," + "vendorunit_bbm,"
				+ "vendorunit_tollparking," + "vendor_name," + "vendor_logo," + "unit_baggage," + "unit_passenger,"
				+ "vendorunit_nominal," + "paket_id," + "paket_name," + "paket_jumlahjam," + "location_detail,"
				+ "vendor_policy," + "unit_id," + "vendor_id," + "vendorloc_locationid," + "vendorloc_id,"
				+ "vendorunit_id," + "categories_islogin,categories_id " + "FROM ref_vendor "
				+ "LEFT JOIN ref_vendor_unit ON vendorunit_vendorid = vendor_id "
				+ "LEFT JOIN ref_unit ON unit_id = vendorunit_unitid "
				+ "LEFT JOIN ref_vendor_unitstock ON vendorunit_unitid = unitstock_unitid "
				+ "AND vendorunit_vendorid = unitstock_vendorid "
				+ "LEFT JOIN ref_paket ON paket_id = vendorunit_paketid "
				+ "LEFT JOIN ref_vendor_location ON vendorloc_vendorid = vendor_id "
				+ "LEFT JOIN ref_location ON location_id = vendorloc_locationid "
				+ "LEFT JOIN ref_categories ON categories_id = unit_categoriesid  "
				+ "WHERE (vendorloc_id,vendorunit_id) NOT IN ("
				+ "SELECT trxpinjam_vendorlocationid,trxpinjam_vendorunitid " + "FROM trx_pinjam "
				+ "WHERE (DATE(trxpinjam_tanggalmulai) BETWEEN " + startDate + " AND " + endDate + " "
				+ "OR DATE(trxpinjam_tanggalselesai) BETWEEN " + startDate + " AND " + endDate + " "
				+ "AND trxpinjam_id NOT IN (SELECT cancel_pinjamid FROM trx_cancel))" + ")" + param
				+ "ORDER BY vendorunit_nominal " + (sortByPrice ? "ASC" : "DESC");
		LOGGER.debug(query);
		return getJdbcTemplate().query(query, new CariRowMapper());
	}

	@Override
	public List<Pesanan> pinjamsByUserId(String userid) {
		// System.out.println(GET_PINJAM_BY_USERID);
		return getJdbcTemplate().query(GET_PINJAM_BY_USERID, new OrderRowMapper(), new Object[] { userid });
	}

	@Override
	public TransaksiPinjam getTransaksiById(String id) {
		List<TransaksiPinjam> list = getJdbcTemplate().query(GET_BY_ID, new Object[] { id },
				new TransaksiPinjamRowMapper());
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<TransaksiVendorHelper> getAllTranskasiByVendorId(int id) {
		return getJdbcTemplate().query(GET_BY_VENDOR_ID, new Object[] { id }, new TransaksiVendorHelperRowMapper());
	}

}
