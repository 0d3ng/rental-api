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

import com.frent.helper.TransaksiKonfirmasiRowMapper;
import com.frent.model.TransaksiKonfirmasi;

@Repository
public class TransaksiKonfirmasiDaoImpl extends JdbcDaoSupport implements TransaksiKonfirmasiDao {

	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String INSERT = "INSERT INTO `trx_confirmation` (" + "  `confirmation_pinjamid`,"
			+ "  `confirmation_date`," + "  `confirmation_image`,confirmation_bank,`confirmation_transfer`,"
			+ "  `confirmation_userid`" + ") " + "VALUES(?,?,?,?,?,?)";
	private final String GET_BY_ID = "SELECT " + "  `confirmation_id`," + "  `confirmation_pinjamid`,"
			+ "  `confirmation_date`," + "  `confirmation_image`," + "  `confirmation_userid` " + "FROM"
			+ "  `trx_confirmation` " + "  WHERE `confirmation_id`=?";
	private final String GET_BY_ID_TRX = "SELECT  + " + "`confirmation_id`, + " + "`confirmation_pinjamid`, + "
			+ "`confirmation_date`, + " + "`confirmation_image`, + " + "`confirmation_userid`  + " + "FROM + "
			+ "`trx_confirmation`  + " + "WHERE `confirmation_pinjamid`=?";
	private final String GET_BY_USER_ID_TRX = "SELECT  + " + "`confirmation_id`, + " + "`confirmation_pinjamid`, + "
			+ "`confirmation_date`, + " + "`confirmation_image`, + " + "`confirmation_userid`  + " + "FROM + "
			+ "`trx_confirmation`  + " + "WHERE `confirmation_userid`=?";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.dao.TransaksiConfirmationDao#insert(com.erental.model.
	 * TransaksiKonfirmasi)
	 */
	@Override
	public boolean insert(TransaksiKonfirmasi konfirmasi) {
		return getJdbcTemplate().update(INSERT,
				new Object[] { konfirmasi.getPinjam().getTrxpinjam_id(), konfirmasi.getConfirmation_date(),
						konfirmasi.getConfirmation_image(), konfirmasi.getConfirmation_bank(),
						konfirmasi.getConfirmation_transfer(), konfirmasi.getUser().getUser_id() }) > 0 ? true : false;
	}

	@Override
	public List<TransaksiKonfirmasi> getById(TransaksiKonfirmasi konfirmasi) {
		return getJdbcTemplate().query(GET_BY_ID, new Object[] { konfirmasi.getConfirmation_id() },
				new TransaksiKonfirmasiRowMapper());
	}

	@Override
	public List<TransaksiKonfirmasi> getByIdTransaksi(TransaksiKonfirmasi konfirmasi) {
		return getJdbcTemplate().query(GET_BY_ID_TRX, new Object[] { konfirmasi.getPinjam().getTrxpinjam_id() },
				new TransaksiKonfirmasiRowMapper());
	}

	@Override
	public List<TransaksiKonfirmasi> getByUserId(String userId) {
		return getJdbcTemplate().query(GET_BY_USER_ID_TRX, new Object[] { userId }, new TransaksiKonfirmasiRowMapper());
	}

}
