/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class VendorLokasi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6890671422393277057L;
	private int vendorloc_id;
	private Lokasi lokasi;
	
	/**
	 * @return the vendorloc_id
	 */
	public int getVendorloc_id() {
		return vendorloc_id;
	}
	/**
	 * @param vendorloc_id the vendorloc_id to set
	 */
	public void setVendorloc_id(int vendorloc_id) {
		this.vendorloc_id = vendorloc_id;
	}
	/**
	 * @return the lokasi
	 */
	public Lokasi getLokasi() {
		return lokasi;
	}
	/**
	 * @param lokasi the lokasi to set
	 */
	public void setLokasi(Lokasi lokasi) {
		this.lokasi = lokasi;
	}
	
}
