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

import com.frent.dao.LokasiDao;
import com.frent.model.Lokasi;

@Service("lokasiService")
public class LokasiServiceImpl implements LokasiService {

	@Autowired
	LokasiDao lokasiDao;
	
	/* (non-Javadoc)
	 * @see com.erental.services.LokasiService#lokasis()
	 */
	@Override
	public List<Lokasi> lokasis() {
		return lokasiDao.lokasis();
	}

}
