/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.frent.model.Kategori;

public interface KategoriDao {
	
	public List<Kategori> kategoris();

}
