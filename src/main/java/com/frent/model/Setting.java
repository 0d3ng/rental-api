/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

public class Setting {
	
	private int id;
	private String nama;
	private String value;
	private String keterangan;
	private String tipe;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nama
	 */
	public String getNama() {
		return nama;
	}
	/**
	 * @param nama the nama to set
	 */
	public void setNama(String nama) {
		this.nama = nama;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the keterangan
	 */
	public String getKeterangan() {
		return keterangan;
	}
	/**
	 * @param keterangan the keterangan to set
	 */
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	/**
	 * @return the tipe
	 */
	public String getTipe() {
		return tipe;
	}
	/**
	 * @param tipe the tipe to set
	 */
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Setting [id=" + id + ", nama=" + nama + ", value=" + value + ", keterangan=" + keterangan + ", tipe="
				+ tipe + "]";
	}

}
