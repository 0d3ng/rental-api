/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {
	@Value("${config.frent.base-app}")
	private String baseApp;
	@Value("${config.frent.url-reset}")
	private String urlReset;
	@Value("${config.frent.resources.img.photo}")
	private String img;
	@Value("${config.frent.resources.img.unit}")
	private String imgUnit;
	@Value("${config.frent.resources.img.vendor}")
	private String imgVendor;
	@Value("${config.frent.resources.img.location}")
	private String imgLocation;
	@Value("${config.frent.resources.img.bank}")
	private String imgBank;
	@Value("${config.frent.resources.img.uploaded}")
	private String imgUploaded;
	@Value("${spring.social.google.appId}")
	private String googleAppid;
	/**
	 * @return the baseApp
	 */
	public String getBaseApp() {
		return baseApp;
	}
	/**
	 * @param baseApp the baseApp to set
	 */
	public void setBaseApp(String baseApp) {
		this.baseApp = baseApp;
	}
	/**
	 * @return the urlReset
	 */
	public String getUrlReset() {
		return urlReset;
	}
	/**
	 * @param urlReset the urlReset to set
	 */
	public void setUrlReset(String urlReset) {
		this.urlReset = urlReset;
	}
	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}
	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * @return the imgUnit
	 */
	public String getImgUnit() {
		return imgUnit;
	}
	/**
	 * @param imgUnit the imgUnit to set
	 */
	public void setImgUnit(String imgUnit) {
		this.imgUnit = imgUnit;
	}
	/**
	 * @return the imgVendor
	 */
	public String getImgVendor() {
		return imgVendor;
	}
	/**
	 * @param imgVendor the imgVendor to set
	 */
	public void setImgVendor(String imgVendor) {
		this.imgVendor = imgVendor;
	}
	/**
	 * @return the imgLocation
	 */
	public String getImgLocation() {
		return imgLocation;
	}
	/**
	 * @param imgLocation the imgLocation to set
	 */
	public void setImgLocation(String imgLocation) {
		this.imgLocation = imgLocation;
	}
	/**
	 * @return the imgBank
	 */
	public String getImgBank() {
		return imgBank;
	}
	/**
	 * @param imgBank the imgBank to set
	 */
	public void setImgBank(String imgBank) {
		this.imgBank = imgBank;
	}
	/**
	 * @return the imgUploaded
	 */
	public String getImgUploaded() {
		return imgUploaded;
	}
	/**
	 * @param imgUploaded the imgUploaded to set
	 */
	public void setImgUploaded(String imgUploaded) {
		this.imgUploaded = imgUploaded;
	}
	/**
	 * @return the googleAppid
	 */
	public String getGoogleAppid() {
		return googleAppid;
	}
	/**
	 * @param googleAppid the googleAppid to set
	 */
	public void setGoogleAppid(String googleAppid) {
		this.googleAppid = googleAppid;
	}
	
	
	

}
