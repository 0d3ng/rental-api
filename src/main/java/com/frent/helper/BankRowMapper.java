/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frent.model.Bank;

public class BankRowMapper implements RowMapper<Bank> {

	@Override
	public Bank mapRow(ResultSet arg0, int arg1) throws SQLException {
		Bank bank = new Bank();
		bank.setId(arg0.getInt("bank_id"));
		bank.setName(arg0.getString("bank_name"));
		bank.setRekening(arg0.getString("bank_rekening"));
		bank.setAccount(arg0.getString("bank_account"));
		bank.setLocation(arg0.getString("bank_location"));
		bank.setLogo(arg0.getString("bank_logo"));
		return bank;
	}

}
