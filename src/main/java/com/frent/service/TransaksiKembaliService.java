/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.service;

import java.util.List;

import com.frent.model.TransaksiKembali;

public interface TransaksiKembaliService {
	
	public boolean insert(TransaksiKembali kembali);

	public TransaksiKembali geTransaksiKembaliById(long id);

	public List<TransaksiKembali> geTransaksiKembalisByUserId(int id);

}
