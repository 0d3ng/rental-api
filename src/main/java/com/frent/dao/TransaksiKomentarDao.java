/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import com.frent.model.TransaksiKomentar;

public interface TransaksiKomentarDao {

	public boolean insert(TransaksiKomentar testimoni);
	
	public boolean update(TransaksiKomentar testimoni);
	
	public boolean delete(TransaksiKomentar testimoni);

	public List<TransaksiKomentar> getById(TransaksiKomentar testimoni);

	public List<TransaksiKomentar> getByIdTransaksi(TransaksiKomentar testimoni);

}
