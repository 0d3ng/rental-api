/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.helper;

public class VendorHelper {
	
	private int id;
	private String nama;
	private String logo;
	private String alamat;
	private String telfon;
	private String email;
	private String website;
	private String foto;
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
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * @return the alamat
	 */
	public String getAlamat() {
		return alamat;
	}
	/**
	 * @param alamat the alamat to set
	 */
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	/**
	 * @return the telfon
	 */
	public String getTelfon() {
		return telfon;
	}
	/**
	 * @param telfon the telfon to set
	 */
	public void setTelfon(String telfon) {
		this.telfon = telfon;
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
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}
	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	/**
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VendorHelper [id=" + id + ", nama=" + nama + ", logo=" + logo + ", alamat=" + alamat + ", telfon="
				+ telfon + ", email=" + email + ", website=" + website + ", foto=" + foto + "]";
	}
	

}
