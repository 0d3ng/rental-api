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

import com.frent.helper.BankRowMapper;
import com.frent.model.Bank;

@Repository
public class BankDaoImpl extends JdbcDaoSupport implements BankDao{

	private final String GET_ALL = "SELECT `bank_id`,`bank_name`,`bank_rekening`,`bank_account`,`bank_location`,`bank_logo` FROM `ref_bank_transfer` ORDER BY `bank_id`";
	
	@Autowired
	public void setDS(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	@Override
	public List<Bank> getAllBank() {
		return getJdbcTemplate().query(GET_ALL, new BankRowMapper());
	}

}
