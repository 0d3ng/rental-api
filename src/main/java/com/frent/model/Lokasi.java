/**
 * @author odeng
 *
 * Copyright © 2017 All Rights Reserved
 *
 */
package com.frent.model;

import java.io.Serializable;

public class Lokasi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5988588791425254481L;
	
	private int location_id;
	private String location_name;
	private String location_photo;
	private String location_detail;
	
	/**
	 * @return the location_id
	 */
	public int getLocation_id() {
		return location_id;
	}
	/**
	 * @param location_id the location_id to set
	 */
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}
	/**
	 * @return the location_name
	 */
	public String getLocation_name() {
		return location_name;
	}
	/**
	 * @param location_name the location_name to set
	 */
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}
	/**
	 * @return the location_photo
	 */
	public String getLocation_photo() {
		return location_photo;
	}
	/**
	 * @param location_photo the location_photo to set
	 */
	public void setLocation_photo(String location_photo) {
		this.location_photo = location_photo;
	}
	/**
	 * @return the location_detail
	 */
	public String getLocation_detail() {
		return location_detail;
	}
	/**
	 * @param location_detail the location_detail to set
	 */
	public void setLocation_detail(String location_detail) {
		this.location_detail = location_detail;
	}
	
	
	

}
