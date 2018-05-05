/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frent.dao.TransaksiBatalDao;
import com.frent.model.TransaksiBatal;

@Service("transaksiService")
public class TransaksiBatalServiceImpl implements TransaksiBatalService {

	@Autowired
	TransaksiBatalDao transaksiCancelDao;
	
	/* (non-Javadoc)
	 * @see com.erental.dao.TransaksiCancelDao#insert(com.erental.model.TransaksiCancel)
	 */
	@Override
	public boolean insert(TransaksiBatal transaksiCancel) {
		return transaksiCancelDao.insert(transaksiCancel);
	}

}
