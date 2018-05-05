/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import com.frent.helper.Pesanan;
import com.frent.helper.TransaksiVendorHelper;
import com.frent.model.TransaksiPinjam;
import com.frent.model.VendorUnit;

public interface TransaksiPinjamDao {

	public boolean insert(TransaksiPinjam pinjam);

	public boolean update(TransaksiPinjam pinjam);

	public List<TransaksiPinjam> pinjams();
	
	public List<TransaksiVendorHelper> getAllTranskasiByVendorId(int id);
	
	public TransaksiPinjam getTransaksiById(String id);
	
	public List<Pesanan> pinjamsByUserId(String userid);

	public List<VendorUnit> getTransaksiPinjamByParam(String lokasi_id, String kategori_id, int stok, String startDate, String endDate, String unit_id, String vendor_id, boolean isDriven, boolean isBbm, boolean isTol, String paket_id, boolean sortByPrice);

}
