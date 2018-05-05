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

import com.frent.dao.TransaksiKomentarDao;
import com.frent.model.TransaksiKomentar;

@Service("transaksiKomentarService")
public class TransaksiKomentarServiceImpl implements TransaksiKomentarService{

	@Autowired
	TransaksiKomentarDao transaksiTestimoniDao;
	
	@Override
	public boolean insert(TransaksiKomentar tetimoni) {
		return transaksiTestimoniDao.insert(tetimoni);
	}

	@Override
	public List<TransaksiKomentar> getById(TransaksiKomentar testimoni) {
		return transaksiTestimoniDao.getById(testimoni);
	}

	@Override
	public List<TransaksiKomentar> getByIdTransaksi(TransaksiKomentar testimoni) {
		return transaksiTestimoniDao.getByIdTransaksi(testimoni);
	}

	@Override
	public boolean update(TransaksiKomentar testimoni) {
		return transaksiTestimoniDao.update(testimoni);
	}

	@Override
	public boolean delete(TransaksiKomentar testimoni) {
		return transaksiTestimoniDao.delete(testimoni);
	}

}
