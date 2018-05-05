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

import com.frent.dao.TransaksiKembaliDao;
import com.frent.model.TransaksiKembali;

@Service("transaksiKembaliService")
public class TransaksiKembaliServiceImpl implements TransaksiKembaliService{

	@Autowired
	TransaksiKembaliDao transaksiKembaliDao;
	
	@Override
	public boolean insert(TransaksiKembali kembali) {
		return transaksiKembaliDao.insert(kembali);
	}

	@Override
	public TransaksiKembali geTransaksiKembaliById(long id) {
		return transaksiKembaliDao.geTransaksiKembaliById(id);
	}

	@Override
	public List<TransaksiKembali> geTransaksiKembalisByUserId(int id) {
		return transaksiKembaliDao.geTransaksiKembalisByUserId(id);
	}

}
