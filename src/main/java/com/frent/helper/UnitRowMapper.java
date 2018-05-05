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
import com.frent.model.Unit;

public class UnitRowMapper implements RowMapper<Unit> {

	@Override
	public Unit mapRow(ResultSet arg0, int arg1) throws SQLException {
		Kategori kategori = new Kategori();
		kategori.setCategories_id(arg0.getInt("unit_categoriesid"));
		Unit unit = new Unit();
		unit.setUnit_id(arg0.getInt("unit_id"));		
		unit.setUnit_name(arg0.getString("unit_name"));
		unit.setUnit_produsen(arg0.getString("unit_produsen"));
		unit.setUnit_class(arg0.getString("unit_class"));
		unit.setUnit_framework(arg0.getString("unit_framework"));
		unit.setUnit_detail(arg0.getString("unit_detail"));
		unit.setUnit_thumbnail(arg0.getString("unit_thumbnail"));
		unit.setUnit_baggage(arg0.getInt("unit_baggage"));
		unit.setUnit_passenger(arg0.getInt("unit_passenger"));
		unit.setKategori(kategori);
		return unit;
	}

}
