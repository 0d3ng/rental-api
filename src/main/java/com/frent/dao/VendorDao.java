/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import com.frent.helper.Pesanan;
import com.frent.helper.VendorHelper;
import com.frent.model.Vendor;
import com.frent.model.VendorUnit;

public interface VendorDao {

	public List<Vendor> getVendors();

	public VendorHelper getVendorByEmail(String email);

	public VendorUnit getVendorUnitById(int id);

	public Pesanan getVendorUnitPesanById(int idVendorUnit, int idVendorLocation);

}
