/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.frent.model.Kategori;

public class KategoriRowMapper implements RowMapper<Kategori> {

	@Override
	public Kategori mapRow(ResultSet arg0, int arg1) throws SQLException {
		Kategori kategori = new Kategori();
		kategori.setCategories_id(arg0.getInt("categories_id"));
		kategori.setCategories_name(arg0.getString("categories_name"));
		return kategori;
	}

}
