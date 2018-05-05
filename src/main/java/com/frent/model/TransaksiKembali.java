/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

public class TransaksiKembali {
	
	private long id;
	private String transaksiId;
	private String tanggal;
	private String image;
	private String note;
	private long userId;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the transaksiId
	 */
	public String getTransaksiId() {
		return transaksiId;
	}
	/**
	 * @param transaksiId the transaksiId to set
	 */
	public void setTransaksiId(String transaksiId) {
		this.transaksiId = transaksiId;
	}
	/**
	 * @return the tanggal
	 */
	public String getTanggal() {
		return tanggal;
	}
	/**
	 * @param tanggal the tanggal to set
	 */
	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	

}
