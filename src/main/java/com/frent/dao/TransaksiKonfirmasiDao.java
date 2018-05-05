/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import com.frent.model.TransaksiKonfirmasi;

public interface TransaksiKonfirmasiDao {

	public boolean insert(TransaksiKonfirmasi konfirmasi);

	public List<TransaksiKonfirmasi> getById(TransaksiKonfirmasi konfirmasi);

	public List<TransaksiKonfirmasi> getByUserId(String userId);
	
	public List<TransaksiKonfirmasi> getByIdTransaksi(TransaksiKonfirmasi konfirmasi);

}
