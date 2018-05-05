/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class TransaksiPinjam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4133720569136901584L;
	private String trxpinjam_id;
	private String trxpinjam_tanggalmulai;
	private String trxpinjam_tanggalselesai;
	private String trxpinjam_detail;
	private String trxpinjam_specialrequest;
	private int trxpinjam_totalunit;
	private int trxpinjam_totalnominal;
	private String trxpinjam_date;
	private int trxpinjam_kodebayar;
	private User user;
	private VendorLokasi vendorLokasi;
	private VendorUnit vendorUnit;
	/**
	 * @return the trxpinjam_id
	 */
	public String getTrxpinjam_id() {
		return trxpinjam_id;
	}
	/**
	 * @param trxpinjam_id the trxpinjam_id to set
	 */
	public void setTrxpinjam_id(String trxpinjam_id) {
		this.trxpinjam_id = trxpinjam_id;
	}
	/**
	 * @return the trxpinjam_tanggalmulai
	 */
	public String getTrxpinjam_tanggalmulai() {
		return trxpinjam_tanggalmulai;
	}
	/**
	 * @param trxpinjam_tanggalmulai the trxpinjam_tanggalmulai to set
	 */
	public void setTrxpinjam_tanggalmulai(String trxpinjam_tanggalmulai) {
		this.trxpinjam_tanggalmulai = trxpinjam_tanggalmulai;
	}
	/**
	 * @return the trxpinjam_tanggalselesai
	 */
	public String getTrxpinjam_tanggalselesai() {
		return trxpinjam_tanggalselesai;
	}
	/**
	 * @param trxpinjam_tanggalselesai the trxpinjam_tanggalselesai to set
	 */
	public void setTrxpinjam_tanggalselesai(String trxpinjam_tanggalselesai) {
		this.trxpinjam_tanggalselesai = trxpinjam_tanggalselesai;
	}
	/**
	 * @return the trxpinjam_detail
	 */
	public String getTrxpinjam_detail() {
		return trxpinjam_detail;
	}
	/**
	 * @param trxpinjam_detail the trxpinjam_detail to set
	 */
	public void setTrxpinjam_detail(String trxpinjam_detail) {
		this.trxpinjam_detail = trxpinjam_detail;
	}
	/**
	 * @return the trxpinjam_specialrequest
	 */
	public String getTrxpinjam_specialrequest() {
		return trxpinjam_specialrequest;
	}
	/**
	 * @param trxpinjam_specialrequest the trxpinjam_specialrequest to set
	 */
	public void setTrxpinjam_specialrequest(String trxpinjam_specialrequest) {
		this.trxpinjam_specialrequest = trxpinjam_specialrequest;
	}
	/**
	 * @return the trxpinjam_totalunit
	 */
	public int getTrxpinjam_totalunit() {
		return trxpinjam_totalunit;
	}
	/**
	 * @param trxpinjam_totalunit the trxpinjam_totalunit to set
	 */
	public void setTrxpinjam_totalunit(int trxpinjam_totalunit) {
		this.trxpinjam_totalunit = trxpinjam_totalunit;
	}
	/**
	 * @return the trxpinjam_totalnominal
	 */
	public int getTrxpinjam_totalnominal() {
		return trxpinjam_totalnominal;
	}
	/**
	 * @param trxpinjam_totalnominal the trxpinjam_totalnominal to set
	 */
	public void setTrxpinjam_totalnominal(int trxpinjam_totalnominal) {
		this.trxpinjam_totalnominal = trxpinjam_totalnominal;
	}
	/**
	 * @return the trxpinjam_date
	 */
	public String getTrxpinjam_date() {
		return trxpinjam_date;
	}
	/**
	 * @param trxpinjam_date the trxpinjam_date to set
	 */
	public void setTrxpinjam_date(String trxpinjam_date) {
		this.trxpinjam_date = trxpinjam_date;
	}
	/**
	 * @return the trxpinjam_kodebayar
	 */
	public int getTrxpinjam_kodebayar() {
		return trxpinjam_kodebayar;
	}
	/**
	 * @param trxpinjam_kodebayar the trxpinjam_kodebayar to set
	 */
	public void setTrxpinjam_kodebayar(int trxpinjam_kodebayar) {
		this.trxpinjam_kodebayar = trxpinjam_kodebayar;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
	/**
	 * @return the vendorUnit
	 */
	public VendorUnit getVendorUnit() {
		return vendorUnit;
	}
	/**
	 * @param vendorUnit the vendorUnit to set
	 */
	public void setVendorUnit(VendorUnit vendorUnit) {
		this.vendorUnit = vendorUnit;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransaksiPinjam [trxpinjam_id=" + trxpinjam_id + ", trxpinjam_tanggalmulai=" + trxpinjam_tanggalmulai
				+ ", trxpinjam_tanggalselesai=" + trxpinjam_tanggalselesai + ", trxpinjam_detail=" + trxpinjam_detail
				+ ", trxpinjam_specialrequest=" + trxpinjam_specialrequest + ", trxpinjam_totalunit="
				+ trxpinjam_totalunit + ", trxpinjam_totalnominal=" + trxpinjam_totalnominal + ", trxpinjam_date="
				+ trxpinjam_date + ", trxpinjam_kodebayar=" + trxpinjam_kodebayar + ", user=" + user + ", vendorLokasi="
				+ vendorLokasi + ", vendorUnit=" + vendorUnit + "]";
	}

	

}
