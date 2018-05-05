/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

public class Konfirmasi {
	
	private long id;
	private String transaksiId;
	private String tanggal;
	private String image;
	private int bank;
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
	 * @return the bank
	 */
	public int getBank() {
		return bank;
	}
	/**
	 * @param bank the bank to set
	 */
	public void setBank(int bank) {
		this.bank = bank;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Konfirmasi [id=" + id + ", transaksiId=" + transaksiId + ", tanggal=" + tanggal + ", image=" + image
				+ ", bank=" + bank + ", note=" + note + ", userId=" + userId + "]";
	}
	
}
