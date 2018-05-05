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

import com.frent.helper.TransaksiKembaliHelper;
import com.frent.model.TransaksiKembali;

@Repository
public class TransaksiKembaliDaoImpl extends JdbcDaoSupport implements TransaksiKembaliDao {

	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String INSERT = "INSERT INTO `trx_kembali` (" + "  `kembali_pinjamid`," + "  `kembali_date`,"
			+ "  `kembali_image`," + "  `kembali_note`," + "  `kembali_userid`" + ") " + "VALUES(?,?,?,?,?)";
	private final String SELECT_BY_ID = "SELECT " + "  `kembali_id`," + "  `kembali_pinjamid`," + "  `kembali_date`,"
			+ "  `kembali_image`," + "  `kembali_note`," + "  `kembali_userid`" + "FROM"
			+ "  `trx_kembali` WHERE kembali_id=?";

	private final String SELECT_BY_USER_ID = "SELECT " + "  `kembali_id`," + "  `kembali_pinjamid`,"
			+ "  `kembali_date`," + "  `kembali_image`," + "  `kembali_note`," + "  `kembali_userid`" + "FROM"
			+ "  `trx_kembali` WHERE kembali_userid=?";

	@Override
	public boolean insert(TransaksiKembali kembali) {
		return getJdbcTemplate().update(INSERT, new Object[] { kembali.getTransaksiId(), kembali.getTanggal(),kembali.getImage(),
				kembali.getNote(), kembali.getUserId() }) > 0 ? true : false;
	}

	@Override
	public TransaksiKembali geTransaksiKembaliById(long id) {
		List<TransaksiKembali> list = getJdbcTemplate().query(SELECT_BY_ID, new TransaksiKembaliHelper(),
				new Object[] { id });
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public List<TransaksiKembali> geTransaksiKembalisByUserId(int id) {
		return getJdbcTemplate().query(SELECT_BY_USER_ID, new TransaksiKembaliHelper(),
				new Object[] { id });
	}

}
