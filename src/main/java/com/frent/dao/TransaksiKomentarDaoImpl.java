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

import com.frent.helper.TransaksiKomentarRowMapper;
import com.frent.model.TransaksiKomentar;

@Repository
public class TransaksiKomentarDaoImpl extends JdbcDaoSupport implements TransaksiKomentarDao {

	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String INSERT = "INSERT INTO `trx_testimoni` (" + "  `testimoni_userid`,"
			+ "  `testimoni_trxpinjamid`," + "  `testimoni_create`," + "  `testimoni_content`," + "  `testimoni_rate`"
			+ ") " + "VALUES(?,?,NOW(),?,?);";
	private final String UPDATE = "UPDATE `trx_testimoni`" + "  SET `testimoni_create`=?," + "  `testimoni_content`=?,"
			+ "  `testimoni_rate`=? WHERE `testimoni_id`=? ";
	private final String DELETE = "DELETE " + "  `trx_testimoni`" + "  WHERE `testimoni_id`=? ";
	private final String GET_BY_ID = "SELECT " + "  `testimoni_id`," + "  `testimoni_userid`,"
			+ "  `testimoni_trxpinjamid`," + "  `testimoni_create`," + "  `testimoni_content`," + "  `testimoni_rate` "
			+ "FROM" + "  `trx_testimoni`" + "  WHERE `testimoni_id`=? ";
	private final String GET_BY_ID_TRX = "SELECT " + "  `testimoni_id`," + "  `testimoni_userid`,"
			+ "  `testimoni_trxpinjamid`," + "  `testimoni_create`," + "  `testimoni_content`," + "  `testimoni_rate` "
			+ "FROM" + "  `trx_testimoni`" + "  WHERE `testimoni_trxpinjamid`=? ";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.dao.TransaksiTestimoniDao#insert(com.erental.model.
	 * TransaksiTestimoni)
	 */
	@Override
	public boolean insert(TransaksiKomentar testimoni) {
		return getJdbcTemplate().update(INSERT,
				new Object[] { testimoni.getUser().getUser_id(), testimoni.getPinjam().getTrxpinjam_id(), testimoni.getTestimoni_content(),
						testimoni.getTestimoni_rate() }) > 0 ? true : false;
	}

	@Override
	public List<TransaksiKomentar> getById(TransaksiKomentar testimoni) {
		return getJdbcTemplate().query(GET_BY_ID, new Object[] { testimoni.getTestimoni_id() },
				new TransaksiKomentarRowMapper());
	}

	@Override
	public List<TransaksiKomentar> getByIdTransaksi(TransaksiKomentar testimoni) {
		return getJdbcTemplate().query(GET_BY_ID_TRX, new Object[] { testimoni.getPinjam().getTrxpinjam_id() },
				new TransaksiKomentarRowMapper());
	}

	@Override
	public boolean update(TransaksiKomentar testimoni) {
		return getJdbcTemplate().update(UPDATE, new Object[] { testimoni.getTestimoni_create(),
				testimoni.getTestimoni_content(), testimoni.getTestimoni_rate(), testimoni.getTestimoni_id() }) > 0
						? true
						: false;
	}

	@Override
	public boolean delete(TransaksiKomentar testimoni) {
		return getJdbcTemplate().update(DELETE, new Object[] { testimoni.getTestimoni_id() }) > 0 ? true : false;
	}

}
