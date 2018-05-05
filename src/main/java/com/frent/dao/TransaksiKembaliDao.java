/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import com.frent.model.TransaksiKembali;

public interface TransaksiKembaliDao {

	public boolean insert(TransaksiKembali kembali);

	public TransaksiKembali geTransaksiKembaliById(long id);

	public List<TransaksiKembali> geTransaksiKembalisByUserId(int id);

}
