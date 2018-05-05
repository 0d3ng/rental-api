/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frent.dao.UnitDao;
import com.frent.model.Unit;

@Service("unitService")
public class UnitServiceImpl implements UnitService {

	@Autowired
	UnitDao unitDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.services.UnitService#units()
	 */
	@Override
	public List<Unit> units() {
		return unitDao.units();
	}

}
