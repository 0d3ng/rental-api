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

import com.frent.dao.VendorDao;
import com.frent.helper.Pesanan;
import com.frent.helper.VendorHelper;
import com.frent.model.Vendor;
import com.frent.model.VendorUnit;

@Service("vendorService")
public class VendorServiceImpl implements VendorService {
	
	@Autowired
	VendorDao vendorDao;

	/* (non-Javadoc)
	 * @see com.erental.services.VendorService#getVendors()
	 */
	@Override
	public List<Vendor> getVendors() {
		return vendorDao.getVendors();
	}

	@Override
	public VendorUnit getVendorUnitById(int id) {
		return vendorDao.getVendorUnitById(id);
	}

	@Override
	public Pesanan getVendorUnitPesanById(int idVendorUnit, int idVendorLocation) {
		return vendorDao.getVendorUnitPesanById(idVendorUnit,idVendorLocation);
	}

	@Override
	public VendorHelper getVendorByEmail(String email) {
		return vendorDao.getVendorByEmail(email);
	}

}
