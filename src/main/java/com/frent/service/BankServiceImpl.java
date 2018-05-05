/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.frent.dao.BankDao;
import com.frent.model.Bank;

@Service("bankService")
public class BankServiceImpl implements BankService {

	@Autowired
	BankDao bankDao;
	/* (non-Javadoc)
	 * @see com.erental.services.BankService#getAllBank()
	 */
	@Override
	public List<Bank> getAllBank() {
		return bankDao.getAllBank();
	}

}
