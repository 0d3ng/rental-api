/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

public class VendorUnitStok {

	private long unitstock_id;
	private int unitstock_stockawal;
	private int unitstock_stockakhir;
	private Vendor vendor;
	private Unit unit;
	/**
	 * @return the unitstock_id
	 */
	public long getUnitstock_id() {
		return unitstock_id;
	}
	/**
	 * @param unitstock_id the unitstock_id to set
	 */
	public void setUnitstock_id(long unitstock_id) {
		this.unitstock_id = unitstock_id;
	}
	/**
	 * @return the unitstock_stockawal
	 */
	public int getUnitstock_stockawal() {
		return unitstock_stockawal;
	}
	/**
	 * @param unitstock_stockawal the unitstock_stockawal to set
	 */
	public void setUnitstock_stockawal(int unitstock_stockawal) {
		this.unitstock_stockawal = unitstock_stockawal;
	}
	/**
	 * @return the unitstock_stockakhir
	 */
	public int getUnitstock_stockakhir() {
		return unitstock_stockakhir;
	}
	/**
	 * @param unitstock_stockakhir the unitstock_stockakhir to set
	 */
	public void setUnitstock_stockakhir(int unitstock_stockakhir) {
		this.unitstock_stockakhir = unitstock_stockakhir;
	}
	/**
	 * @return the vendor
	 */
	public Vendor getVendor() {
		return vendor;
	}
	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
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
