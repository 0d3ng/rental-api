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

import com.frent.dao.KategoriDao;
import com.frent.model.Kategori;

@Service("kategoriService")
public class KategoriServiceImpl implements KategoriService {
	
	@Autowired
	private KategoriDao kategoriDao;

	/* (non-Javadoc)
	 * @see com.erental.services.KategoriService#kategoris()
	 */
	@Override
	public List<Kategori> kategoris() {
		return kategoriDao.kategoris();
	}

}
