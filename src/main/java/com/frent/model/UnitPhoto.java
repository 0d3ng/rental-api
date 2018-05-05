/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class UnitPhoto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4999335357388464175L;
	private int unitphoto_id;
	private String unitphoto_photo;
	private Unit unit;
	/**
	 * @return the unitphoto_id
	 */
	public int getUnitphoto_id() {
		return unitphoto_id;
	}
	/**
	 * @param unitphoto_id the unitphoto_id to set
	 */
	public void setUnitphoto_id(int unitphoto_id) {
		this.unitphoto_id = unitphoto_id;
	}
	/**
	 * @return the unitphoto_photo
	 */
	public String getUnitphoto_photo() {
		return unitphoto_photo;
	}
	/**
	 * @param unitphoto_photo the unitphoto_photo to set
	 */
	public void setUnitphoto_photo(String unitphoto_photo) {
		this.unitphoto_photo = unitphoto_photo;
	}
	/**
	 * @return the unit
	 */
	public Unit getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	

}
