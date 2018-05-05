/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.service;

import java.util.List;

import com.frent.model.TransaksiKonfirmasi;

public interface TransaksiKonfirmasiService {

	public boolean insert(TransaksiKonfirmasi konfirmasi);

	public List<TransaksiKonfirmasi> getById(TransaksiKonfirmasi konfirmasi);
	
	public List<TransaksiKonfirmasi> getByUserId(String userId);

	public List<TransaksiKonfirmasi> getByIdTransaksi(TransaksiKonfirmasi konfirmasi);

}
