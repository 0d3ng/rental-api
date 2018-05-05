/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.service;

import java.util.List;

import com.frent.helper.Pesanan;
import com.frent.helper.VendorHelper;
import com.frent.model.Vendor;
import com.frent.model.VendorUnit;

public interface VendorService {

	public List<Vendor> getVendors();

	public VendorUnit getVendorUnitById(int id);
	
	public VendorHelper getVendorByEmail(String email);

	public Pesanan getVendorUnitPesanById(int idVendorUnit, int idVendorLocation);
}
