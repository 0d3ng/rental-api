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

import com.frent.dao.TransaksiPinjamDao;
import com.frent.helper.Pesanan;
import com.frent.helper.TransaksiVendorHelper;
import com.frent.model.TransaksiPinjam;
import com.frent.model.VendorUnit;

@Service("transaksiPinjamService")
public class TransaksiPinjamServiceImpl implements TransaksiPinjamService {

	@Autowired
	TransaksiPinjamDao transaksiPinjamDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.services.TransaksiPinjamService#insert(com.erental.model.
	 * TransaksiPinjam)
	 */
	@Override
	public boolean insert(TransaksiPinjam pinjam) {
		return transaksiPinjamDao.insert(pinjam);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.erental.services.TransaksiPinjamService#pinjams()
	 */
	@Override
	public List<TransaksiPinjam> pinjams() {
		return transaksiPinjamDao.pinjams();
	}

	@Override
	public boolean update(TransaksiPinjam pinjam) {
		return transaksiPinjamDao.update(pinjam);
	}

	@Override
	public List<VendorUnit> getTransaksiPinjamByParam(String lokasi_id, String kategori_id, int stok, String startDate,
			String endDate, String unit_id, String vendor_id, boolean isDriven, boolean isBbm, boolean isTol, String paket_id,
			boolean sortByPrice) {
		return transaksiPinjamDao.getTransaksiPinjamByParam(lokasi_id, kategori_id, stok, startDate, endDate, unit_id,
				vendor_id, isDriven, isBbm, isTol, paket_id, sortByPrice);
	}

	@Override
	public List<Pesanan> pinjamsByUserId(String userid) {
		return transaksiPinjamDao.pinjamsByUserId(userid);
	}

	@Override
	public TransaksiPinjam getTransaksiById(String id) {
		return transaksiPinjamDao.getTransaksiById(id);
	}

	@Override
	public List<TransaksiVendorHelper> getAllTranskasiByVendorId(int id) {
		return transaksiPinjamDao.getAllTranskasiByVendorId(id);
	}

}
