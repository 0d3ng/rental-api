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

import com.frent.helper.PaketRowMapper;
import com.frent.model.Paket;

@Repository
public class PaketDaoImpl extends JdbcDaoSupport implements PaketDao {

	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String GETALL = "SELECT paket_id,paket_name,paket_jumlahjam FROM ref_paket";

	/* (non-Javadoc)
	 * @see com.erental.dao.PaketDao#pakets()
	 */
	@Override
	public List<Paket> pakets() {
		return getJdbcTemplate().query(GETALL, new PaketRowMapper());
	}

}
