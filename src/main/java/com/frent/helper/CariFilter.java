/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

public class CariFilter {
	
	private int lokasiId;
	private int kategoriId;
	private int stok;
	private String startDate;
	private String endDate;
	private int[] unitId;
	private int[] vendorId;
	private boolean driver;
	private boolean bbm;
	private boolean tol;
	private int[] paketId;
	private boolean sortByPrice;
	/**
	 * @return the lokasiId
	 */
	public int getLokasiId() {
		return lokasiId;
	}
	/**
	 * @param lokasiId the lokasiId to set
	 */
	public void setLokasiId(int lokasiId) {
		this.lokasiId = lokasiId;
	}
	/**
	 * @return the kategoriId
	 */
	public int getKategoriId() {
		return kategoriId;
	}
	/**
	 * @param kategoriId the kategoriId to set
	 */
	public void setKategoriId(int kategoriId) {
		this.kategoriId = kategoriId;
	}
	/**
	 * @return the stok
	 */
	public int getStok() {
		return stok;
	}
	/**
	 * @param stok the stok to set
	 */
	public void setStok(int stok) {
		this.stok = stok;
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
	 * @return the unitId
	 */
	public int[] getUnitId() {
		return unitId;
	}
	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(int[] unitId) {
		this.unitId = unitId;
	}
	/**
	 * @return the vendorId
	 */
	public int[] getVendorId() {
		return vendorId;
	}
	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(int[] vendorId) {
		this.vendorId = vendorId;
	}
	/**
	 * @return the driver
	 */
	public boolean isDriver() {
		return driver;
	}
	/**
	 * @param driver the driver to set
	 */
	public void setDriver(boolean driver) {
		this.driver = driver;
	}
	/**
	 * @return the bbm
	 */
	public boolean isBbm() {
		return bbm;
	}
	/**
	 * @param bbm the bbm to set
	 */
	public void setBbm(boolean bbm) {
		this.bbm = bbm;
	}
	/**
	 * @return the tol
	 */
	public boolean isTol() {
		return tol;
	}
	/**
	 * @param tol the tol to set
	 */
	public void setTol(boolean tol) {
		this.tol = tol;
	}
	/**
	 * @return the paketId
	 */
	public int[] getPaketId() {
		return paketId;
	}
	/**
	 * @param paketId the paketId to set
	 */
	public void setPaketId(int[] paketId) {
		this.paketId = paketId;
	}
	/**
	 * @return the sortByPrice
	 */
	public boolean isSortByPrice() {
		return sortByPrice;
	}
	/**
	 * @param sortByPrice the sortByPrice to set
	 */
	public void setSortByPrice(boolean sortByPrice) {
		this.sortByPrice = sortByPrice;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CariFilter [lokasiId=" + lokasiId + ", kategoriId=" + kategoriId + ", stok=" + stok + ", startDate="
				+ startDate + ", endDate=" + endDate + ", unitId=" + unitId + ", vendorId=" + vendorId + ", driver="
				+ driver + ", bbm=" + bbm + ", tol=" + tol + ", paketId=" + paketId + ", sortByPrice=" + sortByPrice
				+ "]";
	}
	
	

}
