/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.service;

import java.util.List;

import com.frent.helper.Pesanan;
import com.frent.helper.TransaksiVendorHelper;
import com.frent.model.TransaksiPinjam;
import com.frent.model.VendorUnit;

public interface TransaksiPinjamService {

	public boolean insert(TransaksiPinjam pinjam);

	public boolean update(TransaksiPinjam pinjam);

	public TransaksiPinjam getTransaksiById(String id);
	
	public List<TransaksiVendorHelper> getAllTranskasiByVendorId(int id);
	
	public List<TransaksiPinjam> pinjams();
	
	public List<Pesanan> pinjamsByUserId(String userid);
	
	public List<VendorUnit> getTransaksiPinjamByParam(String lokasi_id, String kategori_id, int stok, String startDate,
			String endDate, String unit_id, String vendor_id, boolean isDriven, boolean isBbm, boolean isTol, String paket_id,
			boolean sortByPrice);

}
