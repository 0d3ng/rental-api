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

import com.frent.dao.PaketDao;
import com.frent.model.Paket;

@Service("paketService")
public class PaketServiceImpl implements PaketService {
	
	@Autowired
	PaketDao paketDao;

	/* (non-Javadoc)
	 * @see com.erental.services.PaketService#pakets()
	 */
	@Override
	public List<Paket> pakets() {
		return paketDao.pakets();
	}

}
