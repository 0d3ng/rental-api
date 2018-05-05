/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

public class TransaksiPinjamHelper {
	
	private String idTransaksi;
	private long userId;
	private int vendorLocationId;
	private int vendorUnitId;
	private String startDate;
	private String endDate;
	private String lokasiPenjemputan;
	private String note;
	private int totalUnit;
	private int totalNominal;
	private int kodeBayar;
	private String idCard;
	private String noHp;
	private String email;
	private String namaLengkap;
	/**
	 * @return the idTransaksi
	 */
	public String getIdTransaksi() {
		return idTransaksi;
	}
	/**
	 * @param idTransaksi the idTransaksi to set
	 */
	public void setIdTransaksi(String idTransaksi) {
		this.idTransaksi = idTransaksi;
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
	/**
	 * @return the vendorLocationId
	 */
	public int getVendorLocationId() {
		return vendorLocationId;
	}
	/**
	 * @param vendorLocationId the vendorLocationId to set
	 */
	public void setVendorLocationId(int vendorLocationId) {
		this.vendorLocationId = vendorLocationId;
	}
	/**
	 * @return the vendorUnitId
	 */
	public int getVendorUnitId() {
		return vendorUnitId;
	}
	/**
	 * @param vendorUnitId the vendorUnitId to set
	 */
	public void setVendorUnitId(int vendorUnitId) {
		this.vendorUnitId = vendorUnitId;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the lokasiPenjemputan
	 */
	public String getLokasiPenjemputan() {
		return lokasiPenjemputan;
	}
	/**
	 * @param lokasiPenjemputan the lokasiPenjemputan to set
	 */
	public void setLokasiPenjemputan(String lokasiPenjemputan) {
		this.lokasiPenjemputan = lokasiPenjemputan;
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
	 * @return the totalUnit
	 */
	public int getTotalUnit() {
		return totalUnit;
	}
	/**
	 * @param totalUnit the totalUnit to set
	 */
	public void setTotalUnit(int totalUnit) {
		this.totalUnit = totalUnit;
	}
	/**
	 * @return the totalNominal
	 */
	public int getTotalNominal() {
		return totalNominal;
	}
	/**
	 * @param totalNominal the totalNominal to set
	 */
	public void setTotalNominal(int totalNominal) {
		this.totalNominal = totalNominal;
	}
	/**
	 * @return the kodeBayar
	 */
	public int getKodeBayar() {
		return kodeBayar;
	}
	/**
	 * @param kodeBayar the kodeBayar to set
	 */
	public void setKodeBayar(int kodeBayar) {
		this.kodeBayar = kodeBayar;
	}
	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}
	/**
	 * @return the noHp
	 */
	public String getNoHp() {
		return noHp;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param noHp the noHp to set
	 */
	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}
	/**
	 * @param idCard the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	/**
	 * @return the namaLengkap
	 */
	public String getNamaLengkap() {
		return namaLengkap;
	}
	/**
	 * @param namaLengkap the namaLengkap to set
	 */
	public void setNamaLengkap(String namaLengkap) {
		this.namaLengkap = namaLengkap;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransaksiPinjamHelper [idTransaksi=" + idTransaksi + ", userId=" + userId + ", vendorLocationId="
				+ vendorLocationId + ", vendorUnitId=" + vendorUnitId + ", startDate=" + startDate + ", endDate="
				+ endDate + ", lokasiPenjemputan=" + lokasiPenjemputan + ", note=" + note + ", totalUnit=" + totalUnit
				+ ", totalNominal=" + totalNominal + ", kodeBayar=" + kodeBayar + ", idCard=" + idCard + ", noHp="
				+ noHp + ", email=" + email + ", namaLengkap=" + namaLengkap + "]";
	}
	
}
