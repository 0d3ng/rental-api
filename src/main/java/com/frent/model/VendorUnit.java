/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class VendorUnit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2812058655216924163L;
	private int vendorunit_id;
	private boolean vendorunit_driver;
	private boolean vendorunit_bbm;
	private boolean vendorunit_tollparking;
	private int vendorunit_nominal;
	private Unit unit;
	private Vendor vendor;
	private Paket paket;
	private VendorLokasi vendorLokasi;

	/**
	 * @return the vendorunit_id
	 */
	public int getVendorunit_id() {
		return vendorunit_id;
	}

	/**
	 * @param vendorunit_id
	 *            the vendorunit_id to set
	 */
	public void setVendorunit_id(int vendorunit_id) {
		this.vendorunit_id = vendorunit_id;
	}

	/**
	 * @return the vendorunit_driver
	 */
	public boolean isVendorunit_driver() {
		return vendorunit_driver;
	}

	/**
	 * @param vendorunit_driver
	 *            the vendorunit_driver to set
	 */
	public void setVendorunit_driver(boolean vendorunit_driver) {
		this.vendorunit_driver = vendorunit_driver;
	}

	/**
	 * @return the vendorunit_bbm
	 */
	public boolean isVendorunit_bbm() {
		return vendorunit_bbm;
	}

	/**
	 * @param vendorunit_bbm
	 *            the vendorunit_bbm to set
	 */
	public void setVendorunit_bbm(boolean vendorunit_bbm) {
		this.vendorunit_bbm = vendorunit_bbm;
	}

	/**
	 * @return the vendorunit_tollparking
	 */
	public boolean isVendorunit_tollparking() {
		return vendorunit_tollparking;
	}

	/**
	 * @param vendorunit_tollparking
	 *            the vendorunit_tollparking to set
	 */
	public void setVendorunit_tollparking(boolean vendorunit_tollparking) {
		this.vendorunit_tollparking = vendorunit_tollparking;
	}

	/**
	 * @return the vendorunit_nominal
	 */
	public int getVendorunit_nominal() {
		return vendorunit_nominal;
	}

	/**
	 * @param vendorunit_nominal
	 *            the vendorunit_nominal to set
	 */
	public void setVendorunit_nominal(int vendorunit_nominal) {
		this.vendorunit_nominal = vendorunit_nominal;
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
	 * @return the paket
	 */
	public Paket getPaket() {
		return paket;
	}

	/**
	 * @param paket the paket to set
	 */
	public void setPaket(Paket paket) {
		this.paket = paket;
	}

	/**
	 * @return the vendorLokasi
	 */
	public VendorLokasi getVendorLokasi() {
		return vendorLokasi;
	}

	/**
	 * @param vendorLokasi the vendorLokasi to set
	 */
	public void setVendorLokasi(VendorLokasi vendorLokasi) {
		this.vendorLokasi = vendorLokasi;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VendorUnit [vendorunit_id=" + vendorunit_id + ", vendorunit_driver=" + vendorunit_driver
				+ ", vendorunit_bbm=" + vendorunit_bbm + ", vendorunit_tollparking=" + vendorunit_tollparking
				+ ", vendorunit_nominal=" + vendorunit_nominal + ", unit=" + unit + ", vendor=" + vendor + ", paket="
				+ paket + ", vendorLokasi=" + vendorLokasi + "]";
	}

	

}
