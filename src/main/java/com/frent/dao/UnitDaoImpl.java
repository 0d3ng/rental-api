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

import com.frent.helper.UnitRowMapper;
import com.frent.model.Unit;

@Repository
public class UnitDaoImpl extends JdbcDaoSupport implements UnitDao {
	
	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	private final String GETALL = "SELECT `unit_id`,`unit_categoriesid`,`unit_name`,`unit_produsen`,`unit_class`,`unit_framework`,`unit_detail`,`unit_thumbnail`,`unit_baggage`,`unit_passenger` FROM `ref_unit` u ";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.dao.UnitDao#units()
	 */
	@Override
	public List<Unit> units() {
		return getJdbcTemplate().query(GETALL, new UnitRowMapper());
	}

}
