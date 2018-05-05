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

import com.frent.helper.LokasiRowMapper;
import com.frent.model.Lokasi;

@Repository
public class LokasiDaoImpl extends JdbcDaoSupport implements LokasiDao {
	
	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}

	private final String GETALL = "SELECT location_id,location_name,location_photo,location_detail FROM ref_location";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.dao.LokasiDao#lokasis()
	 */
	@Override
	public List<Lokasi> lokasis() {
		return getJdbcTemplate().query(GETALL, new LokasiRowMapper());
	}

}
