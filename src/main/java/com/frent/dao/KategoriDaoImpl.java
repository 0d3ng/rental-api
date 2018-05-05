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

import com.frent.helper.KategoriRowMapper;
import com.frent.model.Kategori;

@Repository
public class KategoriDaoImpl extends JdbcDaoSupport implements KategoriDao {
	
	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}

	private final String GETALL = "SELECT categories_id,categories_name FROM ref_categories";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.dao.KategoriDao#kategoris()
	 */
	@Override
	public List<Kategori> kategoris() {
		return getJdbcTemplate().query(GETALL, new KategoriRowMapper());
	}

}
