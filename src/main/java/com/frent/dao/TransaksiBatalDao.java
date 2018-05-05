/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import com.frent.model.TransaksiBatal;

public interface TransaksiBatalDao {

	public boolean insert(TransaksiBatal transaksiCancel);

	public List<TransaksiBatal> getById(TransaksiBatal transaksiCancel);

	public List<TransaksiBatal> getByIdTransaksi(TransaksiBatal transaksiCancel);

}
