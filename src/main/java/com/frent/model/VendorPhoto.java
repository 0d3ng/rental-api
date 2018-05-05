/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class VendorPhoto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5524891969676999867L;
	private int vendorfoto_id;
	private int vendorfoto_photo;
	private Vendor vendor;
	/**
	 * @return the vendorfoto_id
	 */
	public int getVendorfoto_id() {
		return vendorfoto_id;
	}
	/**
	 * @param vendorfoto_id the vendorfoto_id to set
	 */
	public void setVendorfoto_id(int vendorfoto_id) {
		this.vendorfoto_id = vendorfoto_id;
	}
	/**
	 * @return the vendorfoto_photo
	 */
	public int getVendorfoto_photo() {
		return vendorfoto_photo;
	}
	/**
	 * @param vendorfoto_photo the vendorfoto_photo to set
	 */
	public void setVendorfoto_photo(int vendorfoto_photo) {
		this.vendorfoto_photo = vendorfoto_photo;
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
	
	
}
