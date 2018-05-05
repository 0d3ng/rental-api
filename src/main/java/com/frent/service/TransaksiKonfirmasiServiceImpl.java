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

import com.frent.dao.TransaksiKonfirmasiDao;
import com.frent.model.TransaksiKonfirmasi;

@Service("transaksiKonfirmasiService")
public class TransaksiKonfirmasiServiceImpl implements TransaksiKonfirmasiService {

	@Autowired
	TransaksiKonfirmasiDao transaksiKonfirmationDao;
	
	/* (non-Javadoc)
	 * @see com.erental.services.TransaksiKonfirmasiService#insert(com.erental.model.TransaksiKonfirmasi)
	 */
	@Override
	public boolean insert(TransaksiKonfirmasi konfirmasi) {
		return transaksiKonfirmationDao.insert(konfirmasi);
	}

	@Override
	public List<TransaksiKonfirmasi> getById(TransaksiKonfirmasi konfirmasi) {
		return transaksiKonfirmationDao.getById(konfirmasi);
	}

	@Override
	public List<TransaksiKonfirmasi> getByIdTransaksi(TransaksiKonfirmasi konfirmasi) {
		return transaksiKonfirmationDao.getByIdTransaksi(konfirmasi);
	}

	@Override
	public List<TransaksiKonfirmasi> getByUserId(String userId) {
		return transaksiKonfirmationDao.getByUserId(userId);
	}

}
