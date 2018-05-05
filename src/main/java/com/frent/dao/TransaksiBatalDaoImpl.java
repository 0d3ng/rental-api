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

import com.frent.helper.TransaksiBatalRowMapper;
import com.frent.model.TransaksiBatal;

@Repository
public class TransaksiBatalDaoImpl extends JdbcDaoSupport implements TransaksiBatalDao {

	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String INSERT = "INSERT INTO `trx_cancel` (" + 
			"  `cancel_id`," + 
			"  `cancel_pinjamid`," + 
			"  `cancel_date`," + 
			"  `cancel_user`," + 
			"  `cancel_telp`," + 
			"  `cancel_reason`" + 
			") " + 
			"VALUES(?,?,?,?,?,?)";
	private final String GET_BY_ID = "SELECT " + 
			"  `cancel_id`," + 
			"  `cancel_pinjamid`," + 
			"  `cancel_date`," + 
			"  `cancel_user`," + 
			"  `cancel_telp`," + 
			"  `cancel_reason` " + 
			"FROM" + 
			"  `trx_cancel`" + 
			"  WHERE `cancel_id`=?";
	private final String GET_BY_ID_TRX = "SELECT " + 
			"  `cancel_id`," + 
			"  `cancel_pinjamid`," + 
			"  `cancel_date`," + 
			"  `cancel_user`," + 
			"  `cancel_telp`," + 
			"  `cancel_reason` " + 
			"FROM" + 
			"  `trx_cancel`" + 
			"  WHERE `cancel_pinjamid`=?";
	
	/* (non-Javadoc)
	 * @see com.erental.dao.TransaksiCancelDao#insert(com.erental.model.TransaksiCancel)
	 */
	@Override
	public boolean insert(TransaksiBatal transaksiCancel) {
		return getJdbcTemplate().update(INSERT, new Object[]{transaksiCancel.getCancel_id(),transaksiCancel.getPinjam().getTrxpinjam_id(),transaksiCancel.getCancel_date(),transaksiCancel.getCancel_user(),transaksiCancel.getCancel_telp(),transaksiCancel.getCancel_reason()}) > 0 ? true : false;
	}

	@Override
	public List<TransaksiBatal> getById(TransaksiBatal transaksiCancel) {
		return getJdbcTemplate().query(GET_BY_ID, new Object[] {transaksiCancel.getCancel_id()}, new TransaksiBatalRowMapper());
	}

	@Override
	public List<TransaksiBatal> getByIdTransaksi(TransaksiBatal transaksiCancel) {
		return getJdbcTemplate().query(GET_BY_ID_TRX, new Object[] {transaksiCancel.getCancel_id()}, new TransaksiBatalRowMapper());
	}

}
