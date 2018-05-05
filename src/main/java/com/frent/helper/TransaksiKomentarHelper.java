/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

public class TransaksiKomentarHelper {
	
	private long id;
	private String userId;
	private String transaksiId;
	private String isi;
	private int rate;
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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
	 * @return the isi
	 */
	public String getIsi() {
		return isi;
	}
	/**
	 * @param isi the isi to set
	 */
	public void setIsi(String isi) {
		this.isi = isi;
	}
	/**
	 * @return the rate
	 */
	public int getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(int rate) {
		this.rate = rate;
	}
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransaksiKomentarHelper [id=" + id + ", userId=" + userId + ", transaksiId=" + transaksiId + ", isi="
				+ isi + ", rate=" + rate + "]";
	}
	

}
